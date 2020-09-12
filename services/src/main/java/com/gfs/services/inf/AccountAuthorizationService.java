package com.gfs.services.inf;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.enums.AccountLoginType;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.model.CurrentAccountLogin;
import com.gfs.domain.model.CurrentStudentLogin;
import com.gfs.domain.model.CurrentTutorLogin;
import com.gfs.domain.request.AccountLoginRequest;
import com.gfs.domain.request.LoginRequest;
import com.gfs.domain.response.AccountLoginResponse;
import com.gfs.domain.response.GeneralSubmitResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface AccountAuthorizationService {
    public AccountLoginResponse login(AccountProfile profile, @Valid AccountLoginRequest request) throws Exception;

    public AccountLoginResponse loginSuccessfully(Account account, @Valid LoginRequest request, AccountLoginType loginType) throws Exception;

    public CurrentAccountLogin loadAccount(String authorization);

    public CurrentStudentLogin loadStudent(String authorization);

    public CurrentTutorLogin loadTutor(String authorization);

    public GeneralSubmitResponse logout(AccountAuthorizedToken authorizedToken);

    public AccountLoginResponse switchOrganizationContext(AccountAuthorizedToken authorizedToken, String orgId) throws Exception;
}
