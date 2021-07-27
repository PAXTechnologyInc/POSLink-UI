package com.pax.us.pay.ui.core.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface ICardTypeListener {
    @Deprecated
    void onShowCardType(@NonNull String cardType);

    void onShowCardType(@NonNull String cardType, @Nullable String soundUri);
}
