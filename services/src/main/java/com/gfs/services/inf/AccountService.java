package com.gfs.services.inf;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.request.*;
import com.gfs.domain.response.AccountLoginResponse;
import com.gfs.domain.response.ForgotPasswordResponse;
import com.gfs.domain.response.GeneralSubmitResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface AccountService {
    public Account createAccount(@NotNull AccountProfile profile, @Valid RegisterAccountRequest request);

    public GeneralSubmitResponse resendActivateAccount(Account account);

    public AccountLoginResponse activateAccount(Account account, AccountAuthorizedToken authorizedToken, @Valid ActivateAccountRequest request) throws Exception;

    public ForgotPasswordResponse forgotPassword(@NotNull AccountProfile profile, @Valid ForgotPasswordRequest request) throws Exception;

    public GeneralSubmitResponse resendForgotPasswordCode(Account account, AccountAuthorizedToken authorizedToken);

    public GeneralSubmitResponse verifyForgotPassword(AccountAuthorizedToken authorizedToken, @Valid VerifyForgotPasswordRequest request);

    public GeneralSubmitResponse resetPassword(Account account, AccountAuthorizedToken authorizedToken, @Valid ResetPasswordRequest request);

    public GeneralSubmitResponse changePassword(Account account, AccountAuthorizedToken authorizedToken, @Valid ChangePasswordRequest request);

    public Account ifBeValidAccount(String email);
}
