package com.gfs.admin.service.converter;

import com.gfs.admin.service.inf.AdminAuthenticationService;
import com.gfs.domain.model.CurrentAdminLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrentAdminLoginConverter implements Converter<String, CurrentAdminLogin> {

    @Autowired
    AdminAuthenticationService adminAuthenticationService;

    @Override
    public CurrentAdminLogin convert(String authorization) {
        return adminAuthenticationService.loadAccount(authorization);
    }
}
