package com.pax.us.pay.ui.core.api;

public interface ICashbackOptionListener extends IUIListener {
    /**
     * show pre-set cash back options
     *
     * @param options
     */
    void onShowCashbackOptions(String[] options);
}
