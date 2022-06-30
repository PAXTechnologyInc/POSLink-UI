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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.ConfirmOptionsHelper;


public class ConfirmOKDialogActivity extends ConfirmDialogActivity implements ConfirmOptionsHelper.IComfirmOptionListener {

    private ConfirmOptionsHelper helper = null;

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
    }


    @Override
    protected void loadParam(Intent intent) {
        setContentMsg(getResources().getString(R.string.confirm_adjust_tip));
        helper = new ConfirmOptionsHelper(this, new RespStatusImpl(this));
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
            //setContentMsg(message);
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
