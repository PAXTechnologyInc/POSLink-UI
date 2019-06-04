package com.pax.pay.ui.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pax.pay.ui.R;

public class ToastHelper {

    /**
     * old message
     */
    private static String oldMsg;
    /**
     * Toast object
     */
    private static Toast toast = null;
    /**
     * first time
     */
    private static long oneTime = 0;
    /**
     * second time
     */
    private static long twoTime = 0;

    private ToastHelper() {
        //do nothing
    }

    public static void showMessage(final Context context, final String message) {
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflate == null)
            return;
        View view = inflate.inflate(R.layout.layout_toast, null);
        TextView textView = view.findViewById(R.id.message);
        if (toast == null) {
            textView.setText(message);
            toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 100);// set gravity center
            toast.setView(view);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();

            if (message.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = message;
                textView.setText(message);
                toast.setView(view);
                toast.show();
            }
        }

        oneTime = twoTime;
    }
}
