package com.pax.us.pay.ui.core.api;

import android.support.annotation.NonNull;

public interface IInformationListener extends IUIListener {
    void onShowInformation(@NonNull String[] key, @NonNull String[] value);
}
