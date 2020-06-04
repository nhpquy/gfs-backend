package com.gfs.domain.request.admin;

import com.gfs.domain.request.AdminActionRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class BanTutorIdRequest extends AdminActionRequest {
    @NotNull(message = "tutorId must not be null")
    @NotEmpty(message = "tutorId must not be empty")
    private String tutor_id;
}
