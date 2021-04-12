package android.support.v7.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.paxus.view.R;
import com.paxus.view.preferenceview.LimitValueEditTextPreference;
import com.paxus.view.utils.TextValueWatcher;
import com.paxus.view.utils.ToastHelper;

import java.util.Locale;


/**
 * Created by linhb on 1/9/2018.
 */

public class PercentEditTextPreference extends LimitValueEditTextPreference {

    private static final String TAG = "PercentEditTextPref";

    private float minFloat;
    private float maxFloat;

    public PercentEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public PercentEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PercentEditTextPreference(Context context) {
        this(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LimitValueEditText);
        maxFloat = typedArray.getFloat(R.styleable.LimitValueEditText_maxValue, -1);
        minFloat = typedArray.getFloat(R.styleable.LimitValueEditText_minValue, -1);
        minFloat = Math.max(minFloat, 0);
        typedArray.recycle();
    }

    public float getMinFloat() {
        return minFloat;
    }

    public void setMinFloat(float minFloat) {
        this.minFloat = Math.max(minFloat, 0);
    }

    public float getMaxFloat() {
        return maxFloat;
    }

    public void setMaxFloat(float maxFloat) {
        this.maxFloat = maxFloat;
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        setText(restoreValue ? getPersistedFloat(0.0f) : (Float) defaultValue);
    }

    protected void setText(Float value) {
        float var = value != null ? value : 0.0f;
        setText(String.format(Locale.US,"%.03f",var)); //Fix ANFDRC-648
    }

    @Override
    protected boolean persistString(String value) {
        if (!shouldPersist()) {
            return false;
        }

        float v = Float.parseFloat(value);

        // Shouldn't store 0L
        if (v == getPersistedFloat(0.0f)) {
            // It's already there, so the same as persisting
            return true;
        }

        PreferenceDataStore dataStore = getPreferenceDataStore();
        if (dataStore != null) {
            dataStore.putFloat(getKey(), v);
        } else {
            SharedPreferences.Editor editor = getPreferenceManager().getEditor();
            editor.putFloat(getKey(), v);
            if (getPreferenceManager().shouldCommit()) {
                editor.apply();
            }
        }
        return true;
    }

    @Override
    protected Class<? extends LimitValuePref> getDialogFragmentCompatClass() {
        return PercentValuePref.class;
    }

    @Override
    protected Bundle setDialogFragmentCompatBundle(Bundle b) {
        b.putFloat(PercentValuePref.FLAG_MIN_FLOAT, minFloat);
        b.putFloat(PercentValuePref.FLAG_MAX_FLOAT, maxFloat);
        return super.setDialogFragmentCompatBundle(b);
    }

    public static class PercentValuePref extends LimitValuePref {
        public static final String FLAG_MAX_FLOAT = "max_float";
        public static final String FLAG_MIN_FLOAT = "min_float";

        private float mMinFloat;
        private float mMaxFloat;

        @Override
        protected void onBindDialogView(View view) {
            mMinFloat = getArguments().getFloat(FLAG_MIN_FLOAT, -1);
            mMaxFloat = getArguments().getFloat(FLAG_MAX_FLOAT, -1);
            super.onBindDialogView(view);
        }

        @Override
        protected TextValueWatcher genTextWatcher() {
            if ((mMaxFloat == -1 && mMinFloat == -1) || mMaxFloat < mMinFloat) {
                return null;
            }
            TextValueWatcher<Float> textValueWatcher = new TextValueWatcher<Float>(Math.min(mMaxFloat, Long.MAX_VALUE)) {
                @Override
                public void afterTextChanged(Editable s) {
                    if (!mEditing) {
                        mEditing = true;
                        String temp = s.toString();
                        try {
                            //Fix ANBP-289
                            //Yanina: Fix input issues when cursor is not on end
                            float curr = Long.parseLong(temp.replaceAll("[^0-9]", "")) * 0.001f;
                            String currStr = String.format(Locale.US, "%.03f", curr);
                            if (compareListener != null && compareListener.onCompare(currStr, maxValue)) {
                                s.replace(0, s.length(), currStr);
                                if (textChangedListener != null)
                                    textChangedListener.afterTextChanged(temp);
                            } else {
                                s.replace(0, s.length(), mPreStr, 0, mPreStr.length());
                                if (errorListener != null)
                                    errorListener.onError(temp, maxValue);
                            }
                        } catch (NumberFormatException e) {
                            s.replace(0, s.length(), mPreStr, 0, mPreStr.length());
                        }
                        mEditing = false;
                    }
                }
            };
            textValueWatcher.setOnCompareListener((value, max) -> {
                float fValue = 0;
                try {
                    fValue = Float.parseFloat(value);
                } catch (NumberFormatException e) {
                    Log.d(TAG, "", e);
                }
                return fValue <= max;
            });
            textValueWatcher.setOnErrorListener((value, max) ->
                    ToastHelper.showMessage(getContext(), getString(R.string.notice_percent_value_is_out_of_range, mMinFloat, max)));
            return textValueWatcher;
        }

        @Override
        public boolean onEditorAction(@NonNull String str, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                double value = 0;
                try {
                    value = Double.parseDouble(str);
                } catch (NumberFormatException e) {
                    Log.d(TAG, "", e);
                }
                if (value >= mMinFloat)
                    return true;
                else {
                    ToastHelper.showMessage(getContext(), getString(R.string.notice_percent_value_is_out_of_range,
                            mMinFloat, Math.min(mMaxFloat, Long.MAX_VALUE)));
                }
            }
            return false;
        }

        @Override
        protected String updateDefaultValue() {
            return String.format(Locale.US,"%.03f",getPreference().getSharedPreferences().getFloat(getPreference().getKey(), 0.0f)); //Fix ANFDRC-648
        }

        @Override
        protected void setExtendedParam(EditText editText) {
            super.setExtendedParam(editText);
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
    }
}
