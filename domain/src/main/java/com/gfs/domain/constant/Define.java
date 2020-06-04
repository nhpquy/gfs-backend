package com.gfs.domain.constant;

import java.util.concurrent.TimeUnit;

public class Define {

    public static final long SATOSHI_UNIT = 100000000;

    public static final String LANGUAGE_VI = "vi";
    public static final String LANGUAGE_EN = "sms_templates/en";
    public static final String LANGUAGE_ZH_CN = "zh_cn";
    public static final String LANGUAGE_KO = "ko";
    public static final String LANGUAGE_ES = "es";

    public static final long MAX_INACTIVE_INTERVAL_24H_AS_SECOND = TimeUnit.HOURS.toSeconds(24);
    public static final long MAX_INACTIVE_INTERVAL_5MINUTE_AS_SECOND = TimeUnit.MINUTES.toSeconds(1);
}
