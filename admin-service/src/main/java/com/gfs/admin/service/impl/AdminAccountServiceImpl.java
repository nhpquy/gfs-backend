package com.gfs.admin.service.impl;

import com.gfs.admin.service.inf.AdminAccountService;
import com.gfs.domain.component.ScryptKdfPasswordEncoder;
import com.gfs.domain.document.AdminAccount;
import com.gfs.domain.enums.AccountStatus;
import com.gfs.domain.repository.inf.AdminAccountRepository;
import com.gfs.domain.request.CreateAdminAccountRequest;
import com.gfs.domain.response.AdminAccountInfoResponse;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.domain.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminAccountServiceImpl implements AdminAccountService {

    @Autowired
    AdminAccountRepository adminAccountRepository;

    @Autowired
    ScryptKdfPasswordEncoder scryptKdfPasswordEncoder;

    @Override
    public AdminAccountInfoResponse createAdminAccount(CreateAdminAccountRequest request) {
        AdminAccount adminAccount = adminAccountRepository.findByUsername(request.getUsername());
        if (adminAccount != null)
            throw ServiceExceptionUtils.accountAlreadyExists();

        adminAccount = new AdminAccount();
        adminAccount.setAccount_id(UUID.randomUUID().toString());
        adminAccount.setAccount_status(AccountStatus.activated);
        adminAccount.setEmail(StringUtils.handleEmailOrPhoneNumber(request.getEmail()));
        adminAccount.setName(request.getName());
        adminAccount.setPassword(scryptKdfPasswordEncoder.encode(request.getPassword()));
        adminAccount.setRole(request.getRole());
        adminAccount.setUsername(request.getUsername());
        adminAccount = adminAccountRepository.save(adminAccount);
        return new AdminAccountInfoResponse(adminAccount);
    }
}
