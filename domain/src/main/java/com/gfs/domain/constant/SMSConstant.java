package com.gfs.domain.constant;

import java.util.HashMap;
import java.util.Map;

public class SMSConstant {
    public static final Map<Integer, Integer> RETRY_SMS;

    static {
        RETRY_SMS = new HashMap<Integer, Integer>();
        RETRY_SMS.put(0, 60);
        RETRY_SMS.put(1, 120);
        RETRY_SMS.put(2, 180);
        RETRY_SMS.put(3, 240);
        RETRY_SMS.put(4, 300);
        RETRY_SMS.put(5, -1);
    }

    public static final String SEND_SMS_METHOD_TWILIO = "twilio";
    public static final String SEND_SMS_METHOD_VIETGUYS = "vietguys";

    public static final String SMS_VERIFY_CODE_TEMPLATE = "Your GFS verify code is @verify_code@";
}
