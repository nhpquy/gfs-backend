package com.gfs.admin.service.inf;

import com.gfs.domain.request.CreateAdminAccountRequest;
import com.gfs.domain.response.AdminAccountInfoResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface AdminAccountService {
    public AdminAccountInfoResponse createAdminAccount(@Valid CreateAdminAccountRequest request);
}
