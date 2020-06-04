package com.gfs.api.controller.tutor;

import com.gfs.api.annotation.ApiRestController;
import com.gfs.domain.constant.SwaggerTag;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.model.CurrentTutorLogin;
import com.gfs.domain.request.AddTutorCertificateRequest;
import com.gfs.domain.request.RemoveTutorCertificateRequest;
import com.gfs.domain.request.UpdateTutorCertificateRequest;
import com.gfs.domain.request.UpdateTutorProfileRequest;
import com.gfs.services.annotation.AccountAuthorized;
import com.gfs.services.inf.AccountService;
import com.gfs.services.inf.TutorAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = SwaggerTag.tutor)
@ApiRestController(value = "/tutor/profile")
public class RestTutorProfileController {
    @Autowired
    TutorAccountService tutorAccountService;

    @Autowired
    AccountService accountService;

    @ApiOperation(value = "Update Tutor Profile")
    @PostMapping(value = "/update")
    public Object updateTutorProfile(HttpServletRequest servletRequest,
                                     @AccountAuthorized(profiles = AccountProfile.tutor)
                                     @RequestHeader(name = "Authorization", required = false)
                                             CurrentTutorLogin currentTutorLogin,
                                     @RequestBody UpdateTutorProfileRequest request) {
        return tutorAccountService.updateTutorProfile(currentTutorLogin.getTutorAccount(), request);
    }

    @Deprecated
    @GetMapping(value = "/certificate/list")
    public Object getCertificate(HttpServletRequest servletRequest,
                                 @AccountAuthorized(profiles = AccountProfile.tutor)
                                 @RequestHeader(name = "Authorization", required = false)
                                         CurrentTutorLogin currentTutorLogin) {
        return tutorAccountService.getTutorCertificate(currentTutorLogin.getTutorAccount());
    }

    @Deprecated
    @PostMapping(value = "/certificate/add")
    public Object addCertificate(HttpServletRequest servletRequest,
                                 @AccountAuthorized(profiles = AccountProfile.tutor)
                                 @RequestHeader(name = "Authorization", required = false)
                                         CurrentTutorLogin currentTutorLogin,
                                 @RequestBody AddTutorCertificateRequest request) {
        return tutorAccountService.addTutorCertificate(currentTutorLogin.getTutorAccount(), request);
    }

    @Deprecated
    @PostMapping(value = "/certificate/update")
    public Object updateCertificate(HttpServletRequest servletRequest,
                                    @AccountAuthorized(profiles = AccountProfile.tutor)
                                    @RequestHeader(name = "Authorization", required = false)
                                            CurrentTutorLogin currentTutorLogin,
                                    @RequestBody UpdateTutorCertificateRequest request) {
        return tutorAccountService.updateTutorCertificate(currentTutorLogin.getTutorAccount(), request);
    }

    @Deprecated
    @DeleteMapping(value = "/certificate/remove")
    public Object removeCertificate(HttpServletRequest servletRequest,
                                    @AccountAuthorized(profiles = AccountProfile.tutor)
                                    @RequestHeader(name = "Authorization", required = false)
                                            CurrentTutorLogin currentTutorLogin,
                                    @RequestBody RemoveTutorCertificateRequest request) {
        return tutorAccountService.removeTutorCertificate(currentTutorLogin.getTutorAccount(), request);
    }
}
