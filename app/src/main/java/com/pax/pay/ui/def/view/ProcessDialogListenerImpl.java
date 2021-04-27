package com.pax.pay.ui.def.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.pax.pay.ui.def.dialog.TransResultAlertDialog;
import com.pax.pay.ui.def.dialog.WarningDialog;
import com.pax.us.pay.ui.component.dialog.ProcessDialog;

/**
 * Created by Sim.G on 2017/6/5 09:44
 * reuse one dialog for different dialog type
 */
public class ProcessDialogListenerImpl implements ProcessDialogListener {

    private final Context context;
    private BaseDialogFragment dialogFragment;
    private ProcessDialog processDialog;
    private WarningDialog warningDialog;
    private TransResultAlertDialog transResultAlertDialog;
    private Handler handler;
    private Runnable run;


    public ProcessDialogListenerImpl(Context context) {
        this.context = context;
    }

    @Override
    public synchronized void onShowProgress(String message) {

        if (context == null || ((Activity) context).isDestroyed() || ((Activity) context).isFinishing()) {
            Log.i("DialogActivity", "onShowProgress Activity or Context is null ");
            return;
        }

        if (processDialog != null) {
            Log.i("DialogActivity", "onShowProgress setContent : " + message);
            processDialog.setContent(message);
        } else {
            if (dialogFragment != null) {
                dialogFragment.dismiss();
                warningDialog = null;
                transResultAlertDialog = null;
            }
            Log.i("DialogActivity", "onShowProgress new Dialog : " + message);
            processDialog = new ProcessDialog(context, message);
            dialogFragment = BaseDialogFragment.newInstance(context, cont -> processDialog, false, null);
            dialogFragment.show(((FragmentActivity) context).getSupportFragmentManager(), context.toString());
        }

    }

    @Override
    public void onHideProgress() {

        if (context == null || ((Activity) context).isDestroyed() || ((Activity) context).isFinishing()) {
            Log.i("DialogActivity", "onHideProgress Activity or Context is null ");
            return;
        }
        if (dialogFragment != null) {
            dialogFragment.dismiss();
            dialogFragment = null;
            processDialog = null;
            warningDialog = null;
            transResultAlertDialog = null;
        }

    }

    @Override
    public void onShowWarn(String message) {

        if (context == null || ((Activity) context).isDestroyed() || ((Activity) context).isFinishing()) {
            Log.i("DialogActivity", "onShowWarn Activity or Context is null ");
            return;
        }
                if (warningDialog != null) {
                    warningDialog.setContent(message);
                } else {
                    if (dialogFragment != null) {
                        dialogFragment.dismiss();
                        processDialog = null;
                        transResultAlertDialog = null;
                    }
                    warningDialog = new WarningDialog(context, null, message);
                    dialogFragment = BaseDialogFragment.newInstance(context, cont -> warningDialog, false, null);
                    dialogFragment.show(((FragmentActivity) context).getSupportFragmentManager(), context.toString());
                }
    }

    @Override
    public void onUpdateMessage(String message) {

        if (context == null || ((Activity) context).isDestroyed() || ((Activity) context).isFinishing()) {
            Log.i("DialogActivity", "onUpdateMessage Activity or Context is null ");
            return;
        }
        if (processDialog != null) {
            processDialog.setContent(message);
        }

    }

    @Override
    public void onShowResult(boolean isSuccess, String message) {
        onShowResult(isSuccess, message, null);
    }

    @Override
    public void onShowResult(boolean isSuccess, String message, DialogInterface.OnDismissListener dismissListener) {
        final int successFlg;
        if (isSuccess) {
            successFlg = TransResultAlertDialog.TYPE_SUCC;
        } else {
            successFlg = TransResultAlertDialog.TYPE_FAIL;
        }

        if (context == null || ((Activity) context).isDestroyed() || ((Activity) context).isFinishing()) {
            Log.i("DialogActivity", "onShowResult Activity or Context is null ");
            return;
        }
//        if (transResultAlertDialog != null) {
//            transResultAlertDialog.dismiss();
//            transResultAlertDialog = null;
//        }

        if (dialogFragment != null) {
            Log.i("DialogActivity", "onShowResult dialogFragment dismiss");
            dialogFragment.dismiss();
            processDialog = null;
            warningDialog = null;
        }

        transResultAlertDialog = new TransResultAlertDialog(context, successFlg, null, message);
        transResultAlertDialog.setOnKeyListener((dialogInterface, keyCode, event) -> keyCode == KeyEvent.KEYCODE_BACK);
        transResultAlertDialog.create();
        transResultAlertDialog.setCanceledOnTouchOutside(true);

        Log.i("DialogActivity", "onShowResult Activity " + context);
        dialogFragment = BaseDialogFragment.newInstance(context, cont -> transResultAlertDialog, dismissListener != null, dismissListener);
        dialogFragment.show(((FragmentActivity) context).getSupportFragmentManager(), context.toString());
    }

}
