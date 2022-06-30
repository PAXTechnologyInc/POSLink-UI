package com.pax.pay.ui.def.utils;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.pax.pay.ui.def.R;

/**
 * Created by Kim.L 2020/04/01
 */
public class OptionModelViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public OptionModelViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.mode_grid_tv);
    }
}
