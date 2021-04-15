package com.pax.us.pay.ui.core.api;

import android.support.annotation.Nullable;

public interface ISignatureLineListener extends IUIListener {
    void onShowSignatureLine(@Nullable String line1, @Nullable String line2);
}
