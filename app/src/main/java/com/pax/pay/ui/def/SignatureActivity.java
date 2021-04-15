package com.pax.pay.ui.def;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.base.ElectronicSignatureView;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.component.utils.TickTimer;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.helper.SignatureHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;

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

    RelativeLayout writeUserName;

    Button clearBtn;
    Button confirmBtn;
    Button cancelBtn;

    private long displayAmount;
    private boolean isPoint = false;
    private SignatureHelper helper;
    private boolean processing = false;
    private long timeOut = 0, leftTime = 0;


    private final OnKeyListener onkeyListener = (v, keyCode, event) -> {
        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER) {
            confirmBtn.performClick();
            return true;
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
        writeUserName = findViewById(R.id.writeUserNameSpace);
        clearBtn = findViewById(R.id.clear_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        confirmBtn = findViewById(R.id.confirm_btn);
        lineTv1 = findViewById(R.id.message_prompt_1);
        lineLayout1 = findViewById(R.id.message_layout_1);
        lineTv2 = findViewById(R.id.message_prompt_2);
        lineLayout2 = findViewById(R.id.message_layout_2);
        getTickTimeout();
        navTitle = getString(R.string.signature);
        displayAmount = 0;
        CurrencyConverter.setDefCurrency(CurrencyType.USD);

        helper = new SignatureHelper(this, new RespStatusImpl(this));
    }

    @Override
    protected void initViews() {
        amountLayout.setVisibility(View.INVISIBLE);
        confirmBtn.requestFocus();
        // 内置签名板
        mSignatureView = new ElectronicSignatureView(SignatureActivity.this);
        mSignatureView.setBitmap(new Rect(0, 0, 384, 128), 0, Color.WHITE);
        writeUserName.addView(mSignatureView);
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
    protected boolean onKeyBackDown() {
        tickTimerStop();
        helper.sendAbort();
        return true;
    }

    @Override
    public void onClickProtected(View v) {
        int i = v.getId();
        if (i == R.id.clear_btn) {
            if (isProcessing()) {
                return;
            }
            setProcessFlag();
            restartTimer();
            mSignatureView.clear();
            clearProcessFlag();

        } else if (i == R.id.confirm_btn) {
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
                Log.i("SignatureActivity", "total Length = " + total.length);
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
        } else if (i == R.id.cancel_btn) {
            tickTimerStop();
            helper.sendAbort();

        } else {
        }
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
}
