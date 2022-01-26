package com.pax.pay.ui.def;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseStackActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.poslink.ShowTextBoxController;
import com.pax.pay.ui.def.poslink.TextController;
import com.pax.us.pay.ui.component.utils.TickTimer;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst;
import com.pax.us.pay.ui.core.helper.ShowTextBoxHelper;
import com.paxus.utils.log.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jolie.Yang on 3/29/2021
 */
public class ShowTextBoxActivity extends BaseStackActivity implements ShowTextBoxHelper.IShowTextBoxHelper, View.OnClickListener {

    private ShowTextBoxHelper helper;
    public static final String POSLINK_REQUEST_ACTION_CLEAR_MESSAGE = "com.pax.us.pay.poslink.request.CLEAR_MESSAGE";
    LinearLayout llShowTextBox;
    LinearLayout llContainerShowTextBox;
    LinearLayout layoutBtn1;
    LinearLayout layoutBtn2;
    LinearLayout layoutBtn3;

    private String btnNum;
    private List<String> hardKeyList;
    private String enableHardKey;
    private String btnKey1, btnKey2, btnKey3;
    private String btnName1, btnName2, btnName3;
    private LinearLayout.LayoutParams lp;

    private boolean isNoBlocking = false;
    private boolean continuousScreen = false;
    private TickTimer tickTimer;
    private long timeout;
    private Bundle bundle;
    private ClearMsgReceiver clearMsgReceiver;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_text_box;
    }

    @Override
    protected void initViews() {
        enableHardKey = bundle.getString(EntryExtraData.PARAM_ENABLE_HARD_KEY);
        String keyListStr = (String) bundle.get(EntryExtraData.PARAM_HARD_KEY_LIST);
        hardKeyList = Arrays.asList(keyListStr.split(" "));

        btnKey1 = bundle.getString(EntryExtraData.PARAM_BUTTON_1_KEY);
        btnKey2 = bundle.getString(EntryExtraData.PARAM_BUTTON_2_KEY);
        btnKey3 = bundle.getString(EntryExtraData.PARAM_BUTTON_3_KEY);

        btnName1 = bundle.getString(EntryExtraData.PARAM_BUTTON_1_NAME);
        btnName2 = bundle.getString(EntryExtraData.PARAM_BUTTON_2_NAME);
        btnName3 = bundle.getString(EntryExtraData.PARAM_BUTTON_3_NAME);

        long time = bundle.getLong(EntryExtraData.PARAM_TIMEOUT, -1L);

        if (time <= 0) {
            tickTimerStop();
        }

        String text = bundle.getString(EntryExtraData.PARAM_TEXT);

        ShowTextBoxController.showText(this, llContainerShowTextBox, text, lp);

        if (!isNoBlocking) {
            boolean hasPhyKeyboard = bundle.getBoolean(EntryExtraData.PARAM_HAS_PHYSICAL_KEYBOARD, false);
            if (!hasPhyKeyboard || !"1".equals(enableHardKey)) {
                setBtnListVisible();
            }
        }
    }

    @Override
    protected void loadParam() {
        bundle = getIntent().getExtras();
        createTimer();
        tickTimer.start(getTickTimeout());

        llShowTextBox = findViewById(R.id.ll_show_text_box);
        llContainerShowTextBox = findViewById(R.id.ll_container_show_text_box);
        layoutBtn1 = findViewById(R.id.rl_btn_1);
        layoutBtn2 = findViewById(R.id.rl_btn_2);
        layoutBtn3 = findViewById(R.id.rl_btn_3);

        String title = bundle.getString(EntryExtraData.PARAM_TITLE);
        if (!TextUtils.isEmpty(title)) {
            navTitle = title;
        }

        try {
            if (ManageUIConst.ContinuousScreen.DO_NOT_GO_TO_IDLE.equals(bundle.getString(EntryExtraData.PARAM_CONTINUE_SCREEN,""))) {
                continuousScreen = true;
            }
        } catch (Exception e) {
            Logger.e(e);
            continuousScreen = false;
        }

        clearMsgReceiver = new ClearMsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(POSLINK_REQUEST_ACTION_CLEAR_MESSAGE);
        registerReceiver(clearMsgReceiver, intentFilter);


        lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        helper = new ShowTextBoxHelper(this, new RespStatus(this));

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus && timeout == 0){
            tickTimerStop();
            helper.sendNext();
        }
    }


    @Override
    protected boolean onKeyBackDown() {
        tickTimerStop();
        helper.sendAbort();
        if (!continuousScreen && !isNoBlocking) {
            finish();
        }
        return true;
    }

    @Override
    protected void setListeners() {
        layoutBtn1.setOnClickListener(this);
        layoutBtn2.setOnClickListener(this);
        layoutBtn3.setOnClickListener(this);
    }

    @Override
    protected void onClickProtected(View view) {
        int id = view.getId();
        if (id == R.id.rl_btn_1) {
            btnNum = "1";
        } else if (id == R.id.rl_btn_2) {
            btnNum = "2";
        } else if (id == R.id.rl_btn_3) {
            btnNum = "3";
        }
        tickTimerStop();
        helper.sendNext(btnNum);
        if (!continuousScreen)
            finish();
    }


    @Override
    public void finish() {
        helper.stop();
        super.finish();
        //remove activity animation
        overridePendingTransition(0, 0);
    }

    @Override
    public void onDestroy(){
        if (clearMsgReceiver!= null) {
            unregisterReceiver(clearMsgReceiver);
        }
        super.onDestroy();

    }

    @Override
    public void onStartHelper() {
        helper.start(this, getIntent());
        if(isNoBlocking){
            helper.sendNext();
        }
    }

    @Override
    public void onAbortHelper() {
        tickTimerStop();
        helper.sendAbort();
        finish();
    }

    public boolean getColor(String color) {
        int r = Integer.valueOf(color.substring(0, 2),16);
        int g = Integer.valueOf(color.substring(2, 4),16);
        int b = Integer.valueOf(color.substring(4),16);
        //浅色
        //深色
        return !(r * 0.299 + g * 0.578 + b * 0.114 >= 192);
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

    protected void timeOnTick(long leftTime) {
        //this.leftTime = leftTime;
        //setTitle(String.format(Locale.US, "%s ( %d )", getString(R.string.signature), leftTime));
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
        if (timeout <= 0)
            return;

        tickTimerStop();
        helper.sendTimeout();

        if (!continuousScreen) {
            finish();
        }
    }

    protected long getTickTimeout() {
        timeout = -1;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            long time = bundle.getLong(EntryExtraData.PARAM_TIMEOUT, -1L);
            if (time > 0) {
                timeout = (int) (time / 1000L);
            }
        }

        if (timeout == 0)
            isNoBlocking = true;

        return timeout;
    }

    private void setBtnListVisible() {
        if (!TextUtils.isEmpty(btnName1)) {
            if (!TextUtils.isEmpty(btnName2)) {
                if (!TextUtils.isEmpty(btnName3)) {
                    setButtonView(layoutBtn1, btnName1, bundle.getString(EntryExtraData.PARAM_BUTTON_1_COLOR));

                    setButtonView(layoutBtn2, btnName2, bundle.getString(EntryExtraData.PARAM_BUTTON_2_COLOR));

                    setButtonView(layoutBtn3, btnName3, bundle.getString(EntryExtraData.PARAM_BUTTON_3_COLOR));
                } else {
                    setButtonView(layoutBtn1, btnName1, bundle.getString(EntryExtraData.PARAM_BUTTON_1_COLOR));

                    setButtonView(layoutBtn2, btnName2, bundle.getString(EntryExtraData.PARAM_BUTTON_2_COLOR));
                }
            } else {
                setButtonView(layoutBtn1, btnName1, bundle.getString(EntryExtraData.PARAM_BUTTON_1_COLOR));
            }
        }
    }

    private void setButtonView(LinearLayout layoutBtn, String btnName, String btnColor) {
        layoutBtn.setVisibility(View.VISIBLE);
        List<TextView> viewList = TextController.getViewList(this, btnName, lp);
        for (TextView view : viewList) {
            if (!TextUtils.isEmpty(btnColor) && getColor(btnColor)) {
                view.setTextColor(Color.WHITE);
            } else {
                view.setTextColor(Color.BLACK);
            }
//            view.setEllipsize(TextUtils.TruncateAt.END);
            LinearLayout.LayoutParams lps = (LinearLayout.LayoutParams) view.getLayoutParams();
            lps.gravity = Gravity.CENTER;
            view.setGravity(lps.gravity);
            view.setLayoutParams(lps);
            layoutBtn.addView(view);
        }
        layoutBtn.setBackgroundResource(R.drawable.bg_textbox_btn);
        GradientDrawable drawable =(GradientDrawable)layoutBtn.getBackground();
        if (!TextUtils.isEmpty(btnColor)) {
            drawable.setColor(Color.parseColor("#" + btnColor));
        } else {
            drawable.setColor(Color.WHITE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ("1".equals(enableHardKey)) {
            hardKeyEnabled(keyCode);
        } else {
            hardKeyNotEnabled(keyCode);
        }
        if (isNoBlocking) {
            if (keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_APP_SWITCH) {
                return super.onKeyDown(keyCode, event);
            }
        }
        return true;
    }

    private void hardKeyEnabled(int keyCode) {
        if (hardKeyList.size() > 0) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_0:
                    if (hardKeyList.contains("KEY0")) {
                        btnNum = "KEY0";
                    }
                    break;
                case KeyEvent.KEYCODE_1:
                    if (hardKeyList.contains("KEY1")) {
                        btnNum = "KEY1";
                    }
                    break;
                case KeyEvent.KEYCODE_2:
                    if (hardKeyList.contains("KEY2")) {
                        btnNum = "KEY2";
                    }
                    break;
                case KeyEvent.KEYCODE_3:
                    if (hardKeyList.contains("KEY3")) {
                        btnNum = "KEY3";
                    }
                    break;
                case KeyEvent.KEYCODE_4:
                    if (hardKeyList.contains("KEY4")) {
                        btnNum = "KEY4";
                    }
                    break;
                case KeyEvent.KEYCODE_5:
                    if (hardKeyList.contains("KEY5")) {
                        btnNum = "KEY5";
                    }
                    break;
                case KeyEvent.KEYCODE_6:
                    if (hardKeyList.contains("KEY6")) {
                        btnNum = "KEY6";
                    }
                    break;
                case KeyEvent.KEYCODE_7:
                    if (hardKeyList.contains("KEY7")) {
                        btnNum = "KEY7";
                    }
                    break;
                case KeyEvent.KEYCODE_8:
                    if (hardKeyList.contains("KEY8")) {
                        btnNum = "KEY8";
                    }
                    break;
                case KeyEvent.KEYCODE_9:
                    if (hardKeyList.contains("KEY9")) {
                        btnNum = "KEY9";
                    }
                    break;
                case KeyEvent.KEYCODE_ENTER:
                    if (isNoBlocking) {
                        return;
                    }

                    if (hardKeyList.contains("KEYENTER")) {
                        btnNum = "KEYENTER";
                    }
                    break;
                case KeyEvent.KEYCODE_BACK:
                    if (isNoBlocking) {
                        return;
                    }

                    if (hardKeyList.contains("KEYCANCEL")) {
                        btnNum = "KEYCANCEL";
                    }
                    break;

                case KeyEvent.KEYCODE_DEL:

                    if (isNoBlocking) {
                        return;
                    }

                    if (hardKeyList.contains("KEYCLEAR")) {
                        btnNum = "KEYCLEAR";
                    }
                    break;
            }
        }else {
            switch (keyCode) {
                case KeyEvent.KEYCODE_0:
                    btnNum = "KEY0";
                    break;
                case KeyEvent.KEYCODE_1:
                    btnNum = "KEY1";
                    break;
                case KeyEvent.KEYCODE_2:
                    btnNum = "KEY2";
                    break;
                case KeyEvent.KEYCODE_3:
                    btnNum = "KEY3";
                    break;
                case KeyEvent.KEYCODE_4:
                    btnNum = "KEY4";
                    break;
                case KeyEvent.KEYCODE_5:
                    btnNum = "KEY5";
                    break;
                case KeyEvent.KEYCODE_6:
                    btnNum = "KEY6";
                    break;
                case KeyEvent.KEYCODE_7:
                    btnNum = "KEY7";
                    break;
                case KeyEvent.KEYCODE_8:
                    btnNum = "KEY8";
                    break;
                case KeyEvent.KEYCODE_9:
                    btnNum = "KEY9";
                    break;
                case KeyEvent.KEYCODE_ENTER:
                    if (isNoBlocking) {
                        return;
                    }
                    btnNum = "KEYENTER";
                    break;
                case KeyEvent.KEYCODE_BACK:
                    if (isNoBlocking) {
                        return;
                    }

                    btnNum = "KEYCANCEL";
                    break;
                case KeyEvent.KEYCODE_DEL:
                    if (isNoBlocking) {
                        return;
                    }
                    btnNum = "KEYCLEAR";
                    break;
            }
        }

        if (!TextUtils.isEmpty(btnNum) && (hardKeyList.size() == 0 || hardKeyList.contains(btnNum))) {
            tickTimerStop();
            helper.sendNext(btnNum);
            if(!continuousScreen)
                finish();
        }
    }

    private void hardKeyNotEnabled(int keyCode) {
        String key = "";
        switch (keyCode) {
            case KeyEvent.KEYCODE_0:
                key = "KEY0";
                break;
            case KeyEvent.KEYCODE_1:
                key = "KEY1";
                break;
            case KeyEvent.KEYCODE_2:
                key = "KEY2";
                break;
            case KeyEvent.KEYCODE_3:
                key = "KEY3";
                break;
            case KeyEvent.KEYCODE_4:
                key = "KEY4";
                break;
            case KeyEvent.KEYCODE_5:
                key = "KEY5";
                break;
            case KeyEvent.KEYCODE_6:
                key = "KEY6";
                break;
            case KeyEvent.KEYCODE_7:
                key = "KEY7";
                break;
            case KeyEvent.KEYCODE_8:
                key = "KEY8";
                break;
            case KeyEvent.KEYCODE_9:
                key = "KEY9";
                break;
            case KeyEvent.KEYCODE_ENTER:
                if (isNoBlocking) {
                    return;
                }
                key = "KEYENTER";
                break;
            case KeyEvent.KEYCODE_BACK:
                if (isNoBlocking) {
                    return;
                }
                key = "KEYCANCEL";
                if (key.equals(btnKey1)) {
                    if (TextUtils.isEmpty(btnName1)) {
                        tickTimerStop();
                        helper.sendAbort();
                        if(!continuousScreen)
                            finish();
                        return;
                    }
                } else if (key.equals(btnKey2)) {
                    if (TextUtils.isEmpty(btnName2)) {
                        tickTimerStop();
                        helper.sendAbort();
                        if (!continuousScreen)
                            finish();
                        return;
                    }
                } else if (key.equals(btnKey3)){
                    if (TextUtils.isEmpty(btnName3)) {
                        tickTimerStop();
                        helper.sendAbort();
                        if(!continuousScreen)
                            finish();
                        return;
                    }
                } else {
                    tickTimerStop();
                    if (TextUtils.isEmpty(btnNum))
                        helper.sendAbort();
                    else
                        helper.sendNext(btnNum);  //same as sendAbort
                    if(!continuousScreen)
                        finish();
                    return;
                }
                break;
            case KeyEvent.KEYCODE_DEL:
                if (isNoBlocking) {
                    return;
                }
                key = "KEYCLEAR";
                break;
        }

        setBtnNum(key);
    }

    private void setBtnNum(String key) {
        if (!TextUtils.isEmpty(btnName1) && !TextUtils.isEmpty(btnKey1) && btnKey1.equals(key)) {
            btnNum = "1";
            tickTimerStop();
            helper.sendNext(btnNum);
            if(!continuousScreen)
                finish();
        } else if (!TextUtils.isEmpty(btnName1) && !TextUtils.isEmpty(btnName2) && !TextUtils.isEmpty(btnKey2) && btnKey2.equals(key)) {
            btnNum = "2";
            tickTimerStop();
            helper.sendNext(btnNum);
            if(!continuousScreen)
                finish();
        } else if (!TextUtils.isEmpty(btnName1) && !TextUtils.isEmpty(btnName2) &&
                !TextUtils.isEmpty(btnName3) && !TextUtils.isEmpty(btnKey3) && btnKey3.equals(key)) {
            btnNum = "3";
            tickTimerStop();
            helper.sendNext(btnNum);
            if(!continuousScreen)
                finish();
        }
    }


    private class RespStatus extends RespStatusImpl{

        public RespStatus(Activity activity) {
            super(activity);
        }
        @Override
        public void onAccepted() {
            //don't close activity.
        }
    }

    private class ClearMsgReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (POSLINK_REQUEST_ACTION_CLEAR_MESSAGE.equals(intent.getAction())){
                llContainerShowTextBox.removeAllViews();
            }
        }
    }


}
