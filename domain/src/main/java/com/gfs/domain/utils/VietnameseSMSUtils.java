package com.gfs.domain.utils;

import java.util.HashMap;

public class VietnameseSMSUtils {
    private static final HashMap<String, String> phoneCarrierMap = new HashMap<String, String>() {{
        put("86", "viettel");
        put("96", "viettel");
        put("97", "viettel");
        put("98", "viettel");
        put("32", "viettel");
        put("33", "viettel");
        put("34", "viettel");
        put("35", "viettel");
        put("36", "viettel");
        put("37", "viettel");
        put("38", "viettel");
        put("39", "viettel");
        put("89", "mobifone");
        put("90", "mobifone");
        put("93", "mobifone");
        put("70", "mobifone");
        put("79", "mobifone");
        put("77", "mobifone");
        put("76", "mobifone");
        put("78", "mobifone");
        put("88", "vinaphone");
        put("91", "vinaphone");
        put("94", "vinaphone");
        put("83", "vinaphone");
        put("84", "vinaphone");
        put("85", "vinaphone");
        put("81", "vinaphone");
        put("82", "vinaphone");
        put("92", "vietnamobile");
        put("56", "vietnamobile");
        put("58", "vietnamobile");
        put("99", "gmobile");
        put("59", "gmobile");
    }};

    public static String getPhoneCarrier(String phoneNumber) {
        String prefix = phoneNumber.substring(3, 5);
        return phoneCarrierMap.get(prefix);
    }
}
