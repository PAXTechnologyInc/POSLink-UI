package com.pax.us.pay.ui.core.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface ITipOptionListener extends IUIListener {
    void onShowTipOptions(@NonNull String[] options, @Nullable String[] rateOptions);
}
