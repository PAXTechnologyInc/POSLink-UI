package com.pax.pay.ui.def;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseStackActivity;
import com.pax.pay.ui.def.poslink.TextController;
import com.pax.pay.ui.def.poslink.print.PrintDataItem;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.helper.ShowOnlyHelper;
import com.paxus.utils.StringUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ZZ on 8/20/2020
 */
public class ShowThankYouActivity extends BaseStackActivity implements ShowOnlyHelper.IShowOnlyListener {

    private ShowOnlyHelper helper;

    private static final String MESSAGE_TAG = "messageTag";
    private static final String TITLE_TAG = "titleTag";
    String titleStr;
    String msg1;
    String msg2;
    private Timer timer;
    LinearLayout title;
    LinearLayout line1;
    LinearLayout line2;
    private boolean continuousScreen;
    private boolean noBlocking;
    private LinearLayout.LayoutParams lp;


    @Override
    protected int getLayoutId() {
        return R.layout.show_thankyou_layout;
    }

    @Override
    protected void initViews() {

        if (StringUtils.isEmpty(titleStr) && StringUtils.isEmpty(msg1)
                && StringUtils.isEmpty(msg2)) {
            setTextView(TextController.getViewList(this, getResources().getText(R.string.thx_show_message_context).toString(), lp), line1, MESSAGE_TAG);
        } else {
            setTextView(TextController.getTitleViewList(this, titleStr, lp), title, TITLE_TAG);
            setTextView(TextController.getViewList(this, msg1, lp), line1, MESSAGE_TAG);
            setTextView(TextController.getViewList(this, msg2, lp), line2, MESSAGE_TAG);
        }
    }

    @Override
    protected void loadParam() {
        super.loadParam();
        title = findViewById(R.id.thx_msg_title);
        line1 = findViewById(R.id.thx_msg_01);
        line2 = findViewById(R.id.thx_msg_02);

        lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);

        Bundle bundle = getIntent().getExtras();
        continuousScreen = false;
        long timeoutMs = -1;
        stopTimer();

        titleStr = null;
        msg1 = null;
        msg2 = null;
        if(bundle != null) {
            titleStr = bundle.getString(EntryExtraData.PARAM_TITLE, getResources().getString(R.string.title_default));
            msg1 = bundle.getString(EntryExtraData.PARAM_MESSAGE_1, getResources().getString(R.string.msg_1_default));
            if (!msg1.startsWith(PrintDataItem.RIGHT_ALIGN) &&
                    !msg1.startsWith(PrintDataItem.LEFT_ALIGN) &&
                    !msg1.startsWith(PrintDataItem.CENTER_ALIGN))
                msg1 = PrintDataItem.CENTER_ALIGN + msg1;
            msg2 = bundle.getString(EntryExtraData.PARAM_MESSAGE_2, getResources().getString(R.string.msg_2_default));
            if (!msg2.startsWith(PrintDataItem.RIGHT_ALIGN) &&
                    !msg2.startsWith(PrintDataItem.LEFT_ALIGN) &&
                    !msg2.startsWith(PrintDataItem.CENTER_ALIGN))
                msg2 = PrintDataItem.CENTER_ALIGN + msg2;
            timeoutMs = bundle.getLong(EntryExtraData.PARAM_TIMEOUT, -1L);
            if(timeoutMs == 0){
                continuousScreen = true;
                noBlocking = true;
            }
        }


        startTimer(timeoutMs);
        helper = new ShowOnlyHelper(this, new PoslinkRespStatusImpl());

    }

    private void setTextView(List<TextView> viewList, LinearLayout msgLayout, String tag) {

        for (TextView textView : viewList) {
            if (TITLE_TAG.equals(tag)) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.ui_text_size_25));
                textView.setTextColor(ContextCompat.getColor(this, R.color.primary_dark));
            } else if (MESSAGE_TAG.equals(tag)) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.ui_font_size_normal));
                textView.setTextColor(Color.BLACK);
            }

            textView.setMaxLines(1);
            msgLayout.addView(textView, lp);
        }
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
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {

    }

    @Override
    public void finish() {
        helper.stop();
        super.finish();
        //remove activity animation
        overridePendingTransition(0, 0);
    }

    @Override
    public void onStartHelper() {
        helper.start(this, getIntent());
        if(noBlocking){
            helper.sendNext();
        }
    }

    @Override
    public void onAbortHelper() {
        onAbort();
        finish();
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
        if (!continuousScreen) {
            finish();
        }
    }

    private class PoslinkRespStatusImpl implements IRespStatus {

        @Override
        public void onAccepted() {
            if (!continuousScreen) {
                finish();
            }
        }

        @Override
        public void onDeclined(long code, @Nullable String message) {
            if (!continuousScreen) {
                finish();
            }
        }
    }

}
