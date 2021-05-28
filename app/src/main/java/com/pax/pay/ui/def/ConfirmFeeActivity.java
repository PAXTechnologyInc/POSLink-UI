package com.pax.pay.ui.def;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pax.pay.ui.def.base.FinishRespStatusImpl;
import com.pax.pay.ui.def.dialog.FeeAlertDialog;
import com.pax.pay.ui.def.eventbus.ConfirmDialogEndEvent;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.api.IUIListener;
import com.pax.us.pay.ui.core.helper.ConfirmFeeHelper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author arvind
 */

public abstract class ConfirmFeeActivity extends AppCompatActivity implements IUIListener {

    String currency = CurrencyType.USD;
    private ConfirmFeeHelper helper;

    String feeName;
    long totalAmount;
    long feeAmount;
    private FeeAlertDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        feeName = getString(R.string.additional_fee);
        EventBusUtil.doEvent(new ConfirmDialogEndEvent());
        EventBusUtil.register(this);

        loadParam(getIntent());
//        Bundle bundle = getIntent().getExtras();
//        feeName = bundle.getString(EntryExtraData.PARAM_SURCHARGE_FEE_NAME, feeName);
//        totalAmount = bundle.getLong(EntryExtraData.PARAM_TOTAL_AMOUNT);
//        feeAmount = bundle.getLong(EntryExtraData.PARAM_SURCHARGE_FEE);

        helper = new ConfirmFeeHelper(this, new FinishRespStatusImpl(this));
        Log.i("ConfirmFeeActivity", "onCreate ");

    }

    private void displayDialog(Context context) {
        if (dialog != null) {
            dialog.setTableContent(feeName, totalAmount, feeAmount);
            dialog.setCancelText(context.getString(R.string.dialog_no));
            dialog.setConfirmText(context.getString(R.string.dialog_yes));
            Log.i("ConfirmFeeActivity", "dialog.reset ");
        } else {
            dialog = new FeeAlertDialog(context, currency);
            dialog.setTableContent(feeName, totalAmount, feeAmount);
            dialog.setCancelClickListener(alertDialog -> {
                Log.i("ConfirmFeeActivity", "dialog.dismiss cancel ");
                dialog.dismiss();
                dialog = null;
                sendNext(false);
            }).setConfirmClickListener(alertDialog -> {
                Log.i("ConfirmFeeActivity", "dialog.dismiss confirm ");
                dialog.dismiss();
                dialog = null;
                sendNext(true);
            }).setKeycodeBackClickListener(alertDialog -> {
                Log.i("ConfirmFeeActivity", "dialog.dismiss back ");
                dialog.dismiss();
                dialog = null;
                sendAbort();
            }).create();
            dialog.setCancelButtonColor(R.drawable.btn_bg_dark);
            dialog.showCancelButton(true);
            dialog.setCancelText(context.getString(R.string.dialog_no));
            dialog.setCancelButtonColor(R.drawable.btn_bg_dark);
            dialog.showConfirmButton(true);
            dialog.setConfirmText(context.getString(R.string.dialog_yes));
            //dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
            try {
                dialog.show();
                Log.i("ConfirmFeeActivity", "new dialog.show ");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void sendNext(boolean flag) {
        helper.sendNext(flag);
    }

    private void sendAbort() {
        helper.sendAbort();
    }

    @Override
    protected void onStart() {
        helper.start(this, getIntent());
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i("ConfirmFeeActivity", "onResume ");
        super.onResume();
        new Handler().postDelayed(() -> {
            Log.i("ConfirmFeeActivity", "displayDialog ");
            displayDialog(this);
        }, 100);
    }

    @Override
    protected void onStop() {
        helper.stop();
        super.onStop();
    }

    @Override
    public void finish() {
        Log.i("ConfirmFeeActivity", "finish");
        super.finish();
        //remove activity animation
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onDestroy() {
        Log.i("ConfirmFeeActivity", "onDestroy");
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        EventBusUtil.unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEndEvent(ConfirmDialogEndEvent event) {
        Log.i("ConfirmFeeActivity", "ConfirmDialogEndEvent");
        EventBusUtil.unregister(this);
        finish();
    }

    protected abstract void loadParam(Intent intent);

}
