package com.pax.pay.ui.def;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.recyclerview.GridSpacingItemDecoration;
import com.pax.pay.ui.def.utils.EnterAmountTextWatcher;
import com.pax.pay.ui.def.utils.FSAType;
import com.pax.pay.ui.def.utils.HealthCareType;
import com.pax.pay.ui.def.utils.OptionModelViewHolder;
import com.pax.us.pay.ui.component.keyboard.CustomKeyboardEditText;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.helper.EnterFSAAmountHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.view.utils.KeyboardUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnterFSAAmountOptionActivity extends BaseActivity implements View.OnClickListener, EnterFSAAmountHelper.IEnterFSAAmountListener {
    private static final int STATE_SLT_FSA_TYPE = 0;
    private static final int STATE_INPUT_TOTAL_AMT = 1;
    private static final int STATE_SLT_HEALTH_SUB_TYPE = 2;
    private static final int STATE_INPUT_SUB_HEALTH_AMT = 3;

    Button confirmBtn;
    LinearLayout selectLayout;
    TextView promptSelect;
    RecyclerView mRecyclerView;

    LinearLayout inputAmountLayout;
    LinearLayout transAmountLayout;
    TextView amountTv;

    TextView promptTv;
    CustomKeyboardEditText mEditText;

    private long totalAmount;
    private boolean healthcardEnable;
    private boolean transitEnable;

    private long healthAmt = 0;
    private long clinicAmt = 0;
    private long prescriptionAmt = 0;
    private long dentalAmt = 0;
    private long visionAmt = 0;
    private long copayAmt = 0;
    private long otcAmt = 0;
    private long transitAmt = 0;


    private EnterFSAAmountHelper helper;

    private MyHandler mHandler;
    private RecyclerView.Adapter<OptionModelViewHolder> mAdapter;
    private final List<String> mSelectOption = new ArrayList<>();
    private int selected = -1;
    //private String currHealthCareType;
    private FSAType currentFSAType;
    private int currState = 0;
    private int currHealthCareType;
    private EnterAmountTextWatcher watcher = null;

    private final List<HealthCareType> subHealthTypeList = new ArrayList<>();
    private String fsaOption = "";
    private boolean hasPhyKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_fsa_data_default;
    }

    @Override
    protected void setListeners() {
        confirmBtn.setOnClickListener(this);
        mEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NONE) {
                //Fix ANFDRC-226, After click KEY_BACK_DOWN
                //finish(new ActionResult<>(TransResult.ERR_USER_CANCEL, null));
                //To do Abort
                helper.sendAbort();
                return true;
            } else if (actionId == EditorInfo.IME_ACTION_DONE) {
                onDataConfirmed();
            }
            return false;
        });
    }

    @Override
    protected void initViews() {
        mHandler = new MyHandler(this);
        mEditText.setKeyboardId(R.xml.keyboard_numeric_confirm);
    }

    @Override
    protected void loadParam() {
        selectLayout = findViewById(R.id.select_fsa_option);
        promptSelect = findViewById(R.id.prompt_select);
        mRecyclerView = findViewById(R.id.option_select);

        inputAmountLayout = findViewById(R.id.enter_amount_layout);
        transAmountLayout = findViewById(R.id.trans_amount_layout);
        amountTv = findViewById(R.id.amount_tv);
        promptTv = findViewById(R.id.prompt_tv);
        mEditText = findViewById(R.id.data_edt);
        confirmBtn = findViewById(R.id.confirm_btn);

        healthcardEnable = false;
        transitEnable = false;
        currHealthCareType = 0;
        CurrencyConverter.setDefCurrency(CurrencyType.USD);
        IRespStatus respStatus = new RespStatusImpl(this) {
            @Override
            public void onDeclined(long code, @Nullable String message) {
                super.onDeclined(code, message);
                //Fix ANBP-642 "Clinical Amount Page being Prompted Twice for FSA Transaction"
                // Arvind: Reset the activity after sending data to the Host, in case the data validation failed
                currHealthCareType = 0;
                healthAmt = transitAmt = clinicAmt = prescriptionAmt = dentalAmt = visionAmt = copayAmt = otcAmt = 0;
                mHandler.sendEmptyMessage(currState);
            }
        };
        helper = new EnterFSAAmountHelper(this, respStatus);
    }

    @Override
    protected void onStart() {
        helper.start(this, getIntent());
        super.onStart();
    }

    @Override
    protected void onStop() {
        helper.stop();
        super.onStop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //ANFDRC-737
    @Override
    protected boolean onKeyBackDown() {
        helper.sendAbort();
        return true;
    }

    private void onDataConfirmed() {
        long amount = CurrencyConverter.parse(mEditText.getText().toString());
        if (currState == STATE_INPUT_TOTAL_AMT) {
            if (currentFSAType == FSAType.Transit)
                transitAmt = amount;
            else
                healthAmt = amount;
        } else if (currState == STATE_INPUT_SUB_HEALTH_AMT) {
            switch (subHealthTypeList.get(currHealthCareType)) {
                case Clinical:
                    clinicAmt = amount;
                    break;
                case Dental:
                    dentalAmt = amount;
                    break;
                case Copay:
                    copayAmt = amount;
                    break;
                case RX:
                    prescriptionAmt = amount;
                    break;
                case Vision:
                    visionAmt = amount;
                    break;
                case OTC:
                    otcAmt = amount;
                    break;
            }
            if (currHealthCareType < subHealthTypeList.size() - 1) {
                currHealthCareType++;
                mHandler.sendEmptyMessage(STATE_INPUT_SUB_HEALTH_AMT);
                return;
            }
        }
        if (healthAmt == 0)
            healthAmt = otcAmt+clinicAmt + prescriptionAmt + dentalAmt + visionAmt + copayAmt;
        helper.sendNext(healthAmt, clinicAmt, prescriptionAmt, dentalAmt, visionAmt, copayAmt, otcAmt, transitAmt, totalAmount, fsaOption);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.confirm_btn) {
            if (currState == STATE_SLT_FSA_TYPE) {
                currentFSAType = FSAType.valueOf(mSelectOption.get(selected));
                mHandler.sendEmptyMessage(FSAType.Transit == currentFSAType ? STATE_INPUT_TOTAL_AMT : STATE_SLT_HEALTH_SUB_TYPE);
                fsaOption = currentFSAType.toString();
            } else if (currState == STATE_SLT_HEALTH_SUB_TYPE) {
                mHandler.sendEmptyMessage(selected == 0 ? STATE_INPUT_TOTAL_AMT : STATE_INPUT_SUB_HEALTH_AMT);
                // Arvind: Hacky but works for now. Using fsaOption param to inform the Host app what option was taken
                // by the user: HealthCare Total or SubHealthCare Types. This will help solve https://pax-us.atlassian.net/browse/ANBP-598.
                fsaOption = selected == 0? FSAType.HealthCare.toString(): getString(R.string.sub_healthcare);
            } else if (currState == STATE_INPUT_SUB_HEALTH_AMT
                    || currState == STATE_INPUT_TOTAL_AMT) {
                onDataConfirmed();
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onShowAmount(long l) {
        totalAmount = l;
        amountTv.setText(CurrencyConverter.convert(totalAmount, ""));
    }

    @Override
    public void onShowCurrency(@Nullable String s, boolean isPoint) {
        CurrencyConverter.setDefCurrency(s);
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    @Override
    public void onShowFsaAmountOption(boolean healthCareVisible, boolean ClinicVisible, boolean prescriptionVisible,
                                      boolean dentalVisible, boolean visionVisible, boolean copayVisible, boolean otcVisible, boolean transitVisable) {
        healthcardEnable = healthCareVisible;
        transitEnable = transitVisable;

        if (ClinicVisible) subHealthTypeList.add(HealthCareType.Clinical);
        if (prescriptionVisible) subHealthTypeList.add(HealthCareType.RX);
        if (dentalVisible) subHealthTypeList.add(HealthCareType.Dental);
        if (visionVisible) subHealthTypeList.add(HealthCareType.Vision);
        if (copayVisible) subHealthTypeList.add(HealthCareType.Copay);
        if (otcVisible) subHealthTypeList.add(HealthCareType.OTC);

        if (healthcardEnable && transitEnable) {
            mHandler.sendEmptyMessage(STATE_SLT_FSA_TYPE);
        } else if (healthcardEnable) {
            currentFSAType = FSAType.HealthCare;
            mHandler.sendEmptyMessage(STATE_SLT_HEALTH_SUB_TYPE);
        } else if (transitEnable) {
            currentFSAType = FSAType.Transit;
            mHandler.sendEmptyMessage(STATE_INPUT_TOTAL_AMT);
        }
    }

    private void initSelectView(String prompt, List<String> item) {
        selected = -1;
        inputAmountLayout.setVisibility(View.GONE);
        selectLayout.setVisibility(View.VISIBLE);
        if (totalAmount != 0) {
            amountTv.setText(CurrencyConverter.convert(totalAmount, ""));
        } else {
            transAmountLayout.setVisibility(View.GONE);
        }

        confirmBtn.setEnabled(false);
        mEditText.hideKeyboard(true);
        KeyboardUtils.hideSystemKeyboard(this, mEditText);


        promptSelect.setText(prompt);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerView.Adapter<OptionModelViewHolder>() {
            @NonNull
            @Override
            public OptionModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new OptionModelViewHolder(LayoutInflater.from(EnterFSAAmountOptionActivity.this).inflate(R.layout.item_mode_grid, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull OptionModelViewHolder holder, int position) {

                String viewData = item.get(position);

                if (viewData != null) {
                    holder.itemView.setContentDescription(viewData);
                    holder.textView.setText(viewData);
                    holder.textView.setSelected(selected == position);
                    holder.textView.setOnClickListener(v -> {
                        selected = holder.getAdapterPosition();
                        confirmBtn.setEnabled(true);
                        mAdapter.notifyDataSetChanged();
                    });
                }
            }

            @Override
            public int getItemCount() {
                return item == null ? 0 : item.size();
            }
        };
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, getResources().getDimensionPixelSize(R.dimen.space_horizontal), true));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initInputView(String title) {
        inputAmountLayout.setVisibility(View.VISIBLE);
        transAmountLayout.setVisibility(View.VISIBLE);
        selectLayout.setVisibility(View.GONE);
        confirmBtn.setVisibility(View.VISIBLE);
        confirmBtn.setEnabled(true);
        if (totalAmount != 0) {
            amountTv.setText(CurrencyConverter.convert(totalAmount, ""));
        } else {
            transAmountLayout.setVisibility(View.INVISIBLE);
        }
        promptTv.setText(title);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mEditText.setKeepKeyBoardOn(true);
        if (watcher != null)
            mEditText.removeTextChangedListener(watcher);
        watcher = new EnterAmountTextWatcher();
        mEditText.requestFocus();
        mEditText.addTextChangedListener(watcher);
        mEditText.setText("");

        mEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onDataConfirmed();
            }
            return false;
        });
        if(hasPhyKeyboard) {
            mEditText.hideKeyboard(true, false);
        }
    }

    @Override
    public void onHasPhyKeyboard(boolean notShowVirtualKeyBoard) {
        this.hasPhyKeyboard = notShowVirtualKeyBoard;
    }

    private static class MyHandler extends Handler {

        private final WeakReference<Context> reference;
        private final static Map<String, Integer> resMap = new HashMap<>();

        static {
            resMap.put(FSAType.HealthCare.name(), R.string.option_healthcare);
            resMap.put(FSAType.Transit.name(), R.string.option_transit);
        }

        MyHandler(Context context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            EnterFSAAmountOptionActivity activity = (EnterFSAAmountOptionActivity) reference.get();
            if (activity != null) {
                activity.currState = msg.what;
                if (msg.what == STATE_SLT_FSA_TYPE) {
                    if (activity.mSelectOption.size() > 0)
                        activity.mSelectOption.clear();
                    for (FSAType fsaType : FSAType.values()) {
                        activity.mSelectOption.add(reference.get().getResources().getString(resMap.get(fsaType.name())));
                    }
                    activity.initSelectView(activity.getResources().getString(R.string.select_fsa_type), activity.mSelectOption);
                } else if (msg.what == STATE_SLT_HEALTH_SUB_TYPE) {
                    if (activity.mSelectOption.size() > 0)
                        activity.mSelectOption.clear();
                    activity.mSelectOption.clear();
                    activity.mSelectOption.add(activity.getResources().getString(R.string.healthcare_total));
                    activity.mSelectOption.add(activity.getResources().getString(R.string.healthcare_sub_type));
                    activity.initSelectView(activity.getResources().getString(R.string.select_health_sub_types), activity.mSelectOption);
                } else if (msg.what == STATE_INPUT_TOTAL_AMT) {
                    activity.initInputView("Please Enter " + activity.currentFSAType.name() + " Amount:");
                } else if (msg.what == STATE_INPUT_SUB_HEALTH_AMT) {
                    HealthCareType healthCareType = activity.subHealthTypeList.get(activity.currHealthCareType);
                    activity.initInputView("Please Enter " + healthCareType.getDetail() + " Amount:");
                }
            }
        }
    }
}
