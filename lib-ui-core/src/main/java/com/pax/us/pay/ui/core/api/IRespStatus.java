package com.pax.us.pay.ui.core.api;

import android.support.annotation.Nullable;

public interface IRespStatus {
    void onAccepted();

    void onDeclined(long code, @Nullable String message);
}
