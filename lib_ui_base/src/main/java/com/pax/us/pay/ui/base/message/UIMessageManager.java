package com.pax.us.pay.ui.base.message;

import android.content.Context;
import android.content.Intent;

import com.pax.us.pay.ui.base.message.api.IRespStatus;
import com.pax.us.pay.ui.base.message.api.IUIListener;
import com.pax.us.pay.ui.base.message.helper.BaseHelper;

public class UIMessageManager {
    static UIMessageManager Instance;

    private UIMessageManager() {
    }

    public static UIMessageManager getInstance() {
        if (Instance == null) {
            Instance = new UIMessageManager();
        }
        return Instance;
    }

    public void registerUI(Context context, IUIListener uiListener, BaseHelper helper, Intent intent, IRespStatus respStatus) {
        UIMessageCenter.getInstance().registerUICenter(context, uiListener, helper, intent, respStatus);
    }

    public void unregisterUI(Context context, BaseHelper helper) {
        UIMessageCenter.getInstance().unregisterUIReceiver(context);
        UIMessageCenter.getInstance().unregisterUICenter(helper);
    }

//    public void registerAction(Context context, final ActionFinish actionFinish) {
//        this.actionFinish = actionFinish;
//        UIMessageCenter.getInstance(context).registerTransactionFinishListener(new UIMessageCenter.TransactionFinishListener() {
//            @Override
//            public void onFinish() {
//                actionFinish.onFinished();
//            }
//        });
//
//    }

//    public interface ActionFinish {
//        void onFinished();
//    }

    ;

    public void unregisterAction(Context context) {
        UIMessageCenter.getInstance().unregisterUIReceiver(context);
        //UIMessageCenter.getInstance().unregisterTransactionFinishListener();
    }
}
