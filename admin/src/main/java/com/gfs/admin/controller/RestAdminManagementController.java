package com.gfs.admin.controller;

import com.gfs.admin.annotation.AdminManagerAuthorized;
import com.gfs.admin.annotation.ApiRestController;
import com.gfs.admin.service.inf.AdminAccountService;
import com.gfs.domain.enums.admin.AdminSwaggerTag;
import com.gfs.domain.request.CreateAdminAccountRequest;
import com.gfs.domain.response.AdminAccountInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@ApiRestController(value = "/manage")
@Api(tags = AdminSwaggerTag.admin_manager)
public class RestAdminManagementController {

    @Autowired
    AdminAccountService adminAccountService;

    @ApiOperation(value = "Create Admin Account")
    @PostMapping(value = "/create")
    public AdminAccountInfoResponse createAdminAccount(
            @AdminManagerAuthorized @RequestHeader(name = "Authorization") String authorization,
            @RequestBody CreateAdminAccountRequest request) {
        return adminAccountService.createAdminAccount(request);
    }
}
