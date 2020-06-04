package com.gfs.domain.repository.extend;

import com.gfs.domain.document.GFSSharedFile;
import com.gfs.domain.request.ListSharedFilesPagingRequest;

import java.util.List;

public interface GFSSharedFileRepositoryExtend {
    public List<GFSSharedFile> findByOwnerIdPaging(String ownerId, ListSharedFilesPagingRequest request);
}
