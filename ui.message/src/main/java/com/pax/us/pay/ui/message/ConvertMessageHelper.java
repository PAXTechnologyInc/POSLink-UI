package com.pax.us.pay.ui.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_ONLINE_RETRY_OFFLINE, R.string.confirm_online_retry_offline);
//        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_DEBIT_TRANS, R.string.confirm_debit_trans);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_UNTIPPED, R.string.confirm_untipped_close);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_DUPLICATE_TRANS, R.string.prompt_confirm_dup_transaction);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_UPLOAD_TRANS, R.string.confirm_upload_trans);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_UPLOAD_RETRY, R.string.confirm_upload_retry);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_PRINT_FAILED_TRANS, R.string.confirm_print_failed_trans);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_PRINT_FPS, R.string.confirm_print_fps);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_DELETE_SF, R.string.confirm_delete_sf);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_PRINT_CUSTOMER_COPY, R.string.confirm_print_customer_copy);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_BATCH_FOR_APPLICATION_UPDATE, R.string.confirm_application_update_with_batch_close);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_ONLINE_RETRY, R.string.confirm_online_retry);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_ADJUST_TIP, R.string.confirm_adjust_tip);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_RECEIPT_SIGNATURE, R.string.confirm_receipt_signature);
        messageMap.put(ConfirmationEntry.ACTION_CONFIRM_SIGNATURE_MATCH, R.string.confirm_signature_match);
    }

    public String getMessage(Context context, Intent intent) {
        String message = "";
        int iResId;
        if (messageMap.containsKey(intent.getAction())) {
            iResId = messageMap.get(intent.getAction());
        } else
            iResId = 0;
        if (iResId > 0) {
            message = context.getResources().getString(iResId);
        }else if (intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            String currentAction = bundle.getString("currentAction","");
            if (ConfirmationEntry.ACTION_CONFIRM_BATCH_CLOSE.equals(currentAction)){
                if( bundle.getBoolean("internalNicknameFlag",false)){
                    message = context.getString(R.string.confirm_reconciliation_close);
                }else {
                    message = context.getString(R.string.confirm_batch_close);
                }
            } else if (ConfirmationEntry.ACTION_REVERSE_PARTIAL_APPROVAL.equals(currentAction)){
                long approvedAmt = bundle.getLong(EntryExtraData.PARAM_APPROVED_AMOUNT);
                long total = bundle.getLong(EntryExtraData.PARAM_TOTAL_AMOUNT);
                long due = total - approvedAmt;
                message = context.getResources().getString(R.string.select_reverse_partial,
                        CurrencyConverter.convert(approvedAmt, ""),
                        CurrencyConverter.convert(due, ""));
            }else if(ConfirmationEntry.ACTION_SUPPLEMENT_PARTIAL_APPROVAL.equals(currentAction)) {
                long approvedAmt = bundle.getLong(EntryExtraData.PARAM_APPROVED_AMOUNT);
                long total = bundle.getLong(EntryExtraData.PARAM_TOTAL_AMOUNT);
                long due = total - approvedAmt;
                message = context.getResources().getString(R.string.select_supplement_partial,
                        CurrencyConverter.convert(approvedAmt, ""),
                        CurrencyConverter.convert(due, ""));
            } else if (ConfirmationEntry.ACTION_CONFIRM_PRINTER_STATUS.equals(currentAction)) {
                //ANFDRC-687 avoid empty value of printStatus
                String key = bundle.getString(EntryExtraData.PARAM_PRINT_STATUS,"");
                if(PrintStatusType.PRINTER_OUT_OF_PAPER.equals(key)){
                    message = context.getString(R.string.prompt_printer_out_of_paper);
                }else if(PrintStatusType.PRINTER_HOT.equals(key)){
                    message = context.getString(R.string.confirm_printer_over_hot);
                }else if(PrintStatusType.PRINTER_VOLTAGE_TOO_LOW.equals(key)){
                    message = context.getString(R.string.confirm_printer_voltage_low);
                }else {
                    message = context.getString(R.string.confirm_printer_status);
                }
            } else if (ConfirmationEntry.ACTION_CONFIRM_BALANCE.equals(currentAction)) {
                String currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "USD");
                CurrencyConverter.setDefCurrency(currency);
                long amount = bundle.getLong(EntryExtraData.PARAM_BALANCE,0L);
                message = context.getString(R.string.confirm_balance) +"\n"+CurrencyConverter.convert(amount, "");
            }
        }
        return message;
    }
}
