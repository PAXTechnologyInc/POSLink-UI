package com.pax.pay.ui;


import com.pax.pay.ui.constant.EUIMessageKey;

import java.util.HashMap;
import java.util.Map;

public class EnterAVSActivity extends EnterDataLine2Activity<Map<String, String>> {

    @Override
    protected void loadOtherParam(String label) {
        int minLen = getIntent().getIntExtra(EUIMessageKey.IntentKey.MINLENGTH, 0);
        int maxLen = getIntent().getIntExtra(EUIMessageKey.IntentKey.MAXLENGTH, 255);  //default 15
        navTitle = "";

        EditTextDataLimit address = new EditTextDataLimit(getResources().getString(R.string.pls_input_address),
                "", minLen, maxLen, EInputType.ALLTEXT, false);  //maxLen default 20
        EditTextDataLimit zipCode = new EditTextDataLimit(getResources().getString(R.string.pls_input_zip_code),
                "", minLen, maxLen, EInputType.NUM, false);  //maxLen default 9
        setLimit(address, zipCode);
    }

    @Override
    protected Map<String, String> genResult() {
        Map<String, String> voucherMap = new HashMap<>();
        voucherMap.put(EUIMessageKey.INTENT_KEY_ADDRESS, editTv1.getText().toString());
        voucherMap.put(EUIMessageKey.INTENT_KEY_ZIP_CODE, editTv2.getText().toString());
        return voucherMap;
    }

}
