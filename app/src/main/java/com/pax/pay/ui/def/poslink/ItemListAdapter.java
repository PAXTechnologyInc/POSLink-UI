/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2018-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.
 *
 * Module Date: 2019/7/30
 * Module Auth: Frank.W
 * Description:
 *
 * Revision History:
 * Date                   Author                       Action
 * 2019/7/30              Frank.W                       Create
 * ============================================================================
 */

package com.pax.pay.ui.def.poslink;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.R;
import com.pax.pay.ui.def.poslink.info.ItemDetail;
import com.pax.pay.ui.def.poslink.info.ItemDetailWrapper;
import com.paxus.utils.log.Logger;

import java.util.List;
import java.util.Locale;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>{

    private final List<ItemDetailWrapper> itemDetailList;

    private final LayoutInflater layoutInflater;
    private final String currencySymbol;
    public ItemListAdapter(Context context, List<ItemDetailWrapper> itemDetailList, String currencySymbol) {
        layoutInflater = LayoutInflater.from(context);
        this.itemDetailList = itemDetailList;
        this.currencySymbol = currencySymbol;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_show_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ItemDetail itemDetail = itemDetailList.get(position).getItemDetail();
        Logger.d("itemDetail : "+itemDetail.toString());
        String quantityDesc;
        if ("x".equals(itemDetail.getUnit())) {
            quantityDesc = itemDetail.getUnit() + itemDetail.getQuantity();

        }else {
            quantityDesc = itemDetail.getQuantity() + itemDetail.getUnit();
        }

        switch (itemDetail.getUnit()) {
            case "x" :
                quantityDesc = itemDetail.getUnit() + itemDetail.getQuantity();
                break;

            case "1":
                quantityDesc = itemDetail.getQuantity() + "1b";
                break;

            case "2":
                quantityDesc = itemDetail.getQuantity() + "ft";
                break;
        }
        holder.tvProductName.setText((position + 1) + "." + itemDetail.getProductName());

        RelativeLayout.LayoutParams productNameLps = (RelativeLayout.LayoutParams)holder.tvProductName.getLayoutParams();
        if (!TextUtils.isEmpty(itemDetail.getQuantity())) {
            if (!"x".equals(itemDetail.getUnit())) {
                holder.tvQuantityUnit.setText(quantityDesc + "  @"+ currencySymbol + String.format("%.2f", itemDetail.getUnitPrice()) + "/" + itemDetail.getUnit());
            }else {
                holder.tvQuantityUnit.setText(quantityDesc + "  @"+ currencySymbol + String.format("%.2f", itemDetail.getUnitPrice()));
            }
            productNameLps.removeRule(RelativeLayout.CENTER_VERTICAL);
            holder.tvProductName.setLayoutParams(productNameLps);
        } else {
            productNameLps.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            holder.tvProductName.setLayoutParams(productNameLps);
        }

        holder.tvTotalPrice.setText(String.format(Locale.ENGLISH,"%1$s%2$.2f", currencySymbol, itemDetail.getPrice()));
    }

    @Override
    public int getItemCount() {
        if (itemDetailList == null) {
            return 0;
        } else {
            return itemDetailList.size();
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductName;
        TextView tvQuantityUnit;
        TextView tvTotalPrice;

        public ItemViewHolder(View view) {
            super(view);
            tvProductName = view.findViewById(R.id.tv_product_name_show_item);
            tvQuantityUnit = view.findViewById(R.id.tv_quantity_unit_price);
            tvTotalPrice = view.findViewById(R.id.tv_total_price_show_item);
        }
    }
}
