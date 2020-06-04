package com.gfs.domain.utils;

import eu.bitwalker.useragentutils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utils {
    private static final String TAG = Utils.class.getName();

    @SafeVarargs
    public static <T> List<T> toList(T... arrays) {
        return new ArrayList<>(Arrays.asList(arrays));
    }

    public static String getOSName(String userAgent) {
        try {
            UserAgent ua = UserAgent.parseUserAgentString(userAgent);
            OperatingSystem os = ua.getOperatingSystem();
            if (os != null && !DeviceType.UNKNOWN.equals(os.getDeviceType()))
                return os.getName();
        } catch (Exception e) {
        }
        return null;
    }

    public static String getBrowserName(String userAgent) {
        try {
            UserAgent ua = UserAgent.parseUserAgentString(userAgent);
            Browser browser = ua.getBrowser();
//            Browser parent = browser != null ? browser.getGroup() : null;
//            if (parent != null && !BrowserType.UNKNOWN.equals(parent.getBrowserType()))
//                return parent.getName();
            if (browser != null && !BrowserType.UNKNOWN.equals(browser.getBrowserType()))
                return browser.getName();
        } catch (Exception e) {
        }
        return null;
    }

    public static String getDevice(String appName, String appPlatform, String version, String browser, String os) {
        String device = appName + " ";
        if (StringUtils.hasText(appPlatform))
            device = device + appPlatform.toUpperCase() + " app ";
        if (StringUtils.hasText(version))
            device = device + version + " ";
        if (StringUtils.hasText(browser))
            device = device + browser + " ";
        if (StringUtils.hasText(os))
            device = device + os;
        return device.trim();
    }

    public static String getDevice(String appName, String appPlatform, String version, String userAgent) {
        String browser = getBrowserName(userAgent);
        String os = getOSName(userAgent);
        return getDevice(appName, appPlatform, version, browser, os);
    }

    public static <T> List<T> processPagingResponse(List<T> data, String action) {
        if ("previous".equals(action))
            Collections.reverse(data);
        return data;
    }
}
