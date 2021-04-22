package com.paxus.view.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.CallSuper;
import android.support.v4.app.FragmentActivity;

import com.paxus.view.dialog.BaseDialogFragment;
import com.paxus.view.dialog.ProcessDialog;

import java.lang.ref.WeakReference;

/**
 * Created by Kim.L 2020/5/13
 */
public abstract class ProcessingAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    private final WeakReference<Activity> activity;
    private final DialogInterface.OnCancelListener cancellable;
    private String message;
    private BaseDialogFragment dialogFragment;
    private ProcessDialog processDialog;


    public ProcessingAsyncTask(Activity activity, String message, DialogInterface.OnCancelListener cancellable) {
        this.activity = new WeakReference<>(activity);
        this.message = message;
        this.cancellable = cancellable;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    protected Activity getActivity() {
        return activity.get();
    }

    @Override
    protected void onPreExecute() {
        if (processDialog != null) {
            processDialog.setContent(message);
        } else if (activity.get() != null) {
            processDialog = new ProcessDialog(activity.get(), message, cancellable);
            dialogFragment = BaseDialogFragment.newInstance(activity.get(), cont -> processDialog, false, null);
            dialogFragment.show(((FragmentActivity) activity.get()).getSupportFragmentManager(), activity.get().toString());
        }
    }

    @CallSuper
    @Override
    protected void onPostExecute(Result ret) {
        if (dialogFragment != null) {
            dialogFragment.dismiss();
            dialogFragment = null;
            processDialog = null;
        }
    }

}
