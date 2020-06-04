package com.gfs.domain.model.jwt;

import lombok.Data;

@Data
public class JwtPayload {
    private String id;
    private String token;
    private int expires_in;

    public JwtPayload() {
    }

    public JwtPayload(String id, String token, int expires_in) {
        this.id = id;
        this.token = token;
        this.expires_in = expires_in;
    }
}
