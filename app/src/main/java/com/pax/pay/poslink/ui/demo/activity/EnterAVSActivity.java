package com.pax.pay.poslink.ui.demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pax.pay.poslink.ui.demo.R;
import com.pax.pay.poslink.ui.demo.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.EnterAVSHelper;

public class EnterAVSActivity extends AppCompatActivity implements View.OnClickListener, EnterAVSHelper.IEnterAvsListener {

    TextView promptTv1;
    EditText mEditText1;
    TextView promptTv2;
    EditText mEditText2;
    Button confirmBtn;

    private int minLen, maxLen;

    private EnterAVSHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_2_layout);

        promptTv1 = (TextView) findViewById(R.id.prompt_tv_1);
        mEditText1 = (EditText) findViewById(R.id.edit_tv_1);
        promptTv2 = (TextView) findViewById(R.id.prompt_tv_2);
        mEditText2 = (EditText) findViewById(R.id.edit_tv_2);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);

        promptTv1.setText("Please input address");
        promptTv2.setText("Please input zip code");
        minLen = 0;
        maxLen = 300;
        mEditText1.setSingleLine(true);
        mEditText1.requestFocus();
        mEditText1.setImeOptions(EditorInfo.IME_ACTION_DONE);
        //mEditText1.setOnEditorActionListener();


        //Click KEY_BACK, then the editTv2 get IME_ACTION_DONE
        mEditText2.setSingleLine(true);
        //mEditText1.setOnEditorActionListener(new View.OnClickListener());
        // mEditText.setCursorVisible(false);

        //mEditText.setOnKey
        mEditText1.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mEditText1, InputMethodManager.SHOW_IMPLICIT);
        }, 200);


        helper = new EnterAVSHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());
        ActivityLocalManager.getInstance().addActivity(this);
    }


    @Override
    public void onClick(View view) {
        helper.sendNext(mEditText1.getText().toString(), mEditText2.getText().toString());
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            helper.sendNext(mEditText1.getText().toString(), mEditText2.getText().toString());
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
    public void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message) {

    }
}
