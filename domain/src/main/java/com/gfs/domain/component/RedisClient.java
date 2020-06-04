package com.gfs.domain.component;

import com.gfs.domain.config.model.RedisServerConfig;
import com.gfs.domain.constant.RedisConstant;
import com.gfs.domain.repository.inf.ConfigurationRepository;
import com.gfs.domain.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PreDestroy;
import java.util.Collections;

import static com.gfs.domain.config.model.RedisServerConfig.REDIS_CONFIG_KEY;

@Component
public class RedisClient {
    private static final String TAG = RedisClient.class.getName();

    private RedisServerConfig config;
    private JedisPoolConfig poolConfig;
    private JedisPool jedisPool;

    private static RedisClient instance;
    private static ConfigurationRepository configurationRepository;

    @Autowired
    public RedisClient(ConfigurationRepository configurationRepository) {
        instance = this;
        RedisClient.configurationRepository = configurationRepository;
        init();
    }

    @PreDestroy
    public void onDestroy() {
        try {
            if (instance != null && instance.jedisPool != null)
                instance.jedisPool.destroy();
        } catch (Exception e) {
        }
    }

    private JedisPoolConfig buildConfig() {
        final JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(this.config.getMax_pool());
        config.setMaxIdle(10);
        config.setMinIdle(1);
        config.setMaxWaitMillis(10000);
        return config;
    }

    private void init() {
        try {
            LoggerUtil.i(this, "Init RedisClient");
            config = configurationRepository.findByKey(REDIS_CONFIG_KEY, RedisServerConfig.class);

            // TODO: 10/17/19 init redis util and move redis util to domain package
            if (config == null) {
                config = new RedisServerConfig();
                configurationRepository.save(config);
            }
            poolConfig = buildConfig();
            jedisPool = new JedisPool(poolConfig, config.getHost(), config.getPort(), 10000);
            LoggerUtil.i(this, "Init RedisClient successfully with host=" + config.getHost() + " and port=" + config.getPort());

        } catch (Exception e) {
            LoggerUtil.exception(TAG, e, true);
        }
    }

    public static Jedis getClient() {
        try {
            if (instance != null && instance.jedisPool != null)
                return instance.jedisPool.getResource();
        } catch (Exception e) {
            LoggerUtil.e(TAG, "Could not get resource from the pool - " + e.getMessage() + "\n");
        }
        return null;
    }

    @Bean(name = "jedisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(config.getHost());
        configuration.setPort(config.getPort());

        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(configuration);
//        connectionFactory.setHostName(config.getHost());
//        connectionFactory.setPort(config.getPort());
//        connectionFactory.setTimeout(10000);
        connectionFactory.setPoolConfig(buildConfig());
        return connectionFactory;
    }

    @Bean(name = "redisMessageListener")
    public MessageListenerAdapter redisMessageListener(RedisKeyExpireHandler handler) {
        return new MessageListenerAdapter(handler);
    }

    @Bean(name = "redisMessageListenerContainer")
    public RedisMessageListenerContainer redisMessageListenerContainer(JedisConnectionFactory jedisConnectionFactory, MessageListenerAdapter redisMessageListener) {
        LoggerUtil.i(TAG, "create bean: redisMessageListenerContainer");
        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        listenerContainer.setConnectionFactory(jedisConnectionFactory);
        listenerContainer.setMessageListeners(Collections.singletonMap(redisMessageListener, Collections.singletonList(new PatternTopic(RedisConstant.TOPIC_KEY_EXPIRED))));
        return listenerContainer;
    }
}
