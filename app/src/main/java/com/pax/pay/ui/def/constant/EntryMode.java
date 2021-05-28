package com.pax.pay.ui.def.constant;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Kim.L on 2018/5/22.
 */

@IntDef(flag = true, value = {
        EntryMode.MANUAL, EntryMode.SWIPE, EntryMode.INSERT, EntryMode.CLSS, EntryMode.QR,
        EntryMode.SWIPE | EntryMode.SWIPE_FLAG_FALL_BACK,
        EntryMode.SWIPE | EntryMode.SWIPE_FLAG_NO_EMV_APP_FALL_BACK,
        EntryMode.SWIPE | EntryMode.SWIPE_FLAG_FALL_BACK | EntryMode.SWIPE_FLAG_NO_EMV_APP_FALL_BACK
})
@Retention(RetentionPolicy.SOURCE)
public @interface EntryMode {

    /**
     * swiped
     */
    //String SWIPE = "S";
    int SWIPE = 0x01;
    /**
     * insert
     */
    //String INSERT = "I";
    int INSERT = 0x01 << 1;
    /**
     * contactless
     */
    //String CLSS = "C";
    int CLSS = 0x01 << 2;

    /**
     * manually entered
     */
    //String MANUAL = "M";
    int MANUAL = 0x01 << 3;

    /**
     * scanner
     */
    //String QR = "Q";
    int QR = 0x1 << 4;

    /**
     * combine with SWIPE
     */
    int SWIPE_FLAG_FALL_BACK = 0x01000000;
    int SWIPE_FLAG_NO_EMV_APP_FALL_BACK = 0x01000000 << 1;
}
