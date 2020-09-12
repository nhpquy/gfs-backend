package com.gfs.domain.model;

import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.document.JoinedOrganization;
import com.gfs.domain.document.TutorAccount;
import com.gfs.domain.exception.ServiceException;
import lombok.Data;

@Data
public class CurrentTutorLogin extends CurrentAccountLogin {
    private TutorAccount tutorAccount;
    private JoinedOrganization joinedOrg;

    public CurrentTutorLogin() {
    }

    public CurrentTutorLogin(ServiceException e) {
        super(e);
    }

    public CurrentTutorLogin(TutorAccount tutorAccount) {
        this.tutorAccount = tutorAccount;
    }

    public CurrentTutorLogin(TutorAccount tutorAccount, AccountAuthorizedToken authorizedToken, JoinedOrganization joinedOrg) {
        super(tutorAccount, authorizedToken);
        this.tutorAccount = tutorAccount;
        this.joinedOrg = joinedOrg;
    }
}
