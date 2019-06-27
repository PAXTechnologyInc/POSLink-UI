package com.pax.us.pay.ui.core;

import android.os.Bundle;

public abstract class BaseActionHelper {

    private IActionHandler handler;


    protected void sendNext(Bundle bundle) {
        if (handler != null) {
            handler.sendNext(bundle);
        }
    }

    protected void sendNext() {
        if (handler != null) {
            handler.sendNext(null);
        }
    }

    public void sendAbort() {
        if (handler != null) {
            handler.sendAbort();
        }
    }

    public void sendPrev() {
        if (handler != null) {
            handler.sendPrev();
        }
    }

    protected void setSecurityArea(Bundle bundle) {
        if (handler != null) {
            handler.setSecurityArea(bundle);
        }
    }
}
