package com.pax.us.pay.ui.core.api;

import androidx.annotation.Nullable;

public interface IMessageListener extends IUIListener {
    void onShowMessage(@Nullable String transName, @Nullable String message, String transMode);
}
