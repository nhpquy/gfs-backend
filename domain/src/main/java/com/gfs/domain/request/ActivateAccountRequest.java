package com.gfs.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ActivateAccountRequest {
    @NotEmpty(message = "'code' must not be empty")
    @NotNull(message = "'code' must not be null")
    private String code;
}
