package com.pax.us.pay.ui.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.EntryResponse;
import com.pax.us.pay.ui.constant.status.InformationStatus;
import com.pax.us.pay.ui.core.api.IRespStatus;

/**
 * message sender implementation
 */
class UIMessageHandler implements IActionHandler {

    private static final String STATE_ERROR_LOG = "need to load UIMessageHandler firstly";
    private static final String STATE_ERROR_START = "need to start firstly";
    private final BroadcastSender sender;
    private final String packageName;
    private RespReceiver receiver;
    private Context context;
    private boolean isStart = false;

    @Nullable
    private final IRespStatus resp;

    UIMessageHandler(Context context, @NonNull String packageName, @Nullable IRespStatus respStatus) {
        this.context = context;
        this.resp = respStatus;
        this.packageName = packageName;
        this.sender = new BroadcastSender(context);
    }

    @Override
    public void sendNext(@Nullable Bundle bundle) {
        Intent intent = new Intent();
        intent.setPackage(packageName);
        intent.setAction(EntryRequest.ACTION_NEXT);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        sender.send(intent);
    }

    @Override
    public void setSecurityArea(@NonNull Bundle bundle) {
        Intent intent = new Intent();
        intent.setPackage(packageName);
        intent.setAction(EntryRequest.ACTION_SECURITY_AREA);
        intent.putExtras(bundle);
        sender.send(intent);
    }

    @Override
    public void start() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(EntryResponse.ACTION_ACCEPTED);
        intentFilter.addAction(EntryResponse.ACTION_DECLINED);

        try {
            receiver = new RespReceiver();
            context.registerReceiver(receiver, intentFilter);
            isStart = true;
            IntentFilter transactionFilter = new IntentFilter();
            transactionFilter.addAction(InformationStatus.TRANS_COMPLETED);
            transactionFilter.addCategory(InformationStatus.CATEGORY);
            context.registerReceiver(receiver, transactionFilter);
        } catch (Exception e) {
            //throw new IllegalStateException(STATE_ERROR_LOG);
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        if (context == null)
            throw new IllegalStateException(STATE_ERROR_LOG);

        if (isStart == false)
            throw new IllegalStateException(STATE_ERROR_START);

        if (receiver != null) {
            context.unregisterReceiver(receiver);
            receiver = null;
        }
        isStart = false;
    }

    @Override
    public void sendAbort() {
        Intent intent = new Intent();
        intent.setPackage(packageName);
        intent.setAction(EntryRequest.ACTION_ABORT);
        sender.send(intent);
    }

    @Override
    public void sendPrev() {
        Intent intent = new Intent();
        intent.setPackage(packageName);
        intent.setAction(EntryRequest.ACTION_PREV);
        sender.send(intent);
    }

    private class RespReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (TextUtils.isEmpty(action))
                return;
            Log.i("UIDesignReceiver", "onReceive action : " + action);
            switch (action) {
                case EntryResponse.ACTION_ACCEPTED:
                    if (receiver != null) {
                        context.unregisterReceiver(this);
                        receiver = null;
                        Log.i("BroadcastReceiver", "ACCEPTED receiver unregisterReceiver :" + context);
                    }
                    if (resp != null) {
                        resp.onAccepted();
                    }
                    break;
                case EntryResponse.ACTION_DECLINED:
                    if (resp != null) {
                        resp.onDeclined(intent.getLongExtra(EntryResponse.PARAM_CODE, -1),
                                intent.getStringExtra(EntryResponse.PARAM_MSG));
                    }
                    break;
                case InformationStatus.TRANS_COMPLETED:
                    if (receiver != null) {
                        context.unregisterReceiver(this);
                        receiver = null;
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
