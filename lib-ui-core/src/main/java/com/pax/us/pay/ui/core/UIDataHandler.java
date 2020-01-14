package com.pax.us.pay.ui.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.OptionEntry;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class UIDataHandler {

    final static String  AMOUNT = "amount";
    final static String  TIP = "tip";
    final static String  EXPIRY_DATE = "Expiry";
    final static String  DEFAULT = "Default";

    private static String[] options;
    private static String currentAction;
    private static String tipName;

    public static final Map<String, String> REQ_DATA_MAP = new LinkedHashMap<>();

    static {
        REQ_DATA_MAP.put(EntryRequest.PARAM_AMOUNT, AMOUNT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_TOTAL_AMOUNT, AMOUNT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_CASHBACK_AMOUNT, AMOUNT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_HEALTH_CARE_AMOUNT, AMOUNT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_CLINIC_AMOUNT, AMOUNT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_PRESCRIPTION_AMOUNT, AMOUNT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_VISION_AMOUNT, AMOUNT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_DENTAL_AMOUNT, AMOUNT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_COPAY_AMOUNT, AMOUNT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_TRANSIT_AMOUNT, AMOUNT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_FUEL_AMOUNT, AMOUNT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_TAX_AMOUNT, AMOUNT);

        REQ_DATA_MAP.put(EntryRequest.PARAM_TIP, TIP);
        REQ_DATA_MAP.put(EntryRequest.PARAM_ZIP_CODE, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_EXPIRY_DATE, EXPIRY_DATE);
        REQ_DATA_MAP.put(EntryRequest.PARAM_ADDRESS, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_VOUCHER_NUMBER, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_AUTH_CODE, DEFAULT);

        REQ_DATA_MAP.put(EntryRequest.PARAM_TRANS_NUMBER, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_REFERENCE_NUMBER, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_INVOICE_NUMBER, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_CLERK_ID, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_TABLE_NUMBER, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_PHONE_NUMBER, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_GUEST_NUMBER, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_ORDER_NUMBER, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_PO_NUMBER, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_CUSTOMER_CODE, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_PROMPT_RESTRICTION_CODE, DEFAULT);

        REQ_DATA_MAP.put(EntryRequest.PARAM_FLEET_CUSTOMER_DATA, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_FLEET_DEPARTMENT_NUMBER, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_FLEET_USER_ID, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_FLEET_VEHICLE_ID, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_FLEET_VEHICLE_NUMBER, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_FLEET_JOB_NUMBER, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_FLEET_ODOMETER, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_FLEET_DRIVER_ID, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_FLEET_LICENSE_NUMBER, DEFAULT);
        REQ_DATA_MAP.put(EntryRequest.PARAM_DEST_ZIP_CODE, DEFAULT);

        REQ_DATA_MAP.put(EntryRequest.PARAM_INDEX, currentAction);
    }

    public static final Map<String, String> ACTION_MAP = new HashMap<>();

    static {
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_EBT_TYPE, "ebtType");
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_BY_PASS, "bypassReason");
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_SUB_TRANS_TYPE, "subTransType");
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_AID, "aidType");
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_REFUND_REASON, "refundReason");
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_MOTO_TYPE, "motoType");
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_TAX_REASON, "taxReason");
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_DUPLICATE_OVERRIDE, "duplicateType");
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_CARD_TYPE, "cardType");
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_TRANS_TYPE, "transType");
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_EDC_TYPE, "edcType");
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_SEARCH_CRITERIA, "searchCriteria");
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_BATCH_TYPE, "batchType");
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_REPORT_TYPE, "reportType");
        ACTION_MAP.put(OptionEntry.ACTION_SELECT_EDC_GROUP, "edcGroup");
    }

    public static void setOptions(String[] opt) {
        options = opt;
    }

    public static void setAction(String action) {
        currentAction = action;
    }

    public static void setTipName(String tipName) {
        UIDataHandler.tipName = tipName;
    }

    public static void saveData(Context context, @Nullable Bundle bundle){

        SharedPreferences preferences=context.getSharedPreferences(EntryRequest.class.getName(),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        Set<String> keySet = bundle.keySet();
        for(String key : keySet) {
            if (REQ_DATA_MAP.containsKey(key)) {
                String type = REQ_DATA_MAP.get(key);
                Object value = bundle.get(key);
                if (value != null) {
                    if (AMOUNT.equals(type)){
                        String strAmt = convertAmount((long)value);
                        editor.putString(key, strAmt);
                    }else if (TIP.equals(type)){
                        String strAmt = convertAmount((long)value);
                        editor.putString(tipName, strAmt);
                    }else if (DEFAULT.equals(type)) {
                        if (value instanceof String) {
                            editor.putString(key, (String) value);
                        } else if ((value instanceof Integer) || (value instanceof Long)){
                            editor.putString(key, String.valueOf(value));
                        } else if (value instanceof Boolean){
                            editor.putString(key, (boolean)value ? "true" : "false");
                        }
                    }else{
                        if (ACTION_MAP.containsKey(currentAction)) {
                            String action = ACTION_MAP.get(currentAction);
                            String content = options[(int) value];
                            editor.putString(action, content);
                        }
                    }
                    editor.commit();
                }
            }
        }
    }

    private static String convertAmount(long amount){
        Currency currency = Currency.getInstance(Locale.US);
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        formatter.setMinimumFractionDigits(currency.getDefaultFractionDigits());
        formatter.setMaximumFractionDigits(currency.getDefaultFractionDigits());
        Long newAmount = Math.abs(amount); // AET-58
        String prefix = newAmount > amount ? "-" : "";
        try {
            double amt = Double.valueOf(newAmount) / (Math.pow(10, currency.getDefaultFractionDigits()));
            return prefix + formatter.format(amt);
        } catch (IllegalArgumentException e) {
            Log.e("", e.getMessage());
        }
        return "";
    }

}
