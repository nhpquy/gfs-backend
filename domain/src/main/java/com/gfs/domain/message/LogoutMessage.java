package com.gfs.domain.message;

import lombok.Data;

@Data
public class LogoutMessage {
    private String access_token;

    public LogoutMessage() {
    }

    public LogoutMessage(String access_token) {
        this.access_token = access_token;
    }
}
