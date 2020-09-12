package com.gfs.api.controller.authentication;

import com.gfs.api.annotation.ApiV1RestController;
import com.gfs.domain.constant.SwaggerTag;
import com.gfs.domain.request.RegisterAccountRequest;
import com.gfs.domain.response.AccountLoginResponse;
import com.gfs.services.inf.StudentAccountService;
import com.gfs.services.inf.TutorAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@ApiV1RestController(value = "/register")
@Api(tags = SwaggerTag.registration)
public class RestRegisterController {

    @Autowired
    StudentAccountService studentAccountService;

    @Autowired
    TutorAccountService tutorAccountService;

    @ApiOperation(value = "Register as Student")
    @PostMapping(value = "/student")
    public AccountLoginResponse registerStudent(HttpServletRequest servletRequest,
                                                @RequestHeader(name = "user-agent", required = false) String userAgent,
                                                @RequestBody RegisterAccountRequest request) throws Exception {
        request.setUser_agent(userAgent);
        return studentAccountService.registerAccount(request);
    }

    @ApiOperation(value = "Register as Tutor")
    @PostMapping(value = "/tutor")
    public AccountLoginResponse registerTutor(HttpServletRequest servletRequest,
                                              @RequestHeader(name = "user-agent", required = false) String userAgent,
                                              @RequestBody RegisterAccountRequest request) throws Exception {
        request.setUser_agent(userAgent);
        return tutorAccountService.registerAccount(request);
    }
}
