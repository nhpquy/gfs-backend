package com.gfs.domain.repository.impl;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.document.GFSSharedFile;
import com.gfs.domain.repository.extend.GFSSharedFileRepositoryExtend;
import com.gfs.domain.request.ListSharedFilesPagingRequest;
import com.gfs.domain.utils.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GFSSharedFileRepositoryImpl implements GFSSharedFileRepositoryExtend {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<GFSSharedFile> findByOwnerIdPaging(String ownerId, ListSharedFilesPagingRequest request) {
        Criteria criteria = MongoUtils.createPagingCriteria(request);
        criteria = criteria.and("owner_id").is(ownerId);
        if (request.getReceiver_id() != null) {
            criteria = criteria.and("receiver_id").is(request.getReceiver_id());
        }
        Query query = new Query();
        query.addCriteria(criteria);
        query.limit(request.getLimit());
        query.with(MongoUtils.getSortDirection(request));
        return mongoTemplate.find(query, GFSSharedFile.class, CollectionName.GFS_SHARED_FILE);
    }
}

