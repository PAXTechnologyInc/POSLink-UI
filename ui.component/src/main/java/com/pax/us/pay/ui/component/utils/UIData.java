package com.pax.us.pay.ui.component.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pax.us.pay.ui.core.UIDataHandler;

public class UIData {

    public static void saveData(Context context, @Nullable Bundle bundle) {
        UIDataHandler.saveData(context, bundle);
    }
}