package com.gfs.domain.document;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.enums.OrgInvitationStatus;
import com.gfs.domain.enums.OrgRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = CollectionName.JOINED_ORGS)
@CompoundIndex(def = "{'account_id': 1, 'org_id': 1}", unique = true)
public class JoinedOrganization extends ObjectIdDocument {
    @Indexed
    private String account_id;
    @Indexed
    private String org_id;
    private OrgInvitationStatus status;
    private OrgRole role;

    public JoinedOrganization(String account_id, String org_id) {
        super();
        this.setAccount_id(account_id);
        this.setOrg_id(org_id);
        this.setStatus(OrgInvitationStatus.pending);
    }

    public static JoinedOrganization defaultOwnOrg(String ownerId, String orgId) {
        JoinedOrganization ownOrg = new JoinedOrganization();
        ownOrg.setAccount_id(ownerId);
        ownOrg.setOrg_id(orgId);
        ownOrg.setRole(OrgRole.owner);
        ownOrg.setStatus(OrgInvitationStatus.accepted);
        return ownOrg;
    }
}
