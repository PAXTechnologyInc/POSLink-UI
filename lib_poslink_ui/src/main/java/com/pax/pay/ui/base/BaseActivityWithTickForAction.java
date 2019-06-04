package com.pax.pay.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pax.pay.ui.R;
import com.pax.pay.ui.constant.ActivityBroadcastAction;
import com.pax.pay.ui.constant.StatusBroadcastAction;
import com.pax.pay.ui.message.CustomViewModel;
import com.pax.pay.ui.message.RespMessage;
import com.pax.pay.ui.message.UIMessageCenter;
import com.pax.pay.ui.utils.TickTimer;

import java.lang.ref.WeakReference;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * Created by Sim.G on 2017/4/8 10:27
 */
//public abstract class BaseActivityWithTickForAction<T> extends AppCompatActivity BaseActivityIntoStack implements Observer<RespMessage> {
public abstract class BaseActivityWithTickForAction<T> extends AppCompatActivity implements View.OnClickListener, Observer<RespMessage> {

    private Toolbar toolbar;

    /**
     * display Title
     */
    protected String navTitle = "";
    /**
     * Enable navigation back button
     */
    protected boolean navBack = true;

    protected boolean needDefaultToolbar() {
        return true;
    }

    protected TickTimer tickTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        createTimer();

        tickTimer.start(getTickTimeout());
        super.onCreate(savedInstanceState);
        setContentViewInternal(getLayoutId());
        loadParam();

        initToolbar();
        initViews();
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


        UIMessageCenter.getInstance(this).registerUIDesignReceiver();
        //AndroidInjection.inject(this);
        CustomViewModel viewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
        viewModel.getRespLiveData().observe(this, this);
    }


    private void createTimer() {
        tickTimer = new TickTimer(new TickTimer.OnTickTimerListener() {
            @Override
            public void onFinish() {
                onTimerFinish();
            }

            @Override
            public void onTick(long leftTime) {
                timeOnTick(leftTime);
            }
        });
    }

    private void setContentViewInternal(@LayoutRes int layoutResID) {
        if (needDefaultToolbar()) {
            setContentView(R.layout.activity_base);

            ViewGroup rootLayout = findViewById(R.id.root_layout);
            if (rootLayout != null) {
                LayoutInflater.from(this).inflate(layoutResID, rootLayout);
            }
        } else {
            setContentView(layoutResID);
        }
    }

    /**
     * init toolbar
     */
    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(TextUtils.isEmpty(navTitle) ? "" : navTitle);
            setBackEnabled(true);
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

    @Override
    protected void onStop() {
        super.onStop();
        tickTimerStop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return onKeyBackDown();
        }

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            onKeyEnterDown();
        }
        return super.onKeyDown(keyCode, event);
    }

    protected boolean onKeyEnterDown() {
        return false;
    }

    protected boolean onKeyBackDown() {
        //finish();
        return false;
    }

    protected void restartTimer() {
        tickTimerStop();
        createTimer();
        tickTimer.start(getTickTimeout());
    }

    public void tickTimerStop() {
        if (tickTimer != null) {
            tickTimer.stop();
            tickTimer = null;
        }
    }

    protected void onTimerFinish() {
        tickTimerStop();
        finish();
    }

    protected void timeOnTick(long leftTime) {

    }

    protected long getTickTimeout() {
        //long menuTimeout = App.getSysParam().get(SysParam.NumberParam.EDIT_MENU_TIMEOUT);
        long menuTimeout = TickTimer.DEFAULT_TIMEOUT;
        return Math.min(menuTimeout, 60 * 60); // MAX Timeout: 300s
    }

    @Override
    protected void onDestroy() {
        UIMessageCenter.getInstance(this).unregisterTransactionFinishListener();
        super.onDestroy();
        //preAction = null; //Free TransContext
    }


    @Override
    public void onChanged(@Nullable RespMessage respMessage) {
        if (respMessage != null) {

            // Handle common ui action
            String action = respMessage.getReqAction();
            switch (action) {
                case ActivityBroadcastAction.BROADCAST_ACTION_ACCEPTED:
                    UIMessageCenter.getInstance(this).unregisterTransactionFinishListener();
                    onTimerFinish();
                    break;
                case ActivityBroadcastAction.BROADCAST_ACTION_DECLINED:
                    String hintDeclined = "Request Declined,Code:" + respMessage.getResultCode() + ", Msg: " + respMessage.getResultMsg();
                    showToast(this, hintDeclined);
                    break;
                case StatusBroadcastAction.TRANS_COMPLETED:
                    onTimerFinish();
                    break;
                default:
                    break;
            }
        }

    }

    public static void showToast(Context context, final String msg) {
        final WeakReference<Context> contextWeakReference = new WeakReference<>(context);
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Context ctx = contextWeakReference.get();
                if (ctx != null) {
                    Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        };
        // Ensure that toast only show in main thread
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(run);
        } else {
            Handler mHandler = new Handler(Looper.getMainLooper());
            mHandler.post(run);
        }
    }

    @Override
    public void onClick(View v) {

    }

    protected abstract void initViews();

    /**
     * Load parameters
     */
    protected abstract void loadParam();

    protected abstract void setListeners();

    protected abstract int getLayoutId();


}
