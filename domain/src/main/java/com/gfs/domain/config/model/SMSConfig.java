package com.gfs.domain.config.model;

import com.gfs.domain.document.Configuration;
import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class SMSConfig extends Configuration {
    public static final String SMS_CONFIG_KEY = "sms_config";
    private String default_sms_method;
    private LinkedHashMap<String, String> sms_methods; // viettel -> vietguys, mobifone -> twillio
    private LinkedHashMap<String, SMSTemplateConfig> verify_sms_template;
}
