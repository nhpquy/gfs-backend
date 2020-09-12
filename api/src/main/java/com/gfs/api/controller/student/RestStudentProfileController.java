package com.gfs.api.controller.student;

import com.gfs.api.annotation.ApiV1RestController;
import com.gfs.domain.constant.SwaggerTag;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.model.CurrentStudentLogin;
import com.gfs.domain.request.UpdateStudentProfileRequest;
import com.gfs.domain.response.StudentAccountInfoResponse;
import com.gfs.services.annotation.AccountAuthorized;
import com.gfs.services.inf.AccountService;
import com.gfs.services.inf.StudentAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@Api(tags = SwaggerTag.student)
@ApiV1RestController(value = "/student/profile")
public class RestStudentProfileController {
    @Autowired
    StudentAccountService studentAccountService;

    @Autowired
    AccountService accountService;

    @ApiOperation(value = "Update Student Profile")
    @PostMapping(value = "/update")
    public StudentAccountInfoResponse updateTutorProfile(HttpServletRequest servletRequest,
                                                         @AccountAuthorized(profiles = AccountProfile.student)
                                                         @RequestHeader(name = "Authorization", required = false)
                                                                 CurrentStudentLogin currentStudentLogin,
                                                         @RequestBody UpdateStudentProfileRequest request) throws Exception {
        return studentAccountService.updateStudentProfile(currentStudentLogin.getStudentAccount(), request);
    }
}
