package com.pax.us.pay.ui.protocol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.protocol.handler.RespStatusImpl;
import com.pax.us.pay.ui.protocol.utils.StringUtils;

import java.util.Locale;

public class EnterAmountActivity extends AppCompatActivity implements View.OnClickListener {

    TextView promptTv;
    EditText mEditText;
    Button confirmBtn;

    private long amount;
    private String currencyName;
    private int minLen, maxLen;
    private Locale locale;
    private String packageName;

    private ActionHandlerImp helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);

        promptTv = findViewById(R.id.prompt_tv);
        mEditText = (EditText) findViewById(R.id.data_edt);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);


        parseIntent(getIntent());
        minLen = 0;
        maxLen = 12;
        mEditText.setCursorVisible(false);
        mEditText.requestFocus();
        mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        helper = new ActionHandlerImp(this, packageName, new RespStatusImpl(this));
        helper.start();
    }

    @Override
    protected void onStart() {
        moveTaskToBack(false);
        super.onStart();
        helper.stop();
    }

    @Override
    public void onClick(View view) {
        long amount = StringUtils.parseLong(mEditText.getText().toString(), -1);

        helper.sendNext(packBundle(amount));
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
        helper.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void parseIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        String action = intent.getAction();
        packageName = intent.getStringExtra(EntryExtraData.PARAM_PACKAGE);
        String transType = bundle.getString(EntryExtraData.PARAM_TRANS_TYPE);
        String message = bundle.getString(EntryExtraData.PARAM_MESSAGE);
        promptTv.setText(TextUtils.isEmpty(message) ? "Please Input Amount" : message);
        String transMode = bundle.getString(EntryExtraData.PARAM_TRANS_MODE);
        String currency = bundle.getString(EntryExtraData.PARAM_CURRENCY);
    }

    private Bundle packBundle(long amount) {
        Bundle bundle = new Bundle();
        bundle.putLong(EntryRequest.PARAM_AMOUNT, amount);
        return bundle;
    }

}
