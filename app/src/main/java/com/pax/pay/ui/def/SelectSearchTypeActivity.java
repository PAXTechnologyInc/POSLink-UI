package com.pax.pay.ui.def;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.constant.entry.enumeration.SearchCriteria;
import com.pax.us.pay.ui.core.helper.SelectOptionsHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Charles.S on 2017/5/5.
 */

public class SelectSearchTypeActivity extends BaseAppActivity implements SelectOptionsHelper.ISelectOptionListener {

    private static final Map<String, Integer> TRANS_TYPE_ICON_MAP = new HashMap<>();
    private static final Map<String, Integer> TRANS_TYPE_MAP = new HashMap<>();

    static {
        TRANS_TYPE_ICON_MAP.put(SearchCriteria.BY_INVOICE_NUMBER, R.mipmap.by_invoice);
        TRANS_TYPE_ICON_MAP.put(SearchCriteria.BY_TRANS_NUMBER, R.mipmap.by_transnum);
        TRANS_TYPE_ICON_MAP.put(SearchCriteria.BY_REFERENCE_NUMBER, R.mipmap.by_transnum);
        TRANS_TYPE_ICON_MAP.put(SearchCriteria.BY_CARD_TYPE, R.mipmap.by_cardtype);
        TRANS_TYPE_ICON_MAP.put(SearchCriteria.BY_LAST_4_DIGITS, R.mipmap.by_cardnum);
        TRANS_TYPE_ICON_MAP.put(SearchCriteria.BY_CLERK_SERVER_ID, R.mipmap.by_operator);
        TRANS_TYPE_ICON_MAP.put(SearchCriteria.BY_UNTIPPED, R.mipmap.tip_menu);
        TRANS_TYPE_ICON_MAP.put(SearchCriteria.BY_STORE_FORWARD, R.mipmap.store_sorward);
        TRANS_TYPE_ICON_MAP.put(SearchCriteria.BY_UPLOAD_TRANS, R.mipmap.upload);
        TRANS_TYPE_ICON_MAP.put(SearchCriteria.BY_RESENT_FAILED, R.mipmap.resent_failed);
    }

    static {
        TRANS_TYPE_MAP.put(SearchCriteria.BY_TRANS_NUMBER, R.string.by_trans);
        TRANS_TYPE_MAP.put(SearchCriteria.BY_CARD_TYPE, R.string.by_cardtype);
        TRANS_TYPE_MAP.put(SearchCriteria.BY_LAST_4_DIGITS, R.string.by_cardnum);
        TRANS_TYPE_MAP.put(SearchCriteria.BY_UNTIPPED, R.string.scroll_untipped);
        TRANS_TYPE_MAP.put(SearchCriteria.BY_INVOICE_NUMBER, R.string.by_invoice);
        TRANS_TYPE_MAP.put(SearchCriteria.BY_CLERK_SERVER_ID, R.string.by_server);
        TRANS_TYPE_MAP.put(SearchCriteria.BY_STORE_FORWARD, R.string.store_forward);
        TRANS_TYPE_MAP.put(SearchCriteria.BY_UPLOAD_TRANS, R.string.upload_trans);
        TRANS_TYPE_MAP.put(SearchCriteria.BY_RESENT_FAILED, R.string.resent_failed);
        TRANS_TYPE_MAP.put(SearchCriteria.BY_REFERENCE_NUMBER, R.string.by_referencenum);
    }

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
        return R.layout.activity_select_search_criteria;
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
        prompt1 = getResources().getString(R.string.select_search_type);
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
        tvPrompt.setVisibility(View.GONE);
        confirmBtn.setVisibility(View.GONE);
        confirmBtn.setEnabled(false);
    }

    private void showOptions() {

        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount, RecyclerView.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerView.Adapter<OptionModelViewHolder>() {
            @NonNull
            @Override
            public OptionModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new OptionModelViewHolder(LayoutInflater.from(SelectSearchTypeActivity.this).inflate(R.layout.item_mode_grid, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull OptionModelViewHolder holder, int position) {

                String viewData = data.get(position);

                if (viewData == null)
                    return;

                holder.itemView.setContentDescription(viewData);
                if (TRANS_TYPE_MAP.containsKey(viewData)) {
                    String text = getResources().getString(TRANS_TYPE_MAP.get(viewData));
                    holder.textView.setText(text);
                } else {
                    holder.textView.setText(viewData);
                }

                Integer resId = TRANS_TYPE_ICON_MAP.get(viewData);
                holder.textView.setCompoundDrawablesWithIntrinsicBounds(null,
                        ResourcesCompat.getDrawable(getResources(),
                                resId != null ? resId : R.mipmap.by_operator, null),
                        null, null);

                holder.textView.setCompoundDrawablePadding(10);
                holder.textView.setSelected(selected == position);
                holder.textView.setOnClickListener(v -> {
                    selected = holder.getAdapterPosition();
                    helper.sendNext(selected);
                    //confirmBtn.setEnabled(true);
                    mAdapter.notifyDataSetChanged();
                });
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        };
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, 0, false));
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
}
