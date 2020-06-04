package com.gfs.domain.request;

import com.gfs.domain.annotations.Email;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ForgotPasswordRequest {

    @NotNull(message = "'email' must not be null")
    @Email
    private String email;
}
