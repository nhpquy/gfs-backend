package com.gfs.domain.request;

import com.gfs.domain.annotations.Email;
import com.gfs.domain.annotations.Password;
import com.gfs.domain.enums.admin.AdminRole;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateAdminAccountRequest {
    @Email
    @NotNull(message = "'email' must not be null")
    private String email;
    @NotNull(message = "'username' must not be null")
    @NotEmpty(message = "'username' must not be empty")
    private String username;
    @NotNull(message = "'password' must not be null")
    @NotEmpty(message = "'password' must not be empty")
    @Password
    private String password;
    @NotNull(message = "'name' must not be null")
    @NotEmpty(message = "'name' must not be empty")
    private String name;
    @NotNull(message = "'role' must not be null")
    private AdminRole role;
}
