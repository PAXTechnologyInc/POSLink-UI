package com.pax.pay.poslink.ui.demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pax.pay.poslink.ui.demo.R;
import com.pax.pay.poslink.ui.demo.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.VoucherHelper;

public class EnterVoucherActivity extends AppCompatActivity implements View.OnClickListener, VoucherHelper.IEnterVoucherListener {

    TextView promptTv;
    EditText mEditText;
    Button confirmBtn;

    private int minLen, maxLen;

    private VoucherHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_2_layout);

        promptTv = (TextView) findViewById(R.id.prompt_tv_1);
        mEditText = (EditText) findViewById(R.id.edit_tv_1);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);

        promptTv.setText("Please Enter Voucher Number");
        minLen = 0;
        maxLen = 30;
        mEditText.setSingleLine(true);
        mEditText.requestFocus();
        mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        //mEditText.setOnEditorActionListener();


        //mEditText.setOnKey
        mEditText.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
        }, 200);


        helper = new VoucherHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());
        ActivityLocalManager.getInstance().addActivity(this);
    }


    @Override
    public void onClick(View view) {
        helper.sendNext(mEditText.getText().toString());
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            helper.sendNext(mEditText.getText().toString());
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        moveTaskToBack(true);
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message) {

    }

    @Override
    public void onShowAmount(long amount) {

    }

    @Override
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {
    }
}
