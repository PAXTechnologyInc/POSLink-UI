package com.pax.pay.ui.def;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseLandActivity;
import com.pax.pay.ui.def.eventbus.ActivityEndEvent;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.pax.pay.ui.def.eventbus.TransparentActivityEndEvent;
import com.pax.pay.ui.def.utils.EnterDataLineHelper;
import com.pax.pay.ui.def.utils.LanguageConvertUtils;
import com.pax.pay.ui.def.utils.RangeFilter;
import com.pax.us.pay.ui.component.keyboard.CustomKeyboardEditText;
import com.pax.us.pay.ui.component.keyboard.KeyBoardStatus;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.enumeration.TransMode;
import com.pax.us.pay.ui.core.api.IHasPhyKeyboardListener;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.utils.StringUtils;
import com.paxus.utils.log.Logger;
import com.paxus.view.dialog.CustomAlertDialog;
import com.paxus.view.dialog.DialogUtils;
import com.paxus.view.quickclick.QuickClickProtection;
import com.paxus.view.utils.ToastHelper;
import com.paxus.view.utils.ViewUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

abstract class EnterAndOptionWithTwoStyleActivity<T> extends BaseLandActivity implements IMessageListener, View.OnClickListener, IHasPhyKeyboardListener {
    private static final int SELECT_AMOUNT = 1;
    private static final int INPUT_AMOUNT = 0;
    private static boolean isDialog;
    /**
     * display Title
     */
    protected String navTitle = "";
    protected boolean navBack = true;
    protected int selected = -1;
    LinearLayout transAmountLayout;
    TextView promptTv;
    LinearLayout inputAmountLayout;
    TextView amountPromptTv;
    TextView amountTv;
    CustomKeyboardEditText mEditText;
    Button confirmBtn;
    LinearLayout selectLayout;
    TextView tvPrompt;
    private Toolbar toolbar;
    private EditTextDataLimit limit;
    private long amount;
    private List<String> mAmountOption;
    private List<String> mDisplayOption;
    private int viewType = 0;
    private boolean isPoint = false;
    private boolean isAmount = true;
    private long amountUnit = 1;
    protected final QuickClickProtection quickClickProtection = QuickClickProtection.getInstance();
    private String titleName;
    private String subOption;
    private Dialog selectDialog = null;
    private boolean hasPhyKeyboard = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base_default);
//        ViewGroup rootLayout = findViewById(R.id.root_layout);
//        if (rootLayout != null) {
//            LayoutInflater.from(this).inflate(getLayoutId(), rootLayout);
//        }
        Logger.i("EnterAndOption", "onCreate");
        if (isDialog) {
            EventBusUtil.register(this);
        } else {
            //TO avoid flash screen
            //new Handler().postDelayed(() -> {
            //Logger.i("EnterAndOption", "stop Previous ActivityEndEvent");
            //EventBusUtil.doEvent(new ActivityEndEvent());
            Logger.i("EnterAndOption", "register " + this);
                EventBusUtil.register(this);
            //}, 200);
        }

        loadExpandedView(savedInstanceState);

        transAmountLayout = findViewById(R.id.trans_amount_layout);
        promptTv = findViewById(R.id.prompt_tv);

        inputAmountLayout = findViewById(R.id.amount_input_layout);
        amountTv = findViewById(R.id.amount_tv);
        amountPromptTv = findViewById(R.id.amount_prompt_tv);
        mEditText = findViewById(R.id.data_edt);
        confirmBtn = findViewById(R.id.confirm_btn);

        selectLayout = findViewById(R.id.amount_option_layout);
        tvPrompt = findViewById(R.id.prompt_select);

        amount = 0;
        initToolbar();
        setListeners();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mEditText.setKeepKeyBoardOn(true);
        // EnterDataLineHelper.setEditTextAmount(this, mEditText, limit);

        mEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onDataConfirmed();
            }
            return false;
        });

        if (isDialog) {
            loadOtherParam();
            amount = 0;
        } else {
            loadOtherParam();
            initViews();
        }
    }


    @Override
    public void setTheme(int resid) {
        String[] options = null;
        if (getIntent().getExtras().containsKey(EntryExtraData.PARAM_TIP_OPTIONS))
            options = getIntent().getExtras().getStringArray(EntryExtraData.PARAM_TIP_OPTIONS);
        else if (getIntent().getExtras().containsKey(EntryExtraData.PARAM_CASHBACK_OPTIONS))
            options = getIntent().getExtras().getStringArray(EntryExtraData.PARAM_CASHBACK_OPTIONS);
        isDialog = (options != null) && (options.length > 0);
        if (isDialog) {
            super.setTheme(resid);
        } else {
            super.setTheme(R.style.AppTheme_NoActionBar);
        }
    }


    public boolean isIsDialog() {
        return isDialog;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_option_default;
    }

    protected void setListeners() {
        confirmBtn.setOnClickListener(this);
    }

    public void setLimit(@NonNull EditTextDataLimit limit, boolean isAmount, Long amountUnit) {
        this.limit = limit;
        this.isAmount = isAmount;
        if (amountUnit != 0)
            this.amountUnit = amountUnit;
        promptTv.setText(limit.prompt);
        if (isAmount) // Arvind: ANBP-702
            EnterDataLineHelper.setEditTextAmount(this, mEditText, limit);
        else
            EnterDataLineHelper.setEditTextNumber(this, mEditText, limit);

        if (KeyBoardStatus.isKeyBoardPopuped())
            mEditText.requestFocus();
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public void setTipName(String tipName, @NonNull EditTextDataLimit limit) {
        this.limit = limit;
        this.titleName = tipName;
        promptTv.setText(limit.prompt);

    }

    public void setAmount(long amount) {
        this.amount = amount;
        amountTv.setText(isPoint ? String.valueOf(amount) : CurrencyConverter.convert(amount, ""));
        if (!isDialog)
            transAmountLayout.setVisibility(View.VISIBLE);
    }

    public void setPoint(boolean point) {
        isPoint = point;
    }

    public void setCurrencyName(String currencyName) {
        if (!TextUtils.isEmpty(currencyName) && isAmount) {
            CurrencyConverter.setDefCurrency(currencyName);
            mEditText.setText(isPoint ? String.valueOf(0L) : CurrencyConverter.convert(0L, ""));
        }
    }

    public void setAmountOption(List<String> amountOption, String subOption, List<String> displayOption) {
        this.mAmountOption = amountOption;
        this.subOption = subOption;
        this.mDisplayOption = displayOption;
        if (mDisplayOption != null) {
            if (!TextUtils.isEmpty(subOption))
                mDisplayOption.add(subOption);
            viewType = SELECT_AMOUNT;
            initDialog();
        }
    }

    private void initDialog() {
        WindowManager.LayoutParams wl = getWindow().getAttributes();
        wl.alpha = 0.0f;//这句就是设置窗口里控件的透明度的．０.０全透明．１.０不透明．
        getWindow().setAttributes(wl);

        inputAmountLayout.setVisibility(View.GONE);
        selectLayout.setVisibility(View.GONE);
        transAmountLayout.setVisibility(View.GONE);
        confirmBtn.setVisibility(View.GONE);
        if (KeyBoardStatus.isKeyBoardPopuped())
            mEditText.requestFocus();

        //setTheme(R.style.ActivityTranslucent);
        //KeyboardUtils.hideSystemKeyboard(this, mEditText);

        String opt = getString(R.string.pls_select);
        String title;
        if (opt.contains(" \\ ")) {
            String[] select = opt.replace(" \\ ", "\\").split("\\\\");
            String[] titles = titleName.replace(" \\ ", "\\").split("\\\\");
            title = select[0] + " " + titles[0] + " \\ " + select[1] + " " + titles[1];
        } else
            title = opt + " " + titleName;


        //String title = getString(R.string.pls_select) + " " + titleName;
        selectDialog = DialogUtils.showSelectDialog(this,
                title,
                (dialog, which) -> {
                    if (mDisplayOption.get(which).equals(subOption)) {
                        new Handler().postDelayed(() -> {
                            dialog.dismiss();
                        }, 100);
                        viewType = INPUT_AMOUNT;
                        initInputBackground();
                        initInputView();
                    } else {
                        selected = which;
                        onDataConfirmed();
                        dialog.dismiss();
                        finish();
                    }
                },
                true,
                dialog -> {
                    dialog.dismiss();
                    finish();
                    sendAbort();
                },
                true,
                mDisplayOption.toArray(new String[0]),
                -1
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        quickClickProtection.stop();

        new Handler().post(() -> {
        //if (ExchangeData.getTransDataMap() != null)
        //notifyObservers(ExchangeData.getTransDataMap() );

            SharedPreferences preferences = getSharedPreferences(EntryRequest.class.getName(), Context.MODE_PRIVATE);
            Map<String, ?> map = preferences.getAll();
            if (map != null && map.size() > 0)
                notifyObservers(map);
        });
        Logger.i("EnterAndOption", "onResume");
    }

    @Override
    protected void onStop() {
        Logger.i("EnterAndOption", "onStop " + this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Logger.i("EnterAndOption", "onDestroy ");
        if (selectDialog != null) {
            selectDialog.dismiss();
            selectDialog = null;
        }
        EventBusUtil.unregister(this);

        try {
            if (quickClickProtection != null) {
                quickClickProtection.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.onDestroy();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEndEvent(TransparentActivityEndEvent event) {
        if (isDialog) {
            EventBusUtil.unregister(this);
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEndEvent(ActivityEndEvent event) {
        Logger.i("EnterAndOption", "ActivityEndEvent " + this);
        //new Handler().postDelayed(()->{
        if (selectDialog != null) {
            selectDialog.dismiss();
            selectDialog = null;
        }
        EventBusUtil.unregister(this);
        finish();
        //}, 100);

    }


    public void initViews() {
        if (mDisplayOption != null) {
            viewType = SELECT_AMOUNT;
            initDialog();
        } else {
            viewType = INPUT_AMOUNT;
            initInputView();
        }
    }

    private void initInputBackground() {

        getWindow().setBackgroundDrawable(new ColorDrawable(0xfffafafa));  //@color/background_material_light
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.primary));
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.alpha = 1.0f;//这句就是设置窗口里控件的透明度的．０.０全透明．１.０不透明．
        wl.dimAmount = 0.0f;
        window.setAttributes(wl);
    }

    private void initInputView() {

        selectLayout.setVisibility(View.GONE);
        inputAmountLayout.setVisibility(View.VISIBLE);
        transAmountLayout.setVisibility(View.VISIBLE);
        confirmBtn.setVisibility(View.VISIBLE);
        confirmBtn.setEnabled(true);
        if (amount != 0) {
            amountTv.setText(isPoint ? String.valueOf(amount) : CurrencyConverter.convert(amount, ""));
        } else {
            transAmountLayout.setVisibility(View.INVISIBLE);
        }
        promptTv.setText(limit != null ? limit.prompt : "");
        mEditText.requestFocus();

        if (!ViewUtils.isScreenOrientationPortrait(this)) {
            confirmBtn.setVisibility(View.GONE);
        }
    }

    private void onDataConfirmed() {
        try {
            //if(!isDialog)
            confirmBtn.setClickable(false);
            String content;
            if (viewType == INPUT_AMOUNT) {
                content = mEditText.getText().toString();
                if (limit != null && limit.isMustEnter && StringUtils.isEmpty(content)) {
                    return;
                }
            } else {
                content = mAmountOption.get(selected);
                if (content.equals(getString(R.string.other_amount))) {
                    ToastHelper.showMessage(this, getString(R.string.pls_select_again));
                    return;
                }
            }
            if (!validate(content)) {
                return;
            }

            //APMN-78
            if (!isAmount && viewType == INPUT_AMOUNT) {
                content = CurrencyConverter.convert(StringUtils.parseLong(content) * amountUnit);
            }

            T data = convert(content);

            if (limit != null && limit.needConfirm) {
                confirm(data);
            } else {
                sendNext(data);
            }
        } finally {
            confirmBtn.setClickable(true);
        }
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            setBackEnabled(true);
            getSupportActionBar().setTitle(navTitle == null ? "" : navTitle);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(navBack);
        }
    }

    protected void setBackEnabled(boolean enabled) {
        if (enabled) {
            toolbar.setNavigationOnClickListener(v -> onKeyBackDown());
        } else {
            toolbar.setNavigationOnClickListener(null);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return onKeyBackDown();
        }
        if (quickClickProtection.isStarted()) {
            return true;
        }
        quickClickProtection.start();
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            onKeyEnterDown();
        }
        return super.onKeyDown(keyCode, event);
    }

    protected boolean onKeyEnterDown() {
        onDataConfirmed();
        return true;
    }

    protected boolean onKeyBackDown() {

        sendAbort();
        return true;
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        if (getSupportActionBar() != null && !TextUtils.isEmpty(transName)) {
            navTitle = transName;
            getSupportActionBar().setTitle(navTitle);
        }
        setDemo(transMode);
    }

    protected void confirm(final T data) {
        final WeakReference<Context> contextWeakReference = new WeakReference<>(this);
        runOnUiThread(() -> {
            Context ctx = contextWeakReference.get();
            CustomAlertDialog dialog = new CustomAlertDialog(ctx, CustomAlertDialog.NORMAL_TYPE);
            dialog.setCancelClickListener(alertDialog -> {
                alertDialog.dismiss();
                sendAbort();
            }).setConfirmClickListener(alertDialog -> {
                alertDialog.dismiss();
                sendNext(data);
            }).create();
            dialog.setContent(limit != null ? limit.confirmPrompt : "");
            dialog.showConfirmButton(true);
            dialog.showCancelButton(true);
            dialog.show();
        });
    }

    @SuppressLint("StringFormatMatches")
    protected boolean validate(String content) {
        if (!TextUtils.isEmpty(limit.lengthRange)) {
            List<Integer> lengthList = RangeFilter.getLengthList(limit.lengthRange);
            Long amt = CurrencyConverter.parse(content);
            if (amt != null && amt != 0 && !lengthList.contains(String.valueOf(amt).length())) {

//                String pro = getString(R.string.notice_amount_out_of_range);
//                if (pro.contains("\n")){
//                    ToastHelper.showMessage(this, getString(R.string.notice_amount_out_of_range, CurrencyConverter.convert(limit.minValue), CurrencyConverter.convert(limit.maxValue), CurrencyConverter.convert(limit.minValue), CurrencyConverter.convert(limit.maxValue)));
//                }else {
//                    ToastHelper.showMessage(this, getString(R.string.notice_amount_out_of_range, CurrencyConverter.convert(limit.minValue), CurrencyConverter.convert(limit.maxValue)));
//                }
                String title = LanguageConvertUtils.convertString(this, R.string.notice_amount_out_of_range, null, CurrencyConverter.convert(limit.minValue), CurrencyConverter.convert(limit.maxValue));
                ToastHelper.showMessage(this, title);


//                ToastHelper.showMessage(this, getString(R.string.notice_amount_out_of_range,
//                        CurrencyConverter.convert(limit.minValue), CurrencyConverter.convert(limit.maxValue)));
                return false;
            }
        }
        if (CurrencyConverter.parse(content) == 0 && limit.isMustEnter) {
            ToastHelper.showMessage(this, getString(R.string.pls_input_again));
            mEditText.requestFocus();
            return false;
        }
        return true;
    }

    protected void setDemo(String transMode) {
        if (!TransMode.NORMAL.equals(transMode)) {
            addWaterMarkView(transMode);
        } else {
            removeWaterMarkView();
        }
    }

    private void addWaterMarkView(String transMode) {
        removeWaterMarkView();

        String content = null;
        if(TransMode.TEST.equals(transMode)){
            content = getString(R.string.test_only);
        }else if(TransMode.DEMO.equals(transMode)){
            content = getString(R.string.demo_only);
        }else if(TransMode.TEST_AND_DEMO.equals(transMode)){
            content = getString(R.string.test_and_demo);
        }
        ViewUtils.addWaterMarkView(this,content);
    }

    private void removeWaterMarkView() {
        ViewUtils.removeWaterMarkView(this);
    }


    /**
     * convert String content to base UI data type
     *
     * @param content string content(from EditText usually)
     * @return base UI data type
     */
    protected abstract T convert(String content);

    /**
     * load custom data
     */
    protected abstract void loadOtherParam();

    /**
     * send ABORT request
     */
    protected abstract void sendAbort();

    /**
     * send NEXT request with data
     *
     * @param data UI data, see data ui from Entry Actions
     */
    protected abstract void sendNext(T data);

    @Override
    public void onClick(View view) {
        if (quickClickProtection.isStarted()) {
            return;
        }
        quickClickProtection.start();
        if (view.getId() == R.id.confirm_btn) {
            onDataConfirmed();
        }
    }

    @Override
    public final boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            return onKeyBackDown();
        }
        if (quickClickProtection.isStarted()) { //AET-123
            return true;
        }
        quickClickProtection.start();
        return onOptionsItemSelectedSub(item);
    }

    protected boolean onOptionsItemSelectedSub(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasPhyKeyboard && viewType == INPUT_AMOUNT){
            confirmBtn.setVisibility(View.VISIBLE);
            mEditText.hideKeyboard(true, false);
        }
    }

        @Override
        public void onHasPhyKeyboard(boolean notShowVirtualKeyBoard) {
        if(notShowVirtualKeyBoard) {
            this.hasPhyKeyboard = notShowVirtualKeyBoard;
        }
    }

}
