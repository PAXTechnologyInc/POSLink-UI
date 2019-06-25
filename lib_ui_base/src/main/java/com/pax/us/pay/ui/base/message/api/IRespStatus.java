package com.pax.us.pay.ui.base.message.api;

import com.pax.us.pay.ui.base.message.RespMessage;

public interface IRespStatus {
    void respAccept();

    void respDecline(RespMessage respMessage);
}
