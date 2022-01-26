package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;

/**
 * Created by Kim.L on 3/16/2018.
 */

public class NumberListPreference extends ListPreferenceFix {

    public NumberListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NumberListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberListPreference(Context context) {
        this(context, null);
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        setValue(restoreValue ? getPersistedLong(0L) : (Long) defaultValue);
    }

    protected void setValue(Long value) {
        long var = value != null ? value : 0L;
        setValue(Long.toString(var));
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
}
