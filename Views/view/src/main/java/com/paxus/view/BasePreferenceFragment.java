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

import android.annotation.SuppressLint;

import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.XmlRes;
import androidx.preference.PreferenceDataStore;
import androidx.preference.SwitchPreference;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.res.ResourcesCompat;
import androidx.preference.CheckBoxPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.EditTextPreferenceDialogFragmentCompat;
import androidx.preference.ListPreference;
import androidx.preference.ListPreferenceDialogFragmentCompat;
import androidx.preference.MultiSelectListPreferenceDialogFragmentCompat;
import androidx.preference.NumberEditTextPreference;
import androidx.preference.PercentEditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.internal.AbstractMultiSelectListPreference;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.View;

import com.paxus.view.preferenceview.IEditTextPrefFragListener;
import com.paxus.view.preferenceview.TimePickerPreference;
import com.paxus.view.preferenceview.TimePickerPreferenceDialogFragmentCompat;
import com.paxus.view.utils.ViewUtils;

import java.util.Set;

public abstract class BasePreferenceFragment extends PreferenceFragmentCompat {
    private boolean isFirst = true;
    private final String sharedPreferencesName;

    protected static final String PREFERENCES_TAG = "android:preferences";

    protected static final String DIALOG_FRAGMENT_TAG =
            "android.support.v7.preference.PreferenceFragment.DIALOG";

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
        changeToPreferenceDataStore(sharedPreferencesName);
        addPreferencesFromResource(getResourceId());
        isFirst = true;
        initPreference();
        isFirst = false;
    }

    protected void initPreference() {

    }

    /**
     * PreferenceManager get preferences from SharedPreferences by default,
     * call {@link androidx.preference.PreferenceManager#setPreferenceDataStore(PreferenceDataStore)}
     * to use DataStore
     * Notice that getPreferenceManager().getSharedPreferences() will return null if DataStore is set,
     * so use
     * {@link #getString(String, String)},
     * {@link #getBoolean(String, boolean)},
     * {@link #getInt(String, int)},
     * {@link #getLong(String, long)},
     * {@link #getStringSet(String, Set)} instead
     */
    protected void changeToPreferenceDataStore(@Nullable String storeName) {

    }

    protected void bindPreference(Preference preference) {
        if (preference == null) {
            return;
        }
        preference.setOnPreferenceChangeListener(listener);
        if (preference instanceof SwitchPreference || preference instanceof CheckBoxPreference) {
            listener.onPreferenceChange(preference, getBoolean(preference.getKey(), false));
        } else if (preference instanceof NumberEditTextPreference) {
            listener.onPreferenceChange(preference, getLong(preference.getKey(), 0L));
        } else if (preference instanceof PercentEditTextPreference) {
            listener.onPreferenceChange(preference, getFloat(preference.getKey(), 0.0f));
        } else {
            listener.onPreferenceChange(preference, getString(preference.getKey(), ""));
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
            listener.onPreferenceChange(preference, getString(preference.getKey(), ""));
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

    @SuppressLint("RestrictedApi")
    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        if (!enableToDisplayDialog(preference)) {
            return;
        }

        // Try if the preference is one of our custom Preferences
        DialogFragment dialogFragment = null;
        if (preference instanceof IEditTextPrefFragListener) {
            // Create a new instance of TimePreferenceDialogFragment with the key of the related
            // Preference
            dialogFragment = ((IEditTextPrefFragListener) preference).onNewInstance(preference.getKey());
        } else if (preference instanceof TimePickerPreference) {
            dialogFragment = TimePickerPreferenceDialogFragmentCompat.newInstance(preference.getKey());
        }

        if(dialogFragment == null) {
            // the following code is super.onDisplayPreferenceDialog(preference);
            boolean handled = false;
            if (getCallbackFragment() instanceof OnPreferenceDisplayDialogCallback) {
                handled = ((OnPreferenceDisplayDialogCallback) getCallbackFragment())
                        .onPreferenceDisplayDialog(this, preference);
            }
            if (!handled && getActivity() instanceof OnPreferenceDisplayDialogCallback) {
                handled = ((OnPreferenceDisplayDialogCallback) getActivity())
                        .onPreferenceDisplayDialog(this, preference);
            }

            if (handled) {
                return;
            }

            // check if dialog is already showing
            if (getFragmentManager().findFragmentByTag(DIALOG_FRAGMENT_TAG) != null) {
                return;
            }

            if (preference instanceof EditTextPreference) {
                dialogFragment = EditTextPreferenceDialogFragmentCompat.newInstance(preference.getKey());
            } else if (preference instanceof ListPreference) {
                dialogFragment = ListPreferenceDialogFragmentCompat.newInstance(preference.getKey());
            } else if (preference instanceof AbstractMultiSelectListPreference) {
                dialogFragment = MultiSelectListPreferenceDialogFragmentCompat.newInstance(preference.getKey());
            } else {
                throw new IllegalArgumentException("Tried to display dialog for unknown " +
                        "preference type. Did you forget to override onDisplayPreferenceDialog()?");
            }
        }

        dialogFragment.getLifecycle().addObserver(new GenericLifecycleObserver() {
            @Override
            public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                if(Lifecycle.Event.ON_DESTROY.equals(event)) {
                    source.getLifecycle().removeObserver(this);
                    // it's required on Android 5.x
                    if(ViewUtils.canNavigationBarImmersiveSticky()) {
                        ViewUtils.hideNavigationBar(requireActivity().getWindow().getDecorView());
                    }
                }
            }
        });

        dialogFragment.setTargetFragment(this, 0);
        dialogFragment.show(this.getFragmentManager(), DIALOG_FRAGMENT_TAG);
    }

    @XmlRes
    protected abstract int getResourceId();

    /**
     * for cases like "Batch out please" ...
     */
    protected boolean enableToDisplayDialog(Preference preference) {
        return true;
    }

    /**
     * Retrieves a {@link String} value from the data store.
     *
     * @param key the name of the preference to retrieve
     * @param defValue value to return if this preference does not exist in the storage
     * @return the value from the data store or the default return value
     */
    @Nullable
    public final String getString(String key, @Nullable String defValue) {
        SharedPreferences sp = getPreferenceManager().getSharedPreferences();
        PreferenceDataStore pds = getPreferenceManager().getPreferenceDataStore();
        if(sp != null)
            return sp.getString(key, defValue);
        else if(pds != null) {
            return pds.getString(key, defValue);
        }
        return defValue;
    }

    /**
     * Retrieves a set of Strings from the data store.
     *
     * @param key the name of the preference to retrieve
     * @param defValues values to return if this preference does not exist in the storage
     * @return the values from the data store or the default return values
     */
    @Nullable
    public final Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
        SharedPreferences sp = getPreferenceManager().getSharedPreferences();
        PreferenceDataStore pds = getPreferenceManager().getPreferenceDataStore();
        if(sp != null)
            return sp.getStringSet(key, defValues);
        else if(pds != null) {
            return pds.getStringSet(key, defValues);
        }
        return defValues;
    }

    /**
     * Retrieves an {@link Integer} value from the data store.
     *
     * @param key the name of the preference to retrieve
     * @param defValue value to return if this preference does not exist in the storage
     * @return the value from the data store or the default return value
     */
    public final int getInt(String key, int defValue) {
        SharedPreferences sp = getPreferenceManager().getSharedPreferences();
        PreferenceDataStore pds = getPreferenceManager().getPreferenceDataStore();
        if(sp != null)
            return sp.getInt(key, defValue);
        else if(pds != null) {
            return pds.getInt(key, defValue);
        }
        return defValue;
    }

    /**
     * Retrieves a {@link Long} value from the data store.
     *
     * @param key the name of the preference to retrieve
     * @param defValue value to return if this preference does not exist in the storage
     * @return the value from the data store or the default return value
     */
    public final long getLong(String key, long defValue) {
        SharedPreferences sp = getPreferenceManager().getSharedPreferences();
        PreferenceDataStore pds = getPreferenceManager().getPreferenceDataStore();
        if(sp != null)
            return sp.getLong(key, defValue);
        else if(pds != null) {
            return pds.getLong(key, defValue);
        }
        return defValue;
    }

    /**
     * Retrieves a {@link Float} value from the data store.
     *
     * @param key the name of the preference to retrieve
     * @param defValue value to return if this preference does not exist in the storage
     * @return the value from the data store or the default return value
     */
    public final float getFloat(String key, float defValue) {
        SharedPreferences sp = getPreferenceManager().getSharedPreferences();
        PreferenceDataStore pds = getPreferenceManager().getPreferenceDataStore();
        if(sp != null)
            return sp.getFloat(key, defValue);
        else if(pds != null) {
            return pds.getFloat(key, defValue);
        }
        return defValue;
    }

    /**
     * Retrieves a {@link Boolean} value from the data store.
     *
     * @param key the name of the preference to retrieve
     * @param defValue value to return if this preference does not exist in the storage
     * @return the value from the data store or the default return value
     * @see #getBoolean(String, boolean)
     */
    public final boolean getBoolean(String key, boolean defValue) {
        SharedPreferences sp = getPreferenceManager().getSharedPreferences();
        PreferenceDataStore pds = getPreferenceManager().getPreferenceDataStore();
        if(sp != null)
            return sp.getBoolean(key, defValue);
        else if(pds != null) {
            return pds.getBoolean(key, defValue);
        }
        return defValue;
    }
}
