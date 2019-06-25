package com.pax.us.pay.ui.base.message.helper;

import android.os.Bundle;

import com.pax.us.pay.ui.base.message.RespMessage;
import com.pax.us.pay.ui.base.message.api.IRespStatus;
import com.pax.us.pay.ui.constant.parameter.Request;

public class FSAAmountHelper extends BaseHelper {

    public void sendObjNext(long healthAmt, long clinicAmt, long prescriptionAmt, long dentalAmt
            , long visionAmt, long transitAmt, long totalAmount, IRespStatus respStatus) {
        if (healthAmt + transitAmt > totalAmount) {
            RespMessage respMessage = new RespMessage();
            respMessage.setResultMsg("FSA AMOUNT EXCEED LIMIT");
            respMessage.setResultCode(101901);
            respStatus.respDecline(respMessage);
            return;
        } else if (clinicAmt + prescriptionAmt + dentalAmt + visionAmt > healthAmt) {
            RespMessage respMessage = new RespMessage();
            respMessage.setResultMsg("SUB HEALTHCARE AMOUNT EXCEED LIMIT");
            respMessage.setResultCode(101902);
            respStatus.respDecline(respMessage);
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putLong(Request.Text.PARA_HEALTH_CARE_AMOUNT, healthAmt);
        bundle.putLong(Request.Text.PARA_CLINIC_AMOUNT, clinicAmt);
        bundle.putLong(Request.Text.PARA_PRESCRIPTION_AMOUNT, prescriptionAmt);
        bundle.putLong(Request.Text.PARA_DENTAL_AMOUNT, dentalAmt);
        bundle.putLong(Request.Text.PARA_VISION_AMOUNT, visionAmt);
        bundle.putLong(Request.Text.PARA_TRANSIT_AMOUNT, transitAmt);
        super.sendObjNext(bundle);
    }
}
