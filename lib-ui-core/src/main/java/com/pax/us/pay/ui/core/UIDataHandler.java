package com.pax.us.pay.ui.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
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
    final static String DATE = "Date";
    final static String TIME = "Time";

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
        REQ_DATA_MAP.put(EntryRequest.PARAM_SERVER_ID, DEFAULT);
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

    public static final Map<String, String> EXTRA_DATA_MAP = new LinkedHashMap<>();

    static {

        EXTRA_DATA_MAP.put(EntryExtraData.PARAM_TRANS_TYPE, DEFAULT);
        EXTRA_DATA_MAP.put(EntryExtraData.PARAM_TRANS_TIME, TIME);
        EXTRA_DATA_MAP.put(EntryExtraData.PARAM_TRANS_DATE, DATE);
        EXTRA_DATA_MAP.put(EntryExtraData.PARAM_MERCHANT_ID, DEFAULT);
        EXTRA_DATA_MAP.put(EntryExtraData.PARAM_TERMINAL_ID, DEFAULT);
        EXTRA_DATA_MAP.put(EntryExtraData.PARAM_EDC_TYPE, DEFAULT);
        EXTRA_DATA_MAP.put(EntryExtraData.PARAM_TRANS_NUMBER, DEFAULT);
        EXTRA_DATA_MAP.put(EntryExtraData.PARAM_TRANS_MODE, DEFAULT);
        EXTRA_DATA_MAP.put(EntryExtraData.PARAM_SERVICE_FEE, AMOUNT); //Fixed ANBP-1009, display additional fee on AR terminals
        EXTRA_DATA_MAP.put(EntryExtraData.PARAM_TOTAL_AMOUNT, AMOUNT); //Fixed ANBP-1009, display additional fee on AR terminals
        EXTRA_DATA_MAP.put(EntryExtraData.PARAM_TIP1, AMOUNT);//ANBP1039, When send Tip/Cashback from poslink, terminal should show the tip/cashback amount in receipt side
        EXTRA_DATA_MAP.put(EntryExtraData.PARAM_CASH_BACK, AMOUNT);//ANBP1039, When send Tip/Cashback from poslink, terminal should show the tip/cashback amount in receipt side
        EXTRA_DATA_MAP.put(EntryExtraData.PARAM_BASE_AMOUNT, AMOUNT);//ANBP1039, When send Tip/Cashback from poslink, terminal should show the tip/cashback amount in receipt side
		EXTRA_DATA_MAP.put(EntryExtraData.PARAM_SURCHARGE_FEE, AMOUNT);
		EXTRA_DATA_MAP.put(EntryExtraData.PARAM_MERCHANT_FEE, AMOUNT);
		EXTRA_DATA_MAP.put(EntryExtraData.PARAM_TAX, AMOUNT);
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

        if (bundle == null || bundle.size() == 0)
            return;

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
                        if (EntryRequest.PARAM_AMOUNT.equals(key)) //fixed ANBP-1009，total amount name duplicate issue
                            editor.putString("baseAmount", strAmt);
                        else
                            editor.putString(key, strAmt);
                    }else if (TIP.equals(type)){
                        String strAmt = convertAmount((long)value);
                        if (TextUtils.isEmpty(tipName)){
                            editor.putString(type, strAmt);
                        } else {
                            editor.putString(tipName, strAmt);
                        }
                    } else if (EXPIRY_DATE.equals(type)) {
                        if (value instanceof String) {
                            String date = ((String) value).substring(0, 2) + "/" + ((String) value).substring(2, 4);
                            if (!TextUtils.isEmpty(date)) {
                                editor.putString(key, date);
                            }
                        }
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

    public static void saveExtraData(Context context, Bundle bundle) {
        if (bundle == null || bundle.size() == 0)
            return;

        SharedPreferences preferences = context.getSharedPreferences(EntryRequest.class.getName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> keySet = bundle.keySet();
        for (String key : keySet) {
            if (EXTRA_DATA_MAP.containsKey(key)) {
                String type = EXTRA_DATA_MAP.get(key);
                Object value = bundle.get(key);
                if (value != null) {
                    if (DEFAULT.equals(type)) {
                        if (value instanceof String) {
                            editor.putString(key, (String) value);
                        } else if ((value instanceof Integer) || (value instanceof Long)) {
                            editor.putString(key, String.valueOf(value));
                        } else if (value instanceof Boolean) {
                            editor.putString(key, (boolean) value ? "true" : "false");
                        }
                    } else if (DATE.equals(type)) {
                        if (value instanceof String) {
                            String date = ((String) value).substring(4, 6) + "/" + ((String) value).substring(6, 8) + "/" + ((String) value).substring(0, 4);
                            if (!TextUtils.isEmpty(date)) {
                                editor.putString(key, date);
                            }
                        }
                    } else if (TIME.equals(type)) {
                        if (value instanceof String) {
                            String date = ((String) value).substring(0, 2) + ":" + ((String) value).substring(2, 4) + ":" + ((String) value).substring(4, 6);
                            if (!TextUtils.isEmpty(date)) {
                                editor.putString(key, date);
                            }
                        }
                    } else if (AMOUNT.equals(type)){ //Fixed Ticket ANBP1009, In AR6/AR8, Terminal should record additional fee in history page
                        String strAmt = convertAmount((long)value);
                        editor.putString(key, strAmt);
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
