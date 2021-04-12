package com.pax.pay.ui.def.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.pax.pay.ui.def.R;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.paxus.utils.log.Logger;

public abstract class BaseStackActivity extends BaseAppActivity implements IMessageListener {

    public static final String POSLINK_REQUEST_ACTION_ABORT_TRANSACTION = "com.pax.us.pay.poslink.request.ABORT_TRANSACTION";
    private  AbortReceiver abortReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!ActivityStack.getInstance().isExist(this)) {
            ActivityStack.getInstance().push(this);
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(POSLINK_REQUEST_ACTION_ABORT_TRANSACTION);
        abortReceiver = new AbortReceiver();
        registerReceiver(abortReceiver, filter);

        onStartHelper();
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
            }
        }
    }
}
