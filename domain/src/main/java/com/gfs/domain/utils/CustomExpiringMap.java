package com.gfs.domain.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import java.util.concurrent.TimeUnit;

public class CustomExpiringMap<K, V> {
    private static final long DEFAULT_MAX_SIZE = 1000;
    private static final long DEFAULT_EXPIRE_AFTER = 60;
    private static final TimeUnit DEFAULT_EXPIRE_TIME_UNIT = TimeUnit.SECONDS;

    private Cache<K, V> cache;

    public CustomExpiringMap() {
        this(DEFAULT_MAX_SIZE, DEFAULT_EXPIRE_AFTER, DEFAULT_EXPIRE_TIME_UNIT);
    }

    public CustomExpiringMap(long maxSize, long expireAfter, TimeUnit timeUnit) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .expireAfterWrite(expireAfter, timeUnit)
                .build();
    }

    public V get(K key) throws Exception {
        try {
            return cache.get(key, () -> null);
        } catch (CacheLoader.InvalidCacheLoadException e) {
            throw new CustomExpiringMapException("Key does not exists");
        }
    }

    public V get(K key, V defaultValue) {
        try {
            return cache.get(key, () -> defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public void remove(K key) {
        cache.invalidate(key);
    }

    public static final class CustomExpiringMapException extends RuntimeException {
        public CustomExpiringMapException(String message) {
            super(message);
        }
    }
}
