package com.gfs.domain.repository.impl;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.document.Action;
import com.gfs.domain.repository.extend.ActionRepositoryExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ActionRepositoryImpl implements ActionRepositoryExtend {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public boolean addNewAction(Action action) {
        mongoTemplate.save(action);
        return true;
    }

    @Override
    public boolean removeAction(Action action) {
        Query query = new Query();
        query.addCriteria(new Criteria()
                .andOperator(Criteria.where("name").is(action.getName()),
                        Criteria.where("action").is(action.getAction())));
        mongoTemplate.remove(query, Action.class, CollectionName.ACTIONS);
        return true;
    }

    @Override
    public void updateAction(Action action) {
        Query query = new Query();
        query.addCriteria(new Criteria()
                .andOperator(Criteria.where("name").is(action.getName()),
                        Criteria.where("action").is(action.getAction())));
        Update update = new Update();
        update.set("createTime", action.getCreateTime());
        mongoTemplate.findAndModify(query, update, Action.class, CollectionName.ACTIONS);
    }
}
