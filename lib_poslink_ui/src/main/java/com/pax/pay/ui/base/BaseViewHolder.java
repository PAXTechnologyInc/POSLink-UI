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
package com.pax.pay.ui.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        initView();
    }

    protected abstract void initView();

    public final void bindBaseView(final T dataBean, final int pos) {
        bindView(itemView, dataBean, pos);
        itemView.setClickable(true);
    }

    protected void bindView(View view, final T dataBean, final int pos) {

    }
}
