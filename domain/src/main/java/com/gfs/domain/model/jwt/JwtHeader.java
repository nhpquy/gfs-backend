package com.gfs.domain.model.jwt;

import com.gfs.domain.enums.JwtAlgorithm;
import lombok.Data;

@Data
public class JwtHeader {
    private JwtAlgorithm algorithm;

    public JwtHeader() {
    }

    public JwtHeader(JwtAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
}
