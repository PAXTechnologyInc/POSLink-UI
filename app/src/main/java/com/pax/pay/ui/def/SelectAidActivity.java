package com.pax.pay.ui.def;


/**
 * Created by Charles.S on 2017/5/5.
 */
public class SelectAidActivity extends SelectDialogActivity {
    @Override
    protected String loadOtherParam() {
        return getString(R.string.select_emv_aid);
    }
}

