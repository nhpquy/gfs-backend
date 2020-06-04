package com.gfs.domain.repository.impl;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.document.Configuration;
import com.gfs.domain.repository.extend.ConfigurationRepositoryExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigurationRepositoryImpl implements ConfigurationRepositoryExtend {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public <T extends Configuration> T findByKey(String key, Class<T> _class) {
        Query query = new Query();
        query.addCriteria(Criteria.where("key").is(key));
        return mongoTemplate.findOne(query, _class, CollectionName.CONFIGURATIONS);
    }
}
