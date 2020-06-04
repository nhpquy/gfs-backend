package com.gfs.domain.response;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.StudentAccount;
import com.gfs.domain.model.WorkExperience;
import lombok.Data;

@Data
public class StudentAccountInfoResponse extends AccountInfoResponse {

    private WorkExperience current_school;
    private WorkExperience current_job;

    public StudentAccountInfoResponse() {
    }

    public StudentAccountInfoResponse(Account account) {
        super(account);
    }

    public StudentAccountInfoResponse(StudentAccount account) {
        super(account);
        this.current_school = account.getCurrent_school();
        this.current_job = account.getCurrent_job();
    }
}
