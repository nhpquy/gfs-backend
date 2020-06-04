package com.gfs.domain.response;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.document.AdminAccount;
import com.gfs.domain.enums.AuthorizeTokenType;
import com.gfs.domain.enums.CurrentLoginRequirement;
import lombok.Data;

@Data
public class AccountLoginResponse extends BaseResponse {
    private String access_token;
    private AuthorizeTokenType token_type;
    private String email;
    private String phone_number;
    private CurrentLoginRequirement require;

    public AccountLoginResponse() {
    }

    public AccountLoginResponse(String access_token, AuthorizeTokenType token_type, String email, String phone_number, CurrentLoginRequirement require) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.email = email;
        this.phone_number = phone_number;
        this.require = require;
    }

    public AccountLoginResponse(Account account, AccountAuthorizedToken token) throws Exception {
        this(token.getToken_type().name() + " " + token.genAccessToken(),
                token.getToken_type(),
                account.getEmail(),
                account.getPhone_number(),
                token.getCurrent_requirement() == null ? CurrentLoginRequirement.none : token.getCurrent_requirement());
    }

    public AccountLoginResponse(AdminAccount account, AccountAuthorizedToken token) throws Exception {
        this(token.getToken_type().name() + " " + token.genAccessToken(),
                token.getToken_type(),
                account.getEmail(),
                null,
                token.getCurrent_requirement() == null ? CurrentLoginRequirement.none : token.getCurrent_requirement());
    }
}
