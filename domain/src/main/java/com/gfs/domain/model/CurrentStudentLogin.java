package com.gfs.domain.model;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.document.StudentAccount;
import com.gfs.domain.exception.ServiceException;
import lombok.Data;

@Data
public class CurrentStudentLogin extends CurrentAccountLogin {
    private StudentAccount studentAccount;

    public CurrentStudentLogin() {
    }

    public CurrentStudentLogin(Account account, AccountAuthorizedToken authorizedToken) {
        super(account, authorizedToken);
    }

    public CurrentStudentLogin(ServiceException e) {
        super(e);
    }

    public CurrentStudentLogin(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
    }

    public CurrentStudentLogin(StudentAccount studentAccount, AccountAuthorizedToken authorizedToken) {
        super(studentAccount, authorizedToken);
        this.studentAccount = studentAccount;
    }

    public CurrentStudentLogin(ServiceException e, StudentAccount studentAccount) {
        super(e);
        this.studentAccount = studentAccount;
    }
}
