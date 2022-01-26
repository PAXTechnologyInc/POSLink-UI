package com.paxus.view.preferenceview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.View;

import com.paxus.view.BasePreferenceFragment;
import com.paxus.view.R;


/**
 * Created by Kim.L on 4/16/2018.
 */

public abstract class BaseSettingHeadersFragment extends BasePreferenceFragment {
    protected static final String EXTRAS_TABLET_LAYOUT = "SettingHeadersFragment.EXTRAS.tablet_layout";
    protected static final String EXTRAS_TITLE = "SettingHeadersFragment.EXTRAS.title";
    protected static final String EXTRAS_INDEX = "SettingHeadersFragment.EXTRAS.index";
    private View mSelectedItem;

    protected static <T extends BaseSettingHeadersFragment> Fragment newInstance(
            Context context, @NonNull Class<T> clz, boolean isTabletLayout, String title, int index) {
        Bundle args = new Bundle();
        args.putBoolean(BaseSettingHeadersFragment.EXTRAS_TABLET_LAYOUT, isTabletLayout);
        args.putString(BaseSettingHeadersFragment.EXTRAS_TITLE, title);
        args.putInt(BaseSettingHeadersFragment.EXTRAS_INDEX, index);
        return Fragment.instantiate(context, clz.getName(), args);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean isTabletLayout = getArguments().getBoolean(EXTRAS_TABLET_LAYOUT, false);
        String title = getArguments().getString(EXTRAS_TITLE);
        int header = getArguments().getInt(EXTRAS_INDEX, 0);

        //ANDROIDNAT-285
        if (getActivity() != null && !TextUtils.isEmpty(title)) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        }

        if (isTabletLayout) {
            new Handler().postDelayed(() -> clickHeader(header), 80);
        }
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        int size = getFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < size; ++i) {
            getFragmentManager().popBackStack();
        }

        int invisibleCount = 0;
        for (int i = 0; i < preference.getParent().getPreferenceCount(); ++i) {
            Preference pref = preference.getParent().getPreference(i);
            if(!pref.isVisible()){
                invisibleCount++;
            }
            if (preference.equals(pref)) {
                selectHeader(i-invisibleCount);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(preference.getTitle());
                break;
            }
        }
        return super.onPreferenceTreeClick(preference);
    }

    private void selectHeader(int index) {
        boolean isTabletLayout = getArguments().getBoolean(EXTRAS_TABLET_LAYOUT);
        if (isTabletLayout) {
            RecyclerView listView = getListView();
            if (listView != null) {
                if (mSelectedItem != null) {
                    mSelectedItem.setSelected(false);
                }
                mSelectedItem = listView.findViewHolderForAdapterPosition(index).itemView;
                mSelectedItem.setBackgroundResource(R.drawable.bg_header);
                mSelectedItem.setSelected(true);
            }
        }
    }

    private void clickHeader(int index) {
        RecyclerView recyclerView = getListView();
        if (recyclerView != null && recyclerView.findViewHolderForAdapterPosition(index) != null && mSelectedItem != recyclerView.findViewHolderForAdapterPosition(index).itemView) {
            selectHeader(index);
            // don't preform sound
            if (mSelectedItem != null) {
                mSelectedItem.callOnClick();
            }
        }
    }


}
