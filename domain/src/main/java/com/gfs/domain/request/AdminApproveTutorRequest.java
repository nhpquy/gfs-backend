package com.gfs.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AdminApproveTutorRequest extends AdminActionRequest  {
    @NotNull(message = "'tutor_id' must not be null")
    @NotEmpty(message = "'tutor_id' must not be empty")
    private String tutor_id;
}
