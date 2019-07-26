package com.pax.pay.poslink.ui.demo.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pax.pay.poslink.ui.demo.R;
import com.pax.pay.poslink.ui.demo.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.ConfirmMessageHelper;

/**
 * Created by Charles.S on 2017/5/5.
 */

public class ConfirmCardPresentActivity extends AppCompatActivity implements View.OnClickListener, ConfirmMessageHelper.IConfirmMessageListener {

    TextView promptTv;
    Button cancelBtn;
    Button confirmBtn;

    private ConfirmMessageHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_message);

        promptTv = (TextView) findViewById(R.id.prompt_tv);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(this);

        promptTv.setText("Card Persent?");
        helper = new ConfirmMessageHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());
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
        moveTaskToBack(false);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        helper.stop();
        super.onDestroy();
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, boolean isDemo) {

    }
}
