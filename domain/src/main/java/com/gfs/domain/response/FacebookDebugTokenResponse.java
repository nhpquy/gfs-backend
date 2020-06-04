package com.gfs.domain.response;

import com.gfs.domain.model.FacebookDebugTokenData;

public class FacebookDebugTokenResponse {
    private FacebookDebugTokenData data;

    public FacebookDebugTokenData getData() {
        return data;
    }

    public void setData(FacebookDebugTokenData data) {
        this.data = data;
    }
}
