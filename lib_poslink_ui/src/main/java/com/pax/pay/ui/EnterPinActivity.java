package com.pax.pay.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.base.BaseActivityWithTickForAction;
import com.pax.pay.ui.constant.ActivityIntentAction;
import com.pax.pay.ui.constant.EUIMessageKey;
import com.pax.pay.ui.constant.StatusBroadcastAction;
import com.pax.pay.ui.eventbus.Event;
import com.pax.pay.ui.eventbus.EventBusUtil;
import com.pax.pay.ui.handler.EnterPINHandler;
import com.pax.pay.ui.utils.CurrencyConverter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class EnterPinActivity extends BaseActivityWithTickForAction<String[]> {

    TextView amountTv;
    TextView promptTitle;
    TextView pwdInputText;
    LinearLayout llAmount;
    TextView promptBypass;
    Button confirmBtn;

    private String panBlock;
    private long totalAmount;
    private String prompt;
    private boolean isFirstStart = true;// 判断界面是否第一次加载
    //private EEnterPinType enterPinType;
    private boolean allowPinBypass;
    private int minPIN;
    private int maxPIN;
    private int timeout;
    private int pinLen;
    private boolean isUsingExternalPinPad = false;

    private String currentAction;
    private String message;
    private String senderPackage;
    private List<String> options;
    private EnterPINHandler mEnterPINHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBusUtil.register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_pin;
    }

    @Override
    protected void loadParam() {
        amountTv = (TextView) findViewById(R.id.amount_tv);
        promptTitle = (TextView) findViewById(R.id.prompt_title);
        pwdInputText = (TextView) findViewById(R.id.pin_input_text);
        llAmount = (LinearLayout) findViewById(R.id.ll_amount);
        promptBypass = (TextView) findViewById(R.id.prompt_no_pin);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        totalAmount = 0;
        if (totalAmount == 0) {
            llAmount.setVisibility(View.INVISIBLE);
        }
        prompt = getIntent().getStringExtra(EUIMessageKey.INTENT_KEY_MESSAGE);
        senderPackage = getIntent().getStringExtra(EUIMessageKey.KEY_SENDER_PACKAGE);
        options = getIntent().getStringArrayListExtra(EUIMessageKey.INTENT_KEY_OPTIONS);
        currentAction = getIntent().getAction();
        if (currentAction == ActivityIntentAction.ACTION_ENTER_VCODE) {
            minPIN = getIntent().getIntExtra(EUIMessageKey.IntentKey.MINLENGTH, 0);
            maxPIN = getIntent().getIntExtra(EUIMessageKey.IntentKey.MAXLENGTH, 4);
        } else {
            minPIN = getIntent().getIntExtra(EUIMessageKey.IntentKey.MINLENGTH, 4);
            maxPIN = getIntent().getIntExtra(EUIMessageKey.IntentKey.MAXLENGTH, 12);
        }

        allowPinBypass = true;
        isUsingExternalPinPad = false;


        mEnterPINHandler = new EnterPINHandler(this);
        mEnterPINHandler.setRecvPackage(senderPackage);
        timeout = 600000;

        navTitle = "";
        navBack = false;
    }

    @Override
    public void initViews() {
        tickTimerStop();
//        if (ActivityStack.getInstance().top() instanceof EnterPinActivity) {
//            ActivityStack.getInstance().pop();
//        }
        //enableActionBar(false);
        amountTv.setText(CurrencyConverter.convert(totalAmount));
        if (currentAction == ActivityIntentAction.ACTION_ENTER_VCODE) {
            if (prompt == null || prompt.length() == 0)
                prompt = "Please Input CVV";
            allowPinBypass = false;
        }

        if (currentAction == ActivityIntentAction.ACTION_ENTER_PIN) {
            confirmBtn.setVisibility(View.GONE);
        }

        if (prompt != null)
            promptTitle.setText(prompt);
        if (allowPinBypass) {
            promptBypass.setVisibility(View.VISIBLE);
        } else {
            promptBypass.setVisibility(View.GONE);
        }


        if (isUsingExternalPinPad) {
            pwdInputText.setVisibility(View.GONE);
            promptTitle.setText(prompt + "\n On the External PIN Pad");
            promptTitle.setTextSize(32.0f);
            promptBypass.setVisibility(View.GONE);
        }
    }

    // 当页面加载完成之后再执行弹出键盘的动作
    @Override
    protected void onResume() {
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
                mEnterPINHandler.sendInputArea(pwdInputText.getWidth(), pwdInputText.getHeight(), x, y - barHeight);
            }
        });

    }

    @Override
    public void setListeners() {
        confirmBtn.setOnClickListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAsync(Event event) {
        String action = (String) event.getStatus();
        switch (action) {
            case StatusBroadcastAction.PIN_ENTERING:
                pinLen++;
                String star = "*";
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pinLen; i++) {
                    sb.append(star);
                }
                pwdInputText.setText(sb.toString());
                break;
            case StatusBroadcastAction.PIN_ENTER_CLEARED:
                pinLen = 0;
                pwdInputText.setText("");
                break;
        }
    }

    @Override
    protected boolean onKeyEnterDown() {
        mEnterPINHandler.sendNext();
        return true;
    }

    @Override
    public final void onClick(View v) {
        int i = v.getId();
        if (i == R.id.confirm_btn) {
            mEnterPINHandler.sendNext();
        }
    }


    @Override
    protected boolean onKeyBackDown() {
        mEnterPINHandler.sendAbort();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
