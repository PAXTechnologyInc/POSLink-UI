package com.pax.pay.ui.def;


import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.helper.SelectMerchantHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jolie.Yang on July.23.2020.
 */


public class SelectMerchantActivity extends BaseAppActivity implements SelectMerchantHelper.ISelectMerchantOptionListener {
    RecyclerView mRecyclerView;
    private int spanCount;
    private String interfaceStyle;

    private RecyclerView.Adapter<MerchantOptionModelViewHolder> mAdapter;
    private int selected = -1;
    private List<String> data = new ArrayList<>();

    private SelectMerchantHelper helper;

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_merchant;
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void loadParam() {
        mRecyclerView = findViewById(R.id.mm_option_select);
        spanCount = 2;
        CurrencyConverter.setDefCurrency(CurrencyType.USD);
        helper = new SelectMerchantHelper(this, new RespStatusImpl(this));
    }

    @Override
    protected void initViews() {

    }

    private void showOptions() {
        int windowWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        spanCount = windowWidth /320;
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount, RecyclerView.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerView.Adapter<MerchantOptionModelViewHolder>() {
            @NonNull
            @Override
            public MerchantOptionModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new MerchantOptionModelViewHolder(LayoutInflater.from(SelectMerchantActivity.this).inflate(R.layout.item_merchant_grid, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull MerchantOptionModelViewHolder holder, int position) {

                String viewData = data.get(position);

                if (viewData == null)
                    return;
                holder.itemView.setContentDescription(viewData);
                holder.itemView.setOnClickListener(v -> {
//                    mAdapter.notifyDataSetChanged();
                    onMerchantSelected(holder.getAdapterPosition());
                });
                holder.textView.setText(viewData);
//                if (InterfaceStyles.FULL_SCREEN.equals(interfaceStyle)) {
//                    holder.textView.setCompoundDrawablesWithIntrinsicBounds(null,
//                            getResources().getDrawable(R.mipmap.merchant_solo, null), null, null);
//                }
//
//                holder.textView.setPadding(0, 24, 0, 20);
//                holder.textView.setCompoundDrawablePadding(8);
                holder.textView.setSelected(false);

            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        };
//        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, getResources().getDimensionPixelSize(R.dimen.space_horizontal_smaller), false));
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

    }


    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    @Override
    public void onShowOptions(@NonNull String[] options) {
        data = Arrays.asList(options);
        showOptions();
    }

    @Override
    public void onShowInterfaceStyle(@NonNull String interfaceStyle) {
        this.interfaceStyle = interfaceStyle;
    }


    private void onMerchantSelected(int selection){
        if (selected < 0 && selection >= 0) {
            selected = selection;
            helper.sendNext(selected);
        }
    }
    public class MerchantOptionModelViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MerchantOptionModelViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.merchant_name);
        }
    }
}
