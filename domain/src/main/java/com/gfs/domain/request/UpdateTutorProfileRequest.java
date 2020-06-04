package com.gfs.domain.request;

import com.gfs.domain.model.TutorBank;
import com.gfs.domain.model.WorkExperience;
import lombok.Data;

import java.util.List;

@Data
public class UpdateTutorProfileRequest extends UpdateAccountProfileRequest {
    private String short_description;
    private String certificate;
    private String experience;
    private List<String> specializes;
    private TutorBank bank;
    private WorkExperience current_school;
}
