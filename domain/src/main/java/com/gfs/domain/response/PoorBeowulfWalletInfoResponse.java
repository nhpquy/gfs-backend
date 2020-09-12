package com.gfs.domain.response;

import com.gfs.domain.document.BeowulfWallet;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PoorBeowulfWalletInfoResponse extends DocumentResponse {
    private String name;
    private String org_id;

    public PoorBeowulfWalletInfoResponse(BeowulfWallet account) {
        super(account);
        this.setName(account.getName());
        this.setOrg_id(account.getOrg_id());
    }
}
