package com.gfs.domain.handler;

import org.apache.http.concurrent.FutureCallback;

public interface AsyncCallbackHandler<T> extends FutureCallback<T> {
    void call(T result, Throwable exception);
}
