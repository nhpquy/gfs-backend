package com.gfs.domain.utils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class HttpProvider {

    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        System.out.println("X-Forwarded-For: " + ip);
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
            System.out.println("Proxy-Client-IP: " + ip);
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            System.out.println("WL-Proxy-Client-IP: " + ip);
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            System.out.println("HTTP_X_FORWARDED_FOR: " + ip);
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_FORWARDED");
            System.out.println("HTTP_X_FORWARDED: " + ip);
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
            System.out.println("HTTP_X_CLUSTER_CLIENT_IP: " + ip);
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            System.out.println("HTTP_CLIENT_IP: " + ip);
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_FORWARDED_FOR");
            System.out.println("HTTP_FORWARDED_FOR: " + ip);
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_FORWARDED");
            System.out.println("HTTP_FORWARDED: " + ip);
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_VIA");
            System.out.println("HTTP_VIA: " + ip);
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("REMOTE_ADDR");
            System.out.println("REMOTE_ADDR: " + ip);
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
            System.out.println("request.getRemoteAddr(): " + ip);
        }
        return ip;
    }

    public static String getRemoteIPAddress(HttpServletRequest request) {
        //getClientIpAddr(request);

        String ip = request.getHeader("X-Forwarded-For");
        if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip) && isPublicInternetIp(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Real-IP");
        if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
            // get first ip from proxy ip
            int index = ip.indexOf(',');
            if (index != -1) {
                if (isPublicInternetIp(ip.substring(0, index)))
                    return ip.substring(0, index);
            } else {
                if (isPublicInternetIp(ip))
                    return ip;
            }
        }
        return request.getRemoteAddr();
    }

    public static List<String> getRemoteIPAddresses(HttpServletRequest request) {
        //getClientIpAddr(request);

//        String ip = request.getHeader("X-Real-IP");
//        if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
//            return Arrays.asList((ip + "," + request.getRemoteAddr()).split(","));
//        }
        String ip = request.getHeader("X-Forwarded-For");
        if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
            return Arrays.asList((ip + "," + request.getRemoteAddr()).split(","));
        }
        ip = request.getHeader("X-Real-IP");
        if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
            return Arrays.asList((ip + "," + request.getRemoteAddr()).split(","));
        }
        ip = request.getHeader("x-remote-ip");
        if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
            return Arrays.asList((ip + "," + request.getRemoteAddr()).split(","));
        }
        return Arrays.asList(request.getRemoteAddr());
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = getRemoteIPAddress(request);
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    public static String getOSName(HttpServletRequest request) {
        try {
            String userAgent = request.getHeader("user-agent");
            UserAgent ua = UserAgent.parseUserAgentString(userAgent);
            return ua.getOperatingSystem().getName();
        } catch (Exception e) {
            return "";
        }

    }

    public static String getBrowserName(HttpServletRequest request) {
        try {
            String userAgent = request.getHeader("user-agent");
            UserAgent ua = UserAgent.parseUserAgentString(userAgent);
            Browser browser = ua.getBrowser();
            Browser parent = browser.getGroup();
            if (parent != null)
                return parent.getName();
            return browser.getName();
        } catch (Exception e) {
            return "";
        }

    }

    /* This method check whether addStr is a valid ip address or not.
     * addStr : A given ip string.
     * return : true means valid, false means invalid.
     * */
    public static boolean isValidIp(String addStr) {
        boolean ret = true;
        // If addStr is empty or reserved address then return false.
        if ("".equals(addStr) || addStr == null || "0.0.0.0".equals(addStr)) {
            return false;
        }

        // Split ip address string into 4 length string array.
        String ipEle[] = addStr.split("\\.");
        // If ip address do not include four parts return false.
        if (ipEle.length != 4) {
            return false;
        } else {
            // Loop for the four parts in ip address.
            for (int i = 0; i < 4; i++) {
                String ipEleStr = ipEle[i];
                // If not integer return false.
                if (!StringUtils.isInteger(ipEleStr)) {
                    return false;
                } else {
                    // If less than zero or bigger than 256 return false.
                    if (Integer.parseInt(ipEleStr) < 0 || Integer.parseInt(ipEleStr) >= 256) {
                        return false;
                    } else {
                        // If the first part in ip address is 127 return false.
                        if (i == 0 && Integer.parseInt(ipEleStr) == 127) {
                            return false;
                        }
                    }
                }
            }
        }

        return ret;
    }

    /* Checkout whether ipStr is a public network ip.
     * ipStr : String ip address.
     * return : true means ipStr is a public network ip, false means ipStr is not a public network ip.
     * */
    public static boolean isPublicInternetIp(String ipStr) {
        boolean ret = true;

        if (StringUtils.isEmpty(ipStr)) {
            ret = false;
        } else {
            ipStr = ipStr.trim();
            // If ip is not valid return false.
            if (!isValidIp(ipStr)) {
                ret = false;
            } else {
                //If ip is reserved local network address ip return false.
                if (ipStr.startsWith("0.") || ipStr.startsWith("10.") || ipStr.startsWith("127.") || ipStr.startsWith("192.168.") || ipStr.startsWith("169.254.")) {
                    ret = false;
                } else {
                    // Split ip address into four parts array.
                    String ipColumnArr[] = ipStr.split("\\.");
                    // If the array dose not include four parts return false.
                    if (ipColumnArr.length != 4) {
                        ret = false;
                    } else {
                        String ipC0 = ipColumnArr[0];
                        String ipC1 = ipColumnArr[1];
                        String ipC2 = ipColumnArr[2];
                        String ipC3 = ipColumnArr[3];

                        // If any part in ip address is not an integer.
                        if (!StringUtils.isInteger(ipC0) || !StringUtils.isInteger(ipC1) || !StringUtils.isInteger(ipC2) || !StringUtils.isInteger(ipC3)) {
                            ret = false;
                        } else {
                            /*
                             * Check reserved ip address:
                             * 224.0.0.0 to 255.255.255.255
                             * 100.64.x.x－100.127.x.x[3]  、172.16.x.x－172.31.x.x、
                             * */
                            // Check first two parts.
                            int ipC0Int = Integer.parseInt(ipC0);
                            int ipC1Int = Integer.parseInt(ipC1);

                            // Filter out reserved ip address.
                            if (ipC0Int >= 224) {
                                ret = false;
                            } else {
                                if (ipC0Int == 100) {
                                    if (ipC1Int >= 64 && ipC1Int <= 127) {
                                        ret = false;
                                    }
                                } else if (ipC0Int == 172) {
                                    if (ipC1Int >= 16 && ipC1Int <= 31) {
                                        ret = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        System.out.println(isPublicInternetIp("127.0.0.1"));
        System.out.println(isPublicInternetIp("115.79.193.205"));
    }
}
