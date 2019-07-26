package com.pax.pay.poslink.ui.demo.app;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new UIActivityLifecycleCallbacks());
    }

}
