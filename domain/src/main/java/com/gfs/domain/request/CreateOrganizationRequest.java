package com.gfs.domain.request;

import com.gfs.domain.annotations.StringNotEmpty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CreateOrganizationRequest {
    @NotNull(message = "'name' must not be null")
    @StringNotEmpty(message = "'name' must not be empty")
    private String name;
    private String description;
    private String contact;
    @NotNull(message = "'logo' must not be null")
    @StringNotEmpty(message = "'logo' must not be empty")
    private String logo_url;
    private String banner_url;
    @Pattern(regexp = "\\w+", message = "invalid domain")
    @Size(max = 40, message = "domain size must lower than 40")
    private String sub_domain;
}
