package com.gfs.domain.repository.impl;

import com.gfs.domain.document.BeowulfWallet;
import com.gfs.domain.repository.extend.BeowulfWalletRepositoryExtend;
import com.gfs.domain.request.ListBeowulfOrgWalletPagingRequest;
import com.gfs.domain.utils.MongoUtils;
import com.gfs.domain.utils.StringUtils;
import com.gfs.domain.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BeowulfWalletRepositoryImpl implements BeowulfWalletRepositoryExtend {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<BeowulfWallet> findWalletPaging(ListBeowulfOrgWalletPagingRequest request) {
        Criteria criteria = MongoUtils.createPagingCriteria(request);
        Query query = new Query(criteria);
        if (StringUtils.hasText(request.getWallet_name())) {
            query.addCriteria(Criteria.where("name").is(request.getWallet_name()));
        }
        query.limit(request.getLimit());
        query.with(MongoUtils.getSortDirection(request));
        List<BeowulfWallet> templates = mongoTemplate.find(query, BeowulfWallet.class);
        return Utils.processPagingResponse(templates, request.getAction());
    }
}
