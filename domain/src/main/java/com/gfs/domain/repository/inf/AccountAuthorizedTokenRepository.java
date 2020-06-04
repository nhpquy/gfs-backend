package com.gfs.domain.repository.inf;

import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.repository.extend.AccountAuthorizedTokenRepositoryExtend;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AccountAuthorizedTokenRepository extends ObjectIdMongoRepository<AccountAuthorizedToken>,
        AccountAuthorizedTokenRepositoryExtend {
    @Query(value = "{'token' : ?0}")
    public AccountAuthorizedToken findByToken(String token);

    @Query(value = "{'account_id' : ?0}")
    public List<AccountAuthorizedToken> findByAccountId(String accountId);

    @Query(value = "{'account_id' : ?0, 'profile_type' : ?1}")
    public List<AccountAuthorizedToken> findAccountIdAndProfile(String accountId, String profile);
}
