package com.pax.pay.ui.def.utils;

import android.text.Editable;
import android.text.TextWatcher;


/**
 * Created by Kim.L on 2018/8/16.
 */
public class PhoneWatcher implements TextWatcher {

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
            String text = s.toString();
            String tmp = text.replace("(", "");
            tmp = tmp.replace(")", "");
            tmp = tmp.replace("-", "");
            if (tmp.length() > 0 && tmp.length() <= 10 && (!text.contains("(") || !text.contains(")") || !text.contains("-"))) {
                if (tmp.length() > 0  && !text.contains("(") )
                    s.insert(0, "(");
                if (tmp.length()>3 && !text.contains(")"))
                    s.insert(4, ")");
                if (tmp.length()>6 && !text.contains("-"))
                    s.insert(8, "-");
                mEditing = false;
                return;
            }
            mEditing = false;
        }
    }

}
