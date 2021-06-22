package com.pax.us.pay.ui.constant.status;

public final class LogUploadStatus {
    public static final String CATEGORY = "com.pax.us.pay.status.category.LOGUPLOAD";

    public final class BroadcastContract {
        public static final String LOG_UPLOAD_SUCCESS = "com.pax.us.pay.LOG_UPLOAD_SUCCESS";
        public static final String LOG_UPLOAD_FAIL = "com.pax.us.pay.LOG_UPLOAD_FAIL";
        public static final String LOG_UPLOAD_TIMEOUT = "com.pax.us.pay.LOG_UPLOAD_TIMEOUT";
        public static final String LOG_UPLOAD_UNABLE = "com.pax.us.pay.LOG_UPLOAD_UNABLE";
        public static final String LOG_UPLOAD_ABORT = "com.pax.us.pay.LOG_UPLOAD_ABORT";
    }

    public final class StatusCode {
        public static final int OK = 0;
        public static final int COMM_ERROR = 1;
        public static final int ABORTED = 2;
        public static final int CONNECT_ERROR = 3;
        public static final int UNKNOW_ERROR = 4;
        public static final int SFTP_COMM_ERROR = 5;
        public static final int NO_DATA_TO_UPLOAD = 6;
    }
}
