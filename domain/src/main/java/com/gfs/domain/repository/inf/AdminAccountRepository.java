package com.gfs.domain.repository.inf;

import com.gfs.domain.document.AdminAccount;
import com.gfs.domain.repository.extend.AdminAccountRepositoryExtend;
import org.springframework.data.mongodb.repository.Query;

public interface AdminAccountRepository extends ObjectIdMongoRepository<AdminAccount>,
        AdminAccountRepositoryExtend {

    @Query(value = "{'account_id' : ?0}")
    public AdminAccount findByAccountId(String accountId);

    @Query(value = "{'username' : ?0}")
    public AdminAccount findByUsername(String username);
}
