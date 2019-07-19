package com.pax.us.pay.ui.core.api;

public interface IApproveAmountListener extends IUIListener {
    void onShowApproveAmount(long totalAmount, long approveAmount);
}
