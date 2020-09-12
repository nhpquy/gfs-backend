package com.gfs.services.impl;

import com.gfs.domain.document.BeowulfWallet;
import com.gfs.domain.document.JoinedOrganization;
import com.gfs.domain.document.Organization;
import com.gfs.domain.document.TutorAccount;
import com.gfs.domain.enums.OrgInvitationStatus;
import com.gfs.domain.model.CurrentTutorLogin;
import com.gfs.domain.model.aggregate.JoinedOrganizationAggResult;
import com.gfs.domain.repository.inf.BeowulfWalletRepository;
import com.gfs.domain.repository.inf.JoinedOrganizationRepository;
import com.gfs.domain.repository.inf.OrganizationRepository;
import com.gfs.domain.repository.inf.TutorAccountRepository;
import com.gfs.domain.request.*;
import com.gfs.domain.response.*;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.domain.utils.StringUtils;
import com.gfs.services.inf.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final boolean autoAcceptOrgInvitation = true;

    @Autowired
    TutorAccountRepository tutorAccountRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    JoinedOrganizationRepository joinedOrganizationRepository;

    @Autowired
    BeowulfWalletRepository beowulfWalletRepository;


    @Override
    public CreateOrganizationResponse createOrganization(CreateOrganizationRequest request, CurrentTutorLogin currentTutorLogin) {

        String ownerId = currentTutorLogin.getTutorAccount().getAccount_id();
        if (organizationRepository.findByOwner_id(ownerId) != null) {
            throw ServiceExceptionUtils.accountAlreadyOwnedOrg();
        }

        checkSubDomain(request.getSub_domain());

        // Create new school document
        Organization newOrg = new Organization(request, ownerId);
        newOrg = organizationRepository.save(newOrg);

        return new CreateOrganizationResponse(newOrg);
    }

    public boolean checkOrganizationCreate(CreateOrganizationRequest request, CurrentTutorLogin currentTutorLogin) {
        // Check if current tutor account had organization or not?
        String ownerId = currentTutorLogin.getTutorAccount().getAccount_id();
        if (organizationRepository.findByOwner_id(ownerId) != null) {
            throw ServiceExceptionUtils.accountAlreadyOwnedOrg();
        }

        checkSubDomain(request.getSub_domain());
        return true;
    }

    @Override
    public boolean checkSubDomain(String subDomain) {
        if (StringUtils.hasText(subDomain)) {
            if (organizationRepository.findByApproved_sub_domain(subDomain.toLowerCase()) != null) {
                throw ServiceExceptionUtils.subDomainHasBeenRegistered();
            }
        }
        return true;
    }

    @Override
    public GetOrganizationExtendResponse getCurrentOrganizationDetail(CurrentTutorLogin currentTutorLogin) {
        Organization organization = organizationRepository.findByOrg_id(currentTutorLogin.getJoinedOrg().getOrg_id());
        if (organization == null) {
            throw ServiceExceptionUtils.organizationNotFound();
        }
        TutorAccount owner;
        if (organization.getOwner_id().equals(currentTutorLogin.getTutorAccount().getAccount_id())) {
            owner = currentTutorLogin.getTutorAccount();
        } else {
            owner = ifBeValidTutor(organization.getOwner_id());
        }

        return new GetOrganizationExtendResponse(organization, owner, currentTutorLogin.getJoinedOrg());
    }

    @Override
    public GetOrganizationExtendResponse getOrganizationDetailByOwnerId(String ownerId) {
        Organization organization = organizationRepository.findByOwner_id(ownerId);
        if (organization == null) {
            throw ServiceExceptionUtils.organizationNotFound();
        }
        TutorAccount owner = ifBeValidTutor(organization.getOwner_id());

        return new GetOrganizationExtendResponse(organization, owner, null);
    }

    @Override
    public GetOrganizationExtendResponse getOrganizationDetailByOrgId(String orgId) {
        Organization organization = organizationRepository.findByOrg_id(orgId);
        if (organization == null) {
            throw ServiceExceptionUtils.organizationNotFound();
        }
        TutorAccount owner = ifBeValidTutor(organization.getOwner_id());

        return new GetOrganizationExtendResponse(organization, owner, null);
    }

    @Override
    public GetOrganizationExtendResponse getOrganizationDetailBySubDomain(String subDomain) {
        Organization organization = organizationRepository.findByApproved_sub_domain(subDomain);
        if (organization == null) {
            throw ServiceExceptionUtils.organizationNotFound();
        }
        TutorAccount owner = ifBeValidTutor(organization.getOwner_id());

        return new GetOrganizationExtendResponse(organization, owner, null);
    }

    @Override
    public GetOrganizationExtendResponse updateOrganization(UpdateOrganizationRequest request, CurrentTutorLogin currentTutorLogin) {
        Organization organization = organizationRepository.findByOrg_id(currentTutorLogin.getJoinedOrg().getOrg_id());

        Map<String, Object> updateValues = new HashMap<>();
        if (StringUtils.hasText(request.getName())) {
            updateValues.put("name", request.getName());
        }
        if (StringUtils.hasText(request.getFull_name())) {
            updateValues.put("full_name", request.getFull_name());
        }
        if (StringUtils.hasText(request.getOrg_size())) {
            updateValues.put("org_size", request.getOrg_size());
        }
        if (StringUtils.hasText(request.getAddress())) {
            updateValues.put("address", request.getAddress());
        }
        if (StringUtils.hasText(request.getOrg_email())) {
            updateValues.put("org_email", request.getOrg_email());
        }
        if (StringUtils.hasText(request.getDescription())) {
            updateValues.put("description", request.getDescription());
        }
        if (StringUtils.hasText(request.getBanner_url())) {
            updateValues.put("banner_url", request.getBanner_url());
        }
        if (StringUtils.hasText(request.getLogo_url())) {
            updateValues.put("logo_url", request.getLogo_url());
        }

        if (StringUtils.hasText(request.getSub_domain())) {
            if (!request.getSub_domain().equals(organization.getSub_domain())) {
                checkSubDomain(request.getSub_domain());
                updateValues.put("sub_domain", request.getSub_domain());
            }
        }

        if (StringUtils.hasText(request.getOrg_website())) {
            updateValues.put("org_website", request.getOrg_website());
        }

        if (StringUtils.hasText(request.getContact())) {
            updateValues.put("contact", request.getContact());
        }

        if (updateValues.size() <= 0)
            throw ServiceExceptionUtils.missingParam("Nothing to update");

        organization = organizationRepository.updateDetail(organization.getOrg_id(), updateValues);

        TutorAccount owner;
        if (organization.getOwner_id().equals(currentTutorLogin.getTutorAccount().getAccount_id())) {
            owner = currentTutorLogin.getTutorAccount();
        } else {
            owner = ifBeValidTutor(organization.getOwner_id());
        }

        return new GetOrganizationExtendResponse(organization, owner, currentTutorLogin.getJoinedOrg());
    }

    @Override
    public JoinedOrganizationResponse invitePartnerToOrg(InviteToOrganizationRequest request, CurrentTutorLogin currentTutorLogin) {
        Organization organization = organizationRepository.findByOrg_id(currentTutorLogin.getJoinedOrg().getOrg_id());

        TutorAccount partner = tutorAccountRepository.findByEmail(StringUtils.handleEmailOrPhoneNumber(request.getEmail()));
        if (partner == null)
            throw ServiceExceptionUtils.accountNotFound();

        JoinedOrganization invitation = joinedOrganizationRepository.findByOrg_idAndAccount_id(organization.getOrg_id(), partner.getAccount_id());
        // Check if account has been invited yet?
        if (invitation != null) {
            if (invitation.getStatus() == OrgInvitationStatus.denied || invitation.getStatus() == OrgInvitationStatus.kicked) {
                joinedOrganizationRepository.delete(invitation);
            } else {
                throw ServiceExceptionUtils.accountAlreadyInvited();
            }
        }

        // Create new invitation
        JoinedOrganization newInvitation = new JoinedOrganization(partner.getAccount_id(), organization.getOrg_id());
        newInvitation.setRole(request.getRole());

        if (autoAcceptOrgInvitation)
            newInvitation.setStatus(OrgInvitationStatus.accepted);

        invitation = joinedOrganizationRepository.save(newInvitation);

        return new JoinedOrganizationResponse(invitation);
    }

    @Override
    public JoinedOrganizationResponse acceptInvitationToOrg(String orgId, CurrentTutorLogin currentTutorLogin) {
        return handleOrgInvitation(orgId, currentTutorLogin.getTutorAccount(), OrgInvitationStatus.accepted);
    }

    @Override
    public JoinedOrganizationResponse denyInvitationToOrg(String orgId, CurrentTutorLogin currentTutorLogin) {
        return handleOrgInvitation(orgId, currentTutorLogin.getTutorAccount(), OrgInvitationStatus.denied);
    }

    private JoinedOrganizationResponse handleOrgInvitation(String orgId, TutorAccount partner, OrgInvitationStatus handleStatus) {
        String accountId = partner.getAccount_id();

        Organization organization = organizationRepository.findByOrg_id(orgId);
        if (organization == null) {
            throw ServiceExceptionUtils.organizationNotFound();
        }

        JoinedOrganization invitation = joinedOrganizationRepository.findByOrg_idAndAccount_id(orgId, accountId);
        // Check availability of invitation
        if (invitation == null) {
            throw ServiceExceptionUtils.accountNotInvited();
        }

        switch (invitation.getStatus()) {
            case accepted:
                throw ServiceExceptionUtils.accountAlreadyAccepted();
            case denied:
                throw ServiceExceptionUtils.accountAlreadyDenied();
            case kicked:
                throw ServiceExceptionUtils.accountAlreadyKicked();
        }

        // Update status for invitation
        invitation = joinedOrganizationRepository.updateInvitation(invitation, handleStatus);

        return new JoinedOrganizationResponse(invitation);
    }

    @Override
    public JoinedOrganizationResponse kickPartnerOutOfOrg(KickPartnerRequest request, CurrentTutorLogin currentTutorLogin) {
        String ownerId = currentTutorLogin.getTutorAccount().getAccount_id();

        Organization organization = organizationRepository.findByOrg_id(currentTutorLogin.getJoinedOrg().getOrg_id());

        if (ownerId.equals(request.getAccount_id())) {
            throw ServiceExceptionUtils.missingParam("Can not kick yourself");
        }

        TutorAccount account = ifBeValidTutor(request.getAccount_id());

        // Check if tutor has been invited yet
        JoinedOrganization invitation = joinedOrganizationRepository.findByOrg_idAndAccount_id(organization.getOrg_id(), account.getAccount_id());
        if (invitation == null) {
            throw ServiceExceptionUtils.accountNotInvited();
        }

        if (invitation.getStatus() == OrgInvitationStatus.denied) {
            throw ServiceExceptionUtils.accountAlreadyDenied();
        }

        if (invitation.getStatus() == OrgInvitationStatus.kicked) {
            throw ServiceExceptionUtils.accountAlreadyKicked();
        }

        // Update status for invitation
        invitation = joinedOrganizationRepository.updateInvitation(invitation, OrgInvitationStatus.kicked);
        return new JoinedOrganizationResponse(invitation);
    }

    @Override
    public List<JoinedOrganizationExtendResponse> getOrgInvitationPaging(GetOrgInvitationPagingRequest request, CurrentTutorLogin currentTutorLogin) {
        // Get org invitations paging
        Map<String, Object> filter = new HashMap<>();
        if (request.getInvite_status() != null) {
            filter.put("status", request.getInvite_status());
        }
        List<JoinedOrganizationAggResult> orgInvitations = joinedOrganizationRepository.findByPartnerIdPaging(currentTutorLogin.getTutorAccount().getAccount_id(), request, filter);
        return orgInvitations.stream().map(JoinedOrganizationExtendResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<JoinedOrganizationExtendResponse> getSentPartnerInvitationPaging(GetPartnerInvitationPagingRequest request, CurrentTutorLogin currentTutorLogin) {
        Organization organization = organizationRepository.findByOrg_id(currentTutorLogin.getJoinedOrg().getOrg_id());
        // Get sent partner invitations paging
        Map<String, Object> filter = new HashMap<>();
        if (request.getInvite_status() != null) {
            filter.put("status", request.getInvite_status());
        }
        List<JoinedOrganizationAggResult> partnerInvitations = joinedOrganizationRepository.findByOrgIdPaging(organization.getOrg_id(), request, filter);
        return partnerInvitations.stream().map(JoinedOrganizationExtendResponse::new).collect(Collectors.toList());
    }

    @Override
    public JoinedOrganizationResponse updateInvitationToOrg(InviteToOrganizationRequest request, CurrentTutorLogin currentTutorLogin) {
        Organization organization = organizationRepository.findByOrg_id(currentTutorLogin.getJoinedOrg().getOrg_id());

        TutorAccount partner = tutorAccountRepository.findByEmail(StringUtils.handleEmailOrPhoneNumber(request.getEmail()));
        if (partner == null)
            throw ServiceExceptionUtils.accountNotFound();

        JoinedOrganization invitation = joinedOrganizationRepository.findByOrg_idAndAccount_id(organization.getOrg_id(), partner.getAccount_id());
        // Check if account has been invited yet?
        if (invitation == null) {
            throw ServiceExceptionUtils.accountNotInvited();
        }

        switch (invitation.getStatus()) {
            case denied:
                throw ServiceExceptionUtils.accountAlreadyDenied();
            case kicked:
                throw ServiceExceptionUtils.accountAlreadyKicked();
        }

        Map<String, Object> updateValues = new HashMap<>();
        if (request.getRole() == null || request.getRole() != invitation.getRole()) {
            updateValues.put("role", request.getRole());
        }

        if (updateValues.size() <= 0)
            throw ServiceExceptionUtils.missingParam("Nothing to update");

        invitation = joinedOrganizationRepository.updateDetail(invitation, updateValues);

        return new JoinedOrganizationResponse(invitation);
    }

    @Override
    public List<PoorOrganizationInfoResponse> listOrganizationPaging(ListOrgPagingRequest request) {
        List<Organization> organizations = organizationRepository.findOrganizationPaging(request);
        List<PoorOrganizationInfoResponse> responses = new ArrayList<>();
        for (Organization organization : organizations) {
            List<BeowulfWallet> wallets = beowulfWalletRepository.findByOrg_id(organization.getOrg_id());
            if (!wallets.isEmpty())
                responses.add(new PoorOrganizationInfoResponse(organization, wallets.get(0)));
            else
                responses.add(new PoorOrganizationInfoResponse(organization));
        }
        return responses;
    }

    private TutorAccount ifBeValidTutor(String accountId) {
        TutorAccount account = tutorAccountRepository.findByAccountId(accountId);

        if (account == null) {
            throw ServiceExceptionUtils.accountNotFound();
        }

        return account;
    }
}
