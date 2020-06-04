package com.gfs.admin.controller.authentication;

import com.gfs.admin.annotation.ApiRestController;
import com.gfs.admin.service.annotation.AdminAuthorized;
import com.gfs.admin.service.inf.AdminAuthenticationService;
import com.gfs.domain.enums.admin.AdminRole;
import com.gfs.domain.enums.admin.AdminSwaggerTag;
import com.gfs.domain.enums.AuthorizeTokenRole;
import com.gfs.domain.model.CurrentAdminLogin;
import com.gfs.domain.request.AdminLoginRequest;
import com.gfs.domain.response.AccountLoginResponse;
import com.gfs.domain.response.GeneralSubmitResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@Api(tags = AdminSwaggerTag.login)
@ApiRestController(value = "")
public class RestAdminLoginController {

    @Autowired
    AdminAuthenticationService adminAuthenticationService;

    @ApiOperation(value = "Logging in to admin account")
    @PostMapping(value = "/login")
    public AccountLoginResponse login(HttpServletRequest servletRequest,
                                      @RequestBody AdminLoginRequest request) throws Exception {
        return adminAuthenticationService.login(request);
    }

    @ApiOperation(value = "Logout")
    @PostMapping(value = "/logout")
    public GeneralSubmitResponse logout(HttpServletRequest servletRequest,
                                        @AdminAuthorized(adminRoles = AdminRole.all,
                                                tokenRoles = AuthorizeTokenRole.all)
                                        @RequestHeader(name = "Authorization") CurrentAdminLogin currentAdminLogin) {
        return adminAuthenticationService.logout(currentAdminLogin.getAuthorizedToken());
    }
}
