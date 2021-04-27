package com.paxus.view.preferenceview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.preference.StringListPreference;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.paxus.view.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by linhb on 1/10/2018.
 */

public class ListStatePreference extends StringListPreference {

    private List<String> onValues = null;

    public ListStatePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ListStatePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ListStatePreference(Context context) {
        this(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ListStatePreference);
        int valuesResId = typedArray.getResourceId(R.styleable.ListStatePreference_onValues, 0);
        if (valuesResId == 0) {
            throw new IllegalArgumentException(
                    "IntegerListPreference: error - valueList is not specified");
        }

        String[] values = typedArray.getResources().getStringArray(valuesResId);
        onValues = Arrays.asList(values);
        typedArray.recycle();
    }

    @Override
    public void setValue(String value) {
        // Always persist/notify the first time.
        final boolean changed = !TextUtils.equals(getValue(), value);
        super.setValue(value);

        if (onValues == null || onValues.isEmpty()) {
            CharSequence[] entryValues = getEntryValues();
            if (entryValues != null && entryValues.length > 0) {
                onValues = new ArrayList<>();
                for (CharSequence c : entryValues) {
                    onValues.add(c.toString());
                }
            }
        }
        if (changed) {
            notifyDependencyChange(!onValues.contains(getValue()));
            notifyChanged();
        }
    }

    @Override
    public boolean shouldDisableDependents() {
        return !onValues.contains(getValue()) || super.shouldDisableDependents();
    }
}
