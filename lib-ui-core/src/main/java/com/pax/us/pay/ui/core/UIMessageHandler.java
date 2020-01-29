package com.pax.us.pay.ui.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.EntryResponse;
import com.pax.us.pay.ui.core.api.IRespStatus;

import java.util.Set;

/**
 * message sender implementation
 */
class UIMessageHandler implements IActionHandler {

    private final BroadcastSender sender;
    private final String packageName;
    private final RespReceiver receiver = new RespReceiver();
    private final Context context;
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
    public void start() {
        if (!isStart) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(EntryResponse.ACTION_ACCEPTED);
            intentFilter.addAction(EntryResponse.ACTION_DECLINED);

            context.registerReceiver(receiver, intentFilter);
            isStart = true;
        }
    }

    @Override
    public void stop() {
        if (isStart) {
            context.unregisterReceiver(receiver);
            isStart = false;
        }
    }

    @Override
    public void sendNext(@Nullable Bundle bundle) {
        UIDataHandler.saveData(context, bundle);
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
                    stop();
                    Log.i("BroadcastReceiver", "ACCEPTED receiver unregisterReceiver :" + context);
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
                default:
                    break;
            }
        }
    }

}
