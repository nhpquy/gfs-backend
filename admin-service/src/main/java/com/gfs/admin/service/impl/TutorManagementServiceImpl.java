package com.gfs.admin.service.impl;

import com.gfs.admin.service.inf.AdminActivityService;
import com.gfs.admin.service.inf.TutorManagementService;
import com.gfs.domain.document.TutorAccount;
import com.gfs.domain.enums.AccountStatus;
import com.gfs.domain.enums.admin.AdminAction;
import com.gfs.domain.model.CurrentAdminLogin;
import com.gfs.domain.repository.inf.AccountAuthorizedTokenRepository;
import com.gfs.domain.repository.inf.TutorAccountRepository;
import com.gfs.domain.request.AdminApproveTutorRequest;
import com.gfs.domain.request.AdminDeactiveTutorRequest;
import com.gfs.domain.request.AdminListTutorRequest;
import com.gfs.domain.request.admin.BanTutorIdRequest;
import com.gfs.domain.response.AdminTutorAccountInfoResponse;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.domain.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TutorManagementServiceImpl implements TutorManagementService {

    @Autowired
    TutorAccountRepository tutorAccountRepository;

    @Autowired
    AdminActivityService adminActivityService;

    @Autowired
    AccountAuthorizedTokenRepository accountAuthorizedTokenRepository;

    @Override
    public List<AdminTutorAccountInfoResponse> listTutor(AdminListTutorRequest request) {
        List<TutorAccount> tutorAccounts = tutorAccountRepository.listTutor(request);
        List<AdminTutorAccountInfoResponse> responses = tutorAccounts
                .stream().map(AdminTutorAccountInfoResponse::new)
                .collect(Collectors.toList());
        return Utils.processPagingResponse(responses, request.getAction());
    }

    @Override
    public AdminTutorAccountInfoResponse getTutorDetail(String tutorId) {
        TutorAccount tutorAccount = tutorAccountRepository.findByAccountId(tutorId);
        if (tutorAccount == null)
            throw ServiceExceptionUtils.accountNotFound();

        return new AdminTutorAccountInfoResponse(tutorAccount);
    }

    @Override
    public AdminTutorAccountInfoResponse activeTutor(CurrentAdminLogin currentAdminLogin, AdminApproveTutorRequest request) {
        TutorAccount tutorAccount = tutorAccountRepository.findByAccountId(request.getTutor_id());
        if (tutorAccount == null)
            throw ServiceExceptionUtils.accountNotFound();

        if (AccountStatus.approved == tutorAccount.getAccount_status())
            throw ServiceExceptionUtils.tutorAlreadyApproved();

        if (AccountStatus.verifying == tutorAccount.getAccount_status())
            throw ServiceExceptionUtils.accountNotActivated();

        TutorAccount tutorAccountUpdated = tutorAccountRepository.updateAccountDetail(tutorAccount.getAccount_id(), Collections.singletonMap("account_status", AccountStatus.approved));
        adminActivityService.newActivity(currentAdminLogin.getAccount(), AdminAction.approve_tutor, tutorAccount.getAccount_id(), request.getNote(), null, tutorAccount, tutorAccountUpdated);
        return new AdminTutorAccountInfoResponse(tutorAccountUpdated);
    }

    @Override
    public AdminTutorAccountInfoResponse deactiveTutor(CurrentAdminLogin currentAdminLogin, AdminDeactiveTutorRequest request) {
        TutorAccount tutorAccount = tutorAccountRepository.findByAccountId(request.getTutor_id());
        if (tutorAccount == null)
            throw ServiceExceptionUtils.accountNotFound();

        if (AccountStatus.deactive == tutorAccount.getAccount_status())
            throw ServiceExceptionUtils.tutorAlreadyDeactive();

        if (AccountStatus.verifying == tutorAccount.getAccount_status())
            throw ServiceExceptionUtils.accountNotActivated();

        TutorAccount tutorAccountUpdated = tutorAccountRepository.updateAccountDetail(tutorAccount.getAccount_id(), Collections.singletonMap("account_status", AccountStatus.deactive));
        accountAuthorizedTokenRepository.deleteOtherToken(tutorAccount.getAccount_id(), null);
        adminActivityService.newActivity(currentAdminLogin.getAccount(), AdminAction.deactive_tutor, tutorAccount.getAccount_id(), request.getNote(), null, tutorAccount, tutorAccountUpdated);
        return new AdminTutorAccountInfoResponse(tutorAccountUpdated);
    }

    public AdminTutorAccountInfoResponse banAccount(CurrentAdminLogin currentAdminLogin, BanTutorIdRequest request) {
        TutorAccount tutorAccount = tutorAccountRepository.findByAccountId(request.getTutor_id());
        if (tutorAccount == null) {
            throw ServiceExceptionUtils.accountNotFound();
        }
        if (AccountStatus.banned == tutorAccount.getAccount_status()) {
            throw ServiceExceptionUtils.accountAlreadyBanned();
        }
        Map<String, Object> filterAccount = new HashMap<>();
        filterAccount.put("account_status", AccountStatus.banned);

        TutorAccount tutorAccountUpdated = tutorAccountRepository.updateAccountDetail(request.getTutor_id(), filterAccount);

        adminActivityService.newActivity(currentAdminLogin.getAccount(), AdminAction.ban_tutor, tutorAccount.getAccount_id(), request.getNote(), null, tutorAccount, tutorAccountUpdated);

        return new AdminTutorAccountInfoResponse(tutorAccountUpdated);
    }
}
