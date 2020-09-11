package com.pax.us.pay.ui.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.TransMode;
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
        if (uiListener instanceof IMessageListener) {
            String transType = bundle.getString(EntryExtraData.PARAM_TRANS_TYPE, "");
            String message = bundle.getString(EntryExtraData.PARAM_MESSAGE, "");
            String transMode = bundle.getString(EntryExtraData.PARAM_TRANS_MODE, TransMode.NORMAL);

            String[] options = bundle.getStringArray(EntryExtraData.PARAM_OPTIONS);
            if (options != null && options.length > 0){
                UIDataHandler.setAction(intent.getAction());
                UIDataHandler.setOptions(options);
            }

            String tipName = bundle.getString(EntryExtraData.PARAM_TIP_NAME);
            if (!TextUtils.isEmpty(tipName)){
                UIDataHandler.setTipName(tipName);
            }

            if (transType.length() > 0 || message.length() > 0) {
                ((IMessageListener) uiListener).onShowMessage(transType, message, transMode);
            }
        }
    }

    public void start(Context context, Intent intent) {
        if (actionHandler == null) {
            actionHandler = new UIMessageHandler(context, intent.getAction(), intent.getCategories(),
                    intent.getStringExtra(EntryExtraData.PARAM_PACKAGE), respStatus);
        }
        actionHandler.start();
        this.intent = (Intent) intent.clone();
        if (this.intent.getExtras() != null) {
            //AG3G-49
            UIDataHandler.saveExtraData(context, this.intent.getExtras());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    showUI(uiListener, BaseActionHelper.this.intent.getExtras());
                }
            });
        }
    }

    public void stop() {
        if (actionHandler != null) {
            actionHandler.stop();
            actionHandler = null;
        }
    }
}
