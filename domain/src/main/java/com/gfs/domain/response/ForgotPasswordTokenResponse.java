package com.gfs.domain.response;

import lombok.Data;

@Data
public class ForgotPasswordTokenResponse extends BaseResponse {
    private String access_token;
    private String phone_number;
    private int retry_second;

    public ForgotPasswordTokenResponse() {
    }

    public ForgotPasswordTokenResponse(String access_token, String phone_number, int retry_second) {
        this.access_token = access_token;
        this.phone_number = phone_number;
        this.retry_second = retry_second;
    }
}
