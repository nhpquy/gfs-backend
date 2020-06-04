package com.gfs.domain.request;

import com.gfs.domain.annotations.Email;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AccountLoginRequest extends LoginRequest {
    @Email
    @NotNull(message = "'email' must not be null")
    private String email;

    @NotNull(message = "'password' must not be null")
    @NotEmpty(message = "'password' must not be empty")
    private String password;
}