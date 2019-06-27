package com.pax.pay.ui.def_ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pax.pay.ui.def_ui.eventbus.EventBusConstant;
import com.pax.pay.ui.def_ui.eventbus.EventBusUtil;
import com.pax.pay.ui.def_ui.utils.CurrencyCode;
import com.pax.pay.ui.def_ui.utils.CurrencyConverter;
import com.pax.pay.ui.def_ui.utils.EnterDataLineHelper;
import com.pax.pay.ui.def_ui.utils.ToastHelper;
import com.pax.us.pay.ui.core.RespMessage;
import com.pax.us.pay.ui.core.UIMessageManager;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.helper.AmountHelper;

import java.util.Locale;

public class EnterAmountActivity extends AppCompatActivity implements View.OnClickListener, IMessageListener, ICurrencyListener {

    TextView promptTv;
    EditText mEditText;
    Button confirmBtn;

    private long amount;
    private String currencyName;
    private int minLen, maxLen;
    private Locale locale;

    private AmountHelper helper = new AmountHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);

        promptTv = (TextView) findViewById(R.id.prompt_tv);
        mEditText = (EditText) findViewById(R.id.data_edt);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        //transAmountLayout = (LinearLayout) findViewById(R.id.trans_amount_layout);
        confirmBtn.setOnClickListener(this);
        //mEditText.
        promptTv.setText(getResources().getText(R.string.prompt_input_amount));
        minLen = 0;
        maxLen = 300;
        mEditText.setCursorVisible(false);
        mEditText.requestFocus();
        mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        //mEditText.setOnKey
        mEditText.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
        }, 200);

        UIMessageManager.getInstance().registerUI(this, this, helper, getIntent(), new IRespStatus() {
            @Override
            public void onAccepted() {
                EventBusUtil.postEvent(EventBusConstant.END_EVENT);
                finish();
            }

            @Override
            public void onDeclined(RespMessage respMessage) {
                String buff = "Request Declined\n Error Code:" + respMessage.getResultCode() + "\n Error Msg: " + respMessage.getResultMsg();
                //Toast.makeText(this, buff, Toast.LENGTH_LONG).show();
                ToastHelper.showMessage(EnterAmountActivity.this, buff);
            }
        });
    }


    @Override
    public void onClick(View view) {
        Long amount = CurrencyConverter.parse(mEditText.getText().toString(), locale);
        helper.sendNext(amount);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            Long amount = CurrencyConverter.parse(mEditText.getText().toString(), locale);
            helper.sendNext(amount);
        }
        return false;
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

    @Override
    public void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        EnterDataLineHelper.setEditTextAmount(this, mEditText, maxLen, 0);
    }

    @Override
    public void onShowCurrency(String currency) {
        if (currency != null) {
            String countryName = CurrencyCode.findTypeByCurrencyNmae(currency).getCurrencyName();
            locale = CurrencyConverter.findLocalByCountryName(countryName);
            CurrencyConverter.setDefCurrency(countryName);
        }
    }

    @Override
    public void onShowMessage(String message) {
        if (message != null && !message.equals(""))
            promptTv.setText(message);
    }
}
