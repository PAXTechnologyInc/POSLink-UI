/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2016 - ? Pax Corporation. All rights reserved.
 * Module Date: 2016-12-1
 * Module Author: Steven.W
 * Description:
 *
 * ============================================================================
 */
package com.pax.pay.ui.def.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.pax.pay.ui.def.R;
import com.paxus.view.widget.keyboard.PaxKeyboardView;

public class InputSecurityDialog extends Dialog {

    private String title;

    private EditText pwdEdt;
    private int maxLength;
    private View convertView;


    //private OnPwdListener listener;

    public InputSecurityDialog(Context context, int length, String title) {
        this(context, R.style.PaxTheme_PopupDialog);
        this.maxLength = length;
        this.title = title;
    }

    public InputSecurityDialog(Context context, int theme) {
        super(context, theme);

    }


    public View getConvertView() {
        return convertView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        convertView = getLayoutInflater().inflate(R.layout.activity_inner_pwd_layout, null);
        setContentView(convertView);
        if (getWindow() == null) {
            return;
        }
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);
        initViews(convertView);
    }

    private void initViews(View view) {

        TextView titleTv = view.findViewById(R.id.prompt_title);
        titleTv.setText(title);

//        TextView subtitleTv = view.findViewById(R.id.prompt_no_pwd);
//        if (prompt != null) {
//            subtitleTv.setText(prompt);
//        } else {
//            subtitleTv.setVisibility(View.GONE);
//        }

        TextView pwdTv = view.findViewById(R.id.pwd_input_text);
        pwdTv.setVisibility(View.GONE);

        pwdEdt = view.findViewById(R.id.pwd_input_et);
        pwdEdt.setEnabled(false);
        pwdEdt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});

        //final Keyboard keyboard = new Keyboard(view.getContext(), R.xml.pax_alpha_keyboard);

        PaxKeyboardView keyboardView = (PaxKeyboardView) view.findViewById(R.id.pwd_keyboard);
        keyboardView.setInputType(PaxKeyboardView.TYPE_CLASS_TEXT);
        keyboardView.bindEditText(new EditText[]{pwdEdt});
        //KeyboardUtils.bind(keyboardView, new KeyboardUtils(view.getContext(), keyboard, pwdEdt));
        //set keyboardView onKey action to do nothing
//        keyboardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
//            @Override
//            public void onPress(int i) {
//
//            }
//
//            @Override
//            public void onRelease(int i) {
//
//            }
//
//            @Override
//            public void onKey(int i, int[] ints) {
//
//            }
//
//            @Override
//            public void onText(CharSequence charSequence) {
//
//            }
//
//            @Override
//            public void swipeLeft() {
//
//            }
//
//            @Override
//            public void swipeRight() {
//
//            }
//
//            @Override
//            public void swipeDown() {
//
//            }
//
//            @Override
//            public void swipeUp() {
//
//            }
//        });
        //pwdEdt.setFocusable(true);

    }

}
