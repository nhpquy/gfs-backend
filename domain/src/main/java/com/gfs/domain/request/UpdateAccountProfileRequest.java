package com.gfs.domain.request;

import com.gfs.domain.annotations.PhoneNumber;
import com.gfs.domain.enums.Gender;
import lombok.Data;

@Data
public class UpdateAccountProfileRequest {
    @PhoneNumber
    private String phone_number;
    private String name;
    private Gender gender;
    private String address;
    private String avatar;
    private String cover;
    private String date_of_birth;
}
