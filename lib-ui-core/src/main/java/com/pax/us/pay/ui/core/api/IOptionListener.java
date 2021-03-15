package com.pax.us.pay.ui.core.api;

import androidx.annotation.NonNull;

public interface IOptionListener extends IUIListener {
    void onShowOptions(@NonNull String[] options);
}
