package com.gfs.domain.config.model;

import com.gfs.domain.document.Configuration;
import lombok.Data;

@Data
public class XmppConfig extends Configuration {
    public static final String XMPP_CONFIG_KEY = "xmpp_config";

    private String host;
    private int port;
    private String service;
    private String admin_account;
    private String admin_password;
}
