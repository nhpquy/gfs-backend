package com.gfs.admin.controller.storage;

import com.gfs.admin.annotation.ApiRestController;
import com.gfs.admin.service.annotation.AdminAuthorized;
import com.gfs.admin.service.inf.AdminFileService;
import com.gfs.domain.enums.admin.AdminRole;
import com.gfs.domain.model.CurrentAdminLogin;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@ApiRestController(value = "/storage")
public class RestStorageController {
    @Autowired
    AdminFileService adminFileService;

    @ApiOperation(value = "Upload File")
    @RequestMapping(value = "/upload-file", method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest servletRequest,
                             @AdminAuthorized(adminRoles = AdminRole.all)
                             @RequestHeader(name = "Authorization") CurrentAdminLogin currentAdminLogin,
                             @RequestParam(name = "file") MultipartFile file,
                             @RequestParam(name = "filename") String filename) {
        return adminFileService.uploadFile(file, filename);
    }
}
