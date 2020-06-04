package com.gfs.domain.utils;

import com.gfs.domain.config.ApplicationProperties;
import com.gfs.domain.response.FacebookDebugTokenResponse;
import com.gfs.domain.response.FacebookUserDetailResponse;

public class FacebookUtils {
    public static boolean validateUserAccessToken(String userAccessToken) {
        String apiUrl = String.format("https://graph.facebook.com/v6.0/debug_token?access_token=%s&debug=all&format=json&input_token=%s&method=get", ApplicationProperties.getFacebookAppAccessToken(), userAccessToken);
        String response = HttpUtility.sendGet(apiUrl);
        if (StringUtils.hasText(response)) {
            FacebookDebugTokenResponse dataResponse = GsonSingleton.getInstance().fromJson(response, FacebookDebugTokenResponse.class);
            if (dataResponse != null && dataResponse.getData() != null) {
                String appId = dataResponse.getData().getApp_id();
                if (!dataResponse.getData().isIs_valid())
                    return false;
                if (!ApplicationProperties.getFacebookAppClientIds().contains(appId))
                    return false;
                if (dataResponse.getData().getData_access_expires_at() * 1000 <= System.currentTimeMillis() ||
                        dataResponse.getData().getExpires_at() * 1000 <= System.currentTimeMillis())
                    return false;
                return true;
            }
        }
        return false;
    }

    public static FacebookUserDetailResponse getUserDetail(String userAccessToken) {
        String apiUrl = String.format("https://graph.facebook.com/v6.0/me?access_token=%s&format=json&method=get&fields=id,name,email,picture", userAccessToken);
        String response = HttpUtility.sendGet(apiUrl);
        if (StringUtils.hasText(response)) {
            FacebookUserDetailResponse userDetail = GsonSingleton.getInstance().fromJson(response, FacebookUserDetailResponse.class);
            return userDetail;
        }
        return null;
    }
}
