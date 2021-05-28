package com.pax.pay.ui.def;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.utils.DisplayTransInfoUtils;
import com.pax.us.pay.ui.core.helper.ConfirmDetailsHelper;

public abstract class DisplayDetailsActivity extends BaseAppActivity implements ConfirmDetailsHelper.IConfirmDetailsListener {
    TextView textTitle;
    Button btnConfirm;
    Button btnCancel;
    LinearLayout llDetailContainer;

    private DisplayTransInfoUtils displayTransInfoUtils;
    private DisplayTransInfoUtils.ShowType showType;

    private ConfirmDetailsHelper helper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_display_information;
    }

    @Override
    protected boolean onKeyEnterDown() {
        helper.sendNext();
        return true;
    }

    @Override
    protected boolean onKeyBackDown() {
        helper.sendAbort();
        return true;
    }


    @Override
    public void onClickProtected(View v) {
        int i = v.getId();
        if (i == R.id.cancel_btn) {
            helper.sendAbort();
        } else if (i == R.id.confirm_btn) {
            helper.sendNext();
        }
    }

    @Override
    protected void setListeners() {
        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }


    @Override
    protected void initViews() {
    }

    @Override
    protected void loadParam() {
        textTitle = findViewById(R.id.title_id);
        btnConfirm = findViewById(R.id.confirm_btn);
        btnCancel = findViewById(R.id.cancel_btn);
        llDetailContainer = findViewById(R.id.detail_layout);

        textTitle.setVisibility(View.GONE);
        helper = new ConfirmDetailsHelper(this, new RespStatusImpl(this));
        //helper.start(this, getIntent());
        showType = loadOtherParam();
        //Fixed ANFDRC-645
        btnConfirm.requestFocus();
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
    public void onShowDetails(@NonNull String[] key, @NonNull String[] value) {
        if (key.length != value.length)
            return;
        displayTransInfoUtils = new DisplayTransInfoUtils(llDetailContainer, key, value, showType);
        displayTransInfoUtils.addViewsToViewGroup(llDetailContainer);
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    protected abstract DisplayTransInfoUtils.ShowType loadOtherParam();
}
