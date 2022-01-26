package com.pax.pay.ui.def;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pax.pay.ui.def.base.FinishRespStatusImpl;
import com.pax.pay.ui.def.eventbus.ConfirmDialogEndEvent;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.pax.pay.ui.def.utils.SelectOptionContent;
import com.pax.us.pay.ui.core.helper.SelectOptionsHelper;
import com.paxus.view.BaseAppCompatActivity;
import com.paxus.view.dialog.DialogUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jolie.Yang on 2017/5/5.
 */

public abstract class SelectDialogActivity extends BaseAppCompatActivity implements SelectOptionsHelper.ISelectOptionListener {

    protected int selected = -1;
    protected int checkedItem = -1;
    private Dialog selectDialog = null;
    private SelectOptionsHelper helper = null;
    private List<String> mSelectOption;
    private String prompt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        prompt = loadOtherParam();
        EventBusUtil.doEvent(new ConfirmDialogEndEvent());
        EventBusUtil.register(this);
        helper = new SelectOptionsHelper(this, new FinishRespStatusImpl(this));
    }

    private void initDialog() {
        selectDialog = DialogUtils.showSelectDialog(this,
                prompt,
                (dialog, which) -> {
                    selected = which;
                    helper.sendNext(selected);

                },
                true,
                dialog -> {
                    helper.sendAbort();
                },
                true,
                mSelectOption.toArray(new String[0]),
                checkedItem
        );
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
    }

    @Override
    public void onShowOptions(@NonNull String[] options) {
        if (options.length > 0) {
            //mSelectOption = Arrays.asList(options);
            mSelectOption = new ArrayList<>();
            for (int i = 0; i < options.length; i++) {
                if (SelectOptionContent.SELECT_OPTION_MAP.containsKey(options[i])) {
                    mSelectOption.add(getString(SelectOptionContent.SELECT_OPTION_MAP.get(options[i])));
                } else {
                    mSelectOption.add(options[i]);
                }
            }
            initDialog();
        }
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
    protected void onDestroy() {
        if (selectDialog != null) {
            selectDialog.dismiss();
            selectDialog = null;
        }
        EventBusUtil.unregister(this);
        super.onDestroy();
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEndEvent(ConfirmDialogEndEvent event) {
        EventBusUtil.unregister(this);
        finish();
    }

    protected abstract String loadOtherParam();

}

