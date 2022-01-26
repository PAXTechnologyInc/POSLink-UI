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

import androidx.annotation.Nullable;

import android.text.TextUtils;

import com.pax.pay.ui.def.base.FinishRespStatusImpl;
import com.pax.us.pay.ui.core.helper.ConfirmMessageHelper;


public class ConfirmCardEntryRetryDialogActivity extends ConfirmDialogActivity implements ConfirmMessageHelper.IConfirmMessageListener {

    private ConfirmMessageHelper helper = null;

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        if (!TextUtils.isEmpty(message)) {
            setContentMsg(message);
            showSelectDialog(getString(R.string.confirm_continue), getString(R.string.confirm_decline));
        }
    }


    @Override
    protected void loadParam(Intent intent) {
        setContentMsg(getResources().getString(R.string.confirm_card_entry_retry));
        helper = new ConfirmMessageHelper(this, new FinishRespStatusImpl(this));
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

}
