package com.pax.pay.poslink.ui.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pax.pay.poslink.ui.demo.R;
import com.pax.pay.poslink.ui.demo.base.RespStatusImpl;
import com.pax.pay.poslink.ui.demo.utils.StringUtils;
import com.pax.us.pay.ui.core.helper.EnterFuelAmountHelper;

import java.util.Locale;

public class EnterFuelAmountActivity extends AppCompatActivity implements View.OnClickListener, EnterFuelAmountHelper.IEnterFuelAmountListener {

    TextView promptTv;
    EditText mEditText;
    Button confirmBtn;

    private long amount;
    private String currencyName;
    private int minLen, maxLen;
    private Locale locale;

    private EnterFuelAmountHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);

        promptTv = findViewById(R.id.prompt_tv);
        mEditText = (EditText) findViewById(R.id.data_edt);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);


        promptTv.setText("Please Input Fuel Amount");
        minLen = 0;
        maxLen = 12;
        mEditText.setCursorVisible(false);
        mEditText.requestFocus();
        mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        helper = new EnterFuelAmountHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());

    }


    @Override
    public void onClick(View view) {
        long amount = StringUtils.parseLong(mEditText.getText().toString(), -1);
        helper.sendNext(amount);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            confirmBtn.performClick();
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
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {
        if (isPoint)
            promptTv.setText("Please Input Fuel Point");

    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, boolean isDemo) {
    }
}
