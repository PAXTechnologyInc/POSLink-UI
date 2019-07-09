package com.pax.us.pay.ui.core.api;

public interface IRespStatus {
    void onAccepted();

    void onDeclined(long code, String message);
}
