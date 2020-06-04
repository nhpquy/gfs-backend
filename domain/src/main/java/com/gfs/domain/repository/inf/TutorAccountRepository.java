package com.gfs.domain.repository.inf;

import com.gfs.domain.document.TutorAccount;
import com.gfs.domain.repository.extend.TutorAccountRepositoryExtend;
import org.springframework.data.mongodb.repository.Query;

public interface TutorAccountRepository extends ObjectIdMongoRepository<TutorAccount>,
        TutorAccountRepositoryExtend {
    @Query(value = "{'account_id' : ?0}")
    public TutorAccount findByAccountId(String accountId);

    @Query(value = "{'email' : ?0}")
    public TutorAccount findByEmail(String email);

    @Query(value = "{'phone_number' : ?0}")
    public TutorAccount findByPhoneNumber(String phoneNumber);

    @Query(value = "{'fb_account_id' : ?0}")
    public TutorAccount findByFacebookId(String fbId);

    @Query(value = "{'gg_account_id' : ?0}")
    public TutorAccount findByGoogleId(String ggId);
}
