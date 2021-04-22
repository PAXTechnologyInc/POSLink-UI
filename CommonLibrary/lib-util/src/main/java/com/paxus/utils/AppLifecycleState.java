package com.paxus.utils;

/**
 * Created by Kim.L 2020/05/12
 */
public class AppLifecycleState {
    private static Boolean foregrounded = null;

    public synchronized static boolean isAppForegrounded() {
        if (foregrounded == null)
            throw new IllegalStateException("Added LifecycleObserver to your Application");
        return foregrounded;
    }

    public static synchronized void setAppForegrounded(boolean foregrounded) {
        AppLifecycleState.foregrounded = foregrounded;
    }
}
