package com.gfs.domain.response;

import com.gfs.domain.document.Organization;
import com.gfs.domain.enums.OrganizationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateOrganizationResponse extends DocumentResponse {
    private String org_id;
    private String name;
    private String description;
    private String owner_id;
    private OrganizationStatus status;
    private String banner_url;
    private String logo_url;
    private String contact;
    private String sub_domain;

    public CreateOrganizationResponse(Organization organization) {
        super(organization);
        this.setOrg_id(organization.getOrg_id());
        this.setName(organization.getName());
        this.setDescription(organization.getDescription());
        this.setOwner_id(organization.getOwner_id());
        this.setStatus(organization.getStatus());
        this.setBanner_url(organization.getBanner_url());
        this.setLogo_url(organization.getLogo_url());
        this.setContact(organization.getContact());
        this.setSub_domain(organization.getSub_domain());
    }
}
