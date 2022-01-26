package com.pax.pay.ui.def;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseStackActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.poslink.TextController;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst;
import com.pax.us.pay.ui.core.helper.SelectOptionsHelper;
import com.paxus.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ShowDialogActivity extends BaseStackActivity implements SelectOptionsHelper.ISelectOptionListener {
    private SelectOptionsHelper helper;

    LinearLayout mTitle;
    LinearLayout button1;
    LinearLayout button2;
    LinearLayout button3;
    LinearLayout button4;

    private String title;
    private String[] buttons;
    private boolean continuousScreen = false;
    private Timer timer;
    private boolean noBlocking = false;

    @Override
    protected int getLayoutId() {
        return R.layout.show_dialog_layout;
    }

    @Override
    protected boolean onKeyBackDown() {
        onAbort();
        return true;
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected void initViews() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);

        setTextView(TextController.getTitleViewList(this, title, lp), lp);
    }

    @Override
    protected void loadParam() {
        super.loadParam();
        mTitle = findViewById(R.id.tv_title_show_dialog);
        button1 = findViewById(R.id.btn_show_dialog_1);
        button2 = findViewById(R.id.btn_show_dialog_2);
        button3 = findViewById(R.id.btn_show_dialog_3);
        button4 = findViewById(R.id.btn_show_dialog_4);

        mTitle.removeAllViews();
        title = null;
        continuousScreen = false;
        long timeoutMs = -1;
        stopTimer();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            title = bundle.getString(EntryExtraData.PARAM_TITLE);
            continuousScreen = ManageUIConst.ContinuousScreen.DO_NOT_GO_TO_IDLE.equals(bundle.getString(EntryExtraData.PARAM_CONTINUE_SCREEN));

            noBlocking = false;
            if(bundle.containsKey(EntryExtraData.PARAM_TIMEOUT)){
                timeoutMs = bundle.getLong(EntryExtraData.PARAM_TIMEOUT, -1L);
                if(timeoutMs == 0){
                    noBlocking = true;
                }
            }
        }

        startTimer(timeoutMs);
        helper = new SelectOptionsHelper(this, new PoslinkRespStatusImpl(this));
    }

    private void showButtons(){
        if (noBlocking){
            return;
        }
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
                            stopTimer();
                            if (!continuousScreen) {
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
        List<TextView> viewList = TextController.getTitleViewList(this, btnName, lp);
        for (TextView view : viewList) {
            LinearLayout.LayoutParams lps = (LinearLayout.LayoutParams) view.getLayoutParams();
            lps.gravity = lps.gravity | Gravity.CENTER;
            view.setGravity(lps.gravity);
            view.setLayoutParams(lps);
            view.setTextColor(Color.WHITE);
            layoutBtn.addView(view);
        }
    }

    @Override
    public void onStartHelper(){
        helper.start(this, getIntent());
        if(noBlocking){
            helper.sendNext();
        }
    }

    @Override
    public void onAbortHelper() {
        onAbort();
    }

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

    private void startTimer(long timeoutMs) {
        if(timeoutMs > 0) {
            stopTimer();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    onTimeout();
                }
            }, timeoutMs);
        }
    }

    private void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }
    private void onTimeout(){
        stopTimer();
        helper.sendTimeout();
        if (!continuousScreen) {
            finish();
        }
    }
    private void onAbort(){
        stopTimer();
        helper.sendAbort();
        //NoBlocking mode and continuousScreen not finish screen until next command
        if (!continuousScreen && !noBlocking) {
            finish();
        }
    }

    private class PoslinkRespStatusImpl extends RespStatusImpl {

        public PoslinkRespStatusImpl(Activity activity) {
            super(activity);
        }
        @Override
        public void onAccepted() {
            //don't close activity.
        }
    }

}
