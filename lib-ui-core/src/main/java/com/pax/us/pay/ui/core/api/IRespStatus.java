package com.pax.us.pay.ui.core.api;

import com.pax.us.pay.ui.core.RespMessage;

public interface IRespStatus {
    void onAccepted();

    void onDeclined(RespMessage respMessage);
}
