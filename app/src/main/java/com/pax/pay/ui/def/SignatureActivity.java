package com.pax.pay.ui.def;

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
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.base.ElectronicSignatureView;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.component.utils.TickTimer;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.helper.SignatureHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.utils.log.Logger;

import java.util.List;
import java.util.Locale;

public class SignatureActivity extends BaseAppActivity implements SignatureHelper.ISignatureListener {

    TextView amountPrompt;
    TextView amountTv;
    LinearLayout amountLayout;
    TextView lineTv1;
    TextView lineTv2;
    LinearLayout lineLayout1;
    LinearLayout lineLayout2;
    ElectronicSignatureView mSignatureView;
    protected TickTimer tickTimer;

    Button clearBtn;
    Button confirmBtn;
    Button cancelBtn;

    private long displayAmount;
    private boolean isPoint = false;
    private SignatureHelper helper;
    private boolean processing = false;
    private long timeOut = 0, leftTime = 0;
    private AbortReceiver abortReceiver;
    public static final String POSLINK_REQUEST_ACTION_ABORT_TRANSACTION = "com.pax.us.pay.poslink.request.ABORT_TRANSACTION";
    public static final String POSLINK_REQUEST_ACTION_RESET = "com.pax.us.pay.poslink.request.RESET";


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
        IntentFilter filter = new IntentFilter();
        filter.addAction(POSLINK_REQUEST_ACTION_ABORT_TRANSACTION);
        filter.addAction(POSLINK_REQUEST_ACTION_RESET);
        abortReceiver = new AbortReceiver();
        registerReceiver(abortReceiver, filter);
        super.onCreate(savedInstanceState);
        createTimer();
        tickTimer.start(getTickTimeout());

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_authgraph_layout;
    }

    @Override
    protected void loadParam() {
        amountTv = findViewById(R.id.trans_amount_tv);
        amountPrompt = findViewById(R.id.trans_amount_prompt);
        amountLayout = findViewById(R.id.trans_amount_layout);
        clearBtn = findViewById(R.id.clear_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        confirmBtn = findViewById(R.id.confirm_btn);
        lineTv1 = findViewById(R.id.message_prompt_1);
        lineLayout1 = findViewById(R.id.message_layout_1);
        lineTv2 = findViewById(R.id.message_prompt_2);
        lineLayout2 = findViewById(R.id.message_layout_2);
        mSignatureView = findViewById(R.id.signatureView);
        getTickTimeout();
        navTitle = getString(R.string.signature);
        displayAmount = 0;
        CurrencyConverter.setDefCurrency(CurrencyType.USD);

        helper = new SignatureHelper(this, new RespStatusImpl(this));
    }

    @Override
    protected void initViews() {
        cancelBtn.setVisibility(View.GONE);
        amountLayout.setVisibility(View.INVISIBLE);
        confirmBtn.requestFocus();
        // 内置签名板
        mSignatureView.setBitmap(new Rect(0, 0, 384, 128), 0, Color.WHITE);
        //deleteSignFile();
    }

    @Override
    protected void onStart() {
        helper.start(this, getIntent());
        super.onStart();
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
    protected void onStop() {
        super.onStop();
        helper.stop();
        tickTimerStop();
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
        onCancelClick();
        return true;
    }

    @Override
    protected void onDestroy(){
        if (abortReceiver != null)
            unregisterReceiver(abortReceiver);
        super.onDestroy();
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
        } else {
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
            Logger.d( "total Length = " + total.length);
//                if (total.length > 4000) {
//                    Toast.makeText(this, getResources().getString(R.string.pls_re_signature), Toast.LENGTH_LONG).show();
//                    setProcessFlag();
//                    mSignatureView.clear();
//                    clearProcessFlag();
//                    return;
//                }
            clearProcessFlag();
            tickTimerStop();
            helper.sendNext(total);
        } finally {
            confirmBtn.setClickable(true);
        }
    }

    private void onCancelClick(){
        tickTimerStop();
        helper.sendAbort();
    }

    protected void timeOnTick(long leftTime) {
        this.leftTime = leftTime;
        setTitle(String.format(Locale.US, "%s ( %d )", getString(R.string.signature), leftTime));
    }

    protected long getTickTimeout() {
        if (timeOut == 0)
            timeOut = 30000;
        return timeOut / 1000;
    }

    protected void onTimerFinish() {
        if (leftTime > 0) {
            leftTime = leftTime - 1;
            setTitle(String.format(Locale.US, "%s ( %d )", getString(R.string.signature), leftTime));
        }
        tickTimerStop();
        if (leftTime == 0)
            helper.sendTimeout();
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
    public void onShowAmount(long l) {
        displayAmount = l;

        if (isPoint) {
            amountPrompt.setText(R.string.history_detail_point);
            amountTv.setText(String.valueOf(l));
        } else {
            amountTv.setText(CurrencyConverter.convert(l, ""));
        }
        amountLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShowCurrency(@Nullable String s, boolean isPoint) {
        this.isPoint = isPoint;
        if (!isPoint) {
            CurrencyConverter.setDefCurrency(s);
        }
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    @Override
    public void onShowTimeout(long timeoutMs) {
        this.timeOut = timeoutMs;
        restartTimer();
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

    @Override
    public void onShowCancelButton(boolean enableCancel) {
        if (enableCancel) {
            cancelBtn.setVisibility(View.VISIBLE);
        } else
            cancelBtn.setVisibility(View.GONE);
    }

    @Override
    public void onShowSignatureLine(@Nullable String line1, @Nullable String line2) {
        if (!TextUtils.isEmpty(line1)){
            lineTv1.setText(line1);
            lineLayout1.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(line2)){
            lineTv2.setText(line2);
            lineLayout2.setVisibility(View.VISIBLE);
        }
    }

    private class AbortReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//            Logger.d("AbortReceiver " + intent.getAction());
            if (POSLINK_REQUEST_ACTION_ABORT_TRANSACTION.equals(intent.getAction())){
                tickTimerStop();
                if (helper!= null)
                    helper.sendAbort();
            }  else if (POSLINK_REQUEST_ACTION_RESET.equals(intent.getAction())){
                finish();
            }
        }
    }
}
