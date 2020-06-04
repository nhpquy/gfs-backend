package com.gfs.domain.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
    public static String randomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String randomAlphanumericString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String randomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public static String randomAlphanumericLowerCaseString(int length) {
        return RandomStringUtils.randomAlphanumeric(length).toLowerCase();
    }

    public static String randomAlphanumericUpperCaseString(int length) {
        return RandomStringUtils.randomAlphanumeric(length).toUpperCase();
    }

    public static long randomLong() {
        long randlong = org.apache.commons.lang3.RandomUtils.nextLong(0, 1000L);
        return System.currentTimeMillis() * 1000 + randlong;
    }

    public static void main(String[] args) {
        System.out.println(randomString(16));
        System.out.println(randomString(16));
    }
}
