package com.pax.pay.ui.def;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.utils.EnterAmountTextWatcher;
import com.pax.pay.ui.def.utils.FSAType;
import com.pax.us.pay.ui.component.keyboard.CustomKeyboardEditText;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.helper.EnterFSAAmountHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;

@Deprecated
public class EnterFSAAmountActivity extends BaseAppActivity implements View.OnClickListener, EnterFSAAmountHelper.IEnterFSAAmountListener {

    TextView amountTv;
    Button confirmBtn;
    LinearLayout transAmountLayout;

    CustomKeyboardEditText healthcareEditText;
    CustomKeyboardEditText clinicEditText;
    CustomKeyboardEditText prescriptionEditText;
    CustomKeyboardEditText visionEditText;
    CustomKeyboardEditText dentalEditText;
    CustomKeyboardEditText copayEditText;
    CustomKeyboardEditText otcEditText;
    CustomKeyboardEditText transitEditText;

    LinearLayout healthcareLayout;
    LinearLayout clinicLayout;
    LinearLayout prescriptionLayout;
    LinearLayout visionLayout;
    LinearLayout dentalLayout;
    LinearLayout copayLayout;
    LinearLayout otcLayout;

    LinearLayout transitLayout;
    private long amount;
    private boolean healthcardEnable;
    private EnterFSAAmountHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_fsa_amount_default;
    }

    @Override
    protected void setListeners() {
        confirmBtn.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void loadParam() {
        amountTv = findViewById(R.id.amount_tv);
        confirmBtn = findViewById(R.id.confirm_btn);
        transAmountLayout = findViewById(R.id.trans_amount_layout);
        healthcareEditText = findViewById(R.id.fsa_healthcare_edt);
        clinicEditText = findViewById(R.id.fsa_clinic_edt);
        prescriptionEditText = findViewById(R.id.fsa_prescription_edt);
        visionEditText = findViewById(R.id.fsa_vision_edt);
        dentalEditText = findViewById(R.id.fsa_dental_edt);
        copayEditText = findViewById(R.id.fsa_copay_edt);
        otcEditText = findViewById(R.id.fsa_otc_edt);
        transitEditText = findViewById(R.id.fsa_transit_edt);

        healthcareLayout = findViewById(R.id.trans_healthcare_layout);
        healthcareLayout.setVisibility(View.GONE);
        otcLayout = findViewById(R.id.trans_otc_layout);
        otcLayout.setVisibility(View.GONE);
        clinicLayout = findViewById(R.id.trans_clinic_layout);
        clinicLayout.setVisibility(View.GONE);
        prescriptionLayout = findViewById(R.id.trans_prescription_layout);
        prescriptionLayout.setVisibility(View.GONE);
        visionLayout = findViewById(R.id.trans_vision_layout);
        visionLayout.setVisibility(View.GONE);
        dentalLayout = findViewById(R.id.trans_dental_layout);
        dentalLayout.setVisibility(View.GONE);
        copayLayout = findViewById(R.id.trans_copay_layout);
        copayLayout.setVisibility(View.GONE);
        transitLayout = findViewById(R.id.trans_transit_layout);
        transitLayout.setVisibility(View.GONE);
        healthcardEnable = false;
        CurrencyConverter.setDefCurrency(CurrencyType.USD);
        helper = new EnterFSAAmountHelper(this, new RespStatusImpl(this));
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


    @Override
    protected boolean onKeyBackDown() {
        helper.sendAbort();
        return true;
    }

    private void onDataConfirmed() {
        long healthAmt = CurrencyConverter.parse(healthcareEditText.getText().toString());
        long clinicAmt = CurrencyConverter.parse(clinicEditText.getText().toString());
        long prescriptionAmt = CurrencyConverter.parse(prescriptionEditText.getText().toString());
        long dentalAmt = CurrencyConverter.parse(dentalEditText.getText().toString());
        long visionAmt = CurrencyConverter.parse(visionEditText.getText().toString());
        long copayAmt = CurrencyConverter.parse(copayEditText.getText().toString());
        long transitAmt = CurrencyConverter.parse(transitEditText.getText().toString());
        long otcAmt = CurrencyConverter.parse(otcEditText.getText().toString());

        if (healthAmt + transitAmt > amount) {
            Toast.makeText(this, "FSA AMOUNT EXCEED LIMIT", Toast.LENGTH_LONG).show();
            return;
        } else if (clinicAmt + prescriptionAmt + dentalAmt + visionAmt > healthAmt) {
            Toast.makeText(this, "Sub Healthcare Amount Exceeded Limit", Toast.LENGTH_LONG).show();
            return;
        }

        helper.sendNext(healthAmt, clinicAmt, prescriptionAmt, dentalAmt, visionAmt, copayAmt, otcAmt,transitAmt, amount, FSAType.HealthCare.toString());

    }

    @Override
    public void onClickProtected(View view) {
        int i = view.getId();
        if (i == R.id.confirm_btn) {
            onDataConfirmed();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void setEditTextAmount(CustomKeyboardEditText mEditText) {
        mEditText.setKeyboardId(R.xml.keyboard_numeric_confirm);
        mEditText.setKeepKeyBoardOn(false);
        EnterAmountTextWatcher watcher = new EnterAmountTextWatcher();
        mEditText.addTextChangedListener(watcher);
        mEditText.setText("");
    }

    @Override
    public void onShowAmount(long l) {
        amount = l;
        amountTv.setText(CurrencyConverter.convert(amount, ""));
    }

    @Override
    public void onShowCurrency(@Nullable String s, boolean isPoint) {
        CurrencyConverter.setDefCurrency(s);
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    
    @Override
    public void onShowFsaAmountOption(boolean healthCareVisible, boolean ClinicVisible, boolean prescriptionVisible,
                                      boolean dentalVisible, boolean versionVisible, boolean copayVisible, boolean otcVisible, boolean transitVisable) {
        if (healthCareVisible) {
            healthcareLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(healthcareEditText);
            healthcareEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        healthcareEditText.setKeepKeyBoardOn(false);
                        //clinicEditText.requestFocus();
                    }
                    return true;
                }
            });
            healthcareEditText.requestFocus();
            healthcardEnable = true;
        }
        if (otcVisible) {
            otcLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(otcEditText);
            otcEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        otcEditText.setKeepKeyBoardOn(false);
                    }
                    return true;
                }
            });
        }

        if (ClinicVisible) {
            clinicLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(clinicEditText);
            clinicEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        clinicEditText.setKeepKeyBoardOn(false);
                    }
                    return true;
                }
            });
        }
        if (prescriptionVisible) {
            prescriptionLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(prescriptionEditText);
            prescriptionEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        prescriptionEditText.setKeepKeyBoardOn(false);
                    }
                    return true;
                }
            });
        }
        if (dentalVisible) {
            dentalLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(dentalEditText);
            dentalEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        dentalEditText.setKeepKeyBoardOn(false);
                    }
                    return true;
                }
            });
        }
        if (versionVisible) {
            visionLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(visionEditText);
            visionEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        visionEditText.setKeepKeyBoardOn(false);
                    }
                    return true;
                }
            });
        }
        if (copayVisible) {
            copayLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(copayEditText);
            copayEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        copayEditText.setKeepKeyBoardOn(false);
                    }
                    return true;
                }
            });
        }
        if (transitVisable) {
            transitLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(transitEditText);
            transitEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        transitEditText.setKeepKeyBoardOn(false);
                    }
                    return true;
                }
            });
            if (!healthcardEnable) {
                transitLayout.requestFocus();
            }
        }
    }

    @Override
    public void onHasPhyKeyboard(boolean notShowVirtualKeyBoard) {
        if(notShowVirtualKeyBoard ){
           // confirmBtn.setVisibility(View.VISIBLE);
            if (View.VISIBLE == healthcareLayout.getVisibility()) {
                healthcareEditText.hideKeyboard(true, false);
            }
            if (View.VISIBLE == transitLayout.getVisibility()) {
                transitEditText.hideKeyboard(true, false);
            }
        }
    }
}
