package com.paxus.view.preferenceview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.preference.EditTextPreferenceDialogFragmentCompat;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.paxus.view.R;
import com.paxus.view.utils.TextValueWatcher;
import com.paxus.view.utils.ToastHelper;

/**
 * Created by linhb on 1/9/2018.
 */

public class LimitValueEditTextPreference extends GroupEditTextPreference implements IEditTextPrefFragListener {

    private long maxLength;
    private long minLength;

    private int inputType = 0;

    public LimitValueEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public LimitValueEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LimitValueEditTextPreference(Context context) {
        this(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LimitValueEditText);
        maxLength = typedArray.getInteger(R.styleable.LimitValueEditText_maxLen, -1);
        minLength = typedArray.getInteger(R.styleable.LimitValueEditText_minLen, -1);
        typedArray.recycle();
    }

    public long getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(long maxLength) {
        this.maxLength = maxLength;
    }

    public long getMinLength() {
        return minLength;
    }

    public void setMinLength(long minLength) {
        this.minLength = minLength;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    @Override
    public void setSummary(CharSequence summary) {
        if (summary != null) {
            int typeClass = inputType & InputType.TYPE_MASK_CLASS;
            int variation = inputType & InputType.TYPE_MASK_VARIATION;
            if ((typeClass == InputType.TYPE_CLASS_NUMBER && variation == InputType.TYPE_NUMBER_VARIATION_PASSWORD)
                    || (typeClass == InputType.TYPE_CLASS_TEXT && variation == InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                super.setSummary(summary.toString().replaceAll(".", "\u2022"));
                return;
            }
        }

        super.setSummary(summary);
    }

    @Override
    public final EditTextPreferenceDialogFragmentCompat onNewInstance(String key) {
        final Bundle b = new Bundle(4);
        return LimitValuePref.newInstance(key, getDialogFragmentCompatClass(), setDialogFragmentCompatBundle(b));
    }

    protected Class<? extends LimitValuePref> getDialogFragmentCompatClass() {
        return LimitValuePref.class;
    }

    protected Bundle setDialogFragmentCompatBundle(Bundle b) {
        b.putLong(LimitValuePref.FLAG_MIN_LEN, minLength);
        b.putLong(LimitValuePref.FLAG_MAX_LEN, maxLength);
        if (inputType != 0)
            b.putInt(LimitValuePref.FLAG_INPUT_TYPE, inputType);
        return b;
    }

    public static class LimitValuePref extends EditTextPreferenceDialogFragmentCompat implements TextView.OnEditorActionListener {
        public static final String FLAG_MAX_LEN = "max_len";
        public static final String FLAG_MIN_LEN = "min_len";
        public static final String FLAG_INPUT_TYPE = "input_type";

        protected EditText mEditText;
        protected long mMaxLength;
        protected long mMinLength;
        protected int mInputType = 0;
        private TextValueWatcher mTextWatcher;

        public static <T extends LimitValuePref> T newInstance(String key, Class<T> clz, Bundle b) {
            try {
                final T fragment = clz.newInstance();
                b.putString(ARG_KEY, key);
                fragment.setArguments(b);
                return fragment;
            } catch (java.lang.InstantiationException | IllegalAccessException e) {
                Log.e("PreferenceView", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onBindDialogView(View view) {
            super.onBindDialogView(view);
            mEditText = view.findViewById(android.R.id.edit);
            mMinLength = getArguments().getLong(FLAG_MIN_LEN, -1);
            mMaxLength = getArguments().getLong(FLAG_MAX_LEN, -1);
            mInputType = getArguments().getInt(FLAG_INPUT_TYPE, mEditText.getInputType());
            // calling setSingleLine (boolean singleLine) resets the EditText settings,
            // so you'll have to call that first and after that set custom settings like the password InputType.
            mEditText.setMaxLines(1);
            mEditText.setSingleLine();

            if (mTextWatcher != null)
                mEditText.removeTextChangedListener(mTextWatcher);
            TextValueWatcher temp = genTextWatcher();

            setExtendedParam(mEditText);

            String text = updateDefaultValue();

            mEditText.setText(text);

            if (temp != null)
                mEditText.addTextChangedListener(temp);

            mEditText.setSelection(text.length());
            mEditText.setOnEditorActionListener(this);

            //ANDROIDNAT-224
            mEditText.setOnKeyListener((v, keyCode, event) -> {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER) {
                    //workaround to get the button
                    Button positive = getDialog().findViewById(android.R.id.button1);
                    positive.performClick();
                    return true;
                }
                return false;
            });

            mTextWatcher = temp;
        }

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            return onEditorAction(v.getText().toString(), actionId, event);
        }

        public boolean onEditorAction(@NonNull String str, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (str.length() >= Math.max(mMinLength, 0))
                    return true;
                else {
                    showErr();
                }
            }
            return false;
        }

        private void showErr() {
            if (mMaxLength == mMinLength) {
                ToastHelper.showMessage(getContext(), getString(R.string.notice_value_length_is_wrong, mMinLength));
            } else if (mMaxLength == -1) {
                ToastHelper.showMessage(getContext(), getString(R.string.notice_length_is_out_of_range_2, mMinLength));
            } else {
                ToastHelper.showMessage(getContext(), getString(R.string.notice_is_out_of_range, "length",
                        Math.max(mMinLength, 0), mMaxLength));
            }
        }

        /**
         * @return Length limit Text Watcher
         */
        protected TextValueWatcher genTextWatcher() {
            if ((mMaxLength == -1 && mMinLength == -1) || mMaxLength < mMinLength) {
                return null;
            }
            TextValueWatcher<Long> textValueWatcher = new TextValueWatcher<>(Math.min(mMaxLength, Long.MAX_VALUE));
            textValueWatcher.setOnCompareListener((value, max) -> {
                long temp = value.length();
                return temp <= max;
            });
            textValueWatcher.setOnErrorListener((value, max) -> showErr());
            return textValueWatcher;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE && !onEditorAction(mEditText, EditorInfo.IME_ACTION_DONE, null)) {
                return;
            }
            super.onClick(dialog, which);
        }

        protected void setExtendedParam(EditText editText) {
            mEditText.setSelectAllOnFocus(true);
            mEditText.setInputType(mInputType);
            int typeClass = mInputType & InputType.TYPE_MASK_CLASS;
            int variation = mInputType & InputType.TYPE_MASK_VARIATION;
            if ((typeClass == InputType.TYPE_CLASS_NUMBER && variation == InputType.TYPE_NUMBER_VARIATION_PASSWORD)
                    || (typeClass == InputType.TYPE_CLASS_TEXT && variation == InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }

        protected String updateDefaultValue() {
            //do nothing
            return getPreference().getSharedPreferences().getString(getPreference().getKey(), "");
        }
    }
}
