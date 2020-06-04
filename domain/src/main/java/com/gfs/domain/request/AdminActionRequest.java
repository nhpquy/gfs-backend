package com.gfs.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AdminActionRequest {
    @NotNull(message = "'note' must not be null")
    @NotEmpty(message = "'note' must not be empty")
    private String note;
}
