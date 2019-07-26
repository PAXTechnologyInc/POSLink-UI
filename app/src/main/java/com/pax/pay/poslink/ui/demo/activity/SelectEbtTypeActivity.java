package com.pax.pay.poslink.ui.demo.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pax.pay.poslink.ui.demo.R;
import com.pax.pay.poslink.ui.demo.base.BaseViewHolder;
import com.pax.pay.poslink.ui.demo.base.RespStatusImpl;
import com.pax.us.pay.ui.core.helper.SelectOptionsHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Charles.S on 2017/5/5.
 */

public class SelectEbtTypeActivity extends AppCompatActivity implements View.OnClickListener, SelectOptionsHelper.ISelectOptionListener {

    RecyclerView mRecyclerView;
    Button confirmBtn;
    private TextView tvPrompt;
    private String prompt1;
    private RecyclerView.Adapter<BaseViewHolder<String>> mAdapter;
    private int selected = -1;
    private List<String> selectOption = new ArrayList<>();

    private SelectOptionsHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_options);

        tvPrompt = (TextView) findViewById(R.id.prompt_select);
        mRecyclerView = (RecyclerView) findViewById(R.id.option_select);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);
        confirmBtn.setEnabled(false);


        tvPrompt.setText("Please Select EBT Type");
        helper = new SelectOptionsHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());

    }

    @Override
    public void onClick(View view) {
        if (selected != -1) {
            Integer index = (Integer) selectOption.indexOf(selectOption.get(selected));
            helper.sendNext(index);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        }
        return false;
    }

    @Override
    protected void onStop() {
        moveTaskToBack(false);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        helper.stop();
        super.onDestroy();
    }


    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, boolean isDemo) {

    }

    @Override
    public void onShowOptions(@NonNull String[] options) {
        if (options != null) {
            selectOption = Arrays.asList(options);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
            mRecyclerView.setLayoutManager(layoutManager);
            mAdapter = new RecyclerView.Adapter<BaseViewHolder<String>>() {
                @NonNull
                @Override
                public BaseViewHolder<String> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    return new OptionModelViewHolder(LayoutInflater.from(SelectEbtTypeActivity.this).inflate(R.layout.item_mode_grid, parent, false));
                }

                @Override
                public void onBindViewHolder(@NonNull BaseViewHolder<String> holder, int position) {

                    String viewData = selectOption.get(position);

                    if (viewData == null)
                        return;
                    holder.bindBaseView(viewData, position);
                }

                @Override
                public int getItemCount() {
                    return selectOption.size();
                }
            };
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(mAdapter);
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
            textView.setText(dataBean);
            textView.setSelected(selected == pos);
            textView.setOnClickListener(v -> {
                selected = pos;
                confirmBtn.setEnabled(true);
                mAdapter.notifyDataSetChanged();
            });
        }
    }

}
