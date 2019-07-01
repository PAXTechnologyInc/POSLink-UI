package com.pax.pay.poslink.ui.demo;

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

import com.pax.pay.poslink.ui.demo.activity.ActivityManager;
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
    EditText transitEditText;
    private long totalAmount;

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
        transitEditText = (EditText) findViewById(R.id.fsa_transit_edt);
        confirmBtn.setOnClickListener(this);

        healthcareEditText.requestFocus();
        healthcareEditText.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(healthcareEditText, InputMethodManager.SHOW_IMPLICIT);
        }, 200);

        helper = new EnterFSAAmountHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());
        ActivityManager.getInstance().addActivity(this);
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
        initEditText();
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
        helper.sendNext(healthAmt, clinicAmt, prescriptionAmt, dentalAmt, visionAmt, transitAmt, totalAmount);
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
    public void onShowCurrency(String currency) {
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message) {

    }
}
