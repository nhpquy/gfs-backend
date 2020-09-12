package com.gfs.services.impl;

import com.gfs.domain.component.ScryptKdfPasswordEncoder;
import com.gfs.domain.document.*;
import com.gfs.domain.enums.*;
import com.gfs.domain.exception.ServiceException;
import com.gfs.domain.model.AuthorizationData;
import com.gfs.domain.model.CurrentAccountLogin;
import com.gfs.domain.model.CurrentStudentLogin;
import com.gfs.domain.model.CurrentTutorLogin;
import com.gfs.domain.model.jwt.JwtAccessToken;
import com.gfs.domain.repository.inf.*;
import com.gfs.domain.request.AccountLoginRequest;
import com.gfs.domain.request.LoginRequest;
import com.gfs.domain.response.AccountLoginResponse;
import com.gfs.domain.response.GeneralSubmitResponse;
import com.gfs.domain.utils.BasicAccessTokenUtils;
import com.gfs.domain.utils.JWTAccessTokenUtils;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.domain.utils.StringUtils;
import com.gfs.services.inf.AccountAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AccountAuthorizationServiceImpl implements AccountAuthorizationService {
    @Autowired
    AccountAuthorizedTokenRepository accountAuthorizedTokenRepository;

    @Autowired
    StudentAccountRepository studentAccountRepository;

    @Autowired
    TutorAccountRepository tutorAccountRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    JoinedOrganizationRepository joinedOrganizationRepository;

    @Autowired
    ScryptKdfPasswordEncoder scryptKdfPasswordEncoder;


    @Override
    public AccountLoginResponse login(AccountProfile profile, AccountLoginRequest request) throws Exception {
        Account account = null;
        String email = StringUtils.handleEmailOrPhoneNumber(request.getEmail());

        if (profile == AccountProfile.student)
            account = studentAccountRepository.findByEmail(email);
        else if (profile == AccountProfile.tutor)
            account = tutorAccountRepository.findByEmail(email);

        if (account == null)
            throw ServiceExceptionUtils.accountNotFound();

        if (!scryptKdfPasswordEncoder.matches(request.getPassword(), account.getPassword()))
            throw ServiceExceptionUtils.wrongPassword();

        return loginSuccessfully(account, request, AccountLoginType.email);
    }

    @Override
    public AccountLoginResponse loginSuccessfully(Account account, LoginRequest request, AccountLoginType loginType) throws Exception {
        AccountAuthorizedToken authorizedToken = AccountAuthorizedToken.generateLoginSuccessfullyToken(account, request, loginType);

        if (AccountStatus.activated == account.getAccount_status() || AccountStatus.approved == account.getAccount_status()) {
            authorizedToken.setCurrent_requirement(CurrentLoginRequirement.none);
            authorizedToken.setRole(AuthorizeTokenRole.authorized);
        } else if (AccountStatus.verifying == account.getAccount_status()) {
            authorizedToken.setCurrent_requirement(CurrentLoginRequirement.activate);
            authorizedToken.setRole(AuthorizeTokenRole.require_activate);
        } else throw ServiceExceptionUtils.unAuthorize();

        authorizedToken = accountAuthorizedTokenRepository.save(authorizedToken);

        switch (account.getProfile_type()) {
            case student:
                return new AccountLoginResponse(account, authorizedToken);
            case tutor:
                JoinedOrganization joinedOrg = joinedOrganizationRepository.findByOrg_idAndAccount_id(account.getCurrent_org_id(), account.getAccount_id());
                return new AccountLoginResponse(account, authorizedToken, joinedOrg);
            default:
                throw ServiceExceptionUtils.unAuthorize();
        }
    }

    @Override
    public CurrentAccountLogin loadAccount(String authorization) {
        try {
            if (StringUtils.isEmpty(authorization))
                throw ServiceExceptionUtils.unAuthorize();

            AuthorizationData data = getAuthorizationData(authorization);
            AccountAuthorizedToken authorizedToken = getAccountAuthorizedToken(data);

            Account account = null;
            if (AccountProfile.student == authorizedToken.getProfile_type())
                account = studentAccountRepository.findByAccountId(authorizedToken.getAccount_id());
            else if (AccountProfile.tutor == authorizedToken.getProfile_type())
                account = tutorAccountRepository.findByAccountId(authorizedToken.getAccount_id());

            if (account == null)
                throw ServiceExceptionUtils.unAuthorize();

            if (!account.getAccount_id().equals(data.getId()))
                throw ServiceExceptionUtils.unAuthorize();

            return new CurrentAccountLogin(account, authorizedToken);
        } catch (ServiceException e) {
            return new CurrentAccountLogin(e);
        } catch (Exception e) {
            return new CurrentAccountLogin(ServiceExceptionUtils.unAuthorize());
        }
    }

    @Override
    public CurrentStudentLogin loadStudent(String authorization) {
        try {
            if (StringUtils.isEmpty(authorization))
                throw ServiceExceptionUtils.unAuthorize();

            AuthorizationData data = getAuthorizationData(authorization);
            AccountAuthorizedToken authorizedToken = getAccountAuthorizedToken(data);

            StudentAccount account = null;
            if (AccountProfile.student == authorizedToken.getProfile_type())
                account = studentAccountRepository.findByAccountId(authorizedToken.getAccount_id());

            if (account == null)
                throw ServiceExceptionUtils.unAuthorize();

            if (!account.getAccount_id().equals(data.getId()))
                throw ServiceExceptionUtils.unAuthorize();

            return new CurrentStudentLogin(account, authorizedToken);
        } catch (ServiceException e) {
            return new CurrentStudentLogin(e);
        } catch (Exception e) {
            return new CurrentStudentLogin(ServiceExceptionUtils.unAuthorize());
        }
    }

    @Override
    public CurrentTutorLogin loadTutor(String authorization) {
        try {
            if (StringUtils.isEmpty(authorization))
                throw ServiceExceptionUtils.unAuthorize();

            AuthorizationData data = getAuthorizationData(authorization);
            AccountAuthorizedToken authorizedToken = getAccountAuthorizedToken(data);

            TutorAccount account = null;
            if (AccountProfile.tutor == authorizedToken.getProfile_type())
                account = tutorAccountRepository.findByAccountId(authorizedToken.getAccount_id());

            if (account == null)
                throw ServiceExceptionUtils.unAuthorize();

            if (!account.getAccount_id().equals(data.getId()))
                throw ServiceExceptionUtils.unAuthorize();

            JoinedOrganization joinedOrg = joinedOrganizationRepository.findByOrg_idAndAccount_id(account.getCurrent_org_id(), account.getAccount_id());

            return new CurrentTutorLogin(account, authorizedToken, joinedOrg);
        } catch (ServiceException e) {
            return new CurrentTutorLogin(e);
        } catch (Exception e) {
            return new CurrentTutorLogin(ServiceExceptionUtils.unAuthorize());
        }
    }

    @Override
    public GeneralSubmitResponse logout(AccountAuthorizedToken authorizedToken) {
        accountAuthorizedTokenRepository.delete(authorizedToken);
        return new GeneralSubmitResponse(true);
    }

    @Override
    public AccountLoginResponse switchOrganizationContext(AccountAuthorizedToken authorizedToken, String orgId) throws Exception {
        TutorAccount account = tutorAccountRepository.findByAccountId(authorizedToken.getAccount_id());

        if (account == null)
            throw ServiceExceptionUtils.unAuthorize();

        if (orgId.equals(account.getCurrent_org_id())) {
            throw ServiceExceptionUtils.organizationAlreadySwitched();
        }

        JoinedOrganization joinedOrg = joinedOrganizationRepository.findByOrg_idAndAccount_id(orgId, account.getAccount_id());

        if (joinedOrg == null) {
            throw ServiceExceptionUtils.accountNotInvited();
        }

        switch (joinedOrg.getStatus()) {
            case pending:
                throw ServiceExceptionUtils.accountNotAccepted();
            case denied:
                throw ServiceExceptionUtils.accountAlreadyDenied();
            case kicked:
                throw ServiceExceptionUtils.accountAlreadyKicked();
        }

        account = tutorAccountRepository.updateAccountDetail(account.getAccount_id(), Collections.singletonMap("current_org_id", orgId));

        return new AccountLoginResponse(account, authorizedToken, joinedOrg);
    }

    /**
     * Private method
     */
    private AuthorizationData getAuthorizationData(String authorization) {
        AuthorizationData authorizationData = null;
        if (StringUtils.isEmpty(authorization))
            return null;
        if (authorization.startsWith(AuthorizeTokenType.Basic.name()))
            authorizationData = BasicAccessTokenUtils.decodeBasicAccessToken(authorization);
        else if (authorization.startsWith(AuthorizeTokenType.Bearer.name())) {
            JwtAccessToken accessToken = JWTAccessTokenUtils.decodeAccessToken(authorization);
            if (accessToken != null && accessToken.isValid()) {
                if (accessToken.getPayload() != null) {
                    authorizationData = new AuthorizationData(accessToken.getPayload().getId(), accessToken.getPayload().getToken());
                }
            }
        }
        return authorizationData;
    }

    private AccountAuthorizedToken getAccountAuthorizedToken(AuthorizationData data) {
        if (data == null)
            throw ServiceExceptionUtils.unAuthorize();
        if (StringUtils.isEmpty(data.getId()) || StringUtils.isEmpty(data.getToken()))
            throw ServiceExceptionUtils.unAuthorize();

        AccountAuthorizedToken authorizedToken = accountAuthorizedTokenRepository.findByToken(data.getToken());
        if (authorizedToken == null)
            throw ServiceExceptionUtils.unAuthorize();

        return authorizedToken;
    }
}
