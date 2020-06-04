package com.gfs.domain.constant;

import com.gfs.domain.enums.AccountProfile;

import java.util.HashMap;
import java.util.Map;

public class AppName {
    public static final Map<AccountProfile, String> APP_NAME;

    static {
        APP_NAME = new HashMap<AccountProfile, String>();
        APP_NAME.put(AccountProfile.student, "GFS Student");
        APP_NAME.put(AccountProfile.tutor, "GFS Tutor");
    }
}
