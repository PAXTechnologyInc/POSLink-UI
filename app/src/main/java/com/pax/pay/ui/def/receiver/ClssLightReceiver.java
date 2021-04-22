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

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.pax.pay.ui.def.eventbus.ClssLightEvent;
import com.pax.pay.ui.def.eventbus.EventBusUtil;

public class ClssLightReceiver extends android.content.BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

//        if (!StatusActionCheck.isNeedReceiveMessage(context)) {
//            return;
//        }

        if (TextUtils.isEmpty(intent.getAction()))
            return;
        Log.i("ClssLightReceiver", "receive broadcast :" + intent.getAction());
        EventBusUtil.doEvent(new ClssLightEvent(intent.getAction()));

    }


}
