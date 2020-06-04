package com.gfs.domain.repository.inf;

import com.gfs.domain.document.GFSFile;
import com.gfs.domain.repository.extend.GFSFileRepositoryExtend;
import org.springframework.data.mongodb.repository.Query;

public interface GFSFileRepository extends ObjectIdMongoRepository<GFSFile>,
        GFSFileRepositoryExtend {
    @Query(value = "{'hash_code' : ?0, 'owner_id' : ?1}")
    public GFSFile findByHash_codeAndOwner_id(String hashCode, String ownerId);
}
