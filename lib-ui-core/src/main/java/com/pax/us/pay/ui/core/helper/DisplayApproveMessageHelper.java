package com.pax.us.pay.ui.core.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.ICardTypeListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;

public class DisplayApproveMessageHelper extends BaseActionHelper {
    public DisplayApproveMessageHelper(@Nullable IDisplayApproveMessageListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext(boolean flag) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(EntryRequest.PARAM_CONFIRMED, flag);
        super.sendNext(bundle);
    }

    @Override
    protected void showUI(@Nullable IUIListener uiListener, @NonNull Bundle bundle) {
        super.showUI(uiListener, bundle);
        if (uiListener instanceof IDisplayApproveMessageListener && bundle.containsKey(EntryExtraData.PARAM_CARD_TYPE)) {
            String cardType = bundle.getString(EntryExtraData.PARAM_CARD_TYPE);
            String soundUri = null;
            if (bundle.containsKey(EntryExtraData.PARAM_SOUND_URI))
                soundUri = bundle.getString(EntryExtraData.PARAM_SOUND_URI);
            if (!TextUtils.isEmpty(cardType)) {
                if (!TextUtils.isEmpty(soundUri))
                    ((ICardTypeListener) uiListener).onShowCardType(cardType, soundUri);
                else
                    ((ICardTypeListener) uiListener).onShowCardType(cardType);

            }
        }
    }

    public interface IDisplayApproveMessageListener extends IMessageListener, ICardTypeListener{
    }
}
