package com.gfs.domain.response;

import com.gfs.domain.document.BeowulfWallet;
import com.gfs.domain.document.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoorOrganizationInfoResponse extends DocumentResponse {
    private String org_id;
    private String name;
    private String full_name;
    private String address;
    private String org_email;
    private String logo_url;
    private String banner_url;
    private String contact;
    private String org_website;

    private PoorBeowulfWalletInfoResponse wallet;

    public PoorOrganizationInfoResponse(Organization organization) {
        super(organization);
        this.setOrg_id(organization.getOrg_id());
        this.setName(organization.getName());
        this.setFull_name(organization.getFull_name());
        this.setAddress(organization.getAddress());
        this.setOrg_email(organization.getOrg_email());
        this.setLogo_url(organization.getLogo_url());
        this.setBanner_url(organization.getBanner_url());
        this.setOrg_website(organization.getOrg_website());
        this.setContact(organization.getContact());
    }

    public PoorOrganizationInfoResponse(Organization organization, BeowulfWallet wallet) {
        super(organization);
        this.setOrg_id(organization.getOrg_id());
        this.setName(organization.getName());
        this.setFull_name(organization.getFull_name());
        this.setAddress(organization.getAddress());
        this.setOrg_email(organization.getOrg_email());
        this.setLogo_url(organization.getLogo_url());
        this.setBanner_url(organization.getBanner_url());
        this.setOrg_website(organization.getOrg_website());
        this.setContact(organization.getContact());
        this.setWallet(new PoorBeowulfWalletInfoResponse(wallet));
    }
}
