package com.pax.pay.ui.def.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.pax.pay.ui.def.R;
import com.paxus.view.dialog.OptAnimationLoader;
import com.paxus.view.dialog.SuccessTickView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

public class TransResultAlertDialog extends Dialog {

    public static final int TYPE_SUCC = 0;
    public static final int TYPE_FAIL = 1;

    @IntDef({TYPE_SUCC, TYPE_FAIL})
    @Retention(RetentionPolicy.SOURCE)
    @Target(value = {ElementType.METHOD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.FIELD})
    public @interface DialogType {
    }

    private @DialogType
    final
    int dialogType;

    private final AnimationSet mSuccessLayoutAnimSet;
    private final Animation mSuccessBowAnim;

    private final String mTitle;
    private final String mContent;
    private TextView mTitleTextView;
    private TextView mContentTextView;

    //For fail type
    private FrameLayout mErrorFrame;
    private ImageView mErrorX;
    private final Animation mErrorInAnim;
    private final AnimationSet mErrorXInAnim;


    //For succ type
    private FrameLayout mSuccessFrame;
    private SuccessTickView mSuccessTick;


    private View mSuccessLeftMask;
    private View mSuccessRightMask;

    public TransResultAlertDialog(Context context, @DialogType int type, String title, String content) {
        super(context, R.style.PaxTheme_AlertDialog);
        dialogType = type;

        setCancelable(false);
        setCanceledOnTouchOutside(false);

        mErrorInAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.error_frame_in);
        mErrorXInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.error_x_in);

        // 2.3.x system don't support alpha-animation on layer-list drawable
        // remove it from animation set
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
            List<Animation> childAnims = mErrorXInAnim.getAnimations();
            int idx = 0;
            for (; idx < childAnims.size(); idx++) {
                if (childAnims.get(idx) instanceof AlphaAnimation) {
                    break;
                }
            }
            if (idx < childAnims.size()) {
                childAnims.remove(idx);
            }
        }
        mSuccessBowAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.success_bow_roate);
        mSuccessLayoutAnimSet = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(),
                R.anim.success_mask_layout);

        mTitle = title;
        mContent = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trans_ret_alert_dialog);
        Window window = getWindow();
        if (window == null)
            return;
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mErrorFrame = findViewById(R.id.error_frame);
        mErrorX = mErrorFrame.findViewById(R.id.error_x);
        mSuccessFrame = findViewById(R.id.success_frame);
        mSuccessTick = mSuccessFrame.findViewById(R.id.success_tick);
        mSuccessLeftMask = mSuccessFrame.findViewById(R.id.mask_left);
        mSuccessRightMask = mSuccessFrame.findViewById(R.id.mask_right);

        mTitleTextView = findViewById(R.id.title_text);
        mContentTextView = findViewById(R.id.content_text);

        setTitle(mTitle);
        setContent(mContent);

        updateAlertView();
    }

    private void setTitle(String text) {
        mTitleTextView.setText(text);
        mTitleTextView.setVisibility(mTitleTextView.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
    }

    private void setContent(String content) {
        mContentTextView.setText(content);
        mContentTextView.setVisibility(mContentTextView.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
    }

    private void updateAlertView() {
        switch (dialogType) {
            case TYPE_FAIL:
                mErrorFrame.setVisibility(View.VISIBLE);
                mSuccessFrame.setVisibility(View.GONE);
                break;
            case TYPE_SUCC:
                mErrorFrame.setVisibility(View.GONE);
                mSuccessFrame.setVisibility(View.VISIBLE);
                // initial rotate layout of success mask
                mSuccessLeftMask.startAnimation(mSuccessLayoutAnimSet.getAnimations().get(0));
                mSuccessRightMask.startAnimation(mSuccessLayoutAnimSet.getAnimations().get(1));
                break;
            default:
                break;
        }
    }

    private void playAnimation() {
//        if (dialogType == TYPE_FAIL) {
//            mErrorFrame.startAnimation(mErrorInAnim);
//            mErrorX.startAnimation(mErrorXInAnim);
//        } else if (dialogType == TYPE_SUCC) {
//            mSuccessTick.startTickAnim();
//            mSuccessRightMask.startAnimation(mSuccessBowAnim);
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        playAnimation();
    }

}
