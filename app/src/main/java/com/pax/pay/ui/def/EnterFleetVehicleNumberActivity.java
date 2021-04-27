package com.pax.pay.ui.def;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterFleetVehicleNumberHelper;
import com.paxus.utils.StringUtils;

public class EnterFleetVehicleNumberActivity extends EnterDataLine1Activity<String> implements EnterFleetVehicleNumberHelper.IEnterFleetVehicleNumberListener {

    private EnterFleetVehicleNumberHelper helper;


    @Override
    protected String convert(String content) {
        return content;
    }

    @Override
    protected void loadOtherParam(String message) {
        String minLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MIN_LENGTH);
        String maxLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MAX_LENGTH);
        int min = StringUtils.parseInt(minLength, 0);
        int max = StringUtils.parseInt(maxLength, 6);
        EditTextDataLimit limit = new EditTextDataLimit(getString(R.string.prompt_input_fleet_vehicle_no),
                "", min, max, EInputType.NUM, false);
        setLimit(limit);
        helper = new EnterFleetVehicleNumberHelper(this, new RespStatusImpl(this));
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
