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

    private static final String ERROR_INVALID_SHORT_NAME = "400004";
    private static final String DESC_ERROR_INVALID_SHORT_NAME = "Short name must have 4 characters or less.";

    private static final String ERROR_SHORT_NAME_EXISTS = "400005";
    private static final String DESC_ERROR_SHORT_NAME_EXISTS = "Short name is already exists";

    private static final String ERROR_EMAIL_EXISTS = "400006";
    private static final String DESC_ERROR_EMAIL_EXISTS = "Email is already exists";

    private static final String ERROR_VALUE_EXISTS = "400007";
    private static final String DESC_ERROR_VALUE_EXISTS = "Value is already exists: %s";

    private static final String ERROR_INVALID_WALLET_NAME_LENGTH = "400008";
    private static final String DESC_ERROR_INVALID_WALLET_NAME_LENGTH = "Wallet name must have 16 characters or less";

    private static final String ERROR_INVALID_WALLET_NAME_UPPERCASE = "400009";
    private static final String DESC_ERROR_INVALID_WALLET_NAME_UPPERCASE = "Wallet name must be lowercase";

    private static final String ERROR_INVALID_WALLET_NAME = "400010";
    private static final String DESC_ERROR_INVALID_WALLET_NAME = "Invalid wallet name";

    private static final String ERROR_INVALID_WALLET_NAME_EXISTS = "400011";
    private static final String DESC_ERROR_INVALID_WALLET_NAME_EXISTS = "Wallet name already exists";

    private static final String ERROR_INVALID_EMAIL_OR_PASSWORD = "400012";
    private static final String DESC_ERROR_INVALID_EMAIL_OR_PASSWORD = "Invalid email or password";

    private static final String ERROR_INVALID_REFRESH_TOKEN = "400013";
    private static final String DESC_ERROR_INVALID_REFRESH_TOKEN = "Invalid token";

    private static final String ERROR_ACCOUNT_ALREADY_ENABLE_2FA = "400014";
    private static final String DESC_ERROR_ACCOUNT_ALREADY_ENABLE_2FA = "Account already enable 2fa";

    private static final String ERROR_INVALID_FA_CODE = "400015";
    private static final String DESC_ERROR_INVALID_FA_CODE = "Invalid 2fa code";

    private static final String ERROR_GROUP_ALREADY_EXISTS = "400016";
    private static final String DESC_ERROR_GROUP_ALREADY_EXISTS = "Group already exists";

    private static final String ERROR_ACCOUNT_ALREADY_EXISTS = "400017";
    private static final String DESC_ERROR_ACCOUNT_ALREADY_EXISTS = "Account already exists";

    private static final String ERROR_ALIAS_ALREADY_EXISTS = "400018";
    private static final String DESC_ERROR_ALIAS_ALREADY_EXISTS = "Alias already exists";

    private static final String ERROR_ALIAS_NOT_CONTAIN_SPACE = "400019";
    private static final String DESC_ERROR_ALIAS_NOT_CONTAIN_SPACE = "Alias shouldn't contain whitespace";

    private static final String ERROR_INVALID_PARAM = "400020";
    private static final String DESC_ERROR_INVALID_PARAM = "Invalid param: %s";

    private static final String ERROR_ACCOUNT_NOT_ACTIVATED = "400021";
    private static final String DESC_ERROR_ACCOUNT_NOT_ACTIVATED = "Account was not activated";

    private static final String ERROR_PASSWORD_NOT_CONTAIN_WHITESPACE = "400022";
    private static final String DESC_ERROR_PASSWORD_NOT_CONTAIN_WHITESPACE = "Password doesn't contain whitespace";

    private static final String ERROR_LINK_EXPIRED = "400023";
    private static final String DESC_ERROR_LINK_EXPIRED = "Link was expired";

    private static final String ERROR_INVALID_LINK = "400024";
    private static final String DESC_ERROR_INVALID_LINK = "Link is invalid";

    private static final String ERROR_ACCOUNT_ACTIVATED = "400025";
    private static final String DESC_ERROR_ACCOUNT_ACTIVATED = "Email was activated";

    private static final String ERROR_ACCOUNT_IN_CALL = "400026";
    private static final String DESC_ERROR_ACCOUNT_IN_CALL = "Account is in a call";

    private static final String ERROR_CONFLICT_MAX_MEMBER = "400027";
    private static final String DESC_ERROR_CONFLICT_MAX_MEMBER = "Total member is greater than %s";

    private static final String ERROR_ACCOUNT_ALREADY_SET_PASSWORD = "400028";
    private static final String DESC_ERROR_ACCOUNT_ALREADY_SET_PASSWORD = "You have already set password";

    private static final String ERROR_WRONG_PASSWORD = "400029";
    private static final String DESC_ERROR_WRONG_PASSWORD = "Wrong password";

    private static final String ERROR_DRAG_VERIFY_FAIL = "400030";
    private static final String DESC_ERROR_DRAG_VERIFY_FAIL = "Invalid Captcha";

    private static final String ERROR_ACCOUNT_NOT_ENABLE_2FA = "400031";
    private static final String DESC_ERROR_ACCOUNT_NOT_ENABLE_2FA = "Account is not enable 2fa";

    private static final String ERROR_LOGIN_BUTTON_DISABLED = "400032";
    private static final String DESC_ERROR_LOGIN_BUTTON_DISABLED = "Login button was disabled";

    private static final String ERROR_MEMBER_EXISTS = "400033";
    private static final String DESC_ERROR_MEMBER_EXISTS = "Member already existed";

    private static final String ERROR_MISSING_COMPANY_NAME = "400034";
    private static final String DESC_ERROR_MISSING_COMPANY_NAME = "Missing company name";

    private static final String ERROR_PHONE_NUMBER_EXISTS = "400035";
    private static final String DESC_ERROR_PHONE_NUMBER_EXISTS = "Phone number already exists";

    private static final String ERROR_LINKED_TELEGRAM = "400036";
    private static final String DESC_ERROR_LINKED_TELEGRAM = "Account already linked telegram";

    private static final String ERROR_USED_TELEGRAM = "400037";
    private static final String DESC_ERROR_USED_TELEGRAM = "This Telegram account has been linked to another Quickom account";

    private static final String ERROR_NOT_LINKED_TELEGRAM = "400038";
    private static final String DESC_ERROR_NOT_LINKED_TELEGRAM = "Account didn't link telegram";

    private static final String ERROR_CAN_NOT_SEND_SMS = "400039";
    private static final String DESC_ERROR_CAN_NOT_SEND_SMS = "Can not send SMS";

    private static final String ERROR_INVALID_PHONE_NUMBER = "400040";
    private static final String DESC_ERROR_INVALID_PHONE_NUMBER = "Invalid Phone Number";

    private static final String ERROR_INVALID_AMOUNT = "400041";
    private static final String DESC_ERROR_INVALID_AMOUNT = "Invalid Amount";

    private static final String ERROR_BILLING_ALREADY_SPLIT = "400042";
    private static final String DESC_ERROR_BILLING_ALREADY_SPLIT = "Billing is already split";

    private static final String ERROR_INVALID_TRANSACTION = "400043";
    private static final String DESC_ERROR_INVALID_TRANSACTION = "Invalid transaction";

    private static final String ERROR_FACEBOOK_ACCOUNT_ALREADY_USED = "400044";
    private static final String DESC_ERROR_FACEBOOK_ACCOUNT_ALREADY_USED = "Facebook account is already used.";

    private static final String ERROR_GOOGLE_ACCOUNT_ALREADY_USED = "400045";
    private static final String DESC_ERROR_GOOGLE_ACCOUNT_ALREADY_USED = "Google account is already used.";

    private static final String ERROR_EMAIL_ALREADY_USED = "400046";
    private static final String DESC_ERROR_EMAIL_ALREADY_USED = "Email is already used.";

    private static final String ERROR_EMAIL_ALREADY_VERIFIED = "400047";
    private static final String DESC_ERROR_EMAIL_ALREADY_VERIFIED = "Account verified";

    private static final String ERROR_CODE_EXPIRED = "400048";
    private static final String DESC_ERROR_CODE_EXPIRED = "Code was expired";

    private static final String ERROR_INVALID_AUTHORIZATION_CODE = "400049";
    private static final String DESC_ERROR_INVALID_AUTHORIZATION_CODE = "Invalid authorization code";

    private static final String ERROR_INVALID_TOKEN = "400050";
    private static final String DESC_ERROR_INVALID_TOKEN = "Invalid token";

    private static final String ERROR_COMPANY_EXISTED = "400051";
    private static final String DESC_ERROR_COMPANY_EXISTED = "Company existed";

    private static final String ERROR_TAX_CODE_EXISTED = "400052";
    private static final String DESC_ERROR_TAX_CODE_EXISTED = "Tax-code existed";

    private static final String ERROR_INVALID_DISTANCE = "400053";
    private static final String DESC_ERROR_INVALID_DISTANCE = "Invalid distance";

    private static final String ERROR_INVITE_SENT = "400054";
    private static final String DESC_ERROR_INVITE_SENT = "Invite request has been sent";

    private static final String ERROR_DRIVER_INVITED = "400055";
    private static final String DESC_ERROR_DRIVER_INVITED = "Driver has been added";

    private static final String ERROR_DRIVER_JOINED_ANOTHER_COMPANY = "400056";
    private static final String DESC_ERROR_DRIVER_JOINED_ANOTHER_COMPANY = "Driver joined another company";

    private static final String ERROR_DRIVER_NOT_JOINED_COMPANY = "400057";
    private static final String DESC_ERROR_DRIVER_NOT_JOINED_COMPANY = "Driver didn't join any company";

    private static final String ERROR_INVALID_VALUE = "400058";
    private static final String DESC_ERROR_INVALID_VALUE = "Invalid value: '%s'";

    private static final String ERROR_ACCOUNT_NOT_SET_PASSWORD = "400059";
    private static final String DESC_ERROR_ACCOUNT_NOT_SET_PASSWORD = "You haven't set password";

    private static final String ERROR_WRONG_CODE = "400060";
    private static final String DESC_ERROR_WRONG_CODE = "Wrong code";

    private static final String ERROR_INVALID_REDIRECT_URL = "400061";
    private static final String DESC_ERROR_INVALID_REDIRECT_URL = "Invalid redirect URL";

    private static final String ERROR_STUDENT_NOT_JOIN_COURSE = "400062";
    private static final String DESC_ERROR_STUDENT_NOT_JOIN_COURSE = "Student did not join course";

    private static final String ERROR_COURSE_NOT_START = "400063";
    private static final String DESC_ERROR_COURSE_NOT_START = "Course has not started yet";

    private static final String ERROR_COURSE_FINISHED = "400064";
    private static final String DESC_ERROR_COURSE_FINISHED = "Course has finished";

    private static final String ERROR_PAYMENT_EXISTED = "400065";
    private static final String DESC_ERROR_PAYMENT_EXISTED = "Payment existed";

    private static final String ERROR_INVALID_CHECKSUM = "400066";
    private static final String DESC_ERROR_INVALID_CHECKSUM = "Invalid checksum";

    private static final String ERROR_TUTOR_ALREADY_APPROVED = "400067";
    private static final String DESC_ERROR_TUTOR_ALREADY_APPROVED = "Tutor already has been approved";

    private static final String ERROR_TUTOR_ALREADY_DEACTIVE = "400068";
    private static final String DESC_ERROR_TUTOR_ALREADY_DEACTIVE = "Tutor already has been deactived";

    private static final String ERROR_TUTOR_NOT_APPROVED = "400069";
    private static final String DESC_ERROR_TUTOR_NOT_APPROVED = "Tutor has not been approved";

    private static final String ERROR_COURSE_APPROVED = "400070";
    private static final String DESC_ERROR_COURSE_APPROVED = "Course already has been approved";

    private static final String ERROR_TUTOR_NOT_ACCEPT_COURSE = "400071";
    private static final String DESC_ERROR_TUTOR_NOT_ACCEPT_COURSE = "Tutor does not accept this course yet";

    private static final String ERROR_COURSE_DEACTIVED = "400072";
    private static final String DESC_ERROR_COURSE_DEACTIVED = "Course was inactive";

    private static final String ERROR_COURSE_REJECTED = "400073";
    private static final String DESC_ERROR_COURSE_REJECTED = "Course already has been rejected";

    private static final String ERROR_SCHOOL_APPROVED = "400074";
    private static final String DESC_ERROR_SCHOOL_APPROVED = "School already has been approved";

    private static final String ERROR_INVALID_COURSE_TIMELINE = "400075";
    private static final String DESC_ERROR_INVALID_COURSE_TIMELINE = "Invalid course timeline (startTime|endTime)";

    private static final String ERROR_TUTOR_HAS_ASSIGNED_COURSES = "400076";
    private static final String DESC_ERROR_TUTOR_HAS_ASSIGNED_COURSES = "Please assign course(s) for another tutor before remove this tutor";

    private static final String ERROR_PROMOTION_INVALID = "400077";
    private static final String DESC_ERROR_PROMOTION_INVALID = "Promotion Code is invalid";

    private static final String ERROR_PROMOTION_VALUE_INVALID = "400078";
    private static final String DESC_ERROR_PROMOTION_VALUE_INVALID = "Promotion Value is invalid";

    private static final String ERROR_COURSE_NOT_APPROVED = "400079";
    private static final String DESC_ERROR_COURSE_NOT_APPROVED = "Course is not approved";

    private static final String ERROR_SCHOOL_REJECTED = "400080";
    private static final String DESC_ERROR_SCHOOL_REJECTED = "School already has been rejected";

    private static final String ERROR_INVALID_PROCESS_IN_COURSE = "400081";
    private static final String DESC_ERROR_INVALID_PROCESS_IN_COURSE = "Invalid process to apply in Course State";

    private static final String ERROR_COURSE_ALREADY_ACCEPTED = "400082";
    private static final String DESC_ERROR_COURSE_ALREADY_ACCEPTED = "Assigned Course has already been accepted by";

    private static final String ERROR_COURSE_IS_NOT_DRAFT = "400083";
    private static final String DESC_ERROR_COURSE_IS_NOT_DRAFT = "Course can not be deleted";

    private static final String ERROR_FILE_EXISTED = "400084";
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

    private static final String ERROR_GG_EMAIL_NOT_VERIFIED = "401004";
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

    private static final String ERROR_SIP_ACCOUNT_NOT_FOUND = "404003";
    private static final String DESC_ERROR_SIP_ACCOUNT_NOT_FOUND = "Sip account not found";

    private static final String ERROR_CALL_TRANSACTION_NOT_FOUND = "404004";
    private static final String DESC_ERROR_CALL_TRANSACTION_NOT_FOUND = "Call transaction not found";

    private static final String ERROR_PEER_INFORMATION_NOT_FOUND = "404005";
    private static final String DESC_ERROR_PEER_INFORMATION_NOT_FOUND = "Peer Information not found";

    private static final String ERROR_ACCOUNT_WALLET_NOT_FOUND = "404006";
    private static final String DESC_ERROR_ACCOUNT_WALLET_NOT_FOUND = "Account wallet not found";

    private static final String ERROR_RELAY_NOT_FOUND = "404007";
    private static final String DESC_ERROR_RELAY_NOT_FOUND = "Relay not found";

    private static final String ERROR_API_KEY_NOT_FOUND = "404008";
    private static final String DESC_ERROR_API_KEY_NOT_FOUND = "Api key not found";

    private static final String ERROR_GROUP_NOT_FOUND = "404009";
    private static final String DESC_ERROR_GROUP_NOT_FOUND = "Group not found";

    private static final String ERROR_QR_CODE_NOT_FOUND = "404010";
    private static final String DESC_ERROR_QR_CODE_NOT_FOUND = "QR code not found";

    private static final String ERROR_PAYMENT_NOT_FOUND = "404011";
    private static final String DESC_PAYMENT_METHOD_NOT_FOUND = "Payment method not found";

    private static final String ERROR_BILLING_TRANSACTION_NOT_FOUND = "404012";
    private static final String DESC_ERROR_BILLING_TRANSACTION_NOT_FOUND = "Billing transaction not found";

    private static final String ERROR_LOGIN_BUTTON_NOT_FOUND = "404013";
    private static final String DESC_ERROR_LOGIN_BUTTON_NOT_FOUND = "Login button not found";

    private static final String ERROR_CONTACT_NOT_FOUND = "404014";
    private static final String DESC_ERROR_CONTACT_NOT_FOUND = "Contact not found";

    private static final String ERROR_MEMBER_NOT_FOUND = "404015";
    private static final String DESC_ERROR_MEMBER_NOT_FOUND = "Member not found";

    private static final String ERROR_VEHICLE_NOT_FOUND = "404016";
    private static final String DESC_ERROR_VEHICLE_NOT_FOUND = "Vehicle not found";

    private static final String ERROR_COURSE_NOT_FOUND = "404017";
    private static final String DESC_ERROR_COURSE_NOT_FOUND = "Course not found";

    private static final String ERROR_TRANSACTION_NOT_FOUND = "404018";
    private static final String DESC_ERROR_TRANSACTION_NOT_FOUND = "Transaction not found";

    private static final String ERROR_WALLET_NOT_FOUND = "404019";
    private static final String DESC_ERROR_WALLET_NOT_FOUND = "Wallet not found";

    private static final String ERROR_PAYMENT_TRANSACTION_NOT_FOUND = "404020";
    private static final String DESC_PAYMENT_TRANSACTION_NOT_FOUND = "Payment transaction not found";

    private static final String ERROR_COMPANY_NOT_FOUND = "404021";
    private static final String DESC_ERROR_COMPANY_NOT_FOUND = "Company not found";

    private static final String ERROR_LICENSE_NOT_FOUND = "404022";
    private static final String DESC_ERROR_LICENSE_NOT_FOUND = "Driving License not found";

    private static final String ERROR_CERTIFICATE_NOT_FOUND = "404023";
    private static final String DESC_ERROR_CERTIFICATE_NOT_FOUND = "Certificate not found";

    private static final String ERROR_QUALIFICATION_NOT_FOUND = "404024";
    private static final String DESC_ERROR_QUALIFICATION_NOT_FOUND = "Qualification not found";

    private static final String ERROR_SCHOOL_NOT_FOUND = "404025";
    private static final String DESC_ERROR_SCHOOL_NOT_FOUND = "School not found";

    //    private static final String ERROR_PAYMENT_NOT_FOUND = "404011";
    private static final String DESC_PAYMENT_NOT_FOUND = "Payment not found %s";

    private static final String ERROR_NOTI_NOT_FOUND = "404026";
    private static final String DESC_ERROR_NOTI_NOT_FOUND = "Notification not found";

    private static final String ERROR_FILE_NOT_FOUND = "400084";
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
    private static final String ERROR_NOT_ALLOWED_CALL = "406001";

    private static final String ERROR_NOT_ENOUGH_BALANCE = "406002";
    private static final String DESC_ERROR_NOT_ENOUGH_BALANCE = "Account is not enough balance";

    private static final String ERROR_MAX_MEMBER = "406003";
    private static final String DESC_ERROR_MAX_MEMBER = "Account has reached maximum member";

    private static final String ERROR_INVALID_PAYMENT_METHOD = "406004";
    private static final String DESC_ERROR_INVALID_PAYMENT_METHOD = "This payment method is not acceptable";

    private static final String ERROR_PAYMENT_METHOD_IS_DUPLICATED = "406005";
    private static final String DESC_ERROR_PAYMENT_METHOD_IS_DUPLICATED = "This payment method is already added";

    private static final String ERROR_PERMISSION_DENIED = "406006";
    private static final String DESC_ERROR_PERMISSION_DENIED = "Permission denied";

    private static final String ERROR_ACCOUNT_OWING = "406007";
    private static final String DESC_ERROR_ACCOUNT_OWING = "Account is owing";

    private static final String ERROR_TRANSACTION_ALREADY_CREDIT = "406008";
    private static final String DESC_TRANSACTION_ALREADY_CREDIT = "Transaction was already credit to account balance";

    public static final String ERROR_COURSE_OUT_OF_SLOT = "406009";
    public static final String DESC_ERROR_COURSE_OUT_OF_SLOT = "Course is out of slot";

    public static final String ERROR_COURSE_ALREADY_JOINED = "406010";
    public static final String DESC_ERROR_COURSE_ALREADY_JOINED = "Course already joined";

    public static final String ERROR_COURSE_ALREADY_BOOK = "406011";
    public static final String DESC_ERROR_COURSE_ALREADY_BOOKED = "Course already booked";

    public static final String ERROR_ACCOUNT_IS_ON_PAYMENT = "406012";
    public static final String DESC_ERROR_ACCOUNT_IS_ON_PAYMENT = "account is on payment";

    public static final String ERROR_PAYMENT_ALREADY_SUCCESS = "406013";
    public static final String DESC_ERROR_PAYMENT_ALREADY_SUCCESS = "payment already success";

    public static final String ERROR_PAYMENT_EXPIRED = "406014";
    public static final String DESC_ERROR_PAYMENT_EXPIRED = "payment expired";

    public static final String ERROR_PAYMENT_FAILED = "406015";
    public static final String DESC_ERROR_PAYMENT_ALREADY_FAIL = "payment already failed";
    public static final String DESC_ERROR_PAYMENT_FAIL = "payment failed";

    public static final String ERROR_PAYMENT_CANCEL = "406016";
    public static final String DESC_ERROR_PAYMENT_CANCEL = "payment was canceled";

    public static final String ERROR_REGISTER_COURSE = "406017";
    public static final String DESC_ERROR_REGISTER_COURSE = "course_cart is full";

    private static final String ERROR_ACCOUNT_ALREADY_BE_PRINCIPAL = "406018";
    private static final String DESC_ERROR_ACCOUNT_ALREADY_BE_PRINCIPAL = "Account has already been principal of one School";

    private static final String ERROR_SCHOOL_NOT_APPROVED = "406019";
    private static final String DESC_ERROR_SCHOOL_NOT_APPROVED = "School has not been approved";

    private static final String ERROR_TUTOR_ALREADY_INVITED = "406020";
    private static final String DESC_ERROR_TUTOR_ALREADY_INVITED = "Tutor has already been invited";

    private static final String ERROR_TUTOR_NOT_INVITED = "406021";
    private static final String DESC_ERROR_TUTOR_NOT_INVITED = "Tutor has not been invited";

    private static final String ERROR_TUTOR_ALREADY_ACCEPTED = "406022";
    private static final String DESC_ERROR_TUTOR_ALREADY_ACCEPTED = "Tutor has already been accepted invitation";

    private static final String ERROR_TUTOR_ALREADY_DENIED = "406023";
    private static final String DESC_ERROR_TUTOR_ALREADY_DENIED = "Tutor has already been denied invitation";

    public static final String ERROR_ASSIGNED_COURSE_ALREADY_INTERACTED = "406024";
    public static final String DESC_ERROR_ASSIGNED_COURSE_ALREADY_INTERACTED = "Assigned Course has already been adjusted by tutor";

    public static final String ERROR_NOT_ALLOW_START_COURSE = "406025";
    public static final String DESC_ERROR_NOT_ALLOW_START_COURSE = "Not allow to start course";

    public static final String ERROR_TUTOR_ALREADY_KICKED = "406026";
    public static final String DESC_ERROR_TUTOR_ALREADY_KICKED = "Tutor has already been kicked out of school";

    private static final String ERROR_TUTOR_ALREADY_BANNED = "406027";
    private static final String DESC_ERROR_TUTOR_ALREADY_BANNED = "Tutor has already been banned";

    public static final String ERROR_SUBDOMAIN_HAS_BEEN_REGISTERED = "406028";
    public static final String DESC_ERROR_SUBDOMAIN_HAS_BEEN_REGISTERED = "SubDomain has been registered";


    public static final String ERROR_DUPLICATE_CATEGORY_AND_SUBJECT = "406029";
    public static final String DESC_ERROR_DUPLICATE_CATEGORY_AND_SUBJECT = "duplicate category and subject";

    public static final String ERROR_DUPLICATE_GRADE_AND_SUBJECT = "406030";
    public static final String DESC_ERROR_DUPLICATE_GRADE_AND_SUBJECT = "duplicate grade and subject";

    public static final String ERROR_DUPLICATE_CATEGORY = "406031";
    public static final String DESC_ERROR_DUPLICATE_CATEGORY = "duplicate category";
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

    private static final String ERROR_UPDATE_RELAY_FAILED = "500002";
    private static final String DESC_ERROR_UPDATE_RELAY_FAILED = "Update relay failed";

    private static final String ERROR_REGISTER_RELAY_FAILED = "500003";
    private static final String DESC_ERROR_REGISTER_RELAY_FAILED = "Register relay failed";

    private static final String ERROR_UNREGISTER_RELAY_FAILED = "500004";
    private static final String DESC_ERROR_UNREGISTER_RELAY_FAILED = "Unregister relay failed";

    private static final String ERROR_RELAY_START_FAILED = "500005";
    private static final String DESC_ERROR_RELAY_START_FAILED = "Start relay failed";

    private static final String ERROR_RELAY_STOP_FAILED = "500006";
    private static final String DESC_ERROR_RELAY_STOP_FAILED = "Stop relay failed";

    private static final String ERROR_CAN_NOT_CREATE_BRAINTREE_CUSTOMER = "500007";
    private static final String DESC_ERROR_CAN_NOT_CREATE_BRAINTREE_CUSTOMER = "Can not create braintree customer";

    private static final String ERROR_CAN_NOT_MAKE_BRAINTREE_TRANSACTION = "500008";
    private static final String DESC_ERROR_CAN_NOT_MAKE_BRAINTREE_TRANSACTION = "Can not make braintree transaction";

    private static final String ERROR_CAN_NOT_CREATE_BRAINTREE_PAYMENT_METHOD = "500009";
    private static final String DESC_ERROR_CAN_NOT_CREATE_BRAINTREE_PAYMENT_METHOD = "Can not create braintree payment method";

    private static final String ERROR_CAN_NOT_QUERY_VNPAY_TRANSACTION = "5000010";
    private static final String DESC_CAN_NOT_QUERY_VNPAY_TRANSACTION = "Can not query transaction info from VNPay server";

    private static final String ERROR_SECURE_HASH_MISS_MATCHED = "5000011";
    private static final String DESC_SECURE_HASH_MISS_MATCHED = "Secure hash from VNPay is miss-matched";

    private static final String ERROR_AMOUNT_MISS_MATCHED = "5000012";
    private static final String DESC_AMOUNT_MISS_MATCHED = "Amount from VNPay is miss-matched";

    private static final String ERROR_PAYMENT_TRANSACTION_ID_MISS_MATCHED = "5000013";
    private static final String DESC_PAYMENT_TRANSACTION_ID_MISS_MATCHED = "Payment transaction id from VNPay is miss-matched";

    private static final String ERROR_TRANSACTION_TYPE_IS_NOT_CORRECT = "5000014";
    private static final String DESC_TRANSACTION_TYPE_IS_NOT_CORRECT = "Payment transaction type is not correct";

    private static final String ERROR_TRANSACTION_IS_NOT_SUCCESS = "5000015";
    private static final String DESC_TRANSACTION_IS_NOT_SUCCESS = "VNPay transaction is not success";

    /**
     * End 500 zone
     **/

    /**
     * Start VNPay zone
     */

    private static final String VNPAY_ERROR_ORDER_IS_NOT_FOUND = "01";
    private static final String VNPAY_DESC_ORDER_IS_NOT_FOUND = "Your order is not found";

    private static final String VNPAY_ERROR_ORDER_WAS_PROCESS_BEFORE = "02";
    private static final String VNPAY_DESC_ORDER_WAS_PROCESS_BEFORE = "Order was processed before";

    private static final String VNPAY_ERROR_IP_IS_NOT_IN_WHITELIST = "03";
    private static final String VNPAY_DESC_IP_IS_NOT_IN_WHITELIST = "Your IP is not allowed to access this resource";

    private static final String VNPAY_ERROR_INVALID_AMOUNT = "04";
    private static final String VNPAY_DESC_INVALID_AMOUNT = "Invalid amount";

    private static final String VNPAY_ERROR_INVALID_CHECKSUM = "97";
    private static final String VNPAY_DESC_INVALID_CHECKSUM = "Invalid checksum";

    private static final String VNPAY_ERROR_INTERNAL_SERVER_ERROR = "99";
    private static final String VNPAY_DESC_INTERAL_SERVER_ERROR = "Internal server error";

    /**
     * End VNPay zone
     */

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

    public static ServiceException callTransactionNotFound() {
        return new ServiceException(ERROR_CALL_TRANSACTION_NOT_FOUND, DESC_ERROR_CALL_TRANSACTION_NOT_FOUND,
                HttpStatus.NOT_FOUND);
    }

    public static ServiceException peerInformationNotFound() {
        return new ServiceException(ERROR_PEER_INFORMATION_NOT_FOUND, DESC_ERROR_PEER_INFORMATION_NOT_FOUND,
                HttpStatus.NOT_FOUND);
    }

    public static ServiceException sipAccountNotFound() {
        return new ServiceException(ERROR_SIP_ACCOUNT_NOT_FOUND, DESC_ERROR_SIP_ACCOUNT_NOT_FOUND,
                HttpStatus.NOT_FOUND);
    }

    public static ServiceException internalServerError() {
        return new ServiceException(INTERNAL_SERVER_ERROR, DESC_INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException methodIsNotAllowed() {
        return new ServiceException(ERROR_METHOD_NOT_ALLOWED, DESC_ERROR_METHOD_NOT_ALLOWED,
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    public static ServiceException invalidShortname() {
        return new ServiceException(ERROR_INVALID_SHORT_NAME, DESC_ERROR_INVALID_SHORT_NAME, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException shortnameExists() {
        return new ServiceException(ERROR_SHORT_NAME_EXISTS, DESC_ERROR_SHORT_NAME_EXISTS, HttpStatus.BAD_REQUEST);
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

    public static ServiceException accountWalletNotFound() {
        return new ServiceException(ERROR_ACCOUNT_WALLET_NOT_FOUND, DESC_ERROR_ACCOUNT_WALLET_NOT_FOUND,
                HttpStatus.NOT_FOUND);
    }

    public static ServiceException relayNotFound() {
        return new ServiceException(ERROR_RELAY_NOT_FOUND, DESC_ERROR_RELAY_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException updateRelayFailed() {
        return new ServiceException(ERROR_UPDATE_RELAY_FAILED, DESC_ERROR_UPDATE_RELAY_FAILED,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException invalidWalletNameLength() {
        return new ServiceException(ERROR_INVALID_WALLET_NAME_LENGTH, DESC_ERROR_INVALID_WALLET_NAME_LENGTH,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException invalidWalletNameUppercase() {
        return new ServiceException(ERROR_INVALID_WALLET_NAME_UPPERCASE, DESC_ERROR_INVALID_WALLET_NAME_UPPERCASE,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException invalidWalletNameExists() {
        return new ServiceException(ERROR_INVALID_WALLET_NAME_EXISTS, DESC_ERROR_INVALID_WALLET_NAME_EXISTS,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException invalidWalletName() {
        return new ServiceException(ERROR_INVALID_WALLET_NAME, DESC_ERROR_INVALID_WALLET_NAME, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException notAllowedCall(String message) {
        return new ServiceException(ERROR_NOT_ALLOWED_CALL, message, HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException invalidEmailOrPassword() {
        return new ServiceException(ERROR_INVALID_EMAIL_OR_PASSWORD, DESC_ERROR_INVALID_EMAIL_OR_PASSWORD,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException invalidRefreshToken() {
        return new ServiceException(ERROR_INVALID_REFRESH_TOKEN, DESC_ERROR_INVALID_REFRESH_TOKEN,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException registerRelayFailed() {
        return new ServiceException(ERROR_REGISTER_RELAY_FAILED, DESC_ERROR_REGISTER_RELAY_FAILED,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException unregisterRelayFailed() {
        return new ServiceException(ERROR_UNREGISTER_RELAY_FAILED, DESC_ERROR_UNREGISTER_RELAY_FAILED,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException startRelayFailed() {
        return new ServiceException(ERROR_RELAY_START_FAILED, DESC_ERROR_RELAY_START_FAILED,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException stopRelayFailed() {
        return new ServiceException(ERROR_RELAY_STOP_FAILED, DESC_ERROR_RELAY_STOP_FAILED,
                HttpStatus.INTERNAL_SERVER_ERROR);
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

    public static ServiceException groupAlreadyExists() {
        return new ServiceException(ERROR_GROUP_ALREADY_EXISTS, DESC_ERROR_GROUP_ALREADY_EXISTS,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException accountAlreadyExists() {
        return new ServiceException(ERROR_ACCOUNT_ALREADY_EXISTS, DESC_ERROR_ACCOUNT_ALREADY_EXISTS,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException groupNotFound() {
        return new ServiceException(ERROR_GROUP_NOT_FOUND, DESC_ERROR_GROUP_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException aliasAlreadyExists() {
        return new ServiceException(ERROR_ALIAS_ALREADY_EXISTS, DESC_ERROR_ALIAS_ALREADY_EXISTS,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException aliasNotContainSpace() {
        return new ServiceException(ERROR_ALIAS_NOT_CONTAIN_SPACE, DESC_ERROR_ALIAS_NOT_CONTAIN_SPACE,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException qrCodeNotFound() {
        return new ServiceException(ERROR_QR_CODE_NOT_FOUND, DESC_ERROR_QR_CODE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException paymentMethodNotFound() {
        return new ServiceException(ERROR_PAYMENT_NOT_FOUND, DESC_PAYMENT_METHOD_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException paymentNotFound(String paymentId) {
        return new ServiceException(ERROR_PAYMENT_NOT_FOUND, String.format(DESC_PAYMENT_NOT_FOUND, paymentId), HttpStatus.NOT_FOUND);
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

    public static ServiceException accountInCall() {
        return new ServiceException(ERROR_ACCOUNT_IN_CALL, DESC_ERROR_ACCOUNT_IN_CALL, HttpStatus.BAD_REQUEST);
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

    public static ServiceException billingTransactionNotFound() {
        return new ServiceException(ERROR_BILLING_TRANSACTION_NOT_FOUND, DESC_ERROR_BILLING_TRANSACTION_NOT_FOUND,
                HttpStatus.NOT_FOUND);
    }

    public static ServiceException invalidPaymentMethod() {
        return new ServiceException(ERROR_INVALID_PAYMENT_METHOD, DESC_ERROR_INVALID_PAYMENT_METHOD,
                HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException cannotCreateBraintreeCustomer() {
        return new ServiceException(ERROR_CAN_NOT_CREATE_BRAINTREE_CUSTOMER,
                DESC_ERROR_CAN_NOT_CREATE_BRAINTREE_CUSTOMER, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException cannotMakeBraintreeTransaction() {
        return new ServiceException(ERROR_CAN_NOT_MAKE_BRAINTREE_TRANSACTION,
                DESC_ERROR_CAN_NOT_MAKE_BRAINTREE_TRANSACTION, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException dragVerifyFail() {
        return new ServiceException(ERROR_DRAG_VERIFY_FAIL, DESC_ERROR_DRAG_VERIFY_FAIL, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException cannotCreateBraintreePaymentMethod() {
        return new ServiceException(ERROR_CAN_NOT_CREATE_BRAINTREE_PAYMENT_METHOD,
                DESC_ERROR_CAN_NOT_CREATE_BRAINTREE_PAYMENT_METHOD, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException paymentMethodIsDuplicated() {
        return new ServiceException(ERROR_PAYMENT_METHOD_IS_DUPLICATED, DESC_ERROR_PAYMENT_METHOD_IS_DUPLICATED,
                HttpStatus.NOT_ACCEPTABLE);
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

    public static ServiceException memberExisted() {
        return new ServiceException(ERROR_MEMBER_EXISTS, DESC_ERROR_MEMBER_EXISTS, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException memberNotFound() {
        return new ServiceException(ERROR_MEMBER_NOT_FOUND, DESC_ERROR_MEMBER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException missingCompanyName() {
        return new ServiceException(ERROR_MISSING_COMPANY_NAME, DESC_ERROR_MISSING_COMPANY_NAME,
                HttpStatus.BAD_REQUEST);
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

    public static ServiceException vehicleNotFound() {
        return new ServiceException(ERROR_VEHICLE_NOT_FOUND, DESC_ERROR_VEHICLE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException courseNotFound() {
        return new ServiceException(ERROR_COURSE_NOT_FOUND, DESC_ERROR_COURSE_NOT_FOUND,
                HttpStatus.NOT_FOUND);
    }

    public static ServiceException courseNotFound(String courseId) {
        return new ServiceException(ERROR_COURSE_NOT_FOUND, DESC_ERROR_COURSE_NOT_FOUND + ": " + courseId,
                HttpStatus.NOT_FOUND);
    }

    public static ServiceException courseIsOutOfSlot() {
        return new ServiceException(ERROR_COURSE_OUT_OF_SLOT, DESC_ERROR_COURSE_OUT_OF_SLOT,
                HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException courseAlreadyJoined() {
        return new ServiceException(ERROR_COURSE_ALREADY_JOINED, DESC_ERROR_COURSE_ALREADY_JOINED,
                HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException courseAlreadyBook() {
        return new ServiceException(ERROR_COURSE_ALREADY_BOOK, DESC_ERROR_COURSE_ALREADY_BOOKED,
                HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException accountIsOnPayment() {
        return new ServiceException(ERROR_ACCOUNT_IS_ON_PAYMENT, DESC_ERROR_ACCOUNT_IS_ON_PAYMENT,
                HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException paymentAlreadySuccess() {
        return new ServiceException(ERROR_PAYMENT_ALREADY_SUCCESS, DESC_ERROR_PAYMENT_ALREADY_SUCCESS,
                HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException paymentExpired() {
        return new ServiceException(ERROR_PAYMENT_EXPIRED, DESC_ERROR_PAYMENT_EXPIRED,
                HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException paymentAlreadyFailed() {
        return new ServiceException(ERROR_PAYMENT_FAILED, DESC_ERROR_PAYMENT_ALREADY_FAIL,
                HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException paymentFailed() {
        return new ServiceException(ERROR_PAYMENT_FAILED, DESC_ERROR_PAYMENT_FAIL,
                HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException paymentWasCanceled() {
        return new ServiceException(ERROR_PAYMENT_CANCEL, DESC_ERROR_PAYMENT_CANCEL,
                HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException courseCartIsFull() {
        return new ServiceException(ERROR_REGISTER_COURSE, DESC_ERROR_REGISTER_COURSE,
                HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException invalidAmount() {
        return new ServiceException(ERROR_INVALID_AMOUNT, DESC_ERROR_INVALID_AMOUNT, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException billingAlreadySplit() {
        return new ServiceException(ERROR_BILLING_ALREADY_SPLIT, DESC_ERROR_BILLING_ALREADY_SPLIT,
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

    public static ServiceException walletNotFound() {
        return new ServiceException(ERROR_WALLET_NOT_FOUND, DESC_ERROR_WALLET_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException paymentTransactionNotFound() {
        return new ServiceException(ERROR_PAYMENT_TRANSACTION_NOT_FOUND, DESC_PAYMENT_TRANSACTION_NOT_FOUND,
                HttpStatus.NOT_FOUND);
    }

    public static ServiceException cannotQueryVNPayTransaction() {
        return new ServiceException(ERROR_CAN_NOT_QUERY_VNPAY_TRANSACTION, DESC_CAN_NOT_QUERY_VNPAY_TRANSACTION,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException secureHashMissMatched() {
        return new ServiceException(ERROR_SECURE_HASH_MISS_MATCHED, DESC_SECURE_HASH_MISS_MATCHED,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException amountMissMatched() {
        return new ServiceException(ERROR_AMOUNT_MISS_MATCHED, DESC_AMOUNT_MISS_MATCHED,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException paymentTransactionIdMissMatched() {
        return new ServiceException(ERROR_PAYMENT_TRANSACTION_ID_MISS_MATCHED, DESC_PAYMENT_TRANSACTION_ID_MISS_MATCHED,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException paymentTransactionTypeIsNotCorrect() {
        return new ServiceException(ERROR_TRANSACTION_TYPE_IS_NOT_CORRECT, DESC_TRANSACTION_TYPE_IS_NOT_CORRECT,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException paymentTransactionIsNotSuccess() {
        return new ServiceException(ERROR_TRANSACTION_IS_NOT_SUCCESS, DESC_TRANSACTION_IS_NOT_SUCCESS,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException invalidAuthorizationCode() {
        return new ServiceException(ERROR_INVALID_AUTHORIZATION_CODE, DESC_ERROR_INVALID_AUTHORIZATION_CODE,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException invalidToken() {
        return new ServiceException(ERROR_INVALID_TOKEN, DESC_ERROR_INVALID_TOKEN, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException permissionDenied() {
        return new ServiceException(ERROR_PERMISSION_DENIED, DESC_ERROR_PERMISSION_DENIED, HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException companyExisted() {
        return new ServiceException(ERROR_COMPANY_EXISTED, DESC_ERROR_COMPANY_EXISTED, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException taxCodeExisted() {
        return new ServiceException(ERROR_TAX_CODE_EXISTED, DESC_ERROR_TAX_CODE_EXISTED, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException companyNotFound() {
        return new ServiceException(ERROR_COMPANY_NOT_FOUND, DESC_ERROR_COMPANY_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException invalidDistance() {
        return new ServiceException(ERROR_INVALID_DISTANCE, DESC_ERROR_INVALID_DISTANCE, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException accountOwing() {
        return new ServiceException(ERROR_ACCOUNT_OWING, DESC_ERROR_ACCOUNT_OWING, HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException inviteHasBeenSent() {
        return new ServiceException(ERROR_INVITE_SENT, DESC_ERROR_INVITE_SENT, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException driverHasBeenAdded() {
        return new ServiceException(ERROR_DRIVER_INVITED, DESC_ERROR_DRIVER_INVITED, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException driverJoinedAnotherCompany() {
        return new ServiceException(ERROR_DRIVER_JOINED_ANOTHER_COMPANY, DESC_ERROR_DRIVER_JOINED_ANOTHER_COMPANY,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException driverNotJoinedCompany() {
        return new ServiceException(ERROR_DRIVER_NOT_JOINED_COMPANY, DESC_ERROR_DRIVER_NOT_JOINED_COMPANY,
                HttpStatus.BAD_REQUEST);
    }

    public static ServiceException transactionAlreadyCredit() {
        return new ServiceException(ERROR_TRANSACTION_ALREADY_CREDIT, DESC_TRANSACTION_ALREADY_CREDIT,
                HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException drivingLicenseNotFound() {
        return new ServiceException(ERROR_LICENSE_NOT_FOUND, DESC_ERROR_LICENSE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException accountNotSetPassword() {
        return new ServiceException(ERROR_ACCOUNT_NOT_SET_PASSWORD, DESC_ERROR_ACCOUNT_NOT_SET_PASSWORD, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException certificateNotFound() {
        return new ServiceException(ERROR_CERTIFICATE_NOT_FOUND, DESC_ERROR_CERTIFICATE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException qualificationNotFound() {
        return new ServiceException(ERROR_QUALIFICATION_NOT_FOUND, DESC_ERROR_QUALIFICATION_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException schoolNotFound() {
        return new ServiceException(ERROR_SCHOOL_NOT_FOUND, DESC_ERROR_SCHOOL_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException wrongCode() {
        return new ServiceException(ERROR_WRONG_CODE, DESC_ERROR_WRONG_CODE, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException invalidRedirectUrl() {
        return new ServiceException(ERROR_INVALID_REDIRECT_URL, DESC_ERROR_INVALID_REDIRECT_URL, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException studentNotJoinCourse() {
        return new ServiceException(ERROR_STUDENT_NOT_JOIN_COURSE, DESC_ERROR_STUDENT_NOT_JOIN_COURSE, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException courseNotStart() {
        return new ServiceException(ERROR_COURSE_NOT_START, DESC_ERROR_COURSE_NOT_START, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException courseIsNotDraft() {
        return new ServiceException(ERROR_COURSE_IS_NOT_DRAFT, DESC_ERROR_COURSE_IS_NOT_DRAFT, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException courseFinished() {
        return new ServiceException(ERROR_COURSE_FINISHED, DESC_ERROR_COURSE_FINISHED, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException paymentExisted() {
        return new ServiceException(ERROR_PAYMENT_EXISTED, DESC_ERROR_PAYMENT_EXISTED, HttpStatus.BAD_REQUEST);
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

    public static ServiceException tutorNotApproved() {
        return new ServiceException(ERROR_TUTOR_NOT_APPROVED, DESC_ERROR_TUTOR_NOT_APPROVED, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException accountAlreadyBePrincipal() {
        return new ServiceException(ERROR_ACCOUNT_ALREADY_BE_PRINCIPAL, DESC_ERROR_ACCOUNT_ALREADY_BE_PRINCIPAL, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException schoolNotApproved() {
        return new ServiceException(ERROR_SCHOOL_NOT_APPROVED, DESC_ERROR_SCHOOL_NOT_APPROVED, HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException tutorAlreadyInvited() {
        return new ServiceException(ERROR_TUTOR_ALREADY_INVITED, DESC_ERROR_TUTOR_ALREADY_INVITED, HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException tutorNotInvited() {
        return new ServiceException(ERROR_TUTOR_NOT_INVITED, DESC_ERROR_TUTOR_NOT_INVITED, HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException tutorAlreadyAccepted() {
        return new ServiceException(ERROR_TUTOR_ALREADY_ACCEPTED, DESC_ERROR_TUTOR_ALREADY_ACCEPTED, HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException tutorAlreadyDenied() {
        return new ServiceException(ERROR_TUTOR_ALREADY_DENIED, DESC_ERROR_TUTOR_ALREADY_DENIED, HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException accountAlreadyBanned() {
        return new ServiceException(ERROR_TUTOR_ALREADY_BANNED, DESC_ERROR_TUTOR_ALREADY_BANNED, HttpStatus.NOT_ACCEPTABLE);
    }

    public static ServiceException invalidProcessInCourse(String message) {
        return new ServiceException(ERROR_INVALID_PROCESS_IN_COURSE, DESC_ERROR_INVALID_PROCESS_IN_COURSE + ": " + message, HttpStatus.BAD_REQUEST);
    }

    public static ServiceException fileNotFound() {
        return new ServiceException(ERROR_FILE_NOT_FOUND, DESC_ERROR_FILE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ServiceException fileExisted() {
        return new ServiceException(ERROR_FILE_EXISTED, DESC_ERROR_FILE_EXISTED, HttpStatus.BAD_REQUEST);
    }
}
