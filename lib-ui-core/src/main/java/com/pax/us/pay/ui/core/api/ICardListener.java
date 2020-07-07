package com.pax.us.pay.ui.core.api;

public interface ICardListener extends IUIListener {
    void onShowCard(boolean enableManualEntry, boolean enableSwipe, boolean enableInsert, boolean enableTap);
    void onShowLight(boolean enableContactlessLight);

    void onShowPanStyle(String panStyles);

    void onShowScanIcon(boolean enable);
}
