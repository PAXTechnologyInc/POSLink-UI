package com.paxus.view;

import android.content.Context;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

import com.paxus.utils.LocaleUtils;

/**
 * Created by Yanina.Yang on 9/23/2021.
 */
public class BaseAppCompatActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            super.attachBaseContext(LocaleUtils.wrapContext(newBase));
        } else {
            super.attachBaseContext(newBase);
        }
    }

}
