package com.pax.pay.ui.def.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.View;

import com.pax.pay.ui.def.R;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.TransMode;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.paxus.utils.log.Logger;

public abstract class BaseStackActivity extends BaseAppActivity implements IMessageListener {

    public static final String POSLINK_REQUEST_ACTION_ABORT_TRANSACTION = "com.pax.us.pay.poslink.request.ABORT_TRANSACTION";
    public static final String POSLINK_REQUEST_ACTION_RESET = "com.pax.us.pay.poslink.request.RESET";
    private  AbortReceiver abortReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!ActivityStack.getInstance().isExist(this)) {
            ActivityStack.getInstance().push(this);
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(POSLINK_REQUEST_ACTION_ABORT_TRANSACTION);
        filter.addAction(POSLINK_REQUEST_ACTION_RESET);
        abortReceiver = new AbortReceiver();
        registerReceiver(abortReceiver, filter);

        onStartHelper();
    }
    @Override
    protected void loadParam(){
        Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey(EntryExtraData.PARAM_TRANS_MODE) && TransMode.DEMO.equals(bundle.getString(EntryExtraData.PARAM_TRANS_MODE))){
            navTitle = "Demo";
        } else
            navTitle = "BroadPOS";
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        //setTitle(transName);
        setDemo(transMode);
    }

    @Override
    protected void loadExpandedView(Bundle savedInstanceState){
        super.loadExpandedView(savedInstanceState);
        View expandedView = findViewById(R.id.expanded_info_container);
        expandedView.setVisibility(View.GONE);

        return;
    }

    @Override
    protected void onDestroy(){
        if (abortReceiver != null)
            unregisterReceiver(abortReceiver);
        super.onDestroy();
        try {
            if (ActivityStack.getInstance().contains(this)) {
                ActivityStack.getInstance().removeAndFinish(this);
            }
        } catch (Exception e) {
            Logger.e(e);
            super.onDestroy();
        }
    }


    public abstract void onStartHelper();

    public abstract void onAbortHelper();

    private class AbortReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (POSLINK_REQUEST_ACTION_ABORT_TRANSACTION.equals(intent.getAction())){
                onAbortHelper();
            }  else if (POSLINK_REQUEST_ACTION_RESET.equals(intent.getAction())){
                ActivityStack.getInstance().popAll();
            }
        }
    }
}
