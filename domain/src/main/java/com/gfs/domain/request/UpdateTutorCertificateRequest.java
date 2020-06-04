package com.gfs.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateTutorCertificateRequest {
    @NotNull(message = "'certificate_id' must not be null")
    @NotEmpty(message = "'certificate_id' must not be empty")
    private String certificate_id;
    private String name;
    private String main_photo;
    private String additional_photo;
}
