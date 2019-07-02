package com.pax.pay.poslink.ui.demo;

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

import com.pax.pay.poslink.ui.demo.activity.ActivityManager;
import com.pax.pay.poslink.ui.demo.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.EnterInvoiceNumHelper;
import com.pax.us.pay.ui.core.helper.EnterTransNumHelper;

public class EnterTransNumActivity extends AppCompatActivity implements View.OnClickListener, EnterInvoiceNumHelper.IEnterInvoiceNumListener {

    TextView promptTv;
    EditText mEditText;
    Button confirmBtn;

    private int minLen, maxLen;

    private EnterTransNumHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);

        promptTv = (TextView) findViewById(R.id.prompt_tv);
        mEditText = (EditText) findViewById(R.id.data_edt);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);

        promptTv.setText("Please Enter Transaction Number");
        minLen = 0;
        maxLen = 300;
        mEditText.setCursorVisible(false);
        mEditText.requestFocus();
        mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        //mEditText.setOnKey
        mEditText.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
        }, 200);

        helper = new EnterTransNumHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    public void onClick(View view) {
        helper.sendNext(Integer.valueOf(mEditText.getText().toString()));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            helper.sendNext(Integer.valueOf(mEditText.getText().toString()));
        }
        return false;
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
        if (message != null)
            promptTv.setText(message);
    }
}
