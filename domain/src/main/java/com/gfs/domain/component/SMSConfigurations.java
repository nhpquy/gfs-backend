package com.gfs.domain.component;

import com.gfs.domain.config.model.SMSConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("smsConfigurations")
public class SMSConfigurations extends Configurations<SMSConfig> {

    @Override
    public String getKey() {
        return SMSConfig.SMS_CONFIG_KEY;
    }

    @Override
    public Class<SMSConfig> getClazz() {
        return SMSConfig.class;
    }
}
