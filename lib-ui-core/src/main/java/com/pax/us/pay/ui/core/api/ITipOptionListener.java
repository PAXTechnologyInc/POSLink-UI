package com.pax.us.pay.ui.core.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface ITipOptionListener extends IUIListener {
    void onShowTipOptions(@NonNull String[] options, @Nullable String[] rateOptions);
}
