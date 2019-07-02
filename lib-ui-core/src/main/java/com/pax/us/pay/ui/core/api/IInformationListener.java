package com.pax.us.pay.ui.core.api;

import java.util.Map;

public interface IInformationListener extends IUIListener {
    void onShowInformation(Map<String, String> informations);
}
