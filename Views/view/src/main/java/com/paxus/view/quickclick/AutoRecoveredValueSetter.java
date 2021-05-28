package com.paxus.view.quickclick;

import android.os.SystemClock;

/**
 * auto recovered value for quick click protection
 *
 * @author Steven.W
 */
class AutoRecoveredValueSetter<T> {

    private T value;
    private T recoveredTo;
    private long timeoutMs;

    protected T getValue() {
        return value;
    }

    protected void setValue(T value) {
        this.value = value;
    }

    void setRecoverTo(T value) {
        this.recoveredTo = value;
    }

    void setTimeoutMs(long timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    void recover() {
        this.value = recoveredTo;
    }

    void autoRecover() {
        new Thread(() -> {
            SystemClock.sleep(timeoutMs);
            setValue(recoveredTo);
        }).start();
    }

}
