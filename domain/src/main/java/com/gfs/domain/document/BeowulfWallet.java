package com.gfs.domain.document;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.model.bwfdata.AuthorityData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = CollectionName.BWF_WALLETS)
public class BeowulfWallet extends ObjectIdDocument {
    @Indexed(unique = true)
    private String name;
    private AuthorityData permissions;
    private boolean multisig;
    private String creator;
    @Indexed
    private String created_txn_id;
    private long created_time;

    @Indexed
    private String org_id; // link to org_Id
    private String description;
    private String encrypted_key;
}
