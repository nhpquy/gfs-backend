package com.gfs.domain.component;

import com.gfs.domain.document.Configuration;
import com.gfs.domain.repository.inf.ConfigurationRepository;
import com.gfs.domain.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

@EnableScheduling
public abstract class Configurations<T extends Configuration> {

    @Autowired
    ConfigurationRepository configurationRepository;
    private T config;

    public abstract String getKey();

    public abstract Class<T> getClazz();

    @PostConstruct
    public void onStart() {
        refreshConfig();
    }

    @Scheduled(initialDelay = 60000L, fixedRate = 60000L)
    public void refresh() {
        refreshConfig();
    }

    private void refreshConfig() {
        try {
            config = configurationRepository.findByKey(getKey(), getClazz());
        } catch (Exception e) {
            LoggerUtil.exception(this, e, true);
        }
    }

    public T getConfig() {
        return config;
    }
}
