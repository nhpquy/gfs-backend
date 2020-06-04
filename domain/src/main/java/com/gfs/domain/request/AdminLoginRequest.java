package com.gfs.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AdminLoginRequest extends LoginRequest {
    @NotEmpty(message = "'username' must not be null")
    @NotNull(message = "'username' must not be empty")
    private String username;
    @NotEmpty(message = "'password' must not be null")
    @NotNull(message = "'password' must not be empty")
    private String password;
}
