package com.gfs.services.converter;

import com.gfs.domain.model.CurrentAccountLogin;
import com.gfs.services.inf.AccountAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrentAccountLoginConverter implements Converter<String, CurrentAccountLogin> {

    @Autowired
    AccountAuthorizationService accountAuthorizationService;

    @Override
    public CurrentAccountLogin convert(String authorization) {
        return accountAuthorizationService.loadAccount(authorization);
    }
}
