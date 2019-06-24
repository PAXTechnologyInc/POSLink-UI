package com.pax.us.pay.ui.base.message.api;

import java.util.List;

public interface IOptionListener extends IUIListener {
    void onShowOptions(List<String> options);
}
