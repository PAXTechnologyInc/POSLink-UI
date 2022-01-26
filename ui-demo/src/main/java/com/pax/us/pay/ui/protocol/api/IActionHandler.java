package com.pax.us.pay.ui.protocol.api;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * message sender interface
 */
public interface IActionHandler {

    /**
     * send next request with data
     *
     * @param bundle {@link com.pax.us.pay.ui.constant.entry.EntryRequest}
     */
    void sendNext(@Nullable Bundle bundle);

    /**
     * abort the current action
     */
    void sendAbort();

    /**
     * go back to the previous action, (not supported yet)
     */
    void sendPrev();

    /**
     * set widget area for security action
     *
     * @param bundle {@link com.pax.us.pay.ui.constant.entry.EntryRequest#PARAM_X}
     *               {@link com.pax.us.pay.ui.constant.entry.EntryRequest#PARAM_Y}
     *               {@link com.pax.us.pay.ui.constant.entry.EntryRequest#PARAM_WIDTH}
     *               {@link com.pax.us.pay.ui.constant.entry.EntryRequest#PARAM_HEIGHT}
     */
    void setSecurityArea(@NonNull Bundle bundle);

    void start();

    void stop();

}
