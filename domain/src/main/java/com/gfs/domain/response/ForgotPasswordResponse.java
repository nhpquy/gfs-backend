package com.gfs.domain.response;

import com.gfs.domain.document.AccountAuthorizedToken;
import lombok.Data;

@Data
public class ForgotPasswordResponse {
    private String access_token;

    public ForgotPasswordResponse() {
    }

    public ForgotPasswordResponse(String access_token) {
        this.access_token = access_token;
    }

    public ForgotPasswordResponse(AccountAuthorizedToken authorizedToken) throws Exception {
        this(authorizedToken.getToken_type().name() + " " + authorizedToken.genAccessToken());
    }
}