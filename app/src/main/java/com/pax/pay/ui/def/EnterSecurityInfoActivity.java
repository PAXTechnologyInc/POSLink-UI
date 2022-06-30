package com.pax.pay.ui.def;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.view.utils.ViewUtils;

abstract public class EnterSecurityInfoActivity extends BaseAppActivity implements IMessageListener {


//    TextView amountTv;
    TextView promptTv;
    TextView mEditText;
    Button confirmBtn;
//    LinearLayout transAmountLayout;

//    private long totalAmount;
    private String prompt = null;

    private boolean isPoint;
    private boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);
        first = true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_security_info;
    }

    @Override
    protected void loadParam() {
//        amountTv = findViewById(R.id.amount_tv);
        promptTv = findViewById(R.id.prompt_tv);
        mEditText = findViewById(R.id.data_edt);
//        transAmountLayout = findViewById(R.id.trans_amount_layout);
        confirmBtn = findViewById(R.id.confirm_btn);

//        totalAmount = 0;
//        transAmountLayout.setVisibility(View.INVISIBLE);

        prompt = null;
        navBack = true;
        loadOtherParam();
    }

    @Override
    public void initViews() {
        if (!ViewUtils.isScreenOrientationPortrait(this)) {
            confirmBtn.setVisibility(View.GONE);
        }

//        amountTv.setText(CurrencyConverter.convert(totalAmount));
        if ((prompt != null) && (prompt.length() != 0))
            promptTv.setText(prompt);
    }


    // 当页面加载完成之后再执行弹出键盘的动作
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (first && hasFocus) {
            first = false;
            ViewTreeObserver observer = mEditText.getViewTreeObserver();
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mEditText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int[] location = new int[2];
                    mEditText.getLocationInWindow(location);
                    int x = location[0];
                    int y = location[1];
                    int barHeight = 0;
                    boolean immersiveSticky = (getWindow().getDecorView().getSystemUiVisibility() &
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) > 0;
                    if (!immersiveSticky) {
//                    int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//                    if (resourceId > 0) {
//                        barHeight = getResources().getDimensionPixelSize(resourceId);
//                    }
                        Rect outRect1 = new Rect();
                        getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);
                        barHeight = outRect1.top;  //statusBar's height

                    }
                    sendNext(x, y - barHeight, mEditText.getWidth(), mEditText.getHeight());
                }
            });
            mEditText.requestLayout(); //On some devices, onGlobalLayout is not invoked, call requestLayout to force
        }
        if (hasFocus && ViewUtils.canNavigationBarImmersiveSticky()) {
            ViewUtils.hideNavigationBar(getWindow().getDecorView());
        }
    }

    public void setCurrencyName(String currencyName) {
        if (!TextUtils.isEmpty(currencyName)) {
            CurrencyConverter.setDefCurrency(currencyName);
        }
    }

    public void setPoint(boolean point) {
        isPoint = point;
    }

    public void setTotalAmount(long totalAmount) {
//        this.totalAmount = totalAmount;
//        if (isPoint) {
//            amountTv.setText(String.valueOf(totalAmount));
//        } else {
//            amountTv.setText(CurrencyConverter.convert(totalAmount, ""));
//        }
//        transAmountLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setListeners() {
        confirmBtn.setOnClickListener(this);
    }


    @Override
    protected boolean onKeyEnterDown() {
        sendNext();
        return true;
    }

    @Override
    public void onClickProtected(View view) {
        if (view.getId() == R.id.confirm_btn) {
            sendNext();
        }
    }

    @Override
    protected boolean onKeyBackDown() {
        sendAbort();
        return true;
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
        promptTv.setText(prompt);
    }

    protected abstract void loadOtherParam();

    protected abstract void sendAbort();

    protected abstract void sendNext();

    protected abstract void sendNext(int offsetX, int offsetY, int width, int height);

}
