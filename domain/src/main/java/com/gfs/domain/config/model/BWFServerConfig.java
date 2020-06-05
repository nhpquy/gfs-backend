package com.gfs.domain.config.model;

import com.gfs.domain.document.Configuration;
import com.gfs.domain.utils.StringUtils;
import lombok.Data;

@Data
public class BWFServerConfig extends Configuration {
    public static final String BWF_CONFIG_KEY = "bwf_config";

    private static final String DEFAULT_MAINNET_ENDPOINT = "https://bw.beowulfchain.com/rpc";
    private static final String DEFAULT_TESTNET_ENDPOINT = "https://testnet-bw.beowulfchain.com/rpc";
    private static final int DEFAULT_TIMEOUT = 10000; // 10s

    private String endpoint;
    private boolean testnet;
    private int timeout;

    public BWFServerConfig() {
        super();
        this.setKey(BWF_CONFIG_KEY);
        this.setEndpoint(DEFAULT_TESTNET_ENDPOINT);
        this.setTestnet(true);
        this.setTimeout(DEFAULT_TIMEOUT);
    }

    public BWFServerConfig(String endpoint, boolean testnet) {
        super();
        this.setKey(BWF_CONFIG_KEY);
        if (testnet) {
            this.setEndpoint(StringUtils.hasText(endpoint) ? endpoint : DEFAULT_TESTNET_ENDPOINT);
            this.setTestnet(true);
        } else {
            this.setEndpoint(StringUtils.hasText(endpoint) ? endpoint : DEFAULT_MAINNET_ENDPOINT);
            this.setTestnet(false);
        }
        this.setTimeout(DEFAULT_TIMEOUT);
    }
}
