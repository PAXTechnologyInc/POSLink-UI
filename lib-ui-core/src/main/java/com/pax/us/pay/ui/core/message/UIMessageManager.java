package com.pax.us.pay.ui.core.message;

import android.content.Context;
import android.content.Intent;

import com.pax.us.pay.ui.core.message.api.IRespStatus;
import com.pax.us.pay.ui.core.message.api.IUIListener;

public class UIMessageManager {
    private static UIMessageManager instance;

    private UIMessageManager() {
    }

    public static UIMessageManager getInstance() {
        if (instance == null) {
            instance = new UIMessageManager();
        }
        return instance;
    }

    public void setCurrentUI(Context context, IUIListener uiListener, BaseActionHelper helper, Intent intent, IRespStatus respStatus) {
        UIMessageHandler.getInstance().setCurrentUI(context, uiListener, helper, intent, respStatus);
    }


    public interface ActionFinish {
        void onFinished();
    }
}
