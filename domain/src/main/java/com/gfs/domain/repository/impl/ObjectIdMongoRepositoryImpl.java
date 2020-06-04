package com.gfs.domain.repository.impl;

import com.gfs.domain.document.ObjectIdDocument;
import com.gfs.domain.repository.extend.ObjectIdMongoRepositoryExtend;
import com.gfs.domain.request.PagingRequest;
import com.gfs.domain.utils.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ObjectIdMongoRepositoryImpl<T extends ObjectIdDocument> implements ObjectIdMongoRepositoryExtend<T> {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<T> findByPaging(PagingRequest pagingRequest, Map<String, Object> filter, Class<T> clazz) {
        Criteria criteria = MongoUtils.createPagingCriteria(pagingRequest);
        for (String key : filter.keySet()) {
            if (filter.get(key) != null)
                criteria = criteria.and(key).is(filter.get(key));
        }
        Query query = new Query(criteria);
        query.limit(pagingRequest.getLimit());

        query.with(MongoUtils.getSortDirection(pagingRequest));
        return mongoTemplate.find(query, clazz);
    }
}
