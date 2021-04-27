/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2018-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.

 * Module Date: 2019-3-7
 * Module Auth: Justin.Z
 * Description:

 * Revision History:
 * Date                   Author                       Action
 * 2019-3-7               Justin.Z                      Create
 * ============================================================================
 */
package com.pax.pay.ui.def;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.pax.pay.ui.def.base.FinishRespStatusImpl;
import com.pax.us.pay.ui.constant.entry.ConfirmationEntry;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.ConfirmOptionsHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;


public class ConfirmPartialApproveDialogActivity extends ConfirmDialogActivity implements ConfirmOptionsHelper.IComfirmOptionListener {

    private ConfirmOptionsHelper helper = null;

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
    }


    @Override
    protected void loadParam(Intent intent) {
        String action = intent.getAction();
        String message="";

        String currency = intent.getStringExtra(EntryExtraData.PARAM_CURRENCY);
        if(!TextUtils.isEmpty(currency))
            CurrencyConverter.setDefCurrency(currency);

        long approvedAmt = intent.getLongExtra(EntryExtraData.PARAM_APPROVED_AMOUNT, 0L);
        long total = intent.getLongExtra(EntryExtraData.PARAM_TOTAL_AMOUNT, 0L);
        long due = total - approvedAmt;
        if (ConfirmationEntry.ACTION_REVERSE_PARTIAL_APPROVAL.equals(action)) {
            message = getString(R.string.partial_approve_prompt)+CurrencyConverter.convert(due, "");
        } else if (ConfirmationEntry.ACTION_SUPPLEMENT_PARTIAL_APPROVAL.equals(action)){
            message = getString(R.string.partial_approve_prompt_pmt,
                    CurrencyConverter.convert(due, ""));
//            message = getString(com.pax.us.pay.ui.message.R.string.approve_amount) + CurrencyConverter.convert(approvedAmt, "") + "\n" +
//                    getString(com.pax.us.pay.ui.message.R.string.due) + CurrencyConverter.convert(due, "") + "; " +
//                    getString(R.string.select_supplement_partial);
        }

        setContentMsg(message);

        helper = new ConfirmOptionsHelper(this, new FinishRespStatusImpl(this));
    }

    @Override
    protected void onStart() {
        helper.start(this, getIntent());
        super.onStart();
    }

    @Override
    protected void sendNext(boolean flag) {
        helper.sendNext(flag);
    }

    @Override
    protected void sendAbort() {
        helper.sendAbort();
    }

    @Override
    protected void onStop() {
        helper.stop();
        super.onStop();
    }

    @Override
    public void onShowOptions(@NonNull String[] options) {
        if (options.length > 0) {
            String positiveMessage = options[0];
            String negativeMessage;
            if (options.length > 1)
                negativeMessage = options[1];
            else
                negativeMessage = getString(R.string.dialog_cancel);
            showSelectDialog(positiveMessage, negativeMessage);
        }
    }
}
