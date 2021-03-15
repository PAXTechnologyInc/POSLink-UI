package com.pax.us.pay.ui.core.api;

import androidx.annotation.NonNull;

public interface IEULAViewListener extends IUIListener {
    void onShowEULAView(@NonNull String eulaUri);
}
