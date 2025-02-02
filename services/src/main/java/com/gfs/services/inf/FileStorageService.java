package com.gfs.services.inf;

import com.gfs.domain.model.CurrentAccountLogin;
import com.gfs.domain.request.*;
import com.gfs.domain.response.GFSFileResponse;
import com.gfs.domain.response.GFSSharedFileDetailResponse;
import com.gfs.domain.response.GFSSharedFileResponse;
import com.gfs.domain.response.GeneralSubmitResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface FileStorageService {
    public GFSFileResponse uploadFile(@Valid UploadFileRequest request, @Valid @NotNull MultipartFile file, CurrentAccountLogin currentAccountLogin);

    public List<GFSFileResponse> listOwnerFilesPaging(ListOwnerFilesPagingRequest request, CurrentAccountLogin currentAccountLogin);

    public GFSFileResponse updateGFSFile(@Valid UpdateGFSMetaDataRequest request, CurrentAccountLogin currentAccountLogin);

    public GFSFileResponse getGFSFileDetail(String hashCode, CurrentAccountLogin currentAccountLogin);

    public GeneralSubmitResponse removeGFSFile(@Valid RemoveGFSMetaDataRequest request, CurrentAccountLogin currentAccountLogin);

    public GFSSharedFileDetailResponse shareFileOnPublicChain(@Valid ShareFileRequest request, CurrentAccountLogin currentAccountLogin);

    public List<GFSSharedFileResponse> listSharedFilesPaging(ListSharedFilesPagingRequest request, CurrentAccountLogin currentAccountLogin);

    public List<GFSSharedFileResponse> listReceivedFilesPaging(ListReceivedFilesPagingRequest request, CurrentAccountLogin currentAccountLogin);
}
