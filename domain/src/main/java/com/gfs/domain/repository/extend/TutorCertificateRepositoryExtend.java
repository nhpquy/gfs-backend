package com.gfs.domain.repository.extend;

import com.gfs.domain.document.TutorCertificate;

import java.util.Map;

public interface TutorCertificateRepositoryExtend {
    public TutorCertificate updateDetail(String certificateId, Map<String, Object> updateValues);
}
