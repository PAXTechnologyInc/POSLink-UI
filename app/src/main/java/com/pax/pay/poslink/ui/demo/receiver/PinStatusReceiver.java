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

import com.pax.pay.poslink.ui.demo.event.EventBusUtil;
import com.pax.pay.poslink.ui.demo.event.PINEvent;

public class PinStatusReceiver extends android.content.BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (TextUtils.isEmpty(intent.getAction()))
            return;
        String action = intent.getAction();
        long length = intent.getLongExtra("pinLength", 0);
        EventBusUtil.doEvent(new PINEvent(action, length));

    }
}
