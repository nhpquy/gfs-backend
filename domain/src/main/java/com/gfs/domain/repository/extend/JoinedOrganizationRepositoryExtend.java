package com.gfs.domain.repository.extend;


import com.gfs.domain.document.JoinedOrganization;
import com.gfs.domain.enums.OrgInvitationStatus;
import com.gfs.domain.model.aggregate.JoinedOrganizationAggResult;
import com.gfs.domain.request.GetOrgInvitationPagingRequest;
import com.gfs.domain.request.GetPartnerInvitationPagingRequest;

import java.util.List;
import java.util.Map;

public interface JoinedOrganizationRepositoryExtend {
    JoinedOrganization updateInvitation(JoinedOrganization invitation, OrgInvitationStatus status);

    JoinedOrganization updateDetail(JoinedOrganization invitation, Map<String, Object> updateValues);

    List<JoinedOrganizationAggResult> findByPartnerIdPaging(String accountId, GetOrgInvitationPagingRequest request, Map<String, Object> filter);

    List<JoinedOrganizationAggResult> findByOrgIdPaging(String orgId, GetPartnerInvitationPagingRequest request, Map<String, Object> filter);
}
