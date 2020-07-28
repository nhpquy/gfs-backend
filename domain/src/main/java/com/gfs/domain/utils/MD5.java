package com.gfs.domain.utils;

import java.security.MessageDigest;

public class MD5 {

    /**
     * Hash a message string with MD5 hash algorithm
     *
     * @param sign : message will be hashed
     * @return A hash string if success or null if error occurred
     */
    public static String hash(String sign) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sign.getBytes());
            byte[] hash = md.digest();
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10)
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                else
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
            result = hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
}