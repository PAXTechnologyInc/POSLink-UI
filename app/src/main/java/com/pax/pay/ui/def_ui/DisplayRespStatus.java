package com.pax.pay.ui.def_ui;

import android.content.Context;

import com.pax.pay.ui.def_ui.eventbus.EventBusConstant;
import com.pax.pay.ui.def_ui.eventbus.EventBusUtil;
import com.pax.pay.ui.def_ui.utils.ToastHelper;
import com.pax.us.pay.ui.base.message.RespMessage;
import com.pax.us.pay.ui.base.message.api.IRespStatus;

public class DisplayRespStatus implements IRespStatus {

    private Context context;
    private DisplayRespStatusListener listener;

    DisplayRespStatus(Context context) {
        this.context = context;
    }

    @Override
    public void respAccept() {
        listener.unRegister();
        EventBusUtil.postEvent(EventBusConstant.END_EVENT);
    }

    @Override
    public void respDecline(RespMessage respMessage) {
        String buff = respMessage.getResultMsg() + "\n (" + respMessage.getResultCode() + ")";
        //Toast.makeText(this, buff, Toast.LENGTH_LONG).show();
        ToastHelper.showMessage(context, buff);
    }

//    @Override
//    public void respComplete(RespMessage respMessage) {
//        String buff;
//        AppManager.getAppManager().finishAllActivity();
//        if (respMessage.getResultCode() == 0) {
//            if (respMessage.getResultMsg() != null)
//                buff = respMessage.getResultMsg();
//            else
//                buff = "Transaction Success!";
//        }
//        else
//            buff = "Transaction Failed!\n "+ respMessage.getResultMsg() + "\n (" + respMessage.getResultCode() + ")" ;
//        ToastHelper.showMessage(context, buff);
//        //getActivity().finish();
//    }

    interface DisplayRespStatusListener {
        void unRegister();
    }

    public void setListener(DisplayRespStatusListener listener) {
        this.listener = listener;
    }
}
