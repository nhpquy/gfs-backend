package com.gfs.domain.utils;

import com.gfs.domain.enums.AuthorizeTokenType;
import com.gfs.domain.enums.JwtAlgorithm;
import com.gfs.domain.model.jwt.JwtAccessToken;
import com.gfs.domain.model.jwt.JwtHeader;
import com.gfs.domain.model.jwt.JwtPayload;
import org.apache.commons.codec.binary.Base64;

public class JWTAccessTokenUtils {
    public static String createJwtAccessToken(JwtPayload jwtPayload) {
        JwtHeader header = new JwtHeader(JwtAlgorithm.HMACSHA256);
        JwtAccessToken accessToken = new JwtAccessToken(header, jwtPayload);
        return accessToken.toString();
    }

    public static JwtAccessToken decodeAccessToken(String authorization) {
        if (StringUtils.isEmpty(authorization))
            return null;
        if (!authorization.startsWith(AuthorizeTokenType.Bearer.name()))
            return null;

        String accessToken = authorization.substring(6).trim();
        String[] data = accessToken.split("\\.");
        if (data.length != 3)
            return null;
        String header = new String(Base64.decodeBase64(data[0]));
        String payload = new String(Base64.decodeBase64(data[1]));
        String signature = new String(Base64.decodeBase64(data[2]));
        JwtHeader jwtHeader = GsonSingleton.getInstance().fromJson(header, JwtHeader.class);
        JwtPayload jwtPayload = GsonSingleton.getInstance().fromJson(payload, JwtPayload.class);
        return new JwtAccessToken(jwtHeader, jwtPayload, signature);
    }
}
