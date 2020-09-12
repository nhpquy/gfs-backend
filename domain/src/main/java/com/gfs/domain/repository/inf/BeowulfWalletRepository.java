package com.gfs.domain.repository.inf;

import com.gfs.domain.document.BeowulfWallet;
import com.gfs.domain.repository.extend.BeowulfWalletRepositoryExtend;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BeowulfWalletRepository extends MongoRepository<BeowulfWallet, ObjectId>, BeowulfWalletRepositoryExtend {
    @Query(value = "{'name': ?0}")
    BeowulfWallet findByName(String name);

    @Query(value = "{'org_id': ?0}")
    List<BeowulfWallet> findByOrg_id(String orgId);

    @Query(value = "{'created_txn_id': ?0}")
    List<BeowulfWallet> findByCreated_txn_id(String transactionId);
}
