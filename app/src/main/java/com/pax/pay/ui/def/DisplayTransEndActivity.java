package com.pax.pay.ui.def;


import android.os.Bundle;

import androidx.annotation.Nullable;

import com.pax.pay.ui.def.base.FinishRespStatusImpl;
import com.pax.us.pay.ui.core.helper.ShowOnlyHelper;
import com.paxus.view.BaseAppCompatActivity;

public class DisplayTransEndActivity extends BaseAppCompatActivity implements ShowOnlyHelper.IShowOnlyListener{

    private ShowOnlyHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        helper = new ShowOnlyHelper(this, new FinishRespStatusImpl(this));
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
    public void finish() {
        super.finish();
        //remove activity animation
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //helper.sendNext();
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) helper.sendNext();
    }

}
