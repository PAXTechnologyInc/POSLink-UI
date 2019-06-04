package com.pax.pay.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.base.BaseActivityWithTickForAction;
import com.pax.pay.ui.constant.EUIMessageKey;
import com.pax.pay.ui.handler.EnterTextHandler;
import com.pax.pay.ui.utils.DateUtils;
import com.pax.pay.ui.utils.EnterDataLineHelper;
import com.pax.pay.ui.utils.StringUtils;
import com.pax.pay.ui.utils.ToastHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//import butterknife.BindView;

/**
 * Created by Sim.G on 2017/5/27 16:57
 */
abstract class EnterDataLine2Activity<T extends Map<String, String>> extends BaseActivityWithTickForAction<T> implements TextView.OnEditorActionListener {
    LinearLayout data1;
    LinearLayout data2;
    TextView promptTv1;
    EditText editTv1;
    TextView promptTv2;
    EditText editTv2;
    Button confirmBtn;

    EditTextDataLimit limit1;
    EditTextDataLimit limit2;

    private String currentAction;
    private String message;
    private String senderPackage;
    private List<String> options;
    private Locale locale;

    EnterTextHandler mEnterTextHandler;


    private void setEditText(EditText editText, EditTextDataLimit limit) {
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
    private void setEditTextAmount(EditText editText, final EditTextDataLimit limit) {
        EnterDataLineHelper.setEditTextAmount(this, editText, limit);
    }

    // 日期
    private void setEditTextDate(EditText editText, final EditTextDataLimit limit) {
        EnterDataLineHelper.setEditTextExpiry(this, editText, limit);
    }

    // 纯数字
    private void setEditTextNumber(final EditText editText, final EditTextDataLimit limit) {
        EnterDataLineHelper.setEditTextNumber(this, editText, limit);
    }

    private void setEditTextAllText(EditText editText, final EditTextDataLimit limit) {
        EnterDataLineHelper.setEditTextAllText(this, editText, limit);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_line_2_layout;
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
        if (actionId == EditorInfo.IME_ACTION_NONE) {//Fix ANFDRC-226, After click KEY_BACK_DOWN
            //finish(new ActionResult<>(TransResult.ERR_USER_CANCEL, null));
            //To do Abort
            mEnterTextHandler.sendAbort();
            return true;
        } else if (v.getId() == editTv2.getId() && actionId == EditorInfo.IME_ACTION_DONE) {
            infoConfirmOnBtnClick();
        }
        return false;
    }

    @Override
    protected void loadParam() {
        data1 = (LinearLayout) findViewById(R.id.data_1);
        data2 = (LinearLayout) findViewById(R.id.data_2);
        promptTv1 = (TextView) findViewById(R.id.prompt_tv_1);
        editTv1 = (EditText) findViewById(R.id.edit_tv_1);
        promptTv2 = (TextView) findViewById(R.id.prompt_tv_2);
        editTv2 = (EditText) findViewById(R.id.edit_tv_2);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);

        navTitle = "";
        message = getIntent().getStringExtra(EUIMessageKey.INTENT_KEY_MESSAGE);
        senderPackage = getIntent().getStringExtra(EUIMessageKey.KEY_SENDER_PACKAGE);
        options = getIntent().getStringArrayListExtra(EUIMessageKey.INTENT_KEY_OPTIONS);
        currentAction = getIntent().getAction();
        loadOtherParam(message);
        mEnterTextHandler = new EnterTextHandler(this);
        mEnterTextHandler.setAmountRecvPackage(senderPackage);
    }

    public void setLimit(EditTextDataLimit limit1, EditTextDataLimit limit2) {
        this.limit1 = limit1;
        this.limit2 = limit2;
        if (limit1 == null && limit2 == null) {
            mEnterTextHandler.sendAbort();
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
            editTv1.setImeOptions(EditorInfo.IME_ACTION_DONE);
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
            editTv1.setEnabled(false);
            data1.setVisibility(View.INVISIBLE);
            editTv2.requestFocus();
            editTv2.setImeOptions(EditorInfo.IME_ACTION_DONE);
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
        mEnterTextHandler.sendAbort();
        return true;
    }

    @Override
    public final void onClick(View v) {
        int i = v.getId();
        if (i == R.id.confirm_btn) {
            infoConfirmOnBtnClick();
        }
    }

    private void infoConfirmOnBtnClick() {
        confirmBtn.setClickable(false);
        InputMethodManager imm = (InputMethodManager) EnterDataLine2Activity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTv1, 0);
        //InputMethodManager imm2 = (InputMethodManager) EnterDataLine2Activity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTv2, 0);

        if (checkInput(limit1 != null && limit2 != null, editTv1, limit1)
                && checkInput(true, editTv2, limit1 != null ? limit1 : limit2)) {
            confirmBtn.requestFocusFromTouch();
            //To Do sendMap
            mEnterTextHandler.sendMap(genResult());
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
                SimpleDateFormat simpleDateFormat = DateUtils.create("yyyyMM");
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

    protected abstract Map<String, String> genResult();

    protected abstract void loadOtherParam(String message);


}
