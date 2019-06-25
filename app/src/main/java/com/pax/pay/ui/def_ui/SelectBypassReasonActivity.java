package com.pax.pay.ui.def_ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pax.pay.ui.def_ui.App.AppManager;
import com.pax.pay.ui.def_ui.base.BaseViewHolder;
import com.pax.us.pay.ui.base.message.UIMessageManager;
import com.pax.us.pay.ui.base.message.api.IMessageListener;
import com.pax.us.pay.ui.base.message.api.IOptionListener;
import com.pax.us.pay.ui.base.message.helper.OptionsHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charles.S on 2017/5/5.
 */

public class SelectBypassReasonActivity extends AppCompatActivity implements View.OnClickListener, IMessageListener, IOptionListener {

    private TextView tvPrompt;
    RecyclerView mRecyclerView;
    Button confirmBtn;

    private String prompt1;
    private RecyclerView.Adapter<BaseViewHolder<String>> mAdapter;
    private int selected = -1;
    private List<String> selectOption = new ArrayList<>();

    private OptionsHelper helper = new OptionsHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_options);

        tvPrompt = (TextView) findViewById(R.id.prompt_select);
        mRecyclerView = (RecyclerView) findViewById(R.id.option_select);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);

        tvPrompt.setText(getResources().getText(R.string.select_bypass_reason));
        DisplayRespStatus displayRespStatus = new DisplayRespStatus(this);
        displayRespStatus.setListener(new DisplayRespStatus.DisplayRespStatusListener() {
            @Override
            public void unRegister() {
                UIMessageManager.getInstance().unregisterUI(SelectBypassReasonActivity.this, helper);
            }
        });
        UIMessageManager.getInstance().registerUI(this, this, helper, getIntent(), displayRespStatus);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public void onClick(View view) {
        Integer index = (Integer) selectOption.indexOf(selectOption.get(selected));
        helper.sendObjNext(index);
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
        moveTaskToBack(true);
        super.onStop();
    }


    @Override
    public void onShowOptions(List<String> options) {
        if (options != null) {
            selectOption = options;
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
            mRecyclerView.setLayoutManager(layoutManager);
            mAdapter = new RecyclerView.Adapter<BaseViewHolder<String>>() {
                @NonNull
                @Override
                public BaseViewHolder<String> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    return new OptionModelViewHolder(LayoutInflater.from(SelectBypassReasonActivity.this).inflate(R.layout.item_mode_grid, parent, false));
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

    @Override
    public void onShowMessage(String message) {

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
