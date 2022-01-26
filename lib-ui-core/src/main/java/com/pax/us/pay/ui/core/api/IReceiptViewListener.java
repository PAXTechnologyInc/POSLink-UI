package com.pax.us.pay.ui.core.api;

import androidx.annotation.NonNull;

public interface IReceiptViewListener extends IUIListener {
    void onShowReceiptView(@NonNull String receiptUri);
}
