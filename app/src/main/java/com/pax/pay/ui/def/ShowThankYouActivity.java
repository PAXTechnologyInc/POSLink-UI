package com.pax.pay.ui.def;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseStackActivity;
import com.pax.pay.ui.def.utils.TextController;
import com.pax.us.pay.ui.component.utils.TickTimer;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.helper.ShowOnlyHelper;
import com.paxus.utils.StringUtils;
import com.paxus.utils.log.Logger;

import java.util.List;

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
    private TickTimer tickTimer;
    int timeout;
    LinearLayout title;
    LinearLayout line1;
    LinearLayout line2;
    private boolean isNoBlocking;
    private LinearLayout.LayoutParams lp;


    @Override
    protected int getLayoutId() {
        return R.layout.show_thankyou_layout;
    }

    @Override
    protected void initViews() {


        if (timeout <= 0) {
            setTextView(TextController.getViewList(this, titleStr, lp), title, TITLE_TAG);
            //EventBusUtil.doEvent(new POSLinkManagerUIEvent(POSLinkManagerUIEvent.Status.EVENT_UI_POSLINK_SUCCESS, null));
        } else {
            if (StringUtils.isEmpty(titleStr) && StringUtils.isEmpty(msg1)
                    && StringUtils.isEmpty(msg2)) {
                setTextView(TextController.getViewList(this, getResources().getText(R.string.thx_show_message_context).toString(), lp), line1, MESSAGE_TAG);
            } else {
                setTextView(TextController.getViewList(this, titleStr, lp), title, TITLE_TAG);
                setTextView(TextController.getViewList(this, msg1, lp), line1, MESSAGE_TAG);
                setTextView(TextController.getViewList(this, msg2, lp), line2, MESSAGE_TAG);
            }
        }
    }

    @Override
    protected void loadParam() {
        createTimer();
        tickTimer.start(getTickTimeout());

        title = findViewById(R.id.thx_msg_title);
        line1 = findViewById(R.id.thx_msg_01);
        line2 = findViewById(R.id.thx_msg_02);

        lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);

        Bundle bundle = getIntent().getExtras();
        try {
            titleStr = bundle.getString(EntryExtraData.PARAM_TITLE);
            msg1 = bundle.getString(EntryExtraData.PARAM_MESSAGE_1);
            msg2 = bundle.getString(EntryExtraData.PARAM_MESSAGE_2);
        } catch (Exception e) {
            Logger.e(e);
            titleStr = null;
            msg1 = null;
            msg2 = null;
        }

        helper = new ShowOnlyHelper(this, new PoslinkRespStatusImpl());

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus && timeout <= 0){
            tickTimerStop();
            helper.sendNext();
        }
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
     }

//    protected long getTickTimeout() {
//        if (timeout < 0) {
//            isNoBlocking = true;
//        }
//
//        return timeout <= 1 ? timeout : timeout / 1000;
//    }


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
        if (timeout <= 0) {
            return;
        }
        tickTimerStop();
        helper.sendNext();
        if (!isNoBlocking) {
            finish();
        }
    }

    @Override
    protected boolean onKeyBackDown() {
        tickTimerStop();
        helper.sendAbort();
        //EventBusUtil.doEvent(new POSLinkManagerUIEvent(POSLinkManagerUIEvent.Status.EVENT_UI_POSLINK_ABORT, null));
        tickTimerStop();
        if (!isNoBlocking) {
            finish();
        }

        return true;
    }

    @Override
    protected void setListeners() {

    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {

    }

    protected long getTickTimeout() {
        timeout = -1;
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(EntryExtraData.PARAM_TIMEOUT)) {
            long time = getIntent().getExtras().getLong(EntryExtraData.PARAM_TIMEOUT, -1L);
            timeout = (int)(time/1000l);
        }

        if (timeout <= 0) {
            isNoBlocking = true;
        }

        return timeout;
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
    public void onStartHelper() {
        helper.start(this, getIntent());
    }

    @Override
    public void onAbortHelper() {
        helper.sendAbort();
        finish();
    }

    private class PoslinkRespStatusImpl implements IRespStatus {

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
