/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2017 - ? Pax Corporation. All rights reserved.
 * Module Date: 2017-5-22
 * Module Author: Kim.L
 * Description:
 *
 * ============================================================================
 */
package com.paxus.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.XmlRes;
import android.support.v14.preference.SwitchPreference;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.NumberEditTextPreference;
import android.support.v7.preference.PercentEditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.paxus.view.preferenceview.IEditTextPrefFragListener;
import com.paxus.view.preferenceview.TimePickerPreference;
import com.paxus.view.preferenceview.TimePickerPreferenceDialogFragmentCompat;

public abstract class BasePreferenceFragment extends PreferenceFragmentCompat {
    private boolean isFirst = true;
    private final String sharedPreferencesName;

    public BasePreferenceFragment() {
        this(null);
    }

    public BasePreferenceFragment(String sharedPreferencesName) {
        this.sharedPreferencesName = sharedPreferencesName;
    }

    private final Preference.OnPreferenceChangeListener listener = (preference, value) -> {
        if (preference instanceof ListPreference) {
            return onListPreferenceChanged((ListPreference) preference, value, isFirst);
        } else if (preference instanceof CheckBoxPreference) {
            return onCheckBoxPreferenceChanged((CheckBoxPreference) preference, value, isFirst);
        } else if (preference instanceof SwitchPreference) {
            return onSwitchPreferenceChanged((SwitchPreference) preference, value, isFirst);
        } else if (preference instanceof EditTextPreference) {
            return onEditTextPreferenceChanged((EditTextPreference) preference, value, isFirst);
        } else if (preference instanceof TimePickerPreference) {
            return onTimePickerPreferenceChanged((TimePickerPreference) preference, value, isFirst);
        } else {
            // For all other preferences, set the summary to the value's
            // simple string representation.
            preference.setSummary(value.toString());
        }
        return true;
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.background, null));
        RecyclerView listView = getListView();
        listView.setPadding(0, 0, 0, 0);
    }

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, String rootKey) {
        if (!TextUtils.isEmpty(sharedPreferencesName)) {
            getPreferenceManager().setSharedPreferencesName(sharedPreferencesName);
            getPreferenceManager().setSharedPreferencesMode(Context.MODE_PRIVATE);
        }
        addPreferencesFromResource(getResourceId());
        isFirst = true;
        initPreference();
        isFirst = false;
    }

    protected void initPreference() {

    }

    protected void bindPreference(Preference preference) {
        if (preference == null) {
            return;
        }
        preference.setOnPreferenceChangeListener(listener);
        SharedPreferences sp = getPreferenceManager().getSharedPreferences();
        if (preference instanceof SwitchPreference || preference instanceof CheckBoxPreference) {
            listener.onPreferenceChange(preference, sp.getBoolean(preference.getKey(), false));
        } else if (preference instanceof NumberEditTextPreference) {
            listener.onPreferenceChange(preference, sp.getLong(preference.getKey(), 0L));
        } else if (preference instanceof PercentEditTextPreference) {
            listener.onPreferenceChange(preference, sp.getFloat(preference.getKey(), 0.0f));
        } else {
            listener.onPreferenceChange(preference, sp.getString(preference.getKey(), ""));
        }
    }

    protected void bindPreference(String key) {
        Preference preference = super.findPreference(key);
        bindPreference(preference);
    }

    protected void bindPreference(String key, final CharSequence[] entries,
                                  final CharSequence[] entryValues) {
        Preference preference = super.findPreference(key);
        if (preference == null) {
            return;
        }
        preference.setOnPreferenceChangeListener(listener);
        if (preference instanceof ListPreference) {
            ((ListPreference) preference).setEntries(entries);
            ((ListPreference) preference).setEntryValues(entryValues);
            listener.onPreferenceChange(preference, getPreferenceManager().getSharedPreferences().getString(preference.getKey(), ""));
        }
    }

    protected boolean onListPreferenceChanged(ListPreference preference, Object value, boolean isInitLoading) {
        int index = preference.findIndexOfValue(value.toString());
        // Set the summary to reflect the new value.
        preference.setSummary(index >= 0 ? preference.getEntries()[index] : null);
        return true;
    }

    protected boolean onCheckBoxPreferenceChanged(CheckBoxPreference preference, Object value, boolean isInitLoading) {
        preference.setSummary(value.toString());
        return true;
    }

    protected boolean onSwitchPreferenceChanged(SwitchPreference preference, Object value, boolean isInitLoading) {
        preference.setSummary(value.toString());
        return true;
    }

    protected boolean onEditTextPreferenceChanged(EditTextPreference preference, Object value, boolean isInitLoading) {
        preference.setSummary(value.toString());
        return true;
    }

    protected boolean onTimePickerPreferenceChanged(TimePickerPreference preference, Object value, boolean isInitLoading) {
        preference.setSummary(value.toString());
        return true;
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        if (!enableToDisplayDialog(preference)) {
            return;
        }

        // Try if the preference is one of our custom Preferences
        PreferenceDialogFragmentCompat dialogFragment = null;
        if (preference instanceof IEditTextPrefFragListener) {
            // Create a new instance of TimePreferenceDialogFragment with the key of the related
            // Preference
            dialogFragment = ((IEditTextPrefFragListener) preference).onNewInstance(preference.getKey());
        } else if (preference instanceof TimePickerPreference) {
            dialogFragment = TimePickerPreferenceDialogFragmentCompat.newInstance(preference.getKey());
        }

        // If it was one of our custom Preferences, show its dialog
        if (dialogFragment != null) {
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(this.getFragmentManager(),
                    "PreferenceFragment.DIALOG");
        }
        // Could not be handled here. Try with the super method.
        else {
            super.onDisplayPreferenceDialog(preference);
        }
    }

    @XmlRes
    protected abstract int getResourceId();

    /**
     * for cases like "Batch out please" ...
     */
    protected boolean enableToDisplayDialog(Preference preference) {
        return true;
    }
}
