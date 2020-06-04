package com.gfs.services.converter;

import com.gfs.domain.model.CurrentTutorLogin;
import com.gfs.services.inf.AccountAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrentTutorLoginConverter implements Converter<String, CurrentTutorLogin> {

    @Autowired
    AccountAuthorizationService accountAuthorizationService;

    @Override
    public CurrentTutorLogin convert(String authorization) {
        return accountAuthorizationService.loadTutor(authorization);
    }
}
