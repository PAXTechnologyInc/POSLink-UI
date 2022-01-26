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

package com.pax.us.pay.ui.core;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * A internal helper broadcast sender class
 */
class BroadcastSender {

    private WeakReference<Context> context;

    BroadcastSender(Context context) {
        this.context = new WeakReference<>(context.getApplicationContext());
    }

    void send(@NonNull Intent data) {
        Context con = context.get();
        if (con != null)
            con.sendBroadcast(data);
    }
}
