package com.gfs.domain.repository.impl;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.document.TutorCertificate;
import com.gfs.domain.repository.extend.TutorCertificateRepositoryExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TutorCertificateRepositoryImpl implements TutorCertificateRepositoryExtend {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public TutorCertificate updateDetail(String certificateId, Map<String, Object> updateValues) {
        Query query = new Query();
        query.addCriteria(Criteria.where("certificate_id").is(certificateId));
        Update update = new Update();
        update.set("updated_at", System.currentTimeMillis());
        if (updateValues != null) {
            for (String key : updateValues.keySet()) {
                if (updateValues.get(key) == null)
                    update.unset(key);
                else update.set(key, updateValues.get(key));
            }
        }
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        return mongoTemplate.findAndModify(query, update, options, TutorCertificate.class, CollectionName.TUTOR_CERTIFICATES);
    }
}
