package com.gfs.services.impl;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.TutorAccount;
import com.gfs.domain.document.TutorCertificate;
import com.gfs.domain.enums.AccountLoginType;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.enums.AccountStatus;
import com.gfs.domain.enums.SendEmailType;
import com.gfs.domain.repository.inf.TutorAccountRepository;
import com.gfs.domain.repository.inf.TutorCertificateRepository;
import com.gfs.domain.request.*;
import com.gfs.domain.response.AccountLoginResponse;
import com.gfs.domain.response.GeneralSubmitResponse;
import com.gfs.domain.response.TutorAccountInfoResponse;
import com.gfs.domain.response.TutorCertificateResponse;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.domain.utils.StringUtils;
import com.gfs.services.inf.AccountAuthorizationService;
import com.gfs.services.inf.AccountService;
import com.gfs.services.inf.TutorAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.gfs.services.impl.AccountServiceImpl.*;

@Service
public class TutorAccountServiceImpl implements TutorAccountService {
    @Autowired
    TutorAccountRepository tutorAccountRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountAuthorizationService accountAuthorizationService;

    @Autowired
    TutorCertificateRepository tutorCertificateRepository;

    @Override
    public AccountLoginResponse registerAccount(RegisterAccountRequest request) throws Exception {

        String email = StringUtils.handleEmailOrPhoneNumber(request.getEmail());

        TutorAccount tutorAccount = tutorAccountRepository.findByEmail(email);
        if (tutorAccount != null)
            throw ServiceExceptionUtils.emailExists();

        Account account = accountService.createAccount(AccountProfile.tutor, request);
        tutorAccount = new TutorAccount(account);
        tutorAccount = tutorAccountRepository.save(tutorAccount);
        tutorAccount.setActivate_token(generateVerificationToken(6, TimeUnit.DAYS.toSeconds(1)));
        tutorAccount = tutorAccountRepository.save(tutorAccount);

        sendEmailActivateAccount(tutorAccount, SendEmailType.Gmail);

        return accountAuthorizationService.loginSuccessfully(tutorAccount, request, AccountLoginType.phone);
    }

    @Override
    public List<TutorCertificateResponse> getTutorCertificate(TutorAccount tutorAccount) {
        return tutorCertificateRepository.findByTutorId(tutorAccount.getAccount_id())
                .stream().map(TutorCertificateResponse::new).collect(Collectors.toList());
    }

    @Override
    public TutorCertificateResponse updateTutorCertificate(TutorAccount tutorAccount, UpdateTutorCertificateRequest request) {
        TutorCertificate certificate = tutorCertificateRepository.findByCertificateId(request.getCertificate_id());
        if (certificate == null || !tutorAccount.getAccount_id().equals(certificate.getTutor_id()))
            throw ServiceExceptionUtils.certificateNotFound();

        Map<String, Object> updateValues = new HashMap<>();
        if (StringUtils.hasText(request.getName()))
            updateValues.put("name", request.getName());
        if (StringUtils.hasText(request.getMain_photo()))
            updateValues.put("main_photo", request.getMain_photo());
        if (StringUtils.hasText(request.getAdditional_photo()))
            updateValues.put("additional_photo", request.getAdditional_photo());
        if (updateValues.size() > 0)
            certificate = tutorCertificateRepository.updateDetail(certificate.getCertificate_id(), updateValues);
        return new TutorCertificateResponse(certificate);
    }

    @Override
    public TutorCertificateResponse addTutorCertificate(TutorAccount tutorAccount, AddTutorCertificateRequest request) {
        TutorCertificate certificate = new TutorCertificate();
        certificate.setCertificate_id(UUID.randomUUID().toString());
        certificate.setAdditional_photo(request.getAdditional_photo());
        certificate.setMain_photo(request.getMain_photo());
        certificate.setName(request.getName());
        certificate.setTutor_id(tutorAccount.getAccount_id());
        certificate = tutorCertificateRepository.save(certificate);
        return new TutorCertificateResponse(certificate);
    }

    @Override
    public GeneralSubmitResponse removeTutorCertificate(TutorAccount tutorAccount, RemoveTutorCertificateRequest request) {
        TutorCertificate certificate = tutorCertificateRepository.findByCertificateId(request.getCertificate_id());
        if (certificate == null || !tutorAccount.getAccount_id().equals(certificate.getTutor_id()))
            throw ServiceExceptionUtils.certificateNotFound();
        tutorCertificateRepository.delete(certificate);
        return new GeneralSubmitResponse(true);
    }

    @Override
    public TutorAccountInfoResponse updateTutorProfile(TutorAccount tutorAccount, UpdateTutorProfileRequest request) {
        Map<String, Object> updateValues = updateGeneralAccountProfile(request);

        if (request.getShort_description() != null)
            updateValues.put("short_description", request.getShort_description());
        if (request.getCertificate() != null)
            updateValues.put("certificate", request.getCertificate());
        if (StringUtils.hasText(request.getExperience()))
            updateValues.put("experience", request.getExperience());
        if (request.getSpecializes() != null)
            updateValues.put("specializes", request.getSpecializes());
        if (request.getBank() != null)
            updateValues.put("bank", request.getBank());
        if (request.getBank() != null)
            updateValues.put("current_school", request.getCurrent_school());

        if (updateValues.size() > 0)
            tutorAccount = tutorAccountRepository.updateAccountDetail(tutorAccount.getAccount_id(), updateValues);
        return new TutorAccountInfoResponse(tutorAccount);
    }

    @Override
    public TutorAccount ifBeAvailableTutor(String tutorId) {
        TutorAccount tutor = tutorAccountRepository.findByAccountId(tutorId);

        // Check availability of tutor who is invited
        if (tutor == null) {
            throw ServiceExceptionUtils.accountNotFound();
        }

        if (tutor.getAccount_status() != AccountStatus.activated && tutor.getAccount_status() != AccountStatus.approved) {
            throw ServiceExceptionUtils.accountNotActivated();
        }

        return tutor;
    }

    public TutorAccount findTutorByPhone(String phoneNumber) {
        TutorAccount tutor = tutorAccountRepository.findByPhoneNumber(phoneNumber);

        // Check availability of tutor who is invited
        if (tutor == null) {
            throw ServiceExceptionUtils.accountNotFound();
        }

        if (tutor.getAccount_status() != AccountStatus.activated && tutor.getAccount_status() != AccountStatus.approved) {
            throw ServiceExceptionUtils.accountNotActivated();
        }
        return tutor;
    }
}
