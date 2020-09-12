package com.gfs.api.controller.authentication;

import com.gfs.api.annotation.ApiV1RestController;
import com.gfs.domain.constant.SwaggerTag;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.enums.AuthorizeTokenRole;
import com.gfs.domain.model.CurrentAccountLogin;
import com.gfs.domain.request.ActivateAccountRequest;
import com.gfs.domain.response.AccountLoginResponse;
import com.gfs.domain.response.GeneralSubmitResponse;
import com.gfs.services.annotation.AccountAuthorized;
import com.gfs.services.inf.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@ApiV1RestController(value = "/account/activate")
@Api(tags = SwaggerTag.activation)
public class RestActivateAccountController {

    @Autowired
    AccountService accountService;

    @ApiOperation(value = "Resend Activation Code")
    @PostMapping(value = "/resend")
    public GeneralSubmitResponse resendActivationCode(HttpServletRequest servletRequest,
                                                      @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor},
                                                              roles = AuthorizeTokenRole.require_activate)
                                                      @RequestHeader(name = "Authorization", required = false)
                                                              CurrentAccountLogin currentAccountLogin) {
        return accountService.resendActivateAccount(currentAccountLogin.getAccount());
    }

    @ApiOperation(value = "Activate Account")
    @PostMapping(value = "")
    public AccountLoginResponse activateAccount(HttpServletRequest servletRequest,
                                                @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor},
                                                        roles = AuthorizeTokenRole.require_activate)
                                                @RequestHeader(name = "Authorization", required = false)
                                                        CurrentAccountLogin currentAccountLogin,
                                                @RequestBody ActivateAccountRequest request) throws Exception {
        return accountService.activateAccount(currentAccountLogin.getAccount(), currentAccountLogin.getAuthorizedToken(), request);
    }
}
