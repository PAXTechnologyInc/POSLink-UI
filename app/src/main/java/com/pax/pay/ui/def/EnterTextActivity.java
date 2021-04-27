package com.pax.pay.ui.def;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseStackActivity;
import com.pax.pay.ui.def.base.FinishRespStatusImpl;
import com.pax.pay.ui.def.utils.EnterDataLineHelper;
import com.pax.pay.ui.def.utils.LanguageConvertUtils;
import com.pax.us.pay.ui.component.keyboard.PoslinkCustomKeyboardEditText;
import com.pax.us.pay.ui.component.utils.TickTimer;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst;
import com.pax.us.pay.ui.core.helper.EnterTextHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.utils.StringUtils;
import com.paxus.utils.log.Logger;
import com.paxus.view.dialog.CustomAlertDialog;
import com.paxus.view.utils.ToastHelper;
import com.paxus.view.utils.ViewUtils;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EnterTextActivity extends BaseStackActivity implements EnterTextHelper.IEnterTextHelper,View.OnClickListener {
    private EnterTextHelper helper;

    TextView promptTv;
    PoslinkCustomKeyboardEditText mEditText;
    Button confirmBtn;
    private boolean hasPhyKeyboard = false;

    private long timeout;

    private String inputType;

    private String title;

    private int minLen = 0, maxLen = 32;

    private int textLength;
    private boolean isNoBlocking = false;

    private String defaultValue;
    private EditTextDataLimit limit;
    private TickTimer tickTimer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_input_text;
    }

    @Override
    protected void setListeners() {
        confirmBtn.setOnClickListener(this);
    }

    @Override
    public void onClickProtected(View view) {
        if (view.getId() == R.id.btn_input_text_ok) {
            onDataConfirmed();
        }
    }

    @Override
    protected void loadParam() {
        createTimer();
        tickTimer.start(getTickTimeout());

        promptTv = findViewById(R.id.tv_input_text);
        mEditText = findViewById(R.id.edit_input_text);
        confirmBtn = findViewById(R.id.btn_input_text_ok);
        hasPhyKeyboard = false;
        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle.containsKey(EntryExtraData.PARAM_CONTINUE_SCREEN)
                    && (ManageUIConst.ContinuousScreen.DO_NOT_GO_TO_IDLE.equals(bundle.getString(EntryExtraData.PARAM_CONTINUE_SCREEN)))) {
                isNoBlocking = true;
            }
        } catch (Exception e) {
            Logger.e(e);
            isNoBlocking = false;
        }
        helper = new EnterTextHelper(this, new FinishRespStatusImpl(this));
        loadOtherParam();
    }

    private void loadOtherParam(){
        EditTextDataLimit limit = null;
        title = getIntent().getExtras().getString(EntryExtraData.PARAM_TITLE);
        inputType = getIntent().getExtras().getString(EntryExtraData.PARAM_INPUT_TYPE, "");
        defaultValue = getIntent().getExtras().getString(EntryExtraData.PARAM_DEFAULT_VALUE, "");
        String minLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MIN_LENGTH, "");
        String maxLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MAX_LENGTH);
        minLen = StringUtils.parseInt(minLength, 0);
        maxLen = StringUtils.parseInt(maxLength, 32);

        switch (inputType){
            case "":
            case "0":
                limit = new EditTextDataLimit(title,
                        defaultValue, minLen, maxLen, EInputType.ALLTEXT, false);
                break;
            case "1":
                limit = new EditTextDataLimit(title,
                        defaultValue, minLen, maxLen, EInputType.NUM, false);
                break;
            case "2":
                limit = new EditTextDataLimit(title,
                        defaultValue, minLen, maxLen, EInputType.DATE, false);
                break;
            case "3":
                limit = new EditTextDataLimit(title,
                        defaultValue, minLen, maxLen, EInputType.TIME, false);
                break;
            case "4":
                setCurrencyName(CurrencyType.USD);

                if (StringUtils.isEmpty(defaultValue))
                    defaultValue = CurrencyConverter.convert(0L);
                String tmpAmt = null;
                if (!defaultValue.contains(".")){
                    tmpAmt = CurrencyConverter.convert(Long.parseLong(defaultValue));
                }else
                    tmpAmt = defaultValue.startsWith("$") ? defaultValue : "$"+defaultValue;

                limit = new EditTextDataLimit(title,
                        tmpAmt, minLen, maxLen, EInputType.AMOUNT, false);
                break;
            case "5":
                limit = new EditTextDataLimit(title,
                        defaultValue, minLen, maxLen, EInputType.PASSWORD, false);
                break;
            case "6":
                limit = new EditTextDataLimit(title,
                        defaultValue, minLen, maxLen, EInputType.PHONE, false);
                break;
            case "7":
                limit = new EditTextDataLimit(title,
                        defaultValue, minLen, maxLen, EInputType.SOCIALSECURITY, false);
                break;
        }

        if (limit != null)
            setLimit(limit);
        setPromptTv(title);
    }


    public void setLimit(EditTextDataLimit limit) {
        this.limit = limit;
        promptTv.setText(limit != null ? limit.prompt : "");
        setEditText();
        mEditText.requestFocus();
    }

    protected void setPromptTv(String prompt) {
        if (!TextUtils.isEmpty(prompt))
            promptTv.setText(prompt);
    }

    public void setCurrencyName(String currencyName) {
        CurrencyConverter.setDefCurrency(currencyName);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initViews() {
        if (!ViewUtils.isScreenOrientationPortrait(this) || Build.MODEL.equals("A30")) {
            confirmBtn.setVisibility(View.GONE);
        }
        mEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NONE) {
                tickTimerStop();
                helper.sendAbort();
                if (!isNoBlocking)
                    finish();
                return true;
            } else if (actionId == EditorInfo.IME_ACTION_DONE) {
                onDataConfirmed();
            }
            return false;
        });
    }

    @Override
    protected boolean onKeyEnterDown() {
        onDataConfirmed();
        return true;
    }

    @Override
    public void finish() {
        helper.stop();
        super.finish();
    }

    private void setEditText() {
        switch (limit.inputType) {
            case AMOUNT:
                setEditTextAmount();
                break;
            case PASSWORD:
                setEditPassword();
                break;
            case DATE:
                setEditTextDate("MM/DD/YYYY");
                break;
            case TIME:
                setEditTextTime("HH:MM:SS");
                break;
            case NUM:
                setEditTextNumber();
                break;
            case ALLTEXT:
                setEditTextAllText();
                break;
            case PHONE:
                setEditTextPhone();
                break;
            case SOCIALSECURITY:
                setEditTextSocialSecurity();
                break;
            default:
                break;
        }
    }


    // 金额
    private void setEditTextAmount() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);   //ANBP-543
        mEditText.setKeepKeyBoardOn(true);
        EnterDataLineHelper.setEditTextAmount(this, mEditText, limit);
        if (!TextUtils.isEmpty(limit.value)) {
            EnterDataLineHelper.getWatcher().setAmount(0, CurrencyConverter.parse(limit.value));
        }
    }

    // 日期
    private void setEditTextDate(String format) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);   //ANBP-543
        mEditText.setKeepKeyBoardOn(true);
        EnterDataLineHelper.setEditTextFormatDate(this,format, mEditText);

    }

    // Time
    private void setEditTextTime(String format) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);   //ANBP-543
        mEditText.setKeepKeyBoardOn(true);
        EnterDataLineHelper.setEditTextTime(this,format, mEditText);

    }

    // Phone
    private void setEditTextPhone() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);   //ANBP-543
        mEditText.setKeepKeyBoardOn(true);
        EnterDataLineHelper.setEditTextPhone(this, mEditText);

    }

    // Phone
    private void setEditTextSocialSecurity() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);   //ANBP-543
        mEditText.setKeepKeyBoardOn(true);
        EnterDataLineHelper.setEditTextSocialSecurity(this, mEditText);

    }

    // Password
    private void setEditPassword() {
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
//                | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        mEditText.setKeepKeyBoardOn(true);
        mEditText.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) EnterTextActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
        }, 100);
        EnterDataLineHelper.setEditTextAllText(this, mEditText, limit);
        //mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());

    }

    // 纯数字
    private void setEditTextNumber() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);   //ANBP-543
        mEditText.setKeepKeyBoardOn(true);
        EnterDataLineHelper.setEditTextNumber(this, mEditText, limit);

    }

    private void setEditTextAllText() {
        if (limit.inputType == EInputType.ALLTEXT) {
            mEditText.postDelayed(() -> {
                InputMethodManager imm = (InputMethodManager) EnterTextActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
            }, 100);
        }
        EnterDataLineHelper.setEditTextAllText(this, mEditText, limit);

    }


    @Override
    protected boolean onKeyBackDown() {
        tickTimerStop();
        helper.sendAbort();
        if (!isNoBlocking)
            finish();
        return true;
    }

    private void onDataConfirmed() {
        try {
            confirmBtn.setClickable(false);

            String content = mEditText.getText().toString();
            if (StringUtils.isEmpty(content)) {
                if (limit != null
                        && (EInputType.PHONE == limit.inputType || EInputType.SOCIALSECURITY == limit.inputType)) {
                    mEditText.requestFocus();
                    return;
                }
                else{
                    tickTimerStop();
                    helper.sendAbort();
                    if (!isNoBlocking)
                        finish();
                    return;
                }
            }

            if (!validate(content)) {
                //ANFDRC-692
                mEditText.requestFocus();
                return;
            }

            String data = convert(content);

            if (limit != null && limit.needConfirm) {
                confirm(data);
            } else {
                tickTimerStop();
                helper.sendNext(data);
                if (!isNoBlocking)
                    finish();
            }
        } finally {
            confirmBtn.setClickable(true);
        }
    }

    private String convert(String content){
        switch (limit.inputType) {
            case NUM:
            case PASSWORD:
            case ALLTEXT:
                return content;
            case AMOUNT:
                if (!TextUtils.isEmpty(content)) {
                    Long amt = CurrencyConverter.parse(content);
                    return String.valueOf(amt);
                }
                return content;
            case DATE:
                if (content == null || content.isEmpty()) {
                    return content;
                }
                return content.replace("/", "");
            case TIME:
                if (content == null || content.isEmpty()) {
                    return content;
                }
                return content.replace(":", "");
            case PHONE:
                if (content == null || content.isEmpty()) {
                    return content;
                }
                String tmp = content.replace("(", "");
                tmp = tmp.replace(")", "");
                tmp = tmp.replace(" ", "");
                tmp = tmp.replace("-", "");
                return tmp;
            case SOCIALSECURITY:
                if (content == null || content.isEmpty()) {
                    return content;
                }
                return content.replace("-", "");
        }
        return null;

    }


    @SuppressLint("StringFormatMatches")
    protected boolean validate(String content) {
        switch (limit.inputType) {
            case NUM:
            case PASSWORD:
                if (limit.minLen == 0 && limit.maxLen == 0) { // don't need to enter;
                    return true;
                }
                if (content != null && (content.length() < limit.minLen || content.length() > limit.maxLen)) {
                    String title = LanguageConvertUtils.convertString(this, R.string.notice_len_out_of_range, R.string.length, limit.minLen, limit.maxLen);
                    ToastHelper.showMessage(this, title);
                    return false;
                }
                break;
            case AMOUNT:
                Long amt = CurrencyConverter.parse(content);
                if (amt != null && amt != 0 &&
                        (String.valueOf(amt).length()<limit.minLen || String.valueOf(amt).length()>limit.maxLen)){
                    String title = LanguageConvertUtils.convertString(this, R.string.notice_amount_out_of_range, null, CurrencyConverter.convert(limit.minValue), CurrencyConverter.convert(limit.maxValue));
                    ToastHelper.showMessage(this, title);

                    return false;
                }
                if (CurrencyConverter.parse(content) == 0 && limit.isMustEnter) {
                    ToastHelper.showMessage(this, getString(R.string.pls_input_again));
                    mEditText.requestFocus();
                    return false;
                }
                break;
            case DATE:
                if (content == null || content.isEmpty()) {
                    if (limit.isMustEnter) {
                        return false;
                    }
                }
                if (content != null) {
                    String date = content.replace("/", "");
                    if (date.length() == 8) {

                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy", Locale.US);
                        Date date1 = null;
                        try {
                            dateFormat.setLenient(false);
                            date1 = dateFormat.parse(date);
                        } catch (ParseException e) {

                        }
                        if (date1 == null) {
                            ToastHelper.showMessage(this, getString(R.string.err_invalid_date));
                            return false;
                        }
                    } else if (!content.isEmpty()) {
                        ToastHelper.showMessage(this, getString(R.string.err_invalid_date));
                        return false;
                    }
                }
                break;
            case TIME:
                if (content == null || content.isEmpty()) {
                    if (limit.isMustEnter) {
                        return false;
                    }
                }
                if (content != null) {
                    String time = content.replace(":", "");
                    if (time.length() == 6) {

                        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss", Locale.US);
                        Date time1 = null;
                        try {
                            dateFormat.setLenient(false);
                            time1 = dateFormat.parse(time);
                        } catch (ParseException e) {

                        }
                        if (time1 == null) {
                            ToastHelper.showMessage(this, getString(R.string.err_invalid_time));
                            return false;
                        }
                    } else if (!content.isEmpty()) {
                        ToastHelper.showMessage(this, getString(R.string.err_invalid_time));
                        return false;
                    }
                }
                break;
            case PHONE:
                if (content == null || content.isEmpty()) {
                    if (limit.isMustEnter) {
                        return false;
                    }
                }
                if (content != null) {
                    String tmp = content.replace("(", "");
                    tmp = tmp.replace(")", "");
                    tmp = tmp.replace(" ", "");
                    tmp = tmp.replace("-", "");

                    if (tmp != null && (tmp.length() < limit.minLen || tmp.length() > limit.maxLen)) {
                        String title = LanguageConvertUtils.convertString(this, R.string.notice_len_out_of_range, R.string.length, limit.minLen, limit.maxLen);
                        ToastHelper.showMessage(this, title);
                        return false;
                    }
                }
                break;
            case SOCIALSECURITY:
                if (content == null || content.isEmpty()) {
                    if (limit.isMustEnter) {
                        return false;
                    }
                }
                if (content != null) {
                    String tmp = content.replace("-", "");

                    if (tmp != null && (tmp.length() != 9)) {
                        String title = LanguageConvertUtils.convertString(this, R.string.notice_out_of_length, R.string.length, 9);
                        ToastHelper.showMessage(this, title);
                        return false;
                    }
                }
                break;
            default:
                if (content != null && (content.length() < limit.minLen || content.length() > limit.maxLen)) {
                    String title = LanguageConvertUtils.convertString(this, R.string.notice_len_out_of_range, R.string.length, limit.minLen, limit.maxLen);
                    ToastHelper.showMessage(this, title);
                    return false;
                }
                break;
        }
        //ANFDRC-646
        mEditText.requestFocus();
        return true;
    }

    protected void confirm(final String data) {
        final WeakReference<Context> contextWeakReference = new WeakReference<>(this);
        runOnUiThread(() -> {
            Context ctx = contextWeakReference.get();
            CustomAlertDialog dialog = new CustomAlertDialog(ctx, CustomAlertDialog.NORMAL_TYPE);
            dialog.setCancelClickListener(alertDialog -> {
                alertDialog.dismiss();
                tickTimerStop();
                helper.sendAbort();
                if (!isNoBlocking)
                    finish();
            }).setConfirmClickListener(alertDialog -> {
                alertDialog.dismiss();
                tickTimerStop();
                helper.sendNext(data);
                if (!isNoBlocking)
                    finish();
            }).create();
            dialog.setContent(limit != null ? limit.confirmPrompt : "");
            dialog.showConfirmButton(true);
            dialog.showCancelButton(true);
            dialog.show();
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus ){
            if(hasPhyKeyboard && limit.inputType != EInputType.ALLTEXT){
                mEditText.hideKeyboard(true, false);
            }
            if (!TextUtils.isEmpty(limit.value)) {
                mEditText.setText(limit.value);
            }
        }
    }

    @Override
    public void onHasPhyKeyboard(boolean notShowVirtualKeyBoard) {
        if(notShowVirtualKeyBoard) {
            this.hasPhyKeyboard = notShowVirtualKeyBoard;
        }
    }

    @Override
    public void onStartHelper() {
        helper.start(this, getIntent());
    }

    @Override
    public void onAbortHelper() {
        helper.sendAbort();
        finish();
    }

    private void createTimer() {
        tickTimer = new TickTimer(new TickTimer.OnTickTimerListener() {
            @Override
            public void onFinish() {
                onTimerFinish();
            }

            @Override
            public void onTick(long leftTime) {
                timeOnTick(leftTime);
            }
        });
    }

    protected void timeOnTick(long leftTime) {
        //this.leftTime = leftTime;
        //setTitle(String.format(Locale.US, "%s ( %d )", getString(R.string.signature), leftTime));
    }

    protected void restartTimer() {
        tickTimerStop();
        createTimer();
        tickTimer.start(getTickTimeout());
    }

    public void tickTimerStop() {
        if (tickTimer != null) {
            tickTimer.stop();
            tickTimer = null;
        }
    }

    protected void onTimerFinish() {
        if (timeout <= 0)
            return;

        tickTimerStop();
        helper.sendTimeout();

        if (!isNoBlocking) {
            finish();
        }
    }

    protected long getTickTimeout() {
        timeout = -1;
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(EntryExtraData.PARAM_TIMEOUT)) {
            long time = getIntent().getExtras().getLong(EntryExtraData.PARAM_TIMEOUT, -1L);
            timeout = (int)(time/1000l);
        }

//        if (timeout == 0)
//            isNoBlocking = true;

        return timeout;
    }
}
