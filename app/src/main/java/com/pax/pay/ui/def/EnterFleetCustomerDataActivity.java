package com.pax.pay.ui.def;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterFleetCustomerDataHelper;
import com.paxus.utils.StringUtils;

public class EnterFleetCustomerDataActivity extends EnterDataLine1Activity<String> implements EnterFleetCustomerDataHelper.IEnterFleetCustomerDataListener {

    private EnterFleetCustomerDataHelper helper;


    @Override
    protected String convert(String content) {
        return content;
    }

    @Override
    protected void loadOtherParam(String message) {
        String minLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MIN_LENGTH);
        String maxLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MAX_LENGTH);
        int min = StringUtils.parseInt(minLength, 0);
        int max = StringUtils.parseInt(maxLength, 12);
        EditTextDataLimit limit = new EditTextDataLimit(getString(R.string.prompt_input_fleet_customer_data),
                "", min, max, EInputType.ALLTEXT, false);
        setLimit(limit);
        helper = new EnterFleetCustomerDataHelper(this, new RespStatusImpl(this));
    }

    @Override
    protected void sendAbort() {
        helper.sendAbort();
    }

    @Override
    protected void sendNext(String data) {
        helper.sendNext(data);
    }


    @Override
    protected void onStart() {
        helper.start(this, getIntent());
        super.onStart();
    }

    @Override
    protected void onStop() {
        helper.stop();
        super.onStop();
    }

}
