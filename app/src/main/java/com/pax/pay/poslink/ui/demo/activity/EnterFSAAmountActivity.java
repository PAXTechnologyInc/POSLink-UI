package com.pax.pay.poslink.ui.demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.poslink.ui.demo.R;
import com.pax.pay.poslink.ui.demo.base.RespStatusImpl;
import com.pax.pay.poslink.ui.demo.utils.StringUtils;
import com.pax.us.pay.ui.core.helper.EnterFSAAmountHelper;

public class EnterFSAAmountActivity extends AppCompatActivity implements View.OnClickListener, EnterFSAAmountHelper.IEnterFSAAmountListener {

    TextView amountTv;
    Button confirmBtn;
    LinearLayout transAmountLayout;

    EditText healthcareEditText;
    EditText clinicEditText;
    EditText prescriptionEditText;
    EditText visionEditText;
    EditText dentalEditText;
    EditText copayEditText;
    EditText transitEditText;

    LinearLayout healthcareLayout;
    LinearLayout clinicLayout;
    LinearLayout prescriptionLayout;
    LinearLayout visionLayout;
    LinearLayout dentalLayout;
    LinearLayout copayLayout;
    LinearLayout transitLayout;

    private long totalAmount;

    private boolean healthcardEnable;

    private EnterFSAAmountHelper helper;

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
        copayEditText = (EditText) findViewById(R.id.fsa_copay_edt);
        transitEditText = (EditText) findViewById(R.id.fsa_transit_edt);

        healthcareLayout = (LinearLayout) findViewById(R.id.trans_healthcare_layout);
        healthcareLayout.setVisibility(View.GONE);
        clinicLayout = (LinearLayout) findViewById(R.id.trans_clinic_layout);
        clinicLayout.setVisibility(View.GONE);
        prescriptionLayout = (LinearLayout) findViewById(R.id.trans_prescription_layout);
        prescriptionLayout.setVisibility(View.GONE);
        visionLayout = (LinearLayout) findViewById(R.id.trans_vision_layout);
        visionLayout.setVisibility(View.GONE);
        dentalLayout = (LinearLayout) findViewById(R.id.trans_dental_layout);
        dentalLayout.setVisibility(View.GONE);
        copayLayout = (LinearLayout) findViewById(R.id.trans_copay_layout);
        copayLayout.setVisibility(View.GONE);
        transitLayout = (LinearLayout) findViewById(R.id.trans_transit_layout);
        transitLayout.setVisibility(View.GONE);

        confirmBtn.setOnClickListener(this);
        healthcardEnable = false;

//        healthcareEditText.requestFocus();
//        healthcareEditText.postDelayed(() -> {
//            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.showSoftInput(healthcareEditText, InputMethodManager.SHOW_IMPLICIT);
//        }, 200);


        helper = new EnterFSAAmountHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());
        ActivityLocalManager.getInstance().addActivity(this);
    }

//    private void initEditText() {
//        setEditTextAmount(healthcareEditText);
//        setEditTextAmount(clinicEditText);
//        setEditTextAmount(prescriptionEditText);
//        setEditTextAmount(visionEditText);
//        setEditTextAmount(dentalEditText);
//        setEditTextAmount(transitEditText);
//        healthcareEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    clinicEditText.requestFocus();
//                }
//                return true;
//            }
//        });
//
//        clinicEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    prescriptionEditText.requestFocus();
//                }
//                return true;
//            }
//        });
//
//        prescriptionEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    visionEditText.requestFocus();
//                }
//                return true;
//            }
//        });
//
//        visionEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    dentalEditText.requestFocus();
//                }
//                return true;
//            }
//        });
//
//        dentalEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    transitEditText.requestFocus();
//                }
//                return true;
//            }
//        });
//
//        transitEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    OnDataConfirm();
//                }
//                return true;
//            }
//        });
//
//    }

    private void setEditTextAmount(EditText mEditText) {

        mEditText.setClickable(true);
        mEditText.setLongClickable(false);
        mEditText.setCursorVisible(false);
        mEditText.setFocusable(true);
        mEditText.setOnClickListener(this);
        mEditText.setFocusableInTouchMode(true);

        mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        mEditText.setText("");
        mEditText.setSelection(mEditText.getText().length());


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
    protected void onResume() {
        super.onResume();
        //initEditText();
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
        super.onDestroy();
    }

    void OnDataConfirm() {
        long healthAmt = StringUtils.parseLong(healthcareEditText.getText().toString());
        long clinicAmt = StringUtils.parseLong(clinicEditText.getText().toString());
        long prescriptionAmt = StringUtils.parseLong(prescriptionEditText.getText().toString());
        long dentalAmt = StringUtils.parseLong(dentalEditText.getText().toString());
        long visionAmt = StringUtils.parseLong(visionEditText.getText().toString());
        long transitAmt = StringUtils.parseLong(transitEditText.getText().toString());
        long copayAmt = StringUtils.parseLong(copayEditText.getText().toString());
        helper.sendNext(healthAmt, clinicAmt, prescriptionAmt, dentalAmt, visionAmt, copayAmt, transitAmt, totalAmount);
    }

    @Override
    public void onShowAmount(long amount) {
        totalAmount = amount;
        if (totalAmount != 0) {
            amountTv.setText(String.valueOf(amount));
        } else {
            transAmountLayout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message) {

    }


    @Override
    public void onShowFsaAmountOption(boolean healthCareAmountVisible, boolean ClinicAmountVisible, boolean prescriptionVisible,
                                      boolean dentalVisible, boolean versionVisible, boolean copayVisible, boolean transitAmount) {
        if (healthCareAmountVisible) {
            healthcareLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(healthcareEditText);

            healthcareEditText.requestFocus();
            healthcareEditText.postDelayed(() -> {
                InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(healthcareEditText, InputMethodManager.SHOW_IMPLICIT);
            }, 200);
            healthcardEnable = true;
        }
        if (ClinicAmountVisible) {
            clinicLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(clinicEditText);
        }
        if (prescriptionVisible) {
            prescriptionLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(prescriptionEditText);
        }

        if (dentalVisible) {
            dentalLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(dentalEditText);
        }

        if (versionVisible) {
            visionLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(visionEditText);
        }

        if (copayVisible) {
            copayLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(copayEditText);
        }

        if (transitAmount) {
            transitLayout.setVisibility(View.VISIBLE);
            setEditTextAmount(transitEditText);
            if (healthcardEnable == false) {
                transitLayout.requestFocus();
                transitLayout.postDelayed(() -> {
                    InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(transitLayout, InputMethodManager.SHOW_IMPLICIT);
                }, 200);
            }
        }
    }
}
