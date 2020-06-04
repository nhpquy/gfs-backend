package com.gfs.domain.request;

import com.gfs.domain.annotations.Password;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ChangePasswordRequest {
    @NotNull(message = "'current_password' must not be null")
    @NotEmpty(message = "'current_password' must not be empty")
    private String current_password;

    @NotNull(message = "'new_password' must not be null")
    @Password(message = "New password is invalid")
    private String new_password;

    private boolean delete_all_session;
}
