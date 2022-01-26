package com.pax.us.pay.ui.core.api;

import androidx.annotation.Nullable;

public interface IDccListener {
    void onShowDcc(@Nullable String amountMessage, @Nullable String cardCurrency,
                   @Nullable String exchangeRate, @Nullable String margin, @Nullable String foreignAmountMessage);
}
