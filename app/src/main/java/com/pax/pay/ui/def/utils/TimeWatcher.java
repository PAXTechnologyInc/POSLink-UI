package com.pax.pay.ui.def.utils;

import android.text.Editable;
import android.text.TextWatcher;


/**
 * Created by Kim.L on 2018/8/16.
 */
public class TimeWatcher implements TextWatcher {

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

            s.replace(0, s.length(), replace(s.toString()));

            mEditing = false;
        }
    }

    private boolean isValid(String date) {
        if (!date.matches("[0-9]{1,6}"))
            return false;

        if(!date.matches("^(([0-1][0-9]|2[0-3])(\\d{0,2})(\\d{0,2}))|[0-2]"))
            return false;

        if (date.length() > 2) {
            if (!date.substring(2).matches("^(([0-5][0-9])(\\d{0,2}))|[0-5]")) {
                return false;
            }
        }
        if (date.length() > 4) {
            return date.substring(4).matches("^([0-5][0-9])|[0-5]");
        }
        return true;
    }

    private String replace(String s) {
        String exp = s.replace(":", "");

        if (!isValid(exp) && exp.length() > 0) {
            exp = exp.substring(0, exp.length() - 1);
        }
        if (exp.length() > 4) {
            exp = exp.replaceAll("(\\d{2})(\\d{2})(?!$)", "$1:$2:");
        } else {
            exp = exp.replaceAll("(\\d{2})(?!$)", "$1:");
        }
        return exp;
    }
}
