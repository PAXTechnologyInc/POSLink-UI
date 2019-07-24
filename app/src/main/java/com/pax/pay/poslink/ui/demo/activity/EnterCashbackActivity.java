package com.pax.pay.poslink.ui.demo.activity;

import android.content.Context;
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
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pax.pay.poslink.ui.demo.R;
import com.pax.pay.poslink.ui.demo.base.BaseViewHolder;
import com.pax.pay.poslink.ui.demo.base.RespStatusImpl;
import com.pax.pay.poslink.ui.demo.utils.StringUtils;
import com.pax.us.pay.ui.core.helper.EnterCashbackHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnterCashbackActivity extends AppCompatActivity implements View.OnClickListener, EnterCashbackHelper.IEnterCashbackListener {

    TextView promptTv;
    EditText mEditText;
    Button confirmBtn;
    RecyclerView mRecyclerView;

    private int minLen, maxLen;

    private RecyclerView.Adapter<BaseViewHolder<String>> mAdapter;
    private int selected = -1;
    private List<String> selectOption = new ArrayList<>();
    private int viewType = 0;
    private static final int SELECT_AMOUNT = 1;
    private static final int INPUT_AMOUNT = 0;

    private EnterCashbackHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_option);

        promptTv = (TextView) findViewById(R.id.prompt_tv);
        mEditText = (EditText) findViewById(R.id.data_edt);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.option_select);

        promptTv.setText("Please Input Cashback Amount");
        minLen = 0;
        maxLen = 12;
        mEditText.setCursorVisible(false);
        mEditText.requestFocus();
        mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        mEditText.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
        }, 200);
        viewType = INPUT_AMOUNT;

        helper = new EnterCashbackHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());
        ActivityLocalManager.getInstance().addActivity(this);
    }


    @Override
    public void onClick(View view) {
        long amount;
        if (viewType == INPUT_AMOUNT)
            amount = StringUtils.parseLong(mEditText.getText().toString(), -1);
        else {
            amount = StringUtils.parseLong((String) selectOption.get(selected));
        }

        helper.sendNext(amount);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            confirmBtn.performClick();
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
    protected void onResume() {
        super.onResume();
        if (viewType == SELECT_AMOUNT) {
            mEditText.setVisibility(View.GONE);
            promptTv.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            confirmBtn.setEnabled(false);
        } else {
            mEditText.setVisibility(View.VISIBLE);
            promptTv.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onShowCurrency(@Nullable String currency, boolean isPoint) {
    }


    @Override
    public void onShowAmount(long amount) {

    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message) {
    }


    @Override
    public void onShowCashbackOptions(@NonNull String[] options) {
        mEditText.setVisibility(View.GONE);
        promptTv.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        confirmBtn.setEnabled(false);
        setAmountOption(options);
        viewType = SELECT_AMOUNT;
    }

    void setAmountOption(@NonNull String[] options) {
        selectOption = Arrays.asList(options);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerView.Adapter<BaseViewHolder<String>>() {
            @NonNull
            @Override
            public BaseViewHolder<String> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new EnterCashbackActivity.OptionModelViewHolder(LayoutInflater.from(EnterCashbackActivity.this).inflate(R.layout.item_mode_grid, parent, false));
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
