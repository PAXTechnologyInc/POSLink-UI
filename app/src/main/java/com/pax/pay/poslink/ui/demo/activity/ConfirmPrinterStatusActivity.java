package com.pax.pay.poslink.ui.demo.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pax.pay.poslink.ui.demo.R;
import com.pax.pay.poslink.ui.demo.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.enumeration.PrintStatusType;
import com.pax.us.pay.ui.core.helper.ConfirmPrinterStatusHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Charles.S on 2017/5/5.
 */

public class ConfirmPrinterStatusActivity extends AppCompatActivity implements View.OnClickListener, ConfirmPrinterStatusHelper.IConfirmPrinterStatusListener {

    private static Map<String, String> messageMap = new HashMap<>();

    static {
        messageMap.put(PrintStatusType.PRINTER_OUT_OF_PAPER, "Printer Out Of Paper, Retry?");
        messageMap.put(PrintStatusType.PRINTER_HOT, "Printer Over Hot, Retry?");
        messageMap.put(PrintStatusType.PRINTER_VOLTAGE_TOO_LOW, "Printer Voltage Too Low, Retry?");
    }

    TextView promptTv;
    Button cancelBtn;
    Button confirmBtn;

    private ConfirmPrinterStatusHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_message);

        promptTv = (TextView) findViewById(R.id.prompt_tv);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(this);

        promptTv.setText("Printer Error!");
        helper = new ConfirmPrinterStatusHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());
        ActivityLocalManager.getInstance().addActivity(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.confirm_btn)
            helper.sendNext(true);
        else if (view.getId() == R.id.cancel_btn)
            helper.sendNext(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        }
        return false;
    }

    @Override
    protected void onStop() {
        helper.stop();
        moveTaskToBack(false);
        super.onStop();
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, boolean isDemo) {

    }

    @Override
    public void onShowPrinterStatus(String printerStatus) {
        if (!TextUtils.isEmpty(printerStatus))
            promptTv.setText(messageMap.get(printerStatus));
        else
            finish();
    }
}
