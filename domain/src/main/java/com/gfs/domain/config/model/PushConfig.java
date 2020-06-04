package com.gfs.domain.config.model;

import com.gfs.domain.document.Configuration;
import lombok.Data;

@Data
public class PushConfig extends Configuration {
    public static final String PUSH_CONFIG_KEY = "push_config_key";
    private String push_user_url;
    private String push_driver_url;
    private boolean production;
}
