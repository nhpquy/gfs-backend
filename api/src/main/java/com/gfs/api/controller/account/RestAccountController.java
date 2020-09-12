package com.gfs.api.controller.account;

import com.gfs.api.annotation.ApiV1RestController;
import com.gfs.domain.constant.SwaggerTag;
import com.gfs.domain.document.StudentAccount;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.model.CurrentAccountLogin;
import com.gfs.domain.response.AccountInfoResponse;
import com.gfs.domain.response.StudentAccountInfoResponse;
import com.gfs.domain.response.TutorAccountInfoResponse;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.services.annotation.AccountAuthorized;
import com.gfs.services.inf.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@ApiV1RestController(value = "/account")
@Api(tags = SwaggerTag.account)
public class RestAccountController {

    @Autowired
    AccountService accountService;

    @ApiOperation(value = "Get Detail of Account")
    @PostMapping(value = "/detail")
    public AccountInfoResponse getAccountDetail(HttpServletRequest servletRequest,
                                                @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor})
                                                @RequestHeader(name = "Authorization", required = false)
                                                        CurrentAccountLogin currentAccountLogin) {
        if (AccountProfile.student == currentAccountLogin.getAccount().getProfile_type())
            return new StudentAccountInfoResponse((StudentAccount) currentAccountLogin.getAccount());
        if (AccountProfile.tutor == currentAccountLogin.getAccount().getProfile_type()) {
            return new TutorAccountInfoResponse(currentAccountLogin.getAccount());
        }
        throw ServiceExceptionUtils.unAuthorize();
    }
}
