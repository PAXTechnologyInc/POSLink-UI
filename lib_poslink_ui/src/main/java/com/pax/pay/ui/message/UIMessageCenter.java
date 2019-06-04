package com.pax.pay.ui.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;

import com.pax.pay.ui.constant.ActivityBroadcastAction;
import com.pax.pay.ui.constant.EUIMessageKey;
import com.pax.pay.ui.constant.StatusBroadcastAction;
import com.pax.pay.ui.eventbus.EventBusConstant;
import com.pax.pay.ui.eventbus.EventBusUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.MutableLiveData;

public class UIMessageCenter {
    private Context context;
    private List<Intent> receiverDataCacheList = new LinkedList<>();
    private BroadcastReceiver receiver;
    private BroadcastSender sender;
    private MutableLiveData<RespMessage> liveData;

    private TransactionFinishListener transFinishListener;

    private static UIMessageCenter instance;

    public static UIMessageCenter getInstance(Context context) {
        if (instance == null) {
            instance = new UIMessageCenter(context.getApplicationContext());
        }
        return instance;
    }

    private UIMessageCenter(Context context) {
        this.context = context.getApplicationContext();
    }

    /**
     * This should be called in your Application onCreate. This initialize the BroadcastReceiver.
     */
    public void registerUIDesignReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ActivityBroadcastAction.BROADCAST_ACTION_ACCEPTED);
        intentFilter.addAction(ActivityBroadcastAction.BROADCAST_ACTION_DECLINED);
        intentFilter.addAction(ActivityBroadcastAction.BROADCAST_ACTION_AREA);
        intentFilter.addAction(StatusBroadcastAction.TRANS_COMPLETED);
        registerUIDesignReceiver(intentFilter);
    }

    private void registerUIDesignReceiver(IntentFilter intentFilter) {
        receiverDataCacheList.clear();
        sender = new BroadcastSender(context);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                final RespMessage respMessage = new RespMessage();
                respMessage.setReqAction(action);
                if (TextUtils.isEmpty(action))
                    return;
                Log.i("UIDesignReceiver", "onReceive action : " + action);
                //Toast.makeText(context, "receive UIMessage : " + action, Toast.LENGTH_SHORT).show();
                switch (action) {
                    case ActivityBroadcastAction.BROADCAST_ACTION_ACCEPTED:
                        notifyUI(respMessage);
                        if (receiver != null) {
                            context.getApplicationContext().unregisterReceiver(receiver);
                            receiver = null;
                        }
                        EventBusUtil.postEvent(EventBusConstant.END_EVENT);
                        break;
                    case ActivityBroadcastAction.BROADCAST_ACTION_DECLINED:
                        respMessage.setResultCode(intent.getStringExtra(EUIMessageKey.IntentKey.RESULT_CODE));
                        respMessage.setResultMsg(intent.getStringExtra(EUIMessageKey.IntentKey.RESULT_MSG));
                        notifyUI(respMessage);
                        break;
                    case StatusBroadcastAction.TRANS_COMPLETED:
                        notifyUI(respMessage);
                        receiverDataCacheList.clear();
                        if (transFinishListener != null) {
                            transFinishListener.onFinish();
                        } else {
                            receiverDataCacheList.add(intent);
                        }
                        break;
                    default:
                        // No need to response to invalid action
                        break;
                }
            }
        };
        context.registerReceiver(receiver, intentFilter);
    }

    public void registerUIDesignListener(MutableLiveData<RespMessage> liveData) {
        this.liveData = liveData;
    }

    private void notifyUI(RespMessage action) {
        if (liveData != null) {
            liveData.postValue(action);
        }
    }

    public void registerTransactionFinishListener(TransactionFinishListener transFinishListener) {
        this.transFinishListener = transFinishListener;
        Intent cacheIntent = detectCacheAction(StatusBroadcastAction.TRANS_COMPLETED);
        if (cacheIntent != null) {
            transFinishListener.onFinish();
            receiverDataCacheList.remove(cacheIntent);
        }
    }

    public void unregisterTransactionFinishListener() {
        transFinishListener = null;
        receiverDataCacheList.clear();
    }


    public void init() {
        transFinishListener = null;
        receiverDataCacheList.clear();
    }

    private Intent detectCacheAction(String action) {
        for (Intent intent : receiverDataCacheList) {
            String curAction = intent.getAction();
            if (action.equals(curAction)) {
                return intent;
            }

        }
        return null;
    }


    public interface TransactionFinishListener {
        void onFinish();
    }


    public void sendNext(String senderPackage) {
        Intent intent = new Intent();
        sender.send(senderPackage, ActivityBroadcastAction.BROADCAST_ACTION_NEXT, intent);
    }

    /**
     * You need call this value entered. <br>
     *
     * @param senderPackage The package name of the transaction service.
     * @param value         The value entered.
     */
    public void sendNext(String senderPackage, String key, String value) {
        Intent intent = new Intent();
        intent.putExtra(key, value);
        sender.send(senderPackage, ActivityBroadcastAction.BROADCAST_ACTION_NEXT, intent);
    }

    public void sendNext(String senderPackage, String key, int value) {
        Intent intent = new Intent();
        intent.putExtra(key, value);
        sender.send(senderPackage, ActivityBroadcastAction.BROADCAST_ACTION_NEXT, intent);
    }

    public void sendNext(String senderPackage, String key, long value) {
        Intent intent = new Intent();
        intent.putExtra(key, value);
        sender.send(senderPackage, ActivityBroadcastAction.BROADCAST_ACTION_NEXT, intent);
    }

    public void sendNext(String senderPackage, String key, byte[] value) {
        Intent intent = new Intent();
        intent.putExtra(key, value);
        sender.send(senderPackage, ActivityBroadcastAction.BROADCAST_ACTION_NEXT, intent);
    }

    public void sendNext(String senderPackage, String key, short[] value) {
        Intent intent = new Intent();
        intent.putExtra(key, value);
        sender.send(senderPackage, ActivityBroadcastAction.BROADCAST_ACTION_NEXT, intent);
    }

    public void sendLongMap(String senderPackage, Map<String, Long> map) {
        Intent intent = new Intent();
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            intent.putExtra(entry.getKey(), entry.getValue());
        }
        sender.send(senderPackage, ActivityBroadcastAction.BROADCAST_ACTION_NEXT, intent);
    }

    public void sendStringMap(String senderPackage, Map<String, String> map) {
        Intent intent = new Intent();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            intent.putExtra(entry.getKey(), entry.getValue());
        }
        sender.send(senderPackage, ActivityBroadcastAction.BROADCAST_ACTION_NEXT, intent);
    }

    /**
     * When you need to terminate the transaction, you can call this. <br>
     *
     * @param senderPackage The package name of the transaction service.
     */
    public void sendAbort(String senderPackage) {
        Intent intent = new Intent();
        sender.send(senderPackage, ActivityBroadcastAction.BROADCAST_ACTION_ABORT, intent);
    }


    /**
     * When you need to return the previous step of the transaction, you can call this. <br>
     *
     * @param senderPackage The package name of the transaction service.
     */
    public void sendPrev(String senderPackage) {
        Intent intent = new Intent();
        sender.send(senderPackage, ActivityBroadcastAction.BROADCAST_ACTION_PREV, intent);
    }

    /**
     * Send input account float view area
     *
     * @param senderPackage The package name of the transaction service.
     * @param width         float view width
     * @param height        float view height
     * @param offsetX       float view x offset to screen's left edge
     * @param offsetY       float view y offset to screen's top edge
     */
    public void sendInputArea(String senderPackage, int width, int height, int offsetX, int offsetY) {
        Intent intent = new Intent();
        intent.putExtra(EUIMessageKey.INTENT_KEY_X, offsetX);
        intent.putExtra(EUIMessageKey.INTENT_KEY_Y, offsetY);
        intent.putExtra(EUIMessageKey.INTENT_KEY_WIDTH, width);
        intent.putExtra(EUIMessageKey.INTENT_KEY_HEIGHT, height);
        sender.send(senderPackage, ActivityBroadcastAction.BROADCAST_ACTION_AREA, intent);
    }


}
