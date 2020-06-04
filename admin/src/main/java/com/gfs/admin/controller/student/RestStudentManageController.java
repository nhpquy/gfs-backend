package com.gfs.admin.controller.student;

import com.gfs.admin.annotation.ApiRestController;
import com.gfs.admin.service.annotation.AdminAuthorized;
import com.gfs.admin.service.inf.StudentManagementService;
import com.gfs.domain.enums.admin.AdminRole;
import com.gfs.domain.enums.admin.AdminSwaggerTag;
import com.gfs.domain.model.CurrentAdminLogin;
import com.gfs.domain.request.AdminListStudentRequest;
import com.gfs.domain.response.AdminStudentAccountInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = AdminSwaggerTag.student)
@ApiRestController(value = "/student")
public class RestStudentManageController {

    @Autowired
    StudentManagementService studentManagementService;

    @ApiOperation(value = "List Student")
    @PostMapping(value = "/list")
    public List<AdminStudentAccountInfoResponse> listStudent(HttpServletRequest servletRequest,
                                                             @AdminAuthorized(adminRoles = AdminRole.all)
                                                             @RequestHeader(name = "Authorization") CurrentAdminLogin currentAdminLogin,
                                                             @RequestBody AdminListStudentRequest request) {
        request.setClient_timestamp(System.currentTimeMillis());
        return studentManagementService.listStudent(request);
    }

    @ApiOperation(value = "Get Student Detail")
    @GetMapping(value = "/detail")
    public AdminStudentAccountInfoResponse getTutorDetail(HttpServletRequest servletRequest,
                                                          @AdminAuthorized(adminRoles = AdminRole.all)
                                                          @RequestHeader(name = "Authorization") CurrentAdminLogin currentAdminLogin,
                                                          @RequestParam(name = "studentId") String studentId) {
        return studentManagementService.getStudentDetail(studentId);
    }
}
