package com.pax.pay.ui.def.base;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pax.pay.ui.def.R;
import com.pax.pay.ui.def.fragment.ExpandFragment;
import com.paxus.view.BaseAppCompatActivity;

import java.util.Observable;
import java.util.Observer;

public abstract class BaseLandActivity extends BaseAppCompatActivity implements IExpandableLayoutListener {

    private static final String EXPANDED_PANE = "EXPANDED_PANE";
    protected boolean needToolBar = true;
    private String expandedPaneTag = null;
    private Observable observableDelegate = new Observable() {
        @Override
        public void notifyObservers() {
            setChanged();
            super.notifyObservers();
        }

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(arg);
        }
    };

    protected boolean needDefaultToolbar() {
        return needToolBar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewInternal(getLayoutId());
    }

    private void setContentViewInternal(@LayoutRes int layoutResID) {
        ViewGroup targetView = null;
        if (this instanceof IExpandableLayoutListener) {
            setContentView(R.layout.activity_expandable);
            ViewGroup rootView = findViewById(R.id.info_container);
            if (needDefaultToolbar()) {
                View viewWithBar = LayoutInflater.from(this).inflate(R.layout.activity_base_default, rootView);
                targetView = viewWithBar.findViewById(R.id.root_layout);
            } else {
                targetView = rootView;
            }
        } else {
            if (needDefaultToolbar()) {
                setContentView(R.layout.activity_base_default);
                targetView = findViewById(R.id.root_layout);
            }
        }

        if (targetView != null)
            LayoutInflater.from(this).inflate(layoutResID, targetView);
        else
            setContentView(layoutResID);
    }

    /**
     * get layout file ID
     *
     * @return layout id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    protected void loadExpandedView(Bundle savedInstanceState) {

        addExpandedView(savedInstanceState);
        //FIXME: Aries serial workaround, should handle rotation change
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        if (height > width) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this instanceof IExpandableLayoutListener)
            outState.putString(EXPANDED_PANE, expandedPaneTag);
    }

    @Override
    public Fragment onCreateExpandedPane() {
        return new ExpandFragment();
    }

    private void addExpandedView(Bundle savedInstanceState) {
        if (this instanceof IExpandableLayoutListener) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            String currExpandedPaneTag = null;
            if (savedInstanceState != null) {
                currExpandedPaneTag = savedInstanceState.getString(EXPANDED_PANE);
            }

            Fragment expandedPane = currExpandedPaneTag == null ?
                    ((IExpandableLayoutListener) this).onCreateExpandedPane() :
                    manager.findFragmentByTag(currExpandedPaneTag);

            if (expandedPane instanceof Observer) {
                Observer observer = (Observer) expandedPane;
                observableDelegate.deleteObserver(observer);
                observableDelegate.addObserver(observer);
            }

            View expandedView = findViewById(R.id.expanded_info_container);
            if (expandedPane != null) {
                expandedPaneTag = expandedPane.getClass().getSimpleName();
                transaction.replace(R.id.expanded_info_container,
                        expandedPane, expandedPaneTag);
                DisplayMetrics dm = getResources().getDisplayMetrics();
                int width = dm.widthPixels;
                int height = dm.heightPixels;
                float density = dm.density;
                int swDp = (int)(Math.min(width, height) / density);
//                Logger.d("SW DP:" + swDp); //Fix ANFDRC-1161, A30-sw360, Aries6-sw640
                boolean showExpanded = swDp >= 500 && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
                expandedView.setVisibility(showExpanded? View.VISIBLE : View.GONE);
                transaction.commitAllowingStateLoss();
            } else {
                expandedView.setVisibility(View.GONE);
            }

        }
    }

    protected void addObserver(Observer o) {
        observableDelegate.addObserver(o);
    }

    protected void notifyObservers() {
        observableDelegate.notifyObservers();
    }

    protected void notifyObservers(Object arg) {
        observableDelegate.notifyObservers(arg);
    }


}
