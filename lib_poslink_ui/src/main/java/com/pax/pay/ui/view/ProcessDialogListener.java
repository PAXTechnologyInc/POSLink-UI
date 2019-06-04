package com.pax.pay.ui.view;

/**
 * Created by Sim.G on 2017/6/5 09:42
 */
public interface ProcessDialogListener {

    void onShowProgress(String message);

    /**
     * must be called after calling any show*** method once
     */
    void onHideProgress();

    void onShowWarn(String message);

    void onUpdateMessage(String message);

}
