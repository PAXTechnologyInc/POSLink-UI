package com.pax.us.pay.ui.core.api;

import android.support.annotation.NonNull;

public interface IReceiptViewListener extends IUIListener {
    void onShowReceiptView(@NonNull String receiptUri);
}
