package com.pax.us.pay.ui.constant.status;

/**
 * define Broadcast which has no category
 */
public final class Uncategory {
    private Uncategory(){

    }
    /**
     * @deprecated Not used anymore
     */
    public static final String ACTIVATE_STARTED = "com.pax.us.pay.ACTIVATE_STARTED";
    /**
     * @deprecated Not used anymore
     */
    public static final String ACTIVATE_COMPLETED = "com.pax.us.pay.ACTIVATE_COMPLETED";

    /**
     * Broadcast Action: prompt "Downloading EMV CAPK\nPlease Wait..."
     */
    public static final String CAPK_UPDATE_STARTED = "com.pax.us.pay.CAPK_UPDATE_STARTED";
    /**
     * Broadcast Action: close "Downloading EMV CAPK\nPlease Wait..."
     */
    public static final String CAPK_UPDATE_COMPLETED = "com.pax.us.pay.CAPK_UPDATE_COMPLETED";

    /**
     * Broadcast Action: prompt "Printing..."
     */
    public static final String PRINT_STARTED = "com.pax.us.pay.PRINT_STARTED";
    /**
     * Broadcast Action: close "Printing..."
     */
    public static final String PRINT_COMPLETED = "com.pax.us.pay.PRINT_COMPLETED";

    /**
     * Broadcast Action: prompt "Printing..."
     */
    public static final String FILE_UPDATE_STARTED = "com.pax.us.pay.FILE_UPDATE_STARTED";
    /**
     * Broadcast Action: close "Printing..."
     */
    public static final String FILE_UPDATE_COMPLETED = "com.pax.us.pay.FILE_UPDATE_COMPLETED";

    /**
     * Broadcast Action: prompt "Starting Upload...\nWaiting For Connection"
     */
    public static final String LOG_UPLOAD_STARTED = "com.pax.us.pay.LOG_UPLOAD_STARTED";
    /**
     * Broadcast Action: prompt "Connected... (uploadPercent %)"
     * <p>
     *     Input: {@link StatusData#PARAM_UPLOAD_CURRENT_PERCENT} uploadPercent</p>
     */
    public static final String LOG_UPLOAD_CONNECTED = "com.pax.us.pay.LOG_UPLOAD_CONNECTED";
    /**
     * Broadcast Action: prompt "Updating... logUploadCount/logTotalCount (uploadPercent%)"
     * <p>
     *     Input: {@link StatusData#PARAM_UPLOAD_CURRENT_COUNT} logUploadCount</p>
     * <p>
     *     Input: {@link StatusData#PARAM_UPLOAD_TOTAL_COUNT} logTotalCount</p>
     * <p>
     *     Input: {@link StatusData#PARAM_UPLOAD_CURRENT_PERCENT} uploadPercent</p>
     */
    public static final String LOG_UPLOAD_UPLOADING = "com.pax.us.pay.LOG_UPLOAD_UPLOADING";
    /**
     * Broadcast Action: close Log Upload prompt
     */
    public static final String LOG_UPLOAD_COMPLETED = "com.pax.us.pay.LOG_UPLOAD_COMPLETED";

    /**
     * Broadcast Action: prompt "Checking For Update..."
     */
    public static final String FCP_FILE_UPDATE_STARTED = "com.pax.us.pay.FCP_FILE_UPDATE_STARTED";

    /**
     * Broadcast Action: close "Checking For Update..."
     */
    public static final String FCP_FILE_UPDATE_COMPLETED = "com.pax.us.pay.FCP_FILE_UPDATE_COMPLETED";


}
