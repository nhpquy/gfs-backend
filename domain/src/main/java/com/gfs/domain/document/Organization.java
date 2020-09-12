package com.gfs.domain.document;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.enums.OrganizationStatus;
import com.gfs.domain.request.CreateOrganizationRequest;
import com.gfs.domain.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = CollectionName.TUTOR_ORGS)
public class Organization extends ObjectIdDocument {
    @Indexed(unique = true)
    private String org_id;
    private String name;
    private String full_name;
    private String org_size;
    private String address;
    private String org_email;
    private String description;
    private String logo_url;
    private String banner_url;
    private String sub_domain;
    private String contact;
    @Indexed(unique = true)
    private String owner_id; // link to ownerId
    private OrganizationStatus status;
    private String rejected_note;
    private String approved_sub_domain;
    private String org_website;
    /**
     * Blockchain Info
     */
    private int bwf_wallet_count;

    public Organization(CreateOrganizationRequest request, String ownerId) {
        super();
        this.setOrg_id(UUID.randomUUID().toString());
        this.setName(request.getName());
        this.setDescription(request.getDescription());
        this.setOwner_id(ownerId);
        this.setStatus(OrganizationStatus.pending);
        this.setBwf_wallet_count(0);
        if (StringUtils.hasText(request.getLogo_url()))
            this.setLogo_url(request.getLogo_url());
        if (StringUtils.hasText(request.getBanner_url()))
            this.setBanner_url(request.getBanner_url());
        if (StringUtils.hasText(request.getSub_domain()))
            this.setSub_domain(request.getSub_domain().toLowerCase());
        if (StringUtils.hasText(request.getContact()))
            this.setContact(request.getContact());
    }

    public static Organization defaultOwnOrg(String ownerId) {
        Organization organization = new Organization();
        organization.setOrg_id(UUID.randomUUID().toString());
        organization.setOwner_id(ownerId);
        organization.setName("noNameOrganization");
        organization.setStatus(OrganizationStatus.pending);
        organization.setBwf_wallet_count(0);
        return organization;
    }
}
