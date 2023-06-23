package com.pax.us.pay.ui.constant.status;

/**
 * define Broadcast which has BATCH Category
 */
public final class BatchStatus {
    private BatchStatus(){

    }
    /**
     * Broadcast Category: BATCH<br>{@value #CATEGORY}<br>
     */
    public static final String CATEGORY = "com.pax.us.pay.status.category.BATCH";

    /**
     * Broadcast Action: Batch Close begin<br>{@value #BATCH_CLOSE_STARTED}<br>
     * <p>"Batch Close Start..."</p>
     * @deprecated Not used anymore. Use {@link #BATCH_CLOSE_UPLOADING} instead.
     */
    public static final String BATCH_CLOSE_STARTED = "com.pax.us.pay.BATCH_CLOSE_STARTED";

    /**
     * Broadcast Action: Batch Upload begin<br>{@value #BATCH_CLOSE_UPLOADING}<br>
     * <p>"Uploading "+edcType+"\n"+currentCount+"/"+totalCount</p>
     * <p>
     *     Input: {@link StatusData#PARAM_EDC_TYPE} edcType.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.EDCType}
     * </p>
     * <p>
     *     Input: {@link StatusData#PARAM_UPLOAD_CURRENT_COUNT} currentCount<br>
     *     Type: Long
     * </p>
     * <p>
     *     Input: {@link StatusData#PARAM_UPLOAD_TOTAL_COUNT} totalCount<br>
     *     Type: Long
     * </p>
     */
    public static final String BATCH_CLOSE_UPLOADING = "com.pax.us.pay.BATCH_CLOSE_UPLOADING";

    /**
     * Broadcast Action: Batch Upload end<br>{@value #BATCH_CLOSE_COMPLETED}<br>
     */
    public static final String BATCH_CLOSE_COMPLETED = "com.pax.us.pay.BATCH_CLOSE_COMPLETED";

    /**
     * Broadcast Action: S&amp;F start<br>{@value #BATCH_SF_STARTED}<br>
     * @deprecated Not used anymore. Use {@link #BATCH_UPLOADING} instead.
     */
    public static final String BATCH_SF_STARTED = "com.pax.us.pay.BATCH_SF_STARTED";

    /**
     * Broadcast Action: S&amp;F Upload begin<br>{@value #BATCH_UPLOADING}<br>
     * <p>
     *     Input: {@link StatusData#PARAM_SF_TYPE} sfType <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link StatusData#PARAM_SF_CURRENT_COUNT} currentCount</p>
     * <p>
     *     Input: {@link StatusData#PARAM_SF_TOTAL_COUNT} totalCount</p>
     *
     * <p>
     *     if sfType is {@link com.pax.us.pay.ui.constant.entry.enumeration.SFType#STORED},<br>
     *     display: "Uploading Store Transaction...\nTotal Count :"+totalCount+" Current Count :"+totalCount<br>
     *     if sfType is {@link com.pax.us.pay.ui.constant.entry.enumeration.SFType#FAILED},<br>
     *     display: "Uploading Failed Transaction...\nTotal Count :"+totalCount+" Current Count :"+totalCount
     * </p>
     */
    public static final String BATCH_UPLOADING = "com.pax.us.pay.BATCH_UPLOADING";
    /**
     * Broadcast Action: S&amp;F Upload end<br>{@value #BATCH_SF_COMPLETED}<br>
     */
    public static final String BATCH_SF_COMPLETED = "com.pax.us.pay.BATCH_SF_COMPLETED";

}
