package com.gfs.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateTutorSchoolRequest {
    @NotEmpty(message = "'school_id' must not be null")
    @NotNull(message = "'school_id' must not be empty")
    private String school_id;
    private String school_name;
    private String address;
}
