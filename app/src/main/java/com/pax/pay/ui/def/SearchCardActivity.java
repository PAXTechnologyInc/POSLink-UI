package com.pax.pay.ui.def;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.base.SecurityRespStatusImpl;
import com.pax.pay.ui.def.eventbus.AmountEvent;
import com.pax.pay.ui.def.eventbus.CardEvent;
import com.pax.pay.ui.def.eventbus.ClssLightEvent;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.pax.pay.ui.def.eventbus.SecurityEvent;
import com.pax.pay.ui.def.utils.GifLoadOneTimeGif;
import com.pax.pay.ui.def.view.ClssLight;
import com.pax.pay.ui.def.view.ClssLightsView;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.constant.entry.enumeration.PanStyles;
import com.pax.us.pay.ui.constant.status.CardStatus;
import com.pax.us.pay.ui.constant.status.ClssLightStatus;
import com.pax.us.pay.ui.constant.status.InformationStatus;
import com.pax.us.pay.ui.constant.status.SecurityStatus;
import com.pax.us.pay.ui.core.helper.SearCardHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.utils.StringUtils;
import com.paxus.utils.log.Logger;
import com.paxus.view.utils.ViewUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;

import static com.bumptech.glide.load.resource.gif.GifDrawable.LOOP_FOREVER;

public class SearchCardActivity extends BaseAppActivity implements SearCardHelper.ISearchCardListener {
    TextView merchantTv;
    LinearLayout merchantLayout;
    TextView promptTv;
    TextView cardNumEdt;
    ClssLightsView tvClssLight;
    TextView amountTv;
    Button confirmBtn;
    Button scanBtn;
    LinearLayout amountLayout;
    TextView totalView;
    LinearLayout cardLayout;
//    TextView tvSwipe;
//    TextView tvInsert;
//    TextView tvTap;
    LinearLayout logoLayout;
    ImageView ivApple;
    ImageView ivGoogle;
    ImageView ivSamsung;
    ImageView ivTap;

    private SwitchCompat a11ySwitch;
    private AppCompatTextView a11ySwitchText;


    private boolean enableManualEntry;
    //private boolean enableTaplEntry =true;
    private long totalAmount;
    private String amountMessage;
    private String merchantName;

    private String amountLine;
    private boolean isPoint;
    private SearCardHelper helper;
    private boolean enableContactlessLight = true;
    private int panLength;
    private String accessibilityPinPadMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);

        EventBusUtil.register(this);
    }

    @Override
    protected int getLayoutId() {
        if (Build.MODEL.equals("A30")) {
            return R.layout.activity_search_card_a30;
        }
        return R.layout.activity_search_card_default;
    }

    @Override
    protected void setListeners() {
        confirmBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        Logger.d( "SearchCardActivity onDestroy notifyObservers false");
        notifyObservers(false);
        EventBusUtil.unregister(this);
        super.onDestroy();
    }

    @Override
    protected void loadParam() {
        merchantTv = findViewById(R.id.merchant_prompt_tv);
        merchantLayout = findViewById(R.id.merchant_prompt_layout);
        promptTv = findViewById(R.id.prompt_tv);
        cardNumEdt = findViewById(R.id.card_number_edit);
        tvClssLight = findViewById(R.id.clssLight);
        amountTv = findViewById(R.id.amount_prompt_tv);
        confirmBtn = findViewById(R.id.confirm_btn);
        amountLayout = findViewById(R.id.amount_prompt_layout);
        totalView = findViewById(R.id.total_text);
        cardLayout = findViewById(R.id.supported_card_prompt);
        logoLayout = findViewById(R.id.search_paylogo_layout);
        ivApple = findViewById(R.id.iv_apple);
        ivGoogle = findViewById(R.id.iv_google);
        ivSamsung = findViewById(R.id.iv_samsung);
        ivTap = findViewById(R.id.iv_tap);
        scanBtn = findViewById(R.id.scan_btn);

        tvClssLight.setVisibility(View.INVISIBLE);
        CurrencyConverter.setDefCurrency(CurrencyType.USD);
        totalAmount = 0;
        merchantName = "";
        panLength = 0;
        helper = new SearCardHelper(this, new SecurityRespStatusImpl(this));
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        a11ySwitch = findViewById(R.id.a11ySwitch);
        a11ySwitchText = findViewById(R.id.a11ySwitch_tag_text);
        Bundle bundle = getIntent().getExtras();
        accessibilityPinPadMode = bundle.getString(EntryExtraData.PARAM_ACCESSIBILITY_PIN_PAD_MODE);
        if ("Y".equals(accessibilityPinPadMode)){
            a11ySwitchText.setVisibility(View.VISIBLE);
            a11ySwitch.setVisibility(View.VISIBLE);
            a11ySwitch.setChecked(true);
        } else if ("N".equals(accessibilityPinPadMode)){
            a11ySwitchText.setVisibility(View.VISIBLE);
            a11ySwitch.setVisibility(View.VISIBLE);
            a11ySwitch.setChecked(false);
        }
        a11ySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(StringUtils.isEmpty(accessibilityPinPadMode) || "D".equals(accessibilityPinPadMode)) return;
                if(b){
                    Intent newIntent = new Intent("com.pax.us.pay.setAccessibilityMode");
                    newIntent.putExtra(EntryExtraData.PARAM_ACCESSIBILITY_PIN_PAD_MODE, "Y");
                    sendBroadcast(newIntent);
                }else{
                    Intent newIntent = new Intent("com.pax.us.pay.setAccessibilityMode");
                    newIntent.putExtra(EntryExtraData.PARAM_ACCESSIBILITY_PIN_PAD_MODE, "N");
                    sendBroadcast(newIntent);
                }
            }
        });
    }

    @Override
    public void initViews() {
        //Default don't display logo
        ivApple.setVisibility(View.GONE);
        ivGoogle.setVisibility(View.GONE);
        ivSamsung.setVisibility(View.GONE);
        ivTap.setVisibility(View.GONE);
        scanBtn.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        helper.start(this, getIntent());
        super.onStart();
    }

    @Override
    public void onNewIntent(Intent intent) {
        setIntent(intent);
        helper.start(this, intent);
        super.onNewIntent(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.i("ReceiptFragment", "SearchCardActivity onResume notifyObservers " + (enableTaplEntry ? "true" : "false"));
        //notifyObservers(enableTaplEntry);
    }

    @Override
    public void onStop() {
        Logger.d("SearchCard Helper Stop");
        helper.stop();
        super.onStop();
    }


    @Override
    public void onClickProtected(View view) {
        int i = view.getId();
        if (i == R.id.confirm_btn) {
            onConfirmClicked();
        } else if (i == R.id.scan_btn) {
            Intent intent = new Intent(EntryRequest.ACTION_START_SCAN);
            intent.setPackage(intent.getStringExtra(EntryExtraData.PARAM_PACKAGE));
            intent.putExtra(EntryRequest.PARAM_ACTION, intent.getAction());
            sendBroadcast(intent);
        }
    }

    private void handleBackForSearchCard() {
        //To do prev step
        helper.sendPrev();
        isSuccLeave = true;
    }

    private void onConfirmClicked() {
        if (enableManualEntry) {
            helper.sendNext();
        }
    }

    @Override
    protected boolean onKeyBackDown() {
        helper.sendAbort();
        return true;
    }

    private void updateCardLayout(boolean enableSwipe, boolean enableInsert, boolean enableTap, boolean enableManual) {
        cardLayout.removeAllViews();

        int swipeId = R.drawable.selection_swipe_card_a920;
        int insertId = R.drawable.selection_insert_card_a920;
        int tapId = R.drawable.selection_tap_card_a920;

        String deviceModel = Build.MODEL;

        if ("M50".equals(deviceModel) || "M8".equals(deviceModel)){
            swipeId = R.raw.insert_card_m50;
            insertId = R.raw.insert_card_m50;
            tapId = R.raw.tap_card_m50;
            int manualId =R.raw.enter_card_m50;
            int insertTapId = R.raw.insert_tap_card_m50;
            if (enableSwipe)
                loadImage(swipeId, enableSwipe);
            if (enableInsert && enableTap) {
                loadImage(insertTapId, enableInsert);
            } else if (enableInsert) {
                loadImage(insertId, enableInsert);
            } else if (enableTap) {
                loadImage(tapId, enableTap);
            }
            if (enableManual)
                loadImage(swipeId, enableSwipe);

        } else {
            if ("A60".equals(deviceModel)) {
                swipeId = R.drawable.selection_swipe_card_a60;
                insertId = R.drawable.selection_insert_card_a60;
            } else if ("Aries8".equals(deviceModel)) {
                swipeId = R.drawable.selection_swipe_card_ar_x;
                insertId = R.drawable.selection_insert_card_ar_x;
                tapId = R.drawable.selection_tap_card_ar_x;
            } else if ("Aries6".equals(deviceModel)){
                swipeId = R.drawable.selection_swipe_card_ar6;
                insertId = R.drawable.selection_insert_card_ar6;
                tapId = R.drawable.selection_tap_card_ar6;
            } else if ("A80".equals(deviceModel)) {
                swipeId = R.drawable.selection_swipe_card_a80;
            } else if ("A930".equals(deviceModel)) {
                swipeId = R.drawable.selection_swipe_card_a930;
            } else if ("A77".equals(deviceModel)) {
                swipeId = R.drawable.selection_swipe_card_a77;
                insertId = R.drawable.selection_insert_card_a77;
                tapId = R.drawable.selection_tap_card_a77;
            } else if ("PX7A".equals(deviceModel)) {
                swipeId = R.drawable.selection_swipe_card_px7a;
            } else if ("IM30".equals(deviceModel)) {
                swipeId = R.drawable.selection_swipe_card_im30;
                insertId = R.drawable.selection_insert_card_im30;
                tapId = R.drawable.selection_tap_card_im30;
            } else if ("A30".equals(deviceModel)) {
                swipeId = R.drawable.selection_swipe_card_a30;
                insertId = R.drawable.selection_insert_card_a30;
                tapId = R.drawable.selection_tap_card_a30;
            } else if ("A35".equals(deviceModel)) {
                swipeId = R.drawable.selection_swipe_card_a35;
                insertId = R.drawable.selection_insert_card_a35;
                tapId = R.drawable.selection_tap_card_a35;
            }
            // 支持刷卡
            //tvSwipe.setEnabled((mode & SearchMode.SWIPE) == SearchMode.SWIPE);
            addCardImage(getString(R.string.swipe_action), swipeId, enableSwipe);

            // 支持插卡
            //tvInsert.setEnabled((mode & SearchMode.INSERT) == SearchMode.INSERT);
            addCardImage(getString(R.string.insert_action), insertId, enableInsert);

            addCardImage(getString(R.string.tap_action), tapId, enableTap);
        }
    }

    private void addCardImage(String text, @DrawableRes int resId, boolean enable) {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        params.setMargins(4, 4, 4, 4);
        textView.setLayoutParams(params);
        textView.setPadding(4, 4, 4, 4);
        textView.setGravity(Gravity.BOTTOM | Gravity.CENTER);
        textView.setText(text);
        textView.setBackgroundResource(resId);
        textView.setEnabled(enable);
//        textView.setTextSize(getResources().getDimension(R.dimen.font_size_label_card_entry_logo));
        cardLayout.addView(textView);
    }

    private void loadImage(@RawRes int resId, boolean enable) {
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(4, 4, 4, 4);
        imageView.setLayoutParams(params);
        imageView.setPadding(4, 4, 4, 4);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        GifLoadOneTimeGif.loadOneTimeGif(this, resId, imageView, LOOP_FOREVER, new GifLoadOneTimeGif.GifListener() {
            @Override
            public void gifPlayComplete() {
                //Logger.d("VisaAnimationActivity gifPlayComplete");
                //helper.sendNext(true);
            }
        });
        cardLayout.addView(imageView);
    }


    // 寻卡成功时，此界面还保留， 在后续界面切换时，还有机会跑到前台，此时按返回键，此activity finish，同时会有两个分支同时进行
    // 如果寻卡成功时， 此标志为true
    private boolean isSuccLeave = false;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAsync(ClssLightEvent event) {
        if (event == null)
            return;
        String action = event.getStatus();
        Logger.d("clss action :" + action);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            notifyObservers(action);
        } else {
            switch (action) {
                case ClssLightStatus.CLSS_LIGHT_COMPLETED:
                case ClssLightStatus.CLSS_LIGHT_NOT_READY: //Fix ANBP-383, ANFDRC-319
                    tvClssLight.setLights(-1, ClssLight.OFF);
                    break;
                case ClssLightStatus.CLSS_LIGHT_ERROR:
                    tvClssLight.setLight(0, ClssLight.OFF);
                    tvClssLight.setLight(1, ClssLight.OFF);
                    tvClssLight.setLight(2, ClssLight.OFF);
                    tvClssLight.setLight(3, ClssLight.ON);
                    break;
                case ClssLightStatus.CLSS_LIGHT_IDLE:
                    tvClssLight.setLights(0, ClssLight.BLINK);
                    break;
                case ClssLightStatus.CLSS_LIGHT_PROCESSING:
                    tvClssLight.setLight(0, ClssLight.ON);
                    tvClssLight.setLight(1, ClssLight.ON);
                    tvClssLight.setLight(2, ClssLight.OFF);
                    tvClssLight.setLight(3, ClssLight.OFF);
                    break;
                case ClssLightStatus.CLSS_LIGHT_READY_FOR_TXN:
                    tvClssLight.setLight(0, ClssLight.ON);
                    tvClssLight.setLight(1, ClssLight.OFF);
                    tvClssLight.setLight(2, ClssLight.OFF);
                    tvClssLight.setLight(3, ClssLight.OFF);
                    break;
                case ClssLightStatus.CLSS_LIGHT_REMOVE_CARD:
                    tvClssLight.setLight(0, ClssLight.ON);
                    tvClssLight.setLight(1, ClssLight.ON);
                    tvClssLight.setLight(2, ClssLight.ON);
                    tvClssLight.setLight(3, ClssLight.OFF);

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
                default:
                    break;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAsync(CardEvent event) {
        Intent intent;
        Bundle bundle;
        if (event == null)
            return;
        String action = event.getStatus();
        Logger.d("card action :" + action);
        switch (action) {
            case CardStatus.CARD_INSERT_REQUIRED:
                //APAR-38
                enableContactlessLight = false;
                notifyObservers(false);
                onShowCard(false, false, true, false);
                logoLayout.setVisibility(View.GONE);
                intent = getIntent();
                intent.putExtra(EntryExtraData.PARAM_ENABLE_MANUAL, false);
                intent.putExtra(EntryExtraData.PARAM_ENABLE_SWIPE, false);
                intent.putExtra(EntryExtraData.PARAM_ENABLE_INSERT, true);
                intent.putExtra(EntryExtraData.PARAM_ENABLE_TAP, false);
                setIntent(intent);
                break;
            case CardStatus.CARD_SWIPE_REQUIRED:
                //APAR-38
                enableContactlessLight = false;
                notifyObservers(false);
                onShowCard(false, true, false, false);
                logoLayout.setVisibility(View.GONE);
                intent = getIntent();
                intent.putExtra(EntryExtraData.PARAM_ENABLE_MANUAL, false);
                intent.putExtra(EntryExtraData.PARAM_ENABLE_SWIPE, true);
                intent.putExtra(EntryExtraData.PARAM_ENABLE_INSERT, false);
                intent.putExtra(EntryExtraData.PARAM_ENABLE_TAP, false);
                setIntent(intent);
                break;
            case CardStatus.CARD_TAP_REQUIRED:
                enableContactlessLight = true;
                onShowCard(false, false, false, true);
                intent = getIntent();
                intent.putExtra(EntryExtraData.PARAM_ENABLE_MANUAL, false);
                intent.putExtra(EntryExtraData.PARAM_ENABLE_SWIPE, false);
                intent.putExtra(EntryExtraData.PARAM_ENABLE_INSERT, false);
                intent.putExtra(EntryExtraData.PARAM_ENABLE_TAP, true);
                setIntent(intent);
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAsync(AmountEvent event) {
        if (event == null)
            return;
        String action = event.getStatus();
        Logger.d("amount action :" + action);
        if (InformationStatus.TRANS_AMOUNT_CHANGED_IN_CARD_PROCESSING.equals(action)) {
            if (isPoint) {
                amountLine = String.valueOf(event.getData());
            } else {
                amountLine = CurrencyConverter.convert(event.getData(), "");
            }
            amountTv.setText(amountLine);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAsync(SecurityEvent event) {
        if (event == null)
            return;
        String action = event.getStatus();
        Logger.d( "SecurityEvent action :" + action);
        switch (action) {
            case SecurityStatus.SECURITY_ENTERING:
                panLength++;
                confirmBtn.setEnabled(true);
                break;
            case SecurityStatus.SECURITY_ENTER_CLEARED:
                panLength = 0;
                confirmBtn.setEnabled(false);
                break;
            case SecurityStatus.SECURITY_ENTER_DELETE:
                panLength --;
                if (panLength==0)
                    confirmBtn.setEnabled(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void onShowCard(boolean b, boolean b1, boolean b2, boolean b3) {
        if (StringUtils.isEmpty(merchantName)) {
            merchantLayout.setVisibility(View.INVISIBLE);
        } else {
            merchantLayout.setVisibility(View.VISIBLE);
        }

        if (totalAmount == 0 && StringUtils.isEmpty(amountMessage)) {
            amountLayout.setVisibility(View.INVISIBLE);
        } else {
            amountLayout.setVisibility(View.VISIBLE);
        }
        enableManualEntry = b;
        if (b) {
            // support input card by manual
            confirmBtn.setVisibility(View.VISIBLE);
            confirmBtn.setEnabled(false);  //BPOSANDJAX-203
        } else {
            promptTv.setVisibility(View.INVISIBLE);
            confirmBtn.setVisibility(View.INVISIBLE);
        }

        //enableTaplEntry = b3;
        updateCardLayout(b1, b2, b3, b);
        if (b3) {
            // enable tap
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Logger.d("SearchCardActivity onShowCard notifyObservers true ");
                if (enableContactlessLight) {
                    notifyObservers(true);
                }

                tvClssLight.setVisibility(View.GONE);
            } else {
                if (enableContactlessLight)
                    tvClssLight.setVisibility(View.VISIBLE);
                else
                    tvClssLight.setVisibility(View.INVISIBLE);
            }
        } else {
            tvClssLight.setVisibility(View.GONE);
            ivApple.setVisibility(View.GONE);
            ivGoogle.setVisibility(View.GONE);
            ivSamsung.setVisibility(View.GONE);
            ivTap.setVisibility(View.GONE);
        }

        //must at here, due to after get enableManualEntry to decide send security area or not
        sendSecurity();
    }

    @Override
    public void onShowScanIcon(boolean enable) {
        if (enable)
            scanBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShowLight(boolean enableContactlessLight) {
        this.enableContactlessLight = enableContactlessLight;
        if (enableContactlessLight) {
            notifyObservers(true);
            tvClssLight.setVisibility(View.VISIBLE);
        } else {
            notifyObservers(false);
            tvClssLight.setVisibility(View.GONE);
        }
    }

    @Override
    public void onShowPanStyle(String panStyles) {
        if (!StringUtils.isEmpty(panStyles)) {
            if (PanStyles.NEW_PAN.equals(panStyles))
                promptTv.setText(R.string.enter_new_account);
            else
                promptTv.setText(R.string.hint_enter_account);
        }
    }

    @Override
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {
        this.isPoint = isPoint;
        if (!isPoint) {
            CurrencyConverter.setDefCurrency(currency);
        }
    }

    @Override
    public void onShowAmount(long l) {
        totalAmount = l;
        if (l == 0) {
            amountLayout.setVisibility(View.INVISIBLE);
        } else {
            amountLayout.setVisibility(View.VISIBLE);
            if (isPoint) {
                totalView.setText(R.string.total_point);
                amountLine = String.valueOf(l);
            } else {
                amountLine = CurrencyConverter.convert(l, "");
            }
            amountTv.setText(amountLine);
        }
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    private void sendSecurity() {
        if (enableManualEntry) {
            ViewTreeObserver observer = cardNumEdt.getViewTreeObserver();
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    cardNumEdt.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    if(Build.MODEL.equals("A35")){
                        new Handler().postDelayed(()-> {
                            sendEditTextLocation();
                        },100);
                    }else{
                        sendEditTextLocation();
                    }
                }
            });
        }

    }

    private void sendEditTextLocation(){
        int[] location = new int[2];
        cardNumEdt.getLocationInWindow(location);
        int x = location[0];
        int y = location[1];
        int barHeight = 0;
        boolean immersiveSticky = (getWindow().getDecorView().getSystemUiVisibility() &
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) > 0;
        if (!immersiveSticky) {
            //area of application
            Rect outRect1 = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);
            barHeight = outRect1.top;  //statusBar's height
        }
        int fontSize = ViewUtils.px2sp(SearchCardActivity.this, cardNumEdt.getTextSize());
        helper.setSecurityArea(x, y - barHeight, cardNumEdt.getWidth(), cardNumEdt.getHeight(), fontSize);
        Logger.d("search card "+x+','+y+','+barHeight);
    }

    @Override
    public void onShowCardPay(boolean enableApplePay, boolean enableGooglePay, boolean enableSumsungPay, boolean enableNFCPay) {
        if (enableApplePay)
            ivApple.setVisibility(View.VISIBLE);
        else
            ivApple.setVisibility(View.GONE);
        if (enableGooglePay)
            ivGoogle.setVisibility(View.VISIBLE);
        else
            ivGoogle.setVisibility(View.GONE);
        if (enableSumsungPay)
            ivSamsung.setVisibility(View.VISIBLE);
        else
            ivSamsung.setVisibility(View.GONE);
        if (enableNFCPay)
            ivTap.setVisibility(View.VISIBLE);
        else
            ivTap.setVisibility(View.GONE);
    }

    @Override
    public void onShowAmountMessage(String amountMessage) {
        if (!StringUtils.isEmpty(amountMessage)) {
            this.amountMessage = amountMessage;
            amountLayout.setVisibility(View.VISIBLE);
            amountTv.setText(amountMessage);
        }
    }

    @Override
    public void onShowMerchant(@Nullable String merchantName) {
        if (!StringUtils.isEmpty(merchantName)) {
            this.merchantName = merchantName;
            merchantLayout.setVisibility(View.VISIBLE);
            merchantTv.setText(merchantName);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && ViewUtils.canNavigationBarImmersiveSticky()) {
            ViewUtils.hideNavigationBar(getWindow().getDecorView());
        }
    }
}
