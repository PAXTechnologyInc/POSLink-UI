package com.paxus.utils.log;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Logger, but more pretty, simple and powerful
 */
public final class Logger {

    private Logger() {
        //no instance
    }

    public static void d(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.d(message, args);
    }

    public static void d(@Nullable Object object) {
        com.orhanobut.logger.Logger.d(object);
    }

    public static void e(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.e(message, args);
    }

    public static void e(@Nullable Throwable throwable) {
        e(throwable, throwable == null || throwable.getMessage() == null ? "" : throwable.getMessage());
    }

    public static void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.e(throwable, message, args);
    }

    public static void i(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.i(message, args);
    }

    public static void v(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.v(message, args);
    }

    public static void w(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.w(message, args);
    }

    /**
     * Tip: Use this for exceptional situations to log
     * ie: Unexpected errors etc
     */
    public static void wtf(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.wtf(message, args);
    }

    /**
     * Formats the given json content and print it
     */
    public static void json(@Nullable String json) {
        com.orhanobut.logger.Logger.json(json);
    }

    /**
     * Formats the given xml content and print it
     */
    public static void xml(@Nullable String xml) {
        com.orhanobut.logger.Logger.d(xml);
    }

}
