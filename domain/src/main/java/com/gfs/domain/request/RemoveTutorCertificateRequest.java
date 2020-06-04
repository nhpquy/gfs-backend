package com.gfs.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RemoveTutorCertificateRequest {
    @NotNull(message = "'certificate_id' must not be null")
    @NotEmpty(message = "'certificate_id' must not be empty")
    private String certificate_id;
}
