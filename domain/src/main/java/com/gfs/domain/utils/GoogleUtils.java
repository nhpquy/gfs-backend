package com.gfs.domain.utils;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.gfs.domain.config.ApplicationProperties;

public class GoogleUtils {
    private static final String TAG = GoogleUtils.class.getName();

    private static GoogleIdTokenVerifier googleIdTokenVerifier;

    public static void init() {
        try {
            googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                    .setAudience(ApplicationProperties.getGoogleAppClientIds())
                    .build();
        } catch (Exception e) {
            LoggerUtil.exception(TAG, e, true);
        }
    }

    public static GoogleIdToken verify(String token) {
        try {
            if (googleIdTokenVerifier != null)
                return googleIdTokenVerifier.verify(token);
        } catch (Exception e) {
            LoggerUtil.exception(TAG, e, true);
        }
        return null;
    }
}
