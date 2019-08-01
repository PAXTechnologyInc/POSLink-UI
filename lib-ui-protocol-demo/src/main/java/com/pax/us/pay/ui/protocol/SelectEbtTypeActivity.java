package com.pax.us.pay.ui.protocol;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.EntryResponse;
import com.pax.us.pay.ui.protocol.api.IRespStatus;
import com.pax.us.pay.ui.protocol.utils.BaseViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Charles.S on 2017/5/5.
 */

public class SelectEbtTypeActivity extends AppCompatActivity implements View.OnClickListener, IRespStatus {

    RecyclerView mRecyclerView;
    Button confirmBtn;
    private TextView tvPrompt;
    private RecyclerView.Adapter<BaseViewHolder<String>> mAdapter;
    private int selected = -1;
    private List<String> selectOption = new ArrayList<>();
    private String packageName;

    private ActionHandlerImp helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_options);

        tvPrompt = (TextView) findViewById(R.id.prompt_select);
        mRecyclerView = (RecyclerView) findViewById(R.id.option_select);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);
        confirmBtn.setEnabled(false);


        parseIntent(getIntent());
        helper = new ActionHandlerImp(this, packageName, this);
        helper.start();

    }

    @Override
    public void onClick(View view) {
        if (selected != -1) {
            Integer index = (Integer) selectOption.indexOf(selectOption.get(selected));
            helper.sendNext(packBundle(index));
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
    protected void onStart() {
        helper.start();
        super.onStart();
    }

    @Override
    protected void onStop() {
        moveTaskToBack(false);
        super.onStop();
        helper.stop();
    }

    private void parseIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        String action = intent.getAction();
        packageName = intent.getStringExtra(EntryExtraData.PARAM_PACKAGE);
        String transType = bundle.getString(EntryExtraData.PARAM_TRANS_TYPE);
        String message = bundle.getString(EntryExtraData.PARAM_MESSAGE);
        tvPrompt.setText(TextUtils.isEmpty(message) ? "Please Select EBT Type" : message);
        String transMode = bundle.getString(EntryExtraData.PARAM_TRANS_MODE);
        String[] options = bundle.getStringArray(EntryExtraData.PARAM_OPTIONS);
        if (options != null || options.length > 0) {
            onShowOptions(options);
        }
    }

    private Bundle packBundle(int index) {
        Bundle bundle = new Bundle();
        bundle.putLong(EntryRequest.PARAM_INDEX, index);
        return bundle;
    }

    @Override
    public void onAccepted() {

    }

    @Override
    public void onDeclined(@Nullable Bundle bundle) {
        final long code = bundle.getLong(EntryResponse.PARAM_CODE, -1);
        final String message = bundle.getString(EntryResponse.PARAM_MSG);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String buff;
                if (TextUtils.isEmpty(message))
                    buff = "Trans Failed! Error Code : " + code;
                else
                    buff = message + "\n Error Code : " + code;
                Toast.makeText(SelectEbtTypeActivity.this, buff, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void onShowOptions(String[] options) {
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
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected = pos;
                    confirmBtn.setEnabled(true);
                    mAdapter.notifyDataSetChanged();

                }
            });
        }
    }

}
