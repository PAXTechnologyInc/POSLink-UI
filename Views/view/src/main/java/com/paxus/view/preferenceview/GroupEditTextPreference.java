package com.paxus.view.preferenceview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.preference.Preference;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.paxus.view.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhb on 1/11/2018.
 */

public class GroupEditTextPreference extends EditTextPreferenceFix {

    private String mMasterKey;
    private List<GroupEditTextPreference> mSlaves;

    public GroupEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public GroupEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GroupEditTextPreference(Context context) {
        this(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GroupPreference);
        mMasterKey = typedArray.getString(R.styleable.GroupPreference_master);

        typedArray.recycle();
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
    public void setText(String text) {
        // Always persist/notify the first time.
        final boolean changed = getText() != null && !TextUtils.equals(getText(), text);
        super.setText(text);
        if (changed) {
            notifyMasterChange();
        }
    }

    private void registerMaster() {

        if (TextUtils.isEmpty(mMasterKey)) return;

        Preference preference = findPreferenceInHierarchy(mMasterKey);
        if (preference instanceof GroupEditTextPreference) {
            ((GroupEditTextPreference) preference).registerSlaves(this);
        } else {
            throw new IllegalStateException("Dependency \"" + mMasterKey
                    + "\" not found for preference \"" + getKey() + "\" (title: \"" + getTitle() + "\"");
        }
    }

    private void unregisterMaster() {
        if (mMasterKey != null) {
            final GroupEditTextPreference oldDependency = (GroupEditTextPreference) findPreferenceInHierarchy(mMasterKey);
            if (oldDependency != null) {
                oldDependency.unregisterSlaves(this);
            }
        }
    }

    private void registerSlaves(GroupEditTextPreference dependent) {
        if (mSlaves == null) {
            mSlaves = new ArrayList<>();
        }

        mSlaves.add(dependent);
    }

    private void unregisterSlaves(GroupEditTextPreference dependent) {
        if (mSlaves != null) {
            mSlaves.remove(dependent);
        }
    }

    private void notifyMasterChange() {
        final List<GroupEditTextPreference> slaves = mSlaves;

        if (slaves == null) {
            return;
        }

        for (GroupEditTextPreference i : slaves) {
            i.onMasterChanged(this);
        }
    }

    private void onMasterChanged(GroupEditTextPreference master) {
        // Enabled state can change dependent preferences' states, so notify
        setText(master.getText());
        callChangeListener(master.getText());
    }
}
