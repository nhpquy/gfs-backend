package com.gfs.api.controller.authentication;

import com.gfs.api.annotation.ApiRestController;
import com.gfs.domain.constant.SwaggerTag;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.enums.AuthorizeTokenRole;
import com.gfs.domain.model.CurrentAccountLogin;
import com.gfs.domain.request.ChangePasswordRequest;
import com.gfs.domain.request.ForgotPasswordRequest;
import com.gfs.domain.request.ResetPasswordRequest;
import com.gfs.domain.request.VerifyForgotPasswordRequest;
import com.gfs.domain.response.ForgotPasswordResponse;
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

@Api(tags = SwaggerTag.password)
@ApiRestController(value = "/account/pwd")
public class RestPasswordController {

    @Autowired
    AccountService accountService;

    @ApiOperation(value = "Student Forgot Password")
    @PostMapping(value = "/forgot/student")
    public ForgotPasswordResponse forgotPasswordStudent(HttpServletRequest servletRequest,
                                                        @RequestBody ForgotPasswordRequest request) throws Exception {
        return accountService.forgotPassword(AccountProfile.student, request);
    }

    @ApiOperation(value = "Tutor Forgot Password")
    @PostMapping(value = "/forgot/tutor")
    public ForgotPasswordResponse forgotPasswordTutor(HttpServletRequest servletRequest,
                                                      @RequestBody ForgotPasswordRequest request) throws Exception {
        return accountService.forgotPassword(AccountProfile.tutor, request);
    }

    @ApiOperation(value = "Resend Forgot Password Code")
    @PostMapping(value = "/forgot/resend")
    public GeneralSubmitResponse resendForgotPassword(HttpServletRequest servletRequest,
                                                      @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor},
                                                              roles = AuthorizeTokenRole.forgot_password)
                                                      @RequestHeader(name = "Authorization") CurrentAccountLogin currentAccountLogin) {
        return accountService.resendForgotPasswordCode(currentAccountLogin.getAccount(), currentAccountLogin.getAuthorizedToken());
    }

    @ApiOperation(value = "Verify Forgot Password Code")
    @PostMapping(value = "/forgot/verify")
    public GeneralSubmitResponse verifyForgotPassword(HttpServletRequest servletRequest,
                                                      @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor},
                                                              roles = AuthorizeTokenRole.forgot_password)
                                                      @RequestHeader(name = "Authorization") CurrentAccountLogin currentAccountLogin,
                                                      @RequestBody VerifyForgotPasswordRequest request) {
        return accountService.verifyForgotPassword(currentAccountLogin.getAuthorizedToken(), request);
    }

    @ApiOperation(value = "Reset New Password")
    @PostMapping(value = "/forgot/reset")
    public GeneralSubmitResponse resetPassword(HttpServletRequest servletRequest,
                                               @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor},
                                                       roles = AuthorizeTokenRole.activated_forgot_password)
                                               @RequestHeader(name = "Authorization") CurrentAccountLogin currentAccountLogin,
                                               @RequestBody ResetPasswordRequest request) throws Exception {
        return accountService.resetPassword(currentAccountLogin.getAccount(), currentAccountLogin.getAuthorizedToken(), request);
    }

    @ApiOperation(value = "Change Password")
    @PostMapping(value = "/change")
    public GeneralSubmitResponse changePassword(HttpServletRequest servletRequest,
                                                @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor},
                                                        roles = AuthorizeTokenRole.authorized)
                                                @RequestHeader(name = "Authorization") CurrentAccountLogin currentAccountLogin,
                                                @RequestBody ChangePasswordRequest request) throws Exception {
        return accountService.changePassword(currentAccountLogin.getAccount(), currentAccountLogin.getAuthorizedToken(), request);
    }
}
