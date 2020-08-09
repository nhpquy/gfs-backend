package com.gfs.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@Configuration
@EnableScheduling
public class ApplicationProperties {
    private static ApplicationProperties instance;

    /**
     * Email
     */
    @Value("${ses.company.name}")
    private String sesCompanyName;
    @Value("${ses.company.email}")
    private String sesCompanyEmail;
    @Value("${ses.key}")
    private String sesAccessKey;
    @Value("${ses.secret}")
    private String sesSecret;
    @Value("${ses.logo}")
    private String sesLogo;

    /**
     * Cross Origin
     */
    @Value("#{'${web.mvc.cors:}'.split(',')}")
    private List<String> allowedCrossDomains;
    @Value("#{'${web.mvc.cors.admin:}'.split(',')}")
    private List<String> allowedCrossDomainsAdmin;

    /**
     * Official domain
     */
    @Value("${domain.web}")
    private String officialWebDomain;
    @Value("${domain.api}")
    private String officialApiDomain;
    @Value("${domain.app}")
    private String officialAppDomain;

    /**
     * Facebook
     */
    @Value("#{'${facebook.app.client.ids}'.split(',')}")
    private List<String> facebookAppClientIds;
    @Value("${facebook.app.access.token}")
    private String facebookAppAccessToken;

    /**
     * Google
     */
    @Value("#{'${google.app.client.ids}'.split(',')}")
    private List<String> googleAppClientIds;

    /**
     * S3
     */
    @Value("${s3.key.access}")
    private String s3AccessKey;
    @Value("${s3.key.secret}")
    private String s3SecretKey;
    @Value("${s3.bucket.name}")
    private String s3BucketName;

    /**
     * Jwt Config
     */
    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * SMTP Config
     */
    @Value("${gmail.account}")
    private String gmailAccount;
    @Value("${gmail.project}")
    private String gmailProject;
    @Value("${gmail.project.logo}")
    private String gmailProjectLogo;
    @Value("${gmail.pwd}")
    private String gmailPwd;

    /**
     * Secret declare
     */
    @Value("${crypto.aes.secretkey:1234567812345678}")
    private String crypto_aes_secretKey;
    @Value("${crypto.aes.initVector:1234567812345678}")
    private String crypto_aes_initVector;

    public ApplicationProperties() {
        instance = this;
    }

    public static ApplicationProperties getInstance() {
        return instance;
    }

    public static String getSesCompanyName() {
        return instance.sesCompanyName;
    }

    public static String getSesCompanyEmail() {
        return instance.sesCompanyEmail;
    }

    public static String getSesAccessKey() {
        return instance.sesAccessKey;
    }

    public static String getSesSecret() {
        return instance.sesSecret;
    }

    public static String getSesLogo() {
        return instance.gmailProjectLogo;
    }

    public static List<String> getAllowedCrossDomains() {
        return instance.allowedCrossDomains;
    }

    public static String getOfficialWebDomain() {
        return instance.officialWebDomain;
    }

    public static String getOfficialApiDomain() {
        return instance.officialApiDomain;
    }

    public static String getOfficialAppDomain() {
        return instance.officialAppDomain;
    }

    public static List<String> getFacebookAppClientIds() {
        return instance.facebookAppClientIds;
    }

    public static String getFacebookAppAccessToken() {
        return instance.facebookAppAccessToken;
    }

    public static List<String> getGoogleAppClientIds() {
        return instance.googleAppClientIds;
    }

    public static String getS3AccessKey() {
        return instance.s3AccessKey;
    }

    public static String getS3SecretKey() {
        return instance.s3SecretKey;
    }

    public static String getS3BucketName() {
        return instance.s3BucketName;
    }

    public static String getJwtSecret() {
        return instance.jwtSecret;
    }

    public static List<String> getAllowedCrossDomainsAdmin() {
        return instance.allowedCrossDomainsAdmin;
    }

    public static String getGmailAccount() {
        return instance.gmailAccount;
    }

    public static String getGmailProject() {
        return instance.gmailProject;
    }

    public static String getGmailPwd() {
        return instance.gmailPwd;
    }

    public static String getGmailProjectLogo() {
        return instance.gmailProjectLogo;
    }

    public static String getCrypto_aes_secretKey() {
        return instance.crypto_aes_secretKey;
    }

    public static String getCrypto_aes_initVector() {
        return instance.crypto_aes_initVector;
    }
}
