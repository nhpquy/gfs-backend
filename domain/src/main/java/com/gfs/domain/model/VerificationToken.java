package com.gfs.domain.model;

import com.gfs.domain.enums.VerificationTokenType;
import lombok.Data;

@Data
public class VerificationToken {
    private String account_id;
    private VerificationTokenType type;
    private String token;
    private String pending_phone_number;
    private String code;
    private long created_at = System.currentTimeMillis();
    private long valid_second;

    public boolean isValid() {
        return created_at + valid_second * 1000 > System.currentTimeMillis();
    }

    public boolean isValid(String code) {
        return this.code.equals(code);
    }
}
