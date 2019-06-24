package com.pax.pay.ui.def_ui.utils;

import android.text.Editable;
import android.text.TextWatcher;

//public class EnterAmountTextWatcher expands TextValueWatcher<Long>
public class EnterAmountTextWatcher implements TextWatcher {
    public static final long DEF_MAX_VALUE = 99999999L;
    public static final int DEF_MAX_LEN = 8;

    private int maxLen = DEF_MAX_LEN;

    protected Long maxValue;
    protected boolean mEditing;
    protected boolean mIsForward = true;
    private long mPre = 0L;
    protected String mPreStr;
    private OnTipListener fListener;

    private long mBaseAmount = 0L;

    public EnterAmountTextWatcher() {
        mEditing = false;
        maxValue = DEF_MAX_VALUE;
    }

    public EnterAmountTextWatcher(int maxNum) {
        this();
        maxLen = maxNum;
    }

    public EnterAmountTextWatcher(long baseAmount, long initTipAmount) {
        this();
        setAmount(baseAmount, initTipAmount);
    }

    public EnterAmountTextWatcher setAmount(long baseAmount, long tipAmount) {
        mBaseAmount = baseAmount;
        mPre = tipAmount;
        if (fListener != null) {
            fListener.onUpdateTipListener(mBaseAmount, mPre);
        }
        return this;
    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //do nothing
    }

    //@Override
    public void onError(String message, long value) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (!mEditing) {
            mIsForward = (after >= count);
            mPreStr = s.toString();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!mEditing) {

            mEditing = true;
            String edit = s.toString().trim();

            long curr = mIsForward ? doForward(edit, mBaseAmount) : doBackward(edit, mBaseAmount);

            String str = CurrencyConverter.convert(curr);
            mPre = curr - mBaseAmount;
            updateEditable(s, str);
            mEditing = false;
        }
    }

    private void updateEditable(Editable s, String str) {
        try {
            s.replace(0, s.length(), str);
            if (fListener != null) {
                fListener.onUpdateTipListener(mBaseAmount, mPre);
            }
        } catch (NumberFormatException nfe) {
            s.clear();
            mPre = 0L;
        }
    }

    private long doForward(String edit, long currAmount) {
        long curr = currAmount;
        long lastDigit = 0L;
        int time = 0;
        if (!edit.isEmpty() && !mPreStr.isEmpty()) {
            int start = edit.indexOf(mPreStr);
            if (start != -1) {
                start += mPreStr.length();
                time = edit.length() - start;
            } else {
                start = 0;
            }
            if (time > 0) {
                try {
                    lastDigit = Long.parseLong(edit.substring(start).replaceAll("[^0-9]", ""));
                } catch (NumberFormatException e) {
                    time = 0;
                }
            }
        }

        time = (int) Math.pow(10, time);

        long amt = curr + mPre * time + lastDigit;
        if (maxLen != 0 && edit.replaceAll("[^0-9]", "").length() > maxLen) {
            onError(String.valueOf(amt), (long) maxLen);
            curr += mPre;
        } else if (amt > maxValue ||
                (fListener != null && currAmount >= 0 && !fListener.onVerifyTipListener(currAmount, mPre * time + lastDigit))) { //AET-21
            onError(String.valueOf(amt), maxValue);
            curr += mPre;
        } else {
            curr += mPre * time + lastDigit;
        }

        return curr;
    }

    private long doBackward(String edit, long currAmount) {
        long curr = currAmount;
        if (0 == edit.length()) {
            mPre = 0L;
        }
        curr += mPre / 10;
        return curr;
    }

    public Long getMaxValue() {
        return maxValue;
    }

    public EnterAmountTextWatcher setMaxValue(long maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public EnterAmountTextWatcher setOnTipListener(OnTipListener listener) {
        this.fListener = listener;
        return this;
    }

    public interface OnTipListener {
        void onUpdateTipListener(long baseAmount, long tipAmount);

        boolean onVerifyTipListener(long baseAmount, long tipAmount);
    }

}
