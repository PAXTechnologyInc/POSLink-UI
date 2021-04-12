package com.pax.pay.ui.def;

import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.utils.EnterDataLineHelper;
import com.pax.us.pay.ui.component.keyboard.CustomKeyboardEditText;
import com.paxus.utils.StringUtils;
import com.paxus.view.utils.KeyboardUtils;
import com.paxus.view.utils.ToastHelper;
import com.paxus.view.utils.ViewUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Sim.G on 2017/5/27 16:57
 */
abstract class EnterDataLine2Activity extends BaseAppActivity implements TextView.OnEditorActionListener {
    LinearLayout data1;
    LinearLayout data2;
    TextView promptTv1;
    CustomKeyboardEditText editTv1;
    TextView promptTv2;
    CustomKeyboardEditText editTv2;
    Button confirmBtn;

    EditTextDataLimit limit1;
    EditTextDataLimit limit2;

    private void setEditText(CustomKeyboardEditText editText, EditTextDataLimit limit) {
        switch (limit.inputType) {
            case AMOUNT:
                setEditTextAmount(editText, limit);
                break;
            case EXPIRY_DATE:
                setEditTextDate(editText, limit);
                break;
            case NUM:
                setEditTextNumber(editText, limit);
                break;
            case ALLTEXT:
                setEditTextAllText(editText, limit);
                break;
            default:
                break;
        }
    }

    // 金额
    private void setEditTextAmount(CustomKeyboardEditText editText, final EditTextDataLimit limit) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        editText.setKeepKeyBoardOn(false);
        EnterDataLineHelper.setEditTextAmount(this, editText, limit);
    }

    // 日期
    private void setEditTextDate(CustomKeyboardEditText editText, final EditTextDataLimit limit) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        editText.setKeepKeyBoardOn(false);
        EnterDataLineHelper.setEditTextExpiry(this, editText, limit);
    }

    // 纯数字
    private void setEditTextNumber(final CustomKeyboardEditText editText, final EditTextDataLimit limit) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        editText.setKeepKeyBoardOn(false);

        EnterDataLineHelper.setEditTextNumber(this, editText, limit);
    }

    private void setEditTextAllText(final CustomKeyboardEditText editText, final EditTextDataLimit limit) {
        EnterDataLineHelper.setEditTextAllText(this, editText, limit);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_line_2_layout_default;
    }

    @Override
    protected void setListeners() {
        confirmBtn.setOnClickListener(this);
        editTv1.setSingleLine(true);
        //Click KEY_BACK, then the editTv2 get IME_ACTION_DONE
        editTv2.setSingleLine(true);
        editTv2.setOnEditorActionListener(this);
        editTv1.setOnEditorActionListener(this);
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_NONE) {
            //Fix ANFDRC-226, After click KEY_BACK_DOWN
            //finish(new ActionResult<>(TransResult.ERR_USER_CANCEL, null));
            //To do Abort
            sendAbort();
            return true;
        } else if (v.getId() == editTv2.getId() && actionId == EditorInfo.IME_ACTION_DONE) {
            infoConfirmOnBtnClick();
        }
        return false;
    }

    @Override
    protected void loadParam() {
        data1 = findViewById(R.id.data_1);
        data2 = findViewById(R.id.data_2);
        promptTv1 = findViewById(R.id.prompt_tv_1);
        editTv1 = findViewById(R.id.edit_tv_1);
        promptTv2 = findViewById(R.id.prompt_tv_2);
        editTv2 = findViewById(R.id.edit_tv_2);
        confirmBtn = findViewById(R.id.confirm_btn);
        limit1 = EditTextDataLimit.getDef();
        limit2 = EditTextDataLimit.getDef();
        loadOtherParam();
    }

    public void setLimit(EditTextDataLimit limit1, EditTextDataLimit limit2) {
        this.limit1 = limit1;
        this.limit2 = limit2;
        if (limit1 == null && limit2 == null) {
            sendAbort();
        }
    }


    @Override
    public void initViews() {
        if (limit1 != null && limit2 != null) {
            promptTv1.setText(limit1.prompt);
            editTv1.setText(limit1.value);
            setEditText(editTv1, limit1);

            promptTv2.setText(limit2.prompt);
            editTv2.setText(limit2.value);
            setEditText(editTv2, limit2);

            editTv1.requestFocus();
            if (limit1.inputType == EInputType.ALLTEXT) {
                editTv1.postDelayed(() -> {
                    InputMethodManager imm = (InputMethodManager) EnterDataLine2Activity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editTv1, InputMethodManager.SHOW_IMPLICIT);
                }, 100);
            }
        } else {
            //always use ed_tv2 for not adding extra padding view
            promptTv2.setText(limit1 == null ? limit2.prompt : limit1.prompt);
            editTv2.setText(limit1 == null ? limit2.value : limit1.value);
            setEditText(editTv2, limit1 == null ? limit2 : limit1);
            if (!ViewUtils.isScreenOrientationPortrait(this)) {
                promptTv2.setTextSize(getResources().getDimension(R.dimen.font_size_prompt_large));
                editTv2.setTextAppearance(this, R.style.DefLargeEditText);
                LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) editTv2.getLayoutParams(); // 取控件当前的布局参数
                linearParams.height = getResources().getDimensionPixelSize(R.dimen.edit_height_large); // 控件的高强制设成xx象素
                editTv2.setLayoutParams(linearParams);
                editTv1.setVisibility(View.GONE);
                EInputType inputType = limit1 == null ? limit2.inputType : limit1.inputType;
                if (!inputType.equals(EInputType.ALLTEXT))
                    confirmBtn.setVisibility(View.INVISIBLE);
            } else
                editTv1.setEnabled(false);
            data1.setVisibility(View.INVISIBLE);
            editTv2.requestFocus();
            if (limit2 != null && limit2.inputType == EInputType.ALLTEXT) {
                editTv2.postDelayed(() -> {
                    InputMethodManager imm = (InputMethodManager) EnterDataLine2Activity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editTv2, InputMethodManager.SHOW_IMPLICIT);
                }, 100);
            }
        }
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

    @Override
    protected void onClickProtected(View v) {
        int i = v.getId();
        if (i == R.id.confirm_btn) {
            infoConfirmOnBtnClick();

        }
    }

    private void infoConfirmOnBtnClick() {
        confirmBtn.setClickable(false);
        KeyboardUtils.hideSystemKeyboard(EnterDataLine2Activity.this, editTv1);
        KeyboardUtils.hideSystemKeyboard(EnterDataLine2Activity.this, editTv2);
        if (checkInput(limit1 != null && limit2 != null, editTv1, limit1)
                && checkInput(true, editTv2, limit1 != null ? limit1 : limit2)) {
            confirmBtn.requestFocusFromTouch();
            sendNext(editTv1.getText().toString(), editTv2.getText().toString());
        }
        confirmBtn.setClickable(true);
    }

    private boolean checkInput(boolean isEditable, EditText editText, EditTextDataLimit limit) {
        if (!isEditable)
            return true;
        String content = editText.getText().toString();
        switch (limit.inputType) {
            case NUM:
                if (StringUtils.isEmpty(content) && limit.isMustEnter) {
                    return false;
                }

                if (limit.minLen != 0 && !TextUtils.isDigitsOnly(content)
                        || content.length() < limit.minLen) {
                    return false;
                }
                break;
            case EXPIRY_DATE:
                if (content.length() == 0) {
                    return true;
                }
                if (content.length() < 4) {
                    ToastHelper.showMessage(this, getResources().getString(R.string.pls_input_again_date));
                    return false;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM", Locale.US);
                simpleDateFormat.setLenient(false);
                String nowTime = simpleDateFormat.format(new Date());
                String inputTime = nowTime.substring(0, 2) + content.substring(2, 4) + content.substring(0, 2);
                Date inPutDate;
                Date nowDate;
                try {
                    inPutDate = simpleDateFormat.parse(inputTime);
                    nowDate = simpleDateFormat.parse(nowTime);
                    if (inPutDate.before(nowDate)) {
                        ToastHelper.showMessage(this, getResources().getString(R.string.pls_input_again_date));
                        return false;
                    } else {
                        return true;
                    }
                } catch (ParseException e) {
                    ToastHelper.showMessage(this, getResources().getString(R.string.pls_input_again_date));
                    return false;
                }
            case AMOUNT:
                if ((!TextUtils.isDigitsOnly(content) || "0.00".equals(content)) && limit.isMustEnter) {
                    ToastHelper.showMessage(this, getResources().getString(R.string.pls_input_again));
                    return false;
                } else if (TextUtils.isDigitsOnly(content)) {
                    return true;
                }
                break;
            default:
                break;
        }
        return true;
    }

    protected abstract void loadOtherParam();

    protected abstract void sendAbort();

    protected abstract void sendNext(String line1, String line2);


}
