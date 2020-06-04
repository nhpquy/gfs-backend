package com.gfs.domain.repository.impl;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.document.GFSFile;
import com.gfs.domain.repository.extend.GFSFileRepositoryExtend;
import com.gfs.domain.request.ListOwnerFilesPagingRequest;
import com.gfs.domain.utils.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GFSFileRepositoryImpl implements GFSFileRepositoryExtend {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public GFSFile updateGFSFileDetail(String ownerId, String hashCode, Map<String, Object> updateValues) {
        Query query = new Query();
        query.addCriteria(Criteria.where("hash_code").is(hashCode));
        query.addCriteria(Criteria.where("owner_id").is(ownerId));
        Update update = new Update();
        update.set("updated_at", System.currentTimeMillis());
        for (String key : updateValues.keySet()) {
            if (updateValues.get(key) == null)
                update.unset(key);
            else update.set(key, updateValues.get(key));
        }
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        return mongoTemplate.findAndModify(query, update, options, GFSFile.class, CollectionName.GFS_FILE);
    }

    @Override
    public List<GFSFile> findByOwnerIdPaging(String ownerId, ListOwnerFilesPagingRequest request) {
        Criteria criteria = MongoUtils.createPagingCriteria(request);
        criteria = criteria.and("owner_id").is(ownerId);
        if (request.getStatus() != null) {
            criteria = criteria.and("status").is(request.getStatus());
        }
        if (request.getEncrypted() != null) {
            criteria = criteria.and("encrypted").is(request.getEncrypted());
        }
        Query query = new Query();
        query.addCriteria(criteria);
        query.limit(request.getLimit());
        query.with(MongoUtils.getSortDirection(request));
        return mongoTemplate.find(query, GFSFile.class, CollectionName.GFS_FILE);
    }
}
