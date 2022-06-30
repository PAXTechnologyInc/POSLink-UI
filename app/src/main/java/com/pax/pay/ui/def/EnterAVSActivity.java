package com.pax.pay.ui.def;

import androidx.annotation.Nullable;

import android.text.TextUtils;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterAVSHelper;

public class EnterAVSActivity extends EnterDataLine2Activity implements EnterAVSHelper.IEnterAvsListener {

    private EnterAVSHelper helper;

    @Override
    protected void loadOtherParam() {
        EditTextDataLimit address;  //maxLen default 20
        EditTextDataLimit zipCode;  //maxLen default 9

        String addressLengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_ADDRESS_PATTERN);
        if (!TextUtils.isEmpty(addressLengthRange)) {
            address = new EditTextDataLimit(getString(R.string.pls_input_address),
                    "", addressLengthRange, EInputType.ALLTEXT, false);
        } else {
            address = new EditTextDataLimit(getString(R.string.pls_input_address),
                    "", 0, 20, EInputType.ALLTEXT, false);
        }

        String zipCodeLengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_ZIP_CODE_PATTERN);
        //Fixed ANBP-1022, zip code alphpa-numeric issue
        String eInputType = getIntent().getExtras().getString(EntryExtraData.PARAM_EINPUT_TYPE);
        EInputType inputType = EInputType.NUM;
        if (eInputType != null && eInputType.equals("ALLTEXT"))
            inputType = EInputType.ALLTEXT;
        if (!TextUtils.isEmpty(zipCodeLengthRange)) {
            zipCode = new EditTextDataLimit(getString(R.string.pls_input_zip_code),
                    "", zipCodeLengthRange, inputType, false);
        } else {
            zipCode = new EditTextDataLimit(getString(R.string.pls_input_zip_code),
                    "", 0, 9,inputType, false);
        }

        setLimit(address, zipCode);
        helper = new EnterAVSHelper(this, new RespStatusImpl(this));

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


    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    @Override
    protected void sendAbort() {
        helper.sendAbort();
    }

    @Override
    protected void sendNext(String line1, String line2) {
        helper.sendNext(line1, line2);
    }
}
