package com.paxus.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.paxus.view.utils.ViewUtils;

/**
 * Created by Kim.L on 2018/4/19.
 */
public class BaseDialogFragment extends DialogFragment {

    private static final String TAG = BaseDialogFragment.class.getSimpleName();

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
        Dialog dialog = mOnCallDialog.getDialog(context != null ? context : getActivity());
        if (ViewUtils.canNavigationBarImmersiveSticky() && dialog != null) {
            View decorView = dialog.getWindow().getDecorView();
            if (decorView != null) {
                ViewUtils.hideNavigationBar(decorView);
            }
        }
        return dialog;
    }

    @Override
    public void onStart() {
        Dialog dialog = getDialog();
        if (ViewUtils.canNavigationBarImmersiveSticky() && dialog != null) {
            // Set the dialog to not focusable.
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

            //dialog.show() is invoked in super.onStart();
            super.onStart();

            // Set the dialog to focusable again.
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        } else {
            super.onStart();
        }

        //-----Fix ANFDRC-327, Keep consistent with BroadPOS Vantiv--------------
        //-----Keep Grey Background----------------------------------------------
//        Dialog dialog = getDialog();
//        if (dialog != null) {
//
//            // 在 5.0 以下的版本会出现白色背景边框，若在 5.0 以上设置则会造成文字部分的背景也变成透明
//            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
//                getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            }
//
//            Window window = getDialog().getWindow();
//            WindowManager.LayoutParams windowParams = window.getAttributes();
//            windowParams.dimAmount = 0.0f;
//            window.setAttributes(windowParams);
//        }
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
            Log.e(TAG, e.getMessage());

            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mDismissListener != null && getDialog() != null) {
            mDismissListener.onDismiss(getDialog());
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
            Log.e(TAG, e.getMessage());
            //Fix Dialog can not dismiss when app running in background
            if (getFragmentManager() != null) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.remove(this);
                ft.commitAllowingStateLoss();
            }
            new Handler().postDelayed(() -> onDismiss(getDialog()), 50);
        }
    }

    public interface OnCallDialog {
        Dialog getDialog(Context context);
    }

}
