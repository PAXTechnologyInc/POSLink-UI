package com.pax.pay.ui.def.base;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import com.pax.pay.ui.def.R;
import com.pax.pay.ui.def.eventbus.ActivityEndEvent;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.pax.us.pay.ui.constant.entry.enumeration.TransMode;
import com.paxus.utils.log.Logger;
import com.paxus.view.utils.ViewUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;

public abstract class BaseActivity extends BaseLandActivity {

    private Toolbar toolbar;


    /**
     * display Title
     */
    protected String navTitle = "";
    /**
     * Enable navigation back button
     */
    protected boolean navBack = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentViewInternal(getLayoutId());

        EventBusUtil.doEvent(new ActivityEndEvent());
        EventBusUtil.register(this);
        loadParam();
        loadExpandedView(savedInstanceState);
        initToolbar();
        initViews(savedInstanceState);
        setListeners();

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
        Logger.d( "registerReceiver : " + this.getLocalClassName());
    }

    @Override
    protected void onDestroy() {
        EventBusUtil.unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEndEvent(ActivityEndEvent event) {
        EventBusUtil.unregister(this);
        finish();
    }

    /**
     * init toolbar
     */
    protected void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            setBackEnabled(true);
            getSupportActionBar().setTitle(navTitle == null ? "" : navTitle);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(navBack);
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
     * set Listener
     */
    protected abstract void setListeners();

    /**
     * init views
     */
    protected abstract void initViews();

    protected void initViews(Bundle savedInstanceState) {
        initViews();
    }

    /**
     * Load parameters
     */
    protected abstract void loadParam();

    @Override
    public Resources getResources() {
        //  set android app font, which not effect by system font size
        Resources res = super.getResources();
        //Bug fix: In Aries8, updateConfiguration will change the locale back to default
//        Configuration config = new Configuration();
//        config.setToDefaults();
//        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    protected final void setTitle(String str) {
        if (getSupportActionBar() != null && !TextUtils.isEmpty(str)) {
            navTitle = str;
            getSupportActionBar().setTitle(navTitle);
        }
    }

    protected final void enableBack(boolean back) {
        if (toolbar != null) {
            navBack = back;
            getSupportActionBar().setDisplayHomeAsUpEnabled(navBack);
        }
    }

    /**
     * Enable ActionBar
     *
     * @param showActionBar true Visible false Invisible
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

    protected void setDemo(String transMode) {
        if (!TransMode.NORMAL.equals(transMode)) {
            addWaterMarkView(transMode);
        } else {
            removeWaterMarkView();
        }
    }

    private void addWaterMarkView(String transMode) {
        removeWaterMarkView();

        String content = null;
        if(TransMode.TEST.equals(transMode)){
            content = getString(R.string.test_only);
        }else if(TransMode.DEMO.equals(transMode)){
            content = getString(R.string.demo_only);
        }else if(TransMode.TEST_AND_DEMO.equals(transMode)){
            content = getString(R.string.test_and_demo);
        }
        ViewUtils.addWaterMarkView(this,content);

    }

    private void removeWaterMarkView() {
        ViewUtils.removeWaterMarkView(this);
    }

}
