package com.gfs.services.impl;

import com.gfs.domain.component.IPFSClient;
import com.gfs.domain.document.Account;
import com.gfs.domain.document.GFSFile;
import com.gfs.domain.document.GFSSharedFile;
import com.gfs.domain.enums.GFSFileStatus;
import com.gfs.domain.model.CurrentAccountLogin;
import com.gfs.domain.repository.inf.GFSFileRepository;
import com.gfs.domain.repository.inf.GFSSharedFileRepository;
import com.gfs.domain.request.*;
import com.gfs.domain.response.GFSFileResponse;
import com.gfs.domain.response.GeneralSubmitResponse;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.domain.utils.StringUtils;
import com.gfs.services.inf.AccountService;
import com.gfs.services.inf.FileStorageService;
import io.ipfs.api.MerkleNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    GFSFileRepository gfsFileRepository;

    @Autowired
    GFSSharedFileRepository gfsSharedFileRepository;

    @Autowired
    IPFSClient ipfsClient;

    @Autowired
    AccountService accountService;

    @Override
    public GFSFileResponse uploadFile(UploadFileRequest request, MultipartFile file, CurrentAccountLogin currentAccountLogin) {
        MerkleNode addResult;
        try {
            addResult = ipfsClient.createContent(file.getBytes());
        } catch (Exception e) {
            log.error("Exception while getting bytes from file", e);
            throw ServiceExceptionUtils.internalServerError();
        }
        String hashCode = addResult.hash.toString();
        if (gfsFileRepository.findByHash_codeAndOwner_id(hashCode, currentAccountLogin.getAccount().getAccount_id()) != null) {
            throw ServiceExceptionUtils.fileExisted();
        }
        GFSFile newFile = new GFSFile(request, currentAccountLogin.getAccount().getAccount_id());
        if (StringUtils.hasText(request.getName())) {
            newFile.setFile_name(file.getOriginalFilename());
        }
        newFile.setHash_code(hashCode);
        newFile.setSize(addResult.largeSize.map(Long::valueOf).orElse(0L));
        newFile.setLinks(addResult.links.stream().map(l -> l.hash.toString()).collect(Collectors.toList()));
        newFile.setContent_type(file.getContentType());
        newFile = gfsFileRepository.save(newFile);

        return new GFSFileResponse(newFile);
    }

    @Override
    public List<GFSFileResponse> listOwnerFilePaging(ListOwnerFilesPagingRequest request, CurrentAccountLogin
            currentAccountLogin) {
        List<GFSFile> gfsFiles = gfsFileRepository.findByOwnerIdPaging(currentAccountLogin.getAccount().getAccount_id(), request);
        return gfsFiles.stream().map(GFSFileResponse::new).collect(Collectors.toList());
    }

    @Override
    public GFSFileResponse updateGFSFile(@Valid UpdateGFSMetaDataRequest request, CurrentAccountLogin currentAccountLogin) {
        String ownerId = currentAccountLogin.getAccount().getAccount_id();
        GFSFile gfsFile = gfsFileRepository.findByHash_codeAndOwner_id(request.getHash_code(), ownerId);
        if (gfsFile == null) {
            throw ServiceExceptionUtils.fileNotFound();
        }

        Map<String, Object> updateValues = new HashMap<>();
        if (StringUtils.hasText(request.getDescription())) {
            updateValues.put("description", request.getDescription());
        }
        if (StringUtils.hasText(request.getFile_name())) {
            updateValues.put("file_name", request.getFile_name());
        }
        if (request.getStatus() != null) {
            updateValues.put("status", request.getStatus());
        }

        gfsFile = gfsFileRepository.updateGFSFileDetail(ownerId, request.getHash_code(), updateValues);
        if (gfsFile == null) {
            log.error("Exception while updating meta-data for file [hash: {}]", request.getHash_code());
            throw ServiceExceptionUtils.internalServerError();
        }
        return new GFSFileResponse(gfsFile);
    }

    @Override
    public GFSFileResponse getGFSFileDetail(String hashCode, CurrentAccountLogin currentAccountLogin) {
        String ownerId = currentAccountLogin.getAccount().getAccount_id();
        GFSFile gfsFile = gfsFileRepository.findByHash_codeAndOwner_id(hashCode, ownerId);
        if (gfsFile == null) {
            throw ServiceExceptionUtils.fileNotFound();
        }
        return new GFSFileResponse(gfsFile);
    }

    @Override
    public GeneralSubmitResponse removeGFSFile(@Valid RemoveGFSMetaDataRequest request, CurrentAccountLogin currentAccountLogin) {
        String ownerId = currentAccountLogin.getAccount().getAccount_id();
        GFSFile gfsFile = gfsFileRepository.findByHash_codeAndOwner_id(request.getHash_code(), ownerId);
        if (gfsFile == null) {
            throw ServiceExceptionUtils.fileNotFound();
        }

        if (request.is_permanent()) {
            gfsFileRepository.delete(gfsFile);
        } else {
            Map<String, Object> updateValues = new HashMap<>();
            updateValues.put("status", GFSFileStatus.deleted);
            gfsFile = gfsFileRepository.updateGFSFileDetail(ownerId, request.getHash_code(), updateValues);
            if (gfsFile == null) {
                log.error("Exception while updating meta-data for file [hash: {}]", request.getHash_code());
                throw ServiceExceptionUtils.internalServerError();
            }
        }

        return new GeneralSubmitResponse(true);
    }

    @Override
    public Object shareFileOnPublicChain(@Valid ShareFileRequest request, CurrentAccountLogin currentAccountLogin) {
        String receiver_email = StringUtils.handleEmailOrPhoneNumber(request.getReceiver_email());
        Account receiver = accountService.ifBeValidAccount(receiver_email);

        GFSSharedFile sharedFile = new GFSSharedFile(request, currentAccountLogin.getAccount().getAccount_id(), receiver.getAccount_id());

        return null;
    }
}
