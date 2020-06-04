package com.gfs.services.inf;

import com.gfs.domain.document.TutorAccount;
import com.gfs.domain.request.*;
import com.gfs.domain.response.AccountLoginResponse;
import com.gfs.domain.response.GeneralSubmitResponse;
import com.gfs.domain.response.TutorAccountInfoResponse;
import com.gfs.domain.response.TutorCertificateResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface TutorAccountService {
    public AccountLoginResponse registerAccount(@Valid RegisterAccountRequest request) throws Exception;

    public List<TutorCertificateResponse> getTutorCertificate(TutorAccount tutorAccount);

    public TutorCertificateResponse updateTutorCertificate(TutorAccount tutorAccount, @Valid UpdateTutorCertificateRequest request);

    public TutorCertificateResponse addTutorCertificate(TutorAccount tutorAccount, @Valid AddTutorCertificateRequest request);

    public GeneralSubmitResponse removeTutorCertificate(TutorAccount tutorAccount, @Valid RemoveTutorCertificateRequest request);

    public TutorAccountInfoResponse updateTutorProfile(TutorAccount tutorAccount, @Valid UpdateTutorProfileRequest request);

    public TutorAccount ifBeAvailableTutor(String tutorId);

    public TutorAccount findTutorByPhone(String phoneNumber);

}
