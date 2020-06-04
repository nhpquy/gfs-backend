package com.gfs.domain.config.model;

import com.gfs.domain.document.Configuration;
import com.gfs.domain.utils.StringUtils;
import lombok.Data;

@Data
public class IPFSServerConfig extends Configuration {
    public static final String IPFS_CONFIG_KEY = "ipfs_config";

    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 5001;
    private static final int DEFAULT_TIMEOUT = 10000;
    private static final int DEFAULT_THREAD_POOL = 10;

    private String host;
    private int port;
    private int max_pool;
    private int time_out;

    public IPFSServerConfig() {
        super();
        this.setKey(IPFS_CONFIG_KEY);
        this.setHost(DEFAULT_HOST);
        this.setPort(DEFAULT_PORT);
        this.setMax_pool(DEFAULT_THREAD_POOL);
        this.setTime_out(DEFAULT_TIMEOUT);
    }

    public IPFSServerConfig(String host, Integer port) {
        super();
        this.setKey(IPFS_CONFIG_KEY);
        this.setHost(StringUtils.hasText(host) ? host : DEFAULT_HOST);
        this.setPort(port != null ? port : DEFAULT_PORT);
        this.setMax_pool(DEFAULT_THREAD_POOL);
        this.setTime_out(DEFAULT_TIMEOUT);
    }
}
