package com.gfs.domain.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class HMACSHA256 {

    public static String genSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HMACSHA256");
            SecretKey secretKey = keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String signHexString(String key, String data) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HMACSHA256");
            byte[] decodedKey = key.getBytes();
            SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HMACSHA256");
            sha256_HMAC.init(secretKey);

            return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
        } catch (Exception e) {
        }
        return null;
    }

    public static byte[] sign(byte[] key, byte[] data) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HMACSHA256");
            SecretKey secretKey = new SecretKeySpec(key, 0, key.length, "HMACSHA256");
            sha256_HMAC.init(secretKey);
            return sha256_HMAC.doFinal(data);
        } catch (Exception e) {
        }
        return null;
    }

    public static String signHexString(SecretKey secretKey, String data) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HMACSHA256");
            sha256_HMAC.init(secretKey);
            return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] ahihi) {
        try {
            String key = genSecretKey();
            String key2 = genSecretKey();
            String authorization = signHexString(key2, UUID.randomUUID().toString());
            String pattern = signHexString(key, authorization);

            if (StringUtils.hasText(key)) {
                System.out.println("Key: " + key);
                System.out.println("Authorization: " + authorization);
                System.out.println("pattern: " + pattern);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}