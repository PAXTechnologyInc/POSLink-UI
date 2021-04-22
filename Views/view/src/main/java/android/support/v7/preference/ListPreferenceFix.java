package android.support.v7.preference;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Kim.L on 3/16/2018.
 */

public abstract class ListPreferenceFix extends ListPreference {

    public ListPreferenceFix(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ListPreferenceFix(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListPreferenceFix(Context context) {
        this(context, null);
    }

    @Override
    public void onAttached() {
        super.onAttached();
        int index = findIndexOfValue(getValue());
        // Set the summary to reflect the new value.
        setSummary(index >= 0 ? getEntries()[index] : null);
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);
        int index = findIndexOfValue(getValue());
        // Set the summary to reflect the new value.
        setSummary(index >= 0 ? getEntries()[index] : null);
    }
}
