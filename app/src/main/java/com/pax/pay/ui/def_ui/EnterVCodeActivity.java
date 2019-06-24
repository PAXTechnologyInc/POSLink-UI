package com.pax.pay.ui.def_ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import com.pax.pay.ui.def_ui.eventbus.EventBusConstant;
import com.pax.pay.ui.def_ui.eventbus.EventBusUtil;
import com.pax.pay.ui.def_ui.utils.ToastHelper;
import com.pax.us.pay.ui.core.message.RespMessage;
import com.pax.us.pay.ui.core.message.UIMessageManager;
import com.pax.us.pay.ui.core.message.api.IMessageListener;
import com.pax.us.pay.ui.core.message.api.IRespStatus;
import com.pax.us.pay.ui.core.message.helper.SecurityHelper;

public class EnterVCodeActivity extends AppCompatActivity implements View.OnClickListener, IMessageListener {

    TextView promptTitle;
    TextView pwdInputText;
    Button confirmBtn;
    private int pinLen;


    private SecurityHelper helper = new SecurityHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_cvv);

        promptTitle = (TextView) findViewById(R.id.prompt_title);
        pwdInputText = (TextView) findViewById(R.id.pin_input_text);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);

        promptTitle.setText(getResources().getText(R.string.pls_input_vcode));
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
                ToastHelper.showMessage(EnterVCodeActivity.this, buff);
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
                helper.setSecurityArea(x, y - barHeight, pwdInputText.getWidth(), pwdInputText.getHeight());
            }
        });
    }

    @Override
    public void onShowMessage(String message) {
        if (message != null && !message.equals("")) {
            promptTitle.setText(message);
        }
    }
}
