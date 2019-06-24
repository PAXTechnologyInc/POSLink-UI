package com.pax.pay.ui.def_ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import com.pax.pay.ui.def_ui.eventbus.Event;
import com.pax.pay.ui.def_ui.eventbus.EventBusConstant;
import com.pax.pay.ui.def_ui.eventbus.EventBusUtil;
import com.pax.pay.ui.def_ui.utils.ToastHelper;
import com.pax.us.pay.ui.base.constant.status.PINStatus;
import com.pax.us.pay.ui.base.message.RespMessage;
import com.pax.us.pay.ui.base.message.UIMessageManager;
import com.pax.us.pay.ui.base.message.api.IMessageListener;
import com.pax.us.pay.ui.base.message.api.IRespStatus;
import com.pax.us.pay.ui.base.message.helper.SecurityHelper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EnterPinActivity extends AppCompatActivity implements View.OnClickListener, IMessageListener {

    TextView amountTv;
    TextView promptTitle;
    TextView pwdInputText;
    TextView promptBypass;
    Button confirmBtn;
    private int pinLen;


    private SecurityHelper helper = new SecurityHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);
        //moveTaskToFront();
        EventBusUtil.register(this);

        amountTv = (TextView) findViewById(R.id.amount_tv);
        promptTitle = (TextView) findViewById(R.id.prompt_title);
        pwdInputText = (TextView) findViewById(R.id.pin_input_text);
        promptBypass = (TextView) findViewById(R.id.prompt_no_pin);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);

        promptTitle.setText(getResources().getText(R.string.prompt_pin));
        UIMessageManager.getInstance().registerUI(this, this, helper, getIntent(), new IRespStatus() {
            @Override
            public void respAccept() {
                EventBusUtil.postEvent(EventBusConstant.END_EVENT);
                finish();
            }

            @Override
            public void respDecline(RespMessage respMessage) {
                String buff = "Request Declined\n Error Code:" + respMessage.getResultCode() + "\n Error Msg: " + respMessage.getResultMsg();
                //Toast.makeText(this, buff, Toast.LENGTH_LONG).show();
                ToastHelper.showMessage(EnterPinActivity.this, buff);
            }

            @Override
            public void respComplete() {
                finish();
            }
        });
    }


    @Override
    public void onClick(View view) {
        helper.sendObjNext();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            helper.sendObjNext();
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
        UIMessageManager.getInstance().unregisterUI(this, helper);
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
                helper.sendObjNext(x, y - barHeight, pwdInputText.getWidth(), pwdInputText.getHeight());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAsync(Event event) {
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
    public void onShowMessage(String message) {
        if (message != null && !message.equals("")) {
            promptTitle.setText(message);
        }
    }
}
