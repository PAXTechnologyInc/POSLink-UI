package com.paxus.view.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.paxus.view.R;

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
        showMessage(context, message, 100);
    }
    public static void showMessage(final Context context, final String message, int yOffset) {
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflate == null) {
            return;
        }
        View view = inflate.inflate(R.layout.layout_toast, null);
        TextView textView = view.findViewById(R.id.message);
        if (toast == null) {
            textView.setText(message);
            toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_SHORT);
            // set gravity center
            toast.setGravity(Gravity.CENTER, 0, yOffset); //APMN-221
            toast.setView(view);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();

            if (message.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.setGravity(Gravity.CENTER, 0, yOffset);
                    toast.show();
                }
            } else {
                oldMsg = message;
                textView.setText(message);
                toast.setView(view);
                toast.setGravity(Gravity.CENTER, 0, yOffset);
                toast.show();
            }
        }

        oneTime = twoTime;
    }
}
