package com.pax.pay.ui.def;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.utils.EnterDataLineHelper;
import com.pax.pay.ui.def.utils.RangeFilter;
import com.pax.us.pay.ui.component.keyboard.CustomKeyboardEditText;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.utils.LocaleUtils;
import com.paxus.utils.StringUtils;
import com.paxus.view.dialog.CustomAlertDialog;
import com.paxus.view.dialog.DialogUtils;
import com.paxus.view.utils.ToastHelper;
import com.paxus.view.utils.ViewUtils;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

abstract class EnterDataLine1Activity<T> extends BaseAppActivity implements IMessageListener, IHasPhyKeyboardListener {

    TextView amountTv;
    TextView promptTv;
    CustomKeyboardEditText mEditText;
    Button confirmBtn;
    LinearLayout transAmountLayout;
    private boolean hasPhyKeyboard = false;

    private EditTextDataLimit limit;
    private boolean isPoint = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_info_default;
    }

    @Override
    protected void setListeners() {
        confirmBtn.setOnClickListener(this);
    }

    @Override
    protected void loadParam() {
        amountTv = findViewById(R.id.amount_tv);
        promptTv = findViewById(R.id.prompt_tv);
        mEditText = findViewById(R.id.data_edt);
        confirmBtn = findViewById(R.id.confirm_btn);
        transAmountLayout = findViewById(R.id.trans_amount_layout);

        limit = EditTextDataLimit.getDef();
        setCurrencyName(CurrencyType.USD);
        setAmount(0L);
        hasPhyKeyboard = false;
        loadOtherParam("");
    }



    public void setAmount(long amount) {
        if (amount != 0) {
            amountTv.setText(isPoint ? String.valueOf(amount) : CurrencyConverter.convert(amount, ""));
        } else {
            transAmountLayout.setVisibility(View.INVISIBLE);
        }
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
        if (limit.inputType.equals(EInputType.AMOUNT))
            mEditText.setText(isPoint ? String.valueOf(0L) : CurrencyConverter.convert(0L, ""));
    }

    public void setPoint(boolean point) {
        isPoint = point;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

        @Override
    public void initViews() {
        if (!ViewUtils.isScreenOrientationPortrait(this)) {
            confirmBtn.setVisibility(View.GONE);
        }
        mEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NONE) {
                //Fix ANFDRC-226, After click KEY_BACK_DOWN
                //finish(new ActionResult<>(TransResult.ERR_USER_CANCEL, null));
                //To do Abort
                sendAbort();
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

    private void setEditText() {
        switch (limit.inputType) {
            case AMOUNT:
                setEditTextAmount();
                break;
            case EXPIRY_DATE:
                setEditTextExpiryDate();
                break;
            case DATE:
                setEditTextDate();
                break;
            case NUM:
                setEditTextNumber();
                break;
            case ALLTEXT:
                setEditTextAllText();
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
    }

    // 日期
    private void setEditTextExpiryDate() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  //ANBP-543
        mEditText.setKeepKeyBoardOn(true);
        EnterDataLineHelper.setEditTextExpiry(this, mEditText, limit);
    }

    private void setEditTextDate() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);   //ANBP-543
        mEditText.setKeepKeyBoardOn(true);
        EnterDataLineHelper.setEditTextDate(this, mEditText);
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
                InputMethodManager imm = (InputMethodManager) EnterDataLine1Activity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
            }, 100);
        }
        EnterDataLineHelper.setEditTextAllText(this, mEditText, limit);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected boolean onKeyBackDown() {
        sendAbort();
        return true;
    }

    private void onDataConfirmed() {
        try {
            confirmBtn.setClickable(false);

            String content = mEditText.getText().toString();
            if (limit != null && limit.isMustEnter && StringUtils.isEmpty(content)) {
                //ANFDRC-692
                mEditText.requestFocus();
                return;
            }
            if (!validate(content)) {
                //ANFDRC-692
                mEditText.requestFocus();
                return;
            }

            T data = convert(content);

            if (limit != null && limit.needConfirm) {
                confirm(data);
            } else {
                sendNext(data);
            }
        } finally {
            confirmBtn.setClickable(true);
        }
    }

    @Override
    public void onClickProtected(View view) {
        if (view.getId() == R.id.confirm_btn) {
            onDataConfirmed();
        }
    }

    @SuppressLint("StringFormatMatches")
    protected boolean validate(String content) {
        switch (limit.inputType) {
            case NUM:
                if (limit.minLen == 0 && limit.maxLen == 0) { // don't need to enter;
                    return true;
                }
                if (StringUtils.isEmpty(limit.lengthRange)) {
                    if (content != null && (content.length() < limit.minLen || content.length() > limit.maxLen)) {
                        if (limit.minLen == 4 && limit.maxLen == 4) {
                            ToastHelper.showMessage(this, getString(R.string.pls_input_4digit));
                        } else {
                            String title = LocaleUtils.getString(this, R.string.notice_length_out_of_range, limit.minValue, limit.maxValue);
                            ToastHelper.showMessage(this, title);
                        }
                        return false;
                    }
                } else {
                    List<Integer> lengthList = RangeFilter.getLengthList(limit.lengthRange);
                    if (content != null && !lengthList.contains(content.length())) {
                        if (limit.minLen == 4 && limit.maxLen == 4) {
                            ToastHelper.showMessage(this, getString(R.string.pls_input_4digit));
                        } else {
                            String title = LocaleUtils.getString(this, R.string.notice_out_of_range, R.string.length, limit.lengthRange);
                            ToastHelper.showMessage(this, title);
                        }
                        return false;
                    }
                }

                long value = StringUtils.parseLong(content);
                if (value > limit.maxValue || value < limit.minValue) {
                    String title = LocaleUtils.getString(this, R.string.notice_number_out_of_range, limit.minValue, limit.maxValue);
                    ToastHelper.showMessage(this, title);
                    return false;
                } else if (limit.minValue != 0) {
                    if (StringUtils.parseLong(content) < limit.minValue) {
                        ToastHelper.showMessage(this, getString(R.string.data_cannot_zero));
                        return false;
                    }
                }
                mEditText.setText("");
                break;
            case EXPIRY_DATE:
                if (StringUtils.isEmpty(content)) {
                    ToastHelper.showMessage(this, getString(R.string.invalid_exp_date));
                    return false;
                }
                if (content.length() != 5) {
                    ToastHelper.showMessage(this, getString(R.string.invalid_exp_date));
                    return false;
                }

                int month = StringUtils.parseInt(content.substring(0, 2));
                if (month < 1 || month > 12) {
                    ToastHelper.showMessage(this, getString(R.string.invalid_exp_date));
                    return false;
                }
                //ANBP-560 expiry date screen don't need restore
                //mEditText.setText("");
                break;
            case AMOUNT:
                if (!StringUtils.isEmpty(limit.lengthRange)) {
                    List<Integer> lengthList = RangeFilter.getLengthList(limit.lengthRange);
                    Long amt = CurrencyConverter.parse(content);
                    if (amt != null && amt != 0 && !lengthList.contains(String.valueOf(amt).length())) {
//                        ToastHelper.showMessage(this, getString(R.string.notice_amount_out_of_range,
//                                //FIXME Kim.L limit.minValue/maxValue is never set
//                                CurrencyConverter.convert(limit.minValue), CurrencyConverter.convert(limit.maxValue)));

//                        String pro = getString(R.string.notice_amount_out_of_range);
//                        if (pro.contains("\n")){
//                            ToastHelper.showMessage(this, getString(R.string.notice_amount_out_of_range, CurrencyConverter.convert(limit.minValue), CurrencyConverter.convert(limit.maxValue), CurrencyConverter.convert(limit.minValue), CurrencyConverter.convert(limit.maxValue)));
//                        }else {
//                            ToastHelper.showMessage(this, getString(R.string.notice_amount_out_of_range, CurrencyConverter.convert(limit.minValue), CurrencyConverter.convert(limit.maxValue)));
//                        }

                        String title = LocaleUtils.getString(this, R.string.notice_amount_out_of_range,  CurrencyConverter.convert(limit.minValue), CurrencyConverter.convert(limit.maxValue));
                        ToastHelper.showMessage(this, title);

                        return false;
                    }
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

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);
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
            default:
                if (!StringUtils.isEmpty(limit.lengthRange)) {
                    List<Integer> lengthList = RangeFilter.getLengthList(limit.lengthRange);
                    if (content != null && !lengthList.contains(content.length())) {
                        String title = LocaleUtils.getString(this, R.string.notice_out_of_range, R.string.length, limit.lengthRange);
                        ToastHelper.showMessage(this, title);
                        return false;
                    }
                }
                break;
        }
        //ANFDRC-646
        mEditText.requestFocus();
        return true;
    }

    protected void confirm(final T data) {
        final WeakReference<Context> contextWeakReference = new WeakReference<>(this);
        runOnUiThread(() -> {
            Context ctx = contextWeakReference.get();
            CustomAlertDialog dialog = new CustomAlertDialog(ctx, CustomAlertDialog.NORMAL_TYPE);
            dialog.setCancelClickListener(alertDialog -> {
                alertDialog.dismiss();
                sendAbort();
            }).setConfirmClickListener(alertDialog -> {
                alertDialog.dismiss();
                sendNext(data);
            }).create();
            dialog.setContent(limit != null ? limit.confirmPrompt : "");
            dialog.showConfirmButton(true);
            dialog.showCancelButton(true);
            DialogUtils.showDialog(this, dialog);
        });
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasPhyKeyboard && limit.inputType != EInputType.ALLTEXT){
            confirmBtn.setVisibility(View.VISIBLE);
            mEditText.hideKeyboard(true, false);
        }
    }

    @Override
    public void onHasPhyKeyboard(boolean notShowVirtualKeyBoard) {
        if(notShowVirtualKeyBoard) {
            this.hasPhyKeyboard = notShowVirtualKeyBoard;
        }
    }

    protected abstract T convert(String content);

    protected abstract void loadOtherParam(String message);

    protected abstract void sendAbort();

    protected abstract void sendNext(T data);
}
