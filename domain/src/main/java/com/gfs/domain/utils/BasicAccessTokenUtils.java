package com.gfs.domain.utils;

import com.gfs.domain.enums.AuthorizeTokenType;
import com.gfs.domain.model.AuthorizationData;
import org.apache.commons.codec.binary.Base64;

public class BasicAccessTokenUtils {
    public static String createBasicAccessToken(String id, String token) {
        return Base64.encodeBase64String((id + ":" + token).getBytes());
    }

    public static AuthorizationData decodeBasicAccessToken(String authorization) {
        if (StringUtils.isEmpty(authorization))
            return null;
        if (!authorization.startsWith(AuthorizeTokenType.Basic.name()))
            return null;

        String accessToken = authorization.substring(5).trim();
        String data = new String(Base64.decodeBase64(accessToken));
        String[] datas = data.split(":");
        if (datas.length != 2)
            return null;
        return new AuthorizationData(datas[0], datas[1]);
    }
}
