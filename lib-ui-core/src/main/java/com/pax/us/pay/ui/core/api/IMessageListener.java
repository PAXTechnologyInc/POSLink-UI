package com.pax.us.pay.ui.core.api;

import android.support.annotation.Nullable;

public interface IMessageListener extends IUIListener {
    void onShowMessage(@Nullable String transName, @Nullable String message, String transMode);
}
