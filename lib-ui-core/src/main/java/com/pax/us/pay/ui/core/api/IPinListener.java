package com.pax.us.pay.ui.core.api;

import android.support.annotation.Nullable;

public interface IPinListener extends IUIListener {
    /*
    @Deprecated
    @Link onShowPin((@Nullable String pinStyles, boolean isOnline, boolean isPinBypass))
    void onShowPin(@Nullable String pinStyles, boolean isOnline);
    */
    void onShowPin(@Nullable String pinStyles, boolean isOnline, boolean isPinBypass, boolean isExternalPinpad);

    void onShowVirtualPinpad(boolean showVirtualPinPad);
}
