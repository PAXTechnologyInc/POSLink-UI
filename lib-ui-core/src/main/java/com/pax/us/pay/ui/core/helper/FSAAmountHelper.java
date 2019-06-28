package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;

public class FSAAmountHelper extends BaseActionHelper {

    public void sendNext(long healthAmt, long clinicAmt, long prescriptionAmt, long dentalAmt
            , long visionAmt, long transitAmt, long totalAmount) {
        if (healthAmt + transitAmt > totalAmount) {
            decline(101901, "FSA AMOUNT EXCEED LIMIT");
            return;
        } else if (clinicAmt + prescriptionAmt + dentalAmt + visionAmt > healthAmt) {
            decline(101902, "SUB HEALTH CARE AMOUNT EXCEED LIMIT");
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putLong(EntryRequest.PARAM_HEALTH_CARE_AMOUNT, healthAmt);
        bundle.putLong(EntryRequest.PARAM_CLINIC_AMOUNT, clinicAmt);
        bundle.putLong(EntryRequest.PARAM_PRESCRIPTION_AMOUNT, prescriptionAmt);
        bundle.putLong(EntryRequest.PARAM_DENTAL_AMOUNT, dentalAmt);
        bundle.putLong(EntryRequest.PARAM_VISION_AMOUNT, visionAmt);
        bundle.putLong(EntryRequest.PARAM_TRANSIT_AMOUNT, transitAmt);
        super.sendNext(bundle);
    }
}
