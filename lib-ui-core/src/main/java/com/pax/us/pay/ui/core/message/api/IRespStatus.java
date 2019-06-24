package com.pax.us.pay.ui.core.message.api;

import com.pax.us.pay.ui.core.message.RespMessage;

public interface IRespStatus {
    void onAccepted();

    void onDeclined(RespMessage respMessage);
}
