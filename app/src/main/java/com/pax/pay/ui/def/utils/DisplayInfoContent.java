package com.pax.pay.ui.def.utils;


import com.pax.pay.ui.def.EnterCardAllDigitsActivity;
import com.pax.pay.ui.def.EnterCardLast4DigitsActivity;
import com.pax.pay.ui.def.EnterPinActivity;
import com.pax.pay.ui.def.EnterVCodeActivity;
import com.pax.pay.ui.def.R;
import com.pax.pay.ui.def.SearchCardActivity;
import com.pax.pay.ui.def.constant.EdcTransContract;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;

import java.util.LinkedHashMap;
import java.util.Map;

//import com.paxus.data.contract.internal.EdcTransContract;

public class DisplayInfoContent {
//    public static final Map<String, String> FEE_MAP = new LinkedHashMap<>();
//
//    static {
//        FEE_MAP.put(EntryExtraData.PARAM_USER_MESSAGE, null);
//        FEE_MAP.put(EntryExtraData.PARAM_PRIMARY_AMOUNT, "Primary Amount : ");
//        FEE_MAP.put(EntryExtraData.PARAM_CONVENIENCE_FEE, "Convenience Fee : ");
//        FEE_MAP.put(EntryExtraData.PARAM_SERVICE_FEE, "Service Fee : ");
//        FEE_MAP.put(EntryExtraData.PARAM_TOTAL_FEE, "Total Amount : ");
//    }
//
//    public static final Map<String, String> BALANCE_MAP = new LinkedHashMap<>();
//
//    static {
//        BALANCE_MAP.put(EntryExtraData.PARAM_CASH_BENEFIT_BALANCE, "Cash Benefit : ");
//        BALANCE_MAP.put(EntryExtraData.PARAM_FOOD_STAMPS_BALANCE, "Food Stamp : ");
//        BALANCE_MAP.put(EntryExtraData.PARAM_BALANCE, "Balance : ");
//    }

//    public static final Map<String, String> TRANS_MAP = new LinkedHashMap<>();
//
//    static {
//        TRANS_MAP.put(EntryExtraData.PARAM_TRANS_MODE, "mode");
//        TRANS_MAP.put(EntryExtraData.PARAM_EDC_TYPE, "EDC Type : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_TRANS_TYPE, "Trans. Type : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_SUB_TRANS_TYPE, "Sub Trans. Type : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_TRANS_NUMBER, "Trans. No. : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_CARD_NUMBER, "Card No. : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_BATCH_NUMBER, "Batch No. : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_ORDER_NUMBER, "Order No. : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_TIP1, "Tip1 : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_TIP2, "Tip2 : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_TIP3, "Tip3 : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_BASE_AMOUNT, "Base Amount : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_TOTAL_AMOUNT, "Total Amount : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_APPROVED_AMOUNT, "Approved Amount : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_BASE_POINTS, "Base Points : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_TOTAL_POINTS, "Total Points : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_APPROVED_POINTS, "Approved Points : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_TAX, "Tax : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_CASH_BACK, "Cashback : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_MERCHANT_FEE, "Merchant Fee : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_SURCHARGE_FEE, "Surcharge Fee : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_ADDITIONAL_FEE, "Addition Fee : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_HEALTH_CARE_AMOUNT, "Health Amount : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_CLINIC_AMOUNT, "Clinic Amount : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_DENTAL_AMOUNT, "Dental Amount : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_COPAY_AMOUNT, "Copay Amount : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_PRESCRIPTION_AMOUNT, "Prescription Amount : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_VISION_AMOUNT, "Vision Amount : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_TRANSIT_AMOUNT, "Transit Amount : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_STATUS, "Status : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_CLERK_ID, "Clerk ID : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_CLERK_NAME, "Clerk Name : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_INVOICE_NUMBER, "Invoice Number : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_PO_NUMBER, "PO Number : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_TABLE_NUMBER, "Table Number : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_GUESTS_NUMBER, "Guests Number : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_CUST_CODE, "Customer Code : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_MERCHANT_TAX_ID, "Merchant Tax ID : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_PROD_DESC, "Prod Description : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_TAX_EXEMPT_ID, "Tax Exempt ID : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_REF_NUMBER, "Reference Number : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_AUTH_CODE, "Auth Code : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_RESPONSE_STATUS, "Response Status : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_AVS_RESPONSE, "AVS Response : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_ENTRY_MODE, "Entry Mode : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_FOOD_STAMPS_BALANCE, "Food Stamps Balance : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_CASH_BENEFIT_BALANCE, "Cash Benefit Balance : ");
//        TRANS_MAP.put(EntryExtraData.PARAM_TRANS_STATUS, "Trans. Status : ");
//    }

    public static final Map<String, Integer> EXPAND_TRANS_MAP = new LinkedHashMap<>();

    public static void init_trans_map() {
        //AG3G-49
        EXPAND_TRANS_MAP.put(EntryExtraData.PARAM_TRANS_DATE, R.string.view_trans_date);
        EXPAND_TRANS_MAP.put(EntryExtraData.PARAM_TRANS_TIME, R.string.view_trans_time);
        EXPAND_TRANS_MAP.put(EntryExtraData.PARAM_MERCHANT_ID, R.string.view_merchant_id);
        EXPAND_TRANS_MAP.put(EntryExtraData.PARAM_TERMINAL_ID, R.string.view_terminal_id);

        //EXPAND_TRANS_MAP.put(EntryRequest.PARAM_AMOUNT, R.string.view_amount);
        EXPAND_TRANS_MAP.put("baseAmount", R.string.view_base_amount); //ANBP1009, amount total amount name duplicate
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_TOTAL_AMOUNT, R.string.view_total_amount);
        EXPAND_TRANS_MAP.put("Tip", R.string.view_tip);
        EXPAND_TRANS_MAP.put(EntryExtraData.PARAM_TIP1, R.string.view_tip_1);
        EXPAND_TRANS_MAP.put(EntryExtraData.PARAM_TIP2, R.string.view_tip_2);
        EXPAND_TRANS_MAP.put(EntryExtraData.PARAM_TIP3, R.string.view_tip_3);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_CASHBACK_AMOUNT, R.string.view_cashback);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_HEALTH_CARE_AMOUNT, R.string.view_health_care);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_CLINIC_AMOUNT, R.string.view_clinic);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_PRESCRIPTION_AMOUNT, R.string.view_rx);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_VISION_AMOUNT, R.string.view_vision);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_DENTAL_AMOUNT, R.string.view_dental);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_COPAY_AMOUNT, R.string.view_copay);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_TRANSIT_AMOUNT, R.string.view_transit);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_FUEL_AMOUNT, R.string.view_fuel);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_TAX_AMOUNT, R.string.view_tax);

        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_ZIP_CODE, R.string.view_zip);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_EXPIRY_DATE, R.string.view_expiry);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_ADDRESS, R.string.view_address);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_VOUCHER_NUMBER, R.string.view_voucher_no);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_AUTH_CODE, R.string.view_auth_code);

        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_TRANS_NUMBER, R.string.view_trans_no);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_REFERENCE_NUMBER, R.string.view_ref_no);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_INVOICE_NUMBER, R.string.view_invoice_no);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_CLERK_ID, R.string.view_clerk_id);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_SERVER_ID, R.string.view_server_id);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_TABLE_NUMBER, R.string.view_table_no);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_PHONE_NUMBER, R.string.view_phone);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_GUEST_NUMBER, R.string.view_gust_no);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_ORDER_NUMBER, R.string.view_order_no);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_PO_NUMBER, R.string.view_po_no);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_CUSTOMER_CODE, R.string.view_cus_code);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_PROMPT_RESTRICTION_CODE, R.string.view_restric_code);

        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_FLEET_CUSTOMER_DATA, R.string.view_cus_data);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_FLEET_DEPARTMENT_NUMBER, R.string.view_department_no);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_FLEET_USER_ID, R.string.view_user_id);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_FLEET_VEHICLE_ID, R.string.view_vehicle_id);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_FLEET_VEHICLE_NUMBER, R.string.view_vehicle_no);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_FLEET_JOB_NUMBER, R.string.view_job_no);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_FLEET_ODOMETER, R.string.view_odo);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_FLEET_DRIVER_ID, R.string.view_driver_id);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_FLEET_LICENSE_NUMBER, R.string.view_license);
        EXPAND_TRANS_MAP.put(EntryRequest.PARAM_DEST_ZIP_CODE, R.string.view_dest_zip);
        EXPAND_TRANS_MAP.put(EntryExtraData.PARAM_SERVICE_FEE, R.string.view_service_fee); //Fixed ANBP-1009, display addional fee on AR terminals

        EXPAND_TRANS_MAP.put("ebtType", R.string.view_ebt_type);
        EXPAND_TRANS_MAP.put("bypassReason", R.string.view_bypass_reason);
        EXPAND_TRANS_MAP.put("subTransType", R.string.view_sub_trans_type);
        EXPAND_TRANS_MAP.put("aidType", R.string.view_aid_type);
        EXPAND_TRANS_MAP.put("refundReason", R.string.view_refund_reason);
        EXPAND_TRANS_MAP.put("motoType", R.string.view_moto_type);
        EXPAND_TRANS_MAP.put("taxReason", R.string.view_tax_reason);
        EXPAND_TRANS_MAP.put("duplicateType", R.string.view_duplicatie_type);
        EXPAND_TRANS_MAP.put("cardType", R.string.view_card_type);
        EXPAND_TRANS_MAP.put("transType", R.string.view_trans_type);
        EXPAND_TRANS_MAP.put("edcType", R.string.view_edc_type);
        EXPAND_TRANS_MAP.put("searchCriteria", R.string.view_search_criteria);
        EXPAND_TRANS_MAP.put("batchType", R.string.view_batch_type);
        EXPAND_TRANS_MAP.put("reportType", R.string.view_report_type);
        EXPAND_TRANS_MAP.put("edcGroup", R.string.view_edc_group);

        EXPAND_TRANS_MAP.put(SearchCardActivity.class.getName(), R.string.view_search_card);
        EXPAND_TRANS_MAP.put(EnterVCodeActivity.class.getName(), R.string.view_enter_cvv);
        EXPAND_TRANS_MAP.put(EnterPinActivity.class.getName(), R.string.view_enter_pin);
        EXPAND_TRANS_MAP.put(EnterCardAllDigitsActivity.class.getName(), R.string.view_enter_card_no);
        EXPAND_TRANS_MAP.put(EnterCardLast4DigitsActivity.class.getName(), R.string.view_enter_card_last_4_digits);

        EXPAND_TRANS_MAP.put(EntryExtraData.PARAM_TRANS_MODE, R.string.view_mode);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.TRANS_DATE, R.string.view_trans_date);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.TRANS_TIME, R.string.view_trans_time);
        //EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.TERMINAL_ID, "Terminal ID : ");
        //EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.MERCHANT_ID, "Merchant ID : ");
        //EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.DEVICE_ID, "Device ID : ");

        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.EDC_TYPE, R.string.view_edc_type);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.TRANS_TYPE, R.string.view_trans_type);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.SUB_TRANS_TYPE, R.string.view_sub_trans_type);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.TRANS_NUMBER, R.string.view_trans_number);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.CARD_TYPE, R.string.view_card_type);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.PAN, R.string.view_card_number);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.CURRENCY, R.string.view_currency);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.EXPIRY, R.string.view_expiry);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.ENTRY_MODE, R.string.view_entry_mode);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.TIP0, R.string.view_tip_1);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.TIP1, R.string.view_tip_2);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.TIP2, R.string.view_tip_3);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.TIP_DISCOUNT, R.string.view_tip_discount);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.BASE_AMOUNT, R.string.view_base_amount);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.AMOUNT, R.string.view_total_amount);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.AUTH_AMOUNT, R.string.view_approved_amount);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.TAX, R.string.view_tax);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.CASH_BACK, R.string.view_cashback);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.MERCHANT_FEE, R.string.view_merchant_fee);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.SURCHARGE_FEE, R.string.view_surcharge_fee);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.SERVICE_FEE, R.string.view_service_fee);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.HEALTH_AMOUNT, R.string.view_health_amount);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.RX_AMOUNT, R.string.view_rx_amount);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.CLINICAL_AMOUNT, R.string.view_clinic_amount);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.DENTAL_AMOUNT, R.string.view_dental_amount);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.COPAY_AMOUNT, R.string.view_copay_amount);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.VISION_AMOUNT, R.string.view_vision_amount);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.TRANSIT_AMOUNT, R.string.view_transit_amount);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.REQ_STATUS, R.string.view_trans_status);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.SF_UPLOAD_STATUS, R.string.view_status);
        EXPAND_TRANS_MAP.put(EdcTransContract.ManageTrans.REFUND_REASON, R.string.view_refund_reason);
        EXPAND_TRANS_MAP.put(EdcTransContract.ManageTrans.ORIG_TRANS_DATE, R.string.view_orig_trans_date);
        EXPAND_TRANS_MAP.put(EdcTransContract.ManageTrans.ORIG_TRANS_TIME, R.string.view_orig_trans_time);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.CLERK_ID, R.string.view_clerk_id);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.CLERK_NAME, R.string.view_clerk_name);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.INVOICE, R.string.view_invoice_number);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.PO_NUMBER, R.string.view_po_number);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.ORDER_NUMBER, R.string.view_order_number);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.TABLE_NUMBER, R.string.view_table_number);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.GUEST_NUMBER, R.string.view_guests_number);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.CUSTOMER_CODE, R.string.view_customer_code);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.CS_PHONE_NUMBER, R.string.view_customer_phone);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.MERCHANT_TAX_ID, R.string.view_merchant_tax_id);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.PROD_DESC, R.string.view_prod_description);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.TAX_EXEMPT_ID, R.string.view_tax_exempt_id);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.BATCH_NUMBER, R.string.view_batch_number);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.REF_NUMBER, R.string.view_reference_number);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.AUTH_CODE, R.string.view_auth_code);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.RESP_STATUS, R.string.view_response_status);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.AVS_RESP, R.string.view_avs_response);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.BALANCE0, R.string.view_food_stamps_balance);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.BALANCE1, R.string.view_cash_benefit_balance);

        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.EMV_AID, R.string.view_aid);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.EMV_TVR, R.string.view_tvr);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.EMV_IAD, R.string.view_iad);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.EMV_TSI, R.string.view_tsi);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.EMV_ARC, R.string.view_arc);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.EMV_APP_LABEL, R.string.view_app_label);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.EMV_TC, R.string.view_tc);
        EXPAND_TRANS_MAP.put(EdcTransContract.CommonTrans.EMV_ATC, R.string.view_atc);
    }

}
