package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.paxus.view.R;
import com.paxus.view.preferenceview.LimitValueEditTextPreference;
import com.paxus.view.utils.TextValueWatcher;
import com.paxus.view.utils.ToastHelper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


/**
 * Created by Mingfei Tan on 7/30/2021.
 */

public class MultiPrecisionPercentEditTextPreference extends LimitValueEditTextPreference {

    private static final String TAG = "MultiPrecision";
    private int decimalPrecision = 3;
    private float minFloat;
    private float maxFloat;

    public MultiPrecisionPercentEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MultiPrecisionPercentEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MultiPrecisionPercentEditTextPreference(Context context) {
        this(context, null);
    }

    public int getDecimalPrecision() {
        return decimalPrecision;
    }

    public void setDecimalPrecision(int decimalPlacement) {
        decimalPrecision = decimalPlacement;
        maxFloat = (float) (10.0 - Math.pow(0.1, decimalPrecision));

    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LimitValueEditText);
        maxFloat = typedArray.getFloat(R.styleable.LimitValueEditText_maxValue, -1);
        minFloat = typedArray.getFloat(R.styleable.LimitValueEditText_minValue, -1);
        minFloat = Math.max(minFloat, 0);
        setDecimalPrecision(decimalPrecision);
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

    private static String formatFloat(float value, int decimalPrecision) {
        StringBuilder builder = new StringBuilder("0.");
        for (int i = 0; i < decimalPrecision; i++) {
            builder.append("0");
        }
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);//French format "0,000" is invalid for Floar.parseFloat
        decimalFormat.applyPattern(builder.toString());
        return decimalFormat.format(value);
    }
    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        setText(restoreValue ? getPersistedFloat(0.0f) : (Float) defaultValue);
    }

    protected void setText(Float value) {
        float var = value != null ? value : 0.0f;
        setText(formatFloat(var, decimalPrecision)); //Fix ANFDRC-648
    }

    @Override
    protected boolean persistString(String value) {
        if (!shouldPersist()) {
            return false;
        }

        float v = Float.parseFloat(value);

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
        b.putInt(PercentValuePref.FLAG_PRECISION, decimalPrecision);
        return super.setDialogFragmentCompatBundle(b);
    }

    public static class PercentValuePref extends LimitValuePref {
        public static final String FLAG_MAX_FLOAT = "max_float";
        public static final String FLAG_MIN_FLOAT = "min_float";
        public static final String FLAG_PRECISION = "precision";

        private float mMinFloat;
        private float mMaxFloat;
        private int precision;

        @Override
        protected void onBindDialogView(View view) {
            mMinFloat = getArguments().getFloat(FLAG_MIN_FLOAT, -1);
            mMaxFloat = getArguments().getFloat(FLAG_MAX_FLOAT, -1);
            precision = getArguments().getInt(FLAG_PRECISION, -1);
            super.onBindDialogView(view);
        }

        @Override
        protected TextValueWatcher genTextWatcher() {
            Log.d(TAG, "mMaxFloat:" + mMaxFloat);
            if ((mMaxFloat == -1 && mMinFloat == -1) || mMaxFloat < mMinFloat) {
                return null;
            }

            TextValueWatcher<Float> textValueWatcher = new FloatTextValueWatcher(mMinFloat, mMaxFloat, precision);
            textValueWatcher.setOnErrorListener((value, max) ->
                    ToastHelper.showMessage(getContext(), getString(R.string.notice_percent_value_is_out_of_range_2, formatFloat(0.0f, precision), formatFloat(max, precision))));
            return textValueWatcher;
        }

        @Override
        public boolean onEditorAction(@NonNull String str, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                float value = 0;
                try {
                    value = Float.parseFloat(str);
                } catch (NumberFormatException e) {
                    Log.d(TAG, "", e);
                }
                if (value >= mMinFloat && value <= mMaxFloat)
                    return true;
                else {
                    ToastHelper.showMessage(getContext(), getString(R.string.notice_percent_value_is_out_of_range_2,
                            formatFloat(mMinFloat, precision), formatFloat(mMaxFloat, precision)));
                }
            }
            return false;
        }

        @Override
        protected String updateDefaultValue() {
            float val = 0.0f;
            SharedPreferences sp = getPreference().getSharedPreferences();
            PreferenceDataStore pds = getPreference().getPreferenceDataStore();
            if(sp != null)
                val = sp.getFloat(getPreference().getKey(), 0.0f);
            else if(pds != null)
                val = pds.getFloat(getPreference().getKey(), 0.0f);

            return formatFloat(val, precision); //Fix ANFDRC-648
        }

        @Override
        protected void setExtendedParam(EditText editText) {
            super.setExtendedParam(editText);
            editText.setSelectAllOnFocus(false);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.setKeyListener(DigitsKeyListener.getInstance("1234567890."));
        }
    }

    public static class FloatTextValueWatcher extends TextValueWatcher<Float> {
        private int precision;
        private float minValue;

        public FloatTextValueWatcher(float minValue, float maxValue, int decimalPrecision) {
            mEditing = false;
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.precision = decimalPrecision;

            setOnCompareListener((value, max) -> {
                float fValue = 0;
                try {
                    fValue = Float.parseFloat(value);
                } catch (NumberFormatException e) {
                    Log.d(TAG, "", e);
                }
                return fValue <= max && fValue >= minValue;
            });
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!mEditing) {
                mEditing = true;
                String temp = s.toString();
                try {
                    //Fix ANBP-289
                    //Yanina: Fix input issues when cursor is not on end
                    float curr = (float) (Long.parseLong(temp.replaceAll("[^0-9]", "")) * Math.pow(0.1, precision));
                    String currStr = formatFloat(curr, precision);
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
    }

}
