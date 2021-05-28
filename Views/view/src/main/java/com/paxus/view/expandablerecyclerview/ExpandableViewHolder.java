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

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public abstract class ExpandableViewHolder extends RecyclerView.ViewHolder {

    @Nullable
    public final View toggleView;
    @Nullable
    public final View expandView;

    /**
     * no expand view
     *
     * @param itemView target view which needs to be expandable, it's the toggle view as well
     */
    public ExpandableViewHolder(@NonNull View itemView) {
        super(itemView);
        this.toggleView = itemView;
        this.expandView = null;
    }

    /**
     * pre-define expand view layout in target view
     *
     * @param itemView     target view which needs to be expandable, it's the toggle view as well
     * @param expandViewId id of the expand view in target view
     */
    public ExpandableViewHolder(@NonNull View itemView, @IdRes int expandViewId) {
        super(itemView);
        this.toggleView = itemView;
        this.expandView = itemView.findViewById(expandViewId);
    }

    /**
     * pre-define expand view layout in target view
     *
     * @param itemView     target view which needs to be expandable
     * @param toggleId     id of the toggle view in target view
     * @param expandViewId id of the expand view in target view
     */
    public ExpandableViewHolder(@NonNull View itemView, @IdRes int toggleId, @IdRes int expandViewId) {
        super(itemView);
        this.toggleView = itemView.findViewById(toggleId);
        this.expandView = itemView.findViewById(expandViewId);
    }

    /**
     * inflate expand view from xml
     * @param base target view which needs to be expandable
     * @param toggleId id of the toggle view in target view
     * @param expandResource id of the expand view in target view
     */
    /*
    public ExpandableViewHolder(RecyclerView.ViewHolder base, @IdRes int toggleId, @LayoutRes int expandResource) {
        super(base.itemView);
        this.toggleView = Preconditions.checkNotNull(base.itemView.findViewById(toggleId));
        this.expandView = LayoutInflater.from(base.itemView.getContext()).inflate(expandResource, (ViewGroup)base.itemView);
        this.expandView.setVisibility(View.GONE);
        initView();
    }
    */
}
