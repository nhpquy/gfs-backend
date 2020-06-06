package com.gfs.domain.repository.impl;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.document.GFSSharedFile;
import com.gfs.domain.repository.extend.GFSSharedFileRepositoryExtend;
import com.gfs.domain.request.ListReceivedFilesPagingRequest;
import com.gfs.domain.request.ListSharedFilesPagingRequest;
import com.gfs.domain.request.PagingRequest;
import com.gfs.domain.utils.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GFSSharedFileRepositoryImpl implements GFSSharedFileRepositoryExtend {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<GFSSharedFile> findByOwnerIdPaging(String ownerId, ListSharedFilesPagingRequest request) {
        Map<String, Object> filter = new HashMap<>();
        filter.put("owner_id", ownerId);
        if (request.getReceiver_id() != null) {
            filter.put("receiver_id", request.getReceiver_id());
        }
        return listInPaging(request, filter);
    }

    @Override
    public List<GFSSharedFile> findByReceiverIdPaging(String receiverId, ListReceivedFilesPagingRequest request) {
        Map<String, Object> filter = new HashMap<>();
        filter.put("receiver_id", receiverId);
        if (request.getSender_id() != null) {
            filter.put("owner_id", request.getSender_id());
        }
        return listInPaging(request, filter);
    }

    private List<GFSSharedFile> listInPaging(PagingRequest request, Map<String, Object> filter) {
        Criteria criteria = MongoUtils.createPagingCriteria(request);
        for (String key : filter.keySet()) {
            criteria = criteria.and(key).is(filter.get(key));
        }
        Query query = new Query();
        query.addCriteria(criteria);
        query.limit(request.getLimit());
        query.with(MongoUtils.getSortDirection(request));
        return mongoTemplate.find(query, GFSSharedFile.class, CollectionName.GFS_SHARED_FILE);
    }
}

