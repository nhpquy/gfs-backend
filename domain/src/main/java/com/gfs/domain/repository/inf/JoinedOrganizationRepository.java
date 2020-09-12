package com.gfs.domain.repository.inf;

import com.gfs.domain.document.JoinedOrganization;
import com.gfs.domain.repository.extend.JoinedOrganizationRepositoryExtend;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface JoinedOrganizationRepository extends MongoRepository<JoinedOrganization, ObjectId>,
        JoinedOrganizationRepositoryExtend {
    @Query(value = "{'org_id' : ?0, 'account_id':?1}")
    public JoinedOrganization findByOrg_idAndAccount_id(String orgId, String accountId);
}
