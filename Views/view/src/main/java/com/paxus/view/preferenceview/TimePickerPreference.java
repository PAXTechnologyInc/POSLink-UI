package com.paxus.view.preferenceview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.preference.DialogPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import com.paxus.view.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A {@link Preference} that displays a time picker as a dialog.
 * <p>
 * This preference will save the picked time as a string into the SharedPreferences.
 * This string uses the 24-hour clock formatted using {@link #FORMAT}.
 *
 * @see #PATTERN
 * @see #FORMAT
 */
public class TimePickerPreference extends DialogPreference {
    /**
     * The pattern that is used for parsing the default value.
     */
    public static final String PATTERN = "HH:mm";

    /**
     * The date format that can be used to convert the saved value to {@link Date} objects.
     */
    public final SimpleDateFormat FORMAT = new SimpleDateFormat(PATTERN, Locale.US);

    public static final int FORMAT_AUTO = 0;
    public static final int FORMAT_12H = 1;
    public static final int FORMAT_24H = 2;
    private Date time;
    private String summaryPattern;
    private CharSequence summary;

    public TimePickerPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TimePickerPreference, defStyleAttr, 0);
        summaryPattern = a.getString(R.styleable.TimePickerPreference_summaryPattern);

        a.recycle();

        summary = super.getSummary();
    }

    public TimePickerPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @SuppressLint("RestrictedApi")
    public TimePickerPreference(Context context, AttributeSet attrs) {
        this(context, attrs, TypedArrayUtils.getAttr(context, R.attr.dialogPreferenceStyle,
                android.R.attr.dialogPreferenceStyle));
    }

    public TimePickerPreference(Context context) {
        this(context, null);
    }

    /**
     * Returns the hour of the day (a.k.a. 24-hour clock version). The range is 0-23, or -1 if the
     * time is not set.
     *
     * @return The hour of the day (a.k.a. 24-hour clock version). The range is 0-23, or -1 if the
     * time is not set.
     */
    @IntRange(from = -1, to = 23)
    public int getHourOfDay() {
        if (time != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(time);
            return cal.get(Calendar.HOUR_OF_DAY);
        }

        return -1;
    }

    /**
     * Returns the minute of the hour. The range is 0-59, or -1 if the
     * time is not set.
     *
     * @return The minute of the hour. The range is 0-59, or -1 if the
     * time is not set.
     */
    @IntRange(from = -1, to = 59)
    public int getMinute() {
        if (time != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(time);
            return cal.get(Calendar.MINUTE);
        }

        return -1;
    }

    /**
     * Returns the selected time.
     *
     * @return The selected time.
     */
    @Nullable
    public Date getTime() {
        return time;
    }

    /**
     * Sets and persists the selected time.
     *
     * @param time The selected time.
     */
    public void setTime(@Nullable Date time) {
        this.time = time;
    }

    /**
     * Sets and persists the picked time of the preference.
     *
     * @param hourOfDay The hour of the day (a.k.a. 24-hour clock version). The valid range is 0-23.
     * @param minute    The minute of the hour. The valid range is 0-59.
     */
    public void setTime(@IntRange(from = 0, to = 23) int hourOfDay, @IntRange(from = 0, to = 59) int minute) {
        setInternalTime(String.format(Locale.US, "%02d:%02d", hourOfDay, minute), false);
    }

    private void setInternalTime(String time, boolean force) {
        String oldTime = getPersistedString(null);

        final boolean changed = (oldTime != null && !oldTime.equals(time)) || (time != null && !time.equals(oldTime));

        if (changed || force) {
            if (!TextUtils.isEmpty(time)) {
                try {
                    this.time = FORMAT.parse(time);
                } catch (ParseException e) {
                    Log.e("PreferenceView", e.getMessage());
                    this.time = null;
                }
            }

            persistString(time == null ? "" : time);

            notifyChanged();
        }
    }

    /**
     * Returns the summary of this Preference. If no {@code pref_summaryHasTime} is set, this will be
     * displayed if no time is selected; otherwise the formatted time will be used.
     *
     * @return The summary.
     */
    @Override
    public CharSequence getSummary() {
        if (time == null) {
            return summary;
        } else {
            DateFormat simpleDateFormat;

            if (summaryPattern == null) {
                simpleDateFormat = android.text.format.DateFormat.getTimeFormat(getContext());
            } else {
                simpleDateFormat = new SimpleDateFormat(summaryPattern, Locale.getDefault());
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(time);

            String formattedDate = simpleDateFormat.format(cal.getTime());
            if (formattedDate != null) {
                return formattedDate;
            } else {
                return summary;
            }
        }
    }

    /**
     * Sets the summary for this Preference with a CharSequence. If no {@code pref_summaryHasTime} is
     * set, this will be displayed if no time is selected; otherwise the formatted time will be
     * used.
     *
     * @param summary The summary for the preference.
     */
    @Override
    public void setSummary(CharSequence summary) {
        super.setSummary(summary);
        if (summary == null && this.summary != null) {
            this.summary = null;
        } else if (summary != null && !summary.equals(this.summary)) {
            this.summary = summary.toString();
        }
    }

    /**
     * Returns the time pattern that will be used in the summary to format the selected time. If not
     * set, the default format will be used based on the current locale. It can contain the usual
     * formatting characters. See {@link SimpleDateFormat} for more details.
     *
     * @return The time pattern that will be used in the summary to format the selected time.
     */
    public String getSummaryPattern() {
        return summaryPattern;
    }

    /**
     * Sets the time pattern that will be used in the summary to format the selected time. If not
     * set, the default format will be used based on the current locale. It can contain the usual
     * formatting characters. See {@link SimpleDateFormat} for more details.
     *
     * @param summaryPattern The time pattern that will be used in the summary to format the
     *                       selected time.
     */
    public void setSummaryPattern(String summaryPattern) {
        this.summaryPattern = summaryPattern;
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValueObj) {
        final String defaultValue = (String) defaultValueObj;
        setInternalTime(restoreValue ? getPersistedString(null) : (!TextUtils.isEmpty(defaultValue) ? defaultValue : null), true);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        if (isPersistent()) {
            // No need to save instance state since it's persistent
            return superState;
        }

        final SavedState myState = new SavedState(superState);
        myState.time = getTime();
        return myState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state == null || !state.getClass().equals(SavedState.class)) {
            // Didn't save state for us in onSaveInstanceState
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState myState = (SavedState) state;
        super.onRestoreInstanceState(myState.getSuperState());
        setTime(myState.time);
    }

    @IntDef({FORMAT_AUTO, FORMAT_12H, FORMAT_24H})
    @Retention(RetentionPolicy.SOURCE)
    @interface HourFormat {
    }

    private static class SavedState extends BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    @Override
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    @Override
                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
        private Date time;

        public SavedState(Parcel source) {
            super(source);
            time = (Date) source.readSerializable();
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeSerializable(time);
        }
    }

    public static class TimeWrapper {
        public final int hour;
        public final int minute;

        public TimeWrapper(int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
        }
    }
}
