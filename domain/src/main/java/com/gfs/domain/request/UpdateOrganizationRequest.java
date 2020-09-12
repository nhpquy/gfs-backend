package com.gfs.domain.request;

import lombok.Data;

@Data
public class UpdateOrganizationRequest {
    private String name;
    private String full_name;
    private String org_size;
    private String address;
    private String org_email;
    private String description;
    private String banner_url;
    private String logo_url;
    private String sub_domain;
    private String contact;
    private String org_website;
}
