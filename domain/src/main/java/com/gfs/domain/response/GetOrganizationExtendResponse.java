package com.gfs.domain.response;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.JoinedOrganization;
import com.gfs.domain.document.Organization;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetOrganizationExtendResponse extends GetOrganizationResponse {
    private PoorAccountInfoResponse owner_detail;
    private JoinedOrganizationResponse joined_detail;

    public GetOrganizationExtendResponse(Organization organization, Account owner, JoinedOrganization joinedOrg) {
        super(organization);
        this.setOwner_detail(new PoorAccountInfoResponse(owner));
        this.setJoined_detail(new JoinedOrganizationResponse(joinedOrg));
    }

    public GetOrganizationExtendResponse(Organization organization, Account owner) {
        super(organization);
        this.setOwner_detail(new PoorAccountInfoResponse(owner));
    }
}
