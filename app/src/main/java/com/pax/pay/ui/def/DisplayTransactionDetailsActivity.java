package com.pax.pay.ui.def;


import com.pax.pay.ui.def.utils.DisplayTransInfoUtils;

/**
 * Created by Charles.S on 2017/5/5.
 */

public class DisplayTransactionDetailsActivity extends DisplayDetailsActivity {

    @Override
    protected DisplayTransInfoUtils.ShowType loadOtherParam() {
        return DisplayTransInfoUtils.ShowType.TRANS;
    }

}
