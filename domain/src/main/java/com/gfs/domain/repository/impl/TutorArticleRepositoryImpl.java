package com.gfs.domain.repository.impl;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.document.TutorArticle;
import com.gfs.domain.repository.extend.TutorArticleRepositoryExtend;
import com.gfs.domain.request.GetTutorArticlePagingRequest;
import com.gfs.domain.utils.MongoUtils;
import com.gfs.domain.utils.Utils;
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
public class TutorArticleRepositoryImpl implements TutorArticleRepositoryExtend {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public TutorArticle updateDetail(String articleId, Map<String, Object> updateValues) {
        Query query = new Query();
        query.addCriteria(Criteria.where("article_id").is(articleId));
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
        return mongoTemplate.findAndModify(query, update, options, TutorArticle.class, CollectionName.TUTOR_ARTICLES);
    }

    @Override
    public List<TutorArticle> findArticlesByTutorIdPaging(String tutorId, GetTutorArticlePagingRequest request) {
        Criteria criteria = MongoUtils.createPagingCriteria(request);
        Query query = new Query(criteria);
        query.addCriteria(Criteria.where("tutor_id").is(tutorId));
        if (request.getStatuses() != null) {
            query.addCriteria(Criteria.where("status").in(request.getStatuses()));
        }
        query.limit(request.getLimit());
        query.with(MongoUtils.getSortDirection(request));
        List<TutorArticle> templates = mongoTemplate.find(query, TutorArticle.class);
        return Utils.processPagingResponse(templates, request.getAction());
    }
}
