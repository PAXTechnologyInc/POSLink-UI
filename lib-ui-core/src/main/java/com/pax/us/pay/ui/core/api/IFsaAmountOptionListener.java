package com.pax.us.pay.ui.core.api;

public interface IFsaAmountOptionListener extends IUIListener {
    //void onShowFsaAmountOption(String[] option);
    void onShowHealthCareAmount();

    void onShowClinicAmount();

    void onShowPrescriptionAmt();

    void onShowDentalAmount();

    void onShowVisionAmount();

    void onShowCopayAmount();

    void onShowTransitAmount();
}
