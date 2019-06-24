package com.pax.pay.ui.def_ui.utils;

//import android.support.annotation.VisibleForTesting;

import android.support.annotation.VisibleForTesting;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.regex.Pattern;


/**
 * Created by Kim.L on 2018/8/16.
 */
public class ExpDateWatcher implements TextWatcher {

    private boolean mEditing = false;

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //do nothing
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //do nothing
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!mEditing) {
            mEditing = true;

            String exp = s.toString().replace("/", "");

            s.replace(0, s.length(), replace(exp));

            mEditing = false;
        }
    }

    @VisibleForTesting
    static String getRegex() {
        return "^([01])|(0[1-9]|1[0-2])(\\d{0,2})";
    }

    @VisibleForTesting
    static boolean isValid(String s) {
        return Pattern.compile(getRegex()).matcher(s).matches();
    }

    private String replace(String s) {
        String exp = s.replace("/", "");

        if (isValid(exp)) {
            exp = exp.replaceAll("(\\d{2})(?!$)", "$1/");
        } else if (exp.length() > 0) {
            exp = exp.substring(0, exp.length() - 1);
        }
        return exp;
    }
}
