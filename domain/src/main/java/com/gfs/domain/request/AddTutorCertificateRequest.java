package com.gfs.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddTutorCertificateRequest {
    @NotNull(message = "'name' must not be null")
    @NotEmpty(message = "'name' must not be empty")
    private String name;

    @NotNull(message = "'main_photo' must not be null")
    @NotEmpty(message = "'main_photo' must not be empty")
    private String main_photo;
    private String additional_photo;
}
