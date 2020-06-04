package com.gfs.domain.request;

import com.gfs.domain.annotations.Email;
import com.gfs.domain.annotations.Password;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegisterAccountRequest extends LoginRequest {

    @Email
    @NotNull(message = "'email' must not be null")
    private String email;

    @NotNull(message = "'name' must not be null")
    @NotEmpty(message = "'name' must not be empty")
    private String name;

    @NotNull(message = "'password' must not be null")
    @Password
    private String password;
}
