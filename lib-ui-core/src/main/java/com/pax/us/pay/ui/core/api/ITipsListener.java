package com.pax.us.pay.ui.core.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;

public interface ITipsListener {

    void onShowTips(@NonNull String[] tipNames, @Nullable long[] tipAmounts);
}
