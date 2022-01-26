package com.pax.pay.ui.def;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.TextUtils;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.EnterCashbackHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EnterCashbackActivity extends EnterAndOptionWithTwoStyleActivity<Long> implements EnterCashbackHelper.IEnterCashbackListener {

    private EnterCashbackHelper helper;
    private boolean enableOtherPrompt = false;

    @Override
    protected void loadOtherParam() {
        //setCurrencyName(CurrencyType.USD);
        setAmountOption(null, null, null);
        setDialogTitle(getString(R.string.prompt_select_cashback));
        EditTextDataLimit limit;
        String lengthRange = getIntent().getExtras().getString(EntryExtraData.PARAM_VALUE_PATTERN);
        if (!TextUtils.isEmpty(lengthRange)) {
            limit = new EditTextDataLimit(getResources().getString(R.string.prompt_input_cashback),
                    "", lengthRange, EInputType.AMOUNT, false);
        } else {
            String minLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MIN_LENGTH);
            String maxLength = getIntent().getExtras().getString(EntryExtraData.PARAM_MAX_LENGTH);
            int min = StringUtils.parseInt(minLength, 0);
            int max = StringUtils.parseInt(maxLength, 8);
            limit = new EditTextDataLimit(getResources().getString(R.string.prompt_input_cashback),
                    "", min, max, EInputType.AMOUNT, false);
        }
        setLimit(limit, true, 0L);
        enableOtherPrompt = false;

        helper = new EnterCashbackHelper(this, new RespStatusImpl(this));
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
        return CurrencyConverter.parse(content);
    }


    @Override
    public void onShowCurrency(@Nullable String s, boolean isPoint) {
        if (isPoint) {
            setPoint(true);
        } else {
            setCurrencyName(s);
            setPoint(false);
        }
    }

    @Override
    public void onShowAmount(long amount) {
        setAmount(amount);
    }

    @Override
    public void onShowCashbackOptions(@NonNull String[] options, @Nullable String[] rateOptions) {
        if (options.length > 0) {
            List<String> amountList = new ArrayList<>();
            List<String> displayAmountList = new ArrayList<>();
            if (rateOptions != null && rateOptions.length > 0) {
                for (int j = 0; j < options.length; j++) {
                    if ("0".equals(options[j])) {
                        displayAmountList.add(getString(R.string.no_thanks));
                    } else {
                        String tmpOption = rateOptions[j] + getString(R.string.cashback_options) + CurrencyConverter.convert(Long.valueOf(options[j])) + ")";
                        displayAmountList.add(tmpOption);
                    }
                    amountList.add(CurrencyConverter.convert(Long.valueOf(options[j])));
                }
                if (enableOtherPrompt)
                    setAmountOption(amountList, getString(R.string.other_amount), displayAmountList);
                else
                    setAmountOption(amountList, null, displayAmountList);
            } else {
                for (String i : options) {
                    if ("0".equals(i)) {
                        displayAmountList.add(getString(R.string.no_thanks));
                    } else
                        displayAmountList.add(CurrencyConverter.convert(Long.valueOf(i)));
                    amountList.add(CurrencyConverter.convert(Long.valueOf(i)));
                }
                if (enableOtherPrompt)
                    setAmountOption(amountList, getString(R.string.other_amount), displayAmountList);
                else
                    setAmountOption(amountList, null, displayAmountList);
            }
        }
    }

    @Override
    public void onShowEnableOtherPrompt(boolean enable) {
        enableOtherPrompt = enable;
    }
}
