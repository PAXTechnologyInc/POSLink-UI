package com.pax.pay.ui;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.base.BaseActivityWithTickForAction;
import com.pax.pay.ui.constant.ActivityIntentAction;
import com.pax.pay.ui.constant.EUIMessageKey;
import com.pax.pay.ui.dialog.CustomAlertDialog;
import com.pax.pay.ui.handler.EnterTextHandler;
import com.pax.pay.ui.utils.CurrencyCode;
import com.pax.pay.ui.utils.CurrencyConverter;
import com.pax.pay.ui.utils.DateUtils;
import com.pax.pay.ui.utils.EnterDataLineHelper;
import com.pax.pay.ui.utils.StringUtils;
import com.pax.pay.ui.utils.ToastHelper;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

//import butterknife.BindView;


abstract class EnterDataLine1Activity<T> extends BaseActivityWithTickForAction<T> {

    TextView amountTv;
    TextView promptTv;
    EditText mEditText;
    Button confirmBtn;
    LinearLayout transAmountLayout;

    private EditTextDataLimit limit;
    private long amount;
    private String currencyName;
    private String currentAction;
    private String message;
    private String senderPackage;
    private List<String> options;
    private Locale locale;

    EnterTextHandler mEnterTextHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_info;
    }

    @Override
    protected void setListeners() {
        confirmBtn.setOnClickListener(this);
    }

    @Override
    protected void loadParam() {
        amountTv = (TextView) findViewById(R.id.amount_tv);
        promptTv = (TextView) findViewById(R.id.prompt_tv);
        mEditText = (EditText) findViewById(R.id.data_edt);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        transAmountLayout = (LinearLayout) findViewById(R.id.trans_amount_layout);

        message = getIntent().getStringExtra(EUIMessageKey.INTENT_KEY_MESSAGE);
        senderPackage = getIntent().getStringExtra(EUIMessageKey.KEY_SENDER_PACKAGE);
        options = getIntent().getStringArrayListExtra(EUIMessageKey.INTENT_KEY_OPTIONS);
        currentAction = getIntent().getAction();
        loadOtherParam(message);
        if (currencyName != null) {
            String countryName = CurrencyCode.findTypeByCurrencyNmae(currencyName).getCurrencyName();
            locale = CurrencyConverter.findLocalByCountryName(countryName);
            CurrencyConverter.setDefCurrency(countryName);
        }

        mEnterTextHandler = new EnterTextHandler(this);
        mEnterTextHandler.setAmountRecvPackage(senderPackage);
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setLimit(EditTextDataLimit limit) {
        this.limit = limit;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    @Override
    public void initViews() {
        if (amount != 0) {
            amountTv.setText(CurrencyConverter.convert(amount, "", locale));
        } else {
            transAmountLayout.setVisibility(View.INVISIBLE);
        }
        promptTv.setText(limit != null ? limit.prompt : "");
        setEditText();
        mEditText.setCursorVisible(false);
        mEditText.requestFocus();
        mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((i == EditorInfo.IME_ACTION_DONE) || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    onDataConfirmed();
                    return false;

                } else if ((i == EditorInfo.IME_ACTION_NONE) || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK)) {
                    mEnterTextHandler.sendAbort();
                    return true;
                }
                return false;
            }

        });

        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((i == KeyEvent.ACTION_UP) && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK)) {
                    mEditText.onEditorAction(EditorInfo.IME_ACTION_NONE);
                    //Logger.d("editTextvalue:" + editText.getText());
                    return true;
                }
                return false;
            }
        });
        //mEditText.setOnKey
        getSupportActionBar().setTitle(navTitle);
        mEditText.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) EnterDataLine1Activity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
        }, 200);
        //waitPop(mEditText);
    }

    //等待弹出方法
    private void waitPop(EditText editText) {
        Timer timer = new Timer();//开启一个时间等待任务
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);//得到系统的输入方法服务
                imm.showSoftInput(editText, 0);
            }
        }, 300);
    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        /* 返回键 */
//        if (keyCode == Keyboard.KEYCODE_CANCEL) {
//            mEditText.onEditorAction(EditorInfo.IME_ACTION_NONE);;
//        }else if (keyCode == Keyboard.KEYCODE_DONE) {
//            mEditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
//        }
//        return false;
//    }


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
        EnterDataLineHelper.setEditTextAmount(this, mEditText, limit);
    }

    // 日期
    private void setEditTextExpiryDate() {
        //mEditText.setKeepKeyBoardOn(true);
        EnterDataLineHelper.setEditTextExpiry(this, mEditText, limit);
    }

    private void setEditTextDate() {
        //mEditText.setKeepKeyBoardOn(true);
        EnterDataLineHelper.setEditTextDate(this, mEditText);
    }


    // 纯数字
    private void setEditTextNumber() {
        EnterDataLineHelper.setEditTextNumber(this, mEditText, limit);
    }

    private void setEditTextAllText() {
//        if (limit.inputType == EInputType.ALLTEXT) {
//            mEditText.postDelayed(() -> {
//                InputMethodManager imm = (InputMethodManager) EnterDataLine1Activity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
//            }, 100);
//        }
        EnterDataLineHelper.setEditTextAllText(this, mEditText, limit);
    }

    @Override
    protected boolean onKeyBackDown() {
        mEnterTextHandler.sendAbort();
        return true;
    }

    @Override
    protected boolean onKeyEnterDown() {
        onDataConfirmed();
        return true;
    }

    @Override
    public final void onClick(View v) {
        int i = v.getId();
        if (i == R.id.confirm_btn) {
            onDataConfirmed();
        }
    }

    private void onDataConfirmed() {
        try {
            confirmBtn.setClickable(false);

            String content = mEditText.getText().toString();
            if (limit != null && limit.isMustEnter && StringUtils.isEmpty(content)) {
                return;
            }
            if (!validate(content)) {
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

    protected boolean validate(String content) {
        switch (limit.inputType) {
            case NUM:
                if (limit.minLen == 0 && limit.maxLen == 0) { // don't need to enter;
                    return true;
                }
                if (content != null && (content.length() < limit.minLen || content.length() > limit.maxLen)) {
                    if (limit.minLen == 4 && limit.maxLen == 4) {
                        ToastHelper.showMessage(this, getString(R.string.pls_input_4digit));
                    } else {
                        ToastHelper.showMessage(this, getString(R.string.notice_is_out_of_range, "length", limit.minLen, limit.maxLen));
                    }
                    return false;
                } else {
                    long value = StringUtils.parseLong(content);
                    if (value > limit.maxValue || value < limit.minValue) {
                        ToastHelper.showMessage(this, getString(R.string.notice_is_out_of_range, "number", limit.minValue, limit.maxValue));
                        return false;
                    } else if (limit.minValue != 0) {
                        if (StringUtils.parseLong(content) < limit.minValue) {
                            ToastHelper.showMessage(this, getString(R.string.data_cannot_zero));
                            return false;
                        }
                    }
                }
                mEditText.setText("");
                break;
            case EXPIRY_DATE:
                if (StringUtils.isEmpty(content)) {
                    return false;
                }
                if (content.length() != 5) {
                    return false;
                }
                int month = StringUtils.parseInt(content.substring(0, 2));
                if (month < 1 || month > 12) {
                    ToastHelper.showMessage(this, getString(R.string.invalid_exp_date));
                    return false;
                }
                mEditText.setText("");
                break;
            case AMOUNT:
                if (CurrencyConverter.parse(content, locale) == 0 && limit.isMustEnter) {
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

                        SimpleDateFormat dateFormat = DateUtils.create("yyyyMMdd");
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
                break;
        }
        return true;
    }

    protected void confirm(final T data) {
        final WeakReference<Context> contextWeakReference = new WeakReference<>(this);
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Context ctx = contextWeakReference.get();
                CustomAlertDialog dialog = new CustomAlertDialog(ctx, CustomAlertDialog.NORMAL_TYPE);
                dialog.setCancelClickListener(alertDialog ->
                {
                    alertDialog.dismiss();
                    mEnterTextHandler.sendAbort();
                }).
                        setConfirmClickListener(alertDialog ->
                        {
                            alertDialog.dismiss();
                            sendNext(data);
                        }).
                        create();
                dialog.setContent(limit != null ? limit.confirmPrompt : "");
                dialog.showConfirmButton(true);
                dialog.showCancelButton(true);
                dialog.show();
            }
        };
        ((Activity) this).runOnUiThread(run);
    }

    private void sendNext(T data) {
        switch (currentAction) {
            case ActivityIntentAction.ACTION_ENTER_AMOUNT:
                long amount = (Long) data;
                mEnterTextHandler.sendNext(EUIMessageKey.INTENT_KEY_AMOUNT, amount);
                break;
            case ActivityIntentAction.ACTION_ENTER_TIP:
                long tip = (Long) data;
                mEnterTextHandler.sendNext(EUIMessageKey.INTENT_KEY_TIP, tip);
                break;
            case ActivityIntentAction.ACTION_ENTER_ZIPCODE:
                mEnterTextHandler.sendNext(EUIMessageKey.INTENT_KEY_ZIP_CODE, (String) data);
                break;
            case ActivityIntentAction.ACTION_ENTER_AUTH:
                mEnterTextHandler.sendNext(EUIMessageKey.INTENT_KEY_AUTH_CODE, (String) data);
                break;
            case ActivityIntentAction.ACTION_ENTER_ADDRESS:
                mEnterTextHandler.sendNext(EUIMessageKey.INTENT_KEY_ADDRESS, (String) data);
                break;
            case ActivityIntentAction.ACTION_ENTER_EXPIRE_DATE:
                mEnterTextHandler.sendNext(EUIMessageKey.INTENT_KEY_EXPIRE_DATE, (String) data);
                break;
            case ActivityIntentAction.ACTION_ENTER_TRANS_NO:
                int transNo = (Integer) data;
                mEnterTextHandler.sendNext(EUIMessageKey.INTENT_KEY_TRANS_NO, transNo);
                break;
            default:
                break;
        }

    }

    protected abstract T convert(String content);

    protected abstract void loadOtherParam(String message);
}
