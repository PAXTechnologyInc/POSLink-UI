package com.pax.pay.ui;

import com.pax.pay.ui.constant.ActivityIntentAction;
import com.pax.pay.ui.constant.EUIMessageKey;

public class EnterTextActivity extends EnterDataLine1Activity<String> {

    EInputType inputType;

    @Override
    protected void loadOtherParam(String label) {
        navTitle = "";
        int minLen = getIntent().getIntExtra(EUIMessageKey.IntentKey.MINLENGTH, 0);
        int maxLen = getIntent().getIntExtra(EUIMessageKey.IntentKey.MAXLENGTH, 255);
        String action = getCurrentAction();
        switch (action) {
            case ActivityIntentAction.ACTION_ENTER_ZIPCODE:
                inputType = EInputType.NUM;
                break;
            case ActivityIntentAction.ACTION_ENTER_EXPIRE_DATE:
                inputType = EInputType.EXPIRY_DATE;
                break;
            case ActivityIntentAction.ACTION_ENTER_AUTH:
            case ActivityIntentAction.ACTION_ENTER_ADDRESS:
                inputType = EInputType.ALLTEXT;
                break;
        }
        EditTextDataLimit limit = new EditTextDataLimit(label == null ? "" : label,
                "", minLen, maxLen, inputType, false);
        if (limit == null)
            limit = EditTextDataLimit.getDef();
        setLimit(limit);
    }

    @Override
    protected String convert(String content) {
        return content;
    }
}
