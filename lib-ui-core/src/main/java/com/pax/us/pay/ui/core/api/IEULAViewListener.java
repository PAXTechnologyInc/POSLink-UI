package com.pax.us.pay.ui.core.api;

import android.support.annotation.NonNull;

public interface IEULAViewListener extends IUIListener {
    void onShowEULAView(@NonNull String eulaUri);
}
