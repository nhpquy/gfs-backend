package com.gfs.domain.repository.inf;

import com.gfs.domain.document.TutorCertificate;
import com.gfs.domain.repository.extend.TutorCertificateRepositoryExtend;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TutorCertificateRepository extends ObjectIdMongoRepository<TutorCertificate>,
        TutorCertificateRepositoryExtend {
    @Query(value = "{'tutor_id' : ?0}")
    public List<TutorCertificate> findByTutorId(String tutorId);

    @Query(value = "{'certificate_id' : ?0}")
    public TutorCertificate findByCertificateId(String certificateId);
}
