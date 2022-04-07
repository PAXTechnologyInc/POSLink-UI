package com.pax.pay.ui.def;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;



import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.UnitType;
import com.pax.us.pay.ui.core.helper.EnterTipHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EnterTipActivity extends EnterAndOptionWithTwoStyleActivity<Long> implements EnterTipHelper.IEnterTipListener {
    private EnterTipHelper helper;
    private boolean tipInDollar = false;
    private String displayMessage = "";
    private int min;
    private int max;
    private String lengthRange;

    @Override
    protected void loadOtherParam() {
        //setCurrencyName(CurrencyType.USD);
        setAmountOption(null, null, null);
        setTitleName(getString(R.string.tip_name_opt));
        // Arvind: ANBP-702
        Long amountUnit = getIntent().getExtras().getLong(EntryExtraData.PARAM_AMOUNT_UNIT);
        if (amountUnit == 0) {
            String tipUnit = getIntent().getExtras().getString(EntryExtraData.PARAM_TIP_UNIT);
            tipInDollar = UnitType.DOLLAR.equals(tipUnit);
            if (tipInDollar)
                amountUnit = 100L;
        } else
            tipInDollar = true;

        displayMessage = getResources().getString(R.string.prompt_input_tip);
        lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_VALUE_PATTERN);
        if (!TextUtils.isEmpty(lengthRange)) {
            setLimit(new EditTextDataLimit(displayMessage,
                    "", lengthRange, EInputType.AMOUNT, false), !tipInDollar, amountUnit);
        } else {
            String minLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MIN_LENGTH);
            String maxLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MAX_LENGTH);
            min = StringUtils.parseInt(minLength, 0);
            max = StringUtils.parseInt(maxLength, 8);
            setLimit(new EditTextDataLimit(displayMessage,
                    "", min, max, EInputType.AMOUNT, false), !tipInDollar, amountUnit);
        }
        helper = new EnterTipHelper(this, new RespStatusImpl(this));
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
    protected void sendAbort() {
        helper.sendAbort();
    }

    @Override
    protected void sendNext(Long data) {
        helper.sendNext(data);
    }

    @Override
    protected Long convert(String content) {
        //if (tipInDollar) return CurrencyConverter.parseDollars(content);
        return CurrencyConverter.parse(content);
    }


    @Override
    public void onShowCurrency(@Nullable String s, boolean isPoint) {
        if (isPoint) {
            setPoint(true);
        } else {
            setPoint(false);
            setCurrencyName(s);
        }
    }

    @Override
    public void onShowAmount(long amount) {
        //setAmount(amount); //APAR-7
    }

    @Override
    public void onShowTipOptions(@NonNull String[] options, @Nullable String[] rateOptions) {
        if (options.length > 0) {
            List<String> amountList = new ArrayList<>();
            List<String> displayAmountList = new ArrayList<>();
            if (rateOptions != null && rateOptions.length > 0) {
                for (int j = 0; j < options.length; j++) {
                    String tmpOption;
                    if ("0".equals(options[j]) || TextUtils.isEmpty(rateOptions[j])) {
                        tmpOption = getString(R.string.no_tip);
                    } else
                        tmpOption = rateOptions[j] + getString(R.string.tip_options) + CurrencyConverter.convert(Long.valueOf(options[j])) + ")";
                    displayAmountList.add(tmpOption);
                    amountList.add(CurrencyConverter.convert(Long.valueOf(options[j])));
                }
            } else {
                for (String i : options) {
                    String amount = CurrencyConverter.convert(Long.valueOf(i));
                    amountList.add(amount);
                    if ("0".equals(i)) {
                        displayAmountList.add(getString(R.string.no_tip));
                    } else {
                        displayAmountList.add(amount);
                    }
                }
            }
            setAmountOption(amountList, getString(R.string.other_amount), displayAmountList);
            //amountList.add(getString(R.string.other_amount));
        }
    }

    @Override
    public void onShowTipName(@Nullable String tipName) {
        if (!TextUtils.isEmpty(tipName)) {
            EditTextDataLimit limit;
            String opt = getString(R.string.prompt_input_tip);
            String title;
            if (opt.contains(" \\ ")) {
                String[] tips = tipName.replace(" \\ ", "\\").split("\\\\");
                title = opt.replace(getString(R.string.tip_name_en), tips[0]);
                title = title.replace(getString(R.string.tip_name_fr), tips[1]);
            } else
                title = opt.replace(getString(R.string.tip_name_opt), tipName);
            if (!TextUtils.isEmpty(lengthRange)) {
                limit = new EditTextDataLimit(title,
                        "", lengthRange, EInputType.AMOUNT, false);
            } else {
                limit = new EditTextDataLimit(title,
                        "", min, max, EInputType.AMOUNT, false);
            }
            setTipName(tipName, limit);
        }
    }

    @Override
    public void onShowTips(@NonNull String[] tipNames, @Nullable long[] tipAmounts) {

    }

    @Override
    public void onShowEnableNoTipSelection(boolean enableCancel) {

    }
}
