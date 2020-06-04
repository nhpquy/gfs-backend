package com.gfs.domain.request;

import com.gfs.domain.model.WorkExperience;
import lombok.Data;

@Data
public class UpdateStudentProfileRequest extends UpdateAccountProfileRequest {
    private WorkExperience current_school;
    private WorkExperience current_job;
}
