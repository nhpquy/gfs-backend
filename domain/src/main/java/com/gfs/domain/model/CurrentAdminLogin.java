package com.gfs.domain.model;

import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.document.AdminAccount;
import com.gfs.domain.exception.ServiceException;
import lombok.Data;

@Data
public class CurrentAdminLogin {
    private AdminAccount account;
    private AccountAuthorizedToken authorizedToken;
    private ServiceException error;

    public CurrentAdminLogin() {
    }

    public CurrentAdminLogin(AdminAccount account, AccountAuthorizedToken authorizedToken) {
        this.account = account;
        this.authorizedToken = authorizedToken;
    }

    public CurrentAdminLogin(ServiceException e) {
        this.error = e;
    }

    public boolean hasError() {
        return this.error != null;
    }
}
