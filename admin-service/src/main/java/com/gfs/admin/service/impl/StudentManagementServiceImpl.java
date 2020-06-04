package com.gfs.admin.service.impl;

import com.gfs.admin.service.inf.AdminActivityService;
import com.gfs.admin.service.inf.StudentManagementService;
import com.gfs.domain.model.aggregate.AdminStudentAggResult;
import com.gfs.domain.repository.inf.AccountAuthorizedTokenRepository;
import com.gfs.domain.repository.inf.StudentAccountRepository;
import com.gfs.domain.request.AdminListStudentRequest;
import com.gfs.domain.response.AdminStudentAccountInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentManagementServiceImpl implements StudentManagementService {

    @Autowired
    StudentAccountRepository studentAccountRepository;

    @Autowired
    AdminActivityService adminActivityService;

    @Autowired
    AccountAuthorizedTokenRepository accountAuthorizedTokenRepository;

    @Override
    public List<AdminStudentAccountInfoResponse> listStudent(AdminListStudentRequest request) {
        List<AdminStudentAggResult> studentAccounts = studentAccountRepository.listStudent(request);
        return studentAccounts
                .stream().map(AdminStudentAccountInfoResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public AdminStudentAccountInfoResponse getStudentDetail(String studentId) {
        return null;
    }
}
