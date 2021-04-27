/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2016 - ? Pax Corporation. All rights reserved.
 * Module Date: 2016-12-1
 * Module Author: Steven.W
 * Description:
 *
 * ============================================================================
 */
package com.paxus.view.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.annotation.MainThread;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;

import com.paxus.view.R;
import com.paxus.view.dialog.CustomAlertDialog.OnCustomClickListener;
import com.paxus.view.utils.ViewUtils;

import java.util.Calendar;

public class DialogUtils {

    private static final String TAG = DialogUtils.class.getSimpleName();

    @StringRes
    private static final int DIALOG_POSITIVE = R.string.dialog_ok;
    @StringRes
    private static final int DIALOG_NEGATIVE = R.string.dialog_cancel;

    private DialogUtils() {
        //do nothing
    }

    @MainThread
    public static void showDialog(Activity context, final Dialog dialog) {
        showDialogInternal(context, dialog, null, null);
    }

    @MainThread
    public static void showDialog(Activity context, final Dialog dialog, final DialogInterface.OnDismissListener listener) {
        showDialogInternal(context, dialog, listener, null);
    }

    private static BaseDialogFragment showDialogInternal(Activity context, final Dialog dialog, final DialogInterface.OnDismissListener listener, DialogInterface.OnCancelListener cancelListener) {
        BaseDialogFragment dialogFragment = BaseDialogFragment.newInstance(context, cont -> dialog, listener != null, listener, cancelListener);
        try {
            dialogFragment.show(((FragmentActivity) context).getSupportFragmentManager(), context.toString());
        } catch (ClassCastException e) {
            Log.d(TAG, "Can't get the fragment manager with this " + context.toString());
        }
        return dialogFragment;
    }

    @MainThread
    public static void showErrMessage(final Activity context, final String title) {
        showErrMessage(context, title, null, null, ViewUtils.FAILED_DIALOG_SHOW_TIME);
    }

    @MainThread
    public static void showErrMessage(final Activity context, final String title, final String msg) {
        showErrMessage(context, title, msg, null, ViewUtils.FAILED_DIALOG_SHOW_TIME);
    }

    /**
     * Show Trans Fail Message (Include specific Error Message)
     */
    @MainThread
    public static void showErrMessage(final Activity context, final String title, final String msg,
                                      final DialogInterface.OnDismissListener listener, final int timeoutSec) {
        if (context == null) {
            return;
        }
        TransResultAlertDialog dialog = new TransResultAlertDialog(context,
                TransResultAlertDialog.TYPE_FAIL, title, msg);
        dialog.setOnKeyListener((dialogInterface, keyCode, event) -> keyCode == KeyEvent.KEYCODE_BACK);
        dialog.create();
        dialog.setCanceledOnTouchOutside(true);
        BaseDialogFragment dialogFragment = showDialogInternal(context, dialog, listener == null ? dialogInterface -> {
            //do nothing just allow to dismiss by touching screen
        } : listener, null);
        new Handler().postDelayed(dialogFragment::dismiss, timeoutSec * 1000);
    }

    @MainThread
    public static void showDisplayMessage(final Activity context, final String title, final String msg,
                                          final DialogInterface.OnDismissListener listener, final int timeoutSec) {
        TransResultAlertDialog dialog = new TransResultAlertDialog(context,
                TransResultAlertDialog.TYPE_FAIL, title, msg);
        dialog.setOnKeyListener((dialogInterface, keyCode, event) -> keyCode == KeyEvent.KEYCODE_BACK);
        dialog.create();
        dialog.setCanceledOnTouchOutside(true);
        BaseDialogFragment dialogFragment = showDialogInternal(context, dialog, listener == null ? dialogInterface -> {
            //do nothing just allow to dismiss by touching screen
        } : listener, null);
        new Handler().postDelayed(dialogFragment::dismiss, timeoutSec * 1000);
    }

    /**
     * Show Success Message
     */
    @MainThread
    public static void showSuccMessage(final Activity context, final String title,
                                       final DialogInterface.OnDismissListener listener, final int timeoutSec) {
        if (context == null) {
            return;
        }
        TransResultAlertDialog dialog = new TransResultAlertDialog(context,
                TransResultAlertDialog.TYPE_SUCC, title, null);
        dialog.setOnKeyListener((dialogInterface, keyCode, event) -> keyCode == KeyEvent.KEYCODE_BACK);
        dialog.create();
        dialog.setCanceledOnTouchOutside(true);
        BaseDialogFragment dialogFragment = showDialogInternal(context, dialog, listener == null ? dialogInterface -> {
            //do nothing just allow to dismiss by touching screen
        } : listener, null);
        new Handler().postDelayed(dialogFragment::dismiss, timeoutSec * 1000);
    }

    @MainThread
    public static void showConfirmDialog(Activity context, String content,
                                         OnCustomClickListener cancelClickListener,
                                         OnCustomClickListener confirmClickListener) {
        showConfirmDialog(context, null, content, null, cancelClickListener, null, confirmClickListener);
    }

    @MainThread
    public static void showConfirmDialog(final Activity context, final String title, final String content,
                                         final String cancelText, final OnCustomClickListener cancelClickListener,
                                         final String confirmText, final OnCustomClickListener confirmClickListener) {
        CustomAlertDialog dialog = new CustomAlertDialog(context, CustomAlertDialog.NORMAL_TYPE);
        dialog.setCancelClickListener(alertDialog -> {
            alertDialog.dismiss();
            if (cancelClickListener != null) {
                cancelClickListener.onClick(alertDialog);
            }
        }).setConfirmClickListener(alertDialog -> {
            alertDialog.dismiss();
            if (confirmClickListener != null) {
                confirmClickListener.onClick(alertDialog);
            }
        }).create();
        dialog.setTitle(title);
        dialog.setContent(content);
        dialog.showCancelButton(true);
        dialog.setCancelText(cancelText);
        dialog.showConfirmButton(true);
        dialog.setConfirmText(confirmText);
        showDialog(context, dialog);
    }

    @MainThread
    public static void showConfirmDialog(final Activity context, final String title, final String content,
                                         final String confirmText, final OnCustomClickListener confirmClickListener) {
        CustomAlertDialog dialog = new CustomAlertDialog(context, CustomAlertDialog.NORMAL_TYPE);
        dialog.setConfirmClickListener(alertDialog -> {
            alertDialog.dismiss();
            if (confirmClickListener != null) {
                confirmClickListener.onClick(alertDialog);
            }
        }).create();
        dialog.setTitle(title);
        dialog.setContent(content);
        dialog.showCancelButton(false);
        dialog.showConfirmButton(true);
        dialog.setConfirmText(confirmText);
        showDialog(context, dialog);
    }

    @MainThread
    public static Dialog showSelectDialog(final Activity context, final String title, final DialogInterface.OnClickListener selectedListener,
                                          final boolean showConfirm, final DialogInterface.OnCancelListener cancelListener, final boolean showCancel,
                                          final String[] items, final int checkedItem) {
        if (context == null) {
            return null;
        }
        SingleChoiceDialog dialog = new SingleChoiceDialog(context, items, checkedItem);
        dialog.setCancelClickListener(alertDialog -> {
            alertDialog.dismiss();
            if (cancelListener != null) {
                cancelListener.onCancel(alertDialog);
            }
        }).setConfirmClickListener((alertDialog, which) -> {
            alertDialog.dismiss();
            if (selectedListener != null) {
                selectedListener.onClick(alertDialog, which);
            }
        }).create();
        dialog.setTitle(title);
        dialog.setContent("");
        dialog.showConfirmButton(showConfirm);
        dialog.showCancelButton(showCancel);
        dialog.setCancelText(context.getString(R.string.dialog_cancel));
        dialog.setConfirmText(context.getString(R.string.dialog_ok));
        showDialog(context, dialog);
        return dialog;
    }

    @MainThread
    public static void showListDialog(final Activity context, final String title, final String[] items,
                                      final DialogInterface.OnClickListener resultListener,
                                      final boolean cancelable) {
        showListDialog(context, title, items, resultListener, null, cancelable);
    }

    @MainThread
    public static void showListDialog(final Activity context, final String title, final String[] items,
                                      final DialogInterface.OnClickListener resultListener,
                                      final DialogInterface.OnCancelListener onCancelListener,
                                      final boolean cancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.PaxTheme_AlertDialog);
        builder.setTitle(title);
        builder.setItems(items, resultListener);
        if (cancelable && onCancelListener != null) {
            showDialogInternal(context, builder.create(), null, onCancelListener);
        } else {
            showDialog(context, builder.create(), cancelable ? dialog -> {
            } : null);
        }
    }

    @MainThread
    public static void showEditTextDialog(final Activity context, final String title,
                                          final IDialogResultListener<String> resultListener,
                                          final boolean cancelable, final int editTextType, final int maxLength) {
        final EditText editText = new EditText(context);
        editText.setInputType(editTextType);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        editText.setBackground(context.getDrawable(R.drawable.edit_frame));
        editText.setPaddingRelative(60, 60, 20, 20);
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.PaxTheme_AlertDialog);
        builder.setTitle(title);
        builder.setView(editText);
        builder.setPositiveButton(DIALOG_POSITIVE, (dialog, which) -> {
            if (resultListener != null) {
                resultListener.onDataResult(editText.getText().toString());
            }
        });
        builder.setNegativeButton(DIALOG_NEGATIVE, null);

        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnKeyListener((dialog, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).performClick();
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_BACK) {
                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).performClick();
                return true;
            }
            return false;
        });
        showDialog(context, alertDialog, cancelable ? dialog -> {
        } : null);
    }

    @MainThread
    public static void showDateDialog(final Activity context, final String title, final Calendar calendar,
                                      final IDialogResultListener<Calendar> resultListener, final boolean cancelable) {
        final DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.PaxTheme_AlertDialog,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    resultListener.onDataResult(calendar);
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.setTitle(title);
        datePickerDialog.setOnShowListener(dialog -> {
            datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setText(DIALOG_POSITIVE);
            datePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setText(DIALOG_NEGATIVE);
        });
        datePickerDialog.setOnKeyListener((dialog, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).performClick();
                return true;
            }
            return false;
        });
        showDialog(context, datePickerDialog, cancelable ? dialog -> {
        } : null);
    }

    @MainThread
    public static void showTimeDialog(final Activity context, final String title, final Calendar calendar,
                                      final IDialogResultListener<Calendar> resultListener, final boolean cancelable) {
        final TimePickerDialog dateDialog = new TimePickerDialog(context, R.style.PaxTheme_AlertDialog,
                (view, hourOfDay, minute) -> {
                    if (resultListener != null) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        resultListener.onDataResult(calendar);
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        dateDialog.setTitle(title);
        dateDialog.setOnShowListener(dialog -> {
            dateDialog.getButton(DialogInterface.BUTTON_POSITIVE).setText(DIALOG_POSITIVE);
            dateDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setText(DIALOG_NEGATIVE);
        });
        dateDialog.setOnKeyListener((dialog, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                dateDialog.getButton(DialogInterface.BUTTON_POSITIVE).performClick();
                return true;
            }
            return false;
        });
        showDialog(context, dateDialog, cancelable ? dialog -> {
        } : null);
    }
}
