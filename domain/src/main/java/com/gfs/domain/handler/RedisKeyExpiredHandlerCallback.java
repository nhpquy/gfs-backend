package com.gfs.domain.handler;

public interface RedisKeyExpiredHandlerCallback {
    public void onKeyExpired(String key);
}
