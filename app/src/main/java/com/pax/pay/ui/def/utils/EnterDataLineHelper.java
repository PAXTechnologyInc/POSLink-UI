package com.pax.pay.ui.def.utils;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;

import com.pax.pay.ui.def.EditTextDataLimit;
import com.pax.pay.ui.def.R;
import com.pax.us.pay.ui.component.keyboard.CustomKeyboardEditText;

/**
 * Created by Kim.L on 2018/8/16.
 */
public class EnterDataLineHelper {
    private static EnterAmountTextWatcher watcher = null;
    // 金额
    public static void setEditTextAmount(Context context, CustomKeyboardEditText editText, final EditTextDataLimit limit) {
        editText.setKeyboardId(R.xml.keyboard_numeric_confirm);

        //remove the watcher for the Number Text type, if watch already be set by the previous screen
        if (watcher != null) {
            editText.removeTextChangedListener(watcher);
            //Log.i("Watch", "setEditTextAmount removeTextChangedListener watcher");
            watcher = null;
        }

        watcher = new EnterAmountTextWatcher(limit.maxLen);
        if (limit.maxValue != 0) {
            watcher.setMaxValue(limit.maxValue);
        }
        if (limit.maxLen > 0 && limit.maxValue == 0) {
            limit.maxValue = (long) Math.pow(10, limit.maxLen) - 1;
        }

        editText.setText("$0.00");
        if(editText.getText()!=null) {
            editText.setSelection(editText.getText().length());
        }
        editText.addTextChangedListener(watcher);
    }

    // 日期
    public static void setEditTextExpiry(Context context, CustomKeyboardEditText editText, final EditTextDataLimit limit) {
        editText.setHint(context.getResources().getString(R.string.prompt_expiry_date_default));
        editText.setKeyboardId(R.xml.keyboard_numeric_confirm);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4 + 1)});
        editText.addTextChangedListener(new ExpDateWatcher());
        if(editText.getText()!=null) {
            editText.setSelection(editText.getText().length());
        }
    }

    public static void setEditTextDate(Context context, CustomKeyboardEditText editText) {
        editText.setHint(context.getResources().getString(R.string.prompt_date_default));
        editText.setKeyboardId(R.xml.keyboard_numeric_confirm);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(editText.getHint().length())});
        editText.addTextChangedListener(new DateWatcher());
        if(editText.getText()!=null) {
            editText.setSelection(editText.getText().length());
        }
    }

    public static void setEditTextFormatDate(Context context,String format, CustomKeyboardEditText editText) {
        editText.setHint(format);
        editText.setKeyboardId(R.xml.keyboard_numeric_confirm);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(editText.getHint().length())});
        editText.addTextChangedListener(new DateWatcher1());
        if(editText.getText()!=null) {
            editText.setSelection(editText.getText().length());
        }
    }

    public static void setEditTextTime(Context context,String format, CustomKeyboardEditText editText) {
        editText.setHint(format);
        editText.setKeyboardId(R.xml.keyboard_numeric_confirm);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(editText.getHint().length())});
        editText.addTextChangedListener(new TimeWatcher());
        if(editText.getText()!=null) {
            editText.setSelection(editText.getText().length());
        }
    }

    public static void setEditTextPhone(Context context,CustomKeyboardEditText editText) {
        editText.setHint(context.getResources().getString(R.string.prompt_phone_xxx_xxx_xxxx));
        editText.setKeyboardId(R.xml.keyboard_numeric_confirm);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(editText.getHint().length())});
        editText.addTextChangedListener(new PhoneWatcher());
        if(editText.getText()!=null) {
            editText.setSelection(editText.getText().length());
        }
    }

    public static void setEditTextSocialSecurity(Context context,CustomKeyboardEditText editText) {
        editText.setHint(context.getResources().getString(R.string.prompt_social_security));
        editText.setKeyboardId(R.xml.keyboard_numeric_confirm);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(editText.getHint().length())});
        editText.addTextChangedListener(new SocialSecurityWatcher());
        if(editText.getText()!=null) {
            editText.setSelection(editText.getText().length());
        }
    }

    // 纯数字
    public static void setEditTextNumber(Context context, CustomKeyboardEditText editText, EditTextDataLimit limit) {
        editText.setKeyboardId(R.xml.keyboard_numeric_confirm);
        if (limit.maxLen != 0) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(limit.maxLen)});
        }
        if (limit.maxLen > 0 && limit.maxValue == 0) {
            limit.maxValue = (long) Math.pow(10, limit.maxLen) - 1;
        }
        //remove the watcher for the Number Text type, if watch already be set by the previous screen
        if (watcher != null) {
            editText.removeTextChangedListener(watcher);
            watcher = null;
        }
        editText.setText("");
        editText.setSelection(0);
    }

    public static void setEditTextAllText(Context context, CustomKeyboardEditText editText, EditTextDataLimit limit) {
        if (limit.maxLen != 0) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(limit.maxLen)});
        }
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        editText.setText("");
        editText.setSelection(0);
    }

    public static EnterAmountTextWatcher getWatcher() {
        return watcher;
    }
}
