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
package com.pax.pay.poslink.ui.demo.receiver;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.pax.pay.poslink.ui.demo.DialogActivity;
import com.pax.pay.poslink.ui.demo.event.ClssLightEvent;
import com.pax.pay.poslink.ui.demo.event.EventBusUtil;
import com.pax.pay.poslink.ui.demo.event.PINEvent;
import com.pax.us.pay.ui.constant.status.ClssLightStatus;
import com.pax.us.pay.ui.constant.status.PINStatus;

import java.util.Set;

public class StatusReceiver extends android.content.BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (TextUtils.isEmpty(intent.getAction()))
            return;
        Log.i("StatusReceiver", "receive broadcast :" + intent.getAction());
        Set<String> category = intent.getCategories();
        if (category.contains(PINStatus.CATEGORY)) {
            doPinEvent(intent);
            return;
        } else if (category.contains(ClssLightStatus.CATEGORY)) {
            doClssLight(intent);
            return;
        }
        DialogActivity.start(context, intent);
    }

    private void doPinEvent(Intent intent) {
        String action = intent.getAction();
        int length = intent.getIntExtra("pinLength", 0);
        EventBusUtil.doEvent(new PINEvent(action, length));
    }

    private void doClssLight(Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action))
            return;
        EventBusUtil.doEvent(new ClssLightEvent(intent.getAction()));
    }
}
