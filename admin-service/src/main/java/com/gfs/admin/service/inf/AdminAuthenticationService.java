package com.gfs.admin.service.inf;

import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.model.CurrentAdminLogin;
import com.gfs.domain.request.AdminLoginRequest;
import com.gfs.domain.response.AccountLoginResponse;
import com.gfs.domain.response.GeneralSubmitResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface AdminAuthenticationService {
    public CurrentAdminLogin loadAccount(String authorization);

    public AccountLoginResponse login(@Valid AdminLoginRequest request) throws Exception;

    public GeneralSubmitResponse logout(AccountAuthorizedToken authorizedToken);
}
