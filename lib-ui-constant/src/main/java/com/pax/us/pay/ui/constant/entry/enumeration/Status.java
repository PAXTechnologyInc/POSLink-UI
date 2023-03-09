package com.pax.us.pay.ui.constant.entry.enumeration;

public class Status {
    public enum State {PROCESSING, WAITING, SUCCESS, FAIL}

    public enum Of {
        TRANS, TRANS_ONLINE, EMV_TRANS_ONLINE, DCC_ONLINE, PINPAD_CONNECTION, RKI, ENTER_PIN,
        CARD_PROCESS, CARD_INSERT, CARD_SWIPE, CARD_TAP, CARD_REMOVAL, CARD_QUICK_REMOVAL,
        PHONE,
        BATCH_CLOSE, BATCH_SF,
        CAPK_UPDATE, PRINT, FILE_UPDATE, LOG_UPLOAD, FCP_FILE_UPLOAD
    }
}
