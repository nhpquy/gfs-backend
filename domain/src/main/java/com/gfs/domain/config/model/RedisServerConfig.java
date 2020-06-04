package com.gfs.domain.config.model;

import com.gfs.domain.document.Configuration;
import lombok.Data;

@Data
public class RedisServerConfig extends Configuration {
    public static final String REDIS_CONFIG_KEY = "redis_config";
    private String host;
    private int port;
    private int max_pool;

    public RedisServerConfig() {
        super();
        this.setKey(REDIS_CONFIG_KEY);
        this.setHost("localhost");
        this.setPort(6379);
        this.setMax_pool(10);
    }
}
