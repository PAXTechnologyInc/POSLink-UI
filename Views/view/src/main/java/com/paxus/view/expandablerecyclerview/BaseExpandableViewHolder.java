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

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.paxus.view.R;

public abstract class BaseExpandableViewHolder<T> extends RecyclerView.ViewHolder {

    private final @IdRes
    int toggleId;
    private final @IdRes
    int expandViewId;
    private final @IdRes
    int loadingExpandId;
    private T dataBean;

    public BaseExpandableViewHolder(View itemView) {
        this(itemView, R.id.expandable_toggle_button, R.id.expandable, R.id.loading_expand);
    }

    public BaseExpandableViewHolder(View itemView, @IdRes int toggleId, @IdRes int expandViewId, @IdRes int loadingExpandId) {
        super(itemView);
        this.toggleId = toggleId;
        this.expandViewId = expandViewId;
        this.loadingExpandId = loadingExpandId;
        initView();
        setListener();
    }

    protected abstract void initView();

    protected abstract void setListener();

    public View getToggleView() {
        return itemView == null ? null : itemView.findViewById(toggleId);
    }

    public View getExpandView() {
        return itemView == null ? null : itemView.findViewById(expandViewId);
    }

    public View getLoadingView() {
        return itemView == null ? null : itemView.findViewById(loadingExpandId);
    }

    final void bindBaseView(final T dataBean, final int pos, final ItemClickListener listener) {
        this.dataBean = dataBean;
        bindView(itemView, dataBean, pos);
        View toggleView = getToggleView();
        if (toggleView != null) {
            updateToggleView(toggleView, dataBean, pos);
            toggleView.setOnClickListener(v -> {
                View loading = getLoadingView();
                View expand = getExpandView();
                if (loading != null && expand != null && expand.getVisibility() != View.VISIBLE) {
                    loading.setVisibility(View.VISIBLE);
                }
                if (listener != null) {
                    listener.onExpand(BaseExpandableViewHolder.this);
                }
            });
        } else {
            itemView.setClickable(true);
        }
        View loading = getLoadingView();
        View expand = getExpandView();
        if (expand != null && expand.getVisibility() == View.VISIBLE) {
            updateExpandView(expand, dataBean, pos);
        }

        if (loading != null) {
            loading.setVisibility(View.GONE);
        }
    }

    protected void bindView(View view, final T dataBean, final int pos) {

    }

    protected void updateToggleView(View view, final T dataBean, final int pos) {

    }

    protected void updateExpandView(View view, final T dataBean, final int pos) {

    }

    public T getDataBean() {
        return dataBean;
    }

    public interface ItemClickListener {
        void onExpand(BaseExpandableViewHolder viewHolder);
    }
}
