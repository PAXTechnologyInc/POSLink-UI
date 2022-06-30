package com.pax.pay.ui.def;

import android.content.Intent;
import android.os.Bundle;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;

public class ConfirmSurchargelFeeActivity extends ConfirmFeeActivity {
    @Override
    protected void loadParam(Intent intent) {
        Bundle bundle = getIntent().getExtras();
        feeName = bundle.getString(EntryExtraData.PARAM_SURCHARGE_FEE_NAME, feeName);
        totalAmount = bundle.getLong(EntryExtraData.PARAM_TOTAL_AMOUNT);
        feeAmount = bundle.getLong(EntryExtraData.PARAM_SURCHARGE_FEE);
        currency = bundle.getString(EntryExtraData.PARAM_CURRENCY);
        enableBypass = bundle.getBoolean(EntryExtraData.PARAM_ENABLE_BYPASS);
    }
}
