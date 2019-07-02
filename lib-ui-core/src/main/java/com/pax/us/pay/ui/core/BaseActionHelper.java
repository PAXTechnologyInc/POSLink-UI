package com.pax.us.pay.ui.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryInput;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public abstract class BaseActionHelper {

    private static final String STATE_ERROR_LOG = "need to call start firstly";

    private IActionHandler actionHandler;

    @Nullable
    private IUIListener uiListener;
    @Nullable
    private IRespStatus respStatus;

    private Intent intent;
    private Handler handler = new Handler();

    protected BaseActionHelper(@Nullable IUIListener uiListener, @Nullable IRespStatus respStatus) {
        this.uiListener = uiListener;
        this.respStatus = respStatus;
    }

    protected void sendNext(Bundle bundle) {
        if (actionHandler == null) {
            throw new IllegalStateException(STATE_ERROR_LOG);
        }
        actionHandler.sendNext(bundle);
    }

    protected void sendNext() {
        sendNext(null);
    }

    public void sendAbort() {
        if (actionHandler == null) {
            throw new IllegalStateException(STATE_ERROR_LOG);
        }
        actionHandler.sendAbort();
    }

    public void sendPrev() {
        if (actionHandler == null) {
            throw new IllegalStateException(STATE_ERROR_LOG);
        }
        actionHandler.sendPrev();
    }

    protected void setSecurityArea(Bundle bundle) {
        if (actionHandler == null) {
            throw new IllegalStateException(STATE_ERROR_LOG);
        }
        actionHandler.setSecurityArea(bundle);
    }

    protected final void decline(int code, String message) {
        if (respStatus != null) {
            respStatus.onDeclined(code, message);
        }
    }

    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        String transType = "";
        String message = "";
        if (uiListener instanceof IMessageListener) {
            if (bundle.containsKey(EntryInput.PARAM_TRANS_TYPE))
                transType = bundle.getString(EntryInput.PARAM_TRANS_TYPE);
            if (bundle.containsKey(EntryInput.PARAM_MESSAGE))
                message = bundle.getString(EntryInput.PARAM_MESSAGE);
            if (transType.length() > 0 || message.length() > 0)
                ((IMessageListener) uiListener).onShowMessage(transType, message);
        }
    }

    public void start(Context context, Intent intent) {
        if (actionHandler == null) {
            actionHandler = new UIMessageHandler(context, intent.getStringExtra(EntryInput.PARAM_PACKAGE), respStatus);
        }

        //this.intent = intent.cloneFilter(); //cloneFilter don't clone Extras data to new intent
        this.intent = (Intent) intent.clone();
        if (this.intent.getExtras() != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    showUI(uiListener, BaseActionHelper.this.intent.getExtras());
                }
            });
        }
    }
}
