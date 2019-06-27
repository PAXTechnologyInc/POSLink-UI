package com.pax.pay.ui.def_ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.NumberKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def_ui.eventbus.EventBusConstant;
import com.pax.pay.ui.def_ui.eventbus.EventBusUtil;
import com.pax.pay.ui.def_ui.utils.CurrencyCode;
import com.pax.pay.ui.def_ui.utils.CurrencyConverter;
import com.pax.pay.ui.def_ui.utils.EnterAmountTextWatcher;
import com.pax.pay.ui.def_ui.utils.ToastHelper;
import com.pax.us.pay.ui.core.RespMessage;
import com.pax.us.pay.ui.core.UIMessageManager;
import com.pax.us.pay.ui.core.api.IAmountListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.helper.FSAAmountHelper;

import java.util.Locale;

public class EnterFSAAmountActivity extends AppCompatActivity implements View.OnClickListener, IMessageListener, ICurrencyListener, IAmountListener {

    TextView amountTv;
    Button confirmBtn;
    LinearLayout transAmountLayout;

    EditText healthcareEditText;
    EditText clinicEditText;
    EditText prescriptionEditText;
    EditText visionEditText;
    EditText dentalEditText;
    EditText transitEditText;
    private Locale locale;
    private long totalAmount;
    private int minLen, maxLen;

    private FSAAmountHelper helper = new FSAAmountHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_fsa_amount);

        amountTv = (TextView) findViewById(R.id.amount_tv);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        transAmountLayout = (LinearLayout) findViewById(R.id.trans_amount_layout);
        healthcareEditText = (EditText) findViewById(R.id.fsa_healthcare_edt);
        clinicEditText = (EditText) findViewById(R.id.fsa_clinic_edt);
        prescriptionEditText = (EditText) findViewById(R.id.fsa_prescription_edt);
        visionEditText = (EditText) findViewById(R.id.fsa_vision_edt);
        dentalEditText = (EditText) findViewById(R.id.fsa_dental_edt);
        transitEditText = (EditText) findViewById(R.id.fsa_transit_edt);

        minLen = 0;
        maxLen = 300;


        healthcareEditText.requestFocus();
        healthcareEditText.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(healthcareEditText, InputMethodManager.SHOW_IMPLICIT);
        }, 200);

        UIMessageManager.getInstance().registerUI(this, this, helper, getIntent(), new IRespStatus() {
            @Override
            public void onAccepted() {
                EventBusUtil.postEvent(EventBusConstant.END_EVENT);
                finish();
            }

            @Override
            public void onDeclined(RespMessage respMessage) {
                String buff = "Request Declined\n Error Code:" + respMessage.getResultCode() + "\n Error Msg: " + respMessage.getResultMsg();
                //Toast.makeText(this, buff, Toast.LENGTH_LONG).show();
                ToastHelper.showMessage(EnterFSAAmountActivity.this, buff);
            }
        });
    }

    private void initEditText() {
        setEditTextAmount(healthcareEditText);
        setEditTextAmount(clinicEditText);
        setEditTextAmount(prescriptionEditText);
        setEditTextAmount(visionEditText);
        setEditTextAmount(dentalEditText);
        setEditTextAmount(transitEditText);
        healthcareEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    clinicEditText.requestFocus();
                }
                return true;
            }
        });

        clinicEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    prescriptionEditText.requestFocus();
                }
                return true;
            }
        });

        prescriptionEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    visionEditText.requestFocus();
                }
                return true;
            }
        });

        visionEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    dentalEditText.requestFocus();
                }
                return true;
            }
        });

        dentalEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    transitEditText.requestFocus();
                }
                return true;
            }
        });

        transitEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    OnDataConfirm();
                }
                return true;
            }
        });

    }

    private void setEditTextAmount(EditText mEditText) {

        mEditText.setClickable(true);
        mEditText.setLongClickable(false);
        mEditText.setCursorVisible(false);
        mEditText.setFocusable(true);
        mEditText.setOnClickListener(this);
        mEditText.setFocusableInTouchMode(true);

        mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        EnterAmountTextWatcher watcher = new EnterAmountTextWatcher();
        mEditText.addTextChangedListener(watcher);
        mEditText.setText("");
        mEditText.setSelection(mEditText.getText().length());

        mEditText.setKeyListener(new NumberKeyListener() {
            @Override
            protected char[] getAcceptedChars() {
                //Customer can add their accepted charter or symbol
                char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '$'};
                return chars;
            }

            @Override
            public int getInputType() {
                return 8194;  //only popup number keyboard, don't use InputType.TYPE_NUMBER_FLAG_DECIMAL(popup all text keyboard);
            }
        });

        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    //after focus change then EditText get focus and move the cursor to the touch place immediately,
                    // so it should delay 50ms to set cursor to the tail.
                    mEditText.postDelayed(() -> mEditText.setSelection(mEditText.getText().length()), 50);
                }
            }
        });

        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mEditText.requestFocus();
                int len = mEditText.getText().length();
                mEditText.setSelection(mEditText.getText().length());
            }
        });
    }

    @Override
    public void onClick(View view) {
        OnDataConfirm();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            OnDataConfirm();
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

    void OnDataConfirm() {
        long healthAmt = CurrencyConverter.parse(healthcareEditText.getText().toString(), locale);
        long clinicAmt = CurrencyConverter.parse(clinicEditText.getText().toString(), locale);
        long prescriptionAmt = CurrencyConverter.parse(prescriptionEditText.getText().toString(), locale);
        long dentalAmt = CurrencyConverter.parse(dentalEditText.getText().toString(), locale);
        long visionAmt = CurrencyConverter.parse(visionEditText.getText().toString(), locale);
        long transitAmt = CurrencyConverter.parse(transitEditText.getText().toString(), locale);
        helper.sendNext(healthAmt, clinicAmt, prescriptionAmt, dentalAmt, visionAmt, transitAmt, totalAmount, new IRespStatus() {
            @Override
            public void onAccepted() {
                ;
            }

            @Override
            public void onDeclined(RespMessage respMessage) {
                String buff = "Request Declined\n Error Code:" + respMessage.getResultCode() + "\n Error Msg: " + respMessage.getResultMsg();
                //Toast.makeText(this, buff, Toast.LENGTH_LONG).show();
                ToastHelper.showMessage(EnterFSAAmountActivity.this, buff);
            }
        });
    }

    @Override
    public void onShowMessage(String message) {

    }


    @Override
    public void onShowAmount(long amount) {
        totalAmount = amount;
        if (totalAmount != 0) {
            amountTv.setText(CurrencyConverter.convert(totalAmount, "", locale));
        } else {
            transAmountLayout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onShowCurrency(String currency) {
        if ((currency == null) || (currency == "")) {
            currency = "USD";
        }
        String countryName = CurrencyCode.findTypeByCurrencyNmae(currency).getCurrencyName();
        locale = CurrencyConverter.findLocalByCountryName(countryName);
        CurrencyConverter.setDefCurrency(countryName);
    }

    @Override
    public void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initEditText();
    }

}
