package com.pax.pay.ui.def;


import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.pax.us.pay.ui.component.utils.TickTimer;
import com.pax.us.pay.ui.core.helper.ConfirmReceiptViewHelper;
import com.paxus.utils.log.Logger;
import com.paxus.view.utils.ToastHelper;

import java.io.IOException;

/**
 * Created by Jolie.Yang on 2020/05/04.
 */
public class PrePrintViewActivity extends BaseAppActivity implements ConfirmReceiptViewHelper.IConfirmReceiptViewListener {

    Button btnPrint;
    ImageView imageView;
    LinearLayout linearLayout;
    ImageView imageViewHeader;
    ConfirmReceiptViewHelper helper;
    private Bitmap bitmap;
    private Animation receiptOutAnim;
    private TickTimer tickTimer;

    private long timeOut = 0, leftTime = 0;

    @Override
    protected void loadParam() {
        btnPrint = findViewById(R.id.confirm_btn);
        imageView = findViewById(R.id.print_preview);
        imageViewHeader = findViewById(R.id.print_preview_header);
        linearLayout = findViewById(R.id.print_preview_linearlayout);
        navTitle = getString(R.string.receipt_preview);
        helper = new ConfirmReceiptViewHelper(this, new RespStatusImpl(this));
        createTimer();
        tickTimer.start(getTickTimeout());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_print_preview_layout;
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
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayout.setVisibility(View.GONE);
            imageViewHeader.setVisibility(View.GONE);

            ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(btnPrint.getLayoutParams());
            margin.setMargins(margin.leftMargin, 200, margin.rightMargin, 200 + margin.height);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
            btnPrint.setLayoutParams(layoutParams);
            new Handler().post(() -> {
                notifyObservers(bitmap);
            });
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onDestroy() {
        EventBusUtil.unregister(this);
        if (receiptOutAnim != null) {
            receiptOutAnim.cancel();
        }
        super.onDestroy();
    }

    @Override
    protected void setListeners() {
        btnPrint.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        receiptOutAnim = AnimationUtils.loadAnimation(this, R.anim.receipt_out);
    }


    @Override
    public void onClickProtected(View v) {
        if (v.getId() == R.id.confirm_btn) {// print
            btnPrint.setEnabled(false);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                notifyObservers(receiptOutAnim);
            } else {
                imageView.startAnimation(receiptOutAnim);
            }
            tickTimerStop();
            helper.sendNext(true);
        }
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
            imageView.startAnimation(receiptOutAnim);
            helper.sendNext(true);
        }
    }

    @Override
    public void onShowTimeout(long timeoutMs) {
        this.timeOut = timeoutMs;
        restartTimer();
    }

    @Override
    public void onShowReceiptView(@NonNull String receiptUri) {
        Uri imageUri = Uri.parse(receiptUri);
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } else {
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), imageUri);
                ImageDecoder.OnHeaderDecodedListener listener = new ImageDecoder.OnHeaderDecodedListener() {
                    @Override
                    public void onHeaderDecoded(@NonNull ImageDecoder decoder, @NonNull ImageDecoder.ImageInfo info, @NonNull ImageDecoder.Source source) {
                        decoder.setAllocator(ImageDecoder.ALLOCATOR_SOFTWARE);
                        decoder.setMutableRequired(true);
                    }
                };
                bitmap = ImageDecoder.decodeBitmap(source);
            }
            if (bitmap == null) {
                tickTimerStop();
                ToastHelper.showMessage(this, getString(R.string.receipt_image_too_larger));
                helper.sendAbort();
            } else {
                imageView.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            Logger.wtf(e.getMessage());
            tickTimerStop();
            ToastHelper.showMessage(this, getString(R.string.receipt_image_too_long));
            helper.sendAbort();
        }
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

