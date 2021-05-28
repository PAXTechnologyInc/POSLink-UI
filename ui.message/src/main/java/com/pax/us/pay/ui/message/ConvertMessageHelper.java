package com.pax.us.pay.ui.message;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.ConfirmationEntry;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.PrintStatusType;

import java.util.HashMap;
import java.util.Map;


public class ConvertMessageHelper {
    private static final Map<String, Integer> messageMap = new HashMap<>();

    static {
        messageMap.put(ConfirmationEntry.ACTION_CHECK_CARD_PRESENT, R.string.check_card_present);
        messageMap.put(ConfirmationEntry.ACTION_CHECK_DEACTIVATE_WARN, R.string.confirm_deactivate_warn);
        messageMap.put(ConfirmationEntry.ACTION_REVERSE_PARTIAL_APPROVAL, R.string.select_reverse_partial);
        messageMap.put(ConfirmationEntry.ACTION_SUPPLEMENT_PARTIAL_APPROVAL, R.string.select_supplement_partial);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_ONLINE_RETRY_OFFLINE, R.string.confirm_online_retry_offline);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_DEBIT_TRANS, R.string.confirm_debit_trans);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_BATCH_CLOSE, R.string.confirm_batch_close);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_UNTIPPED, R.string.confirm_untipped_close);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_DUPLICATE_TRANS, R.string.prompt_confirm_dup_transaction);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_UPLOAD_TRANS, R.string.confirm_upload_trans);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_UPLOAD_RETRY, R.string.confirm_upload_retry);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_PRINT_FAILED_TRANS, R.string.confirm_print_failed_trans);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_PRINT_FPS, R.string.confirm_print_fps);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_DELETE_SF, R.string.confirm_delete_sf);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_PRINT_CUSTOMER_COPY, R.string.confirm_print_customer_copy);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_PRINTER_STATUS, R.string.confirm_printer_status);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_SURCHARGE_FEE, R.string.confirm_surcharge_fee);
        messageMap.put(PrintStatusType.PRINTER_OUT_OF_PAPER, R.string.prompt_printer_out_of_paper);
        messageMap.put(PrintStatusType.PRINTER_HOT, R.string.confirm_printer_over_hot);
        messageMap.put(PrintStatusType.PRINTER_VOLTAGE_TOO_LOW, R.string.confirm_printer_voltage_low);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_BATCH_FOR_APPLICATION_UPDATE, R.string.confirm_application_update_with_batch_close);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_ONLINE_RETRY, R.string.confirm_online_retry);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_ADJUST_TIP, R.string.confirm_adjust_tip);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_RECEIPT_SIGNATURE, R.string.confirm_receipt_signature);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_BALANCE, R.string.confirm_balance);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_SIGNATURE_MATCH, R.string.confirm_signature_match);
    }

    public String getMessage(Context context, Intent intent) {
        String currency;
        String message = "", subMessage = "", subMessage1 = "";
        int iResId;
        long amount, feeAmount, primaryAmount;
        if (messageMap.containsKey(intent.getAction())) {
            iResId = messageMap.get(intent.getAction());
        } else
            iResId = 0;
        if (iResId > 0)
            message = context.getResources().getString(iResId);
        if (intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            if (bundle.containsKey("currentAction")
                    && (bundle.getString("currentAction").equals(ConfirmationEntry.ACTION_CONFIRM_BATCH_CLOSE))
                    && bundle.containsKey(EntryExtraData.PARAM_INTERNAL_NICKNAME_FLAG)
                    && bundle.getBoolean(EntryExtraData.PARAM_INTERNAL_NICKNAME_FLAG)) {
                    message = context.getResources().getString(R.string.confirm_reconciliation_close);
            } else if (bundle.containsKey("currentAction")
                        && (bundle.getString("currentAction").equals(ConfirmationEntry.ACTION_REVERSE_PARTIAL_APPROVAL)
                        || bundle.getString("currentAction").equals(ConfirmationEntry.ACTION_SUPPLEMENT_PARTIAL_APPROVAL))) {
                    long approvedAmt = bundle.getLong(EntryExtraData.PARAM_APPROVED_AMOUNT);
                    long total = bundle.getLong(EntryExtraData.PARAM_TOTAL_AMOUNT);
                    long due = total - approvedAmt;
                    message = context.getResources().getString(R.string.approve_amount) + CurrencyConverter.convert(approvedAmt, "") + "\n" +
                            context.getResources().getString(R.string.due) + CurrencyConverter.convert(due, "") + "; " +
                            message;
            } else if (bundle.containsKey(EntryExtraData.PARAM_SURCHARGE_FEE)) {
                currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "USD");
                CurrencyConverter.setDefCurrency(currency);
                amount = bundle.getLong(EntryExtraData.PARAM_TOTAL_AMOUNT);
                String feeName = bundle.getString(EntryExtraData.PARAM_SURCHARGE_FEE_NAME, message);
                feeAmount = bundle.getLong(EntryExtraData.PARAM_SURCHARGE_FEE);
                primaryAmount = amount - feeAmount;
                message = packMessage(feeName, primaryAmount, feeAmount, amount);

//                subMessage1 = "Total Amount" + CurrencyConverter.convert(amount, "") + "\n";
//                subMessage = "Sale Amount " + CurrencyConverter.convert(primaryAmount, "") + "\n";
//                message = subMessage + feeName + CurrencyConverter.convert(feeAmount, "") + "\n" + subMessage1;
            } else if (bundle.containsKey(EntryExtraData.PARAM_TOTAL_AMOUNT)) {
                currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "USD");
                CurrencyConverter.setDefCurrency(currency);
                amount = bundle.getLong(EntryExtraData.PARAM_TOTAL_AMOUNT);
                message = context.getResources().getString(R.string.total_amount_with_colon) + CurrencyConverter.convert(amount, "") + "\n" + message;
            } else if (bundle.containsKey(EntryExtraData.PARAM_PRINT_STATUS)) {
                //ANFDRC-687 avoid empty value of printStatus
                String key = bundle.getString(EntryExtraData.PARAM_PRINT_STATUS);
                if (!TextUtils.isEmpty(key)) {
                    Integer id = messageMap.get(key);
                    message = context.getResources().getString(id);
                }
            } else if (bundle.containsKey(EntryExtraData.PARAM_BALANCE)) {
                currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "USD");
                CurrencyConverter.setDefCurrency(currency);
                amount = bundle.getLong(EntryExtraData.PARAM_BALANCE);
                message = message + "\n " + CurrencyConverter.convert(amount, "");
            }
        }
        return message;
    }


    private String packMessage(String feeName, long primaryAmount, long feeAmount, long totalAmount) {
        Paint paint = new Paint();
        float feeNameWidth = paint.measureText(feeName);
        float totalWidth = paint.measureText("Total Amount      ");
        float maxWidth = feeNameWidth > totalWidth ? feeNameWidth : totalWidth;
        String primaryAmountName = PadSpace(true, "Sale Amount", maxWidth);
        String newFeeName = PadSpace(true, feeName, maxWidth);
        String totalAmountName = PadSpace(true, "Total Amount", maxWidth);

        String strTotalAmount = CurrencyConverter.convert(totalAmount, "");
        String strPrimaryAmount = CurrencyConverter.convert(primaryAmount, "");
        String strFeeAmount = CurrencyConverter.convert(feeAmount, "");
        float totalAmountWidth = paint.measureText(strTotalAmount);
        float primaryAmountWidth = paint.measureText(strPrimaryAmount);
        maxWidth = totalAmountWidth > primaryAmountWidth ? totalAmountWidth : primaryAmountWidth;

        strPrimaryAmount = PadSpace(false, strPrimaryAmount, maxWidth);
        strTotalAmount = PadSpace(false, strTotalAmount, maxWidth);
        strFeeAmount = PadSpace(false, strFeeAmount, maxWidth);

        String msg = "Please Confirm " + feeName + " : \n" +
                primaryAmountName + " : " + strPrimaryAmount + "\n" +
                newFeeName + " : " + strFeeAmount + "\n" +
                totalAmountName + " : " + strTotalAmount;
        return msg;
    }

    private String PadSpace(boolean AlignLeft, String name, float maxWidth) {
        String temp;
        int len;
        String format;
        temp = name;
        Paint paint = new Paint();
        while (true) {
            float width = paint.measureText(temp);
            if (width >= maxWidth)
                return temp;
            len = temp.length() + 1;
            if (AlignLeft) {
                format = "%-" + String.format("%d", len) + "s";
            } else {
                format = "%" + String.format("%d", len) + "s";
            }
            temp = String.format(format, temp);
        }
    }
}
