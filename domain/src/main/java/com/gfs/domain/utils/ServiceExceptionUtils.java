package com.gfs.domain.utils;

import com.gfs.domain.exception.ServiceException;
import org.springframework.http.HttpStatus;

public class ServiceExceptionUtils {

    /**
     * 400 zone
     */
    // Define error for 400 here
    private static final String ERROR_INVALID_JSON = "400001";
    private static final String DESC_INVALID_JSON = "Invalid Json format";

    private static final String ERROR_MISSING_PARAM = "400002";
    private static final String DESC_ERROR_MISSING_PARAM = "Missing parameter: %s";

    private static final String ERROR_REFERENCE_ID_EXISTS = "400003";
    private static final String DESC_ERROR_REFERENCE_ID_EXISTS = "Reference id is already exists";

    private static final String ERROR_EMAIL_EXISTS = "400004";
    private static final String DESC_ERROR_EMAIL_EXISTS = "Email is already exists";

    private static final String ERROR_VALUE_EXISTS = "400005";
    private static final String DESC_ERROR_VALUE_EXISTS = "Value is already exists: %s";

    private static final String ERROR_INVALID_EMAIL_OR_PASSWORD = "400006";
    private static final String DESC_ERROR_INVALID_EMAIL_OR_PASSWORD = "Invalid email or password";

    private static final String ERROR_INVALID_REFRESH_TOKEN = "400007";
    private static final String DESC_ERROR_INVALID_REFRESH_TOKEN = "Invalid token";

    private static final String ERROR_ACCOUNT_ALREADY_ENABLE_2FA = "400008";
    private static final String DESC_ERROR_ACCOUNT_ALREADY_ENABLE_2FA = "Account already enable 2fa";

    private static final String ERROR_INVALID_FA_CODE = "400009";
    private static final String DESC_ERROR_INVALID_FA_CODE = "Invalid 2fa code";

    private static final String ERROR_ACCOUNT_ALREADY_EXISTS = "400010";
    private static final String DESC_ERROR_ACCOUNT_ALREADY_EXISTS = "Account already exists";

    private static final String ERROR_ALIAS_ALREADY_EXISTS = "400011";
    private static final String DESC_ERROR_ALIAS_ALREADY_EXISTS = "Alias already exists";

    private static final String ERROR_ALIAS_NOT_CONTAIN_SPACE = "400012";
    private static final String DESC_ERROR_ALIAS_NOT_CONTAIN_SPACE = "Alias shouldn't contain whitespace";

    private static final String ERROR_INVALID_PARAM = "400013";
    private static final String DESC_ERROR_INVALID_PARAM = "Invalid param: %s";

    private static final String ERROR_ACCOUNT_NOT_ACTIVATED = "400014";
    private static final String DESC_ERROR_ACCOUNT_NOT_ACTIVATED = "Account was not activated";

    private static final String ERROR_PASSWORD_NOT_CONTAIN_WHITESPACE = "400015";
    private static final String DESC_ERROR_PASSWORD_NOT_CONTAIN_WHITESPACE = "Password doesn't contain whitespace";

    private static final String ERROR_LINK_EXPIRED = "400016";
    private static final String DESC_ERROR_LINK_EXPIRED = "Link was expired";

    private static final String ERROR_INVALID_LINK = "400017";
    private static final String DESC_ERROR_INVALID_LINK = "Link is invalid";

    private static final String ERROR_ACCOUNT_ACTIVATED = "400018";
    private static final String DESC_ERROR_ACCOUNT_ACTIVATED = "Email was activated";

    private static final String ERROR_CONFLICT_MAX_MEMBER = "400019";
    private static final String DESC_ERROR_CONFLICT_MAX_MEMBER = "Total member is greater than %s";

    private static final String ERROR_ACCOUNT_ALREADY_SET_PASSWORD = "400020";
    private static final String DESC_ERROR_ACCOUNT_ALREADY_SET_PASSWORD = "You have already set password";

    private static final String ERROR_WRONG_PASSWORD = "400021";
    private static final String DESC_ERROR_WRONG_PASSWORD = "Wrong password";

    private static final String ERROR_DRAG_VERIFY_FAIL = "400022";
    private static final String DESC_ERROR_DRAG_VERIFY_FAIL = "Invalid Captcha";

    private static final String ERROR_ACCOUNT_NOT_ENABLE_2FA = "400023";
    private static final String DESC_ERROR_ACCOUNT_NOT_ENABLE_2FA = "Account is not enable 2fa";

    private static final String ERROR_LOGIN_BUTTON_DISABLED = "400024";
    private static final String DESC_ERROR_LOGIN_BUTTON_DISABLED = "Login button was disabled";

    private static final String ERROR_PHONE_NUMBER_EXISTS = "400025";
    private static final String DESC_ERROR_PHONE_NUMBER_EXISTS = "Phone number already exists";

    private static final String ERROR_LINKED_TELEGRAM = "400026";
    private static final String DESC_ERROR_LINKED_TELEGRAM = "Account already linked telegram";

    private static final String ERROR_USED_TELEGRAM = "400027";
    private static final String DESC_ERROR_USED_TELEGRAM = "This Telegram account has been linked to another Quickom account";

    private static final String ERROR_NOT_LINKED_TELEGRAM = "400028";
    private static final String DESC_ERROR_NOT_LINKED_TELEGRAM = "Account didn't link telegram";

    private static final String ERROR_CAN_NOT_SEND_SMS = "400029";
    private static final String DESC_ERROR_CAN_NOT_SEND_SMS = "Can not send SMS";

    private static final String ERROR_INVALID_PHONE_NUMBER = "400030";
    private static final String DESC_ERROR_INVALID_PHONE_NUMBER = "Invalid Phone Number";

    private static final String ERROR_INVALID_TRANSACTION = "400031";
    private static final String DESC_ERROR_INVALID_TRANSACTION = "Invalid transaction";

    private static final String ERROR_FACEBOOK_ACCOUNT_ALREADY_USED = "400032";
    private static final String DESC_ERROR_FACEBOOK_ACCOUNT_ALREADY_USED = "Facebook account is already used.";

    private static final String ERROR_GOOGLE_ACCOUNT_ALREADY_USED = "400033";
    private static final String DESC_ERROR_GOOGLE_ACCOUNT_ALREADY_USED = "Google account is already used.";

    private static final String ERROR_EMAIL_ALREADY_USED = "400034";
    private static final String DESC_ERROR_EMAIL_ALREADY_USED = "Email is already used.";

    private static final String ERROR_EMAIL_ALREADY_VERIFIED = "400035";
    private static final String DESC_ERROR_EMAIL_ALREADY_VERIFIED = "Account verified";

    private static final String ERROR_CODE_EXPIRED = "400036";
    private static final String DESC_ERROR_CODE_EXPIRED = "Code was expired";

    private static final String ERROR_INVALID_VALUE = "400037";
    private static final String DESC_ERROR_INVALID_VALUE = "Invalid value: '%s'";

    private static final String ERROR_WRONG_CODE = "400038";
    private static final String DESC_ERROR_WRONG_CODE = "Wrong code";

    private static final String ERROR_INVALID_REDIRECT_URL = "400039";
    private static final String DESC_ERROR_INVALID_REDIRECT_URL = "Invalid redirect URL";

    private static final String ERROR_INVALID_CHECKSUM = "400040";
    private static final String DESC_ERROR_INVALID_CHECKSUM = "Invalid checksum";

    private static final String ERROR_TUTOR_ALREADY_APPROVED = "400041";
    private static final String DESC_ERROR_TUTOR_ALREADY_APPROVED = "Tutor already has been approved";

    private static final String ERROR_TUTOR_ALREADY_DEACTIVE = "400042";
    private static final String DESC_ERROR_TUTOR_ALREADY_DEACTIVE = "Tutor already has been deactived";

    private static final String ERROR_FILE_EXISTED = "400043";
    private static final String DESC_ERROR_FILE_EXISTED = "File existed";
    /** End 400 zone **/

    /**
     * 401 zone
     */
    // Define error for 401 here
    private static final String ERROR_UNAUTHORIZE = "401001";
    private static final String DESC_ERROR_UNAUTHORIZE = "Not authorized";

    private static final String ERROR_API_KEY_DISABLED = "401002";
    private static final String DESC_ERROR_API_KEY_DISABLED = "Api key was disabled";

    private static final String ERROR_GG_EMAIL_NOT_VERIFIED = "401003";
    private static final String DESC_ERROR_GG_EMAIL_NOT_VERIFIED = "Email was not verified";

    /** End 401 zone **/

    /**
     * 404 zone
     */
    // Define error for 404 here
    private static final String ERROR_ADDRESS_NOT_FOUND = "404001";
    private static final String DESC_ERROR_ADDRESS_NOT_FOUND = "Address not found";

    private static final String ERROR_ACCOUNT_NOT_FOUND = "404002";
    private static final String DESC_ERROR_ACCOUNT_NOT_FOUND = "Account not found";

    private static final String ERROR_API_KEY_NOT_FOUND = "404003";
    private static final String DESC_ERROR_API_KEY_NOT_FOUND = "Api key not found";

    private static final String ERROR_LOGIN_BUTTON_NOT_FOUND = "404004";
    private static final String DESC_ERROR_LOGIN_BUTTON_NOT_FOUND = "Login button not found";

    private static final String ERROR_CONTACT_NOT_FOUND = "404005";
    private static final String DESC_ERROR_CONTACT_NOT_FOUND = "Contact not found";

    private static final String ERROR_TRANSACTION_NOT_FOUND = "404006";
    private static final String DESC_ERROR_TRANSACTION_NOT_FOUND = "Transaction not found";

    private static final String ERROR_CERTIFICATE_NOT_FOUND = "404007";
    private static final String DESC_ERROR_CERTIFICATE_NOT_FOUND = "Certificate not found";

    private static final String ERROR_NOTIFICATION_NOT_FOUND = "404008";
    private static final String DESC_ERROR_NOTIFICATION_NOT_FOUND = "Notification not found";

    private static final String ERROR_FILE_NOT_FOUND = "404009";
    private static final String DESC_ERROR_FILE_NOT_FOUND = "File not found";
    /**
     * End 404 zone
     **/

    /**
     * 405 zone
     */
    // Define error for 405 here
    private static final String ERROR_METHOD_NOT_ALLOWED = "405001";
    private static final String DESC_ERROR_METHOD_NOT_ALLOWED = "Method is not allowed";
    /** End 405 zone **/

    /**
     * 406 zone
     */
    // Define error for 406 here
    private static final String ERROR_NOT_ENOUGH_BALANCE = "406001";
    private static final String DESC_ERROR_NOT_ENOUGH_BALANCE = "Account is not enough balance";

    private static final String ERROR_MAX_MEMBER = "406002";
    private static final String DESC_ERROR_MAX_MEMBER = "Account has reached maximum member";

    private static final String ERROR_TUTOR_ALREADY_BANNED = "406003";
    private static final String DESC_ERROR_TUTOR_ALREADY_BANNED = "Tutor has already been banned";
    /** End 406 zone **/

    /**
     * 409 zone
     */
    // Define error for 429 here
    private static final String ERROR_TOO_MANY_REQUEST = "429001";
    private static final String DESC_ERROR_TOO_MANY_REQUEST = "Too many requests";

    /** End 429 zone **/

    /**
     * 500 zone
     */
    // Define error for 500 here
    private static final String INTERNAL_SERVER_ERROR = "500000";
    private static final String DESC_INTERNAL_SERVER_ERROR = "Error while processing request";

    private static final String ERROR_CREATE_WALLET_FAILED = "500001";
    private static final String DESC_ERROR_CREATE_WALLET_FAILED = "Cannot create Beowulf wallet";

    /**
     * End 500 zone
     **/

    public static ServiceException invalidJSON() {
        return new ServiceException(ERROR_INVALID_JSON, DESC_INVALID_JSON, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException missingParam(String param) {
        return new ServiceException(ERROR_MISSING_PARAM, String.format(DESC_ERROR_MISSING_PARAM, param),
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException referenceIdAlreadyExisted() {
        return new ServiceException(ERROR_REFERENCE_ID_EXISTS, DESC_ERROR_REFERENCE_ID_EXISTS, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException unAuthorize() {
        return new ServiceException(ERROR_UNAUTHORIZE, DESC_ERROR_UNAUTHORIZE, HttpStatus.UNAUTHORIZED);
    }

    public static ServiceException apiKeyDisabled() {
        return new ServiceException(ERROR_API_KEY_DISABLED, DESC_ERROR_API_KEY_DISABLED, HttpStatus.UNAUTHORIZED);
    }

    public static ServiceException addressNotFound() {
        return new ServiceException(ERROR_ADDRESS_NOT_FOUND, DESC_ERROR_ADDRESS_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException accountNotFound() {
        return new ServiceException(ERROR_ACCOUNT_NOT_FOUND, DESC_ERROR_ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException internalServerError() {
        return new ServiceException(INTERNAL_SERVER_ERROR, DESC_INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException methodIsNotAllowed() {
        return new ServiceException(ERROR_METHOD_NOT_ALLOWED, DESC_ERROR_METHOD_NOT_ALLOWED,
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    public static ServiceException emailExists() {
        return new ServiceException(ERROR_EMAIL_EXISTS, DESC_ERROR_EMAIL_EXISTS, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException valueExists(String value) {
        return new ServiceException(ERROR_VALUE_EXISTS, String.format(DESC_ERROR_VALUE_EXISTS, value),
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException cannotCreateBeowulfWallet() {
        return new ServiceException(ERROR_CREATE_WALLET_FAILED, DESC_ERROR_CREATE_WALLET_FAILED,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException invalidEmailOrPassword() {
        return new ServiceException(ERROR_INVALID_EMAIL_OR_PASSWORD, DESC_ERROR_INVALID_EMAIL_OR_PASSWORD,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException invalidRefreshToken() {
        return new ServiceException(ERROR_INVALID_REFRESH_TOKEN, DESC_ERROR_INVALID_REFRESH_TOKEN,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException apiKeyNotFound() {
        return new ServiceException(ERROR_API_KEY_NOT_FOUND, DESC_ERROR_API_KEY_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException accountAlreadyEnable2Fa() {
        return new ServiceException(ERROR_ACCOUNT_ALREADY_ENABLE_2FA, DESC_ERROR_ACCOUNT_ALREADY_ENABLE_2FA,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException wrongPassword() {
        return new ServiceException(ERROR_WRONG_PASSWORD, DESC_ERROR_WRONG_PASSWORD, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException invalid2FaCode() {
        return new ServiceException(ERROR_INVALID_FA_CODE, DESC_ERROR_INVALID_FA_CODE, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException accountNotEnable2Fa() {
        return new ServiceException(ERROR_ACCOUNT_NOT_ENABLE_2FA, DESC_ERROR_ACCOUNT_NOT_ENABLE_2FA,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException accountAlreadyExists() {
        return new ServiceException(ERROR_ACCOUNT_ALREADY_EXISTS, DESC_ERROR_ACCOUNT_ALREADY_EXISTS,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException aliasAlreadyExists() {
        return new ServiceException(ERROR_ALIAS_ALREADY_EXISTS, DESC_ERROR_ALIAS_ALREADY_EXISTS,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException aliasNotContainSpace() {
        return new ServiceException(ERROR_ALIAS_NOT_CONTAIN_SPACE, DESC_ERROR_ALIAS_NOT_CONTAIN_SPACE,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException invalidParam(String param) {
        return new ServiceException(ERROR_INVALID_PARAM, String.format(DESC_ERROR_INVALID_PARAM, param),
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException invalidValue(String value) {
        return new ServiceException(ERROR_INVALID_VALUE, String.format(DESC_ERROR_INVALID_VALUE, value),
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException accountNotActivated() {
        return new ServiceException(ERROR_ACCOUNT_NOT_ACTIVATED, DESC_ERROR_ACCOUNT_NOT_ACTIVATED,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException passwordContainWhitespace() {
        return new ServiceException(ERROR_PASSWORD_NOT_CONTAIN_WHITESPACE, DESC_ERROR_PASSWORD_NOT_CONTAIN_WHITESPACE,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException linkExpired() {
        return new ServiceException(ERROR_LINK_EXPIRED, DESC_ERROR_LINK_EXPIRED, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException linkInvalid() {
        return new ServiceException(ERROR_INVALID_LINK, DESC_ERROR_INVALID_LINK, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException notEnoughBalance() {
        return new ServiceException(ERROR_NOT_ENOUGH_BALANCE, DESC_ERROR_NOT_ENOUGH_BALANCE, HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException emailActivated() {
        return new ServiceException(ERROR_ACCOUNT_ACTIVATED, DESC_ERROR_ACCOUNT_ACTIVATED, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException tooManyRequest() {
        return new ServiceException(ERROR_TOO_MANY_REQUEST, DESC_ERROR_TOO_MANY_REQUEST, HttpStatus.TOO_MANY_REQUESTS);
    }

    public static ServiceException maxMember() {
        return new ServiceException(ERROR_MAX_MEMBER, DESC_ERROR_MAX_MEMBER, HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException conflictMaxMember(long maxMember) {
        return new ServiceException(ERROR_CONFLICT_MAX_MEMBER, String.format(DESC_ERROR_CONFLICT_MAX_MEMBER, maxMember),
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException ggEmailNotVerified() {
        return new ServiceException(ERROR_GG_EMAIL_NOT_VERIFIED, DESC_ERROR_GG_EMAIL_NOT_VERIFIED,
                HttpStatus.UNAUTHORIZED);
    }

    public static ServiceException accountAlreadySetPassword() {
        return new ServiceException(ERROR_ACCOUNT_ALREADY_SET_PASSWORD, DESC_ERROR_ACCOUNT_ALREADY_SET_PASSWORD,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException dragVerifyFail() {
        return new ServiceException(ERROR_DRAG_VERIFY_FAIL, DESC_ERROR_DRAG_VERIFY_FAIL, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException loginButtonNotFound() {
        return new ServiceException(ERROR_LOGIN_BUTTON_NOT_FOUND, DESC_ERROR_LOGIN_BUTTON_NOT_FOUND,
                HttpStatus.NOT_FOUND);
    }

    public static ServiceException loginButtonDisabled() {
        return new ServiceException(ERROR_LOGIN_BUTTON_DISABLED, DESC_ERROR_LOGIN_BUTTON_DISABLED,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException contactNotFound() {
        return new ServiceException(ERROR_CONTACT_NOT_FOUND, DESC_ERROR_CONTACT_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException phoneNumberExists() {
        return new ServiceException(ERROR_PHONE_NUMBER_EXISTS, DESC_ERROR_PHONE_NUMBER_EXISTS, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException accountLinkedTelegram() {
        return new ServiceException(ERROR_LINKED_TELEGRAM, DESC_ERROR_LINKED_TELEGRAM, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException usedTelegramAccount() {
        return new ServiceException(ERROR_USED_TELEGRAM, DESC_ERROR_USED_TELEGRAM, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException accountNotLinkedTelegram() {
        return new ServiceException(ERROR_NOT_LINKED_TELEGRAM, DESC_ERROR_NOT_LINKED_TELEGRAM, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException canNotSendSMS() {
        return new ServiceException(ERROR_CAN_NOT_SEND_SMS, DESC_ERROR_CAN_NOT_SEND_SMS, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException invalidPhoneNumber() {
        return new ServiceException(ERROR_INVALID_PHONE_NUMBER, DESC_ERROR_INVALID_PHONE_NUMBER,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException transactionNotFound() {
        return new ServiceException(ERROR_TRANSACTION_NOT_FOUND, DESC_ERROR_TRANSACTION_NOT_FOUND,
                HttpStatus.NOT_FOUND);
    }

    public static ServiceException invalidTransaction() {
        return new ServiceException(ERROR_INVALID_TRANSACTION, DESC_ERROR_INVALID_TRANSACTION, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException facebookAccountUsed() {
        return new ServiceException(ERROR_FACEBOOK_ACCOUNT_ALREADY_USED, DESC_ERROR_FACEBOOK_ACCOUNT_ALREADY_USED,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException googleAccountUsed() {
        return new ServiceException(ERROR_GOOGLE_ACCOUNT_ALREADY_USED, DESC_ERROR_GOOGLE_ACCOUNT_ALREADY_USED,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException emailUsed() {
        return new ServiceException(ERROR_EMAIL_ALREADY_USED, DESC_ERROR_EMAIL_ALREADY_USED, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException accountVerified() {
        return new ServiceException(ERROR_EMAIL_ALREADY_VERIFIED, DESC_ERROR_EMAIL_ALREADY_VERIFIED,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException codeExpired() {
        return new ServiceException(ERROR_CODE_EXPIRED, DESC_ERROR_CODE_EXPIRED, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException certificateNotFound() {
        return new ServiceException(ERROR_CERTIFICATE_NOT_FOUND, DESC_ERROR_CERTIFICATE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException wrongCode() {
        return new ServiceException(ERROR_WRONG_CODE, DESC_ERROR_WRONG_CODE, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException invalidRedirectUrl() {
        return new ServiceException(ERROR_INVALID_REDIRECT_URL, DESC_ERROR_INVALID_REDIRECT_URL, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException invalidChecksum() {
        return new ServiceException(ERROR_INVALID_CHECKSUM, DESC_ERROR_INVALID_CHECKSUM, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException tutorAlreadyApproved() {
        return new ServiceException(ERROR_TUTOR_ALREADY_APPROVED, DESC_ERROR_TUTOR_ALREADY_APPROVED, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException tutorAlreadyDeactive() {
        return new ServiceException(ERROR_TUTOR_ALREADY_DEACTIVE, DESC_ERROR_TUTOR_ALREADY_DEACTIVE, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException accountAlreadyBanned() {
        return new ServiceException(ERROR_TUTOR_ALREADY_BANNED, DESC_ERROR_TUTOR_ALREADY_BANNED, HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException fileNotFound() {
        return new ServiceException(ERROR_FILE_NOT_FOUND, DESC_ERROR_FILE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException fileExisted() {
        return new ServiceException(ERROR_FILE_EXISTED, DESC_ERROR_FILE_EXISTED, HttpStatus.BAD_REQUEST);
    }
}
