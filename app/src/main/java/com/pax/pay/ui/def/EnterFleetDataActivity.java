package com.pax.pay.ui.def;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.fragment.FleetDataFragment;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterFleetDataHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnterFleetDataActivity extends BaseAppActivity implements EnterFleetDataHelper.IEnterFleetDataListener, FleetDataFragment.OnFragmentInteractionListener {
    TextView amountTv;
    LinearLayout transAmountLayout; //Just used to keep same with Input Amount Layout
    private EnterFleetDataHelper helper;
    private long totalAmount;
    private FragmentManager fragmentManager; // 管理
    private FragmentTransaction fragmentTransaction; // 事务
    private final Map<Integer, String> fleetDataMap = new HashMap<>();
    private int currState = -1;
    private FleetDataFragment myFragment;
    private boolean hasPhyKeyboard = false;

    private static final int STATE_ENTER_DRIVER = 0;
    private static final int STATE_ENTER_ODOMETER = 1;
    private static final int STATE_ENTER_VEHICLE_NO = 2;
    private static final int STATE_ENTER_LICENSE_NO = 3;
    private static final int STATE_ENTER_JOB_ID = 4;
    private static final int STATE_ENTER_DEPARTMENT_NO = 5;
    private static final int STATE_ENTER_CUSTOMER_DATA = 6;
    private static final int STATE_ENTER_USER_ID = 7;
    private static final int STATE_ENTER_VEHICLE_ID = 8;

    private final Map<Integer, List<String>> paramList = new LinkedHashMap<>();


    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_fleet_data_default;
    }

    @Override
    protected void initViews() {
        if (paramList != null) {
            for (Map.Entry<Integer, List<String>> entry : paramList.entrySet()) {
                currState = entry.getKey();
                List<String> param = entry.getValue();
                fragmentTransaction = fragmentManager.beginTransaction();
                if(hasPhyKeyboard)
                    myFragment = FleetDataFragment.newInstance(param.get(0), param.get(1), param.get(2), "hasPhyKeyboard");
                else
                    myFragment = FleetDataFragment.newInstance(param.get(0), param.get(1), param.get(2), "");
                // 第一个参数是要放到哪个地方的id，第二个为要放入的fragment
                fragmentTransaction.add(R.id.fleed_data, myFragment);
                fragmentTransaction.commit();
                break;
            }
        }
    }

    @Override
    protected void loadParam() {
        amountTv = findViewById(R.id.amount_tv);
        transAmountLayout = findViewById(R.id.trans_amount_layout);
        fragmentManager = getSupportFragmentManager();
        try {
            String lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_FLEET_DRIVER_ID_PATTERN);
            if (!TextUtils.isEmpty(lengthRange)) {
                //limitList.add(new EditTextDataLimit(getResources().getString(R.string.prompt_input_fleet_driver_id), "", lengthRange, EInputType.NUM, false));
                List<String> driverId = new ArrayList<>();
                driverId.add(getResources().getString(R.string.prompt_input_fleet_driver_id));
                driverId.add(lengthRange);
                driverId.add(EInputType.NUM.name());
                paramList.put(STATE_ENTER_DRIVER, driverId);
            }
            lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_FLEET_ODOMETER_PATTERN);
            if (!TextUtils.isEmpty(lengthRange)) {
                List<String> odo = new ArrayList<>();
                odo.add(getResources().getString(R.string.prompt_input_fleet_odometer));
                odo.add(lengthRange);
                odo.add(EInputType.NUM.name());
                paramList.put(STATE_ENTER_ODOMETER, odo);
                //limitList.add(new EditTextDataLimit(getResources().getString(R.string.prompt_input_fleet_odometer), "", lengthRange, EInputType.NUM, false));
            }

            lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_FLEET_VEHICLE_NUMBER_PATTERN);
            if (!TextUtils.isEmpty(lengthRange)) {
                List<String> vehicleNo = new ArrayList<>();
                vehicleNo.add(getResources().getString(R.string.prompt_input_fleet_vehicle_no));
                vehicleNo.add(lengthRange);
                vehicleNo.add(EInputType.NUM.name());
                paramList.put(STATE_ENTER_VEHICLE_NO, vehicleNo);
                //limitList.add(new EditTextDataLimit(getResources().getString(R.string.prompt_input_fleet_vehicle_no), "", lengthRange, EInputType.NUM, false));
            }

            lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_FLEET_LICENSE_NUMBER_PATTERN);
            if (!TextUtils.isEmpty(lengthRange)) {
                List<String> license = new ArrayList<>();
                license.add(getResources().getString(R.string.prompt_input_fleet_license_no));
                license.add(lengthRange);
                license.add(EInputType.NUM.name());
                paramList.put(STATE_ENTER_LICENSE_NO, license);
                //limitList.add(new EditTextDataLimit(getResources().getString(R.string.prompt_input_fleet_license_no), "", lengthRange, EInputType.NUM, false));
            }

            lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_FLEET_JOB_NUMBER_PATTERN);
            if (!TextUtils.isEmpty(lengthRange)) {
                List<String> job = new ArrayList<>();
                job.add(getResources().getString(R.string.prompt_input_fleet_job_no));
                job.add(lengthRange);
                job.add(EInputType.NUM.name());
                paramList.put(STATE_ENTER_JOB_ID, job);
                //limitList.add(new EditTextDataLimit(getResources().getString(R.string.prompt_input_fleet_job_no), "", lengthRange, EInputType.NUM, false));
            }
            lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_FLEET_DEPARTMENT_NUMBER_PATTERN);
            if (!TextUtils.isEmpty(lengthRange)) {
                List<String> department = new ArrayList<>();
                department.add(getResources().getString(R.string.prompt_input_fleet_department_no));
                department.add(lengthRange);
                department.add(EInputType.ALLTEXT.name());
                paramList.put(STATE_ENTER_DEPARTMENT_NO, department);
                //limitList.add(new EditTextDataLimit(getResources().getString(R.string.prompt_input_fleet_department_no), "", lengthRange, EInputType.ALLTEXT, false));
            }

            lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_FLEET_CUSTOMER_DATA_PATTERN);
            if (!TextUtils.isEmpty(lengthRange)) {
                List<String> customer = new ArrayList<>();
                customer.add(getResources().getString(R.string.prompt_input_fleet_customer_data));
                customer.add(lengthRange);
                customer.add(EInputType.ALLTEXT.name());
                paramList.put(STATE_ENTER_CUSTOMER_DATA, customer);
                //limitList.add(new EditTextDataLimit(getResources().getString(R.string.prompt_input_fleet_customer_data), "", lengthRange, EInputType.ALLTEXT, false));
            }

            lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_FLEET_USER_ID_PATTERN);
            if (!TextUtils.isEmpty(lengthRange)) {
                List<String> user = new ArrayList<>();
                user.add(getResources().getString(R.string.prompt_input_fleet_user_id));
                user.add(lengthRange);
                user.add(EInputType.ALLTEXT.name());
                paramList.put(STATE_ENTER_USER_ID, user);
                //limitList.add(new EditTextDataLimit(getResources().getString(R.string.prompt_input_fleet_user_id), "", lengthRange, EInputType.ALLTEXT, false));
            }

            lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_FLEET_VEHICLE_ID_PATTERN);
            if (!TextUtils.isEmpty(lengthRange)) {
                List<String> vehicleId = new ArrayList<>();
                vehicleId.add(getResources().getString(R.string.prompt_input_fleet_vehicle_id));
                vehicleId.add(lengthRange);
                vehicleId.add(EInputType.NUM.name());
                paramList.put(STATE_ENTER_VEHICLE_ID, vehicleId);
                //limitList.add(new EditTextDataLimit(getResources().getString(R.string.prompt_input_fleet_vehicle_id), "", lengthRange, EInputType.NUM, false));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        helper = new EnterFleetDataHelper(this, new RespStatusImpl(this));
    }

    @Override
    public void onShowAmount(long amount) {
        totalAmount = amount;
        if (amount == 0) {
            amountTv.setText(CurrencyConverter.convert(totalAmount, ""));
        } else {
            transAmountLayout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {
        CurrencyConverter.setDefCurrency(currency);
    }

    @Override
    public void onHasPhyKeyboard(boolean notShowVirtualKeyBoard) {
        this.hasPhyKeyboard = notShowVirtualKeyBoard;
    }

//    @Override
//    protected boolean onKeyEnterDown() {
//        onDataConfirmed();
//        return true;
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        myFragment.onKeyEnterDown(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected boolean onKeyBackDown() {
        helper.sendAbort();
        return true;
    }

    @Override
    protected void setListeners() {

    }

//    @Override
//    public void onClickProtected(View view) {
//        if (view.getId() == R.id.confirm_btn) {
//            onDataConfirmed();
//        }
//    }

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
    public void onDataAbort() {
        helper.sendAbort();
    }

    @Override
    public void onConfirmValue(String value) {
        List<String> param = new ArrayList<>();
        boolean findFlag = false;
        fleetDataMap.put(currState, value);
        for (Map.Entry<Integer, List<String>> entry : paramList.entrySet()) {
            if (currState >= entry.getKey())
                continue;
            currState = entry.getKey();
            param = entry.getValue();
            findFlag = true;
            break;
        }
        if (findFlag) {
            fragmentTransaction = fragmentManager.beginTransaction();
            if(hasPhyKeyboard)
                myFragment = FleetDataFragment.newInstance(param.get(0), param.get(1), param.get(2), "hasPhyKeyboard");
            else
                myFragment = FleetDataFragment.newInstance(param.get(0), param.get(1), param.get(2), "");
            // 第一个参数是要放到哪个地方的id，第二个为要放入的fragment
            fragmentTransaction.replace(R.id.fleed_data, myFragment);
            fragmentTransaction.commit();
        } else {
            helper.sendNext(fleetDataMap.containsKey(STATE_ENTER_CUSTOMER_DATA) ? fleetDataMap.get(STATE_ENTER_CUSTOMER_DATA) : null,
                    fleetDataMap.containsKey(STATE_ENTER_DEPARTMENT_NO) ? fleetDataMap.get(STATE_ENTER_DEPARTMENT_NO) : null,
                    fleetDataMap.containsKey(STATE_ENTER_USER_ID) ? fleetDataMap.get(STATE_ENTER_USER_ID) : null,
                    fleetDataMap.containsKey(STATE_ENTER_VEHICLE_ID) ? fleetDataMap.get(STATE_ENTER_VEHICLE_ID) : null,
                    fleetDataMap.containsKey(STATE_ENTER_VEHICLE_NO) ? fleetDataMap.get(STATE_ENTER_VEHICLE_NO) : null,
                    fleetDataMap.containsKey(STATE_ENTER_JOB_ID) ? fleetDataMap.get(STATE_ENTER_JOB_ID) : null,
                    fleetDataMap.containsKey(STATE_ENTER_ODOMETER) ? fleetDataMap.get(STATE_ENTER_ODOMETER) : null,
                    fleetDataMap.containsKey(STATE_ENTER_DRIVER) ? fleetDataMap.get(STATE_ENTER_DRIVER) : null,
                    fleetDataMap.containsKey(STATE_ENTER_LICENSE_NO) ? fleetDataMap.get(STATE_ENTER_LICENSE_NO) : null);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasPhyKeyboard ){
            myFragment.onWindowFocusChanged(hasPhyKeyboard);
        }
    }

}
