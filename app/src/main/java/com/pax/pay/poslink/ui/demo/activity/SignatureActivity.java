package com.pax.pay.poslink.ui.demo.activity;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pax.pay.poslink.ui.demo.R;
import com.pax.pay.poslink.ui.demo.base.ElectronicSignatureView;
import com.pax.pay.poslink.ui.demo.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.SignatureHelper;

import java.util.List;
import java.util.Locale;

public class SignatureActivity extends AppCompatActivity implements View.OnClickListener, SignatureHelper.ISignatureListener {

    TextView amountTv;
    LinearLayout amountLayout;
    ElectronicSignatureView mSignatureView;

    RelativeLayout writeUserName;

    Button clearBtn;
    Button confirmBtn;
    Button cancelBtn;

    private long displayAmount;
    private String currencyName;
    private Locale locale;
    private boolean processing = false;

    private SignatureHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authgraph_layout);

        amountTv = (TextView) findViewById(R.id.trans_amount_tv);
        amountLayout = (LinearLayout) findViewById(R.id.trans_amount_layout);
        writeUserName = (RelativeLayout) findViewById(R.id.writeUserNameSpace);
        clearBtn = (Button) findViewById(R.id.clear_btn);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);

        cancelBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
        confirmBtn.requestFocus();
        // 内置签名板
        mSignatureView = new ElectronicSignatureView(SignatureActivity.this);
        mSignatureView.setBitmap(new Rect(0, 0, 384, 128), 0, Color.WHITE);
        writeUserName.addView(mSignatureView);
        helper = new SignatureHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());
        ActivityLocalManager.getInstance().addActivity(this);
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.clear_btn) {
            if (isProcessing()) {
                return;
            }
            setProcessFlag();
            //restartTimer();
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

                //Bitmap bitmap = mSignatureView.save(true, 1);
                List<float[]> pathPos = mSignatureView.getPathPos();
                int length = 0;
                for (float[] ba : pathPos) {
                    length += ba.length;
                }
                short[] total = new short[length];
                int index = 0;
                for (float[] ba : pathPos) {
                    for (float b : ba) {
                        total[index++] = (short) b;
                    }
                }
                Log.i("SignatureActivity", "total Length = " + total.length);

                clearProcessFlag();
                //mSignatureHandler.sendNext(EUIMessageKey.INTENT_KEY_SIGNATURE, total);
                helper.sendNext(total);
            } finally {
                confirmBtn.setClickable(true);
            }
        } else if (i == R.id.cancel_btn) {
            helper.sendAbort();

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        }
        return true;
    }

    @Override
    protected void onStop() {
        moveTaskToBack(false);
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        helper.stop();
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
    public void onShowAmount(long amount) {
        displayAmount = amount;
        if (displayAmount != 0) {
            amountTv.setText(String.valueOf(amount));
        } else {
            amountLayout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {
    }


    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, boolean isDemo) {

    }

    @Override
    public void onShowTimeout(long timeout) {

    }
}
