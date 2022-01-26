package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IAmountListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnterFleetDataHelper extends BaseActionHelper {
    public EnterFleetDataHelper(@Nullable IEnterFleetDataListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String customerData, String departmentNumber, String userID,
                         String vehicleID, String vehicleNumber, String jobNumber,
                         String odometer, String driverId, String licenseNumber) {
        Map<String, String> amtMap = new LinkedHashMap<>();
        if (!TextUtils.isEmpty(customerData))
            amtMap.put(EntryRequest.PARAM_FLEET_CUSTOMER_DATA, customerData);
        if (!TextUtils.isEmpty(departmentNumber))
            amtMap.put(EntryRequest.PARAM_FLEET_DEPARTMENT_NUMBER, departmentNumber);
        if (!TextUtils.isEmpty(userID))
            amtMap.put(EntryRequest.PARAM_FLEET_USER_ID, userID);
        if (!TextUtils.isEmpty(vehicleID))
            amtMap.put(EntryRequest.PARAM_FLEET_VEHICLE_ID, vehicleID);
        if (!TextUtils.isEmpty(vehicleNumber))
            amtMap.put(EntryRequest.PARAM_FLEET_VEHICLE_NUMBER, vehicleNumber);
        if (!TextUtils.isEmpty(jobNumber))
            amtMap.put(EntryRequest.PARAM_FLEET_JOB_NUMBER, jobNumber);
        if (!TextUtils.isEmpty(odometer))
            amtMap.put(EntryRequest.PARAM_FLEET_ODOMETER, odometer);
        if (!TextUtils.isEmpty(driverId))
            amtMap.put(EntryRequest.PARAM_FLEET_DRIVER_ID, driverId);
        if (!TextUtils.isEmpty(licenseNumber))
            amtMap.put(EntryRequest.PARAM_FLEET_LICENSE_NUMBER, licenseNumber);

        Bundle bundle = new Bundle();
        for (Map.Entry<String, String> amtType : amtMap.entrySet()) {
            bundle.putString(amtType.getKey(), amtType.getValue());
        }
        super.sendNext(bundle);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
//        boolean customerDataVisible, departmentNumberVisible, userIDVisible;
//        boolean vehicleIDVisible, vehicleNumberVisible, jobNumberVisible;
//        boolean odometerVisible, driverIdVisible, licenseNumberVisible;

        if (uiListener instanceof ICurrencyListener) {
            String currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "USD");
            ((ICurrencyListener) uiListener).onShowCurrency(currency, false);
        }

        if (uiListener instanceof IAmountListener && bundle.containsKey(EntryExtraData.PARAM_TOTAL_AMOUNT)) {
            ((IAmountListener) uiListener).onShowAmount(bundle.getLong(EntryExtraData.PARAM_TOTAL_AMOUNT));
        }
//       TO do need to confirm show all fleet data, don't need show part item.
//        if (uiListener instanceof IEnterFleetDataListener){
//            ((IEnterFleetDataListener) uiListener).onShowFleetData();
//        }
//        fleetDataOption = null;

//        if (uiListener instanceof IEnterFleetDataListener) {
//            //TO DO ?????????????????
//            //String[] options = bundle.getStringArray(EntryExtraData.PARAM_FSA_AMOUNT_OPTIONS);
//            String[] options = new String[]{EntryRequest.PARAM_FLEET_CUSTOMER_DATA, EntryRequest.PARAM_FLEET_DEPARTMENT_NUMBER,
//                    EntryRequest.PARAM_FLEET_USER_ID, EntryRequest.PARAM_FLEET_VEHICLE_ID, EntryRequest.PARAM_FLEET_VEHICLE_NUMBER,
//                    EntryRequest.PARAM_FLEET_JOB_NUMBER, EntryRequest.PARAM_FLEET_ODOMETER,EntryRequest.PARAM_FLEET_DRIVER_ID,
//                    EntryRequest.PARAM_FLEET_LICENSE_NUMBER};
//
//            customerDataVisible = false;
//            departmentNumberVisible = false;
//            userIDVisible = false;
//            vehicleIDVisible = false;
//            vehicleNumberVisible = false;
//            jobNumberVisible = false;
//            odometerVisible = false;
//            driverIdVisible = false;
//            licenseNumberVisible = false;
//            if (options != null && options.length > 0) {
//                fleetDataOption = Arrays.asList(options);
//
//                for (String amtType : fleetDataOption) {
//                    switch (amtType) {
//                        case EntryRequest.PARAM_FLEET_CUSTOMER_DATA:
//                            customerDataVisible = true;
//                            break;
//                        case EntryRequest.PARAM_FLEET_DEPARTMENT_NUMBER:
//                            departmentNumberVisible = true;
//                            break;
//                        case EntryRequest.PARAM_FLEET_USER_ID:
//                            userIDVisible = true;
//                            break;
//                        case EntryRequest.PARAM_FLEET_VEHICLE_ID:
//                            vehicleIDVisible = true;
//                            break;
//                        case EntryRequest.PARAM_FLEET_VEHICLE_NUMBER:
//                            vehicleNumberVisible = true;
//                            break;
//                        case EntryRequest.PARAM_FLEET_JOB_NUMBER:
//                            jobNumberVisible = true;
//                            break;
//                        case EntryRequest.PARAM_FLEET_ODOMETER:
//                            odometerVisible = true;
//                            break;
//                        case EntryRequest.PARAM_FLEET_DRIVER_ID:
//                            driverIdVisible = true;
//                            break;
//                        case EntryRequest.PARAM_FLEET_LICENSE_NUMBER:
//                            licenseNumberVisible = true;
//                            break;
//                    }
//                } //for
//            }// options not null
//            ((IFleetDataOptionListener) uiListener).onShowFleetDataOption(customerDataVisible, departmentNumberVisible, userIDVisible,
//                    vehicleIDVisible, vehicleNumberVisible, jobNumberVisible, odometerVisible, driverIdVisible, licenseNumberVisible);
//        }
    }

    public interface IEnterFleetDataListener extends IMessageListener, ICurrencyListener, IAmountListener, IHasPhyKeyboardListener {
    }

}
