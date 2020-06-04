package com.gfs.domain.config.model;

import lombok.Data;

@Data
public class SMSTemplateConfig {
    private String default_template;
    private String vietnamese_template;
}
