package com.gfs.domain.repository.inf;

import com.gfs.domain.document.Action;
import com.gfs.domain.repository.extend.ActionRepositoryExtend;

public interface ActionRepository extends ObjectIdMongoRepository<Action>,
        ActionRepositoryExtend {

}
