package com.pax.us.pay.ui.constant.entry.enumeration;

public final class BatchType {
    public static final String BATCH_CLOSE = "Batch Close";
    public static final String PURGE_BATCH = "Purge Batch";
    public static final String HOST_TOTALS = "Host Totals";


    public static String[] values() {
        return new String[]{BATCH_CLOSE, PURGE_BATCH, HOST_TOTALS};
    }
}
