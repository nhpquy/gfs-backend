package com.gfs.domain.utils;

import com.gfs.domain.component.RedisClient;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.GeoRadiusParam;

import java.util.List;

public class RedisUtils {
    private static final String TAG = RedisUtils.class.getName();
    public static final int EXPIRE_SECOND_ONE_DAY = 86400;
    public static final int EXPIRE_SECOND_30_DAY = 86400 * 30;

    public static boolean setWithExpire(String key, String value, int seconds) {
        Jedis jedis = RedisClient.getClient();
        try {
            jedis.setex(key, seconds, value);
            return true;
        } catch (NullPointerException e) {
            LoggerUtil.e(TAG, "Redis error-setWithExpire: " + e.getMessage());
            LoggerUtil.e(TAG, "Set jedis before using");
        } catch (Exception e) {
            LoggerUtil.e(TAG, "Redis error-setWithExpire: " + e.getMessage());
        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
        return false;
    }

    public static boolean set(String key, String value) {
        Jedis jedis = RedisClient.getClient();
        try {
            jedis.set(key, value);
            return true;
        } catch (NullPointerException e) {
            LoggerUtil.e(TAG, "Redis error-setWithExpire: " + e.getMessage());
            LoggerUtil.e(TAG, "Set jedis before using");
        } catch (Exception e) {
            LoggerUtil.e(TAG, "Redis error-set: " + e.getMessage());

        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
        return false;
    }

    public static String get(String key) {
        Jedis jedis = RedisClient.getClient();
        try {
            return jedis.get(key);
        } catch (NullPointerException e) {
            LoggerUtil.e(TAG, "Redis error-setWithExpire: " + e.getMessage());
            LoggerUtil.e(TAG, "Set jedis before using");
        } catch (Exception e) {
            LoggerUtil.e(TAG, "Redis error-get: " + e.getMessage());

        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
        return null;
    }

    public static void delete(String key) {
        Jedis jedis = RedisClient.getClient();
        try {
            jedis.del(key);
        } catch (NullPointerException e) {
            LoggerUtil.e(TAG, "Redis error-setWithExpire: " + e.getMessage());
            LoggerUtil.e(TAG, "Set jedis before using");
        } catch (Exception e) {
            LoggerUtil.e(TAG, "Redis error-delete: " + e.getMessage());
        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
    }

    public static boolean hset(String key, String field, String value) {
        Jedis jedis = RedisClient.getClient();
        try {
            jedis.hset(key, field, value);
            return true;
        } catch (NullPointerException e) {
            LoggerUtil.e(TAG, "Redis error-setWithExpire: " + e.getMessage());
            LoggerUtil.e(TAG, "Set jedis before using");
        } catch (Exception e) {
            LoggerUtil.e(TAG, "Redis error-hset: " + e.getMessage());
        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
        return false;
    }

    public static String hget(String key, String field) {
        Jedis jedis = RedisClient.getClient();
        try {
            return jedis.hget(key, field);
        } catch (NullPointerException e) {
            LoggerUtil.e(TAG, "Redis error-setWithExpire: " + e.getMessage());
            LoggerUtil.e(TAG, "Set jedis before using");
        } catch (Exception e) {
            LoggerUtil.e(TAG, "Redis error-hget: " + e.getMessage());
        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
        return null;
    }

    public static long increase(String key) {
        Jedis jedis = RedisClient.getClient();
        try {
            return jedis.incr(key);
        } catch (NullPointerException e) {
            LoggerUtil.e(TAG, "Redis error-setWithExpire: " + e.getMessage());
            LoggerUtil.e(TAG, "Set jedis before using");
        } catch (Exception e) {
            LoggerUtil.e(TAG, "Redis error-increase: " + e.getMessage());
        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
        return -1;
    }

    public static long increaseBy(String key, long value) {
        Jedis jedis = RedisClient.getClient();
        try {
            return jedis.incrBy(key, value);
        } catch (NullPointerException e) {
            LoggerUtil.e(TAG, "Redis error-setWithExpire: " + e.getMessage());
            LoggerUtil.e(TAG, "Set jedis before using");
        } catch (Exception e) {
            LoggerUtil.e(TAG, "Redis error-increaseBy: " + e.getMessage());
        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
        return -1;
    }

    public static long hincreaseBy(String key, String field, long value) {
        Jedis jedis = RedisClient.getClient();
        try {
            return jedis.hincrBy(key, field, value);
        } catch (NullPointerException e) {
            LoggerUtil.e(TAG, "Redis error-setWithExpire: " + e.getMessage());
            LoggerUtil.e(TAG, "Set jedis before using");
        } catch (Exception e) {
            LoggerUtil.e(TAG, "Redis error-hincreaseBy: " + e.getMessage());
        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
        return -1;
    }

    public static long expire(String key, int seconds) {
        Jedis jedis = RedisClient.getClient();
        try {
            return jedis.expire(key, seconds);
        } catch (NullPointerException e) {
            LoggerUtil.e(TAG, "Redis error-setWithExpire: " + e.getMessage());
            LoggerUtil.e(TAG, "Set jedis before using");
        } catch (Exception e) {
            LoggerUtil.e(TAG, "Redis error-expire: " + e.getMessage());
        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
        return -1;
    }

    public static boolean setIfNotExists(String key, String value) {
        Jedis jedis = RedisClient.getClient();
        try {
            jedis.setnx(key, value);
            return true;
        } catch (NullPointerException e) {
            LoggerUtil.e(TAG, "Redis error-setWithExpire: " + e.getMessage());
            LoggerUtil.e(TAG, "Set jedis before using");
        } catch (Exception e) {
            LoggerUtil.e(TAG, "Redis error-setIfNotExists: " + e.getMessage());
        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
        return false;
    }

    public static boolean lpush(String key, String value) {
        Jedis jedis = RedisClient.getClient();
        try {
            jedis.lpush(key, value);
            return true;
        } catch (NullPointerException e) {
            LoggerUtil.e(TAG, "Redis error-setWithExpire: " + e.getMessage());
            LoggerUtil.e(TAG, "Set jedis before using");
        } catch (Exception e) {
            LoggerUtil.e(TAG, "Redis error-lpush: " + e.getMessage());
        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
        return false;
    }

    public static boolean exists(String key) {
        Jedis jedis = RedisClient.getClient();
        try {
            return jedis.exists(key);
        } catch (NullPointerException e) {
            LoggerUtil.e(TAG, "Redis error-setWithExpire: " + e.getMessage());
            LoggerUtil.e(TAG, "Set jedis before using");
        } catch (Exception e) {
            LoggerUtil.e(TAG, "Redis error-exists: " + e.getMessage());
        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
        return false;
    }

    public static boolean updateLocation(String profile, String accountId, Double x, Double y) {
        Jedis jedis = RedisClient.getClient();
        try {
            jedis.geoadd(profile, y, x, accountId);
        } catch (Exception e) {
            LoggerUtil.exception(TAG, e, true);
        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
        return false;
    }

    public static List<GeoRadiusResponse> findNearBy(String profile, Double x, Double y, double radius, String radiusUnit) {
        Jedis jedis = RedisClient.getClient();
        try {
            return jedis.georadius(profile, y, x, radius, GeoUnit.valueOf(radiusUnit.toUpperCase()), GeoRadiusParam.geoRadiusParam()
                    .withCoord()
                    .withDist()
                    .sortAscending()
                    .count(20));
        } catch (Exception e) {
            LoggerUtil.exception(TAG, e, true);
        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
        return null;
    }

    public static void removeLocation(String profile, String accountId) {
        Jedis jedis = RedisClient.getClient();
        try {
            jedis.zrem(profile, accountId);
        } catch (Exception e) {
            LoggerUtil.exception(TAG, e, true);
        } finally {
            if (jedis != null && jedis.isConnected())
                jedis.close();
        }
    }
}
