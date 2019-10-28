package com.pax.us.pay.ui.core.api;

import android.support.annotation.NonNull;

public interface ICashbackOptionListener extends IUIListener {
    /**
     * show pre-set cash back options
     * @param options
     */
    void onShowCashbackOptions(@NonNull String[] options, @NonNull String[] rateOptions);
}
