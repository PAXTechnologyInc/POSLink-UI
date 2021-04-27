package com.pax.pay.ui.def.utils;

import android.text.Editable;
import android.text.TextWatcher;


/**
 * Created by Kim.L on 2018/8/16.
 */
public class SocialSecurityWatcher implements TextWatcher {

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
        return date.matches("[0-9]{1,9}");
    }

    private String replace(String s) {
        String exp = s.replace("-", "");

        if (!isValid(exp) && exp.length() > 0) {
            exp = exp.substring(0, exp.length() - 1);
        }
        if (exp.length() > 5) {
            exp = exp.replaceAll("(\\d{3})(\\d{2})(?!$)", "$1-$2-");
        } else {
            exp = exp.replaceAll("(\\d{3})(?!$)", "$1-");
        }
        return exp;
    }
}
