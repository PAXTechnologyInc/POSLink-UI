package com.pax.us.pay.ui.core.helper;

import androidx.annotation.Nullable;

import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;

/**
 * @author Jolie Yang 03/19/2021
 */

public class ShowOnlyHelper extends BaseActionHelper {
    public ShowOnlyHelper(@Nullable IShowOnlyListener uiListener, @Nullable IRespStatus respStatus) {
        super(uiListener, respStatus);
    }

    public void sendNext() {
        super.sendNext();
    }

    public interface IShowOnlyListener extends IMessageListener {
    }

}
