package com.gfs.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginWithThirdPartyRequest extends LoginRequest {
    @NotNull(message = "'token' must not be null")
    @NotEmpty(message = "'token' must not be empty")
    private String token;
}
