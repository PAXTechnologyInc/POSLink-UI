package com.pax.pay.ui.def.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Kim.L on 2018/4/19.
 */

public class BaseDialogFragment extends DialogFragment {

    private Context context = null;

    /**
     * 监听弹出窗是否被取消
     */
    private DialogInterface.OnDismissListener mDismissListener;

    private DialogInterface.OnCancelListener mOnCancelListener;

    /**
     * 回调获得需要显示的 dialog
     */
    private OnCallDialog mOnCallDialog;

    public interface OnCallDialog {
        Dialog getDialog(Context context);
    }

    public static BaseDialogFragment newInstance(Context context, OnCallDialog callDialog, boolean cancelable, DialogInterface.OnDismissListener dismissListener) {
        BaseDialogFragment instance = new BaseDialogFragment();
        instance.setCancelable(cancelable);
        instance.mDismissListener = dismissListener;
        instance.mOnCallDialog = callDialog;
        instance.context = context;
        return instance;
    }

    public static BaseDialogFragment newInstance(Context context, OnCallDialog callDialog, boolean cancelable, DialogInterface.OnDismissListener dismissListener,
                                                 DialogInterface.OnCancelListener onCancelListener) {
        BaseDialogFragment instance = new BaseDialogFragment();
        instance.setCancelable(cancelable);
        instance.mDismissListener = dismissListener;
        instance.mOnCancelListener = onCancelListener;
        instance.mOnCallDialog = callDialog;
        instance.context = context;
        return instance;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (null == mOnCallDialog) {
            super.onCreate(savedInstanceState);
        }
        return mOnCallDialog.getDialog(context != null ? context : getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {

            // 在 5.0 以下的版本会出现白色背景边框，若在 5.0 以上设置则会造成文字部分的背景也变成透明
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            Window window = getDialog().getWindow();
            WindowManager.LayoutParams windowParams = window.getAttributes();
            windowParams.dimAmount = 0.0f;
            window.setAttributes(windowParams);
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (mOnCancelListener != null) {
            mOnCancelListener.onCancel(dialog);
        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (IllegalStateException e) {
            Log.e("", e.getMessage());

            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mDismissListener != null) {
            mDismissListener.onDismiss(getDialog());
            mDismissListener = null;
        }
    }

    @Override
    public void dismiss() {
        if (getFragmentManager() == null) {
            return;
        }
        try {
            super.dismiss();
        } catch (IllegalStateException e) {
            Log.e("", e.getMessage());
            //Fix Dialog can not dismiss when app running in background
            if (getFragmentManager() != null) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.remove(this);
                ft.commitAllowingStateLoss();
            }
            new Handler().postDelayed(() -> {
                onDismiss(getDialog());
            }, 50);
        }
    }
}
