package com.paxus.view.preferenceview;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class TimePickerPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat implements TimePickerDialog.OnTimeSetListener {

    private int pickedHour;
    private int pickedMinute;

    public static TimePickerPreferenceDialogFragmentCompat newInstance(
            String key) {
        final TimePickerPreferenceDialogFragmentCompat
                fragment = new TimePickerPreferenceDialogFragmentCompat();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);

        return fragment;
    }

    private TimePickerPreference getTimePickerPreference() {
        return (TimePickerPreference) getPreference();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TimePickerPreference preference = getTimePickerPreference();

        Calendar cal = Calendar.getInstance();

        Date time = preference.getTime();

        if (time != null) {
            cal.setTime(time);
        }

        TimePickerDialog dialog = new TimePickerDialog(getActivity(), this, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, preference.getPositiveButtonText(), this);
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, preference.getNegativeButtonText(), this);

        return dialog;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);

        if (which == DialogInterface.BUTTON_POSITIVE) {
            ((TimePickerDialog) getDialog()).onClick(dialog, which);
        }
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        TimePickerPreference preference = getTimePickerPreference();

        if (positiveResult && preference.callChangeListener(new TimePickerPreference.TimeWrapper(pickedHour, pickedMinute))) {
            preference.setTime(pickedHour, pickedMinute);
        }
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        pickedHour = hourOfDay;
        pickedMinute = minute;

        //onClick(getDialog(), DialogInterface.BUTTON_POSITIVE);
        super.onClick(getDialog(), DialogInterface.BUTTON_POSITIVE);
    }
}
