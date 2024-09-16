package com.pax.us.pay.ui.constant.entry.enumeration;

public class BatchReportType {
    private BatchReportType() {

    }

    /**
     * JOURNAL REPORT
     */
    public static final String JOURNAL_REPORT = "Journal Report";

    /**
     * TOTALS_ONLY REPORT
     */
    public static final String TOTAL_REPORT = "Total Report";

    /**
     * CURRENT REPORT
     */
    public static final String TOTAL_AND_JOURNAL = "Total And Journal";

    /**
     * NONE
     */
    public static final String NONE = "None";

    /**
     * All defined String
     * @return String[]
     */
    public static String[] values() {
        return new String[]{JOURNAL_REPORT, TOTAL_REPORT, TOTAL_AND_JOURNAL, NONE};
    }
}
