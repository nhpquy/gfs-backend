package com.gfs.domain.repository.inf;

import com.gfs.domain.document.Configuration;
import com.gfs.domain.repository.extend.ConfigurationRepositoryExtend;

public interface ConfigurationRepository extends ObjectIdMongoRepository<Configuration>,
        ConfigurationRepositoryExtend {
}
