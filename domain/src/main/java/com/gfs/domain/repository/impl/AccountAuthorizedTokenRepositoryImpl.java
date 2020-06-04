package com.gfs.domain.repository.impl;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.enums.AuthorizeTokenRole;
import com.gfs.domain.repository.extend.AccountAuthorizedTokenRepositoryExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountAuthorizedTokenRepositoryImpl implements AccountAuthorizedTokenRepositoryExtend {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public AccountAuthorizedToken updateDetail(String token, Map<String, Object> updateValues) {
        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(token));
        Update update = new Update();
        update.set("updated_at", System.currentTimeMillis());
        for (String key : updateValues.keySet()) {
            if (updateValues.get(key) == null)
                update.unset(key);
            else update.set(key, updateValues.get(key));
        }
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        return mongoTemplate.findAndModify(query, update, options, AccountAuthorizedToken.class, CollectionName.ACCOUNT_AUTH_TOKENS);
    }

    @Override
    public AccountAuthorizedToken activated(AccountAuthorizedToken authorizedToken) {
        Map<String, Object> updateValues = new HashMap<>();
        updateValues.put("current_requirement", null);
        updateValues.put("role", AuthorizeTokenRole.authorized);
        return updateDetail(authorizedToken.getToken(), updateValues);
    }

    @Override
    public void deleteOtherToken(String accountId, String token) {
        Query query = new Query();
        query.addCriteria(Criteria.where("account_id").is(accountId).and("token").ne(token));
        mongoTemplate.remove(query, CollectionName.ACCOUNT_AUTH_TOKENS);
    }
}
