package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.text.InputType;
import android.text.TextUtils;
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


/**
 * Created by linhb on 1/9/2018.
 */

public class NumberEditTextPreference extends LimitValueEditTextPreference {

    private static final String TAG = "NumberEditTextPref";

    protected long minLong;
    protected long maxLong;
    protected String nickName;

    public NumberEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public NumberEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NumberEditTextPreference(Context context) {
        this(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LimitValueEditText);
        maxLong = typedArray.getInteger(R.styleable.LimitValueEditText_maxValue, -1);
        minLong = typedArray.getInteger(R.styleable.LimitValueEditText_minValue, -1);
        nickName = typedArray.getString(R.styleable.LimitValueEditText_nickName);

        typedArray.recycle();
    }

    public void setMaxValue(long maxLong) {
        this.maxLong = maxLong;
    }

    public void setMinValue(long minLong) {
        this.minLong = minLong;
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        setText(restoreValue ? getPersistedLong(0L) : (defaultValue instanceof Long) ? (Long) defaultValue : Long.parseLong((String) defaultValue));
    }

    protected void setText(Long value) {
        long var = value != null ? value : 0L;
        setText(Long.toString(var));
    }

    @Override
    protected boolean persistString(String value) {
        if (!shouldPersist()) {
            return false;
        }

        long v = Long.parseLong(value);

        // Shouldn't store 0L
        if (v == getPersistedLong(0L)) {
            // It's already there, so the same as persisting
            return true;
        }

        PreferenceDataStore dataStore = getPreferenceDataStore();
        if (dataStore != null) {
            dataStore.putLong(getKey(), v);
        } else {
            SharedPreferences.Editor editor = getPreferenceManager().getEditor();
            editor.putLong(getKey(), v);
            if (getPreferenceManager().shouldCommit()) {
                editor.apply();
            }
        }
        return true;
    }

    @Override
    protected Class<? extends LimitValuePref> getDialogFragmentCompatClass() {
        return NumberValuePref.class;
    }

    @Override
    protected Bundle setDialogFragmentCompatBundle(Bundle b) {
        b.putLong(NumberValuePref.FLAG_MIN_LONG, minLong);
        b.putLong(NumberValuePref.FLAG_MAX_LONG, maxLong);
        b.putString(NumberValuePref.FLAG_NICK_NAME, nickName);

        return super.setDialogFragmentCompatBundle(b);
    }

    public static class NumberValuePref extends LimitValuePref {
        public static final String FLAG_MAX_LONG = "max_long";
        public static final String FLAG_MIN_LONG = "min_long";
        public static final String FLAG_NICK_NAME = "nick_name";

        protected long mMinLong;
        protected long mMaxLong;
        protected String mNickName;

        private static long parseLong(String str, long def) {
            long value = def;
            try {
                value = Long.parseLong(str);
            } catch (NumberFormatException e) {
                Log.d(TAG, "", e);
            }
            return value;
        }

        @Override
        protected void onBindDialogView(View view) {
            mMinLong = getArguments().getLong(FLAG_MIN_LONG, -1);
            mMaxLong = getArguments().getLong(FLAG_MAX_LONG, -1);
            mNickName = getArguments().getString(FLAG_NICK_NAME);
            super.onBindDialogView(view);
        }

        @Override
        public boolean onEditorAction(@NonNull String str, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                long value = parseLong(str, 0);
                if (value >= Math.max(mMinLong, 0))
                    return true;
                else {
                    showErr(Math.min(mMaxLong, Long.MAX_VALUE));
                }
            }
            return false;
        }

        @Override
        protected TextValueWatcher genTextWatcher() {
            if ((mMaxLong == -1 && mMinLong == -1) || mMaxLong < mMinLong) {
                return super.genTextWatcher();
            }
            TextValueWatcher<Long> textValueWatcher = new TextValueWatcher<>(Math.min(mMaxLong, Long.MAX_VALUE));
            textValueWatcher.setOnCompareListener((value, max) -> {
                long temp = parseLong(value, 0);
                boolean isOk = temp <= max;
                if (mMaxLength != -1) {
                    isOk &= (value.length() <= mMaxLength);
                }
                return isOk;
            });
            textValueWatcher.setOnErrorListener((value, max) -> showErr(max));
            return textValueWatcher;
        }

        protected final void showErr(long max) {
            if (mMaxLength != -1) {
                if (mMinLong != 0) {
                    ToastHelper.showMessage(getContext(), getString(R.string.notice_length_is_out_of_range_3, Math.max(mMinLong, 0), mMaxLength));
                } else {
                    ToastHelper.showMessage(getContext(), getString(R.string.notice_length_is_out_of_range_4, mMaxLength));
                }
            } else if(!TextUtils.isEmpty(mNickName)){
                ToastHelper.showMessage(getContext(), getString(R.string.notice_is_out_of_range_new, mNickName, Math.max(mMinLong, 0), max)); //Fix ANFDRC-871
            }else {
                ToastHelper.showMessage(getContext(), getString(R.string.notice_is_out_of_range, "value", Math.max(mMinLong, 0), max)); //Fix ANFDRC-438
            }
        }

        @Override
        protected void setExtendedParam(EditText editText) {
            super.setExtendedParam(editText);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }

        @Override
        protected String updateDefaultValue() {
            long val = 0L;
            SharedPreferences sp = getPreference().getSharedPreferences();
            PreferenceDataStore pds = getPreference().getPreferenceDataStore();
            if(sp != null)
                val = sp.getLong(getPreference().getKey(), 0L);
            else if(pds != null)
                val = pds.getLong(getPreference().getKey(), 0L);
            return Long.toString(val);
        }
    }
}
