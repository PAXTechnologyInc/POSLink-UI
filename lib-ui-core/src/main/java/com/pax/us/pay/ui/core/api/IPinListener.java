package com.pax.us.pay.ui.core.api;

import android.support.annotation.Nullable;

public interface IPinListener extends IUIListener {
    void onShowPin(@Nullable String pinStyles, boolean isOnline);
}
