package com.pax.pay.ui.def_ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pax.pay.ui.def_ui.App.AppManager;
import com.pax.pay.ui.def_ui.utils.EnterDataLineHelper;
import com.pax.us.pay.ui.base.message.UIMessageManager;
import com.pax.us.pay.ui.base.message.api.IMessageListener;
import com.pax.us.pay.ui.base.message.helper.TransNumHelper;

public class EnterTransNumActivity extends AppCompatActivity implements View.OnClickListener, IMessageListener {

    TextView promptTv;
    EditText mEditText;
    Button confirmBtn;

    private int minLen, maxLen;

    private TransNumHelper helper = new TransNumHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);

        promptTv = (TextView) findViewById(R.id.prompt_tv);
        mEditText = (EditText) findViewById(R.id.data_edt);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);

        promptTv.setText(getResources().getText(R.string.pls_input_transaction_number));
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

        DisplayRespStatus displayRespStatus = new DisplayRespStatus(this);
        displayRespStatus.setListener(new DisplayRespStatus.DisplayRespStatusListener() {
            @Override
            public void unRegister() {
                UIMessageManager.getInstance().unregisterUI(EnterTransNumActivity.this, helper);
            }
        });
        UIMessageManager.getInstance().registerUI(this, this, helper, getIntent(), displayRespStatus);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public void onClick(View view) {
        helper.sendObjNext(mEditText.getText().toString());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            helper.sendObjNext(mEditText.getText().toString());
        }
        return false;
    }

    @Override
    protected void onStop() {
        moveTaskToBack(true);
        super.onStop();
    }


    @Override
    public void onShowMessage(String message) {
        if (message != null)
            promptTv.setText(message);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        EnterDataLineHelper.setEditTextNumber(this, mEditText, maxLen, 0);
    }
}
