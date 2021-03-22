package com.pax.us.pay.ui.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.EntryResponse;
import com.pax.us.pay.ui.constant.entry.SecurityEntry;
import com.pax.us.pay.ui.core.api.IRespStatus;

import java.util.Set;

/**
 * message sender implementation
 */
class UIMessageHandler implements IActionHandler {

    private final BroadcastSender sender;
    private final String packageName;
    private final String action;
    private final Set<String> categories;
    private final RespReceiver receiver = new RespReceiver();
    private final Context context;
    private boolean isStart = false;
    private boolean isSend = false;

    @Nullable
    private final IRespStatus resp;

    UIMessageHandler(Context context, @NonNull String action, Set<String> categories, @NonNull String packageName, @Nullable IRespStatus respStatus) {
        this.context = context;
        this.action = action;
        this.categories = categories;
        this.resp = respStatus;
        this.packageName = packageName;
        this.sender = new BroadcastSender(context);
    }

    @Override
    public void start() {
        isSend = false;
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
        if (!isSend) {
            UIDataHandler.saveData(context, bundle);
            Intent intent = new Intent();
            intent.setPackage(packageName);
            intent.setAction(EntryRequest.ACTION_NEXT);
            if (bundle == null)
                bundle = new Bundle();
            bundle.putString(EntryRequest.PARAM_ACTION, action);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            //Log.i("UIDesignReceiver", "sendNext " + bundle.toString());
            sender.send(intent);
            if (!categories.contains(SecurityEntry.CATEGORY))
                isSend = true;
        }
    }

    @Override
    public void setSecurityArea(@NonNull Bundle bundle) {
        bundle.putString(EntryRequest.PARAM_ACTION, action);
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
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_ACTION, action);
        intent.putExtras(bundle);
        sender.send(intent);
    }

    @Override
    public void sendPrev() {
        Intent intent = new Intent();
        intent.setPackage(packageName);
        intent.setAction(EntryRequest.ACTION_PREV);
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_ACTION, action);
        intent.putExtras(bundle);
        sender.send(intent);
    }

    @Override
    public void sendTimeout() {
        Intent intent = new Intent();
        intent.setPackage(packageName);
        intent.setAction(EntryRequest.ACTION_TIME_OUT);
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_ACTION, action);
        intent.putExtras(bundle);
        sender.send(intent);
    }

    private class RespReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (TextUtils.isEmpty(action))
                return;
            //Log.i("UIDesignReceiver", "onReceive action : " + action);
            switch (action) {
                case EntryResponse.ACTION_ACCEPTED:
                    stop();
                    //Log.i("BroadcastReceiver", "ACCEPTED receiver unregisterReceiver :" + context);
                    if (resp != null) {
                        resp.onAccepted();
                    }
                    break;
                case EntryResponse.ACTION_DECLINED:
                    if (resp != null) {
                        resp.onDeclined(intent.getLongExtra(EntryResponse.PARAM_CODE, -1),
                                intent.getStringExtra(EntryResponse.PARAM_MSG));
                        isSend = false;
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
