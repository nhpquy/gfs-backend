package com.gfs.domain.utils;

import java.util.Date;
import java.util.Random;

public class ChatUtils {

    private static final String AES_SECRET = "HyZwkmiRFcTkfWEj";
    private static final String AES_INIT_VECTOR = "XPgyRYIqEkZGGOIu";

    public static String encryptPassword(String password) {
        return AESEncryptor.encrypt(AES_SECRET, AES_INIT_VECTOR, password);
    }

    public static String decryptPassword(String encryptedPassword) {
        return AESEncryptor.decrypt(AES_SECRET, AES_INIT_VECTOR, encryptedPassword);
    }

    public static String randomChatId() {
        try {
            Random random = new Random();
            Date now = new Date();
            long date = now.getTime();
            String longRandomString = String.valueOf(Math.abs(random.nextLong()) % 9000 + 1000);
            String dateRandomString = String.valueOf(date);
            long result = Long.parseLong(longRandomString + dateRandomString);
            return String.valueOf(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getChatIdFromFullChatUsername(String chatUsername) {
        String[] args = StringUtils.hasText(chatUsername) ? chatUsername.split("@") : null;
        if (args != null && args.length > 0)
            return args[0];
        return chatUsername;
    }

    public static String getFullUsername(String username, String domain) {
        return username + "@" + domain;
    }
}
