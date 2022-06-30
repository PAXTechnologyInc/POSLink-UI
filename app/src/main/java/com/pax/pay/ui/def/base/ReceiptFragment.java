package com.pax.pay.ui.def.base;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Looper;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.pax.pay.ui.def.R;
import com.pax.pay.ui.def.view.ClssLightsView;
import com.paxus.utils.log.Logger;

import java.util.Observable;
import java.util.Observer;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiptFragment extends Fragment implements Observer {

    private static Boolean lightFlag = false;
    private ImageView iv;
    private ClssLightsView llClssLight;
    //private static int screen_height;


    public ReceiptFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_receipt, container, false);
        iv = root.findViewById(R.id.receipt_view);
        llClssLight = root.findViewById(R.id.clssLighLand);
        //int[] location = new  int[2] ;
        //iv.getLocationInWindow(location);
        //screen_height = root.getHeight();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (lightFlag)
            llClssLight.setVisibility(View.VISIBLE);
        else
            llClssLight.setVisibility(View.GONE);

    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Bitmap) {
            try {
                Bitmap map = (Bitmap) arg;
                Logger.d( "Update Bitmap");

                if (getActivity() != null) {
                    Display display = getActivity().getWindowManager().getDefaultDisplay();
                    Point outSize = new Point();
                    display.getSize(outSize);
                    int screen_height = outSize.y;
                    int statusItemHeight = 0;
                    int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
                    if (resourceId > 0) {
                        statusItemHeight = this.getResources().getDimensionPixelSize(resourceId);
                    }

                    int iMaxHigh;
                    if (lightFlag) {
                        iMaxHigh = screen_height - 20 - 30 - 8 - 8 - statusItemHeight - 10;
                    } else {
                        iMaxHigh = screen_height - 20 - statusItemHeight - 10;
                    }
//                    App.getApp().runInBackground(() -> {
//                        Bitmap receiptAction;
//                        if (map.getHeight() < iMaxHigh) {
//                            receiptAction = adjustBitmapHeight(map, iMaxHigh);
//                            //receiptAction = map;
//                        } else
//                            receiptAction = map;
//                        App.getApp().runOnUiThread(() -> iv.setImageBitmap(receiptAction));
//                    });
                }
            } catch (IllegalStateException e) {
//                Logger.e(e);
            }
        } else if (arg instanceof Animation) {
            Animation aniMap = (Animation) arg;
            if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
//                App.getApp().runOnUiThread(() -> iv.startAnimation(aniMap));
            } else {
                iv.startAnimation(aniMap);
            }
        } else if (arg instanceof Boolean) {
            lightFlag = (Boolean) arg;
            Logger.d("Update lightFlag = " + lightFlag);
        }
//        else if (arg instanceof SearchCardEvent.Status) {
//            SearchCardEvent.Status lightStatus = (SearchCardEvent.Status) arg;
//            switch (lightStatus) {
//                case CLSS_LIGHT_STATUS_NOT_READY:
////                case CLSS_LIGHT_STATUS_IDLE:
//                case CLSS_LIGHT_STATUS_READY_FOR_TXN:
//                case CLSS_LIGHT_STATUS_PROCESSING:
//                     /* Since payment transactions are so fast
//                (less than 500ms), indication of this status
//                will normally not be necessary */
//                case CLSS_LIGHT_STATUS_REMOVE_CARD:
//                case CLSS_LIGHT_STATUS_COMPLETE:
//                case CLSS_LIGHT_STATUS_ERROR:
//                    llClssLight.setLights(lightStatus);
//                    break;
//                default:
//                    break;
//            }
//
//        }
    }

    private Bitmap adjustBitmapHeight(Bitmap bitmap, int iMaxHigh) {
        Bitmap map = Bitmap.createBitmap(bitmap.getWidth(), iMaxHigh, Bitmap.Config.ARGB_4444);
        final Canvas canvas = new Canvas(map);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        // 保存
        canvas.save(/*Canvas.ALL_SAVE_FLAG*/);
        // 存储
        canvas.restore();
        return map;
    }


}
