package com.gfs.api.controller.storage;

import com.gfs.api.annotation.ApiV1RestController;
import com.gfs.domain.constant.SwaggerTag;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.model.CurrentAccountLogin;
import com.gfs.domain.request.*;
import com.gfs.domain.response.GFSFileResponse;
import com.gfs.domain.response.GFSSharedFileDetailResponse;
import com.gfs.domain.response.GFSSharedFileResponse;
import com.gfs.domain.response.GeneralSubmitResponse;
import com.gfs.services.annotation.AccountAuthorized;
import com.gfs.services.inf.FileStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = SwaggerTag.storage)
@ApiV1RestController(value = "/storage")
public class RestStorageController {

    @Autowired
    FileStorageService fileStorageService;

    @ApiOperation(value = "Upload File")
    @PostMapping(value = "/upload")
    public GFSFileResponse uploadFile(HttpServletRequest servletRequest,
                                      @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor})
                                      @RequestHeader(name = "Authorization") CurrentAccountLogin currentAccountLogin,
                                      @RequestParam(name = "name", required = false) String name,
                                      @RequestParam(name = "description", required = false) String description,
                                      @RequestParam(name = "encrypted_pwd", required = false) String encrypted_pwd,
                                      @RequestParam(name = "published", required = false) boolean published,
                                      @RequestParam(name = "file") MultipartFile file) {
        UploadFileRequest request = new UploadFileRequest(name, description, encrypted_pwd, published);
        return fileStorageService.uploadFile(request, file, currentAccountLogin);
    }

    @ApiOperation(value = "List Owner files Paging")
    @PostMapping(value = "/list-owner")
    public List<GFSFileResponse> listOwnerFilesPaging(HttpServletRequest servletRequest,
                                                      @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor})
                                                      @RequestHeader(name = "Authorization") CurrentAccountLogin currentAccountLogin,
                                                      @RequestBody ListOwnerFilesPagingRequest request) {
        return fileStorageService.listOwnerFilesPaging(request, currentAccountLogin);
    }

    @ApiOperation(value = "Update meta-data")
    @PostMapping(value = "/update")
    public GFSFileResponse updateGFSFile(HttpServletRequest servletRequest,
                                         @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor})
                                         @RequestHeader(name = "Authorization") CurrentAccountLogin currentAccountLogin,
                                         @RequestBody UpdateGFSMetaDataRequest request) {
        return fileStorageService.updateGFSFile(request, currentAccountLogin);
    }

    @ApiOperation(value = "Remove meta-data")
    @DeleteMapping(value = "/remove")
    public GeneralSubmitResponse removeGFSFile(HttpServletRequest servletRequest,
                                               @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor})
                                               @RequestHeader(name = "Authorization") CurrentAccountLogin currentAccountLogin,
                                               @RequestBody RemoveGFSMetaDataRequest request) {
        return fileStorageService.removeGFSFile(request, currentAccountLogin);
    }

    @ApiOperation(value = "Get meta-data detail")
    @GetMapping(value = "/detail")
    public GFSFileResponse getGFSFileDetail(HttpServletRequest servletRequest,
                                            @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor})
                                            @RequestHeader(name = "Authorization") CurrentAccountLogin currentAccountLogin,
                                            @RequestParam("hashCode") String hashCode) {
        return fileStorageService.getGFSFileDetail(hashCode, currentAccountLogin);
    }

    @ApiOperation(value = "Share File on Public-chain")
    @PostMapping(value = "/share")
    public GFSSharedFileDetailResponse shareFileOnPublicChain(HttpServletRequest servletRequest,
                                                              @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor})
                                                              @RequestHeader(name = "Authorization") CurrentAccountLogin currentAccountLogin,
                                                              @RequestBody ShareFileRequest request) {
        return fileStorageService.shareFileOnPublicChain(request, currentAccountLogin);
    }

    @ApiOperation(value = "List shared files Paging")
    @PostMapping(value = "/list-shared")
    public List<GFSSharedFileResponse> listSharedFilesPaging(HttpServletRequest servletRequest,
                                                             @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor})
                                                             @RequestHeader(name = "Authorization") CurrentAccountLogin currentAccountLogin,
                                                             @RequestBody ListSharedFilesPagingRequest request) {
        return fileStorageService.listSharedFilesPaging(request, currentAccountLogin);
    }

    @ApiOperation(value = "List received files Paging")
    @PostMapping(value = "/list-received")
    public List<GFSSharedFileResponse> listReceivedFilesPaging(HttpServletRequest servletRequest,
                                                               @AccountAuthorized(profiles = {AccountProfile.student, AccountProfile.tutor})
                                                               @RequestHeader(name = "Authorization") CurrentAccountLogin currentAccountLogin,
                                                               @RequestBody ListReceivedFilesPagingRequest request) {
        return fileStorageService.listReceivedFilesPaging(request, currentAccountLogin);
    }
}
