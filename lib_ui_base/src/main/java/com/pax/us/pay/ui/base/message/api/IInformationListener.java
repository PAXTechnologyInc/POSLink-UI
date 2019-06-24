package com.pax.us.pay.ui.base.message.api;

import java.util.Map;

public interface IInformationListener extends IUIListener {
    void onShowInformation(Map<String, String> informations);
}
