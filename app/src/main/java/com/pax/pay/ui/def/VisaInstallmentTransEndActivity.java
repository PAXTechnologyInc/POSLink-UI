package com.pax.pay.ui.def;

import android.content.Intent;
import android.os.Bundle;

import com.pax.us.pay.ui.component.utils.TickTimer;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.paxus.view.BaseAppCompatActivity;

public class VisaInstallmentTransEndActivity extends BaseAppCompatActivity {

    private String packageName;
    private String action;
    private TickTimer tickTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa_installment_trans_end);
        action = getIntent().getAction();
        packageName = getIntent().getPackage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent();
        intent.setPackage(packageName);
        intent.setAction(EntryRequest.ACTION_NEXT);
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_ACTION, action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        createTimer();
        tickTimer.start(1);
    }

    private void createTimer() {
        tickTimer = new TickTimer(new TickTimer.OnTickTimerListener() {
            @Override
            public void onFinish() {
                onTimerFinish();
            }

            @Override
            public void onTick(long leftTime) {
                timeOnTick(leftTime);
            }
        });
    }

    protected void onTimerFinish() {
        tickTimerStop();
        finish();
    }

    public void tickTimerStop() {
        if (tickTimer != null) {
            tickTimer.stop();
            tickTimer = null;
        }
    }

    protected void timeOnTick(long leftTime) {
    }

}
