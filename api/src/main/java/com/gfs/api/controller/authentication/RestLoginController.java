package com.gfs.api.controller.authentication;

import com.gfs.api.annotation.ApiRestController;
import com.gfs.domain.constant.SwaggerTag;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.request.AccountLoginRequest;
import com.gfs.domain.response.AccountLoginResponse;
import com.gfs.services.inf.AccountAuthorizationService;
import com.gfs.services.inf.StudentAccountService;
import com.gfs.services.inf.TutorAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@Api(tags = SwaggerTag.login)
@ApiRestController(value = "/login")
public class RestLoginController {

    @Autowired
    StudentAccountService studentAccountService;

    @Autowired
    TutorAccountService tutorAccountService;

    @Autowired
    AccountAuthorizationService accountAuthorizationService;

    @ApiOperation(value = "Login as Student")
    @PostMapping(value = "/student")
    public AccountLoginResponse loginStudent(HttpServletRequest servletRequest,
                                             @RequestHeader(name = "user-agent", required = false) String userAgent,
                                             @RequestBody AccountLoginRequest request) throws Exception {
        request.setUser_agent(userAgent);
        return accountAuthorizationService.login(AccountProfile.student, request);
    }

    @ApiOperation(value = "Login as Tutor")
    @PostMapping(value = "/tutor")
    public AccountLoginResponse loginTutor(HttpServletRequest servletRequest,
                                           @RequestHeader(name = "user-agent", required = false) String userAgent,
                                           @RequestBody AccountLoginRequest request) throws Exception {
        request.setUser_agent(userAgent);
        return accountAuthorizationService.login(AccountProfile.tutor, request);
    }
}
