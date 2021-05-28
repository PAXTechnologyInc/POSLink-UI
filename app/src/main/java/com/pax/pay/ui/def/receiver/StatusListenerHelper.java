package com.pax.pay.ui.def.receiver;

public class StatusListenerHelper {
    private static StatusListenerHelper instance;

    private IStatusListener listener;

    public static StatusListenerHelper getInstance() {
        if (instance == null) {
            instance = new StatusListenerHelper();
        }
        return instance;
    }

    public synchronized IStatusListener getListener() {
        return listener;
    }

    public synchronized void setListener(IStatusListener listener) {
        this.listener = listener;
    }

}
