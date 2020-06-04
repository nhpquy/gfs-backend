package com.gfs.domain.repository.extend;

import com.gfs.domain.document.StudentAccount;
import com.gfs.domain.model.aggregate.AdminStudentAggResult;
import com.gfs.domain.request.AdminListStudentRequest;
import com.gfs.domain.request.PhoneNumberRequest;

import java.util.List;
import java.util.Map;

public interface StudentAccountRepositoryExtend {
    public StudentAccount updateAccountDetail(String accountId, Map<String, Object> updateValues);

    public List<StudentAccount> findByPhoneNumberRegex(PhoneNumberRequest request);

    public List<AdminStudentAggResult> listStudent(AdminListStudentRequest request);
}
