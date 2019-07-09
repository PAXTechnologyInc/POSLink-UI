package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IAmountListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IFsaAmountOptionListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnterFSAAmountHelper extends BaseActionHelper {
    private List<String> amountOption = null;


    public EnterFSAAmountHelper(@Nullable IEnterFSAAmountListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(long healthAmt, long clinicAmt, long prescriptionAmt, long dentalAmt
            , long visionAmt, long copayAmt, long transitAmt, long totalAmount) {

        if (amountOption == null) {
            decline(102301, "FSA AMOUNT OPTION IS EMPTY");
            return;
        }

        Map<String, Long> amtMap = new HashMap<>();
        amtMap.put(EntryRequest.PARAM_HEALTH_CARE_AMOUNT, healthAmt);
        amtMap.put(EntryRequest.PARAM_CLINIC_AMOUNT, clinicAmt);
        amtMap.put(EntryRequest.PARAM_PRESCRIPTION_AMOUNT, prescriptionAmt);
        amtMap.put(EntryRequest.PARAM_DENTAL_AMOUNT, dentalAmt);
        amtMap.put(EntryRequest.PARAM_VISION_AMOUNT, visionAmt);
        amtMap.put(EntryRequest.PARAM_COPAY_AMOUNT, copayAmt);
        amtMap.put(EntryRequest.PARAM_TRANSIT_AMOUNT, transitAmt);

        long tmpTotalAmount = 0;
        long tmpHealthAmount = 0;
        for (Map.Entry amtType : amtMap.entrySet()) {
            if ((amountOption.contains(amtType.getKey()) && amtType.getKey().equals(EntryRequest.PARAM_HEALTH_CARE_AMOUNT)) ||
                    (amountOption.contains(amtType.getKey()) && amtType.getKey().equals(EntryRequest.PARAM_TRANSIT_AMOUNT)))
                tmpTotalAmount = tmpTotalAmount + (Long) amtType.getValue();
            else
                tmpHealthAmount = tmpHealthAmount + (Long) amtType.getValue();
        }

        if (tmpTotalAmount > totalAmount) {
            decline(101901, "FSA AMOUNT EXCEED LIMIT");
            return;
        }
        if (tmpHealthAmount > healthAmt) {
            decline(101902, "SUB HEALTH CARE AMOUNT EXCEED LIMIT");
            return;
        }

        Bundle bundle = new Bundle();
        for (String amtType : amountOption) {
            bundle.putLong(amtType, amtMap.get(amtType));
        }
        super.sendNext(bundle);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if (uiListener instanceof ICurrencyListener) {
            String currency = bundle.getString(EntryExtraData.PARAM_CURRENCY, "USD");
            ((ICurrencyListener) uiListener).onShowCurrency(currency);
        }

        if (uiListener instanceof IAmountListener && bundle.containsKey(EntryExtraData.PARAM_DISP_AMOUNT)) {
            ((IAmountListener) uiListener).onShowAmount(bundle.getLong(EntryExtraData.PARAM_DISP_AMOUNT));
        }

        amountOption = null;

        if (uiListener instanceof IFsaAmountOptionListener && bundle.containsKey(EntryExtraData.PARAM_FSA_AMOUNT_OPTIONS)) {
            String[] options = bundle.getStringArray(EntryExtraData.PARAM_FSA_AMOUNT_OPTIONS);

            if (options != null && options.length > 0) {
                amountOption = Arrays.asList(options);
                //((IFsaAmountOptionListener) uiListener).onShowFsaAmountOption(options);
                for (String amtType : amountOption) {
                    //map.get(amtType);
                    switch (amtType) {
                        case EntryRequest.PARAM_HEALTH_CARE_AMOUNT:
                            ((IFsaAmountOptionListener) uiListener).onShowHealthCareAmount();
                            break;
                        case EntryRequest.PARAM_CLINIC_AMOUNT:
                            ((IFsaAmountOptionListener) uiListener).onShowClinicAmount();
                            break;
                        case EntryRequest.PARAM_PRESCRIPTION_AMOUNT:
                            ((IFsaAmountOptionListener) uiListener).onShowPrescriptionAmt();
                            break;
                        case EntryRequest.PARAM_DENTAL_AMOUNT:
                            ((IFsaAmountOptionListener) uiListener).onShowDentalAmount();
                            break;
                        case EntryRequest.PARAM_VISION_AMOUNT:
                            ((IFsaAmountOptionListener) uiListener).onShowVisionAmount();
                            break;
                        case EntryRequest.PARAM_COPAY_AMOUNT:
                            ((IFsaAmountOptionListener) uiListener).onShowCopayAmount();
                            break;
                        case EntryRequest.PARAM_TRANSIT_AMOUNT:
                            ((IFsaAmountOptionListener) uiListener).onShowTransitAmount();
                            break;
                    }
                } //for
            }// options not null
        }
    }

    public interface IEnterFSAAmountListener extends IMessageListener, ICurrencyListener, IAmountListener, IFsaAmountOptionListener {
    }

}
