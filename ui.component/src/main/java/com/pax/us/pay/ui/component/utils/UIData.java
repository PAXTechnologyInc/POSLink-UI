package com.pax.us.pay.ui.component.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.core.UIDataHandler;

public class UIData {

    public static void saveData(Context context, @Nullable Bundle bundle) {
        UIDataHandler.saveData(context, bundle);
    }

    public static void clearData(Context context){
        SharedPreferences preferences=context.getSharedPreferences(EntryRequest.class.getName(),Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
    }
}