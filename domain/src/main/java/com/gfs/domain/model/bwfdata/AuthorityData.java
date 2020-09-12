package com.gfs.domain.model.bwfdata;

import com.beowulfchain.beowulfj.protocol.AccountName;
import com.beowulfchain.beowulfj.protocol.Authority;
import com.beowulfchain.beowulfj.protocol.PublicKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

@Data
@NoArgsConstructor
public class AuthorityData {
    private long weight_threshold;
    private LinkedHashMap<String, Integer> account_auths;
    private LinkedHashMap<String, Integer> key_auths;

    public AuthorityData(Authority authority) {
        this.weight_threshold = authority.getWeightThreshold();
        this.account_auths = new LinkedHashMap<>();
        for (AccountName accountName : authority.getAccountAuths().keySet()) {
            this.account_auths.put(accountName.getName(), authority.getAccountAuths().get(accountName));
        }
        this.key_auths = new LinkedHashMap<>();
        for (PublicKey publicKey : authority.getKeyAuths().keySet()) {
            this.key_auths.put(publicKey.getAddressFromPublicKey(), authority.getKeyAuths().get(publicKey));
        }
    }
}
