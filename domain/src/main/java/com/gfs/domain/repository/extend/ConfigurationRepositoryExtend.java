package com.gfs.domain.repository.extend;

import com.gfs.domain.document.Configuration;

public interface ConfigurationRepositoryExtend {
    public <T extends Configuration> T findByKey(String key, Class<T> _class);
}
