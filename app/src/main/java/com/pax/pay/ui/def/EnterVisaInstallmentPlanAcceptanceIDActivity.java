package com.pax.pay.ui.def;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterVisaInstallmentTransactionPlanAcceptanceIDHelper;

public class EnterVisaInstallmentPlanAcceptanceIDActivity extends EnterDataLine1Activity<String> implements EnterVisaInstallmentTransactionPlanAcceptanceIDHelper.IEnterVisaInstallmentTransactionPlanAcceptanceIDListener{
    private EnterVisaInstallmentTransactionPlanAcceptanceIDHelper helper;

    @Override
    protected void loadOtherParam(String message) {
        EditTextDataLimit limit;
        EInputType inputType = EInputType.ALLTEXT;
        String lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_VALUE_PATTERN);
        limit = new EditTextDataLimit(getResources().getString(R.string.please_input_visa_installment_plan_acceptance_id), "", lengthRange, inputType, false);
        setLimit(limit);
        helper = new EnterVisaInstallmentTransactionPlanAcceptanceIDHelper(this, new RespStatusImpl(this));
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
    protected String convert(String content) {
        return content;
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
