package com.gfs.services.converter;

import com.gfs.domain.model.CurrentStudentLogin;
import com.gfs.services.inf.AccountAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrentStudentLoginConverter implements Converter<String, CurrentStudentLogin> {

    @Autowired
    AccountAuthorizationService accountAuthorizationService;

    @Override
    public CurrentStudentLogin convert(String authorization) {
        return accountAuthorizationService.loadStudent(authorization);
    }
}
