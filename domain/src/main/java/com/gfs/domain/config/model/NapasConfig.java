package com.gfs.domain.config.model;

import com.gfs.domain.document.Configuration;
import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class NapasConfig extends Configuration {
    public static final String NAPAS_CONFIG_KEY = "napas_config";
    private String mode;
    private String aes_secret;
    private String gen_auth_token_url;
    private String gen_payment_url;
    private String query_payment_url;
    private String pay_credit_card_non_3d_url;
    private String pay_credit_card_with_3d_url;
    private String pay_atm_card_url;
    private long minimum_amount = 20000L;
    private LinkedHashMap<String, NapasConfig> configs;
}
