package com.pax.pay.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pax.pay.ui.base.BaseActivityWithTickForAction;
import com.pax.pay.ui.base.BaseViewHolder;
import com.pax.pay.ui.constant.EUIMessageKey;
import com.pax.pay.ui.handler.OptionsHandler;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by Charles.S on 2017/5/5.
 */

abstract class SelectLineActivity<T> extends BaseActivityWithTickForAction<T> {
    private TextView tvPrompt;
    RecyclerView mRecyclerView;
    Button confirmBtn;

    private String prompt1;
    private RecyclerView.Adapter<BaseViewHolder<String>> mAdapter;
    private int selected = -1;
    private List<String> data = new ArrayList<>();
    private String currentAction;
    private String senderPackage;
    private OptionsHandler optionsHandler;

    private int mExpandedPosition = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_ebt_type;
    }

    @Override
    protected void setListeners() {
        confirmBtn.setOnClickListener(this);
    }

    @Override
    protected void loadParam() {

        tvPrompt = (TextView) findViewById(R.id.prompt_select);
        mRecyclerView = (RecyclerView) findViewById(R.id.option_select);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);

        navBack = true;
        navTitle = "";
        prompt1 = getIntent().getStringExtra(EUIMessageKey.INTENT_KEY_MESSAGE);
        senderPackage = getIntent().getStringExtra(EUIMessageKey.KEY_SENDER_PACKAGE);
        data = getIntent().getStringArrayListExtra(EUIMessageKey.INTENT_KEY_OPTIONS);
        currentAction = getIntent().getAction();
        loadOtherParam(data);
        optionsHandler = new OptionsHandler(senderPackage);
    }

    @Override
    protected void initViews() {
        if (prompt1 != null)
            tvPrompt.setText(prompt1);
        confirmBtn.setEnabled(false);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerView.Adapter<BaseViewHolder<String>>() {
            @NonNull
            @Override
            public BaseViewHolder<String> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new OptionModelViewHolder(LayoutInflater.from(SelectLineActivity.this).inflate(R.layout.item_mode_grid, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder<String> holder, int position) {

                String viewData = data.get(position);

                if (viewData == null)
                    return;
                holder.bindBaseView(viewData, position);
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected boolean onKeyBackDown() {
        //TO DO send Abort command
        optionsHandler.sendAbort();
        return true;
    }

    @Override
    protected boolean onKeyEnterDown() {
        Integer index = (Integer) convert(data.get(selected));
        optionsHandler.sendNext(EUIMessageKey.INTENT_KEY_INDEX, index);
        return true;
    }

    @Override
    public final void onClick(View v) {
        int i = v.getId();
        if (i == R.id.confirm_btn) {
            Integer index = (Integer) convert(data.get(selected));
            optionsHandler.sendNext(EUIMessageKey.INTENT_KEY_INDEX, index);
        }
    }

    class OptionModelViewHolder extends BaseViewHolder<String> {
        TextView textView;

        OptionModelViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView() {
            textView = (TextView) itemView.findViewById(R.id.mode_grid_tv);
        }

        @Override
        protected void bindView(View view, final String dataBean, final int pos) {
            //textView = (TextView) view.findViewById(R.id.mode_grid_tv);
            textView.setText(dataBean);
            textView.setSelected(selected == pos);
            textView.setOnClickListener(v -> {
                selected = pos;
                confirmBtn.setEnabled(true);
                mAdapter.notifyDataSetChanged();
            });
        }
    }

    protected abstract T convert(String content);

    protected abstract void loadOtherParam(List<String> message);
}
