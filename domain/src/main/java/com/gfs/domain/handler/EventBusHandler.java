package com.gfs.domain.handler;

import com.google.common.eventbus.AsyncEventBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventBusHandler {
    private static final ExecutorService executor = Executors.newCachedThreadPool();
    private static AsyncEventBus asyncEventBus = new AsyncEventBus(executor);

    public static void register(Object object) {
        asyncEventBus.register(object);
    }

    public static void unregister(Object object) {
        asyncEventBus.unregister(object);
    }

    public static void post(Object object) {
        asyncEventBus.post(object);
    }
}
