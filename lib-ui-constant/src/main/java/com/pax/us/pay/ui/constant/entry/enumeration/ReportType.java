package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Batch Report Type
 */
public final class ReportType {
    private ReportType(){

    }
    //Yanina: Old definition
//    public static final String CURRENT_BATCH = "Current Batch";
//    public static final String PREVIOUS_BATCH = "Previous Batch";
    /**
     * CURRENT BATCH
     */
    public static final String CURRENT_BATCH = "CURRENT BATCH";

    /**
     * PREVIOUS BATCH
     */
    public static final String PREVIOUS_BATCH = "PREVIOUS BATCH";

    /**
     * All defined String
     * @return String[]
     */
    public static String[] values() {
        return new String[]{CURRENT_BATCH, PREVIOUS_BATCH};
    }
}
