package com.gfs.domain.constant;

/**
 * @author KhoaVT
 */
public class PaypalConstant {

    public static final String PAYMENT_METHOD_BEOWULF_WALLET = "beowulf_wallet";
    public static final String ENCRYPTION_INIT_VECTOR = "h56vdsf32iu42dda";

    // For PaymentMethod
    public static final String PAYMENT_METHOD_TYPE_PAYPAL = "paypal";
    public static final String PAYMENT_METHOD_TYPE_CREDIT_CARD = "credit_card";

    // For Transaction
    public static final String TRANSACTION_STATUS_NONE = "none";
    public static final String TRANSACTION_STATUS_START = "start";
    public static final String TRANSACTION_STATUS_END = "end";
    public static final String TRANSACTION_STATUS_COST = "cost";
    public static final String TRANSACTION_STATUS_SUCCESS = "success";
    public static final String TRANSACTION_STATUS_PAYMENT_FAIL = "failed";
    public static final String TRANSACTION_STATUS_PAY_CREATE = "pay_create";
    public static final String TRANSACTION_STATUS_PAY_FINISH = "pay_finish";
    public static final String TRANSACTION_STATUS_PAY_FAIL = "pay_failed";
    public static final String TRANSACTION_STATUS_PAY_PENDING = "pay_pending";

    // For payment
    public static final String PAYMENT_STATUS_DELETED = "deleted";
    public static final String PAYMENT_STATUS_ACTIVE = "active";

    // For order status
    public static final String ORDER_STATUS_CREATED = "CREATED";
    public static final String ORDER_STATUS_SAVED = "SAVED";
    public static final String ORDER_STATUS_APPROVED = "APPROVED";
    public static final String ORDER_STATUS_VOIDED = "VOIDED";
    public static final String ORDER_STATUS_COMPLETED = "COMPLETED";

}
