package com.gfs.domain.component;

import com.gfs.domain.config.model.PushConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("pushConfiguration")
public class PushConfiguration extends Configurations<PushConfig> {

    @Override
    public String getKey() {
        return PushConfig.PUSH_CONFIG_KEY;
    }

    @Override
    public Class<PushConfig> getClazz() {
        return PushConfig.class;
    }
}
