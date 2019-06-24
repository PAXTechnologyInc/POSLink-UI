package com.pax.pay.ui.def_ui.utils;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.NumberKeyListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.pax.pay.ui.def_ui.R;


/**
 * Created by Kim.L on 2018/8/16.
 */
public class EnterDataLineHelper {

    // 金额
    public static void setEditTextAmount(Context context, EditText editText, int maxLen, long maxValue) {
        EnterAmountTextWatcher watcher = new EnterAmountTextWatcher(maxLen);
        if (maxValue != 0) {
            watcher.setMaxValue(maxValue);
        }

        String defaultAmount = CurrencyConverter.convert((long) 0);
        editText.setText(defaultAmount);
        editText.setSelection(editText.getText().length());
        editText.addTextChangedListener(watcher);
        editText.setKeyListener(new NumberKeyListener() {
            @Override
            protected char[] getAcceptedChars() {
                //Customer can add their accepted charter or symbol
                char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '$'};
                return chars;
            }

            @Override
            public int getInputType() {
                return 8194;  //only popup number keyboard, don't use InputType.TYPE_NUMBER_FLAG_DECIMAL(popup all text keyboard);
            }
        });

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    //after focus change then EditText get focus and move the cursor to the touch place immediately,
                    // so it should delay 50ms to set cursor to the tail.
                    editText.postDelayed(() -> editText.setSelection(editText.getText().length()), 50);
                }
                return false;
            }
        });

    }

    // 日期
    public static void setEditTextExpiry(Context context, EditText editText, int maxLen, long maxValue) {
        editText.setHint(context.getResources().getString(R.string.prompt_expiry_date_default));
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4 + 1)});
        editText.addTextChangedListener(new ExpDateWatcher());
        editText.setSelection(editText.getText().length());
        editText.setKeyListener(new NumberKeyListener() {
            @Override
            protected char[] getAcceptedChars() {
                //Customer can add their accepted charter or symbol
                char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '/', 'M', 'Y'};
                return chars;
            }

            @Override
            public int getInputType() {
                return 8194;  //only popup number keyboard, don't use InputType.TYPE_DATETIME_VARIATION_DATE(popup all text keyboard);
            }
        });
    }

    public static void setEditTextDate(Context context, EditText editText) {
        editText.setHint(context.getResources().getString(R.string.prompt_date_default));
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(editText.getHint().length())});
        editText.addTextChangedListener(new DateWatcher());
        editText.setSelection(editText.getText().length());
    }

    // 纯数字
    public static void setEditTextNumber(Context context, EditText editText, int maxLen, long maxValue) {
        if (maxLen != 0) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLen)});
        }
        if (maxLen > 0 && maxValue == 0) {
            maxValue = (long) Math.pow(10, maxLen) - 1;
        }
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setText("");
        editText.setSelection(editText.getText().length());
    }

    public static void setEditTextAllText(Context context, EditText editText, int maxLen, long maxValue) {
        if (maxLen != 0) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLen)});
        }
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        editText.setSelection(editText.getText().length());
    }

}
