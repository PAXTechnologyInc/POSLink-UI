package com.pax.us.pay.ui.core.message.api;

import java.util.List;

public interface IAmountOptionListener extends IUIListener {
    void onShowAmountOptions(List<String> options);
}
