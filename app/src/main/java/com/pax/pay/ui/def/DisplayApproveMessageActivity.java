package com.pax.pay.ui.def;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.ImageView;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.eventbus.ActivityEndEvent;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.pax.pay.ui.def.eventbus.TransparentActivityEndEvent;
import com.pax.pay.ui.def.service.PlayerService;
import com.pax.pay.ui.def.utils.GifLoadOneTimeGif;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.CardType;
import com.pax.us.pay.ui.core.helper.DisplayApproveMessageHelper;
import com.paxus.utils.log.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DisplayApproveMessageActivity extends AppCompatActivity implements DisplayApproveMessageHelper.IDisplayApproveMessageListener {

    ImageView imageView;
    private DisplayApproveMessageHelper helper;
    private boolean needDisplay = false;
    private boolean needPlayAnimation = true;
    private boolean needPlaySound = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (needDisplay) {
            setContentView(R.layout.activity_display_approve_message_default);
            Resources res = getResources();
            Drawable drawable = res.getDrawable(R.drawable.bkcolor);
            this.getWindow().setBackgroundDrawable(drawable);
        } else {
            setContentView(R.layout.activity_custom);
        }

        //EventBusUtil.doEvent(new ActivityEndEvent());
        EventBusUtil.register(this);



        //enableBack(false);
        if (needDisplay)
            imageView = findViewById(R.id.cardimageview);
        helper = new DisplayApproveMessageHelper(this, new RespStatusImpl(this));


    }

    protected void onDestroy() {
        EventBusUtil.unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEndEvent(TransparentActivityEndEvent event) {
        if (!needDisplay) {
            EventBusUtil.unregister(this);
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEndEvent(ActivityEndEvent event) {
        EventBusUtil.unregister(this);
        finish();
    }

    @Override
    public void setTheme(int resid) {
        if (getIntent().getExtras().containsKey(EntryExtraData.PARAM_ANIMATION_SUPPORT))
            needPlayAnimation = getIntent().getExtras().getBoolean(EntryExtraData.PARAM_ANIMATION_SUPPORT);
        if (getIntent().getExtras().containsKey(EntryExtraData.PARAM_SOUND_SUPPORT))
            needPlaySound = getIntent().getExtras().getBoolean(EntryExtraData.PARAM_SOUND_SUPPORT);
        Logger.i("needPlayAnimation : " + needPlayAnimation + " needPlaySound : " + needPlaySound);
        if (getIntent().getExtras().containsKey(EntryExtraData.PARAM_CARD_TYPE) && needPlayAnimation) {
            String cardType = getIntent().getExtras().getString(EntryExtraData.PARAM_CARD_TYPE);
            if (!TextUtils.isEmpty(cardType)) {
                if (CardType.VISA.equals(cardType)) {
                    needDisplay = true;
                    super.setTheme(R.style.AppTheme_NoActionBar);
                    return;
                }
            }
        }
        needDisplay = false;
        super.setTheme(resid);
    }

//        @Override
//    protected void setListeners() {
//
//    }

//    @Override
//    protected void initViews() {
//        enableBack(false);
//    }
//
//    @Override
//    protected void loadParam() {
//        imageView = findViewById(R.id.cardimageview);
//        helper = new DisplayApproveMessageHelper(this, new RespStatusImpl(this));
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_display_approve_message_default;
//    }

    @Override
    protected void onStart() {
        helper.start(this, getIntent());
        super.onStart();
    }

    @Override
    protected void onStop() {
        helper.stop();
        super.onStop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        if (!needDisplay) {
            WindowManager.LayoutParams wl = getWindow().getAttributes();
            wl.alpha = 0.0f;//这句就是设置窗口里控件的透明度的．０.０全透明．１.０不透明．
            getWindow().setAttributes(wl);
        }
        super.onResume();
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {

    }

    @Override
    public void onShowCardType(@NonNull String cardType) {
        if (TextUtils.isEmpty(cardType)) {
            helper.sendAbort();
        } else {
            Logger.i("CardType : " + cardType);
            if (CardType.VISA.equals(cardType)) {
                showVisaAmimation();
            } else {
                needPlayAnimation = false;
                if (needPlaySound)
                    playSound(R.raw.boba);
            }
        }
    }

    private void playSound(int resourcesId) {
        //Fix ANFDRC-977
        Intent intent = new Intent(this, PlayerService.class);
        intent.putExtra(PlayerService.PARAM_RESOURCE_ID,resourcesId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
        if (!needPlayAnimation) {
            helper.sendNext(true);
        }
    }

    private void showVisaAmimation() {
        if (needPlayAnimation) {
            imageView.clearAnimation();
            Logger.i(".......load gif start.........");
            GifLoadOneTimeGif.loadOneTimeGif(this, R.raw.visa_animation, imageView, 1, new GifLoadOneTimeGif.GifListener() {
                @Override
                public void gifPlayComplete() {
                    Logger.i("VisaAnimationActivity gifPlayComplete");
                    helper.sendNext(true);
                }
            });
            Logger.i(".......load gif end.........");
        }
        if (needPlaySound) {
            playSound(R.raw.visa_sound);
        }
    }
}
