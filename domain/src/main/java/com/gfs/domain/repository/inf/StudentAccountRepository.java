package com.gfs.domain.repository.inf;

import com.gfs.domain.document.StudentAccount;
import com.gfs.domain.repository.extend.StudentAccountRepositoryExtend;
import org.springframework.data.mongodb.repository.Query;

public interface StudentAccountRepository extends ObjectIdMongoRepository<StudentAccount>,
        StudentAccountRepositoryExtend {
    @Query(value = "{'account_id' : ?0}")
    public StudentAccount findByAccountId(String accountId);

    @Query(value = "{'email' : ?0}")
    public StudentAccount findByEmail(String email);

    @Query(value = "{'phone_number' : ?0}")
    public StudentAccount findByPhoneNumber(String phoneNumber);

    @Query(value = "{'fb_account_id' : ?0}")
    public StudentAccount findByFacebookId(String fbId);

    @Query(value = "{'gg_account_id' : ?0}")
    public StudentAccount findByGoogleId(String ggId);
}
