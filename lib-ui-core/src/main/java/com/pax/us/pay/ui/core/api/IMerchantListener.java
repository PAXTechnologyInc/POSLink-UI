package com.pax.us.pay.ui.core.api;

import android.support.annotation.Nullable;

public interface IMerchantListener extends IUIListener {
    void onShowMerchant(@Nullable String merchantName);
}
