package com.gfs.domain.model.jwt;

import com.gfs.domain.config.ApplicationProperties;
import com.gfs.domain.utils.GsonSingleton;
import com.gfs.domain.utils.HMACSHA256;
import com.gfs.domain.utils.StringUtils;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;

@Data
public class JwtAccessToken {
    private JwtHeader header;
    private JwtPayload payload;
    private String signature;

    public JwtAccessToken() {
    }

    public JwtAccessToken(JwtHeader header, JwtPayload payload, String signature) {
        this.header = header;
        this.payload = payload;
        this.signature = signature;
    }

    public JwtAccessToken(JwtHeader header, JwtPayload payload) {
        this.header = header;
        this.payload = payload;
        String data = String.format("%s.%s",
                Base64.encodeBase64URLSafeString(GsonSingleton.getInstance().toJson(header).getBytes()),
                Base64.encodeBase64URLSafeString(GsonSingleton.getInstance().toJson(payload).getBytes()));
        this.signature = HMACSHA256.signHexString(ApplicationProperties.getJwtSecret(), data);
    }

    public boolean isValid() {
        String data = String.format("%s.%s",
                Base64.encodeBase64URLSafeString(GsonSingleton.getInstance().toJson(header).getBytes()),
                Base64.encodeBase64URLSafeString(GsonSingleton.getInstance().toJson(payload).getBytes()));
        String signature = HMACSHA256.signHexString(ApplicationProperties.getJwtSecret(), data);
        return StringUtils.match(signature, this.signature);
    }

    @Override
    public String toString() {
        return String.format("%s.%s.%s",
                Base64.encodeBase64URLSafeString(GsonSingleton.getInstance().toJson(header).getBytes()),
                Base64.encodeBase64URLSafeString(GsonSingleton.getInstance().toJson(payload).getBytes()),
                Base64.encodeBase64URLSafeString(signature.getBytes()));
    }
}
