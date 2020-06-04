package com.gfs.admin.controller.tutor;

import com.gfs.admin.annotation.ApiRestController;
import com.gfs.admin.service.annotation.AdminAuthorized;
import com.gfs.admin.service.inf.TutorManagementService;
import com.gfs.domain.enums.admin.AdminRole;
import com.gfs.domain.enums.admin.AdminSwaggerTag;
import com.gfs.domain.model.CurrentAdminLogin;
import com.gfs.domain.request.*;
import com.gfs.domain.request.admin.BanTutorIdRequest;
import com.gfs.domain.response.AdminTutorAccountInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = AdminSwaggerTag.tutor)
@ApiRestController(value = "/tutor")
public class RestTutorManageController {

    @Autowired
    TutorManagementService tutorManagementService;

    @ApiOperation(value = "List Tutor")
    @PostMapping(value = "/list")
    public List<AdminTutorAccountInfoResponse> listTutor(HttpServletRequest servletRequest,
                                                         @AdminAuthorized(adminRoles = AdminRole.all)
                                                         @RequestHeader(name = "Authorization") CurrentAdminLogin currentAdminLogin,
                                                         @RequestBody AdminListTutorRequest request) {
        return tutorManagementService.listTutor(request);
    }

    @ApiOperation(value = "Get Tutor Detail")
    @GetMapping(value = "/detail")
    public AdminTutorAccountInfoResponse getTutorDetail(HttpServletRequest servletRequest,
                                                        @AdminAuthorized(adminRoles = AdminRole.all)
                                                        @RequestHeader(name = "Authorization") CurrentAdminLogin currentAdminLogin,
                                                        @RequestParam(name = "tutorId") String tutorId) {
        return tutorManagementService.getTutorDetail(tutorId);
    }

    @ApiOperation(value = "Approve Tutor")
    @PostMapping(value = "/approve")
    public AdminTutorAccountInfoResponse approveTutor(HttpServletRequest servletRequest,
                                                      @AdminAuthorized(adminRoles = AdminRole.all)
                                                      @RequestHeader(name = "Authorization") CurrentAdminLogin currentAdminLogin,
                                                      @RequestBody AdminApproveTutorRequest request) {
        return tutorManagementService.activeTutor(currentAdminLogin, request);
    }

    @ApiOperation(value = "Deactive Tutor")
    @PostMapping(value = "/deactive")
    public AdminTutorAccountInfoResponse deactiveTutor(HttpServletRequest servletRequest,
                                                       @AdminAuthorized(adminRoles = AdminRole.all)
                                                       @RequestHeader(name = "Authorization") CurrentAdminLogin currentAdminLogin,
                                                       @RequestBody AdminDeactiveTutorRequest request) {
        return tutorManagementService.deactiveTutor(currentAdminLogin, request);
    }

    @ApiOperation(value = "api ban account tutor for admin")
    @PostMapping(value = "/ban")
    public AdminTutorAccountInfoResponse banTutor(HttpServletRequest servletRequest,
                                                  @AdminAuthorized(adminRoles = AdminRole.super_admin)
                                                  @RequestHeader(name = "Authorization") CurrentAdminLogin currentAdminLogin,
                                                  @RequestBody BanTutorIdRequest request) {
        return tutorManagementService.banAccount(currentAdminLogin, request);
    }
}
