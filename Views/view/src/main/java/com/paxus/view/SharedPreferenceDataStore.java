package com.paxus.view;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceDataStore;

import java.util.Set;

/**
 * Created by Kim.L 10/8/21
 * Delegate SharedPreferences as a PreferenceDataStore
 * for replacing default data store(SharedPreferences) in {@link androidx.preference.PreferenceManager}
 * by other data store, such as mmkv, sqlite.
 * Reference chain: PreferenceManager->PreferenceDataStore->SharedPreferenceDataStore->
 * SharedPreferences->MMKVSharedPreferences->MMKV
 */
public class SharedPreferenceDataStore extends PreferenceDataStore {
    private final SharedPreferences mDelegate;

    public SharedPreferenceDataStore(SharedPreferences sp) {
        this.mDelegate = sp;
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void putString(String key, @Nullable String value) {
        mDelegate.edit().putString(key, value).commit();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void putStringSet(String key, @Nullable Set<String> values) {
        mDelegate.edit().putStringSet(key, values).commit();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void putInt(String key, int value) {
        mDelegate.edit().putInt(key, value).commit();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void putLong(String key, long value) {
        mDelegate.edit().putLong(key, value).commit();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void putFloat(String key, float value) {
        mDelegate.edit().putFloat(key, value).commit();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void putBoolean(String key, boolean value) {
        mDelegate.edit().putBoolean(key, value).commit();
    }

    @Nullable
    @Override
    public String getString(String key, @Nullable String defValue) {
        return mDelegate.getString(key, defValue);
    }

    @Nullable
    @Override
    public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
        return mDelegate.getStringSet(key, defValues);
    }

    @Override
    public int getInt(String key, int defValue) {
        return mDelegate.getInt(key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return mDelegate.getLong(key, defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return mDelegate.getFloat(key, defValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return mDelegate.getBoolean(key, defValue);
    }
}
