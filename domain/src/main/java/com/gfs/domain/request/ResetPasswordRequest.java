package com.gfs.domain.request;

import com.gfs.domain.annotations.Password;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ResetPasswordRequest {
    @NotNull(message = "'password' must not be null")
    @Password
    private String password;
}
