package com.pax.pay.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.base.BaseActivityWithTickForAction;
import com.pax.pay.ui.constant.EUIMessageKey;
import com.pax.pay.ui.constant.StatusBroadcastAction;
import com.pax.pay.ui.eventbus.EventBusUtil;
import com.pax.pay.ui.handler.InputAccountHandler;
import com.pax.pay.ui.utils.CurrencyCode;
import com.pax.pay.ui.utils.CurrencyConverter;
import com.pax.pay.ui.utils.StringUtils;
import com.pax.pay.ui.view.ClssLight;
import com.pax.pay.ui.view.ClssLightsView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Locale;

public class SearchCardActivity extends BaseActivityWithTickForAction<Byte> {
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


    private String amountLine;
    private String message;
    private String senderPackage;
    private List<String> options;
    private Boolean enableSwipe;
    private Boolean enableInsert;
    private Boolean enableTap;
    private Boolean enableManualEntry;
    private String currencyName;
    private Locale locale;

    InputAccountHandler inputAccountHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);

        EventBusUtil.register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_card;
    }

    @Override
    protected void setListeners() {
        confirmBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void loadParam() {
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
        navTitle = "";
        getTickTimeout();
        message = getIntent().getStringExtra(EUIMessageKey.INTENT_KEY_MESSAGE);
        senderPackage = getIntent().getStringExtra(EUIMessageKey.KEY_SENDER_PACKAGE);
        options = getIntent().getStringArrayListExtra(EUIMessageKey.INTENT_KEY_OPTIONS);

        enableSwipe = getIntent().getBooleanExtra(EUIMessageKey.INTENT_KEY_ENABLE_SWIPE, false);
        enableInsert = getIntent().getBooleanExtra(EUIMessageKey.INTENT_KEY_ENABLE_INSERT, false);
        enableTap = getIntent().getBooleanExtra(EUIMessageKey.INTENT_KEY_ENABLE_TAP, false);
        enableManualEntry = getIntent().getBooleanExtra(EUIMessageKey.INTENT_KEY_ENABLE_MANUAL, false);
        currencyName = getIntent().getStringExtra(EUIMessageKey.IntentKey.CURRENCY);
        if (currencyName == null || currencyName == "") {
            currencyName = "USD";
        }
        String countryName = CurrencyCode.findTypeByCurrencyNmae(currencyName).getCurrencyName();
        locale = CurrencyConverter.findLocalByCountryName(countryName);
        CurrencyConverter.setDefCurrency(countryName);

        amountLine = CurrencyConverter.convert(getIntent().getLongExtra(EUIMessageKey.INTENT_KEY_AMOUNT, 0),
                "", locale);

        inputAccountHandler = new InputAccountHandler(senderPackage);
    }

    @Override
    public void initViews() {
        if (StringUtils.isEmpty(amountLine)) {
            amountLayout.setVisibility(View.INVISIBLE);
        } else {
            amountTv.setText(amountLine);
        }
        tvClssLight.setVisibility(View.INVISIBLE);
        //cardNumEdt.setVisibility(View.INVISIBLE);
        showIsSupportEntryMode();
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
                inputAccountHandler.sendInputArea(cardNumEdt.getWidth(), cardNumEdt.getHeight(), x, y - barHeight);
            }
        });
    }

    private void showIsSupportEntryMode() {
        if (enableManualEntry) { // 是否支持手输卡号
            confirmBtn.setVisibility(View.VISIBLE);
            confirmBtn.setEnabled(true);
        } else {
            confirmBtn.setVisibility(View.INVISIBLE);
        }

        // 支持刷卡
        tvSwipe.setEnabled(enableSwipe);

        // 支持插卡
        tvInsert.setEnabled(enableInsert);

        if (enableTap) {// 支持非接
            tvTap.setEnabled(true);
            tvClssLight.setVisibility(View.VISIBLE);
        } else {
            tvClssLight.setVisibility(View.GONE);
            tvTap.setEnabled(false);
        }
        promptTv.setText(message);
    }

    private boolean isFirst = true;

    @Override
    protected boolean onKeyEnterDown() {
        onConfirmClicked();
        return true;
    }

    @Override
    public final void onClick(View v) {
        int i = v.getId();
        if (i == R.id.confirm_btn) {
            onConfirmClicked();
        }
    }

    private void handleBackForSearchCard() {
        //To do prev step
        inputAccountHandler.sendPrev();
        isSuccLeave = true;
    }

    private void onConfirmClicked() {
        confirmBtn.setClickable(false);
        if (enableManualEntry) {
            inputAccountHandler.sendNext();
        }
    }

    @Override
    protected boolean onKeyBackDown() {
        inputAccountHandler.sendAbort();
        return super.onKeyBackDown();
    }

    // 寻卡成功时，此界面还保留， 在后续界面切换时，还有机会跑到前台，此时按返回键，此activity finish，同时会有两个分支同时进行
    // 如果寻卡成功时， 此标志为true
    private boolean isSuccLeave = false;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAsync(String action) {
        if (TextUtils.isEmpty(action))
            return;
        Log.i("BroadcastReceiver", "clss action :" + action);
        switch (action) {
            case StatusBroadcastAction.CLSS_LIGHT_NOT_READY:
                tvClssLight.setLights(-1, ClssLight.OFF);
                break;
            case StatusBroadcastAction.CLSS_LIGHT_COMPLETED:
                tvClssLight.setLights(2, ClssLight.BLINK);
                break;
            case StatusBroadcastAction.CLSS_LIGHT_ERROR:
                tvClssLight.setLights(3, ClssLight.BLINK);
                break;
            case StatusBroadcastAction.CLSS_LIGHT_IDLE:
                tvClssLight.setLights(0, ClssLight.BLINK);
                break;
            case StatusBroadcastAction.CLSS_LIGHT_PROCESSING:
                tvClssLight.setLights(1, ClssLight.ON);
                break;
            case StatusBroadcastAction.CLSS_LIGHT_READY_FOR_TXN:
                tvClssLight.setLights(0, ClssLight.ON);
                break;
            case StatusBroadcastAction.CLSS_LIGHT_REMOVE_CARD:
                tvClssLight.setLights(2, ClssLight.ON);
                break;
        }
    }


}
