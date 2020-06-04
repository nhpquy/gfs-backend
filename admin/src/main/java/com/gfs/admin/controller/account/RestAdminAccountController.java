package com.gfs.admin.controller.account;

import com.gfs.admin.annotation.ApiRestController;
import com.gfs.admin.service.annotation.AdminAuthorized;
import com.gfs.admin.service.inf.AdminAccountService;
import com.gfs.domain.enums.admin.AdminRole;
import com.gfs.domain.enums.admin.AdminSwaggerTag;
import com.gfs.domain.model.CurrentAdminLogin;
import com.gfs.domain.response.AdminAccountInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@Api(tags = AdminSwaggerTag.admin_account)
@ApiRestController(value = "/account")
public class RestAdminAccountController {

    @Autowired
    AdminAccountService adminAccountService;

    @ApiOperation(value = "Get Admin Account detail")
    @GetMapping(value = "/detail")
    public AdminAccountInfoResponse getAccountDetail(HttpServletRequest servletRequest,
                                                     @AdminAuthorized(adminRoles = AdminRole.all)
                                                     @RequestHeader(name = "Authorization") CurrentAdminLogin currentAdminLogin) {
        return new AdminAccountInfoResponse(currentAdminLogin.getAccount());
    }
}
