package com.gfs.domain.utils;

import java.util.Base64;

public class VerificationTokenUtils {
    private static final String SECRET = "NgHXckcQOiVDcuCW";
    private static final String IV = "sMGKPFffKJhREeUB";

    public static String encode(String data) {
        String encode = AESEncryptor.encrypt(SECRET, IV, data);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(encode.getBytes());
    }

    public static String decode(String data) {
        String dataDecode = new String(Base64.getUrlDecoder().decode(data.getBytes()));
        return AESEncryptor.decrypt(SECRET, IV, dataDecode);
    }
}
