package com.pax.pay.ui.def;

import android.os.Parcel;
import android.os.Parcelable;

import com.pax.pay.ui.def.utils.RangeFilter;

import java.io.Serializable;

/**
 * Created by Kim.L on 2/24/2018.
 */

public class EditTextDataLimit implements Serializable, Parcelable {

    public String prompt;
    public EInputType inputType;
    public String value;
    public int minLen;
    public int maxLen;
    public long minValue;
    public long maxValue;
    public boolean isMustEnter;
    public boolean needConfirm;
    public String confirmPrompt;
    public String lengthRange;

    public EditTextDataLimit(String prompt, String value, int minLen, int maxLen, EInputType inputType, boolean isMustEnter) {
        this.prompt = prompt;
        this.value = value;
        this.minLen = minLen;
        this.maxLen = maxLen;
        this.inputType = inputType;
        this.isMustEnter = isMustEnter;
    }

    public EditTextDataLimit(String prompt, String value, String lengthRange, EInputType inputType, boolean isMustEnter) {
        this.prompt = prompt;
        this.value = value;
        this.lengthRange = lengthRange;
        this.minLen = RangeFilter.getMinLength(lengthRange);
        this.maxLen = RangeFilter.getMaxLength(lengthRange);

        this.inputType = inputType;
        this.isMustEnter = isMustEnter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getMinLen() {
        return minLen;
    }

    public void setMinLen(int minLen) {
        this.minLen = minLen;
    }

    public int getMaxLen() {
        return maxLen;
    }

    public void setMaxLen(int maxLen) {
        this.maxLen = maxLen;
    }

    public long getMinValue() {
        return minValue;
    }

    public void setMinValue(long minValue) {
        this.minValue = minValue;
    }

    public long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(long maxValue) {
        this.maxValue = maxValue;
    }

    public EditTextDataLimit setValueLimit(long minValue, long maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        return this;
    }

    public EditTextDataLimit setNeedConfirm(boolean needConfirm, String confirmPrompt) {
        this.needConfirm = needConfirm;
        this.confirmPrompt = confirmPrompt;
        return this;
    }

    public static EditTextDataLimit getDef() {
        return new EditTextDataLimit("", "", 0, 0, EInputType.ALLTEXT, false).setValueLimit(0, 0).setNeedConfirm(false, "");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(prompt);
        dest.writeSerializable(inputType);
        dest.writeString(value);
        dest.writeInt(minLen);
        dest.writeInt(maxLen);
        dest.writeLong(minValue);
        dest.writeLong(maxValue);
        dest.writeBooleanArray(new boolean[]{isMustEnter, needConfirm});
        dest.writeString(confirmPrompt);
    }

    public static final Creator<EditTextDataLimit> CREATOR
            = new Creator<EditTextDataLimit>() {
        @Override
        public EditTextDataLimit createFromParcel(Parcel in) {
            return new EditTextDataLimit(in);
        }

        @Override
        public EditTextDataLimit[] newArray(int size) {
            return new EditTextDataLimit[size];
        }
    };

    private EditTextDataLimit(Parcel in) {
        prompt = in.readString();
        inputType = (EInputType) in.readSerializable();
        value = in.readString();
        minLen = in.readInt();
        maxLen = in.readInt();
        minValue = in.readLong();
        maxValue = in.readLong();
        boolean[] array = new boolean[2];
        in.readBooleanArray(array);
        isMustEnter = array[0];
        needConfirm = array[1];
        confirmPrompt = in.readString();
    }

}
