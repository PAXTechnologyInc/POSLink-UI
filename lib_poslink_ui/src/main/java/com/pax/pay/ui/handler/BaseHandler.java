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

import com.pax.pay.ui.message.UIMessageCenter;

import androidx.annotation.Nullable;

/**
 * Create by Fahy.F on 3/28/2019
 */
public abstract class BaseHandler {
    protected String recvPackage;
    private Context context;

    public BaseHandler() {

    }

    public BaseHandler(Context context) {
        this.context = context;
    }

    public BaseHandler(@Nullable String recvPackage) {
        this.recvPackage = recvPackage;
    }

    public void setRecvPackage(String recvPackage) {
        this.recvPackage = recvPackage;
    }

    protected UIMessageCenter getMessageCenter() {
        return UIMessageCenter.getInstance(context);
    }

    public void sendNext() {
        getMessageCenter().sendNext(recvPackage);
    }


    public void sendNext(String key, String enterValue) {
        getMessageCenter().sendNext(recvPackage, key, enterValue);
    }

    public void sendNext(String key, int value) {
        getMessageCenter().sendNext(recvPackage, key, value);
    }

    public void sendNext(String key, long value) {
        getMessageCenter().sendNext(recvPackage, key, value);
    }

    public void sendNext(String key, short[] value) {
        getMessageCenter().sendNext(recvPackage, key, value);
    }

    public void sendNext(String key, byte[] enterValue) {
        getMessageCenter().sendNext(recvPackage, key, enterValue);
    }

    public void sendAbort() {
        getMessageCenter().sendAbort(recvPackage);
    }

    public void sendPrev() {
        getMessageCenter().sendPrev(recvPackage);
    }

}
