package com.pax.pay.ui.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.pax.pay.ui.dialog.ProcessDialog;
import com.pax.pay.ui.dialog.WarningDialog;

import androidx.fragment.app.FragmentActivity;


/**
 * Created by Sim.G on 2017/6/5 09:44
 * reuse one dialog for different dialog type
 */
public class ProcessDialogListenerImpl implements ProcessDialogListener {

    private Context context;
    private BaseDialogFragment dialogFragment;
    private ProcessDialog processDialog;
    private WarningDialog warningDialog;
    //    private ConditionVariable mCv;
    private Handler handler;
    private Runnable run;

    public ProcessDialogListenerImpl(Context context) {
        this.context = context;
    }

    @Override
    public void onShowProgress(String message) {

        if (context == null || ((Activity) context).isDestroyed()) {
            return;
        }
        //mCv = new ConditionVariable();
        handler = new Handler();
        run = new Runnable() {
            @Override
            public void run() {
                if (processDialog != null) {
                    processDialog.setContent(message);
                } else {
                    if (dialogFragment != null) {
                        dialogFragment.dismiss();
                        warningDialog = null;
                    }
                    processDialog = new ProcessDialog(context, message);
                    dialogFragment = BaseDialogFragment.newInstance(context, cont -> processDialog, false, null);
                    dialogFragment.show(((FragmentActivity) context).getSupportFragmentManager(), context.toString());
                }
                //mCv.open();
            }
        };
        handler.post(run);
        //mCv.block();
    }

    @Override
    public void onHideProgress() {

        if (context == null || ((Activity) context).isDestroyed()) {
            return;
        }
        //mCv = new ConditionVariable();

        handler = new Handler(context.getMainLooper());
        run = new Runnable() {
            @Override
            public void run() {
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                    dialogFragment = null;
                    processDialog = null;
                    warningDialog = null;
                }
                //mCv.open();
            }
        };
        handler.post(run);
        //mCv.block();
    }

    @Override
    public void onShowWarn(String message) {
        if (context == null || ((Activity) context).isDestroyed()) {
            return;
        }
        //mCv = new ConditionVariable();

        handler = new Handler();
        run = new Runnable() {
            @Override
            public void run() {
                if (warningDialog != null) {
                    warningDialog.setContent(message);
                } else {
                    if (dialogFragment != null) {
                        dialogFragment.dismiss();
                        processDialog = null;
                    }
                    warningDialog = new WarningDialog(context, null, message);
                    dialogFragment = BaseDialogFragment.newInstance(context, cont -> warningDialog, false, null);
                    dialogFragment.show(((FragmentActivity) context).getSupportFragmentManager(), context.toString());
                }
                //mCv.open();
            }
        };
        handler.post(run);
        //mCv.block();
    }

    @Override
    public void onUpdateMessage(String message) {
        if (context == null || ((Activity) context).isDestroyed()) {
            return;
        }
        handler = new Handler();
        run = new Runnable() {
            @Override
            public void run() {
                if (processDialog != null) {
                    processDialog.setContent(message);
                }
            }
        };
        handler.post(run);
        //mCv.block();
    }

}
