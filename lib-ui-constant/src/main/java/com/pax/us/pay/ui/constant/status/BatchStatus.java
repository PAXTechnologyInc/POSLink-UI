package com.pax.us.pay.ui.constant.status;

public final class BatchStatus {
    public static final String CATEGORY = "com.pax.us.pay.status.category.BATCH";

    //BATCH
    public static final String BATCH_CLOSE_STARTED = "com.pax.us.pay.BATCH_CLOSE_STARTED";
    public static final String BATCH_CLOSE_COMPLETED = "com.pax.us.pay.BATCH_CLOSE_COMPLETED";

    public static final String BATCH_SF_STARTED = "com.pax.us.pay.BATCH_SF_STARTED";

    /**
     * param: {@link BatchStatus#PARAM_SF_TYPE} enum: {@link com.pax.us.pay.ui.constant.entry.enumeration.SFType} <br>
     * param: {@link BatchStatus#PARAM_SF_CURRENT} <br>
     * param: {@link BatchStatus#PARAM_SF_TOTAL} <br>
     */
    public static final String BATCH_SF_UPLOADING = "com.pax.us.pay.BATCH_UPLOADING";
    public static final String BATCH_SF_COMPLETED = "com.pax.us.pay.BATCH_SF_COMPLETED";

    public static final String PARAM_SF_TYPE = "sfType";
    public static final String PARAM_SF_CURRENT = "sfCurrent";
    public static final String PARAM_SF_TOTAL = "sfTotal";
}
