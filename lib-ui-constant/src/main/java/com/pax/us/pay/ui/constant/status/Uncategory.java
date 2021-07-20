package com.pax.us.pay.ui.constant.status;

public final class Uncategory {
    public static final String ACTIVATE_STARTED = "com.pax.us.pay.ACTIVATE_STARTED";
    public static final String ACTIVATE_COMPLETED = "com.pax.us.pay.ACTIVATE_COMPLETED";

    public static final String CAPK_UPDATE_STARTED = "com.pax.us.pay.CAPK_UPDATE_STARTED";
    public static final String CAPK_UPDATE_COMPLETED = "com.pax.us.pay.CAPK_UPDATE_COMPLETED";

    public static final String PRINT_STARTED = "com.pax.us.pay.PRINT_STARTED";
    public static final String PRINT_COMPLETED = "com.pax.us.pay.PRINT_COMPLETED";

    public static final String FILE_UPDATE_STARTED = "com.pax.us.pay.FILE_UPDATE_STARTED";
    public static final String FILE_UPDATE_COMPLETED = "com.pax.us.pay.FILE_UPDATE_COMPLETED";

    public static final String LOG_UPLOAD_STARTED = "com.pax.us.pay.LOG_UPLOAD_STARTED";
    /**
     * param: {@link StatusData#PARAM_UPLOAD_CURRENT_PERCENT} <br>
     */
    public static final String LOG_UPLOAD_CONNECTED = "com.pax.us.pay.LOG_UPLOAD_CONNECTED";
    /**
     * param: {@link StatusData#PARAM_UPLOAD_CURRENT_COUNT} <br>
     * param: {@link StatusData#PARAM_UPLOAD_TOTAL_COUNT} <br>
     * param: {@link StatusData#PARAM_UPLOAD_CURRENT_PERCENT} <br>
     */
    public static final String LOG_UPLOAD_UPLOADING = "com.pax.us.pay.LOG_UPLOAD_UPLOADING";
    public static final String LOG_UPLOAD_COMPLETED = "com.pax.us.pay.LOG_UPLOAD_COMPLETED";


    public static final String FCP_FILE_UPDATE_STARTED = "com.pax.us.pay.FCP_FILE_UPDATE_STARTED";
    public static final String FCP_FILE_UPDATE_COMPLETED = "com.pax.us.pay.FCP_FILE_UPDATE_COMPLETED";


}
