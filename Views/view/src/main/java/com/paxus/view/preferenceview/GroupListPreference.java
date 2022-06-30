package com.paxus.view.preferenceview;

import android.content.Context;
import android.content.res.TypedArray;

import androidx.preference.Preference;

import android.text.TextUtils;
import android.util.AttributeSet;

import com.paxus.view.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhb on 1/11/2018.
 */

public class GroupListPreference extends ListStatePreference {

    private String mMasterKey;
    private List<GroupListPreference> mSlaves;

    public GroupListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public GroupListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GroupListPreference(Context context) {
        this(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GroupPreference);
        mMasterKey = typedArray.getString(R.styleable.GroupPreference_master);

        typedArray.recycle();
    }

    public void setMasterKey(String mMasterKey) {
        this.mMasterKey = mMasterKey;
    }

    @Override
    public void onAttached() {
        super.onAttached();
        registerMaster();
    }

    @Override
    protected void onPrepareForRemoval() {
        super.onPrepareForRemoval();
        unregisterMaster();
    }

    @Override
    public void setValue(String value) {
        // Always persist/notify the first time.
        final boolean changed = getValue() != null && !TextUtils.equals(getValue(), value);
        super.setValue(value);
        if (changed) {
            notifyMasterChange();
        }
    }

    private void registerMaster() {

        if (TextUtils.isEmpty(mMasterKey)) return;

        Preference preference = findPreferenceInHierarchy(mMasterKey);
        if (preference instanceof GroupListPreference) {
            ((GroupListPreference) preference).registerSlaves(this);
        } else {
            throw new IllegalStateException("Dependency \"" + mMasterKey
                    + "\" not found for preference \"" + getKey() + "\" (title: \"" + getTitle() + "\"");
        }
    }

    private void unregisterMaster() {
        if (mMasterKey != null) {
            final GroupListPreference oldDependency = (GroupListPreference) findPreferenceInHierarchy(mMasterKey);
            if (oldDependency != null) {
                oldDependency.unregisterSlaves(this);
            }
        }
    }

    private void registerSlaves(GroupListPreference dependent) {
        if (mSlaves == null) {
            mSlaves = new ArrayList<>();
        }

        mSlaves.add(dependent);
    }

    private void unregisterSlaves(GroupListPreference dependent) {
        if (mSlaves != null) {
            mSlaves.remove(dependent);
        }
    }

    public void notifyMasterChange() {
        final List<GroupListPreference> slaves = mSlaves;

        if (slaves == null) {
            return;
        }

        for (GroupListPreference i : slaves) {
            i.onMasterChanged(this);
        }
    }

    public void onMasterChanged(GroupListPreference master) {
        // Enabled state can change dependent preferences' states, so notify
        setValue(master.getValue());
        callChangeListener(master.getValue());
    }
}
