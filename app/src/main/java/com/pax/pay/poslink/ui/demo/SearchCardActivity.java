package com.pax.pay.poslink.ui.demo;

import android.TextUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.poslink.ui.demo.base.RespStatusImpl;
import com.pax.pay.poslink.ui.demo.event.ClssLightEvent;
import com.pax.pay.poslink.ui.demo.event.EventBusUtil;
import com.pax.pay.poslink.ui.demo.utils.CurrencyCode;
import com.pax.pay.poslink.ui.demo.utils.CurrencyConverter;
import com.pax.pay.poslink.ui.demo.view.ClssLight;
import com.pax.pay.poslink.ui.demo.view.ClssLightsView;
import com.pax.us.pay.ui.constant.status.ClssLightStatus;
import com.pax.us.pay.ui.core.UIMessageManager;
import com.pax.us.pay.ui.core.api.IAmountListener;
import com.pax.us.pay.ui.core.api.ICardListener;
import com.pax.us.pay.ui.core.api.ICurrencyListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.helper.SecurityHelper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

public class SearchCardActivity extends AppCompatActivity implements View.OnClickListener, IMessageListener, ICurrencyListener, IAmountListener, ICardListener {

    TextView promptTv;
    EditText cardNumEdt;
    ClssLightsView tvClssLight;
    TextView amountTv;
    Button confirmBtn;
    LinearLayout amountLayout;
    TextView totalView;
    TextView tvSwipe;
    TextView tvInsert;
    TextView tvTap;

    private long displayAmount;
    private String currencyName = null;
    private int minLen, maxLen;
    private Locale locale;

    private SecurityHelper helper = new SecurityHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_card);
        EventBusUtil.register(this);

        promptTv = (TextView) findViewById(R.id.prompt_tv);
        cardNumEdt = (EditText) findViewById(R.id.card_number_edit);
        tvClssLight = (ClssLightsView) findViewById(R.id.clssLight);
        amountTv = (TextView) findViewById(R.id.amount_prompt_tv);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        amountLayout = (LinearLayout) findViewById(R.id.amount_prompt_layout);
        totalView = (TextView) findViewById(R.id.total_text);
        tvSwipe = (TextView) findViewById(R.id.tv_swipe);
        tvInsert = (TextView) findViewById(R.id.tv_insert);
        tvTap = (TextView) findViewById(R.id.tv_tap);
        confirmBtn.setOnClickListener(this);

        tvClssLight.setVisibility(View.INVISIBLE);

        promptTv.setText(getResources().getText(R.string.hint_enter_account));
        UIMessageManager.getInstance().registerUI(this, this, helper, getIntent(), new RespStatusImpl(this));
    }


    @Override
    public void onClick(View view) {
        helper.sendNext();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            helper.sendNext();
        }
        return false;
    }

    @Override
    protected void onStop() {
        //moveTaskToBack(true);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        UIMessageManager.getInstance().unregisterUI(this, helper);
        super.onDestroy();
    }

    @Override
    public void onShowCard(boolean enableManualEntry, boolean enableSwipe, boolean enableInsert, boolean enableTap) {
        if (enableManualEntry) { // Enable Input Card Number by Manual
            confirmBtn.setVisibility(View.VISIBLE);
            confirmBtn.setEnabled(true);
        } else {
            promptTv.setVisibility(View.INVISIBLE);
            confirmBtn.setVisibility(View.INVISIBLE);
        }

        // Enable Swipe Card
        tvSwipe.setEnabled(enableSwipe);

        // Enable Insert Card
        tvInsert.setEnabled(enableInsert);

        // Enable Tap Card
        if (enableTap) {
            tvTap.setEnabled(true);
            tvClssLight.setVisibility(View.VISIBLE);
        } else {
            tvClssLight.setVisibility(View.GONE);
            tvTap.setEnabled(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewTreeObserver observer = cardNumEdt.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cardNumEdt.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int[] location = new int[2];
                cardNumEdt.getLocationInWindow(location);
                int x = location[0];
                int y = location[1];
                int barHeight = 0;
                int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    barHeight = getResources().getDimensionPixelSize(resourceId);
                }
                helper.setSecurityArea(x, y - barHeight, cardNumEdt.getWidth(), cardNumEdt.getHeight());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAsync(ClssLightEvent event) {
        if (event != null && TextUtils.isEmpty((String) event.getStatus()))
            return;
        Log.i("StatusReceiver", "EVENTBUS action :" + action);
        switch (action) {
            case ClssLightStatus.CLSS_LIGHT_NOT_READY:
                tvClssLight.setLights(-1, ClssLight.OFF);
                break;
            case ClssLightStatus.CLSS_LIGHT_COMPLETED:
                tvClssLight.setLights(2, ClssLight.BLINK);
                break;
            case ClssLightStatus.CLSS_LIGHT_ERROR:
                tvClssLight.setLights(3, ClssLight.BLINK);
                break;
            case ClssLightStatus.CLSS_LIGHT_IDLE:
                tvClssLight.setLights(0, ClssLight.BLINK);
                break;
            case ClssLightStatus.CLSS_LIGHT_PROCESSING:
                tvClssLight.setLights(1, ClssLight.ON);
                break;
            case ClssLightStatus.CLSS_LIGHT_READY_FOR_TXN:
                tvClssLight.setLights(0, ClssLight.ON);
                break;
            case ClssLightStatus.CLSS_LIGHT_REMOVE_CARD:
                tvClssLight.setLights(2, ClssLight.ON);
                break;
            case ClssLightStatus.CLSS_LIGHT_BLUE_ON:
                tvClssLight.setLight(0, ClssLight.ON);
                break;
            case ClssLightStatus.CLSS_LIGHT_BLUE_OFF:
                tvClssLight.setLight(0, ClssLight.OFF);
                break;
            case ClssLightStatus.CLSS_LIGHT_BLUE_BLINK:
                tvClssLight.setLight(0, ClssLight.BLINK);
                break;
            case ClssLightStatus.CLSS_LIGHT_YELLOW_ON:
                tvClssLight.setLight(1, ClssLight.ON);
                break;
            case ClssLightStatus.CLSS_LIGHT_YELLOW_OFF:
                tvClssLight.setLight(1, ClssLight.OFF);
                break;
            case ClssLightStatus.CLSS_LIGHT_YELLOW_BLINK:
                tvClssLight.setLight(1, ClssLight.BLINK);
                break;
            case ClssLightStatus.CLSS_LIGHT_GREEN_ON:
                tvClssLight.setLight(2, ClssLight.ON);
                break;
            case ClssLightStatus.CLSS_LIGHT_GREEN_OFF:
                tvClssLight.setLight(2, ClssLight.OFF);
                break;
            case ClssLightStatus.CLSS_LIGHT_GREEN_BLINK:
                tvClssLight.setLight(2, ClssLight.BLINK);
                break;
            case ClssLightStatus.CLSS_LIGHT_RED_ON:
                tvClssLight.setLight(3, ClssLight.ON);
                break;
            case ClssLightStatus.CLSS_LIGHT_RED_OFF:
                tvClssLight.setLight(3, ClssLight.OFF);
                break;
            case ClssLightStatus.CLSS_LIGHT_RED_BLINK:
                tvClssLight.setLight(3, ClssLight.BLINK);
                break;
        }
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
        if (message != null && !message.equals("")) {
            promptTv.setText(message);
        }
    }
}
