package com.pax.us.pay.ui.component.keyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.pax.us.pay.ui.component.R;

public class PoslinkCustomKeyboardEditText extends CustomKeyboardEditText {

    public PoslinkCustomKeyboardEditText(Context context) {
        super(context);
    }

    public PoslinkCustomKeyboardEditText(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public PoslinkCustomKeyboardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected CustomKeyboardView loadKeyboardView(Context context){
        return (CustomKeyboardView) LayoutInflater.from(context).inflate(R.layout.poslink_custom_keyboard_view_default, null);
    }
}