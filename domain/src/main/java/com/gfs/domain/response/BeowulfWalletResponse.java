package com.gfs.domain.response;

import com.gfs.domain.document.BeowulfWallet;
import com.gfs.domain.model.bwfdata.AuthorityData;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BeowulfWalletResponse extends DocumentResponse {
    private String name;
    private AuthorityData permissions;
    private boolean multisig;
    private String creator;
    private String created_txn_id;
    private long created_time;
    private String org_id;
    private String description;

    public BeowulfWalletResponse(BeowulfWallet account) {
        super(account);
        this.setName(account.getName());
        this.setPermissions(account.getPermissions());
        this.setMultisig(account.isMultisig());
        this.setCreator(account.getCreator());
        this.setCreated_txn_id(account.getCreated_txn_id());
        this.setCreated_time(account.getCreated_time());
        this.setOrg_id(account.getOrg_id());
        this.setDescription(account.getDescription());
    }
}
