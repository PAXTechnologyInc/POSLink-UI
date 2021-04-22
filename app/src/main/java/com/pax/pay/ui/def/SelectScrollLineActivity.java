package com.pax.pay.ui.def;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.recyclerview.GridSpacingItemDecoration;
import com.pax.pay.ui.def.utils.OptionModelViewHolder;
import com.pax.pay.ui.def.utils.SelectOptionContent;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.helper.SelectOptionsHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Charles.S on 2017/5/5.
 */

abstract class SelectScrollLineActivity extends BaseAppActivity implements SelectOptionsHelper.ISelectOptionListener {
    RecyclerView mRecyclerView;
    Button confirmBtn;
    TextView amountTv;
    LinearLayout transAmountLayout;
    private String prompt1;
    private TextView tvPrompt;
    private int spanCount;

    private RecyclerView.Adapter<OptionModelViewHolder> mAdapter;
    private int selected = -1;
    private List<String> data = new ArrayList<>();

    private long amount;
    private SelectOptionsHelper helper;

    public void setData(List<String> data) {
        this.data = data;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_scroll_line_default;
    }

    @Override
    protected void setListeners() {
        confirmBtn.setOnClickListener(this);
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    @Override
    protected void loadParam() {

        tvPrompt = findViewById(R.id.prompt_select);
        mRecyclerView = findViewById(R.id.option_select);
        confirmBtn = findViewById(R.id.confirm_btn);
        amountTv = findViewById(R.id.amount_tv);
        transAmountLayout = findViewById(R.id.trans_amount_layout);
        amount = 0;
        spanCount = 2;
        CurrencyConverter.setDefCurrency(CurrencyType.USD);
        prompt1 = loadOtherParam();
        helper = new SelectOptionsHelper(this, new RespStatusImpl(this));
    }

    @Override
    protected void initViews() {
        if (amount != 0) {
            amountTv.setText(CurrencyConverter.convert(amount, ""));
            transAmountLayout.setVisibility(View.VISIBLE);
        } else {
            transAmountLayout.setVisibility(View.GONE);
        }
        if (prompt1 != null)
            tvPrompt.setText(prompt1);
        confirmBtn.setEnabled(false);
    }

    private void showOptions() {

        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount, RecyclerView.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerView.Adapter<OptionModelViewHolder>() {
            @NonNull
            @Override
            public OptionModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new OptionModelViewHolder(LayoutInflater.from(SelectScrollLineActivity.this).inflate(R.layout.item_mode_grid, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull OptionModelViewHolder holder, int position) {

                String viewData = data.get(position);

                if (viewData == null)
                    return;

                holder.itemView.setContentDescription(viewData);
                if (SelectOptionContent.SELECT_OPTION_MAP.containsKey(viewData)) {
                    String text = getResources().getString(SelectOptionContent.SELECT_OPTION_MAP.get(viewData));
                    holder.textView.setText(text);
                } else {
                    holder.textView.setText(viewData);
                }
                holder.textView.setSelected(selected == position);
                holder.textView.setOnClickListener(v -> {
                    selected = holder.getAdapterPosition();
                    confirmBtn.setEnabled(true);
                    mAdapter.notifyDataSetChanged();
                });
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        };
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, getResources().getDimensionPixelSize(R.dimen.space_horizontal_small), false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
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
    protected boolean onKeyBackDown() {
        helper.sendAbort();
        return true;
    }

    @Override
    public void onClickProtected(View v) {
        if (v.getId() == R.id.confirm_btn) {
            helper.sendNext(selected);
        }
    }


    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        if (!TextUtils.isEmpty(message)) {
            prompt1 = message;
            tvPrompt.setText(prompt1);
        }
        setDemo(transMode);
    }

    @Override
    public void onShowOptions(@NonNull String[] options) {
        data = Arrays.asList(options);
        showOptions();
    }

    protected abstract String loadOtherParam();
}
