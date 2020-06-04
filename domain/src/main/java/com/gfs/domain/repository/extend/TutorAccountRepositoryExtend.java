package com.gfs.domain.repository.extend;

import com.gfs.domain.document.TutorAccount;
import com.gfs.domain.request.AdminListTutorRequest;
import com.gfs.domain.request.PhoneNumberRequest;

import java.util.List;
import java.util.Map;

public interface TutorAccountRepositoryExtend {
    public TutorAccount updateAccountDetail(String accountId, Map<String, Object> updateValues);

    public List<TutorAccount> listTutor(AdminListTutorRequest request);

    public List<TutorAccount> findByPhoneNumberRegex(PhoneNumberRequest request);
}
