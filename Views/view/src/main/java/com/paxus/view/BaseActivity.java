package com.paxus.view;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Method;
import java.util.Observable;
import java.util.Observer;


public abstract class BaseActivity extends BaseAppCompatActivity {

    private static final String EXPANDED_PANE = "EXPANDED_PANE";
    /**
     * 显示的抬头
     */
    protected String navTitle = "";
    /**
     * 是否显示返回按钮
     */
    protected boolean navBack = true;
    private String expandedPaneTag = null;
    private Toolbar toolbar;
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
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewInternal(getLayoutId());
        injectBinder();
        loadParam();

        addExpandedView(savedInstanceState);

        initToolbar();

        initViews(savedInstanceState);
        setListeners();

        //Lock rotation
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
                expandedView.setVisibility(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? View.VISIBLE : View.GONE);
                transaction.commitAllowingStateLoss();
            } else {
                expandedView.setVisibility(View.GONE);
            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this instanceof IExpandableLayoutListener) {
            outState.putString(EXPANDED_PANE, expandedPaneTag);
        }
    }

    private void setContentViewInternal(@LayoutRes int layoutResID) {
        ViewGroup targetView = null;
        if (this instanceof IExpandableLayoutListener) {
            setContentView(R.layout.activity_expandable);
            ViewGroup rootView = findViewById(R.id.info_container);
            if (needDefaultToolbar()) {
                View viewWithBar = LayoutInflater.from(this).inflate(R.layout.activity_base, rootView);
                targetView = viewWithBar.findViewById(R.id.root_layout);
            } else {
                targetView = rootView;
            }
        } else {
            if (needDefaultToolbar()) {
                setContentView(R.layout.activity_base);
                targetView = findViewById(R.id.root_layout);
            }
        }

        if (targetView != null) {
            LayoutInflater.from(this).inflate(layoutResID, targetView);
        } else {
            setContentView(layoutResID);
        }
    }

    /**
     * 初始化 toolbar 内容布局
     */
    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            setBackEnabled(true);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(navTitle == null ? "" : navTitle);
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(navBack);
            }
        }
    }

    protected void setBackEnabled(boolean enabled) {
        if (enabled) {
            toolbar.setNavigationOnClickListener(v -> onKeyBackDown());
        } else {
            toolbar.setNavigationOnClickListener(null);
        }
    }

    protected boolean onKeyBackDown() {
        finish();
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menu != null && menu.getClass().getSimpleName().equals("MenuBuilder")) {
            try {
                Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                m.setAccessible(true);
                m.invoke(menu, true);
            } catch (Exception e) {
                //ignore
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 获取布局文件ID
     */
    protected abstract int getLayoutId();

    /**
     * 设置监听器
     */
    protected abstract void setListeners();

    /**
     * 设置监听器
     */
    protected abstract void initViews();

    protected void initViews(Bundle savedInstanceState) {
        initViews();
    }

    /**
     * 加载调用参数
     */
    protected abstract void loadParam();

    protected void injectBinder() {

    }

    // 设置android app 的字体大小不受系统字体大小改变的影响
    @Override
    public Resources getResources() {
//        Resources res = super.getResources();
//        Configuration config = new Configuration();
//        config.setToDefaults();
//        res.updateConfiguration(config, res.getDisplayMetrics());
        return super.getResources();
    }

    protected final void setTitle(String str) {
        if (getSupportActionBar() != null) {
            navTitle = str;
            getSupportActionBar().setTitle(navTitle);
        }
    }

    protected final void enableBack(boolean back) {
        if (getSupportActionBar() != null) {
            navBack = back;
            getSupportActionBar().setDisplayHomeAsUpEnabled(navBack);
        }
    }

    /**
     * 设置是否显示ActionBar
     *
     * @param showActionBar true 显示 false 隐藏
     */
    protected final void enableActionBar(boolean showActionBar) {
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            if (showActionBar) {
                mActionBar.show();
            } else {
                mActionBar.hide();
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
