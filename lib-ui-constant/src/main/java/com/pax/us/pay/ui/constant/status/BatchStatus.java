package com.pax.us.pay.ui.constant.status;

/**
 * define Broadcast which has BATCH Category
 */
public final class BatchStatus {
    private BatchStatus(){

    }
    /**
     * Broadcast Category: BATCH
     */
    public static final String CATEGORY = "com.pax.us.pay.status.category.BATCH";

    /**
     * Broadcast Action: Batch Close begin
     * <p>"Batch Close Start..."</p>
     * @deprecated Not used anymore. Use {@link #BATCH_CLOSE_UPLOADING} instead.
     */
    public static final String BATCH_CLOSE_STARTED = "com.pax.us.pay.BATCH_CLOSE_STARTED";

    /**
     * Broadcast Action: Batch Upload begin
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
     * Broadcast Action: Batch Upload end
     */
    public static final String BATCH_CLOSE_COMPLETED = "com.pax.us.pay.BATCH_CLOSE_COMPLETED";

    /**
     * Broadcast Action: S&amp;F start
     * @deprecated Not used anymore. Use {@link #BATCH_SF_UPLOADING} instead.
     */
    public static final String BATCH_SF_STARTED = "com.pax.us.pay.BATCH_SF_STARTED";

    /**
     * Broadcast Action: S&amp;F Upload begin
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
    public static final String BATCH_SF_UPLOADING = "com.pax.us.pay.BATCH_UPLOADING";
    /**
     * Broadcast Action: S&amp;F Upload end
     */
    public static final String BATCH_SF_COMPLETED = "com.pax.us.pay.BATCH_SF_COMPLETED";

    /**
     * S&amp;F Type
     * <p>Type: String</p>
     * <p>value see {@link com.pax.us.pay.ui.constant.entry.enumeration.SFType}</p>
     */
    public static final String PARAM_SF_TYPE = "sfType";

    /**
     * S&amp;F Current Count
     * <p>Type: Long. Default: 0</p>
     */
    public static final String PARAM_SF_CURRENT = "sfCurrent";

    /**
     * S&amp;F Total Count
     * <p>Type: Long. Default: 0</p>
     */
    public static final String PARAM_SF_TOTAL = "sfTotal";
}
