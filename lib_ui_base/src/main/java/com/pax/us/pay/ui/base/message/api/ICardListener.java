package com.pax.us.pay.ui.base.message.api;

public interface ICardListener extends IUIListener {
    void onShowCard(boolean enableManualEntry, boolean enableSwipe, boolean enableInsert, boolean enableTap);
}
