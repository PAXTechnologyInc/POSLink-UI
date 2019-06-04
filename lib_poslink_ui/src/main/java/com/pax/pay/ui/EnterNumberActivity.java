package com.pax.pay.ui;

import com.pax.pay.ui.constant.EUIMessageKey;
import com.pax.pay.ui.utils.StringUtils;

public class EnterNumberActivity extends EnterDataLine1Activity<Integer> {
    @Override
    protected void loadOtherParam(String label) {
        navTitle = "";
        int minLen = getIntent().getIntExtra(EUIMessageKey.IntentKey.MINLENGTH, 0);
        int maxLen = getIntent().getIntExtra(EUIMessageKey.IntentKey.MAXLENGTH, 255);
        EditTextDataLimit limit = new EditTextDataLimit(label == null ? "" : label,
                "", minLen, maxLen, EInputType.NUM, false);
        if (limit == null)
            limit = EditTextDataLimit.getDef();
        setLimit(limit);
    }

    @Override
    protected Integer convert(String content) {
        return StringUtils.parseInt(content);
    }
}
