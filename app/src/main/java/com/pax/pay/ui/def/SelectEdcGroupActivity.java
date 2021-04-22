package com.pax.pay.ui.def;


/**
 * Created by Charles.S on 2017/5/5.
 */

public class SelectEdcGroupActivity extends SelectDialogActivity {
    @Override
    protected String loadOtherParam() {
        return getResources().getString(R.string.select_batch_inquiry);
    }

}
