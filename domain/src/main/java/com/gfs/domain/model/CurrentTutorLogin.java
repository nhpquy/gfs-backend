package com.gfs.domain.model;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.document.TutorAccount;
import com.gfs.domain.exception.ServiceException;
import lombok.Data;

@Data
public class CurrentTutorLogin extends CurrentAccountLogin {
    private TutorAccount tutorAccount;

    public CurrentTutorLogin() {
    }

    public CurrentTutorLogin(Account account, AccountAuthorizedToken authorizedToken) {
        super(account, authorizedToken);
    }

    public CurrentTutorLogin(ServiceException e) {
        super(e);
    }

    public CurrentTutorLogin(TutorAccount tutorAccount) {
        this.tutorAccount = tutorAccount;
    }

    public CurrentTutorLogin(TutorAccount tutorAccount, AccountAuthorizedToken authorizedToken) {
        super(tutorAccount, authorizedToken);
        this.tutorAccount = tutorAccount;
    }

    public CurrentTutorLogin(ServiceException e, TutorAccount tutorAccount) {
        super(e);
        this.tutorAccount = tutorAccount;
    }
}
