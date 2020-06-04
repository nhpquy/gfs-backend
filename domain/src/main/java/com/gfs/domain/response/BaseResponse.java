package com.gfs.domain.response;

import lombok.Data;

@Data
public class BaseResponse {
    private long system_time;

    public BaseResponse() {
        system_time = System.currentTimeMillis();
    }
}
