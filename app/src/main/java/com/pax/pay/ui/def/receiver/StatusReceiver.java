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

import com.pax.pay.ui.def.DialogActivity;
import com.pax.us.pay.ui.component.activity.ActivityLifeCheck;
import com.pax.us.pay.ui.constant.status.InformationStatus;
import com.paxus.utils.log.Logger;

public class StatusReceiver extends android.content.BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (TextUtils.isEmpty(intent.getAction()))
            return;

        // Note: In DialogActivity, if manager isn't on front ground, all status don't need start dialogActivity, only recycle the resource.
        boolean isNeedReceiveFlag = ActivityLifeCheck.isAppFrontGround() && ActivityLifeCheck.isUsingDefUI();
        Logger.d("DialogActivity isNeedReceiveFlag :" + isNeedReceiveFlag + " - " + intent.getAction());
        if (!InformationStatus.TRANS_COMPLETED.equals(intent.getAction()) &&
                !isNeedReceiveFlag) {
            return;
        }
        intent.putExtra("isNeedReceiveMessage", isNeedReceiveFlag);
        Logger.i("DialogActivity Can Be Process Broadcast :" + intent.getAction());
        synchronized (this) {
            IStatusListener listener = StatusListenerHelper.getInstance().getListener();
            if (listener != null) {
                try {
                    listener.setIntent(context, intent);
                } catch (Exception e) {
                    Logger.d("setIntent exception " + e.getMessage());
                    DialogActivity.start(context, intent);
                }
            } else {
                Logger.d("listener is empty ");
                DialogActivity.start(context, intent);
            }
        }

    }

}
