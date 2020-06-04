package com.gfs.domain.repository.impl;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.document.StudentAccount;
import com.gfs.domain.enums.AccountStatus;
import com.gfs.domain.model.aggregate.AdminStudentAggResult;
import com.gfs.domain.repository.extend.StudentAccountRepositoryExtend;
import com.gfs.domain.request.AdminListStudentRequest;
import com.gfs.domain.request.PhoneNumberRequest;
import com.gfs.domain.utils.MongoUtils;
import com.gfs.domain.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Repository
public class StudentAccountRepositoryImpl implements StudentAccountRepositoryExtend {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public StudentAccount updateAccountDetail(String accountId, Map<String, Object> updateValues) {
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
        return mongoTemplate.findAndModify(query, update, options, StudentAccount.class, CollectionName.STUDENT_ACCOUNTS);
    }

    @Override
    public List<StudentAccount> findByPhoneNumberRegex(PhoneNumberRequest request) {
        Criteria criteria = MongoUtils.createPagingCriteria(request);
        String phoneNumber = StringUtils.getOnlyDigits(request.getPhone_number());
        criteria = criteria.and("phone_number").regex(Pattern.compile(phoneNumber, Pattern.CASE_INSENSITIVE));
        criteria = criteria.and("account_status").in(Arrays.asList(AccountStatus.activated, AccountStatus.approved));
        Query query = new Query();
        query.addCriteria(criteria);
        query.limit(request.getLimit());
        query.with(MongoUtils.getSortDirection(request));
        return mongoTemplate.find(query, StudentAccount.class, CollectionName.STUDENT_ACCOUNTS);
    }

    @Override
    public List<AdminStudentAggResult> listStudent(AdminListStudentRequest request) {
        return null;
    }
}
