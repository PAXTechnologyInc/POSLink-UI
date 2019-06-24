/*
 *
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2009-2020 PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.
 * Description: // Detail description about the function of this module,
 *             // interfaces with the other modules, and dependencies.
 * ============================================================================
 *
 */

package com.pax.us.pay.ui.base.message;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * A internal helper broadcast sender class
 */

class BroadcastSender {

    private Context context;
    private Intent intent;

    public BroadcastSender(Context context) {
        this.context = context.getApplicationContext();
    }


    public BroadcastSender(Context context, Intent intent) {
        this.context = context.getApplicationContext();
        this.intent = intent;
    }

    public void send(String packageName, String targetAction, Intent data) {
        if (!TextUtils.isEmpty(packageName)) {
            data.setPackage(packageName);
        }
        data.setAction(targetAction);
        context.sendBroadcast(data);
    }

    public void send(Intent data) {
        if (data != null)
            context.sendBroadcast(data);
    }

}
