package com.paxus.utils.log;

import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by Kim.L on 2018/10/22.
 */
public class LogHelper {

    /**
     * make sure the app has the permission to the logPath
     *
     * @param tag     log tag
     * @param logPath path to save log
     */
    public static void init(String tag, String logPath) {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodCount(1)
                .methodOffset(6)        // (Optional) Hides internal method calls up to offset. Default 0
                .tag(tag)
                .build();
        Logger.addLogAdapter(new FileLogAdapter(formatStrategy, logPath, tag));
        Logger.i("init:");
    }

    public static void init(String tag, String logPath, LoggerCallback callback) {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodCount(1)
                .methodOffset(6)        // (Optional) Hides internal method calls up to offset. Default 0
                .tag(tag)
                .build();
        Logger.addLogAdapter(new FileLogAdapter(formatStrategy, logPath, tag, callback));
        Logger.i("init:");
    }

    public interface LoggerCallback {
        public void onAppCrashed();
    }
}
