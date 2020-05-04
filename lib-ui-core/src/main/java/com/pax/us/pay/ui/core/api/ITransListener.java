package com.pax.us.pay.ui.core.api;

import android.support.annotation.NonNull;

public interface ITransListener extends IUIListener {
    //void onShowTrans(@NonNull String transUrl, @NonNull boolean enableVoid, @NonNull boolean enableAdjust, @NonNull boolean enablePrint);
    void onShowTrans(@NonNull String transUrl, @NonNull String selection, @NonNull String[] selectArgs);
}
