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
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.ConfirmDccHelper;


public class ConfirmDccDialogActivity extends ConfirmDialogActivity implements ConfirmDccHelper.IConFirmDccListener {

    private ConfirmDccHelper helper = null;

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
    }


    @Override
    protected void loadParam(Intent intent) {
        helper = new ConfirmDccHelper(this, new RespStatusImpl(this));
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
        if (options.length > 1) {
            String positiveMessage = options[0];
            String negativeMessage = options[1];
            showSelectDialog(positiveMessage, negativeMessage);
        }
    }

    @Override
    public void onShowDcc(@Nullable String amountMessage, @Nullable String cardCurrency, @Nullable String exchangeRate, @Nullable String margin, @Nullable String foreignAmountMessage) {
        StringBuilder contentMsg = new StringBuilder();
        if (!TextUtils.isEmpty(amountMessage)) {
            contentMsg.append("USD "+amountMessage + "\n");
        }
        if (!TextUtils.isEmpty(exchangeRate) && !TextUtils.isEmpty(cardCurrency)){
            contentMsg.append("1 USD = "+exchangeRate + " " + cardCurrency + "\n");
        }
        if (!TextUtils.isEmpty(margin)) {
            contentMsg.append("Int'l Margin "+margin + "%\n");
        }
        if (!TextUtils.isEmpty(foreignAmountMessage) && !TextUtils.isEmpty(cardCurrency)){
            contentMsg.append(cardCurrency + " " + foreignAmountMessage);
        }
        setContentMsg(contentMsg.toString());
    }
}
