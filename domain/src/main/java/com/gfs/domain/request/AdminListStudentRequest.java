package com.gfs.domain.request;

import com.gfs.domain.annotations.Email;
import com.gfs.domain.annotations.PhoneNumber;
import com.gfs.domain.enums.AccountStatus;
import lombok.Data;

@Data
public class AdminListStudentRequest extends PagingRequest {

    private AccountStatus status;

    @PhoneNumber
    private String phone_number;

    @Email
    private String email;

    private long client_timestamp;
}
