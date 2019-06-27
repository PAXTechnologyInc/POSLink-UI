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

/**
 * A internal helper broadcast sender class
 */

class BroadcastSender {

    private Context context;

    BroadcastSender(Context context) {
        this.context = context.getApplicationContext();
    }

    void send(Intent data) {
        if (data != null)
            context.sendBroadcast(data);
    }
}
