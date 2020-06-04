package com.gfs.domain.repository.inf;

import com.gfs.domain.document.Account;
import com.gfs.domain.enums.AccountProfile;

import java.util.Map;

public interface AccountRepository {
    public Account updateAccountDetail(Account account, Map<String, Object> updateValues);

    public Account updateAccountDetail(AccountProfile profile, String accountId, Map<String, Object> updateValues);

    public Account findAccountByEmail(AccountProfile profile, String email);

    public Account findAccountByAccountId(AccountProfile profile, String accountId);

    public Account setPassword(AccountProfile profile, String accountId, String securePassword);
}
