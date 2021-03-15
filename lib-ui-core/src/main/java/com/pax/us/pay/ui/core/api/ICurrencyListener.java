package com.pax.us.pay.ui.core.api;

import androidx.annotation.Nullable;

public interface ICurrencyListener extends IUIListener {
    void onShowCurrency(@Nullable String currency, boolean isPoint);
}
