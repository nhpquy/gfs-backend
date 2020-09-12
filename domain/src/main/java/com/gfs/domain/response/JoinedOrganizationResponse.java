package com.gfs.domain.response;

import com.gfs.domain.document.JoinedOrganization;
import com.gfs.domain.enums.OrgInvitationStatus;
import com.gfs.domain.enums.OrgRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JoinedOrganizationResponse extends DocumentResponse {
    private String account_id;
    private String org_id;
    private OrgInvitationStatus status;
    private OrgRole role;

    public JoinedOrganizationResponse(JoinedOrganization invitation) {
        super(invitation);
        this.account_id = invitation.getAccount_id();
        this.org_id = invitation.getOrg_id();
        this.status = invitation.getStatus();
        this.role = invitation.getRole();
    }
}
