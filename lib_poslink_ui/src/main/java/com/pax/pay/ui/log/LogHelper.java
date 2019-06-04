package com.pax.pay.ui.log;

import android.app.Application;

import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by Kim.L on 2018/10/22.
 */
public class LogHelper {

    public static void init(Application application, String tag) {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 0
                .tag(tag)
                .build();
        //LOG FIXME Kim Android 6.0 and above request permission confirm
        Logger.addLogAdapter(new FileLogAdapter(formatStrategy, application.getExternalFilesDir(null).getAbsolutePath(), tag));
        Logger.d("init:");
    }

}
