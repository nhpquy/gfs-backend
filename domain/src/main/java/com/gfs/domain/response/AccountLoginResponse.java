package com.gfs.domain.response;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.document.AdminAccount;
import com.gfs.domain.document.JoinedOrganization;
import com.gfs.domain.enums.AuthorizeTokenType;
import com.gfs.domain.enums.CurrentLoginRequirement;
import com.gfs.domain.enums.OrgRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountLoginResponse extends BaseResponse {
    private String access_token;
    private AuthorizeTokenType token_type;
    private String email;
    private CurrentLoginRequirement require;
    private String org_id;
    private OrgRole org_role;

    public AccountLoginResponse(String access_token, AuthorizeTokenType token_type, String email, CurrentLoginRequirement require) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.email = email;
        this.require = require;
    }

    public AccountLoginResponse(Account account, AccountAuthorizedToken token) throws Exception {
        this(token.getToken_type().name() + " " + token.genAccessToken(),
                token.getToken_type(),
                account.getEmail(),
                token.getCurrent_requirement() == null ? CurrentLoginRequirement.none : token.getCurrent_requirement());
    }

    public AccountLoginResponse(Account account, AccountAuthorizedToken token, JoinedOrganization joinedOrg) throws Exception {
        this(token.getToken_type().name() + " " + token.genAccessToken(),
                token.getToken_type(),
                account.getEmail(),
                token.getCurrent_requirement() == null ? CurrentLoginRequirement.none : token.getCurrent_requirement(),
                joinedOrg != null ? joinedOrg.getOrg_id() : null,
                joinedOrg != null ? joinedOrg.getRole() : null
        );
    }

    public AccountLoginResponse(AdminAccount account, AccountAuthorizedToken token) throws Exception {
        this(token.getToken_type().name() + " " + token.genAccessToken(),
                token.getToken_type(),
                account.getEmail(),
                token.getCurrent_requirement() == null ? CurrentLoginRequirement.none : token.getCurrent_requirement());
    }
}
