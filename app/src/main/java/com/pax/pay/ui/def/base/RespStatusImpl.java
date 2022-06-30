package com.pax.pay.ui.def.base;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.Nullable;

import android.view.Window;
import android.view.WindowManager;

import com.pax.pay.ui.def.utils.UIControl;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.paxus.view.utils.ToastHelper;

import java.lang.ref.WeakReference;

public class RespStatusImpl implements IRespStatus {

    private final WeakReference<Activity> activityWeakReference;
    private int toastYOffset = 100;

    public RespStatusImpl(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    public int getToastYOffset() {
        return toastYOffset;
    }

    public void setToastYOffset(int toastYOffset) {
        this.toastYOffset = toastYOffset;
    }

    @Override
    public void onAccepted() {
        Activity activity = activityWeakReference.get();
        if(activity != null){
            //close activity which don't involved any transaction.
            if (!UIControl.getInstance().isTransStarted()) {
                 activity.finish();
            }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                Window window = activity.getWindow();
                if(window != null){
                    //ANFDRC-840
                    //Yanina.Y
                    // For Aries8/A30/Aries6, if keep clicking the old activity, it will get input focus
                    // and the new activity can not display successfully. So make it changed to untouchable.
                    window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }
            }
        }
    }

    @Override
    public void onDeclined(long code, @Nullable String message) {
        Activity activity = activityWeakReference.get();
        if (activity != null) {
            activity.runOnUiThread(() -> {
                String buff = message == null ? "Trans Failed!" : message;
                //ANBP-469 don't prompt error code
                //buff += "\n Error Code : " + code;
                //Toast.makeText(activity, buff, Toast.LENGTH_LONG).show();
                // Arvind: Fix for https://pax-us.atlassian.net/browse/ANBP-670
                ToastHelper.showMessage(activity, buff, toastYOffset); //APMN-221
            });
        }
    }
}
