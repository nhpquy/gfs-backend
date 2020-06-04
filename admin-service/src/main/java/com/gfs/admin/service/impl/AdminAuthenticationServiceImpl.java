package com.gfs.admin.service.impl;

import com.gfs.admin.service.inf.AdminAuthenticationService;
import com.gfs.domain.component.ScryptKdfPasswordEncoder;
import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.document.AdminAccount;
import com.gfs.domain.enums.*;
import com.gfs.domain.exception.ServiceException;
import com.gfs.domain.model.AuthorizationData;
import com.gfs.domain.model.CurrentAdminLogin;
import com.gfs.domain.model.jwt.JwtAccessToken;
import com.gfs.domain.repository.inf.AccountAuthorizedTokenRepository;
import com.gfs.domain.repository.inf.AdminAccountRepository;
import com.gfs.domain.request.AdminLoginRequest;
import com.gfs.domain.response.AccountLoginResponse;
import com.gfs.domain.response.GeneralSubmitResponse;
import com.gfs.domain.utils.BasicAccessTokenUtils;
import com.gfs.domain.utils.JWTAccessTokenUtils;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.domain.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AdminAuthenticationServiceImpl implements AdminAuthenticationService {

    @Autowired
    AccountAuthorizedTokenRepository accountAuthorizedTokenRepository;

    @Autowired
    AdminAccountRepository adminAccountRepository;

    @Autowired
    ScryptKdfPasswordEncoder scryptKdfPasswordEncoder;

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

    @Override
    public CurrentAdminLogin loadAccount(String authorization) {
        try {
            if (StringUtils.isEmpty(authorization))
                throw ServiceExceptionUtils.unAuthorize();

            AuthorizationData data = getAuthorizationData(authorization);
            if (data == null)
                throw ServiceExceptionUtils.unAuthorize();
            if (StringUtils.isEmpty(data.getId()) || StringUtils.isEmpty(data.getToken()))
                throw ServiceExceptionUtils.unAuthorize();

            AccountAuthorizedToken authorizedToken = accountAuthorizedTokenRepository.findByToken(data.getToken());
            if (authorizedToken == null)
                throw ServiceExceptionUtils.unAuthorize();

            AdminAccount account = adminAccountRepository.findByAccountId(authorizedToken.getAccount_id());

            if (account == null)
                throw ServiceExceptionUtils.unAuthorize();

            if (!account.getAccount_id().equals(data.getId()))
                throw ServiceExceptionUtils.unAuthorize();

            return new CurrentAdminLogin(account, authorizedToken);
        } catch (ServiceException e) {
            return new CurrentAdminLogin(e);
        } catch (Exception e) {
            return new CurrentAdminLogin(ServiceExceptionUtils.unAuthorize());
        }
    }

    @Override
    public AccountLoginResponse login(AdminLoginRequest request) throws Exception {
        String username = request.getUsername();
        AdminAccount account = adminAccountRepository.findByUsername(username);

        if (account == null)
            throw ServiceExceptionUtils.accountNotFound();

        if (!scryptKdfPasswordEncoder.matches(request.getPassword(), account.getPassword()))
            throw ServiceExceptionUtils.wrongPassword();

        return loginSuccessfully(account, request);
    }

    private AccountLoginResponse loginSuccessfully(AdminAccount account, AdminLoginRequest request) throws Exception {
        AccountAuthorizedToken authorizedToken = new AccountAuthorizedToken();
        authorizedToken.setAccount_id(account.getAccount_id());
        authorizedToken.setApp_platform(request.getApp_platform());
        authorizedToken.setApp_version(request.getApp_version());
        authorizedToken.setAuthorized(true);
        authorizedToken.setExpire_seconds(TimeUnit.DAYS.toSeconds(30));
        authorizedToken.setLogin_type(AccountLoginType.admin);
        authorizedToken.setProfile_type(AccountProfile.admin);
        authorizedToken.setToken(UUID.randomUUID().toString());
        authorizedToken.setToken_type(AuthorizeTokenType.Bearer);
        authorizedToken.setUser_agent(request.getUser_agent());
        authorizedToken.setUser_token(null);

        if (AccountStatus.activated == account.getAccount_status()) {
            authorizedToken.setCurrent_requirement(CurrentLoginRequirement.none);
            authorizedToken.setRole(AuthorizeTokenRole.authorized);
        } else throw ServiceExceptionUtils.unAuthorize();

        authorizedToken = accountAuthorizedTokenRepository.save(authorizedToken);
        return new AccountLoginResponse(account, authorizedToken);
    }

    public GeneralSubmitResponse logout(AccountAuthorizedToken authorizedToken) {
        accountAuthorizedTokenRepository.delete(authorizedToken);
        return new GeneralSubmitResponse(true);
    }
}
