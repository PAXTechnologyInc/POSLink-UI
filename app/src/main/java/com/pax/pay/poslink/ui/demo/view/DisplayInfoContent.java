package com.pax.pay.poslink.ui.demo.view;


import com.pax.us.pay.ui.constant.entry.EntryExtraData;

import java.util.LinkedHashMap;
import java.util.Map;

public class DisplayInfoContent {
    public static final Map<String, String> FEE_MAP = new LinkedHashMap<>();

    static {
        FEE_MAP.put(EntryExtraData.PARAM_TITLE, null);
        FEE_MAP.put(EntryExtraData.PARAM_USER_MESSAGE, null);
        FEE_MAP.put(EntryExtraData.PARAM_PRIMARY_AMOUNT, "Primary Amount : ");
        FEE_MAP.put(EntryExtraData.PARAM_CONVENIENCE_FEE, "Convenience Fee : ");
        FEE_MAP.put(EntryExtraData.PARAM_SERVICE_FEE, "Service Fee : ");
        FEE_MAP.put(EntryExtraData.PARAM_TOTAL_FEE, "Total Amount : ");
    }

    public static final Map<String, String> BALANCE_MAP = new LinkedHashMap<>();

    static {
        BALANCE_MAP.put(EntryExtraData.PARAM_CASH_BENEFIT_BALANCE, "Cash Benefit : ");
        BALANCE_MAP.put(EntryExtraData.PARAM_FOOD_STAMPS_BALANCE, "Food Stamp : ");
        BALANCE_MAP.put(EntryExtraData.PARAM_BALANCE, "Balance : ");
    }

    public static final Map<String, String> TRANS_MAP = new LinkedHashMap<>();

    static {
//        public static final String PARAM_TRANS_MODE = "transMode";
//        public static final String PARAM_EDC_TYPE = "edcType";
//        public static final String PARAM_TRANSYPE = "transype";
//        public static final String PARAM_SUB_TRANS_TYPE = "subTransType";
//        public static final String PARAM_TRANS_NUMBER = "transNumber";
//        public static final String PARAM_CARD_NUMBER = "cardNumber";
//        public static final String PARAM_BATCH_NUMBER = "batchNumber";
//        public static final String PARAM_ORDER_NUMBER = "orderNumber";
//        public static final String PARAM_TIP1 = "tip1";
//        public static final String PARAM_TIP2 = "tip2";
//        public static final String PARAM_TIP3 = "tip3";
//        public static final String PARAM_BASE_AMOUNT = "baseAmount";
//        public static final String PARAM_TOTAL_AMOUNT = "totalAmount";
//        public static final String PARAM_APPROVED_AMOUNT = "approvedAmount";
//        public static final String PARAM_BASE_POINTS = "basePoints";
//        public static final String PARAM_TOTAL_POINTS = "totalPoints";
//        public static final String PARAM_APPROVED_POINTS = "approvedPoints";
//        public static final String PARAM_TAX = "tax";
//        public static final String PARAM_CASH_BACK = "cashBack";
//        public static final String PARAM_MERCHANT_FEE = "merchantFee";
//        public static final String PARAM_SURCHARGE_FEE = "surchargeFee";
//        public static final String PARAM_ADDITION_FEE = "additionFee";
//        public static final String PARAM_FSA_AMOUNT = "fsaAmount";
//        public static final String PARAM_HEALTH_AMOUNT = "healthAmount";
//        public static final String PARAM_CLINIC_AMOUNT = "clinicAmount";
//        public static final String PARAM_DENTAL_AMOUNT = "dentalAmount";
//        public static final String PARAM_COPAY_AMOUNT = "copayAmount";
//        public static final String PARAM_RX_AMOUNT = "rxAmount";
//        public static final String PARAM_VISION_AMOUNT = "visionAmount";
//        public static final String PARAM_TRANSIT_AMOUNT = "transitAmount";
//        public static final String PARAM_STATUS = "status";
//        public static final String PARAM_CLERK_ID = "clerkID";
//        public static final String PARAM_CLERK_NAME = "clerkName";
//        public static final String PARAM_INVOICE_NUMBER = "invoiceNumber";
//        public static final String PARAM_PO_NUMBER = "poNumber";
//        public static final String PARAM_TABLE_NUMBER = "tableNumber";
//        public static final String PARAM_GUESTS_NUMBER = "guestsNumber";
//        public static final String PARAM_CUST_CODE = "custCode";
//        public static final String PARAM_MERCHANT_TAX_ID = "merchantTaxID";
//        public static final String PARAM_PROD_DESC = "prodDesc";
//        public static final String PARAM_TAX_EXEMPT_ID = "taxExemptID";
//        public static final String PARAM_REF_NUMBER = "refNumber";
//        public static final String PARAM_AUTH_CODE = "authCode";
//        public static final String PARAM_RESPONSE_STATUS = "responseStatus";
//        public static final String PARAM_AVS_RESPONSE = "avsResponse";
//        public static final String PARAM_ENTRY_MODE = "entryMode";
//        public static final String PARAM_FOOD_STAMPS_BALANCE = "foodStampsBalance";
//        public static final String PARAM_CASH_BENEFIT_BALANCE = "cashBenefitBalance";


        TRANS_MAP.put(EntryExtraData.PARAM_TRANS_MODE, "mode");
        TRANS_MAP.put(EntryExtraData.PARAM_EDC_TYPE, "EDC Type : ");
        TRANS_MAP.put(EntryExtraData.PARAM_TRANS_TYPE, "Trans Type : ");
    }

}
