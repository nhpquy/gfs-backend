package com.gfs.domain.document;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.enums.*;
import com.gfs.domain.model.jwt.JwtPayload;
import com.gfs.domain.request.LoginRequest;
import com.gfs.domain.utils.BasicAccessTokenUtils;
import com.gfs.domain.utils.JWTAccessTokenUtils;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Document(collection = CollectionName.ACCOUNT_AUTH_TOKENS)
@Data
public class AccountAuthorizedToken extends ObjectIdDocument {
    @Indexed
    private String account_id;
    private AccountProfile profile_type;
    @Indexed(unique = true)
    private String token;
    private AuthorizeTokenType token_type;
    private AuthorizeTokenRole role;
    private CurrentLoginRequirement current_requirement;
    private boolean authorized;
    private long expire_seconds;
    @Indexed(expireAfterSeconds = 2592000) // 30 days
    private Date expired_from;
    private String verify_code;

    private String app_version;
    private String app_platform;
    private AccountLoginType login_type;
    private String user_token;
    private String user_agent;

    public AccountAuthorizedToken() {
        super();
        expire_seconds = 2592000;
        expired_from = new Date();
    }

    public void setExpire_seconds(long expire_seconds) {
        this.expire_seconds = expire_seconds;
        this.expired_from = new Date(new Date().getTime() - 2592000000L + expire_seconds * 1000L);
    }

    public String genAccessToken() throws Exception {
        switch (token_type) {
            case Bearer:
                // remain second to expire
                long expiresIn = expire_seconds - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - this.getCreated_at());
                return JWTAccessTokenUtils.createJwtAccessToken(new JwtPayload(account_id, token, (int) expiresIn));
            case Basic:
                return BasicAccessTokenUtils.createBasicAccessToken(account_id, token);
            default:
                throw new Exception("Unknown token type");
        }
    }

    public static AccountAuthorizedToken generateLoginSuccessfullyToken(Account account, LoginRequest request, AccountLoginType loginType) {
        AccountAuthorizedToken authorizedToken = new AccountAuthorizedToken();
        authorizedToken.setAccount_id(account.getAccount_id());
        authorizedToken.setApp_platform(request.getApp_platform());
        authorizedToken.setApp_version(request.getApp_version());
        authorizedToken.setAuthorized(true);
        authorizedToken.setExpire_seconds(TimeUnit.DAYS.toSeconds(30));
        authorizedToken.setLogin_type(loginType);
        authorizedToken.setProfile_type(account.getProfile_type());
        authorizedToken.setToken(UUID.randomUUID().toString());
        authorizedToken.setToken_type(AuthorizeTokenType.Basic);
        authorizedToken.setUser_agent(request.getUser_agent());
        authorizedToken.setUser_token(null);
        return authorizedToken;
    }
}
