/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2018-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.

 * Module Date: 2019/3/6
 * Module Auth: Justin.Z
 * Description:

 * Revision History:
 * Date                   Author                       Action
 * 2019/3/6               Justin.Z                      Create
 * ============================================================================
 */
package com.pax.pay.ui.def.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.pax.pay.ui.def.expand.ContentResolver;
import com.pax.pay.ui.def.utils.UIControl;
import com.paxus.utils.log.Logger;

public class StartStatusReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (!TextUtils.isEmpty(intent.getAction())) {
            Logger.d( "receive start broadcast :" + intent.getAction());
            UIControl.getInstance().setTransStarted(true);
        }
        ContentResolver.getInstance().clearPreference(context);
    }
}
