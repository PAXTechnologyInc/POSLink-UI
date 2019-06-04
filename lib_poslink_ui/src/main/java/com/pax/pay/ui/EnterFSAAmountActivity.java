package com.pax.pay.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.method.NumberKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pax.pay.ui.base.BaseActivityWithTickForAction;
import com.pax.pay.ui.constant.EUIMessageKey;
import com.pax.pay.ui.handler.EnterFSAAmountHandler;
import com.pax.pay.ui.utils.CurrencyCode;
import com.pax.pay.ui.utils.CurrencyConverter;
import com.pax.pay.ui.utils.EnterAmountTextWatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EnterFSAAmountActivity extends BaseActivityWithTickForAction<String> {

    TextView amountTv;
    Button confirmBtn;
    LinearLayout transAmountLayout;

    EditText healthcareEditText;
    EditText clinicEditText;
    EditText prescriptionEditText;
    EditText visionEditText;
    EditText dentalEditText;
    EditText transitEditText;

    private long amount;
    //private EnterFSAInfoUI.FSAInfo fsaInfo;
    private boolean isFromECR;

    private EditTextDataLimit limit;
    private String currencyName;
    private String currentAction;
    private String message;
    private String senderPackage;
    private List<String> options;
    private Locale locale;

    EnterFSAAmountHandler mEnterFSAAmountHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTickTimeout();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_fsa_amount;
    }

    @Override
    protected void setListeners() {
        confirmBtn.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        if (amount != 0) {
            amountTv.setText(CurrencyConverter.convert(amount, "", locale));
        } else {
            transAmountLayout.setVisibility(View.INVISIBLE);
        }

        initEditText();
        healthcareEditText.requestFocus();
        healthcareEditText.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(healthcareEditText, InputMethodManager.SHOW_IMPLICIT);
        }, 200);
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
                    //healthcareEditText.setKeepKeyBoardOn(false);
                    clinicEditText.requestFocus();
                }
                return true;
            }
        });

        clinicEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //clinicEditText.setKeepKeyBoardOn(false);
                    prescriptionEditText.requestFocus();
                }
                return true;
            }
        });

        prescriptionEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //prescriptionEditText.setKeepKeyBoardOn(false);
                    visionEditText.requestFocus();
                }
                return true;
            }
        });

        visionEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //visionEditText.setKeepKeyBoardOn(false);
                    dentalEditText.requestFocus();
                }
                return true;
            }
        });

        dentalEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //dentalEditText.setKeepKeyBoardOn(false);
                    transitEditText.requestFocus();
                }
                return true;
            }
        });

        transitEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //transitEditText.setKeepKeyBoardOn(false);
                    onDataConfirmed();
                }
                return true;
            }
        });

    }


    @Override
    protected void loadParam() {
        amountTv = (TextView) findViewById(R.id.amount_tv);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        transAmountLayout = (LinearLayout) findViewById(R.id.trans_amount_layout);
        healthcareEditText = (EditText) findViewById(R.id.fsa_healthcare_edt);
        clinicEditText = (EditText) findViewById(R.id.fsa_clinic_edt);
        prescriptionEditText = (EditText) findViewById(R.id.fsa_prescription_edt);
        visionEditText = (EditText) findViewById(R.id.fsa_vision_edt);
        dentalEditText = (EditText) findViewById(R.id.fsa_dental_edt);
        transitEditText = (EditText) findViewById(R.id.fsa_transit_edt);

        message = getIntent().getStringExtra(EUIMessageKey.INTENT_KEY_MESSAGE);
        senderPackage = getIntent().getStringExtra(EUIMessageKey.KEY_SENDER_PACKAGE);
        options = getIntent().getStringArrayListExtra(EUIMessageKey.INTENT_KEY_OPTIONS);
        currentAction = getIntent().getAction();
        amount = getIntent().getLongExtra(EUIMessageKey.INTENT_KEY_AMOUNT, 0);
        currencyName = getIntent().getStringExtra(EUIMessageKey.IntentKey.CURRENCY);
        if ((currencyName == null) || (currencyName == "")) {
            currencyName = "USD";
        }
        String countryName = CurrencyCode.findTypeByCurrencyNmae(currencyName).getCurrencyName();
        locale = CurrencyConverter.findLocalByCountryName(countryName);
        CurrencyConverter.setDefCurrency(countryName);
        navTitle = "";
        mEnterFSAAmountHandler = new EnterFSAAmountHandler(this);
        mEnterFSAAmountHandler.setAmountRecvPackage(senderPackage);
    }

    @Override
    protected boolean onKeyBackDown() {
        //finish(new ActionResult<EnterFSAInfoUI.FSAInfo>(TransResult.ERR_USER_CANCEL, null));
        mEnterFSAAmountHandler.sendAbort();
        return true;
    }

    private void onDataConfirmed() {
        long healthAmt = CurrencyConverter.parse(healthcareEditText.getText().toString(), locale);
        long clinicAmt = CurrencyConverter.parse(clinicEditText.getText().toString(), locale);
        long prescriptionAmt = CurrencyConverter.parse(prescriptionEditText.getText().toString(), locale);
        long dentalAmt = CurrencyConverter.parse(dentalEditText.getText().toString(), locale);
        long visionAmt = CurrencyConverter.parse(visionEditText.getText().toString(), locale);
        long transitAmt = CurrencyConverter.parse(transitEditText.getText().toString(), locale);

        if (healthAmt + transitAmt > amount) {
            Toast.makeText(this, "FSA AMOUNT EXCEED LIMIT", Toast.LENGTH_LONG).show();
            restartTimer();
            return;
        } else if (clinicAmt + prescriptionAmt + dentalAmt + visionAmt > healthAmt) {
            Toast.makeText(this, "SUB HEALTHCARE AMOUNT EXCEED LIMIT", Toast.LENGTH_LONG).show();
            restartTimer();
            return;
        }

        //mEnterTextHandler.setAmount(healthAmt, prescriptionAmt, visionAmt, clinicAmt, dentalAmt, transitAmt);
        Map<String, Long> map = new HashMap<>();
        map.put(EUIMessageKey.INTENT_KEY_HEALTH_CARE_AMOUNT, healthAmt);
        map.put(EUIMessageKey.INTENT_KEY_CLINIC_AMOUNT, clinicAmt);
        map.put(EUIMessageKey.INTENT_KEY_PRESCRIPTION_AMOUNT, prescriptionAmt);
        map.put(EUIMessageKey.INTENT_KEY_DENTAL_AMOUNT, dentalAmt);
        map.put(EUIMessageKey.INTENT_KEY_VISION_AMOUNT, visionAmt);
        map.put(EUIMessageKey.INTENT_KEY_TRANSIT_AMOUNT, transitAmt);
        mEnterFSAAmountHandler.sendMap(map);
        //fsaInfo = new EnterFSAInfoUI.FSAInfo(healthAmt, prescriptionAmt, visionAmt, clinicAmt, dentalAmt, transitAmt);
        //finish(new ActionResult<EnterFSAInfoUI.FSAInfo>(TransResult.SUCC, fsaInfo));

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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void setEditTextAmount(EditText mEditText) {
        //mEditText.setKeyboardId(this, R.xml.keyboard_numeric_confirm);
        //mEditText.setKeepKeyBoardOn(false);
        mEditText.setCursorVisible(false);
        mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        EnterAmountTextWatcher watcher = new EnterAmountTextWatcher();
        mEditText.addTextChangedListener(watcher);
        mEditText.setText("");
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

        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    ;
                }
                return false;
            }
        });
    }
}
