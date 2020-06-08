package com.gfs.services.impl;

import com.gfs.domain.component.ScryptKdfPasswordEncoder;
import com.gfs.domain.constant.EmailConstant;
import com.gfs.domain.document.Account;
import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.enums.*;
import com.gfs.domain.model.VerificationToken;
import com.gfs.domain.repository.inf.*;
import com.gfs.domain.request.*;
import com.gfs.domain.response.AccountLoginResponse;
import com.gfs.domain.response.ForgotPasswordResponse;
import com.gfs.domain.response.GeneralSubmitResponse;
import com.gfs.domain.utils.*;
import com.gfs.services.inf.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Validated
public class AccountServiceImpl implements AccountService {

    @Autowired
    ScryptKdfPasswordEncoder scryptKdfPasswordEncoder;

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    AccountAuthorizedTokenRepository accountAuthorizedTokenRepository;

    @Autowired
    TutorAccountRepository tutorAccountRepository;

    @Autowired
    StudentAccountRepository studentAccountRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account createAccount(AccountProfile profile, RegisterAccountRequest request) {
        Account account = new Account();
        account.setAccount_id(UUID.randomUUID().toString());
        account.setPassword(scryptKdfPasswordEncoder.encode(request.getPassword()));
        account.setEmail(StringUtils.handleEmailOrPhoneNumber(request.getEmail()));
        account.setProfile_type(profile);
        account.setName(request.getName());
        account.setSecure_pwd(true);
        account.setAccount_status(AccountStatus.verifying);
        account.setOnline_status(AccountOnlineStatus.offline);
        return account;
    }

    @Override
    public GeneralSubmitResponse resendActivateAccount(Account account) {
        if (AccountStatus.verifying != account.getAccount_status())
            return new GeneralSubmitResponse(false);

        VerificationToken verificationToken = account.getActivate_token();
        if (verificationToken == null || !verificationToken.isValid()) {
            verificationToken = generateVerificationToken(6, TimeUnit.DAYS.toSeconds(1));

            if (AccountProfile.tutor == account.getProfile_type())
                tutorAccountRepository.updateAccountDetail(account.getAccount_id(), Collections.singletonMap("activate_token", verificationToken));
            else if (AccountProfile.student == account.getProfile_type())
                studentAccountRepository.updateAccountDetail(account.getAccount_id(), Collections.singletonMap("activate_token", verificationToken));
        }

        sendEmailActivateAccount(account, SendEmailType.Gmail);

        return new GeneralSubmitResponse(true);
    }

    @Override
    public AccountLoginResponse activateAccount(Account account, AccountAuthorizedToken authorizedToken, ActivateAccountRequest request) throws Exception {

        VerificationToken verificationToken = account.getActivate_token();
        if (verificationToken == null || !verificationToken.isValid())
            throw ServiceExceptionUtils.codeExpired();

        String key = account.getAccount_id() + ":activate:failed";

        if (verificationToken.isValid(request.getCode())) {
            Map<String, Object> updateValues = new HashMap<>();
            updateValues.put("account_status", AccountStatus.activated);
            updateValues.put("activate_token", null);

            if (AccountProfile.tutor == account.getProfile_type())
                account = tutorAccountRepository.updateAccountDetail(account.getAccount_id(), updateValues);
            else if (AccountProfile.student == account.getProfile_type())
                account = studentAccountRepository.updateAccountDetail(account.getAccount_id(), updateValues);

            authorizedToken = accountAuthorizedTokenRepository.activated(authorizedToken);
            RedisUtils.delete(key);

            return new AccountLoginResponse(account, authorizedToken);
        } else {
            long count = RedisUtils.increase(key);
            RedisUtils.expire(key, (int) TimeUnit.MINUTES.toSeconds(5));
            if (count >= 5) {
                if (AccountProfile.tutor == account.getProfile_type())
                    tutorAccountRepository.updateAccountDetail(account.getAccount_id(), Collections.singletonMap("activate_token", null));
                else if (AccountProfile.student == account.getProfile_type())
                    studentAccountRepository.updateAccountDetail(account.getAccount_id(), Collections.singletonMap("activate_token", null));
                accountAuthorizedTokenRepository.delete(authorizedToken);
                RedisUtils.delete(key);
                throw ServiceExceptionUtils.unAuthorize();
            }
            throw ServiceExceptionUtils.wrongCode();
        }
    }

    @Override
    public ForgotPasswordResponse forgotPassword(AccountProfile profile, ForgotPasswordRequest request) throws Exception {

        String email = StringUtils.handleEmailOrPhoneNumber(request.getEmail());

        Account account = accountRepository.findAccountByEmail(profile, email);
        if (account == null)
            throw ServiceExceptionUtils.accountNotFound();

        AccountAuthorizedToken authorizedToken = generateForgotPasswordToken(account, 6, TimeUnit.DAYS.toSeconds(1));
        sendEmailAuthorizedAccount(email, authorizedToken, SendEmailType.Gmail);
        authorizedToken = accountAuthorizedTokenRepository.save(authorizedToken);

        return new ForgotPasswordResponse(authorizedToken);
    }

    @Override
    public GeneralSubmitResponse resendForgotPasswordCode(Account account, AccountAuthorizedToken authorizedToken) {
        sendEmailAuthorizedAccount(account.getEmail(), authorizedToken, SendEmailType.Gmail);
        return new GeneralSubmitResponse(true);
    }

    @Override
    public GeneralSubmitResponse verifyForgotPassword(AccountAuthorizedToken authorizedToken, VerifyForgotPasswordRequest request) {
        String key = authorizedToken.getToken() + ":verify:failed";
        if (StringUtils.match(authorizedToken.getVerify_code(), request.getCode())) {
            Map<String, Object> updateValues = new HashMap<>();
            updateValues.put("role", AuthorizeTokenRole.activated_forgot_password);
            updateValues.put("current_requirement", CurrentLoginRequirement.none);
            accountAuthorizedTokenRepository.updateDetail(authorizedToken.getToken(), updateValues);
            RedisUtils.delete(key);
            return new GeneralSubmitResponse(true);
        } else {
            long count = RedisUtils.increase(key);
            RedisUtils.expire(key, (int) TimeUnit.MINUTES.toSeconds(5));
            if (count >= 5) {
                accountAuthorizedTokenRepository.delete(authorizedToken);
                throw ServiceExceptionUtils.unAuthorize();
            }
            return new GeneralSubmitResponse(false);
        }
    }

    @Override
    public GeneralSubmitResponse resetPassword(Account account, AccountAuthorizedToken authorizedToken, ResetPasswordRequest request) {
        String securePassword = scryptKdfPasswordEncoder.encode(request.getPassword());
        accountRepository.setPassword(authorizedToken.getProfile_type(), authorizedToken.getAccount_id(), securePassword);
        accountAuthorizedTokenRepository.delete(authorizedToken);
        if (AccountStatus.verifying == account.getAccount_status()) {
            Map<String, Object> updateValues = new HashMap<>();
            updateValues.put("account_status", AccountStatus.activated);
            updateValues.put("activate_token", null);
            accountRepository.updateAccountDetail(account, updateValues);
        }
        return new GeneralSubmitResponse(true);
    }

    @Override
    public GeneralSubmitResponse changePassword(Account account, AccountAuthorizedToken authorizedToken, ChangePasswordRequest request) {
        String key = authorizedToken.getToken() + ":changepassword:failed";
        if (scryptKdfPasswordEncoder.matches(request.getCurrent_password(), account.getPassword())) {
            String securePassword = scryptKdfPasswordEncoder.encode(request.getNew_password());
            accountRepository.setPassword(account.getProfile_type(), account.getAccount_id(), securePassword);
            RedisUtils.delete(key);
            if (request.isDelete_all_session())
                accountAuthorizedTokenRepository.deleteOtherToken(account.getAccount_id(), authorizedToken.getToken());
            return new GeneralSubmitResponse(true);
        } else {
            long count = RedisUtils.increase(key);
            RedisUtils.expire(key, (int) TimeUnit.MINUTES.toSeconds(5));
            if (count >= 5) {
                accountAuthorizedTokenRepository.delete(authorizedToken);
                throw ServiceExceptionUtils.unAuthorize();
            }
            throw ServiceExceptionUtils.wrongPassword();
        }
    }

    @Override
    public Account ifBeValidAccount(String email) {
        Account acocunt = studentAccountRepository.findByEmail(email);
        if (acocunt == null)
            acocunt = tutorAccountRepository.findByEmail(email);
        if (acocunt == null)
            throw ServiceExceptionUtils.accountNotFound();
        return acocunt;
    }

    /**
     * Static method
     */
    protected static VerificationToken generateVerificationToken(int lengthCode, long expiredTime) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setCode(RandomUtils.randomNumber(lengthCode));
        verificationToken.setCreated_at(System.currentTimeMillis());
        verificationToken.setType(VerificationTokenType.activate);
        verificationToken.setValid_second(expiredTime);
        return verificationToken;
    }

    protected static AccountAuthorizedToken generateForgotPasswordToken(Account account, int lengthCode, long expiredTime) {
        AccountAuthorizedToken authorizedToken = new AccountAuthorizedToken();
        authorizedToken.setToken_type(AuthorizeTokenType.Basic);
        authorizedToken.setToken(UUID.randomUUID().toString());
        authorizedToken.setUser_token(null);
        authorizedToken.setUser_agent(null);
        authorizedToken.setRole(AuthorizeTokenRole.forgot_password);
        authorizedToken.setProfile_type(account.getProfile_type());
        authorizedToken.setLogin_type(AccountLoginType.forgot_pwd);
        authorizedToken.setExpire_seconds(expiredTime);
        authorizedToken.setCurrent_requirement(CurrentLoginRequirement.verify_forgot_pwd);
        authorizedToken.setAccount_id(account.getAccount_id());
        authorizedToken.setVerify_code(RandomUtils.randomNumber(lengthCode));
        return authorizedToken;
    }

    protected static void sendEmailActivateAccount(Account account, SendEmailType sendEmailType) {
        VerificationToken verificationToken = account.getActivate_token();
        if (verificationToken == null)
            throw ServiceExceptionUtils.missingParam("activate_token");
        TimeZone tz = TimeZone.getTimeZone("UTC");
        String time = DateTimeProvider.convertDateToStringWithTimeZoneEmailFormat(EmailConstant.LANGUAGE,
                verificationToken.getCreated_at() + verificationToken.getValid_second() * 1000, tz);
        String code = verificationToken.getCode();
        switch (sendEmailType) {
            case SES:
                AmazonSESUtils.sendEmailActivateAccount(EmailConstant.TITLE_VALIDATION, account.getEmail(), code, time);
                break;
            case Gmail:
                GmailUtils.sendEmailActivateAccount(EmailConstant.TITLE_VALIDATION, account.getEmail(), code, time);
                break;
        }
    }

    protected static void sendEmailAuthorizedAccount(String email, AccountAuthorizedToken authorizedToken, SendEmailType sendEmailType) {
        if (authorizedToken == null)
            throw ServiceExceptionUtils.unAuthorize();
        TimeZone tz = TimeZone.getTimeZone("UTC");
        String time = DateTimeProvider.convertDateToStringWithTimeZoneEmailFormat(EmailConstant.LANGUAGE,
                authorizedToken.getCreated_at() + authorizedToken.getExpire_seconds() * 1000, tz);
        String code = authorizedToken.getVerify_code();
        switch (sendEmailType) {
            case SES:
                AmazonSESUtils.sendEmailVerifyEmailAddress(EmailConstant.TITLE_VALIDATION, email, time, code);
                break;
            case Gmail:
                GmailUtils.sendEmailVerifyEmailAddress(EmailConstant.TITLE_VALIDATION, email, time, code);
                break;
        }
    }


    protected static Map<String, Object> updateGeneralAccountProfile(UpdateAccountProfileRequest request) {
        Map<String, Object> updateValues = new HashMap<>();
        if (StringUtils.hasText(request.getPhone_number()))
            updateValues.put("phone_number", StringUtils.handleEmailOrPhoneNumber(request.getPhone_number()));
        if (StringUtils.hasText(request.getName()))
            updateValues.put("name", request.getName());
        if (request.getGender() != null)
            updateValues.put("gender", request.getGender());
        if (StringUtils.hasText(request.getAddress()))
            updateValues.put("address", request.getAddress());
        if (StringUtils.hasText(request.getAvatar()))
            updateValues.put("avatar", request.getAvatar());
        if (StringUtils.hasText(request.getCover()))
            updateValues.put("cover", request.getCover());
        if (StringUtils.hasText(request.getDate_of_birth()))
            updateValues.put("date_of_birth", request.getDate_of_birth());
        return updateValues;
    }
}
