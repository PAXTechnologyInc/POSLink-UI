package com.pax.pay.ui.def;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;

import com.pax.pay.ui.def.base.BaseStackActivity;
import com.pax.pay.ui.def.base.ElectronicSignatureView;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.poslink.ShowTextBoxController;
import com.pax.us.pay.ui.component.keyboard.CustomKeyboardView;
import com.pax.us.pay.ui.component.utils.TickTimer;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst;
import com.pax.us.pay.ui.core.helper.ShowTextBoxHelper;
import com.paxus.utils.log.Logger;

import java.util.List;

public class SignatureBoxActivity extends BaseStackActivity implements ShowTextBoxHelper.IShowTextBoxHelper {
    public static final String POSLINK_REQUEST_ACTION_CLEAR_MESSAGE = "com.pax.us.pay.poslink.request.CLEAR_MESSAGE";

    LinearLayout amountLayout;
    ElectronicSignatureView mSignatureView;
    LinearLayout llContainerShowTextBox;
    LinearLayout llShowTextBox;
    protected ScrollView textScrolerView;
    private Bundle bundle;

    protected TickTimer tickTimer;

    Button clearBtn;
    Button confirmBtn;
    Button cancelBtn;

    private ShowTextBoxHelper helper;
    private boolean processing = false;
    private long timeOut = 0;
    private boolean isNoBlocking = false;
    private ClearMsgReceiver clearMsgReceiver;


    private final OnKeyListener onkeyListener = (v, keyCode, event) -> {
        if (event.getAction() == KeyEvent.ACTION_UP){
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                onConfirmClick();
                return true;
            } else if(keyCode == KeyEvent.KEYCODE_DEL){
                onClearClick();
                return true;
            } else if(keyCode == KeyEvent.KEYCODE_BACK){
                onCancelClick();
                return true;
            }
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createTimer();
        tickTimer.start(getTickTimeout());

    }

    private void createTimer() {

//        if (timeOut == 0)
//            isNoBlocking = true;

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

    @Override
    protected int getLayoutId() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            long signatureBoxOrientation = bundle.getLong(EntryExtraData.PARAM_SIGN_BOX, 2);
            if (SignatureBox.HORIZONTAL_SIGNATURE_BOX == signatureBoxOrientation && !CustomKeyboardView.isScreenOrientationPortrait(this)) {
                return R.layout.activity_authgraph_layout_2;
            }
        }
        return R.layout.activity_authgraph_layout;
    }

    @Override
    protected void loadParam() {
        super.loadParam();
        //amountTv = findViewById(R.id.trans_amount_tv);
        //amountPrompt = findViewById(R.id.trans_amount_prompt);
        amountLayout = findViewById(R.id.trans_amount_layout);
        mSignatureView = findViewById(R.id.signatureView);
        clearBtn = findViewById(R.id.clear_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        confirmBtn = findViewById(R.id.confirm_btn);

        //navTitle = getString(R.string.signature);
        bundle = getIntent().getExtras();
        if(bundle!= null) {
            String title = bundle.getString(EntryExtraData.PARAM_TITLE,"");
            if (!TextUtils.isEmpty(title)) {
                navTitle = title;
            }
        }
        llContainerShowTextBox = findViewById(R.id.ll_container_show_text_box_signature);
        llShowTextBox = findViewById(R.id.ll_show_text_box_signature);
        textScrolerView = findViewById(R.id.ll_show_text_box_scroller_view);
        //CurrencyConverter.setDefCurrency(CurrencyType.USD);

        try {
            if (bundle!=null && ManageUIConst.ContinuousScreen.DO_NOT_GO_TO_IDLE.equals(bundle.getString(EntryExtraData.PARAM_CONTINUE_SCREEN,""))) {
                isNoBlocking = true;
            }
        } catch (Exception e) {
            Logger.e(e);
            isNoBlocking = false;
        }

        clearMsgReceiver = new ClearMsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(POSLINK_REQUEST_ACTION_CLEAR_MESSAGE);
        registerReceiver(clearMsgReceiver, intentFilter);


        helper = new ShowTextBoxHelper(this, new RespStatus(this));
    }

    @Override
    protected void initViews() {
        amountLayout.setVisibility(View.GONE);
        confirmBtn.requestFocus();
        // 内置签名板
        mSignatureView.setBitmap(new Rect(0, 0, 384, 128), 0, Color.WHITE);
        textScrolerView.setVisibility(View.VISIBLE);
        textScrolerView.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP
                    && keyCode == KeyEvent.KEYCODE_ENTER) {
                onClickProtected(confirmBtn);
                return true;
            }
            return false;
        });

        long time = bundle.getLong(EntryExtraData.PARAM_TIMEOUT, -1L);

        if (time <= 0) {
            tickTimerStop();
        }

        String text = bundle.getString(EntryExtraData.PARAM_TEXT);
        if (!TextUtils.isEmpty(text)) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            ShowTextBoxController.showText(this, llContainerShowTextBox, text, lp);
        }
    }

    @Override
    protected void setListeners() {
        cancelBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
        mSignatureView.setOnKeyListener(onkeyListener);
        mSignatureView.setFocusable(true);
        mSignatureView.setFocusableInTouchMode(true);
//        mSignatureView.setDataListener(new ElectronicSignatureView.DataRecordListener() {
//            @Override
//            public void onDown(short x, short y) {
//                short[] pos = new short[]{'M', 0, 0};
//                pos[1] = x;
//                pos[2] = y;
//                mVectorDataList.add(pos);
//            }
//
//            @Override
//            public void onMove(short pre_x, short pre_y, short x, short y) {
//                short[] pos = new short[]{'Q', 0, 0, 0, 0};
//                pos[1] = pre_x;
//                pos[2] = pre_y;
//                pos[3] = x;
//                pos[4] = y;
//                mVectorDataList.add(pos);
//            }
//
//            @Override
//            public void onUp(short x, short y) {
//
//            }
//        });
    }

    @Override
    protected boolean onKeyEnterDown() {
        onConfirmClick();
        return true;
    }

    @Override
    protected boolean onKeyDelDown() {
        onClearClick();
        return true;
    }

    @Override
    protected boolean onKeyBackDown() {
        tickTimerStop();
        helper.sendAbort();
        if(!isNoBlocking)
            finish();
        return true;
    }

    @Override
    public void onClickProtected(View v) {
        int i = v.getId();
        if (i == R.id.clear_btn) {
            onClearClick();
        } else if (i == R.id.confirm_btn) {
            onConfirmClick();
        } else if (i == R.id.cancel_btn) {
            onCancelClick();
        }
    }

    private void onConfirmClick(){
        if (isProcessing()) {
            return;
        }
        if (!mSignatureView.getTouched()) {
            //finish(new ActionResult(TransResult.SUCC, null));
            return;
        }

        try {
            confirmBtn.setClickable(false);
            setProcessFlag();
//                Bitmap bitmap = mSignatureView.save(true, 1);
//                byte[] data = saveSignFile(bitmap);
            List<float[]> pathPos = mSignatureView.getPathPos();
            int len = 0;
            for (float[] ba : pathPos) {
                len += ba.length;
            }
            short[] total = new short[len];
            int index = 0;
            for (float[] ba : pathPos) {
                for (float b : ba) {
                    total[index++] = (short) b;
                }
            }

//              int length  = total.length;
//              byte[] data = gl.getImgProcessing().bitmapToJbig(bitmap, rgb2MonoAlgo);//if anonymous impl, memory leak
//
            Logger.d("total Length = " + total.length);
//                if (total.length > 4000) {
//                    Toast.makeText(this, getResources().getString(R.string.pls_re_signature), Toast.LENGTH_LONG).show();
//                    setProcessFlag();
//                    mSignatureView.clear();
//                    clearProcessFlag();
//                    return;
//                }
            clearProcessFlag();
            tickTimerStop();
            helper.sendNext( "1", total);
            if (!isNoBlocking)
                finish();
        } finally {
            confirmBtn.setClickable(true);
        }
    }
    private void onClearClick(){
        if (isProcessing()) {
            return;
        }
        setProcessFlag();
        restartTimer();
        mSignatureView.clear();
        clearProcessFlag();
    }

    private void onCancelClick(){
        tickTimerStop();
        helper.sendAbort();
        if (!isNoBlocking)
            finish();
    }

    protected void timeOnTick(long leftTime) {
       // this.leftTime = leftTime;
       // setTitle(String.format(Locale.US, "%s ( %d )", getString(R.string.signature), leftTime));
    }

    protected long getTickTimeout() {
        timeOut = -1;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            long time = bundle.getLong(EntryExtraData.PARAM_TIMEOUT, -1L);
            if (time > 0) {
                timeOut = (int) (time / 1000L);
            }
        }

        if (timeOut == 0)
            isNoBlocking = true;

        return timeOut;
    }

    protected void onTimerFinish() {
//        if (leftTime > 0) {
//            leftTime = leftTime - 1;
//            setTitle(String.format(Locale.US, "%s ( %d )", getString(R.string.signature), leftTime));
//        }
//        tickTimerStop();
//        if (leftTime == 0)
//            helper.sendTimeout();
        if (timeOut <= 0)
            return;

        tickTimerStop();
        helper.sendTimeout();

        if (!isNoBlocking) {
            finish();
        }
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
        //MsgInfoCache.clearDefaultCache();
        if (clearMsgReceiver!= null) {
            unregisterReceiver(clearMsgReceiver);
        }
        super.onDestroy();

    }

    protected void setProcessFlag() {
        processing = true;
    }

    protected void clearProcessFlag() {
        processing = false;
    }

    protected boolean isProcessing() {
        return processing;
    }


    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
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

//    @Override
//    public void onShowTimeout(long timeoutMs) {
//        this.timeOut = timeoutMs;
//        restartTimer();
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

//    @Override
//    public void onShowCancelButton(boolean enableCancel) {
//        if (enableCancel) {
//            cancelBtn.setVisibility(View.VISIBLE);
//        } else
//            cancelBtn.setVisibility(View.GONE);
//    }

    public class SignatureBox {
        public static final int HORIZONTAL_SIGNATURE_BOX = 1;
        public static final int VERTICAL_SIGNATURE_BOX = 2;
    }

    private class ClearMsgReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (POSLINK_REQUEST_ACTION_CLEAR_MESSAGE.equals(intent.getAction())) {
                llContainerShowTextBox.removeAllViews();
                tickTimerStop();
                mSignatureView.clear();
                clearProcessFlag();
            }
        }
    }

    private class RespStatus extends RespStatusImpl{

        RespStatus(Activity activity) {
            super(activity);
        }
        @Override
        public void onAccepted() {
            //don't close activity.
        }
    }
}
