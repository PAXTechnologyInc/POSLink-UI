package com.pax.pay.ui.def_ui.log;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Kim.L on 2018/5/25.
 */

public class FileLogAdapter extends AndroidLogAdapter implements Thread.UncaughtExceptionHandler {
    private static final String FILE_NAME_SUFFIX = ".log";

    private String logPath;
    private String filenamePrefix;
    private final SimpleDateFormat dateFormatFileName = new SimpleDateFormat("yyyyMMdd", Locale.US);//日期格式
    private final SimpleDateFormat dateFormatLog = new SimpleDateFormat("MM-dd-HH-mm-ss.SSS", Locale.US);//日期格式
    private final Thread.UncaughtExceptionHandler mHandler;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public FileLogAdapter(@NonNull FormatStrategy formatStrategy, String path, String filenamePrefix) {
        super(formatStrategy);
        logPath = path;
        this.filenamePrefix = filenamePrefix;
        mHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void log(int priority, @Nullable String tag, @NonNull String message) {
        if (priority >= Log.ERROR) {
            writeToFile(tag, message);
        }
        super.log(priority, tag, message);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        // 如果用户没有处理则让系统默认的异常处理器来处理
        if (handleExceptions(ex) && mHandler != null) {
            mHandler.uncaughtException(thread, ex);
        }
    }

    private boolean handleExceptions(Throwable ex) {
        if (ex == null) {
            return false;
        }
        log(Log.ASSERT, "App Crash", ex.getMessage());
        log(Log.ASSERT, "Terminal Info", Build.MODEL);
        uploadExceptionToServer();
        return true;
    }

    private void writeToFile(final String tag, final String msg) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int stacktraceCnt = stackTrace.length >= 7 ? 7 : 2; //Yanina
        final String codeFile = stackTrace[stacktraceCnt].getFileName();
        final int codeLineNum = stackTrace[stacktraceCnt].getLineNumber();
        final String codeMethod = stackTrace[stacktraceCnt].getMethodName();
        executorService.submit(() -> doWriteToFile(tag, msg, codeFile, codeLineNum, codeMethod));
    }

    private void doWriteToFile(String tag, String msg, String codeFile, int codeLineNum, String codeMethod) {

        if (null == logPath) {
            Logger.e("logPath == null ，未初始化LogToFile");
            return;
        }

        if (tag == null)
            tag = "";

        String fileName = logPath + File.separator + filenamePrefix + dateFormatFileName.format(new Date()) + FILE_NAME_SUFFIX;//log日志名，使用时间命名，保证不重复
        String log = dateFormatLog.format(new Date()) + " " + codeFile + "[" + codeLineNum + "]" + "(" + codeMethod + ") " + tag + " " + msg + "\n";//log日志内容，可以自行定制

        //如果父路径不存在
        File file = new File(logPath);
        if (!file.exists()) {
            file.mkdirs();//创建父路径
        }

        try (FileOutputStream fos = new FileOutputStream(fileName, true);
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos))) {
            bw.write(log);

        } catch (IOException e) {
            Logger.e(e.getMessage());
        }
    }

    private void uploadExceptionToServer() {
        // TODO Upload Exception Message To Your Web Server
    }
}
