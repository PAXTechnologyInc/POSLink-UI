package com.pax.pay.ui.def;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.base.SecurityRespStatusImpl;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.pax.pay.ui.def.eventbus.PINEvent;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.enumeration.PinStyles;
import com.pax.us.pay.ui.constant.status.PINStatus;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.helper.EnterPinHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.view.utils.ViewUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EnterPinActivity extends BaseAppActivity implements IMessageListener, EnterPinHelper.IEnterPinListener {
    private EnterPinHelper helper;
    private StringBuilder pin;

    TextView amountPromptTv;
    TextView amountTv;
    TextView promptTitle;
    TextView pwdInputText;
    LinearLayout llAmount;
    TextView promptBypass;
    Button confirmBtn;

    private long totalAmount;
    private String prompt = null;
    private boolean allowPinBypass;
    private boolean isUsingExternalPinPad = false;
    private boolean confirmBtnVisible;
    private boolean showVirtualPinPad;

    private boolean isPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //hide back home button full screen
        //DO NOT add View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY,
        // for the first time entering full screen, system will prompt a dialog for hint,
        // it will be gone after user confirmed, but if this happens on PIN page,
        // this dialog will be displayed all the time because Android system cannot handle screen input event.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        needToolBar = false;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_security_default;
    }

    @Override
    protected void loadParam() {

        pin = new StringBuilder();

        allowPinBypass = false;

        totalAmount = 0;
        prompt = null;
        allowPinBypass = true;
        //ANFDRC-1000
        //setPrompt(getResources().getString(R.string.pls_input_pin), Gravity.CENTER);
        confirmBtnVisible = false;
        showVirtualPinPad = false;

        navBack = false;
        helper = new EnterPinHelper(this, new SecurityRespStatusImpl(this));
        //helper.start(this, getIntent());
        EventBusUtil.register(this);

        //isUsingExternalPinPad = false;
    }

    @Override
    public void initViews() {
        amountTv = findViewById(R.id.amount_tv);
        amountPromptTv = findViewById(R.id.amount_prompt_tv);
        promptTitle = findViewById(R.id.prompt_title);
        pwdInputText = findViewById(R.id.pin_input_text);
        llAmount = findViewById(R.id.ll_amount);
        llAmount.setVisibility(View.INVISIBLE);
        promptBypass = findViewById(R.id.prompt_no_pin);
        confirmBtn = findViewById(R.id.confirm_btn);

        //enableActionBar(false);
        amountTv.setText(CurrencyConverter.convert(totalAmount));

        if ((prompt != null) && (prompt.length() != 0))
            promptTitle.setText(prompt);
        if (allowPinBypass) {
            promptBypass.setVisibility(View.VISIBLE);
        } else {
            promptBypass.setVisibility(View.GONE);
        }

        if (!confirmBtnVisible)
            confirmBtn.setVisibility(View.INVISIBLE);

        if (!ViewUtils.isScreenOrientationPortrait(this)) {
            confirmBtn.setVisibility(View.GONE);
        }

        setExternal(false); //default
    }

    protected void sendAbort() {
        helper.sendAbort();
    }

    protected void sendNext() {
        helper.sendNext();
    }

    protected void sendNext(int offsetX, int offsetY, int width, int height) {
        helper.setSecurityArea(offsetX, offsetY, width, height);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAsync(PINEvent event) {
        if (event == null)
            return;
        String action = event.getStatus();
        switch (action) {
            case PINStatus.PIN_ENTERING:
                pin.append("*");
                pwdInputText.setText(pin.toString());
                break;
            case PINStatus.PIN_ENTER_CLEARED:
                pin.delete(0, pin.length());
                pwdInputText.setText("");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBusUtil.unregister(this);
        super.onDestroy();
    }

    private boolean first = true;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (first && hasFocus) {
            first = false;
            int barHeight = 0;
            int x = 0, y = 0;
            if (!isUsingExternalPinPad) {
                if (showVirtualPinPad && findViewById(R.id.pin_keyboard) != null) {
                    // default layout from PED is attached an input box on Ar8,
                    // so we have to move it to the right position by customization
                    Intent intent = new Intent(EntryRequest.ACTION_SET_PIN_KEY_LAYOUT);
                    intent.setPackage(intent.getStringExtra(EntryExtraData.PARAM_PACKAGE));
                    intent.putExtra(EntryRequest.PARAM_ACTION, intent.getAction());
                    addViewRect2Intent(intent, EntryRequest.PARAM_KEY_0, R.id.btn_zero);
                    addViewRect2Intent(intent, EntryRequest.PARAM_KEY_1, R.id.btn_one);
                    addViewRect2Intent(intent, EntryRequest.PARAM_KEY_2, R.id.btn_two);
                    addViewRect2Intent(intent, EntryRequest.PARAM_KEY_3, R.id.btn_three);
                    addViewRect2Intent(intent, EntryRequest.PARAM_KEY_4, R.id.btn_four);
                    addViewRect2Intent(intent, EntryRequest.PARAM_KEY_5, R.id.btn_five);
                    addViewRect2Intent(intent, EntryRequest.PARAM_KEY_6, R.id.btn_six);
                    addViewRect2Intent(intent, EntryRequest.PARAM_KEY_7, R.id.btn_seven);
                    addViewRect2Intent(intent, EntryRequest.PARAM_KEY_8, R.id.btn_eight);
                    addViewRect2Intent(intent, EntryRequest.PARAM_KEY_9, R.id.btn_nine);
                    addViewRect2Intent(intent, EntryRequest.PARAM_KEY_CLEAR, R.id.btn_clear);
                    addViewRect2Intent(intent, EntryRequest.PARAM_KEY_ENTER, R.id.btn_enter);
                    addViewRect2Intent(intent, EntryRequest.PARAM_KEY_CANCEL, R.id.btn_cancel);
                    sendBroadcast(intent);
                }

                int[] location = new int[2];
                pwdInputText.getLocationOnScreen(location);
                x = location[0];
                y = location[1];

                int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    barHeight = getResources().getDimensionPixelSize(resourceId);
                }
            }

            /*
              Workaround: Kim.L Because Manager starts pin process from PINHoverWindow, the only entry,
              even isUsingExternalPinPad == true
              so before having a updated solution(starts pin process from a different entry?),
              we will keep the original impl(bug): setSecurityArea even when isUsingExternalPinPad == true
             */
            //ANBP-645 to avoid black screen while load PINPAD's Keyboard
            //just a trigger
            sendNext(x, y - barHeight, pwdInputText.getWidth(), pwdInputText.getHeight());
        }
    }

    private void addViewRect2Intent(Intent intent, String key, @IdRes int viewId) {
        View v = findViewById(viewId);
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        intent.putExtra(key, new Rect(location[0], location[1], location[0] + v.getWidth(), location[1] + v.getHeight()));
    }

    public void setCurrencyName(String currencyName) {
        if (!TextUtils.isEmpty(currencyName)) {
            CurrencyConverter.setDefCurrency(currencyName);
        }
    }

    protected void setExternal(boolean isExternalPinpad) {
        isUsingExternalPinPad = isExternalPinpad;
        if (isUsingExternalPinPad) {
            pwdInputText.setVisibility(View.GONE);
            promptTitle.setText(prompt + getString(R.string.on_external_pinpad));
            promptTitle.setTextSize(32.0f);
            promptBypass.setVisibility(View.GONE);
        }
        if (!showVirtualPinPad || isUsingExternalPinPad) {
            View keyboard = findViewById(R.id.pin_keyboard);
            if (keyboard != null)
                keyboard.setVisibility(View.GONE);
        }
    }

    public void setPoint(boolean point) {
        isPoint = point;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
        if (isPoint) {
            amountPromptTv.setText(R.string.total_point);
            amountTv.setText(String.valueOf(totalAmount));
        } else {
            amountTv.setText(CurrencyConverter.convert(totalAmount, ""));
        }
        llAmount.setVisibility(View.VISIBLE);
    }

    @Override
    public void setListeners() {
        confirmBtn.setOnClickListener(this);
    }


    @Override
    protected boolean onKeyEnterDown() {
        sendNext();
        return true;
    }

    @Override
    public void onClickProtected(View view) {
        if (view.getId() == R.id.confirm_btn) {
            sendNext();
        }
    }

    @Override
    protected boolean onKeyBackDown() {
        sendAbort();
        return true;
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    public void setPrompt(String prompt, int gravity) {
        this.prompt = prompt;
        if ((prompt != null) && (prompt.length() != 0)) //Fix ANBP-584
            promptTitle.setText(prompt);
        promptTitle.setGravity(gravity);
    }

    @Override
    public void onShowAmount(long amount) {
        setTotalAmount(amount);
    }

    @Override
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {
        if (isPoint) {
            setPoint(true);
        } else {
            setCurrencyName(currency);
            setPoint(false);
        }
    }


    @Override
    public void onShowPin(@Nullable String pinStyles, boolean isOnline, boolean isPinBypass, boolean isExternalPinpad) {
        if (!TextUtils.isEmpty(pinStyles)) {
            if (pinStyles.equals(PinStyles.RETRY))
                setPrompt(getResources().getString(R.string.pls_input_pin_again), Gravity.CENTER);
            else if (pinStyles.equals(PinStyles.LAST))
                setPrompt(getResources().getString(R.string.pls_input_pin_last), Gravity.CENTER);
            else
                setPrompt(getResources().getString(R.string.pls_input_pin), Gravity.CENTER);
        }
        if (!isExternalPinpad) {
            allowPinBypass = isPinBypass;
            promptBypass.setVisibility(isPinBypass ? View.VISIBLE : View.INVISIBLE);
        }
        setExternal(isExternalPinpad);
    }

    @Override
    public void onShowVirtualPinpad(boolean showVirtualPinPad) {
        this.showVirtualPinPad = showVirtualPinPad;
    }

    @Override
    protected void onStart() {
        helper.start(this, getIntent());
        super.onStart();
    }

    @Override
    protected void onStop() {
        helper.stop();
        super.onStop();
    }
}
