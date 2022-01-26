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

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.pax.pay.ui.def.base.BaseStackActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst;
import com.pax.us.pay.ui.core.helper.ShowDialogFormHelper;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class ShowDialogFormActivity extends BaseStackActivity implements ShowDialogFormHelper.IShowDialogFormHelper, View.OnClickListener {
   private ShowDialogFormHelper helper;
    RecyclerView recyclerView;
    Button cancelBtn;
    Button clearBtn;
    Button confirmBtn;
    View bottomView;

    private LabelAdapter adapter;
    private Bundle reqBundle;
    private boolean continuousScreen = false;
    private Timer timer;

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
        super.loadParam();
        recyclerView = findViewById(R.id.recycler_View);
        cancelBtn = findViewById(R.id.cancel_btn);
        clearBtn = findViewById(R.id.clear_btn);
        confirmBtn = findViewById(R.id.confirm_btn);
        bottomView = findViewById(R.id.bottom_layer);

        reqBundle = getIntent().getExtras();

        navTitle = "";
        continuousScreen = false;
        long timeoutMs = -1;
        stopTimer();

        if(reqBundle != null) {
            navTitle = reqBundle.getString(EntryExtraData.PARAM_TITLE);
            continuousScreen = ManageUIConst.ContinuousScreen.DO_NOT_GO_TO_IDLE.equals(reqBundle.getString(EntryExtraData.PARAM_CONTINUE_SCREEN, ""));
            timeoutMs = reqBundle.getLong(EntryExtraData.PARAM_TIMEOUT, -1L);
        }


        startTimer(timeoutMs);
        helper = new ShowDialogFormHelper(this, new RespStatus(this));
        //loadOtherParam();

    }

    @Override
    protected void onClickProtected(View v) {
        int id = v.getId();
        if (id == R.id.cancel_btn) {
            onAbort();
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
        onAbort();
        return true;
    }

    private void setSelectItem(String selectItem) {
        stopTimer();
        helper.sendNext(selectItem);
        if (!continuousScreen)
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
        onAbort();
    }

    @Override
    public void finish() {
        helper.stop();
        super.finish();
    }

    private void startTimer(long timeoutMs) {
        if(timeoutMs > 0) {
            stopTimer();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    onTimeout();
                }
            }, timeoutMs);
        }
    }

    private void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }
    private void onTimeout(){
        stopTimer();
        helper.sendTimeout();
        if (!continuousScreen) {
            finish();
        }
    }
    private void onAbort(){
        stopTimer();
        helper.sendAbort();
        if (!continuousScreen) {
            finish();
        }
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


    private class RespStatus extends RespStatusImpl {

        public RespStatus(Activity activity) {
            super(activity);
        }
        @Override
        public void onAccepted() {
            //don't close activity.
        }
    }

}
