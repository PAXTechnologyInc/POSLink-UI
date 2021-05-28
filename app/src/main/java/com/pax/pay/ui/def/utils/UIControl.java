package com.pax.pay.ui.def.utils;

public class UIControl {
    private static UIControl inStance;
    private boolean transStarted = false;


    public static UIControl getInstance() {
        if (inStance == null) {
            inStance = new UIControl();
        }

        return inStance;
    }

    public boolean isTransStarted() {
        return transStarted;
    }

    public void setTransStarted(boolean transStarted) {
        this.transStarted = transStarted;
    }
}
