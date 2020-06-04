package com.gfs.domain.constant;

import java.util.HashMap;

/**
 * @author KhoaVT
 */
public class VNPayConstant {

    public static final String VNPAY_HASH_SECRET_INIT_VECTOR = "khagsdasgda67342";

    // VNPAY transaction status

    public static final String VNPAY_PAYMENT_RESPONSE_CODE_SUCCESS = "00";

    public static final String VNPAY_PAY_COMMAND = "pay";

    public static final String VNPAY_QUERYDR_COMMAND = "querydr";

    public static final String VNPAY_BACK_URL = "https://payment.beowulfchain.com";

    public static final String VNPAY_CURRENCY_CODE = "VND";

    public static final String VNPAY_LOCALE = "vn";

    public static final String VNPAY_GATEWAY_NAME = "VNPAY";

    public static final String VNPAY_ORDER_TYPE_TOPUP = "250006";

    public static final String VNPAY_ORDER_TYPE_BILL_PAYMENT = "240000";

    public static final String VNPAY_TRANSACTON_TYPE_PAYMENT = "01";

    public static final String VNPAY_TRANSACTON_TYPE_REFUND_COMPLETE = "02";

    public static final String VNPAY_TRANSACTON_TYPE_REFUND_PARTIAL = "03";

    public static final String VNPAY_PROCCESS_PAYMENT_SUCCESSFULLY_CODE = "00";

    public static final String VNPAY_PROCCESS_PAYMENT_SUCCESSFULLY_MESSAGE = "Payment was processed successfully";

    public static final HashMap<String, String> bankCodeHashMap;

    static {
        bankCodeHashMap = new HashMap<String, String>();
        bankCodeHashMap.put("NCB", "Vietcombank");
        bankCodeHashMap.put("TCB", "Techcombank");
        bankCodeHashMap.put("VIB", "VIB Bank");
        bankCodeHashMap.put("ABB", "ABBank");
        bankCodeHashMap.put("STB", "Sacombank");
        bankCodeHashMap.put("MSB", "Maritime Bank");
        bankCodeHashMap.put("NVB", "Navibank");
        bankCodeHashMap.put("CTG", "Vietinbank");
        bankCodeHashMap.put("DAB", "DongABank");
        bankCodeHashMap.put("HDB", "HDBank");
        bankCodeHashMap.put("VAB", "VietABank");
        bankCodeHashMap.put("VPB", "VPBank");
        bankCodeHashMap.put("ACB", "ACB");
        bankCodeHashMap.put("MB", "MBBank");
        bankCodeHashMap.put("GPB", "GPBank");
        bankCodeHashMap.put("EIB", "Eximbank");
        bankCodeHashMap.put("OJB", "OceanBank");
        bankCodeHashMap.put("NASB", "BacABank");
        bankCodeHashMap.put("OCB", "OricomBank");
        bankCodeHashMap.put("TPB", "TPBank");
        bankCodeHashMap.put("LPB", "LienVietPostBank");
        bankCodeHashMap.put("SEAB", "Seabank");
        bankCodeHashMap.put("BIDV", "BIDV");
        bankCodeHashMap.put("VARB", "AgriBank");
        bankCodeHashMap.put("BVB", "BaoVietBank");
        bankCodeHashMap.put("SHB", "SHB");
        bankCodeHashMap.put("KLB", "KienLongBank");
        bankCodeHashMap.put("SCB", "SCB");
    }
}
