package com.pax.us.pay.ui.core.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Arrays;

public interface ITipsListener {

    void onShowTips(@NonNull String[] tipNames, @Nullable long[] tipAmounts);
}
