package com.gfs.domain.config;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.utils.AESEncryptor;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableMongoRepositories({"com.gfs.domain.*"})
public class MongoConfig {

    private static final String TAG = MongoConfig.class.getName();
    private static final Logger logger = LoggerFactory.getLogger(TAG);

    private static final String AES_SECRET = "OWevkWLEpHQEZFoz";
    private static final String AES_INIT_VECTOR = "daKAaueshtCkzIQt";

    static MongoTemplate mongoTemplate;
    static MongoMappingContext mongoMappingContext;

    @Value("${mongo.db.name}")
    private String mongoDbName;
    @Value("${mongo.db.hosts}")
    private String mongoHosts;
    @Value("${mongo.db.ssl}")
    private boolean mongoSSL;
    @Value("${mongo.db.connection.pool}")
    private int mongoConnectionPool;
    @Value("${mongo.user.name}")
    private String mongoUsername;
    @Value("${mongo.user.password}")
    private String mongoPassword;

    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {
        logger.info(String.format("Start Connecting with: mongodb://%s/%s", mongoHosts, mongoDbName));
        context.setAutoIndexCreation(false);
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
//        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
        MongoConfig.mongoTemplate = mongoTemplate;
        MongoConfig.mongoMappingContext = context;
        ensureIndexes(mongoTemplate);
        logger.info(String.format("Connected with: mongodb://%s/%s", mongoHosts, mongoDbName));
        return mongoTemplate;
    }

    @Bean
    public MongoDbFactory mongoDbFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDbFactory(mongoClient, mongoDbName);
    }

    @Bean
    public MongoClient mongoClient(MongoClientSettings mongoClientSettings) {
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoClientSettings mongoClientSettings() {
        String connectionStr = String.format("mongodb://%s:%s@%s/%s",
                mongoUsername,
                AESEncryptor.decrypt(AES_SECRET, AES_INIT_VECTOR, mongoPassword),
                mongoHosts,
                mongoDbName);

        MongoClientSettings.Builder builder = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionStr))
                .applyToSslSettings(sslBuilder -> {
                    sslBuilder.enabled(mongoSSL)
                            .invalidHostNameAllowed(true);
                })
                .applyToConnectionPoolSettings(poolSetting -> {
                    poolSetting.maxConnectionIdleTime(20000, TimeUnit.MILLISECONDS)
                            .maxWaitQueueSize(1000)
                            .maxSize(mongoConnectionPool);
                })
                .applyToSocketSettings(socketSetting -> {
                    socketSetting.connectTimeout(1800000, TimeUnit.MILLISECONDS)
                            .readTimeout(5000, TimeUnit.MILLISECONDS);
                })
                .readPreference(ReadPreference.nearest());
        return builder.build();
    }

//    @Bean
//    MongoTransactionManager mongoTransactionManager(MongoDbFactory dbFactory) {
//        return new MongoTransactionManager(dbFactory);
//    }

    @EventListener(ApplicationReadyEvent.class)
    public void initIndicesAfterStartup() {
        Reflections reflections = new Reflections("com.gfs.domain.document");
        Set<Class<? extends Object>> classes = reflections.getTypesAnnotatedWith(Document.class);
        IndexResolver resolver = new MongoPersistentEntityIndexResolver(mongoMappingContext);
        for (Class<? extends Object> _class : classes) {
            logger.info("ensure index for class: " + _class.getName());
            IndexOperations indexOps = mongoTemplate.indexOps(_class);
            resolver.resolveIndexFor(_class).forEach(indexOps::ensureIndex);
        }
    }

    // create unique index but ignore null value
    private void ensureIndexes(MongoTemplate mongoTemplate) {
        Index fbIdIndex = new Index()
                .background()
                .unique()
                .named("fb_account_id_1")
                .on("fb_account_id", Sort.Direction.ASC)
                .partial(PartialIndexFilter.of(Criteria.where("fb_account_id").exists(true)));
        mongoTemplate.indexOps(CollectionName.STUDENT_ACCOUNTS).ensureIndex(fbIdIndex);
        mongoTemplate.indexOps(CollectionName.TUTOR_ACCOUNTS).ensureIndex(fbIdIndex);

        Index ggIdIndex = new Index()
                .background()
                .unique()
                .named("gg_account_id_1")
                .on("gg_account_id", Sort.Direction.ASC)
                .partial(PartialIndexFilter.of(Criteria.where("gg_account_id").exists(true)));
        mongoTemplate.indexOps(CollectionName.STUDENT_ACCOUNTS).ensureIndex(ggIdIndex);
        mongoTemplate.indexOps(CollectionName.TUTOR_ACCOUNTS).ensureIndex(ggIdIndex);
    }
}
