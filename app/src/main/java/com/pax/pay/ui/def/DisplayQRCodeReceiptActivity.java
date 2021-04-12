package com.pax.pay.ui.def;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.pax.us.pay.ui.component.utils.TickTimer;
import com.pax.us.pay.ui.core.helper.DisplayQRCodeViewHelper;
import com.paxus.utils.log.Logger;
import com.paxus.view.utils.ToastHelper;

import java.util.Hashtable;

/**
 * Created by Yanina.Yang on 4/1/2021.
 */
public class DisplayQRCodeReceiptActivity extends BaseAppActivity  implements DisplayQRCodeViewHelper.IConfirmQRCodeViewListener {

    ImageView imageView;
    DisplayQRCodeViewHelper helper;
    private Bitmap bitmap;
    private TickTimer tickTimer;

    private long timeOut = 0, leftTime = 0;

    @Override
    protected void loadParam() {
        imageView = findViewById(R.id.qrcode_view);
        helper = new DisplayQRCodeViewHelper(this, new RespStatusImpl(this));
        createTimer();
        tickTimer.start(getTickTimeout());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_display_qrcode_layout;
    }

    @Override
    protected void onStart() {
        helper.start(this, getIntent());
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        helper.stop();
        tickTimerStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        EventBusUtil.unregister(this);
        super.onDestroy();
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected boolean onKeyBackDown() {
        tickTimerStop();
        helper.sendAbort();
        return true;
    }

    private void createTimer() {
        tickTimer = new TickTimer(new TickTimer.OnTickTimerListener() {
            @Override
            public void onFinish() {
                onTimerFinish();
            }

            @Override
            public void onTick(long leftTime) {
                timeOnTick(leftTime);
            }
        });
    }


    protected void timeOnTick(long leftTime) {
        this.leftTime = leftTime;
    }

    protected long getTickTimeout() {
        if (timeOut == 0)
            timeOut = 30000;
        return timeOut / 1000;
    }

    protected void onTimerFinish() {
        tickTimerStop();
        if (leftTime == 0) {
            helper.sendNext(true);
        }
    }

    @Override
    public void onShowTimeout(long timeoutMs) {
        this.timeOut = timeoutMs;
        restartTimer();
    }

    @Override
    public void onShowQRCode(@NonNull String content) {
        try {
            Bitmap logo = BitmapFactory.decodeResource(getResources(),R.mipmap.qr_code_logo);
            bitmap = createQRCode(content, 600, logo);
            if (bitmap == null) {
                tickTimerStop();
                ToastHelper.showMessage(this, getString(R.string.receipt_image_too_larger));
                helper.sendAbort();
            } else {
                imageView.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            Logger.wtf(e.getMessage());
            tickTimerStop();
            ToastHelper.showMessage(this, getString(R.string.receipt_image_too_long));
            helper.sendAbort();
        }
    }

    private Bitmap createQRCode(String content, int qrCodeSize, Bitmap logo){
        Matrix m = new Matrix();
        int imageWidth = 80;
        int imageHeight = 80;
        m.setScale((float) imageWidth/logo.getWidth(), (float)imageHeight/logo.getHeight());
        logo = Bitmap.createBitmap(logo, 0, 0,
                logo.getWidth(), logo.getHeight(), m, false);
        MultiFormatWriter writer = new MultiFormatWriter();
        Hashtable<EncodeHintType, Object> hst = new Hashtable<>();
        hst.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix matrix = null;
        try {
            matrix = writer.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hst);
        } catch (Exception e) {
            Logger.e(e);
            return null;
        }
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int halfW = width / 2;
        int halfH = height / 2;
        int[] pixels = new int[width * height];
        Logger.d("BitMatrix Size:"+width+"x"+height+",content:"+content);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x > halfW - imageWidth/2 && x < halfW + imageWidth/2
                        && y > halfH - imageHeight/2 && y < halfH + imageHeight/2) {
                    pixels[y * width + x] = logo.getPixel(x - halfW
                            + imageWidth/2, y - halfH + imageHeight/2);
                } else {
                    if (matrix.get(x, y)) {
                        pixels[y * width + x] = 0xff000000;
                    }
                }

            }
        }
        logo.recycle();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    private void restartTimer() {
        tickTimerStop();
        createTimer();
        tickTimer.start(getTickTimeout());
    }

    private void tickTimerStop() {
        if (tickTimer != null) {
            tickTimer.stop();
            tickTimer = null;
        }
    }

}
