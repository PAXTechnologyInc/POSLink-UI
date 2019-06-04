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

package com.pax.pay.ui.handler;

import android.content.Context;

import java.util.Map;

/**
 * Create by Fahy.F on 3/28/2019
 */
public class EnterTextHandler extends BaseHandler {

    public EnterTextHandler(Context context) {
        super(context);
    }

    public void setAmountRecvPackage(String recvPackage) {
        setRecvPackage(recvPackage);
    }

    public void sendMap(Map<String, String> enterValue) {
        getMessageCenter().sendStringMap(recvPackage, enterValue);
    }

}
