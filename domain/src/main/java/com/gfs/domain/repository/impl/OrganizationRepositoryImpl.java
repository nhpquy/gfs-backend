package com.gfs.domain.repository.impl;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.document.Organization;
import com.gfs.domain.repository.extend.OrganizationRepositoryExtend;
import com.gfs.domain.request.ListOrgPagingRequest;
import com.gfs.domain.utils.MongoUtils;
import com.gfs.domain.utils.StringUtils;
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
import java.util.regex.Pattern;

@Repository
public class OrganizationRepositoryImpl implements OrganizationRepositoryExtend {
    private static final Integer MAXIMUM_WALLETS = 1;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Organization updateDetail(String orgId, Map<String, Object> updateValues) {
        Query query = new Query();
        query.addCriteria(Criteria.where("org_id").is(orgId));
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
        return mongoTemplate.findAndModify(query, update, options, Organization.class, CollectionName.TUTOR_ORGS);
    }


    @Override
    public Organization decreaseWalletBwfNumber(String orgId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("org_id").is(orgId).and("bwf_wallet_count").gt(0));
        Update update = new Update();
        update.inc("bwf_wallet_count", -1);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true);
        return mongoTemplate.findAndModify(query, update, options, Organization.class);
    }

    @Override
    public Organization increaseWalletBwfNumber(String accountId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("org_id").is(accountId).and("bwf_wallet_count").lt(MAXIMUM_WALLETS));
        Update update = new Update();
        update.inc("bwf_wallet_count", 1);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true);
        return mongoTemplate.findAndModify(query, update, options, Organization.class);
    }

    @Override
    public List<Organization> findOrganizationPaging(ListOrgPagingRequest request) {
        Criteria criteria = MongoUtils.createPagingCriteria(request);
        Query query = new Query(criteria);
        if (StringUtils.hasText(request.getOrg_name())) {
            query.addCriteria(Criteria.where("name").regex(Pattern.compile(request.getOrg_name(), Pattern.CASE_INSENSITIVE)));
        }
        if (StringUtils.hasText(request.getOrg_email())) {
            String handledEmail = StringUtils.handleEmailOrPhoneNumber(request.getOrg_email());
            query.addCriteria(Criteria.where("org_email").regex(Pattern.compile(handledEmail, Pattern.CASE_INSENSITIVE)));
        }
        if (request.getStatuses() != null) {
            query.addCriteria(Criteria.where("status").in(request.getStatuses()));
        }
        query.limit(request.getLimit());
        query.with(MongoUtils.getSortDirection(request));
        List<Organization> templates = mongoTemplate.find(query, Organization.class);
        return Utils.processPagingResponse(templates, request.getAction());
    }

}
