package com.pax.pay.ui.def;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.eventbus.ActivityEndEvent;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.pax.pay.ui.def.eventbus.TransparentActivityEndEvent;
import com.pax.pay.ui.def.service.PlayerService;
import com.pax.pay.ui.def.utils.GifLoadOneTimeGif;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.CardType;
import com.pax.us.pay.ui.core.helper.DisplayApproveMessageHelper;
import com.paxus.utils.StringUtils;
import com.paxus.utils.log.Logger;
import com.paxus.view.BaseAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DisplayApproveMessageActivity extends BaseAppCompatActivity implements DisplayApproveMessageHelper.IDisplayApproveMessageListener {

    ImageView imageView;
    private DisplayApproveMessageHelper helper;
    private boolean needDisplay = false;
    private boolean needPlayAnimation = true;
    private boolean needPlaySound = true;
    private String soundApproval = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (needDisplay) {
            setContentView(R.layout.activity_display_approve_message_default);
            Resources res = getResources();
            Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.bkcolor, null);
            this.getWindow().setBackgroundDrawable(drawable);
        } else {
            setContentView(R.layout.activity_custom);
        }

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
        Bundle bundle = getIntent().getExtras();
        needPlayAnimation = false;
        needPlaySound = false;
        soundApproval = "";
        String cardType = "";
        if(bundle != null) {
            needPlayAnimation = bundle.getBoolean(EntryExtraData.PARAM_ANIMATION_SUPPORT,false);
            needPlaySound = bundle.getBoolean(EntryExtraData.PARAM_SOUND_SUPPORT,false);
            soundApproval = bundle.getString(EntryExtraData.PARAM_SOUND_URI,"");
            cardType = bundle.getString(EntryExtraData.PARAM_CARD_TYPE,"");
        }
        Logger.d("needPlayAnimation : " + needPlayAnimation + " needPlaySound : " + needPlaySound);
        if (needPlayAnimation) {
            if (!StringUtils.isEmpty(cardType)) {
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
        if (StringUtils.isEmpty(cardType)) {
            helper.sendAbort();
        } else {
            Logger.d("CardType : " + cardType);
            if (CardType.VISA.equals(cardType)) {
                showVisaAmimation();
            } else {
                needPlayAnimation = false;
                if (needPlaySound) {
                    if(!StringUtils.isEmpty(soundApproval)){
                        playSound(soundApproval);
                    }else {
                        playSound(R.raw.boba);
                    }
                }
            }
        }
    }

    private void playSound(int resourcesId) {
        //Fix ANFDRC-977
        Intent intent = new Intent(this, PlayerService.class);
        intent.putExtra(PlayerService.PARAM_RESOURCE_ID, resourcesId);
        startService(intent);
        if (!needPlayAnimation) {
            helper.sendNext(true);
        }
    }

    private void playSound(String soundUri) {
        //Fix ANFDRC-977
        Intent intent = new Intent(this, PlayerService.class);
        intent.putExtra(PlayerService.PARAM_APPROVAL_SOUND,soundUri);
        startService(intent);

        if (!needPlayAnimation) {
            helper.sendNext(true);
        }
    }

    private void showVisaAmimation() {
        if (needPlayAnimation) {
            imageView.clearAnimation();
            Logger.d(".......load gif start.........");
            GifLoadOneTimeGif.loadOneTimeGif(this, R.raw.visa_animation, imageView, 1, new GifLoadOneTimeGif.GifListener() {
                @Override
                public void gifPlayComplete() {
                    Logger.d("VisaAnimationActivity gifPlayComplete");
                    helper.sendNext(true);
                }
            });
            Logger.d(".......load gif end.........");
        }
        if (needPlaySound) {
            playSound(R.raw.visa_sound);
        }
    }
}
