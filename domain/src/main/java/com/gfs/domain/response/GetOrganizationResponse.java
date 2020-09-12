package com.gfs.domain.response;

import com.gfs.domain.document.Organization;
import com.gfs.domain.enums.OrganizationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetOrganizationResponse extends DocumentResponse {
    private String org_id;
    private String name;
    private String full_name;
    private String org_size;
    private String address;
    private String org_email;
    private String description;
    private String owner_id;
    private OrganizationStatus status;
    private String logo_url;
    private String banner_url;
    private String sub_domain;
    private String approved_sub_domain;
    private String contact;
    private String rejected_note;
    private int bwf_wallet_count;
    private String org_website;

    public GetOrganizationResponse(Organization organization) {
        super(organization);
        this.setOrg_id(organization.getOrg_id());
        this.setName(organization.getName());
        this.setFull_name(organization.getFull_name());
        this.setOrg_size(organization.getOrg_size());
        this.setAddress(organization.getAddress());
        this.setOrg_email(organization.getOrg_email());
        this.setDescription(organization.getDescription());
        this.setOwner_id(organization.getOwner_id());
        this.setStatus(organization.getStatus());
        this.setBanner_url(organization.getBanner_url());
        this.setSub_domain(organization.getSub_domain());
        this.setLogo_url(organization.getLogo_url());
        this.setContact(organization.getContact());
        this.setRejected_note(organization.getRejected_note());
        this.setBwf_wallet_count(organization.getBwf_wallet_count());
        this.setApproved_sub_domain(organization.getApproved_sub_domain());
        this.setOrg_website(organization.getOrg_website());
    }
}
