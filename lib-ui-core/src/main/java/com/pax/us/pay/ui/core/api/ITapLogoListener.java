package com.pax.us.pay.ui.core.api;

public interface ITapLogoListener extends IUIListener {
    void onShowCardPay(boolean enableApplePay, boolean enableGooglePay, boolean enableSumsungPay, boolean enableNFCPay);
}
