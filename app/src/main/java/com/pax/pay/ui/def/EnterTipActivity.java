package com.pax.pay.ui.def;


import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.utils.EnterDataLineHelper;
import com.pax.pay.ui.def.utils.RangeFilter;
import com.pax.us.pay.ui.component.keyboard.CustomKeyboardEditText;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.UnitType;
import com.pax.us.pay.ui.core.helper.EnterTipHelper;
import com.paxus.utils.CurrencyCode;
import com.paxus.utils.CurrencyConverter;
import com.paxus.utils.LocaleUtils;
import com.paxus.utils.StringUtils;
import com.paxus.view.dialog.CustomAlertDialog;
import com.paxus.view.dialog.DialogUtils;
import com.paxus.view.utils.ToastHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class EnterTipActivity extends BaseAppActivity implements EnterTipHelper.IEnterTipListener {
    private EnterTipHelper helper;

    private TextView amountTv;
    private TextView totalAmountTv;
    private TextView tipNameTv;
    private TextView tipPromptTv;
    private View layoutInfo;
    private CustomKeyboardEditText tipEditText;
    private ListView tipListView;
    private GridView tipOptionView;
    private Button noTipBtn;

    private CurrencyCode currencyCode = CurrencyCode.US;
    private long amountUnit = 1L;
    private boolean isPoint;

    private EditTextDataLimit limit = null;
    private long baseAmount;
    private long tip = 0;
    private List<TipMode> tipModeList = new ArrayList<>();
    private List<TipOption> tipOptionList = new ArrayList<>();

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initViews() {
        amountTv = findViewById(R.id.tv_amount);
        amountTv.setVisibility(View.GONE);
        totalAmountTv = findViewById(R.id.tv_total_amount);
        tipNameTv = findViewById(R.id.tv_tip_name);
        tipPromptTv = findViewById(R.id.tv_tip_prompt);
        tipPromptTv.setText(getString(R.string.tip_amount));

        layoutInfo = findViewById(R.id.layout_info);
        layoutInfo.setVisibility(View.GONE);

        tipEditText = findViewById(R.id.edit_tip);
        tipEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NONE) {
                //Fix ANFDRC-226, After click KEY_BACK_DOWN
                //finish(new ActionResult<>(TransResult.ERR_USER_CANCEL, null));
                //To do Abort
                sendAbort();
                return true;
            } else if (actionId == EditorInfo.IME_ACTION_DONE) {
                onDataConfirmed(v.getText().toString());
            }
            return false;
        });

        noTipBtn = findViewById(R.id.btn_no_tip);
        noTipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNext(0L);
            }
        });
        noTipBtn.setVisibility(View.GONE);
        tipListView = findViewById(R.id.list_tip);
        tipOptionView = findViewById(R.id.grid_tip_options);
        tipOptionView.setVisibility(View.GONE);
        tipOptionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendNext(tipOptionList.get(position).getAmount());
            }
        });
    }

    @Override
    protected void loadParam() {
        helper = new EnterTipHelper(this, new RespStatusImpl(this));
        // Arvind: ANBP-702
        long amountUnit = getIntent().getExtras().getLong(EntryExtraData.PARAM_AMOUNT_UNIT);
        if (amountUnit == 0) {
            String tipUnit = getIntent().getExtras().getString(EntryExtraData.PARAM_TIP_UNIT);
            if (UnitType.DOLLAR.equals(tipUnit)) {
                amountUnit = 100L;
            }
        }
        if (amountUnit != 0){
            this.amountUnit = amountUnit;
        }
        String displayMessage = getResources().getString(R.string.prompt_input_tip);
        String lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_VALUE_PATTERN);
        if (!TextUtils.isEmpty(lengthRange)) {
            limit = new EditTextDataLimit(displayMessage,
                    "", lengthRange, EInputType.AMOUNT, false);
        } else {
            String minLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MIN_LENGTH);
            String maxLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MAX_LENGTH);
            int min = StringUtils.parseInt(minLength, 0);
            int max = StringUtils.parseInt(maxLength, 8);
            limit = new EditTextDataLimit(displayMessage,
                    "", min,max, EInputType.AMOUNT, false);
        }


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_tip;
    }

    @Override
    public void onShowAmount(long amount) {
        this.baseAmount = amount;
        amountTv.setVisibility(View.VISIBLE);
        amountTv.setText(formatAmount(amount));
        updateTotalAmount();

        tipModeList.clear();
        tipModeList.add(new TipMode(getString(R.string.sale_amt), formatAmount(baseAmount), true));
        TipModeAdapter adapter = new TipModeAdapter(this, tipModeList);
        tipListView.setAdapter(adapter);
    }

    private void updateTotalAmount(){
        totalAmountTv.setText(formatAmount(baseAmount+tip));
    }

    @Override
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {
        this.isPoint = isPoint;

        currencyCode = CurrencyCode.findTypeByCurrencyName(currency);
        if(amountUnit > 0){
            if(isPoint){
                amountUnit = 1L;
            }
        }

        if(amountUnit > 1) {
            //Yanina: Workaround, number only
            EnterDataLineHelper.setEditTextAmount(tipEditText, limit, CurrencyCode.POINT);
        }else{
            EnterDataLineHelper.setEditTextAmount(tipEditText, limit, currencyCode);
        }
        tipEditText.setKeepKeyBoardOn(true);
        tipEditText.requestFocus();
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    @Override
    public void onShowTipName(@Nullable String tipName) {
        if(tipName != null && !tipName.isEmpty()){
            tipNameTv.setText(tipName);
        }
    }

    @Override
    public void onShowTipOptions(@NonNull String[] options, @Nullable String[] rateOptions) {
        if (options.length > 0) {
            tipOptionList.clear();
            tipPromptTv.setText(getString(R.string.other_amount));

            for(int i =0;i<options.length; i++){
                String rate = "";
                long amount = StringUtils.parseLong(options[i]);
                if(amount == 0L){
                    noTipBtn.setVisibility(View.VISIBLE);
                    continue;
                }
                if(rateOptions!=null && rateOptions.length > i){
                    rate = rateOptions[i];
                }
                tipOptionList.add(new TipOption(rate,amount));
            }
            if(tipOptionList.size()>0) {
                tipOptionView.setNumColumns(tipOptionList.size());
                tipOptionView.setAdapter(new TipOptionAdapter(this, tipOptionList));
                tipOptionView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onShowEnableNoTipSelection(boolean noTip) {
        noTipBtn.setVisibility(noTip? View.VISIBLE:View.GONE);
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

    protected void sendAbort() {
        helper.sendAbort();
    }

    protected void sendNext(Long data) {
        helper.sendNext(data);
    }

    @Override
    protected boolean onKeyBackDown() {
        sendAbort();
        return true;
    }

    private void onDataConfirmed(String content) {
        long data = 0;
        if(amountUnit > 1) {
            data = StringUtils.parseLong(content);
        }else{
            data = CurrencyConverter.parse(content, currencyCode.getCurrencyName());
        }
        if (!validate(data)) {
            tipEditText.requestFocus();
            return;
        }

        // APMN-78
        data = data * amountUnit;

        if (limit != null && limit.needConfirm) {
            confirm(data);
        } else {
            sendNext(data);
        }
    }

    @Override
    public void onShowTips(@NonNull String[] tipNames, @Nullable long[] tipValues) {
        if(tipNames.length>1) {
            layoutInfo.setVisibility(View.VISIBLE);
            tip = 0;
            for (int i = 0; i < tipNames.length; i++) {
                String amount;
                boolean activated = tipValues != null && tipValues.length > i;
                if (activated) {
                    tip += tipValues[i];
                    amount = formatAmount(tipValues[i]);
                } else {
                    amount = formatAmount(0);
                }
                tipModeList.add(new TipMode(tipNames[i], amount, activated));
            }
            updateTotalAmount();
        }
    }


    public class TipModeAdapter extends BaseAdapter {
        private final List<TipMode> mList;
        private final LayoutInflater inflater;

        public TipModeAdapter(Context context, List<TipMode> objects) {
            mList = objects;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TipMode tipMode = getItem(position);
            final ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_tip_amount, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.type.setText(tipMode.getName()+":");
            holder.amount.setText(tipMode.getAmount());
            if (tipMode.activated) {
                holder.type.setTextColor(getResources().getColor(R.color.primary_dark));
                holder.amount.setTextColor(getResources().getColor(R.color.primary_dark));
            }
            return convertView;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Nullable
        @Override
        public TipMode getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {
            private final TextView type;
            private final TextView amount;

            private ViewHolder(View view) {
                type = view.findViewById(R.id.tip_type);
                amount = view.findViewById(R.id.tip_amount);

            }
        }
    }

    public static class TipMode {
        private String name;
        private String amount;
        private boolean activated = false;

        public TipMode(String name, String amount, boolean activated) {
            this.name = name;
            this.amount = amount;
            this.activated = activated;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

    }

    public class TipOptionAdapter extends BaseAdapter {
        private final List<TipOption> mList;
        private final LayoutInflater inflater;

        public TipOptionAdapter(Context context, List<TipOption> objects) {
            mList = objects;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TipOption tipOption = getItem(position);
            final ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_tip_option, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String percentage = tipOption.getPercentage();
            if(StringUtils.isEmpty(percentage)) {
                holder.percentage.setVisibility(View.GONE);
            }else{
                holder.percentage.setVisibility(View.VISIBLE);
                holder.percentage.setText(percentage);
            }
            holder.amount.setText(formatAmount(tipOption.getAmount()));
            return convertView;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Nullable
        @Override
        public TipOption getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {
            private final TextView percentage;
            private final TextView amount;

            private ViewHolder(View view) {
                percentage = view.findViewById(R.id.tv_percentage);
                amount = view.findViewById(R.id.tv_tip);

            }
        }
    }

    public static class TipOption{
        private String percentage;
        private long amount;

        public TipOption(String percentage, long amount) {
            this.percentage = percentage;
            this.amount = amount;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public long getAmount() {
            return amount;
        }

        public void setAmount(long amount) {
            this.amount = amount;
        }
    }

    protected void confirm(final Long data) {
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
            DialogUtils.showDialog(this, dialog);
        });
    }

    @SuppressLint("StringFormatMatches")
    protected boolean validate(long value) {
        if (!TextUtils.isEmpty(limit.lengthRange)) {
            List<Integer> lengthList = RangeFilter.getLengthList(limit.lengthRange);
            if (value != 0 && !lengthList.contains(String.valueOf(value).length())) {
                String title = LocaleUtils.getString(this, R.string.notice_amount_out_of_range,  formatAmount(limit.minValue), formatAmount(limit.maxValue));
                ToastHelper.showMessage(this, title);
                return false;
            }
        }
        if (value == 0 && limit.isMustEnter) {
            ToastHelper.showMessage(this, getString(R.string.pls_input_again));
            return false;
        }
        return true;
    }

    private String formatAmount(long amount){
        return CurrencyConverter.convert(amount,"",currencyCode.getCurrencyName());
    }
}
