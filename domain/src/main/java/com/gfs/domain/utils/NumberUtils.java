package com.gfs.domain.utils;

public class NumberUtils extends org.springframework.util.NumberUtils {

    public static boolean isInteger(String value) {
        try {
            Integer.valueOf(value);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isLong(String value) {
        try {
            Long.valueOf(value);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isNumber(String value) {
        return isInteger(value) || isLong(value);
    }
}
