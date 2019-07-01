package com.pax.pay.poslink.ui.demo.view;

import com.pax.us.pay.ui.constant.entry.EntryConfirmation;

import java.util.LinkedHashMap;
import java.util.Map;

public class DisplayInfoContent {
    public static final Map<String, String> FEE_MAP = new LinkedHashMap<>();

    static {
        FEE_MAP.put(EntryConfirmation.PARA_TITLE, null);
        FEE_MAP.put(EntryConfirmation.PARA_USER_MESSAGE, null);
        FEE_MAP.put(EntryConfirmation.PARA_PRIMARY_AMOUNT, "Primary Amount : ");
        FEE_MAP.put(EntryConfirmation.PARA_CONVENIENCE_FEE, "Convenience Fee : ");
        FEE_MAP.put(EntryConfirmation.PARA_SERVICE_FEE, "Service Fee : ");
        FEE_MAP.put(EntryConfirmation.PARA_TOTAL_FEE, "Total Amount : ");
    }

    public static final Map<String, String> BALANCE_MAP = new LinkedHashMap<>();

    static {
        BALANCE_MAP.put(EntryConfirmation.PARA_CASH_BENEFIT, "Cash Benefit : ");
        BALANCE_MAP.put(EntryConfirmation.PARA_FOOD_STAMP, "Food Stamp : ");
        BALANCE_MAP.put(EntryConfirmation.PARA_BALANCE, "Balance : ");
    }

    public static final Map<String, String> TRANS_MAP = new LinkedHashMap<>();

    static {
//        public static final String PARA_TRANS_MODE = "transMode";
//        public static final String PARA_EDC_TYPE = "edcType";
//        public static final String PARA_TRANSYPE = "transype";
//        public static final String PARA_SUB_TRANS_TYPE = "subTransType";
//        public static final String PARA_TRANS_NUMBER = "transNumber";
//        public static final String PARA_CARD_NUMBER = "cardNumber";
//        public static final String PARA_BATCH_NUMBER = "batchNumber";
//        public static final String PARA_ORDER_NUMBER = "orderNumber";
//        public static final String PARA_TIP1 = "tip1";
//        public static final String PARA_TIP2 = "tip2";
//        public static final String PARA_TIP3 = "tip3";
//        public static final String PARA_BASE_AMOUNT = "baseAmount";
//        public static final String PARA_TOTAL_AMOUNT = "totalAmount";
//        public static final String PARA_APPROVED_AMOUNT = "approvedAmount";
//        public static final String PARA_BASE_POINTS = "basePoints";
//        public static final String PARA_TOTAL_POINTS = "totalPoints";
//        public static final String PARA_APPROVED_POINTS = "approvedPoints";
//        public static final String PARA_TAX = "tax";
//        public static final String PARA_CASH_BACK = "cashBack";
//        public static final String PARA_MERCHANT_FEE = "merchantFee";
//        public static final String PARA_SURCHARGE_FEE = "surchargeFee";
//        public static final String PARA_ADDITION_FEE = "additionFee";
//        public static final String PARA_FSA_AMOUNT = "fsaAmount";
//        public static final String PARA_HEALTH_AMOUNT = "healthAmount";
//        public static final String PARA_CLINIC_AMOUNT = "clinicAmount";
//        public static final String PARA_DENTAL_AMOUNT = "dentalAmount";
//        public static final String PARA_COPAY_AMOUNT = "copayAmount";
//        public static final String PARA_RX_AMOUNT = "rxAmount";
//        public static final String PARA_VISION_AMOUNT = "visionAmount";
//        public static final String PARA_TRANSIT_AMOUNT = "transitAmount";
//        public static final String PARA_STATUS = "status";
//        public static final String PARA_CLERK_ID = "clerkID";
//        public static final String PARA_CLERK_NAME = "clerkName";
//        public static final String PARA_INVOICE_NUMBER = "invoiceNumber";
//        public static final String PARA_PO_NUMBER = "poNumber";
//        public static final String PARA_TABLE_NUMBER = "tableNumber";
//        public static final String PARA_GUESTS_NUMBER = "guestsNumber";
//        public static final String PARA_CUST_CODE = "custCode";
//        public static final String PARA_MERCHANT_TAX_ID = "merchantTaxID";
//        public static final String PARA_PROD_DESC = "prodDesc";
//        public static final String PARA_TAX_EXEMPT_ID = "taxExemptID";
//        public static final String PARA_REF_NUMBER = "refNumber";
//        public static final String PARA_AUTH_CODE = "authCode";
//        public static final String PARA_RESPONSE_STATUS = "responseStatus";
//        public static final String PARA_AVS_RESPONSE = "avsResponse";
//        public static final String PARA_ENTRY_MODE = "entryMode";
//        public static final String PARA_FOOD_STAMPS_BALANCE = "foodStampsBalance";
//        public static final String PARA_CASH_BENEFIT_BALANCE = "cashBenefitBalance";


        TRANS_MAP.put(EntryConfirmation.PARA_TRANS_MODE, "mode");
        TRANS_MAP.put(EntryConfirmation.PARA_EDC_TYPE, "EDC Type : ");
        TRANS_MAP.put(EntryConfirmation.PARA_TRANSYPE, "Trans Type : ");
    }

}
