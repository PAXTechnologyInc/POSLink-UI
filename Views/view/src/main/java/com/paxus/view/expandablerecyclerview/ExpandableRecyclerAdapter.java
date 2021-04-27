/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2017 - ? Pax Corporation. All rights reserved.
 * Module Date: 2017-8-9
 * Module Author: Kim.L
 * Description:
 * ============================================================================
 */
package com.paxus.view.expandablerecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.paxus.view.R;

import java.util.ArrayList;
import java.util.List;


public abstract class ExpandableRecyclerAdapter<T> extends RecyclerView.Adapter<BaseExpandableViewHolder<T>> {

    private List<T> dataBeanList = new ArrayList<>();
    private int mExpandedPosition = -1;
    private boolean hasBg = true;

    public ExpandableRecyclerAdapter() {
    }

    public ExpandableRecyclerAdapter(List<T> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }

    @NonNull
    @Override
    public abstract BaseExpandableViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    /**
     * 根据不同的类型绑定View
     */
    @Override
    public void onBindViewHolder(final @NonNull BaseExpandableViewHolder<T> holder, int position) {
        T data = dataBeanList.get(position);

        if (data == null)
            return;

        final boolean isExpanded = holder.getAdapterPosition() == mExpandedPosition;
        if (holder.getToggleView() != null)
            holder.getToggleView().setBackground(holder.itemView.getResources().getDrawable(R.drawable.touch_bg, null));
        else if (hasBg) {
            holder.itemView.setBackground(holder.itemView.getResources().getDrawable(R.drawable.touch_bg, null));
        }

        if (holder.getExpandView() != null) {
            holder.getExpandView().setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        }
        holder.bindBaseView(data, position, (viewHolder) -> {
            int old = mExpandedPosition;
            if (mExpandedPosition != -1)
                notifyItemChanged(mExpandedPosition);
            mExpandedPosition = isExpanded ? -1 : viewHolder.getAdapterPosition();
            notifyItemChanged(viewHolder.getAdapterPosition());
            if (old != viewHolder.getAdapterPosition())
                ((RecyclerView) viewHolder.itemView.getParent()).scrollToPosition(viewHolder.getAdapterPosition());
        });
    }

    public void collapseAll() {
        if (mExpandedPosition != -1)
            notifyItemChanged(mExpandedPosition);
        mExpandedPosition = -1;
    }

    @Override
    public int getItemCount() {
        return getDataBeanList().size();
    }

    public List<T> getDataBeanList() {
        if (dataBeanList == null) {
            dataBeanList = new ArrayList<>();
        }
        return dataBeanList;
    }

    public ExpandableRecyclerAdapter<T> setDataBeanList(List<T> dataBeanList) {
        if (dataBeanList == null) {
            return this;
        }
        this.dataBeanList = dataBeanList;
        return this;
    }

    public ExpandableRecyclerAdapter<T> addDataBeanList(List<T> dataBeanList) {
        if (dataBeanList == null) {
            return this;
        }
        getDataBeanList().addAll(dataBeanList);
        return this;
    }

    public boolean isHasBg() {
        return hasBg;
    }

    public void setHasBg(boolean hasBg) {
        this.hasBg = hasBg;
    }
}
