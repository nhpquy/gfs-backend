package com.gfs.domain.document;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.enums.*;
import com.gfs.domain.model.jwt.JwtPayload;
import com.gfs.domain.utils.BasicAccessTokenUtils;
import com.gfs.domain.utils.JWTAccessTokenUtils;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
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
}
