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

import com.pax.pay.ui.def.eventbus.AmountEvent;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.pax.us.pay.ui.constant.status.StatusData;
import com.paxus.utils.log.Logger;

public class AmountStatusReceiver extends android.content.BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        if (!StatusActionCheck.isNeedReceiveMessage(context)) {
//            return;
//        }

        if (TextUtils.isEmpty(intent.getAction()))
            return;
        Logger.d( "receive broadcast :" + intent.getAction());
        long amount = intent.getLongExtra(StatusData.PARAM_TOTAL_AMOUNT, 0);
        EventBusUtil.doEvent(new AmountEvent(intent.getAction(), amount));

    }


}
