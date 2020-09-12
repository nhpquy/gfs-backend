package com.gfs.api.controller.tutor;

import com.gfs.api.annotation.ApiV1RestController;
import com.gfs.domain.constant.SwaggerTag;
import com.gfs.domain.enums.OrgRole;
import com.gfs.domain.enums.OrganizationStatus;
import com.gfs.domain.model.CurrentTutorLogin;
import com.gfs.domain.request.*;
import com.gfs.domain.response.*;
import com.gfs.services.annotation.TutorAccountAuthorized;
import com.gfs.services.inf.AccountAuthorizationService;
import com.gfs.services.inf.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Api(tags = SwaggerTag.organization)
@ApiV1RestController(value = "tutor/org")
public class RestTutorOrganizationController {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    AccountAuthorizationService accountAuthorizationService;

    @ApiOperation(value = "Create new Organization", notes = "'limit' = 1")
    @PostMapping(value = "create")
    public CreateOrganizationResponse createOrganization(@TutorAccountAuthorized(orgRoles = {OrgRole.none})
                                                         @RequestHeader(name = "Authorization") CurrentTutorLogin currentTutorLogin,
                                                         @RequestBody CreateOrganizationRequest request) {
        return organizationService.createOrganization(request, currentTutorLogin);
    }

    @ApiOperation(value = "Switch organization context")
    @PostMapping(value = "switch")
    public AccountLoginResponse switchOrganizationContext(@TutorAccountAuthorized(orgRoles = {OrgRole.none})
                                                          @RequestHeader(name = "Authorization")
                                                                  CurrentTutorLogin currentTutorLogin,
                                                          @RequestParam("orgId") String orgId) throws Exception {
        return accountAuthorizationService.switchOrganizationContext(currentTutorLogin.getAuthorizedToken(), orgId);
    }

    @ApiOperation(value = "Check Organization Data")
    @PostMapping(value = "check")
    public boolean isOrganizationInfoValid(@TutorAccountAuthorized(orgRoles = {OrgRole.none})
                                           @RequestHeader(name = "Authorization") CurrentTutorLogin currentTutorLogin,
                                           @RequestBody CreateOrganizationRequest request) {
        return organizationService.checkOrganizationCreate(request, currentTutorLogin);
    }

    @ApiOperation(value = "Check SubDomain used or not?")
    @PostMapping(value = "subdomain/check")
    public boolean isSubdomainForOrgValid(@TutorAccountAuthorized(orgRoles = {OrgRole.none})
                                          @RequestHeader(name = "Authorization") CurrentTutorLogin currentTutorLogin,
                                          @RequestParam("subdomain") String subDomain) {
        return organizationService.checkSubDomain(subDomain);
    }

    @ApiOperation(value = "Get Current Organization Detail")
    @GetMapping(value = "details")
    public GetOrganizationExtendResponse getCurrentOrganizationDetail(@TutorAccountAuthorized(orgRoles = {OrgRole.none})
                                                                      @RequestHeader(name = "Authorization") CurrentTutorLogin currentTutorLogin) {
        return organizationService.getCurrentOrganizationDetail(currentTutorLogin);
    }


    @ApiOperation(value = "Update Organization profile", notes = "If update value is image's field - only receive 'url' property")
    @PostMapping(value = "update")
    public GetOrganizationExtendResponse updateOrganizationProfile(@TutorAccountAuthorized(orgRoles = {OrgRole.owner, OrgRole.admin})
                                                                   @RequestHeader(name = "Authorization") CurrentTutorLogin currentTutorLogin,
                                                                   @RequestBody UpdateOrganizationRequest request) {
        return organizationService.updateOrganization(request, currentTutorLogin);
    }

    @ApiOperation(value = "Invite participant to Organization")
    @PostMapping(value = "invite")
    public JoinedOrganizationResponse invitePartnerToOrg(@TutorAccountAuthorized(orgRoles = {OrgRole.editor})
                                                         @RequestHeader(name = "Authorization") CurrentTutorLogin currentTutorLogin,
                                                         @RequestBody InviteToOrganizationRequest request) {
        return organizationService.invitePartnerToOrg(request, currentTutorLogin);
    }

    @ApiOperation(value = "Update Invitation to Organization")
    @PostMapping(value = "/partner/update")
    public JoinedOrganizationResponse updateInvitationPartnerToOrg(@TutorAccountAuthorized(orgRoles = {OrgRole.editor})
                                                                   @RequestHeader(name = "Authorization") CurrentTutorLogin currentTutorLogin,
                                                                   @RequestBody InviteToOrganizationRequest request) {
        return organizationService.updateInvitationToOrg(request, currentTutorLogin);
    }

    @ApiOperation(value = "Accept invite to Organization")
    @PostMapping(value = "accept")
    public JoinedOrganizationResponse acceptInvitationToOrg(@TutorAccountAuthorized(orgRoles = {OrgRole.none})
                                                            @RequestHeader(name = "Authorization") CurrentTutorLogin currentTutorLogin,
                                                            @RequestParam("orgId") String orgId) {
        return organizationService.acceptInvitationToOrg(orgId, currentTutorLogin);
    }

    @ApiOperation(value = "Deny invite to Organization")
    @PostMapping(value = "deny")
    public JoinedOrganizationResponse denyInvitationToOrg(@TutorAccountAuthorized(orgRoles = {OrgRole.none})
                                                          @RequestHeader(name = "Authorization") CurrentTutorLogin currentTutorLogin,
                                                          @RequestParam("orgId") String orgId) {
        return organizationService.denyInvitationToOrg(orgId, currentTutorLogin);
    }

    @ApiOperation(value = "Kick partner out of Organization")
    @PostMapping(value = "kick")
    public JoinedOrganizationResponse kickPartnerOutOfOrg(@TutorAccountAuthorized(orgRoles = {OrgRole.editor, OrgRole.owner})
                                                          @RequestHeader(name = "Authorization") CurrentTutorLogin currentTutorLogin,
                                                          @RequestBody KickPartnerRequest request) {
        return organizationService.kickPartnerOutOfOrg(request, currentTutorLogin);
    }

    @ApiOperation(value = "Account get List Organization Invitations", notes = "This API endpoint is used for Basic account")
    @PostMapping(value = "list-orgs")
    public List<JoinedOrganizationExtendResponse> getOrgInvitationPaging(@TutorAccountAuthorized(orgRoles = {OrgRole.none})
                                                                         @RequestHeader(name = "Authorization") CurrentTutorLogin currentTutorLogin,
                                                                         @RequestBody GetOrgInvitationPagingRequest request) {
        return organizationService.getOrgInvitationPaging(request, currentTutorLogin);
    }

    @ApiOperation(value = "Owner get List sent partner Invitations to Organization", notes = "This API endpoint is used for Organization Owner")
    @PostMapping(value = "list-partners")
    public List<JoinedOrganizationExtendResponse> getSentPartnerInvitationPaging(@TutorAccountAuthorized(orgRoles = {OrgRole.none})
                                                                                 @RequestHeader(name = "Authorization") CurrentTutorLogin currentTutorLogin,
                                                                                 @RequestBody GetPartnerInvitationPagingRequest request) {
        return organizationService.getSentPartnerInvitationPaging(request, currentTutorLogin);
    }

    /**
     * Public APIs
     */
    @ApiOperation(value = "Get List Organizations")
    @PostMapping(value = "list")
    public List<PoorOrganizationInfoResponse> listOrganizationPaging(@RequestBody ListOrgPagingRequest request) {
        request.setStatuses(Collections.singletonList(OrganizationStatus.approved));
        return organizationService.listOrganizationPaging(request);
    }

    @ApiOperation(value = "Get Organization Detail by Owner Id")
    @GetMapping(value = "details/owner_id/{owner_id}")
    public GetOrganizationExtendResponse getOrganizationDetailByOwnerId(@PathVariable("owner_id") String ownerId) {
        return organizationService.getOrganizationDetailByOwnerId(ownerId);
    }

    @ApiOperation(value = "Get Organization Detail by Organization Id")
    @GetMapping(value = "details/org_id/{org_id}")
    public GetOrganizationExtendResponse getOrganizationDetailByOrgId(@PathVariable("org_id") String orgId) {
        return organizationService.getOrganizationDetailByOrgId(orgId);
    }

    @ApiOperation(value = "Get Organization Detail by Subdomain")
    @GetMapping(value = "details/subdomain/{subdomain}")
    public GetOrganizationExtendResponse getOrganizationDetailBySubDomain(@PathVariable("subdomain") String subDomain) {
        return organizationService.getOrganizationDetailBySubDomain(subDomain);
    }
}