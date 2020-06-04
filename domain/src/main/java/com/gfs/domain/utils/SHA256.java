package com.gfs.domain.utils;

import java.security.MessageDigest;

public class SHA256 {

    public static String hash(String sign) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(sign.getBytes("UTF-8"));

            // converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }

            digest = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return digest;
    }
}
