package com.gfs.domain.component;

import com.gfs.domain.constant.RedisConstant;
import com.gfs.domain.handler.RedisKeyExpiredHandlerCallback;
import com.gfs.domain.utils.LoggerUtil;
import com.gfs.domain.utils.StringUtils;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class RedisKeyExpireHandler extends MessageListenerAdapter {
    private static final String TAG = RedisKeyExpireHandler.class.getName();

    private static HashMap<String, List<RedisKeyExpiredHandlerCallback>> keyExpiredHandlerCallbackHashMap = new HashMap<>();

    public synchronized static void registerCallback(String event, RedisKeyExpiredHandlerCallback callback) {
        if (StringUtils.hasText(event) && callback != null) {
            List<RedisKeyExpiredHandlerCallback> callbackList = keyExpiredHandlerCallbackHashMap.get(event);
            if (callbackList == null)
                callbackList = new ArrayList<>();
            callbackList.add(callback);
            keyExpiredHandlerCallbackHashMap.put(event, callbackList);
        }
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = new String(message.getBody());
        String topic = new String(pattern);
        if (RedisConstant.TOPIC_KEY_EXPIRED.equals(topic))
            handleKeyExpired(key);
    }

    private void handleKeyExpired(String key) {
        int index = key.indexOf(":");
        if (index > 0) {
            String prefix = key.substring(0, index);
            String value = key.substring(index + 1);
            List<RedisKeyExpiredHandlerCallback> callbackList = keyExpiredHandlerCallbackHashMap.get(prefix);
            if (callbackList != null) {
                for (RedisKeyExpiredHandlerCallback callback : callbackList) {
                    try {
                        callback.onKeyExpired(value);
                    } catch (Exception e) {
                        LoggerUtil.e(this, e.getMessage());
                    }
                }
            }
        }
    }
}
