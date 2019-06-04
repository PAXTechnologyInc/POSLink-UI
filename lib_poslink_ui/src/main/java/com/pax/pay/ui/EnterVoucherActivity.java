package com.pax.pay.ui;


import com.pax.pay.ui.constant.EUIMessageKey;

import java.util.HashMap;
import java.util.Map;

public class EnterVoucherActivity extends EnterDataLine2Activity<Map<String, String>> {

    @Override
    protected void loadOtherParam(String label) {
        int minLen = getIntent().getIntExtra(EUIMessageKey.IntentKey.MINLENGTH, 0);
        int maxLen = getIntent().getIntExtra(EUIMessageKey.IntentKey.MAXLENGTH, 255);  //default 15
        navTitle = "";

        EditTextDataLimit voucherLimit = new EditTextDataLimit(getResources().getString(R.string.please_enter_voucher_number),
                "", minLen, maxLen, EInputType.ALLTEXT, false);
        if (voucherLimit == null)
            voucherLimit = EditTextDataLimit.getDef();
        EditTextDataLimit authCodeLimit = new EditTextDataLimit(getResources().getString(R.string.please_enter_auth_code), "", minLen, maxLen, EInputType.ALLTEXT, false);
        setLimit(voucherLimit, authCodeLimit);
    }

    @Override
    protected Map<String, String> genResult() {
        Map<String, String> voucherMap = new HashMap<>();
        voucherMap.put(EUIMessageKey.INTENT_KEY_VOUCHER_NO, editTv1.getText().toString());
        voucherMap.put(EUIMessageKey.INTENT_KEY_AUTH_CODE, editTv2.getText().toString());
        return voucherMap;
    }

}
