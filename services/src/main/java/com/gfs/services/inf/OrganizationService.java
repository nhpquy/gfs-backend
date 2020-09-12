package com.gfs.services.inf;

import com.gfs.domain.annotations.StringNotEmpty;
import com.gfs.domain.model.CurrentTutorLogin;
import com.gfs.domain.request.*;
import com.gfs.domain.response.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface OrganizationService {
    public CreateOrganizationResponse createOrganization(@Valid CreateOrganizationRequest request, CurrentTutorLogin currentAccountLogin);

    public boolean checkOrganizationCreate(@Valid CreateOrganizationRequest request, CurrentTutorLogin currentTutorLogin);

    public boolean checkSubDomain(@NotNull @StringNotEmpty String subDomain);

    public GetOrganizationExtendResponse getCurrentOrganizationDetail(CurrentTutorLogin currentAccountLogin);

    public GetOrganizationExtendResponse getOrganizationDetailByOwnerId(@NotNull @StringNotEmpty String ownerId);

    public GetOrganizationExtendResponse getOrganizationDetailByOrgId(@NotNull @StringNotEmpty String orgId);

    public GetOrganizationExtendResponse getOrganizationDetailBySubDomain(@NotNull @StringNotEmpty String subDomain);

    public GetOrganizationExtendResponse updateOrganization(UpdateOrganizationRequest request, CurrentTutorLogin currentAccountLogin);

    public JoinedOrganizationResponse invitePartnerToOrg(@Valid InviteToOrganizationRequest request, CurrentTutorLogin currentAccountLogin);

    public JoinedOrganizationResponse acceptInvitationToOrg(@NotNull @StringNotEmpty String orgId, CurrentTutorLogin currentAccountLogin);

    public JoinedOrganizationResponse denyInvitationToOrg(@NotNull @StringNotEmpty String orgId, CurrentTutorLogin currentAccountLogin);

    public JoinedOrganizationResponse kickPartnerOutOfOrg(@Valid KickPartnerRequest request, CurrentTutorLogin currentAccountLogin);

    public List<JoinedOrganizationExtendResponse> getOrgInvitationPaging(GetOrgInvitationPagingRequest request, CurrentTutorLogin currentAccountLogin);

    public List<JoinedOrganizationExtendResponse> getSentPartnerInvitationPaging(GetPartnerInvitationPagingRequest request, CurrentTutorLogin currentAccountLogin);

    public JoinedOrganizationResponse updateInvitationToOrg(@Valid InviteToOrganizationRequest request, CurrentTutorLogin currentAccountLogin);

    public List<PoorOrganizationInfoResponse> listOrganizationPaging(ListOrgPagingRequest request);
}
