package com.gfs.domain.config.model;

import com.gfs.domain.document.Configuration;
import lombok.Data;

@Data
public class VNPayConfig extends Configuration {

    private String mode;

    private String version;

    private String tmn_code;

    private String pay_url;

    private String return_url;

    private String hash_secret;

    private String api_url;

    private long fee_percent;

    private long bonus_percent;

    private String encryption_key;

    private String topup_success_url;
    private String topup_failed_url;
}
