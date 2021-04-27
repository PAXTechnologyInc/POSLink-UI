package com.pax.pay.ui.def;

import android.content.Intent;
import android.os.Bundle;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;

public class ConfirmAdditionalFeeActivity extends ConfirmFeeActivity {
    @Override
    protected void loadParam(Intent intent) {
        Bundle bundle = getIntent().getExtras();
        feeName = bundle.getString(EntryExtraData.PARAM_ADDITIONAL_FEE_NAME, feeName);
        totalAmount = bundle.getLong(EntryExtraData.PARAM_TOTAL_AMOUNT);
        feeAmount = bundle.getLong(EntryExtraData.PARAM_ADDITIONAL_FEE);
    }
}
