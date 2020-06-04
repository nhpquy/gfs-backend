package com.gfs.admin.service.inf;

import com.gfs.domain.model.CurrentAdminLogin;
import com.gfs.domain.request.AdminApproveTutorRequest;
import com.gfs.domain.request.AdminDeactiveTutorRequest;
import com.gfs.domain.request.AdminListTutorRequest;
import com.gfs.domain.request.admin.BanTutorIdRequest;
import com.gfs.domain.response.AdminTutorAccountInfoResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface TutorManagementService {
    public List<AdminTutorAccountInfoResponse> listTutor(@Valid AdminListTutorRequest request);

    public AdminTutorAccountInfoResponse getTutorDetail(@NotNull String tutorId);

    public AdminTutorAccountInfoResponse activeTutor(CurrentAdminLogin currentAdminLogin, @Valid AdminApproveTutorRequest request);

    public AdminTutorAccountInfoResponse deactiveTutor(CurrentAdminLogin currentAdminLogin, @Valid AdminDeactiveTutorRequest request);

    public AdminTutorAccountInfoResponse banAccount(CurrentAdminLogin currentAdminLogin, @Valid BanTutorIdRequest request);
}
