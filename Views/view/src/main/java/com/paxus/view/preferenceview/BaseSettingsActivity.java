package com.paxus.view.preferenceview;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.paxus.view.BaseActivity;
import com.paxus.view.R;

import java.util.List;

/**
 * Created by Sim.G on 2017/4/13 10:07
 * <p>
 * 2 'back' buttons(system back, back on action bar) have different logic by design,
 * system back will return the to the previous page, and the back on action bar providers a shortcut to exit setting
 */
public abstract class BaseSettingsActivity extends BaseActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    private static final String KEY_INDEX = "index";

    private boolean mIsTablet;
    private int currIdx = 0;

    public static boolean isScreenOrientationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    protected abstract Class<? extends BaseSettingHeadersFragment> getHeaderFragmentClass();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            currIdx = savedInstanceState.getInt(KEY_INDEX, 0);
        } else {
            currIdx = getIntent().getIntExtra(KEY_INDEX, 0);
        }
        mIsTablet = !isScreenOrientationPortrait(this);
        final FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.setting_headers_fragment, BaseSettingHeadersFragment.newInstance(getApplicationContext(), getHeaderFragmentClass(), mIsTablet, navTitle, currIdx))
                .commit();
        manager.addOnBackStackChangedListener(() -> {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            if(fragments != null && fragments.size()>0){//Fix ANFDRC-710
                fragments.get(fragments.size()-1).onResume();
            }
            if (getSupportFragmentManager().getBackStackEntryCount() < 1) {
                setTitle(navTitle);
                //When there's empty on stack we just leave the settings, it effects only on tablet version.
                if (mIsTablet) {
                    ActivityCompat.finishAfterTransition(BaseSettingsActivity.this);
                }
            }
        });
    }

    @Override
    protected void initViews() {
        //do nothing
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, currIdx);
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat preferenceFragmentCompat, Preference preference) {
        FragmentManager manager = getSupportFragmentManager();

        Fragment contentFrag = Fragment.instantiate(this, preference.getFragment(), preference.getExtras());
        Fragment headersFrag = manager.findFragmentById(R.id.setting_headers_fragment);
        if (manager.findFragmentByTag(contentFrag.getClass().getName()) != null) {
            contentFrag.setTargetFragment(headersFrag, 0);
        }
        currIdx = preference.getOrder();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.settings_fragment_container, contentFrag, contentFrag.getClass().getName());
        if (!mIsTablet || preferenceFragmentCompat instanceof BaseSettingHeadersFragment) {
            //Phone needs back-stack, otherwise it'd be strange to exit "setting" after clicking "up" or "back" button.
            transaction.addToBackStack(null);
        } else {
            transaction.addToBackStack(preferenceFragmentCompat.getClass().getName());
        }
        transaction.commitAllowingStateLoss();
        return true;
    }

    @Override
    protected boolean onKeyBackDown() {
        if (!mIsTablet) {
            //For phone it is easy return to leave from setting.
            super.onKeyBackDown();
        } else {
            //Because tablet version stacks fragments by selecting different outline items,
            //in order to ease users we need quick leaving from setting.
            ActivityCompat.finishAfterTransition(this);
        }
        return true;
    }
}
