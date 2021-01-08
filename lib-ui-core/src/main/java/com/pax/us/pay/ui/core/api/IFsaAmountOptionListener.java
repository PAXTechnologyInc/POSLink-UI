package com.pax.us.pay.ui.core.api;

public interface IFsaAmountOptionListener extends IUIListener {
    void onShowFsaAmountOption(boolean healthCareVisible, boolean clinicVisible, boolean prescriptionVisible,
                               boolean dentalVisible, boolean versionVisible, boolean copayVisible, boolean otcVisible, boolean transitVisible);
}
