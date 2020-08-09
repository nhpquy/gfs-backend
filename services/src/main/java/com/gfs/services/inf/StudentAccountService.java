package com.gfs.services.inf;

import com.gfs.domain.document.StudentAccount;
import com.gfs.domain.request.RegisterAccountRequest;
import com.gfs.domain.request.UpdateStudentProfileRequest;
import com.gfs.domain.response.AccountLoginResponse;
import com.gfs.domain.response.StudentAccountInfoResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface StudentAccountService {
    public AccountLoginResponse registerAccount(@Valid RegisterAccountRequest request) throws Exception;

    public StudentAccountInfoResponse updateStudentProfile(StudentAccount studentAccount, @Valid UpdateStudentProfileRequest request) throws Exception;
}
