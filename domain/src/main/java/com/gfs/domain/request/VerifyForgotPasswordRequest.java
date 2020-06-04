package com.gfs.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class VerifyForgotPasswordRequest {
    @NotNull(message = "'code' must be not null")
    @NotEmpty(message = "'code' must be not empty")
    private String code;
}
