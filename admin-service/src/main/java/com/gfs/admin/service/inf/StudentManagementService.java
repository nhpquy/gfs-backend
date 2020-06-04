package com.gfs.admin.service.inf;

import com.gfs.domain.request.AdminListStudentRequest;
import com.gfs.domain.response.AdminStudentAccountInfoResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface StudentManagementService {
    public List<AdminStudentAccountInfoResponse> listStudent(@Valid AdminListStudentRequest request);

    public AdminStudentAccountInfoResponse getStudentDetail(@NotNull String studentId);
}
