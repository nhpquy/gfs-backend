package com.gfs.domain.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.springframework.util.StringUtils {

    public static boolean isEmpty(String... arrs) {
        for (String s : arrs) {
            if (org.springframework.util.StringUtils.isEmpty(s)) return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String... arrs) {
        return !isEmpty(arrs);
    }

    public static boolean isInteger(String... args) {
        for (String s : args) {
            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    public static boolean match(String s1, String s2) {
        if (hasText(s1))
            return s1.equals(s2);
        return false;
    }

    public static boolean matchIgnoreCase(String s1, String s2) {
        if (hasText(s1))
            return lowercase(s1).equals(lowercase(s2));
        return false;
    }

    public static String getFullname(String firstName, String lastName) {
        String fullName = "";
        if (StringUtils.hasText(firstName))
            fullName += firstName;
        if (StringUtils.hasText(lastName)) {
            if (StringUtils.hasText(fullName))
                fullName += " " + lastName;
            else fullName += lastName;
        }
        return fullName;
    }

    public static String removeSpace(String value) {
        if (isEmpty(value))
            return value;
        return value.trim().replace(" ", "");
    }

    public static boolean isValidEmailAddress(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+(\\d+)");
    }

    public static String handleEmailOrPhoneNumber(String value) {
        if (StringUtils.isEmpty(value))
            return value;
        value = value.toLowerCase().trim().replace(" ", "");
        if (isValidEmailAddress(value) || isValidPhoneNumber(value))
            return value;
        if (value.matches("(\\d+)")) {
            if (value.startsWith("0"))
                value = "+84" + value.substring(1);
        }
        return value;
    }

    public static String trim(String value) {
        if (isEmpty(value))
            return value;
        return value.trim();
    }

    public static String lowercase(String s) {
        if (hasText(s))
            return s.toLowerCase();
        return s;
    }

    public static String uppercase(String s) {
        if (hasText(s))
            return s.toUpperCase();
        return s;
    }

    public static String getOnlyDigits(String s) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(s);
        return matcher.replaceAll("");
    }

    public static String get(String value, String elseValue) {
        if (hasText(value))
            return value;
        return elseValue;
    }
}