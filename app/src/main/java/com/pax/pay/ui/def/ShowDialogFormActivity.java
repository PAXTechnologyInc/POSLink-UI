/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2018-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.
 *
 * Module Date: 2019/12/20
 * Module Auth: Fahy.F
 * Description:
 *
 * Revision History:
 * Date                   Author                       Action
 * 2019/12/20              Fahy.F                       Create
 * ============================================================================
 */

package com.pax.pay.ui.def;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.pax.pay.ui.def.base.BaseStackActivity;
import com.pax.pay.ui.def.base.FinishRespStatusImpl;
import com.pax.us.pay.ui.component.utils.TickTimer;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst;
import com.pax.us.pay.ui.core.helper.ShowDialogFormHelper;
import com.paxus.utils.log.Logger;

import java.util.ArrayList;


public class ShowDialogFormActivity extends BaseStackActivity implements ShowDialogFormHelper.IShowDialogFormHelper, View.OnClickListener {
   private ShowDialogFormHelper helper;
    RecyclerView recyclerView;
    Button cancelBtn;
    Button clearBtn;
    Button confirmBtn;
    View bottomView;

    private LabelAdapter adapter;
    private Bundle reqBundle;
    private boolean isNoBlocking = false;
    private TickTimer tickTimer;
    private long timeout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_dialog_form;
    }

    @Override
    protected void setListeners() {
        cancelBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        reqBundle = getIntent().getExtras();
        String buttonType = reqBundle.getString(EntryExtraData.PARAM_BUTTON_TYPE, ManageUIConst.ButtonType.RADIO_BUTTON);

        setupRecyclerView(recyclerView);
        if (ManageUIConst.ButtonType.CHECK_BOX.equals(buttonType)) {
            confirmBtn.setText(R.string.dialog_accept);
            bottomView.setVisibility(View.VISIBLE);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void loadParam() {
        createTimer();
        tickTimer.start(getTickTimeout());

        recyclerView = findViewById(R.id.recycler_View);
        cancelBtn = findViewById(R.id.cancel_btn);
        clearBtn = findViewById(R.id.clear_btn);
        confirmBtn = findViewById(R.id.confirm_btn);
        bottomView = findViewById(R.id.bottom_layer);


        try {
            Bundle bundle = getIntent().getExtras();
            navTitle = bundle.getString(EntryExtraData.PARAM_TITLE);
            if (bundle.containsKey(EntryExtraData.PARAM_CONTINUE_SCREEN)
                    && (ManageUIConst.ContinuousScreen.DO_NOT_GO_TO_IDLE.equals(bundle.getString(EntryExtraData.PARAM_CONTINUE_SCREEN)))) {
                isNoBlocking = true;
            }
        } catch (Exception e) {
            Logger.e(e);
            isNoBlocking = false;
        }
        helper = new ShowDialogFormHelper(this, new FinishRespStatusImpl(this));
        //loadOtherParam();

    }


//    private void setTitleView(List<TextView> viewList) {
//        for (TextView textView : viewList) {
//            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_25));
//            textView.setMaxLines(1);
//            textView.setTextColor(Color.WHITE);
//            textView.setEllipsize(null);
//            toolBarEx.addDynamicTitle(textView);
//        }
//    }

    @Override
    protected void onClickProtected(View v) {
        int id = v.getId();
        if (id == R.id.cancel_btn) {
            tickTimerStop();
            helper.sendAbort();
            if (!isNoBlocking)
                finish();
        } else if (id == R.id.clear_btn) {
            adapter.clearCheckedItems();
        } else if (id == R.id.confirm_btn) {
            SparseBooleanArray checkList = adapter.getCheckedList();
            if (checkList != null && checkList.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < checkList.size(); i++) {
                    int key = checkList.keyAt(i);
                    if (checkList.get(key)) {
                        sb.append(key + 1).append(",");
                    }
                }
                String selectItem = sb.toString();
                if (selectItem.endsWith(","))
                    selectItem = selectItem.substring(0, selectItem.length() - 1);
                if (!TextUtils.isEmpty(selectItem)) setSelectItem(selectItem);
            }
        }
    }

    @Override
    protected boolean onKeyBackDown() {
        tickTimerStop();
        helper.sendAbort();
        if (!isNoBlocking)
            finish();
        return true;
    }

    private void setSelectItem(String selectItem) {
        tickTimerStop();
        helper.sendNext(selectItem);
        if (!isNoBlocking)
            finish();
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemViewCacheSize(4);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);

        adapter = new LabelAdapter(reqBundle);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStartHelper() {
        helper.start(this, getIntent());
    }

    @Override
    public void onAbortHelper() {
        helper.sendAbort();
        finish();
    }

    @Override
    public void finish() {
        helper.stop();
        super.finish();
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
        //this.leftTime = leftTime;
        //setTitle(String.format(Locale.US, "%s ( %d )", getString(R.string.signature), leftTime));
    }

    protected void restartTimer() {
        tickTimerStop();
        createTimer();
        tickTimer.start(getTickTimeout());
    }

    public void tickTimerStop() {
        if (tickTimer != null) {
            tickTimer.stop();
            tickTimer = null;
        }
    }

    protected void onTimerFinish() {
        if (timeout <= 0)
            return;

        tickTimerStop();
        helper.sendTimeout();

        if (!isNoBlocking) {
            finish();
        }
    }

    protected long getTickTimeout() {
        timeout = -1;
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(EntryExtraData.PARAM_TIMEOUT)) {
            long time = getIntent().getExtras().getLong(EntryExtraData.PARAM_TIMEOUT, -1L);
            timeout = (int)(time/1000l);
        }

//        if (timeout == 0)
//            isNoBlocking = true;

        return timeout;
    }



    private class LabelAdapter extends RecyclerView.Adapter<LabelAdapter.LabelItemHolder> {
        private final Bundle bundle;
        private final ArrayList<String> labelList;
        private SparseBooleanArray checkList;
        private final ColorStateList colorStateList;

        LabelAdapter(Bundle bundle) {
            this.bundle = bundle;
            labelList = new ArrayList<>();
            addLabel(bundle.getStringArray(EntryExtraData.PARAM_LABELS));

            if (ManageUIConst.ButtonType.CHECK_BOX.equals(bundle.getString(EntryExtraData.PARAM_BUTTON_TYPE))) {
                checkList = new SparseBooleanArray();
                String[] labelProperys = bundle.getStringArray(EntryExtraData.PARAM_LABELS_PROPERTY);
                for(int i=0; i<4; i++) {
                    if (i<labelProperys.length)
                        checkList.put(i, !ManageUIConst.LabelProperty.UNCHECKED.equals(labelProperys[i]));
                    else
                        checkList.put(i, false);
                }
            }

            int colorPrimaryDark = ContextCompat.getColor(ShowDialogFormActivity.this, R.color.primary_dark);
            colorStateList = new ColorStateList(
                    new int[][]{
                            new int[]{-android.R.attr.state_checked},
                            new int[]{android.R.attr.state_checked}
                    },
                    new int[]{
                            colorPrimaryDark,
                            colorPrimaryDark
                    }
            );
        }

        private void addLabel(String[] labels) {
            for(int i=0; i<4; i++) {
                if (i<labels.length) {
                    if (!TextUtils.isEmpty(labels[i]))
                        labelList.add(labels[i]);
                }
            }
        }

        @Override
        public LabelItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LabelItemHolder vh = null;
            if (viewType == 1) {
                AppCompatRadioButton rb = new AppCompatRadioButton(parent.getContext());
                CompoundButtonCompat.setButtonTintList(rb, colorStateList);
                vh = new LabelItemHolder(rb);
            } else if (viewType == 2) {
                CheckBox cb = new CheckBox(parent.getContext());
                CompoundButtonCompat.setButtonTintList(cb, colorStateList);
                vh = new LabelItemHolder(cb);
            }
            if (vh != null) {
                vh.itemView.setPadding(parent.getResources().getDimensionPixelSize(R.dimen.default_gap), 0, 0, 0);
            }
            return vh;
        }

        @Override
        public void onBindViewHolder(LabelItemHolder holder, int position) {
            CompoundButton compoundButton = (CompoundButton) holder.itemView;
            compoundButton.setText(labelList.get(position));
            if (compoundButton instanceof CheckBox) {
                compoundButton.setChecked(checkList.get(position));
            }
            compoundButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
                int handlePosition = holder.getAdapterPosition();
                if (buttonView instanceof RadioButton) {
                    handleRadioButton(handlePosition, isChecked);
                } else if (buttonView instanceof CheckBox) {
                    handleCheckBox(handlePosition, isChecked);
                }
            });
        }

        private void handleRadioButton(int position, boolean isChecked) {
            if (isChecked) {
                setSelectItem(String.valueOf(position + 1));
            }
        }

        private void handleCheckBox(int position, boolean isChecked) {
            if (checkList != null) checkList.put(position, isChecked);
        }

        private void clearCheckedItems() {
            if (checkList != null) checkList.clear();
            notifyDataSetChanged();
        }

        private @Nullable
        SparseBooleanArray getCheckedList() {
            return checkList;
        }

        @Override
        public int getItemViewType(int position) {
            String buttonType = bundle.getString(EntryExtraData.PARAM_BUTTON_TYPE);
            return Integer.parseInt(buttonType);
        }

        @Override
        public int getItemCount() {
            return labelList.size();
        }

        class LabelItemHolder extends RecyclerView.ViewHolder{

            LabelItemHolder(CompoundButton itemView) {
                super(itemView);
            }
        }
    }
}
