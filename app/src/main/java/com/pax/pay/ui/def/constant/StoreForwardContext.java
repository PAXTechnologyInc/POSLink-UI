package com.pax.pay.ui.def.constant;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Kim.L on 2019/10/25
 */
public final class StoreForwardContext {

    public static final String UPLOAD_BEFORE_BATCH = "Y";
    public static final String SILENT_AUTO_UPLOAD = "S";
    public static final int INDICATOR_ALL = 1;
    public static final int INDICATOR_NEW_STORED = 2;
    public static final int INDICATOR_FAILED = 3;
    public static final String MODE_STAY_ONLINE = "N";
    public static final String MODE_STAY_OFFLINE = "F";
    public static final String MODE_OFFLINE_BATCH = "B";
    public static final String MODE_OFFLINE_ON_DEMAND = "D";
    public static final int STATUS_NO_UPLOAD = 1;
    public static final int STATUS_UPLOAD_SUCCESS = 2;
    public static final int STATUS_UPLOAD_FAILED = 3;

    public static String getUploadStatusDesc(Context context, @StoreForwardUploadStatus int s) {
        if (s == STATUS_NO_UPLOAD) {
            return "No Upload";
        } else if (s == STATUS_UPLOAD_SUCCESS) {
            return "Uploaded Success";
        } else if (s == STATUS_UPLOAD_FAILED) {
            return "Uploaded Failed";
        } else {
            return "";
        }
    }

    @StringDef({
            UPLOAD_BEFORE_BATCH,
            SILENT_AUTO_UPLOAD,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface StoreForwardUploadMode {
    }

    @IntDef({
            INDICATOR_ALL,
            INDICATOR_NEW_STORED,
            INDICATOR_FAILED,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface StoreForwardIndicatorType {
    }

    @StringDef({
            MODE_STAY_ONLINE,
            MODE_STAY_OFFLINE,
            MODE_OFFLINE_BATCH,
            MODE_OFFLINE_ON_DEMAND
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface StoreForwardMode {
    }

    @IntDef({
            STATUS_NO_UPLOAD,
            STATUS_UPLOAD_SUCCESS,
            STATUS_UPLOAD_FAILED,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface StoreForwardUploadStatus {
    }
}
