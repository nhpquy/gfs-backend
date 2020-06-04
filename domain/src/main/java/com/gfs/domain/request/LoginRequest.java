package com.gfs.domain.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String app_version;
    private String app_platform;
    private String user_agent;
}
