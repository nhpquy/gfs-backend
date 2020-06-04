package com.gfs.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RemoveGFSMetaDataRequest {
    @NotNull(message = "'hash_code' must not be null")
    @NotEmpty(message = "'hash_code' must not be empty")
    private String hash_code;
    private boolean is_permanent;
}
