package com.pax.pay.ui.def.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.paxus.view.quickclick.QuickClickProtection;
import com.paxus.view.utils.KeyboardUtils;

import java.util.Map;

public abstract class BaseAppActivity extends BaseActivity implements View.OnClickListener {

    protected final QuickClickProtection quickClickProtection = QuickClickProtection.getInstance();

    @Override
    protected void onResume() {
        super.onResume();
        quickClickProtection.stop();

        new Handler().post(() -> {
            //if (ExchangeData.getTransDataMap() != null)
                //notifyObservers(ExchangeData.getTransDataMap() );
            SharedPreferences preferences=getSharedPreferences(EntryRequest.class.getName(), Context.MODE_PRIVATE);
            Map<String, ?> map = preferences.getAll();
            if (map != null && map.size() > 0) {
            Log.i("ReceiptFragment", "BaseAppActivity notifyObservers map : " + map.size());
                notifyObservers(map);
            }
        });
//        App.getApp().runInBackground(() -> {
//        Bitmap bmp = getReceipt();
//        if (bmp != null) {
//            notifyObservers(bmp);
//        }
//        });
    }


    @Override
    protected void onDestroy() {
        try {
            if (quickClickProtection != null) {
                quickClickProtection.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.onDestroy();
        }
    }

    @Override
    public final void onClick(View v) {
        if (quickClickProtection.isStarted()) {
            return;
        }
        quickClickProtection.start();
        onClickProtected(v);
    }

    protected void onClickProtected(View v) {
        // do nothing
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return onKeyBackDown();
        }
        if (quickClickProtection.isStarted()) {
            return true;
        }
        quickClickProtection.start();
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            onKeyEnterDown();
        }
        return super.onKeyDown(keyCode, event);
    }

    protected boolean onKeyEnterDown() {
        return false;
    }


    /**
     * click blank area, EditText hide keyboard
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            // Normally the EditText is the current focus View （some time maybe the trackball or instance will change the focus View）
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                KeyboardUtils.hideSystemKeyboard(this, v);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v instanceof EditText) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0];
            int top = l[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom);
        }
        // if the focus View isn't EditText, then ignore, it happened when the view is just drawn,
        // the first focus is not on the EditView, and the user USES the trackball to select the other focus
        return false;
    }


    @Override
    public final boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            return onKeyBackDown();
        }
        if (quickClickProtection.isStarted()) { //AET-123
            return true;
        }
        quickClickProtection.start();
        return onOptionsItemSelectedSub(item);
    }

    protected boolean onOptionsItemSelectedSub(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

//    protected Bitmap getReceipt() {
//        //get result & pack to result msg
//        Bundle bundle = ContentProviderUtil.query(returnUri, context);
//        if (bundle != null) {
//            ResponseMap responseMap = new ResponseMap(bundle);
//            handleResponseIfNeeded(responseMap, rspCmd);
//            String response = posLinkMsgUtil.getTransactionResponseMsg(responseMap, rspCmd);
//            return response;
//        }
//
//        TransData transData = ARouter.getInstance().navigation(ExchangeDataService.class).getTransData();
//        if (transData == null)
//            return null;
//
//        IReceiptGenerator receiptGeneratorTrans = ReceiptConstructor.create(transData, EPrintUnitedType.Merchant, false);
//        if (receiptGeneratorTrans != null) {
//            Bitmap map = receiptGeneratorTrans.generatePreview();
//            return map;
//        }
//        Resources res = getApplicationContext().getResources();
//        int id = R.mipmap.tip_menu;
//        Bitmap b = BitmapFactory.decodeResource(res, id);
//        return b;
//    }


//    private static Bitmap createRGBImage(Bitmap bitmap, int color) {
//        int bitmap_w = bitmap.getWidth();
//        int bitmap_h = bitmap.getHeight();
//
//        int[] arrayColor = new int[bitmap_w * bitmap_h];
//
//        int count = 0;
//        for (int i = 0; i < bitmap_h; i++) {
//            for (int j = 0; j < bitmap_w; j++) {
//                int color1 = bitmap.getPixel(j, i);
//
//                int g = Color.green(color1);
//                int r = Color.red(color1);
//                int b = Color.blue(color1);
//                int a = Color.alpha(color1);
//                if (a >= 250 && g >= 250 && r >= 250 && b >= 250) {
//                    color1 = color;
//                }
//                arrayColor[count] = color1;
//                count++;
//            }
//        }
//
//        bitmap = Bitmap.createBitmap(arrayColor, bitmap_w, bitmap_h, Bitmap.Config.ARGB_4444);
//        return bitmap;
//    }
}
