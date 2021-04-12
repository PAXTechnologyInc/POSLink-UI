package com.pax.pay.ui.def.view;

import android.content.DialogInterface;

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

    void onShowResult(boolean isSuccess, String message);

    void onShowResult(boolean isSuccess, String message, DialogInterface.OnDismissListener dismissListener);

}
