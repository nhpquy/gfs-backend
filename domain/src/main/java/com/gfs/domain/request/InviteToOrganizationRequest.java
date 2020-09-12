package com.gfs.domain.request;

import com.gfs.domain.annotations.Email;
import com.gfs.domain.enums.OrgRole;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InviteToOrganizationRequest {
    @NotNull(message = "'email' must not be null")
    @Email
    private String email;
    private OrgRole role;
}
