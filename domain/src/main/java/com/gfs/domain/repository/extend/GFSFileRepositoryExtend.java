package com.gfs.domain.repository.extend;

import com.gfs.domain.document.GFSFile;
import com.gfs.domain.request.ListOwnerFilesPagingRequest;

import java.util.List;
import java.util.Map;

public interface GFSFileRepositoryExtend {
    public GFSFile updateGFSFileDetail(String ownerId, String hashCode, Map<String, Object> updateValues);

    public List<GFSFile> findByOwnerIdPaging(String ownerId, ListOwnerFilesPagingRequest request);

//    public List<AdminStudentAggResult> listStudent(AdminListStudentRequest request);
}
