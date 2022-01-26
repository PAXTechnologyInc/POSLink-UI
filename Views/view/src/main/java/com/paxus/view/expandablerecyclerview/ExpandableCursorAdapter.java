/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2020 - ? Pax Corporation. All rights reserved.
 * Module Date: 2020-3-11
 * Module Author: Kim.L
 * Description:
 * ============================================================================
 */
package com.paxus.view.expandablerecyclerview;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.RecyclerCursorAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public abstract class ExpandableCursorAdapter<VH extends ExpandableViewHolder> extends RecyclerCursorAdapter<VH> {

    private int mExpandedPosition = -1;

    public ExpandableCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    /**
     * 根据不同的类型绑定View
     */
    @Override
    public void onBindViewHolder(final @NonNull VH holder, int position) {
        onExpandViewHolder(holder);
        super.onBindViewHolder(holder, position);
    }

    @CallSuper
    public void onExpandViewHolder(final @NonNull VH holder) {
        final boolean isExpanded = holder.getAdapterPosition() == getExpandedPosition();
        if (holder.expandView != null) {
            holder.expandView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        }

        if (holder.toggleView != null) {
            holder.toggleView.setOnClickListener(v -> {
                int old = getExpandedPosition();
                if (old != -1)
                    notifyItemChanged(old);
                if (isExpanded) {
                    onCollapsedAll();
                }
                setExpandedPosition(isExpanded ? -1 : holder.getAdapterPosition());
                notifyItemChanged(holder.getAdapterPosition());
                if (old != holder.getAdapterPosition())
                    ((RecyclerView) holder.itemView.getParent()).scrollToPosition(holder.getAdapterPosition());
            });
        }
    }

    @Override
    public Cursor swapCursor(Cursor newCursor) {
        int oldCnt = getItemCount();
        if (newCursor != null) {
            int newCnt = newCursor.getCount();
            if (newCnt > oldCnt) {
                if(getExpandedPosition()>=0){
                    setExpandedPosition(getExpandedPosition() + newCnt - oldCnt);
                }else {
                    setExpandedPosition(-1);
                }
            } else if (newCnt < oldCnt) {
                setExpandedPosition(-1);
            }
        }
        return super.swapCursor(newCursor);
    }

    protected void onCollapsedAll() {

    }

    public void collapseAll() {
        if (getExpandedPosition() != -1)
            notifyItemChanged(getExpandedPosition());
        setExpandedPosition(-1);
        onCollapsedAll();
    }

    public int getExpandedPosition() {
        return mExpandedPosition;
    }

    public void setExpandedPosition(int mExpandedPosition) {
        this.mExpandedPosition = mExpandedPosition;
    }
}
