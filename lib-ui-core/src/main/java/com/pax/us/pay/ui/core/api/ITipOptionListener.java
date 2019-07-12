package com.pax.us.pay.ui.core.api;

import android.support.annotation.NonNull;

public interface ITipOptionListener extends IUIListener {
    void onShowTipOptions(@NonNull String[] options);
}
