package com.pax.pay.ui.def;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseStackActivity;
import com.pax.pay.ui.def.utils.TextController;
import com.pax.us.pay.ui.component.utils.TickTimer;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.helper.SelectOptionsHelper;
import com.paxus.utils.StringUtils;
import com.paxus.utils.log.Logger;

import java.util.Arrays;
import java.util.List;

public class ShowDialogActivity extends BaseStackActivity implements SelectOptionsHelper.ISelectOptionListener {
    private SelectOptionsHelper helper;

    LinearLayout mTitle;
    LinearLayout button1;
    LinearLayout button2;
    LinearLayout button3;
    LinearLayout button4;

    private String title;
    private String[] buttons;
    private boolean isNoBlocking = false;
    private TickTimer tickTimer;
    int timeout;

    @Override
    protected int getLayoutId() {
        return R.layout.show_dialog_layout;
    }

    @Override
    protected boolean onKeyBackDown() {
        tickTimerStop();
        helper.sendAbort();
        if (!isNoBlocking) {
            finish();
        }
        return true;
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected void initViews() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);

        setTextView(TextController.getViewList(this, title, lp), lp);
    }

    @Override
    protected void loadParam() {
        createTimer();
        tickTimer.start(getTickTimeout());

        mTitle = findViewById(R.id.tv_title_show_dialog);
        button1 = findViewById(R.id.btn_show_dialog_1);
        button2 = findViewById(R.id.btn_show_dialog_2);
        button3 = findViewById(R.id.btn_show_dialog_3);
        button4 = findViewById(R.id.btn_show_dialog_4);

        Bundle bundle = getIntent().getExtras();
        mTitle.removeAllViews();
        try {
            title = bundle.getString(EntryExtraData.PARAM_TITLE);
        } catch (Exception e) {
            Logger.e(e);
            title = null;
        }

        try {
            if (bundle.containsKey(EntryExtraData.PARAM_CONTINUE_SCREEN)
                    && (ManageUIConst.ContinuousScreen.DO_NOT_GO_TO_IDLE.equals(bundle.getString(EntryExtraData.PARAM_CONTINUE_SCREEN)))) {
                isNoBlocking = true;
            }
        } catch (Exception e) {
            Logger.e(e);
            isNoBlocking = false;
        }

        helper = new SelectOptionsHelper(this, new PoslinkRespStatusImpl());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus && timeout == 0){
            tickTimerStop();
            helper.sendNext();
        }
    }

    private void showButtons(){
        List<LinearLayout> disPlayButtons = Arrays.asList(button1, button2, button3, button4);
        for (int i = 0; i < buttons.length; i++) {
            if (StringUtils.isEmpty(buttons[i])) {
                break;
            }
            disPlayButtons.get(i).setVisibility(View.VISIBLE);
            setButtonView(disPlayButtons.get(i), buttons[i]);
            int index = i + 1;
            disPlayButtons.get(i).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            helper.sendNext(index);
                            tickTimerStop();
                            if (!isNoBlocking) {
                                finish();
                            }
                        }
                    }
            );
        }

    }

    private void setTextView(List<TextView> viewList, LinearLayout.LayoutParams lp) {
        for (TextView textView : viewList) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.ui_text_size));
            textView.setMaxLines(1);
            textView.setTextColor(ContextCompat.getColor(this, R.color.primary_dark));
            textView.setEllipsize(null);
            mTitle.addView(textView, lp);
        }
    }

    private void setButtonView(LinearLayout layoutBtn, String btnName) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        layoutBtn.setVisibility(View.VISIBLE);
        List<TextView> viewList = TextController.getViewList(this, btnName, lp);
        for (TextView view : viewList) {
            LinearLayout.LayoutParams lps = (LinearLayout.LayoutParams) view.getLayoutParams();
            lps.gravity = lps.gravity | Gravity.CENTER;
            view.setGravity(lps.gravity);
            view.setLayoutParams(lps);
            view.setTextColor(Color.WHITE);
            layoutBtn.addView(view);
        }
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

        if (!isNoBlocking) {
            finish();
        }
    }

    protected long getTickTimeout() {
        timeout = -1;
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(EntryExtraData.PARAM_TIMEOUT)) {
            long time = getIntent().getExtras().getLong(EntryExtraData.PARAM_TIMEOUT, -1L);
            timeout = (int)(time/1000l);
        }

        if (timeout == 0)
            isNoBlocking = true;

        return timeout;
    }

    @Override
    public void onStartHelper(){
        helper.start(this, getIntent());
    }

    @Override
    public void onAbortHelper() {
        helper.sendAbort();
        finish();
    }

//    @Override
//    protected void onStart() {
//        helper.start(this, getIntent());
//        super.onStart();
//    }
//
//    @Override
//    protected void onStop() {
//        helper.stop();
//        super.onStop();
//    }

    @Override
    public void finish() {
        helper.stop();
        super.finish();
        //remove activity animation
        overridePendingTransition(0, 0);
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {

    }

    @Override
    public void onShowOptions(@NonNull String[] options) {
        if (options != null && options.length>0) {
            buttons = options;
            showButtons();
        }
    }


    private class PoslinkRespStatusImpl implements IRespStatus{

        @Override
        public void onAccepted() {
            if (!isNoBlocking) {
                finish();
            }
        }

        @Override
        public void onDeclined(long code, @Nullable String message) {
            if (!isNoBlocking) {
                finish();
            }
        }
    }

}
