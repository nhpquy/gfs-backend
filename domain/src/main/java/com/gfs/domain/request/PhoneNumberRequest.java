package com.gfs.domain.request;

import com.gfs.domain.annotations.PhoneNumber;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PhoneNumberRequest extends PagingRequest {
    @PhoneNumber
    @NotNull(message = "'phone_number' must not be null")
    private String phone_number;
}
