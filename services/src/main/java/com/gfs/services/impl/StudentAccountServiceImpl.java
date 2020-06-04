package com.gfs.services.impl;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.StudentAccount;
import com.gfs.domain.enums.AccountLoginType;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.enums.SendEmailType;
import com.gfs.domain.repository.inf.StudentAccountRepository;
import com.gfs.domain.repository.inf.TutorAccountRepository;
import com.gfs.domain.request.RegisterAccountRequest;
import com.gfs.domain.request.UpdateStudentProfileRequest;
import com.gfs.domain.response.AccountLoginResponse;
import com.gfs.domain.response.StudentAccountInfoResponse;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.domain.utils.StringUtils;
import com.gfs.services.inf.AccountAuthorizationService;
import com.gfs.services.inf.AccountService;
import com.gfs.services.inf.StudentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.gfs.services.impl.AccountServiceImpl.*;


@Service
@Validated
public class StudentAccountServiceImpl implements StudentAccountService {

    @Autowired
    StudentAccountRepository studentAccountRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountAuthorizationService accountAuthorizationService;


    @Autowired
    TutorAccountRepository tutorAccountRepository;

    @Override
    public AccountLoginResponse registerAccount(RegisterAccountRequest request) throws Exception {

        String email = StringUtils.handleEmailOrPhoneNumber(request.getEmail());

        StudentAccount studentAccount = studentAccountRepository.findByEmail(email);
        if (studentAccount != null)
            throw ServiceExceptionUtils.emailExists();

        Account account = accountService.createAccount(AccountProfile.student, request);
        studentAccount = new StudentAccount(account);
        studentAccount = studentAccountRepository.save(studentAccount);
        studentAccount.setActivate_token(generateVerificationToken(6, TimeUnit.DAYS.toSeconds(1)));
        studentAccount = studentAccountRepository.save(studentAccount);

        sendEmailActivateAccount(studentAccount, SendEmailType.Gmail);

        return accountAuthorizationService.loginSuccessfully(studentAccount, request, AccountLoginType.email);
    }

    @Override
    public StudentAccountInfoResponse updateStudentProfile(StudentAccount studentAccount, UpdateStudentProfileRequest request) {
        Map<String, Object> updateValues = updateGeneralAccountProfile(request);
        if (request.getCurrent_school() != null)
            updateValues.put("current_school", request.getCurrent_school());
        if (request.getCurrent_job() != null)
            updateValues.put("current_job", request.getCurrent_job());

        if (updateValues.size() > 0)
            studentAccount = studentAccountRepository.updateAccountDetail(studentAccount.getAccount_id(), updateValues);
        return new StudentAccountInfoResponse(studentAccount);
    }
}
