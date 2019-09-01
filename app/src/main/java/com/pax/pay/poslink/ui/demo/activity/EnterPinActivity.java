package com.pax.pay.poslink.ui.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.pax.pay.poslink.ui.demo.R;
import com.pax.pay.poslink.ui.demo.base.RespStatusImpl;
import com.pax.pay.poslink.ui.demo.event.EventBusUtil;
import com.pax.pay.poslink.ui.demo.event.PINEvent;
import com.pax.us.pay.ui.constant.entry.enumeration.PinStyles;
import com.pax.us.pay.ui.constant.status.PINStatus;
import com.pax.us.pay.ui.core.helper.EnterPinHelper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EnterPinActivity extends AppCompatActivity implements View.OnClickListener, EnterPinHelper.IEnterPinListener {

    TextView amountTv;
    TextView promptTitle;
    TextView pwdInputText;
    private int pinLen;


    private EnterPinHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);
        //moveTaskToFront();
        EventBusUtil.register(this);

        amountTv = (TextView) findViewById(R.id.amount_tv);
        promptTitle = (TextView) findViewById(R.id.prompt_title);
        pwdInputText = (TextView) findViewById(R.id.pin_input_text);


        promptTitle.setText("Please Enter PIN");
        helper = new EnterPinHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());


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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAsync(PINEvent event) {
        String action = (String) event.getStatus();
        switch (action) {
            case PINStatus.PIN_ENTERING:
                pinLen++;
                String star = "*";
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pinLen; i++) {
                    sb.append(star);
                }
                pwdInputText.setText(sb.toString());
                break;
            case PINStatus.PIN_ENTER_CLEARED:
                pinLen = 0;
                pwdInputText.setText("");
                break;
        }
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

    @Override
    public void onShowPin(@Nullable String pinStyles, boolean isOnline, boolean isPinByapss) {
        if (!TextUtils.isEmpty(pinStyles)) {
            if (pinStyles.equals(PinStyles.LAST))
                promptTitle.setText("Please Enter PIN Last Time");
            else if (pinStyles.equals(PinStyles.RETRY))
                promptTitle.setText("Please Enter PIN Again");
            else
                promptTitle.setText("Please Enter PIN");
        }
    }
}
