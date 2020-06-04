package com.gfs.domain.repository.inf;

import com.gfs.domain.document.GFSSharedFile;
import com.gfs.domain.repository.extend.GFSSharedFileRepositoryExtend;
import org.springframework.data.mongodb.repository.Query;

public interface GFSSharedFileRepository extends ObjectIdMongoRepository<GFSSharedFile>,
        GFSSharedFileRepositoryExtend {
    @Query(value = "{'share_id' : ?0}")
    public GFSSharedFile findByShare_id(String shareId);
}
