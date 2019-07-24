package com.pax.pay.poslink.ui.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import com.pax.pay.poslink.ui.demo.R;
import com.pax.pay.poslink.ui.demo.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.SecurityHelper;

public class EnterVCodeActivity extends AppCompatActivity implements View.OnClickListener, SecurityHelper.ISecurityListener {

    TextView promptTitle;
    TextView pwdInputText;
    Button confirmBtn;
    private int pinLen;


    private SecurityHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_cvv);

        promptTitle = (TextView) findViewById(R.id.prompt_title);
        pwdInputText = (TextView) findViewById(R.id.pin_input_text);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);


        promptTitle.setText("Please Input V-Code");
        helper = new SecurityHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());
        ActivityLocalManager.getInstance().addActivity(this);
    }


    @Override
    public void onClick(View view) {
        helper.sendNext();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            helper.sendNext();
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
    public void onResume() {
        super.onResume();
        ViewTreeObserver observer = pwdInputText.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                pwdInputText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int[] location = new int[2];
                pwdInputText.getLocationInWindow(location);
                int x = location[0];
                int y = location[1];
                int barHeight = 0;
                int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    barHeight = getResources().getDimensionPixelSize(resourceId);
                }
                helper.setSecurityArea(x, y - barHeight, pwdInputText.getWidth(), pwdInputText.getHeight());
            }
        });
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, boolean isDemo) {
    }

    @Override
    public void onShowAmount(long amount) {

    }

    @Override
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {
    }

}
