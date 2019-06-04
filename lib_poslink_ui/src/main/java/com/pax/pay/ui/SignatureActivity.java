package com.pax.pay.ui;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.pax.pay.ui.base.BaseActivityWithTickForAction;
import com.pax.pay.ui.base.ElectronicSignatureView;
import com.pax.pay.ui.constant.EUIMessageKey;
import com.pax.pay.ui.handler.SignatureHandler;
import com.pax.pay.ui.utils.StringUtils;

import java.util.List;

public class SignatureActivity extends BaseActivityWithTickForAction<byte[]> {
//    @BindView(R2.id.trans_amount_tv)
//    TextView amountText;

    ElectronicSignatureView mSignatureView;

    RelativeLayout writeUserName;

    Button clearBtn;
    Button confirmBtn;
    Button cancelBtn;

    private long amount;
    private String currentAction;
    private String message;
    private String senderPackage;
    private List<String> options;
    private SignatureHandler mSignatureHandler;

    private boolean processing = false;

    private OnKeyListener onkeyListener = (v, keyCode, event) -> {
        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER) {
            confirmBtn.performClick();
            return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_authgraph_layout;
    }

    @Override
    protected void loadParam() {
        writeUserName = (RelativeLayout) findViewById(R.id.writeUserNameSpace);
        clearBtn = (Button) findViewById(R.id.clear_btn);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        getTickTimeout();
        navTitle = getString(R.string.signature);
        amount = 0;
        message = getIntent().getStringExtra(EUIMessageKey.INTENT_KEY_MESSAGE);
        senderPackage = getIntent().getStringExtra(EUIMessageKey.KEY_SENDER_PACKAGE);
        options = getIntent().getStringArrayListExtra(EUIMessageKey.INTENT_KEY_OPTIONS);
        currentAction = getIntent().getAction();
        mSignatureHandler = new SignatureHandler(this);
        //APP_PATH =  getApplicationContext().getFilesDir().getAbsolutePath() + File.separator;
        //VM_PATH =  APP_PATH + "data" + File.separator;
    }

    @Override
    protected void initViews() {
        //amountText.setText(CurrencyConverter.convert(amount));
        confirmBtn.requestFocus();
        // 内置签名板
        mSignatureView = new ElectronicSignatureView(SignatureActivity.this);
        mSignatureView.setBitmap(new Rect(0, 0, 384, 128), 0, Color.WHITE);
        writeUserName.addView(mSignatureView);
        //deleteSignFile();
    }

    @Override
    protected void setListeners() {
        mSignatureView.setOnKeyListener(onkeyListener);
        cancelBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
    }

    @Override
    protected boolean onKeyBackDown() {
        mSignatureHandler.sendAbort();
        return true;
    }


    @Override
    public void onClick(View v) {
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

                //Bitmap bitmap = mSignatureView.save(true, 1);
                List<float[]> pathPos = mSignatureView.getPathPos();
                int length = 0;
                for (float[] ba : pathPos) {
                    length += ba.length;
                }
                Log.i("SignatureActivity", "Sign Length = " + length);
                short[] total = new short[length];
                int index = 0;
                for (float[] ba : pathPos) {
                    for (float b : ba) {
                        total[index++] = (short) b;
                    }
                }
//              byte[] data = saveSignFile(bitmap);
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
                int width = 320;
                int height = 400;
                int idx = 0;
                total = new short[width * height * 2];
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        total[2 * idx] = (short) x;
                        total[2 * idx + 1] = (short) y;
                        idx++;
                    }
                }
                mSignatureHandler.sendNext(EUIMessageKey.INTENT_KEY_SIGNATURE, total);
            } finally {
                confirmBtn.setClickable(true);
            }
        } else if (i == R.id.cancel_btn) {
            mSignatureHandler.sendAbort();

        } else {
        }
    }

    @Override
    protected void timeOnTick(long leftTime) {
        setTitle(StringUtils.format("%s ( %d )", getString(R.string.signature), leftTime));
    }

    @Override
    protected long getTickTimeout() {
        long timer = 3600;
        if (timer == 0) {
            timer = 30;
        } else {
            timer /= 10;
        }
        return timer;
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

}
