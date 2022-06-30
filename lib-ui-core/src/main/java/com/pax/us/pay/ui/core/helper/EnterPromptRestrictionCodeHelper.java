package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

public class EnterPromptRestrictionCodeHelper extends BaseActionHelper {

    public EnterPromptRestrictionCodeHelper(@Nullable IEnterPromptRestrictionCodeListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(String promptRestrictionCode) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_PROMPT_RESTRICTION_CODE, promptRestrictionCode);
        super.sendNext(bundle);
    }

    public interface IEnterPromptRestrictionCodeListener extends IMessageListener, IHasPhyKeyboardListener {
    }

}
