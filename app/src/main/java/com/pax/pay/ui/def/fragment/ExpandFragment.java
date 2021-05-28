package com.pax.pay.ui.def.fragment;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pax.pay.ui.def.R;
import com.pax.pay.ui.def.utils.DisplayTransInfoUtils;
import com.pax.pay.ui.def.view.ClssLight;
import com.pax.pay.ui.def.view.ClssLightsView;
import com.pax.us.pay.ui.constant.status.ClssLightStatus;
import com.paxus.view.utils.ViewUtils;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpandFragment extends Fragment implements Observer {

    private static Boolean lightFlag = false;
    LinearLayout llDetailContainer;
    private ImageView iv;
    private ClssLightsView llClssLight;
    private DisplayTransInfoUtils displayTransInfoUtils;
    private DisplayTransInfoUtils.ShowType showType;

    public ExpandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_expand, container, false);
        //iv = root.findViewById(R.id.detail_layout);
        llClssLight = root.findViewById(R.id.clssLighLand);
        llDetailContainer = root.findViewById(R.id.detail_layout);

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
                Log.i("ReceiptFragment", "Update Bitmap");

                int screenHigh = getScreenHigh(lightFlag);
                if (screenHigh > 0) {
                    new Thread(() -> {
                        Bitmap receiptAction;
                        if (map.getHeight() < screenHigh) {
                            receiptAction = adjustBitmapHeight(map, screenHigh);
                            //receiptAction = map;
                        } else
                            receiptAction = map;
                        new Thread(() -> {
                            iv.setImageBitmap(receiptAction);
                        });
                    });
                }
            } catch (IllegalStateException e) {
//                Logger.e(e);
            }
        } else if (arg instanceof Animation) {
            Animation aniMap = (Animation) arg;
            iv.startAnimation(aniMap);
        } else if (arg instanceof Map) {
            Map<String, ?> transDataMap = (Map<String, ?>) arg;
            showType = DisplayTransInfoUtils.ShowType.EXPAND;
            llDetailContainer.removeAllViews();
            int screenHigh = getScreenHigh(lightFlag);
            String[] key = null, value = null;
            if (transDataMap.size() > 0) {
                key = new String[transDataMap.size()];
                value = new String[transDataMap.size()];
                int idx = 0;
                for (Map.Entry<String, ?> mapEntry : transDataMap.entrySet()) {
                    key[idx] = mapEntry.getKey();
                    if(mapEntry.getValue() == null){//Fix ANFDRC-985
                        value[idx] = "";
                    }else {
                        value[idx] = mapEntry.getValue().toString();
                    }
                    idx++;
                }
            }
            if (screenHigh > 0) {
                displayTransInfoUtils = new DisplayTransInfoUtils(llDetailContainer, key, value, showType);
                displayTransInfoUtils.addViewsToViewGroup(llDetailContainer);
                //displayTransInfoUtils.addEmptyViewsToViewGroup(llDetailContainer, screenHigh);
            }

            Log.i("ReceiptFragment", "lightFlag = " + (lightFlag ? "True" : "False"));
            if (lightFlag)
                llClssLight.setVisibility(View.VISIBLE);
            else
                llClssLight.setVisibility(View.GONE);


        } else if (arg instanceof Animation) {
            Animation aniMap = (Animation) arg;
            if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
//                App.getApp().runOnUiThread(() -> iv.startAnimation(aniMap));
            } else {
                iv.startAnimation(aniMap);
            }
        } else if (arg instanceof Boolean) {
            lightFlag = (Boolean) arg;
            if (llClssLight != null) {
                onResume();
            }
            Log.i("ReceiptFragment", "Update lightFlag = " + lightFlag);
        } else if (arg instanceof String) {
            switch ((String) arg) {
                case ClssLightStatus.CLSS_LIGHT_COMPLETED:
                case ClssLightStatus.CLSS_LIGHT_NOT_READY: //Fix ANBP-383, ANFDRC-319
                    llClssLight.setLights(-1, ClssLight.OFF);
                    break;
                case ClssLightStatus.CLSS_LIGHT_ERROR:
                    llClssLight.setLight(0, ClssLight.OFF);
                    llClssLight.setLight(1, ClssLight.OFF);
                    llClssLight.setLight(2, ClssLight.OFF);
                    llClssLight.setLight(3, ClssLight.ON);
                    break;
                case ClssLightStatus.CLSS_LIGHT_IDLE:
                    llClssLight.setLights(0, ClssLight.BLINK);
                    break;
                case ClssLightStatus.CLSS_LIGHT_PROCESSING:
                    llClssLight.setLight(0, ClssLight.ON);
                    llClssLight.setLight(1, ClssLight.ON);
                    llClssLight.setLight(2, ClssLight.OFF);
                    llClssLight.setLight(3, ClssLight.OFF);
                    break;
                case ClssLightStatus.CLSS_LIGHT_READY_FOR_TXN:
                    llClssLight.setLight(0, ClssLight.ON);
                    llClssLight.setLight(1, ClssLight.OFF);
                    llClssLight.setLight(2, ClssLight.OFF);
                    llClssLight.setLight(3, ClssLight.OFF);
                    break;
                case ClssLightStatus.CLSS_LIGHT_REMOVE_CARD:
                    llClssLight.setLight(0, ClssLight.ON);
                    llClssLight.setLight(1, ClssLight.ON);
                    llClssLight.setLight(2, ClssLight.ON);
                    llClssLight.setLight(3, ClssLight.OFF);

                    break;
                case ClssLightStatus.CLSS_LIGHT_BLUE_ON:
                    llClssLight.setLight(0, ClssLight.ON);
                    break;
                case ClssLightStatus.CLSS_LIGHT_BLUE_OFF:
                    llClssLight.setLight(0, ClssLight.OFF);
                    break;
                case ClssLightStatus.CLSS_LIGHT_BLUE_BLINK:
                    llClssLight.setLight(0, ClssLight.BLINK);
                    break;
                case ClssLightStatus.CLSS_LIGHT_YELLOW_ON:
                    llClssLight.setLight(1, ClssLight.ON);
                    break;
                case ClssLightStatus.CLSS_LIGHT_YELLOW_OFF:
                    llClssLight.setLight(1, ClssLight.OFF);
                    break;
                case ClssLightStatus.CLSS_LIGHT_YELLOW_BLINK:
                    llClssLight.setLight(1, ClssLight.BLINK);
                    break;
                case ClssLightStatus.CLSS_LIGHT_GREEN_ON:
                    llClssLight.setLight(2, ClssLight.ON);
                    break;
                case ClssLightStatus.CLSS_LIGHT_GREEN_OFF:
                    llClssLight.setLight(2, ClssLight.OFF);
                    break;
                case ClssLightStatus.CLSS_LIGHT_GREEN_BLINK:
                    llClssLight.setLight(2, ClssLight.BLINK);
                    break;
                case ClssLightStatus.CLSS_LIGHT_RED_ON:
                    llClssLight.setLight(3, ClssLight.ON);
                    break;
                case ClssLightStatus.CLSS_LIGHT_RED_OFF:
                    llClssLight.setLight(3, ClssLight.OFF);
                    break;
                case ClssLightStatus.CLSS_LIGHT_RED_BLINK:
                    llClssLight.setLight(3, ClssLight.BLINK);
                    break;
                default:
                    break;
            }
        }
    }

    private int getScreenHigh(boolean clssFlag) {
        if (getActivity() != null) {
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            Point outSize = new Point();
            display.getSize(outSize);
            int screen_height = ViewUtils.px2Dp(getContext(), outSize.y);
            int statusItemHeight = 0;
            int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusItemHeight = this.getResources().getDimensionPixelSize(resourceId);
            }

            int iMaxHigh = 0;
            if (clssFlag) {
                iMaxHigh = screen_height - 20 - 30 - 8 - 8 - statusItemHeight - 10;
            } else {
                iMaxHigh = screen_height - 20 - statusItemHeight - 10;
            }
            return iMaxHigh;
        }
        return 0;
    }

    private Bitmap adjustBitmapHeight(Bitmap bitmap, int iMaxHigh) {
        Bitmap map = Bitmap.createBitmap(bitmap.getWidth(), iMaxHigh, Bitmap.Config.ARGB_4444);
        final Canvas canvas = new Canvas(map);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        // 保存
        canvas.save(Canvas.ALL_SAVE_FLAG);
        // 存储
        canvas.restore();
        return map;
    }


}
