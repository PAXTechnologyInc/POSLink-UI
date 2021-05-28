package com.pax.pay.ui.def.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import com.pax.pay.ui.def.constant.CardContext;
import com.pax.pay.ui.def.constant.EdcContext;
import com.pax.pay.ui.def.constant.EdcTransContract;
import com.pax.pay.ui.def.constant.EntryMode;
import com.pax.pay.ui.def.constant.StoreForwardContext;
import com.pax.pay.ui.def.constant.TransContext;
import com.pax.us.pay.ui.message.CurrencyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataProviderUtils {
    private static final String[] queryDataKey = {
            EdcTransContract.CommonTrans.EDC_TYPE,
            EdcTransContract.CommonTrans.TRANS_TYPE,
            EdcTransContract.CommonTrans.SUB_TRANS_TYPE,
            EdcTransContract.CommonTrans.TRANS_NUMBER,
            EdcTransContract.CommonTrans.PAN,
            EdcTransContract.CommonTrans.ENTRY_MODE,
            EdcTransContract.CommonTrans.EMV_CARD_SRQ_NUM,
            EdcTransContract.CommonTrans.BASE_AMOUNT,
            EdcTransContract.CommonTrans.TIP0,
            EdcTransContract.CommonTrans.TIP1,
            EdcTransContract.CommonTrans.TIP2,
            EdcTransContract.CommonTrans.MERCHANT_FEE,
            EdcTransContract.CommonTrans.SURCHARGE_FEE,
            EdcTransContract.CommonTrans.CASH_BACK,
            EdcTransContract.CommonTrans.AMOUNT,
            EdcTransContract.CommonTrans.AUTH_AMOUNT,
            EdcTransContract.CommonTrans.REQ_STATUS,
            EdcTransContract.CommonTrans.REF_NUMBER,
            EdcTransContract.CommonTrans.AUTH_CODE,
            EdcTransContract.CommonTrans.RESP_STATUS,
            EdcTransContract.CommonTrans.CARD_TYPE,
            EdcTransContract.CommonTrans.CURRENCY,
            EdcTransContract.CommonTrans.SF_UPLOAD_STATUS,
            EdcTransContract.CommonTrans.TRANS_DATE,
            EdcTransContract.CommonTrans.TRANS_TIME};

    private static final Map<Integer, String> statusMap = new HashMap<>();
    private static final Map<Integer, String> entryModeMap = new HashMap<>();
    private static final Map<Integer, String> subTransTypeMap = new HashMap<>();

    static {
        statusMap.put(StoreForwardContext.STATUS_NO_UPLOAD, "Not Upload");
        statusMap.put(StoreForwardContext.STATUS_UPLOAD_SUCCESS, "Uploaded Success");
        statusMap.put(StoreForwardContext.STATUS_UPLOAD_FAILED, "Uploaded Failed");
    }

    static {
        entryModeMap.put(EntryMode.SWIPE, "Swiped");
        entryModeMap.put(EntryMode.INSERT, "Inserted");
        entryModeMap.put(EntryMode.CLSS, "Taped");
        entryModeMap.put(EntryMode.MANUAL, "Manual");
        entryModeMap.put(EntryMode.QR, "Scanned");
    }

    static {
        subTransTypeMap.put(TransContext.SUB_TYPE_NONE, "None");
        subTransTypeMap.put(TransContext.SUB_TYPE_BY_REF_NUM, "By RefNo.");
        subTransTypeMap.put(TransContext.SUB_TYPE_CASH_OUT, "Cashout Active ");
        subTransTypeMap.put(TransContext.SUB_TYPE_CASH_OUT_ACTIVE, "Cashout");
        subTransTypeMap.put(TransContext.SUB_TYPE_BY_CARD_NUM, "By CardNum");
    }
//  For Test
//    private static List<Map<String, Object>> testData= new ArrayList<>();
//
//    public static void initTransData(){
//        for(int i=1; i<30; i++ ){
//            Map<String, Object> map = new HashMap<>();
//            map.put(EdcTransContract.CommonTrans.EDC_TYPE, EdcContext.EDC_TYPE_CREDIT);
//            map.put(EdcTransContract.CommonTrans.TRANS_TYPE, TransContext.TYPE_SALE);
//            map.put(EdcTransContract.CommonTrans.TRANS_NUMBER, 300l+i);
//            map.put(EdcTransContract.CommonTrans.PAN, "5128570136912348");
//            map.put(EdcTransContract.CommonTrans.CARD_TYPE, CardContext.TYPE_MASTER_CARD);
//            map.put(EdcTransContract.CommonTrans.AMOUNT, i*100L);
//            map.put(EdcTransContract.CommonTrans.CURRENCY, "USD");
//            map.put(EdcTransContract.CommonTrans.AUTH_AMOUNT, i*80L);
//            map.put(EdcTransContract.CommonTrans.TRANS_DATE, DateUtils.getSystemDate("yyyyMMdd"));
//            map.put(EdcTransContract.CommonTrans.TRANS_TIME, DateUtils.getSystemDate("hhmmss"));
//            map.put(EdcTransContract.CommonTrans.REQ_STATUS, TransContext.REQ_STATUS_COMPLETED);
//            map.put(EdcTransContract.CommonTrans.SF_UPLOAD_STATUS, StoreForwardContext.STATUS_UPLOAD_SUCCESS);
//
//            map.put(EdcTransContract.CommonTrans.ENTRY_MODE, EntryMode.INSERT);
//            map.put(EdcTransContract.CommonTrans.EMV_CARD_SRQ_NUM, "1");
//            map.put(EdcTransContract.CommonTrans.REF_NUMBER, "849942955646");
//            map.put(EdcTransContract.CommonTrans.AUTH_CODE, "894368");
//            map.put(EdcTransContract.CommonTrans.RESP_STATUS, TransContext.OL_RESP_STATUS_APPROVED);
//            map.put(EdcTransContract.CommonTrans.SUB_TRANS_TYPE, TransContext.SUB_TYPE_BY_CARD_NUM);
//            testData.add(map);
//        }
//    }

    public static long getCanAdjustTransCount(Context context, String transUri, String selection, String[] selectArgs) {
        //return testData.size();
        long count = 0;
        ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver != null) {
            Uri uri = Uri.parse(transUri);

            try (Cursor cursor = contentResolver.query(uri, new String[]{EdcTransContract.CommonTrans.TRANS_NUMBER},
                    selection,
                    selectArgs,
                    null)) {
                if (cursor != null) {
                    count = cursor.getCount();
                }
            }
        }

        return count;
    }

    public static long getCanAdjustTransCount(Context context, String transUri, boolean supportTipAdjust, boolean untippedOnly, String cardType, String last4Digits) {

        long count = 0;
        ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver != null) {
            Uri uri = Uri.parse(transUri);
            List<String> selectionArgs = new ArrayList<>();
            StringBuilder selection = new StringBuilder();

            selection.append(EdcTransContract.CommonTrans.EDC_TYPE + "=? AND");
            selectionArgs.add(EdcContext.EDC_TYPE_CREDIT);

            selection.append(EdcTransContract.CommonTrans.SF_UPLOAD_STATUS + "IN (?,?) AND");
            selectionArgs.add(String.valueOf(StoreForwardContext.STATUS_NO_UPLOAD));
            selectionArgs.add(String.valueOf(StoreForwardContext.STATUS_UPLOAD_SUCCESS));

            if (supportTipAdjust) {
                selection.append(EdcTransContract.CommonTrans.TRANS_TYPE + "IN (?,?,?) AND");
                selectionArgs.add(TransContext.TYPE_SALE);
                selectionArgs.add(TransContext.TYPE_FORCED);
                selectionArgs.add(TransContext.TYPE_POST_AUTH);
            } else {
                selection.append(EdcTransContract.CommonTrans.TRANS_TYPE + "=? AND");
                selectionArgs.add(TransContext.TYPE_SALE);
            }

            selection.append(EdcTransContract.CommonTrans.REQ_STATUS + "NOT IN (?,?) AND");
            selectionArgs.add(TransContext.REQ_STATUS_VOIDED);
            selectionArgs.add(TransContext.REQ_STATUS_QUICK_SALE);

            selection.append(EdcTransContract.CommonTrans.RESP_STATUS + "NOT IN (?)");
            selectionArgs.add(TransContext.OL_RESP_STATUS_PARTIALLY_APPROVED);

            if (untippedOnly) {
                selection.append(" AND ");
                selection.append(EdcTransContract.CommonTrans.TIP0 + "=? AND");
                selectionArgs.add("0");
                selection.append(EdcTransContract.CommonTrans.TIP1 + "=? AND");
                selectionArgs.add("0");
                selection.append(EdcTransContract.CommonTrans.TIP2 + "=?");
                selectionArgs.add("0");
            }

            if (cardType != null && !CardContext.TYPE_ALL.equals(cardType)) {
                selection.append(" AND ");
                selection.append(EdcTransContract.CommonTrans.CARD_TYPE + "=?");
                selectionArgs.add(cardType);
            }
            if (last4Digits != null && !last4Digits.isEmpty()) {
                selection.append(" AND ");
                selection.append(EdcTransContract.CommonTrans.PAN + "=?");
                selectionArgs.add("%" + last4Digits);
            }

            try (Cursor cursor = contentResolver.query(uri, new String[]{EdcTransContract.CommonTrans.TRANS_NUMBER},
                    selection.toString(),
                    (String[]) selectionArgs.toArray(),
                    null)) {
                if (cursor != null) {
                    count = cursor.getCount();
                }
            }
        }

        return count;
    }


    public static List<Map<String, Object>> findTransDataByPageForAdjust(
            Context context, String transUri, String selection, String[] selectionArgs, long offset, long pageSize) {
//For Test
//        List<Map<String, Object>> list = new ArrayList<>();
//        for(long i= offset; i<offset+pageSize; i++){
//            if(i<testData.size()) {
//                list.add(testData.get((int) i));
//            }
//        }
//        return list;

        ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver != null) {
            Uri uri = Uri.parse(transUri);


            String sortOrder = EdcTransContract.CommonTrans._ID + " desc  limit  " + pageSize + " offset " + offset;
            try (Cursor cursor = contentResolver.query(uri, queryDataKey,
                    selection,
                    selectionArgs,
                    sortOrder)) {
                if (cursor != null) {
                    cursor.moveToFirst();
                    List<Map<String, Object>> list = new ArrayList<>();
                    do {
                        list.add(parseCursor(cursor));
                    } while (cursor.moveToNext());
                    //count = cursor.getCount();
                    return list;
                }
            }
        }

        return new ArrayList<>();
    }

    private static Map<String, Object> parseCursor(Cursor cursor) {
        List<String> stringKey = Arrays.asList(
                EdcTransContract.CommonTrans.EDC_TYPE,
                EdcTransContract.CommonTrans.TRANS_TYPE,
                EdcTransContract.CommonTrans.REQ_STATUS,
                EdcTransContract.CommonTrans.PAN,
                EdcTransContract.CommonTrans.CARD_TYPE,
                EdcTransContract.CommonTrans.CURRENCY,
                EdcTransContract.CommonTrans.EMV_CARD_SRQ_NUM,
                EdcTransContract.CommonTrans.REF_NUMBER,
                EdcTransContract.CommonTrans.AUTH_CODE,
                EdcTransContract.CommonTrans.RESP_STATUS,
                EdcTransContract.CommonTrans.TRANS_DATE,
                EdcTransContract.CommonTrans.TRANS_TIME);
        List<String> longKey = Arrays.asList(
                EdcTransContract.CommonTrans.TRANS_NUMBER,
                EdcTransContract.CommonTrans.AMOUNT,
                EdcTransContract.CommonTrans.BASE_AMOUNT,
                EdcTransContract.CommonTrans.TIP0,
                EdcTransContract.CommonTrans.TIP1,
                EdcTransContract.CommonTrans.TIP2,
                EdcTransContract.CommonTrans.MERCHANT_FEE,
                EdcTransContract.CommonTrans.SURCHARGE_FEE,
                EdcTransContract.CommonTrans.CASH_BACK,
                EdcTransContract.CommonTrans.AUTH_AMOUNT);

        List<String> integerKey = Arrays.asList(EdcTransContract.CommonTrans.ENTRY_MODE,
                EdcTransContract.CommonTrans.SUB_TRANS_TYPE,
                EdcTransContract.CommonTrans.SF_UPLOAD_STATUS);

        Map<String, Object> map = new HashMap<>();
        for (String key : stringKey) {
            map.put(key, cursor.getString(cursor.getColumnIndex(key)));
        }

        for (String key : longKey) {
            map.put(key, cursor.getLong(cursor.getColumnIndex(key)));
        }

        for (String key : integerKey) {
            map.put(key, cursor.getInt(cursor.getColumnIndex(key)));
        }
        return map;
    }

    public static LinkedHashMap<String, String> prepareData(Map<String, Object> transData) {
        String[] list = queryDataKey;

        LinkedHashMap<String, String> displayData = new LinkedHashMap<>();
        for (String temp : list) {
            if (transData.containsKey(temp)) {
                if (transData.get(temp) instanceof String) {
                    if (EdcTransContract.CommonTrans.PAN.equals(temp)) {
                        displayData.put(temp, maskCardNoFixLength((String) transData.get(temp)));
                    }else if (EdcTransContract.CommonTrans.TRANS_DATE.equals(temp)) {
                        String date = String.valueOf(transData.get(temp));
                        if (!TextUtils.isEmpty(date) && date.matches("^[0-9]{8}$")) {
                            displayData.put(temp, date.substring(4, 6) + "/" + date.substring(6) + "/" + date.substring(0, 4));
                        }else {
                            displayData.put(temp,date);
                        }
                    } else if (EdcTransContract.CommonTrans.TRANS_TIME.equals(temp)) {
                        String time = String.valueOf(transData.get(temp));
                        if (!TextUtils.isEmpty(time) && time.matches("^[0-9]{6}$")) {
                            displayData.put(temp, time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4));
                        }else {
                            displayData.put(temp,time);
                        }
                    } else if (!TextUtils.isEmpty((String) transData.get(temp))) {
                        displayData.put(temp, (String) transData.get(temp));
                    }
                } else if (!(transData.get(temp) instanceof Boolean)) {
                    if (EdcTransContract.CommonTrans.AMOUNT.equals(temp) || EdcTransContract.CommonTrans.AUTH_AMOUNT.equals(temp)
                            || EdcTransContract.CommonTrans.BASE_AMOUNT.equals(temp)
                            || EdcTransContract.CommonTrans.TIP0.equals(temp)
                            || EdcTransContract.CommonTrans.TIP1.equals(temp)
                            || EdcTransContract.CommonTrans.TIP2.equals(temp)
                            || EdcTransContract.CommonTrans.SURCHARGE_FEE.equals(temp)
                            || EdcTransContract.CommonTrans.MERCHANT_FEE.equals(temp)
                            || EdcTransContract.CommonTrans.CASH_BACK.equals(temp)) {
                        if((Long) transData.get(temp)>0) {
                            displayData.put(temp, CurrencyConverter.convert((Long) transData.get(temp)));
                        }
                    } else if (EdcTransContract.CommonTrans.ENTRY_MODE.equals(temp)) {
                        displayData.put(temp, entryModeMap.get(transData.get(temp)));
                    } else if (EdcTransContract.CommonTrans.SUB_TRANS_TYPE.equals(temp)) {
                        displayData.put(temp, subTransTypeMap.get(transData.get(temp)));
                    } else if (EdcTransContract.CommonTrans.SF_UPLOAD_STATUS.equals(temp)) {
                        displayData.put(temp, statusMap.get(transData.get(temp)));
                    } else {
                        if (transData.get(temp) != null) {
                            displayData.put(temp, String.valueOf(transData.get(temp)));
                        }
                    }
                }
            }
        }
        return displayData;
    }

    public static void prepareData(Map<String, Object> transData, List<String> key, List<String> value) {
        String[] list = queryDataKey;
        if (key == null)
            key = new ArrayList<>();
        else if (!key.isEmpty())
            key.clear();

        if (value == null)
            value = new ArrayList<>();
        else if (!value.isEmpty())
            value.clear();
        for (String temp : list) {
            if (transData.containsKey(temp)) {
                if (transData.get(temp) instanceof String) {
                    if (EdcTransContract.CommonTrans.PAN.equals(temp)) {
                        key.add(temp);
                        value.add(maskCardNoFixLength((String) transData.get(temp)));
                    } else if (EdcTransContract.CommonTrans.TRANS_DATE.equals(temp)) {
                        key.add(temp);
                        String date = String.valueOf(transData.get(temp));
                        if (!TextUtils.isEmpty(date) && date.matches("^[0-9]{8}$")) {
                            value.add(date.substring(4, 6) + "/" + date.substring(6) + "/" + date.substring(0, 4));
                        }else {
                            value.add(date);
                        }
                    } else if (EdcTransContract.CommonTrans.TRANS_TIME.equals(temp)) {
                        key.add(temp);
                        String time = String.valueOf(transData.get(temp));
                        if (!TextUtils.isEmpty(time) && time.matches("^[0-9]{6}$")) {
                            value.add(time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4));
                        }else {
                            value.add(time);
                        }
                    }else if(EdcTransContract.CommonTrans.TRANS_TYPE.equals(temp)){
                        key.add(temp);
                        value.add(TransContext.getTransTypeDesc(null, (String) transData.get(EdcTransContract.CommonTrans.TRANS_TYPE)));
                    }else if (!TextUtils.isEmpty((String) transData.get(temp))) {
                        key.add(temp);
                        value.add((String) transData.get(temp));
                    }
                } else if (!(transData.get(temp) instanceof Boolean)) {
                    if (EdcTransContract.CommonTrans.AMOUNT.equals(temp)
                            || EdcTransContract.CommonTrans.AUTH_AMOUNT.equals(temp)
                            || EdcTransContract.CommonTrans.BASE_AMOUNT.equals(temp)
                            || EdcTransContract.CommonTrans.TIP0.equals(temp)
                            || EdcTransContract.CommonTrans.TIP1.equals(temp)
                            || EdcTransContract.CommonTrans.TIP2.equals(temp)
                            || EdcTransContract.CommonTrans.SURCHARGE_FEE.equals(temp)
                            || EdcTransContract.CommonTrans.MERCHANT_FEE.equals(temp)
                            || EdcTransContract.CommonTrans.CASH_BACK.equals(temp)) {
                        if((Long) transData.get(temp) > 0) {
                            key.add(temp);
                            value.add(CurrencyConverter.convert((Long) transData.get(temp)));
                        }
                    } else if (EdcTransContract.CommonTrans.ENTRY_MODE.equals(temp)) {
                        key.add(temp);
                        value.add(entryModeMap.get(transData.get(temp)));
                    } else if (EdcTransContract.CommonTrans.SUB_TRANS_TYPE.equals(temp)) {
                        key.add(temp);
                        value.add(subTransTypeMap.get(transData.get(temp)));
                    } else if (EdcTransContract.CommonTrans.SF_UPLOAD_STATUS.equals(temp)) {
                        key.add(temp);
                        value.add(statusMap.get(transData.get(temp)));
                    } else {
                        if (transData.get(temp) != null) {
                            key.add(temp);
                            value.add(String.valueOf(transData.get(temp)));
                        }
                    }
                }
            }
        }
    }

    public static String statusConvert(int status) {
        return statusMap.get(status);
    }

    public static String maskCardNoFixLength(String encryptedPan) {
        return encryptedPan.replaceAll("(\\w+)(?=\\d{4})", "************");
    }
}
