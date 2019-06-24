package com.pax.pay.ui.def_ui;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pax.pay.ui.def_ui.base.ElectronicSignatureView;
import com.pax.pay.ui.def_ui.eventbus.EventBusConstant;
import com.pax.pay.ui.def_ui.eventbus.EventBusUtil;
import com.pax.pay.ui.def_ui.utils.CurrencyCode;
import com.pax.pay.ui.def_ui.utils.CurrencyConverter;
import com.pax.pay.ui.def_ui.utils.ToastHelper;
import com.pax.us.pay.ui.base.message.RespMessage;
import com.pax.us.pay.ui.base.message.UIMessageManager;
import com.pax.us.pay.ui.base.message.api.IAmountListener;
import com.pax.us.pay.ui.base.message.api.ICurrencyListener;
import com.pax.us.pay.ui.base.message.api.IMessageListener;
import com.pax.us.pay.ui.base.message.api.IRespStatus;
import com.pax.us.pay.ui.base.message.helper.SignatureHelper;

import java.util.List;
import java.util.Locale;

public class SignatureActivity extends AppCompatActivity implements View.OnClickListener, IMessageListener, ICurrencyListener, IAmountListener {

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

    private SignatureHelper helper = new SignatureHelper();

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

        UIMessageManager.getInstance().registerUI(this, this, helper, getIntent(), new IRespStatus() {
            @Override
            public void respAccept() {
                EventBusUtil.postEvent(EventBusConstant.END_EVENT);
                finish();
            }

            @Override
            public void respDecline(RespMessage respMessage) {
                String buff = "Request Declined\n Error Code:" + respMessage.getResultCode() + "\n Error Msg: " + respMessage.getResultMsg();
                //Toast.makeText(this, buff, Toast.LENGTH_LONG).show();
                ToastHelper.showMessage(SignatureActivity.this, buff);
            }

            @Override
            public void respComplete() {
                finish();
            }
        });
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
                helper.sendObjNext(total);
            } finally {
                confirmBtn.setClickable(true);
            }
        } else if (i == R.id.cancel_btn) {
            helper.sendAbort();

        } else {
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
        moveTaskToBack(true);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        UIMessageManager.getInstance().unregisterUI(this, helper);
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
            amountTv.setText(CurrencyConverter.convert(amount, "", locale));
        } else {
            amountLayout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onShowCurrency(String currency) {
        if (currency != null && !currency.equals("")) {
            currencyName = currency;
        }
        if (currencyName != null) {
            String countryName = CurrencyCode.findTypeByCurrencyNmae(currency).getCurrencyName();
            locale = CurrencyConverter.findLocalByCountryName(countryName);
            CurrencyConverter.setDefCurrency(countryName);
        }
    }

    @Override
    public void onShowMessage(String message) {

    }
}
