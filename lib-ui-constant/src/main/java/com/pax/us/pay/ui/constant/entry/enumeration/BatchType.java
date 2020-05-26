package com.pax.us.pay.ui.constant.entry.enumeration;

public final class BatchType {
    public static final String BATCH_CLOSE = "Batch Close";
    public static final String PURGE_BATCH = "Purge Batch";
    public static final String HOST_TOTALS = "Host Totals";

    public static final String BATCH_CLOSE_NEW = "BATCH CLOSE";
    public static final String PURGE_BATCH_NEW = "PURGE BATCH";
    public static final String HOST_TOTALS_NEW = "HOST TOTALS";
    public static final String FORCE_BATCH_CLOSE = "FORCE BATCH CLOSE";

    public static String[] values() {
        return new String[]{BATCH_CLOSE_NEW, PURGE_BATCH_NEW, HOST_TOTALS_NEW, FORCE_BATCH_CLOSE};
    }
}
