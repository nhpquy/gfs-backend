package com.gfs.api.controller.authentication;

import com.gfs.api.annotation.ApiRestController;
import com.gfs.domain.constant.SwaggerTag;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.enums.AuthorizeTokenRole;
import com.gfs.domain.model.CurrentAccountLogin;
import com.gfs.domain.response.GeneralSubmitResponse;
import com.gfs.services.annotation.AccountAuthorized;
import com.gfs.services.inf.AccountAuthorizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@Api(tags = SwaggerTag.login)
@ApiRestController(value = "/logout")
public class RestLogoutController {

    @Autowired
    AccountAuthorizationService accountAuthorizationService;

    @ApiOperation(value = "Logout")
    @PostMapping(value = "")
    public GeneralSubmitResponse logout(HttpServletRequest servletRequest,
                                        @AccountAuthorized(roles = AuthorizeTokenRole.all,
                                                profiles = {AccountProfile.student, AccountProfile.tutor})
                                        @RequestHeader(name = "Authorization", required = false)
                                                CurrentAccountLogin currentAccountLogin) {
        return accountAuthorizationService.logout(currentAccountLogin.getAuthorizedToken());
    }
}
