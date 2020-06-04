package com.gfs.domain.repository.impl;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.document.Account;
import com.gfs.domain.document.StudentAccount;
import com.gfs.domain.document.TutorAccount;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.repository.inf.AccountRepository;
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
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Account updateAccountDetail(Account account, Map<String, Object> updateValues) {
        return updateAccountDetail(account.getProfile_type(), account.getAccount_id(), updateValues);
    }

    @Override
    public Account updateAccountDetail(AccountProfile profile, String accountId, Map<String, Object> updateValues) {
        Query query = new Query();
        query.addCriteria(Criteria.where("account_id").is(accountId));
        Update update = new Update();
        update.set("updated_at", System.currentTimeMillis());
        for (String key : updateValues.keySet()) {
            if (updateValues.get(key) == null)
                update.unset(key);
            else update.set(key, updateValues.get(key));
        }
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        if (AccountProfile.student == profile)
            return mongoTemplate.findAndModify(query, update, options, StudentAccount.class, CollectionName.STUDENT_ACCOUNTS);
        else if (AccountProfile.tutor == profile)
            return mongoTemplate.findAndModify(query, update, options, TutorAccount.class, CollectionName.TUTOR_ACCOUNTS);
        else return null;
    }

    @Override
    public Account findAccountByEmail(AccountProfile profile, String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        if (AccountProfile.student == profile)
            return mongoTemplate.findOne(query, StudentAccount.class, CollectionName.STUDENT_ACCOUNTS);
        else if (AccountProfile.tutor == profile)
            return mongoTemplate.findOne(query, TutorAccount.class, CollectionName.TUTOR_ACCOUNTS);
        return null;
    }

    @Override
    public Account findAccountByAccountId(AccountProfile profile, String accountId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("account_id").is(accountId));
        if (AccountProfile.student == profile)
            return mongoTemplate.findOne(query, StudentAccount.class, CollectionName.STUDENT_ACCOUNTS);
        else if (AccountProfile.tutor == profile)
            return mongoTemplate.findOne(query, TutorAccount.class, CollectionName.TUTOR_ACCOUNTS);
        return null;
    }

    @Override
    public Account setPassword(AccountProfile profile, String accountId, String securePassword) {
        Map<String, Object> updateValues = new HashMap<>();
        updateValues.put("password", securePassword);
        updateValues.put("secure_pwd", true);
        return updateAccountDetail(profile, accountId, updateValues);
    }
}
