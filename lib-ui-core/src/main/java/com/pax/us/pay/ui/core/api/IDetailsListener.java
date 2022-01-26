package com.pax.us.pay.ui.core.api;

import androidx.annotation.NonNull;

public interface IDetailsListener extends IUIListener {
    void onShowDetails(@NonNull String[] key, @NonNull String[] value);
}
