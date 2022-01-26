package com.pax.pay.ui.def;


/**
 * Created by Jolie yang on 2021/07/12.
 */

public class SelectOrigCurrencyActivity extends SelectDialogActivity {
    @Override
    protected String loadOtherParam() {
        return getResources().getString(R.string.select_orig_currency);
    }

}
