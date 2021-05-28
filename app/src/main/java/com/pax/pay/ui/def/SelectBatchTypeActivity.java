package com.pax.pay.ui.def;


/**
 * Created by Charles.S on 2017/5/5.
 */

public class SelectBatchTypeActivity extends SelectDialogActivity {
    @Override
    protected String loadOtherParam() {
        checkedItem = 0;
        return getResources().getString(R.string.select_batch_menu);
    }

}
