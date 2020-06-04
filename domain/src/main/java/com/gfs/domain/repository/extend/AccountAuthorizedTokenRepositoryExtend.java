package com.gfs.domain.repository.extend;

import com.gfs.domain.document.AccountAuthorizedToken;

import java.util.Map;

public interface AccountAuthorizedTokenRepositoryExtend {
    public AccountAuthorizedToken updateDetail(String token, Map<String, Object> updateValues);

    public AccountAuthorizedToken activated(AccountAuthorizedToken authorizedToken);

    public void deleteOtherToken(String accountId, String token);
}
