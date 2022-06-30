package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Batch Type
 *
 */
public final class BatchType {
    private BatchType(){

    }
    //Yanina: Old definition
//    public static final String BATCH_CLOSE = "Batch Close";
//    public static final String PURGE_BATCH = "Purge Batch";
//    public static final String HOST_TOTALS = "Host Totals";
    /**
     * Batch Close
     */
    public static final String BATCH_CLOSE = "BATCH CLOSE";

    /**
     * Purge Batch
     */
    public static final String PURGE_BATCH = "PURGE BATCH";

    /**
     * Host Totals
     */
    public static final String HOST_TOTALS = "HOST TOTALS";

    /**
     * Force Batch Close
     */
    public static final String FORCE_BATCH_CLOSE = "FORCE BATCH CLOSE";

    /**
     * All defined String
     * @return String[]
     */
    public static String[] values() {
        return new String[]{BATCH_CLOSE, PURGE_BATCH, HOST_TOTALS, FORCE_BATCH_CLOSE};
    }
}
