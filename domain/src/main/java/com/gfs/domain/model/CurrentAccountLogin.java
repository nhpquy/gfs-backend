package com.gfs.domain.model;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.exception.ServiceException;
import lombok.Data;

@Data
public class CurrentAccountLogin {
    private Account account;
    private AccountAuthorizedToken authorizedToken;
    private ServiceException error;

    public CurrentAccountLogin() {
    }

    public CurrentAccountLogin(Account account, AccountAuthorizedToken authorizedToken) {
        this.account = account;
        this.authorizedToken = authorizedToken;
    }

    public CurrentAccountLogin(ServiceException e) {
        this.error = e;
    }

    public boolean hasError() {
        return this.error != null;
    }
}
