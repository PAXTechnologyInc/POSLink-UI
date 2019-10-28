package com.pax.us.pay.ui.core.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface ICashbackOptionListener extends IUIListener {
    /**
     * show pre-set cash back options
     * @param options
     */
    void onShowCashbackOptions(@NonNull String[] options, @Nullable String[] rateOptions);
}
