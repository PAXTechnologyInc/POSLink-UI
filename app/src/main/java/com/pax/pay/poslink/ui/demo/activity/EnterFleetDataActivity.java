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
import com.pax.us.pay.ui.core.helper.EnterFleetDataHelper;

public class EnterFleetDataActivity extends AppCompatActivity implements View.OnClickListener, EnterFleetDataHelper.IEnterFleetDataListener {

    TextView amountTv;
    Button confirmBtn;
    LinearLayout transAmountLayout;

//    String customerData,  String departmentNumber,  String userID,
//    String vehicleID,  String vehicleNumber,  String jobNumber,
//    String odometer,  String driverId,  String licenseNumber

    EditText customerDataEditText;
    EditText departmentNumberEditText;
    EditText userIDEditText;
    EditText vehicleIDEditText;
    EditText vehicleNumberEditText;
    EditText jobNumberEditText;
    EditText odometerEditText;
    EditText driverIdEditText;
    EditText licenseNumberEditText;

    private EnterFleetDataHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_fleet_data);

        amountTv = (TextView) findViewById(R.id.amount_tv);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        transAmountLayout = (LinearLayout) findViewById(R.id.trans_amount_layout);
        customerDataEditText = (EditText) findViewById(R.id.customer_data_edt);
        departmentNumberEditText = (EditText) findViewById(R.id.department_no_edt);
        userIDEditText = (EditText) findViewById(R.id.user_id_edt);
        vehicleIDEditText = (EditText) findViewById(R.id.vehicle_id_edt);
        vehicleNumberEditText = (EditText) findViewById(R.id.vehicle_no_edt);
        jobNumberEditText = (EditText) findViewById(R.id.job_no_edt);
        odometerEditText = (EditText) findViewById(R.id.odometer_edt);
        driverIdEditText = (EditText) findViewById(R.id.driver_id_edt);
        licenseNumberEditText = (EditText) findViewById(R.id.license_no_edt);

        confirmBtn.setOnClickListener(this);

        customerDataEditText.setSingleLine(true);
        customerDataEditText.requestFocus();
        customerDataEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        departmentNumberEditText.setSingleLine(true);
        userIDEditText.setSingleLine(true);
        vehicleIDEditText.setSingleLine(true);
        vehicleNumberEditText.setSingleLine(true);
        jobNumberEditText.setSingleLine(true);
        odometerEditText.setSingleLine(true);
        driverIdEditText.setSingleLine(true);
        licenseNumberEditText.setSingleLine(true);

        //mEditText.setOnKey
        customerDataEditText.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(customerDataEditText, InputMethodManager.SHOW_IMPLICIT);
        }, 200);


        helper = new EnterFleetDataHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());
        ActivityLocalManager.getInstance().addActivity(this);
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
        moveTaskToBack(false);
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        helper.stop();
        super.onDestroy();
    }

    void OnDataConfirm() {
        helper.sendNext(customerDataEditText.getText().toString(), departmentNumberEditText.getText().toString(),
                userIDEditText.getText().toString(), vehicleIDEditText.getText().toString(),
                vehicleNumberEditText.getText().toString(), jobNumberEditText.getText().toString(), odometerEditText.getText().toString(),
                driverIdEditText.getText().toString(), licenseNumberEditText.getText().toString());
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message) {

    }

    @Override
    public void onShowAmount(long amount) {

    }

    @Override
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {

    }

//    @Override
//    public void onShowFleetData() {
//
//    }
}
