package com.gfs.domain.repository.inf;

import com.gfs.domain.document.Organization;
import com.gfs.domain.repository.extend.OrganizationRepositoryExtend;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface OrganizationRepository extends MongoRepository<Organization, ObjectId>,
        OrganizationRepositoryExtend {
    @Query(value = "{'owner_id' : ?0}")
    public Organization findByOwner_id(String ownerId);

    @Query(value = "{'org_id' : ?0}")
    public Organization findByOrg_id(String orgId);

    @Query(value = "{'approved_sub_domain' : ?0}")
    public Organization findByApproved_sub_domain(String subDomain);
}
