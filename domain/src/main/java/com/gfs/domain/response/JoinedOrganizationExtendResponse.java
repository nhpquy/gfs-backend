package com.gfs.domain.response;

import com.gfs.domain.model.aggregate.JoinedOrganizationAggResult;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JoinedOrganizationExtendResponse extends JoinedOrganizationResponse {
    private GetOrganizationResponse org_detail;
    private PoorAccountInfoResponse partner_detail;
    private PoorAccountInfoResponse owner_detail;

    public JoinedOrganizationExtendResponse(JoinedOrganizationAggResult aggResult) {
        super(aggResult);
        if (aggResult.getOrganization() != null)
            this.setOrg_detail(new GetOrganizationResponse(aggResult.getOrganization()));
        if (aggResult.getPartner() != null)
            this.setPartner_detail(new PoorAccountInfoResponse(aggResult.getPartner()));
        if (aggResult.getOwner() != null)
            this.setOwner_detail(new PoorAccountInfoResponse(aggResult.getOwner()));
    }
}
