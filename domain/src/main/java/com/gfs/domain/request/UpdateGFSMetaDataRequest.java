package com.gfs.domain.request;

import com.gfs.domain.enums.GFSFileStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateGFSMetaDataRequest {
    @NotNull(message = "'hash_code' must not be null")
    @NotEmpty(message = "'hash_code' must not be empty")
    private String hash_code;
    private String file_name;
    private String description;
    private GFSFileStatus status;
}
