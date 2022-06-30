package com.paxus.utils.log;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
    private final SimpleDateFormat dateFormatFileName = new SimpleDateFormat("yyyyMMdd", Locale.US);
    private final SimpleDateFormat dateFormatLog = new SimpleDateFormat("MM-dd-HH-mm-ss.SSS", Locale.US);
    private final Thread.UncaughtExceptionHandler mHandler;
    private String logPath;
    private String filenamePrefix;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private LogHelper.LoggerCallback callback;

    FileLogAdapter(@NonNull FormatStrategy formatStrategy, String path, String filenamePrefix) {
        super(formatStrategy);
        logPath = path;
        this.filenamePrefix = filenamePrefix;
        mHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    FileLogAdapter(@NonNull FormatStrategy formatStrategy, String path, String filenamePrefix, LogHelper.LoggerCallback callback) {
        super(formatStrategy);
        logPath = path;
        this.filenamePrefix = filenamePrefix;
        mHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

        this.callback = callback;
    }

    @Override
    public void log(int priority, @Nullable String tag, @NonNull String message) {
        if (priority >= Log.INFO) {
            if (tag == null || tag.length() == 0) {
                tag = priorityToTag(priority);
            }
            writeToFile(priority, tag, message);
        }
        if (BuildConfig.DEBUG) {
            super.log(priority, tag, message);
        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
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
        // Arvind: Print the log stacktrace to the logfile and display it
        Logger.e("FileLogAdaptor::handleExceptions() => " + ex + "\n" + getStackTrace(ex.getStackTrace()));
        if (callback != null) callback.onAppCrashed();
        return true;
    }

    private void writeToFile(final int priority, final String tag, final String msg) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int stacktraceCnt = 5;
        if (stackTrace.length <= stacktraceCnt) {
            return;
        }
        final String codeFile = stackTrace[stacktraceCnt].getFileName();
        final int codeLineNum = stackTrace[stacktraceCnt].getLineNumber();
        final String codeMethod = stackTrace[stacktraceCnt].getMethodName();
        executorService.submit(() -> doWriteToFile(priority, tag, msg, codeFile, codeLineNum, codeMethod));
    }

    private void doWriteToFile(int priority, String tag, String msg, String codeFile, int codeLineNum, String codeMethod) {

        if (null == logPath) {
            Logger.e("logPath == null ，should init LogToFile");
            return;
        }


        String fileName = logPath + File.separator + filenamePrefix + dateFormatFileName.format(new Date()) + FILE_NAME_SUFFIX;
        String log = dateFormatLog.format(new Date()) + " " + tag + " " + codeFile + "[" + codeLineNum + "]" + "(" + codeMethod + ") " + msg + "\n";


        File file = new File(logPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(fileName, true);
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos))) {
            bw.write(log);
            if (!BuildConfig.DEBUG) {
                String simpleLog = codeFile + "[" + codeLineNum + "]" + "(" + codeMethod + ") " + msg + "\n";
                //tag + "\b" avoids tag string being empty.
                Log.println(priority, tag + "\b", simpleLog);
            }

        } catch (IOException e) {
            Logger.e(e.getMessage());
        }
    }

    private void uploadExceptionToServer() {
        // TODO Upload Exception Message To Your Web Server
    }

    // Arvind: To get the stack trace from the Throwable
    private String getStackTrace(StackTraceElement[] stackTraceElements) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element: stackTraceElements) sb.append(element + "\n");
        return sb.toString();
    }

    private String priorityToTag(int priority) {
        switch (priority) {
            case Log.VERBOSE:
                return "V";
            case Log.DEBUG:
                return "D";
            case Log.INFO:
                return "I";
            case Log.WARN:
                return "W";
            case Log.ERROR:
                return "E";
            case Log.ASSERT:
                return "A";
        }

        return "D";
    }
}
