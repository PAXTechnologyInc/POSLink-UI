package com.pax.us.pay.ui.core.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface ICashbackOptionListener extends IUIListener {
    /**
     * show pre-set cash back options
     * @param options
     */
    void onShowCashbackOptions(@NonNull String[] options, @Nullable String[] rateOptions);

    /**
     * enable or disable show others option
     *
     * @param enable
     */
    void onShowEnableOtherPrompt(boolean enable);
}
