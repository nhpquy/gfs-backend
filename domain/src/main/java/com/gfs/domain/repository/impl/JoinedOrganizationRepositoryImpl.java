package com.gfs.domain.repository.impl;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.document.JoinedOrganization;
import com.gfs.domain.enums.OrgInvitationStatus;
import com.gfs.domain.enums.OrgRole;
import com.gfs.domain.model.aggregate.JoinedOrganizationAggResult;
import com.gfs.domain.repository.extend.JoinedOrganizationRepositoryExtend;
import com.gfs.domain.request.GetOrgInvitationPagingRequest;
import com.gfs.domain.request.GetPartnerInvitationPagingRequest;
import com.gfs.domain.utils.MongoUtils;
import com.gfs.domain.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class JoinedOrganizationRepositoryImpl implements JoinedOrganizationRepositoryExtend {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public JoinedOrganization updateInvitation(JoinedOrganization invitation, OrgInvitationStatus status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("account_id").is(invitation.getAccount_id()).and("org_id").is(invitation.getOrg_id()));
        Update update = new Update();
        update.set("updated_at", System.currentTimeMillis());
        update.set("status", status);
        if (status == OrgInvitationStatus.denied || status == OrgInvitationStatus.kicked) {
            update.set("role", OrgRole.none);
        }
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        return mongoTemplate.findAndModify(query, update, options, JoinedOrganization.class, CollectionName.JOINED_ORGS);
    }

    @Override
    public JoinedOrganization updateDetail(JoinedOrganization invitation, Map<String, Object> updateValues) {
        Query query = new Query();
        query.addCriteria(Criteria.where("org_id").is(invitation.getOrg_id()).and("account_id").is(invitation.getAccount_id()));
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
        return mongoTemplate.findAndModify(query, update, options, JoinedOrganization.class, CollectionName.JOINED_ORGS);
    }

    /**
     * Account get paging invitation to Organization
     *
     * @param accountId partner id
     * @param request   paging request
     * @param filter    filter map for queries
     * @return aggregate joined org result object
     */
    @Override
    public List<JoinedOrganizationAggResult> findByPartnerIdPaging(String accountId, GetOrgInvitationPagingRequest request, Map<String, Object> filter) {

        // Step 1: LEFT JOIN 2 collections (JOINED_ORGS & ORG) with same key org_id
        LookupOperation orgLookupOperation = Aggregation.lookup(CollectionName.TUTOR_ORGS, "org_id", "org_id", "organization");
        UnwindOperation unwindOperation = Aggregation.unwind("$organization");

        // Step 2: create WHERE conditions for paging responses
        Criteria criteria = MongoUtils.createPagingCriteria(request);
        criteria = criteria.and("account_id").is(accountId);
        criteria = criteria.and("role").ne(OrgRole.none);
        criteria = criteria.andOperator(MongoUtils.addFilterCriteria(filter));
        MatchOperation matchOperation = Aggregation.match(criteria);

        // Step 3: create LIMIT & SORT request
        LimitOperation limitOperation = Aggregation.limit(request.getLimit());
        SortOperation sortOperation = Aggregation.sort(MongoUtils.getSortDirection(request));

        // Step 4: do Aggregate
        AggregationResults<JoinedOrganizationAggResult> results = mongoTemplate.aggregate(
                Aggregation.newAggregation(
                        matchOperation,
                        sortOperation,
                        limitOperation,
                        orgLookupOperation,
                        unwindOperation),
                CollectionName.JOINED_ORGS, JoinedOrganizationAggResult.class);

        List<JoinedOrganizationAggResult> aggResults = new ArrayList<>(results.getMappedResults());

        // Step 5: return responses in direction request (next or previous)
        return Utils.processPagingResponse(aggResults, request.getAction());
    }

    /**
     * Owner get paging invitation
     *
     * @param orgId   Org id of owner
     * @param request paging request
     * @param filter  filter map for queries
     * @return aggregate joined org result object
     */
    @Override
    public List<JoinedOrganizationAggResult> findByOrgIdPaging(String orgId, GetPartnerInvitationPagingRequest request, Map<String, Object> filter) {

        // Step 1: LEFT JOIN 2 collections (JOINED_ORGS & ACCOUNTS) with same key account_id
        LookupOperation partnerLookupOperation = Aggregation.lookup(CollectionName.TUTOR_ORGS, "account_id", "account_id", "partner");
        UnwindOperation unwindOperation = Aggregation.unwind("$partner", true);

        // Step 2: create WHERE conditions for paging responses
        Criteria criteria = MongoUtils.createPagingCriteria(request);
        criteria = criteria.and("org_id").is(orgId);
        criteria = criteria.andOperator(MongoUtils.addFilterCriteria(filter));
        MatchOperation matchOperation = Aggregation.match(criteria);

        // Step 3: create LIMIT & SORT request
        LimitOperation limitOperation = Aggregation.limit(request.getLimit());
        SortOperation sortOperation = Aggregation.sort(MongoUtils.getSortDirection(request));

        // Step 4: do Aggregate
        AggregationResults<JoinedOrganizationAggResult> results = mongoTemplate.aggregate(
                Aggregation.newAggregation(
                        matchOperation,
                        sortOperation,
                        limitOperation,
                        partnerLookupOperation,
                        unwindOperation),
                CollectionName.JOINED_ORGS, JoinedOrganizationAggResult.class);

        List<JoinedOrganizationAggResult> aggResults = new ArrayList<>(results.getMappedResults());

        // Step 5: return responses in direction request (next or previous)
        return Utils.processPagingResponse(aggResults, request.getAction());
    }
}
