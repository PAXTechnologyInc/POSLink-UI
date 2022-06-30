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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.R;
import com.pax.pay.ui.def.poslink.info.MsgInfo;
import com.pax.pay.ui.def.poslink.info.MsgInfoWrapper;
import com.paxus.utils.StringUtils;

import java.util.List;

/**
 * Created by John.W on 2018/6/22.
 * Description:
 */

public class MsgListAdapter extends RecyclerView.Adapter<MsgListAdapter.MsgViewHolder> {

    private final LayoutInflater layoutInflater;

    private List<MsgInfoWrapper> msgList;

    private final Context context;

    private final LinearLayout.LayoutParams lp;

    @Override
    public int getItemCount() {
        if (msgList == null) {
            return 0;
        } else {
            return msgList.size();
        }
    }

    public MsgListAdapter(Context context, List<MsgInfoWrapper> msgList) {
        this.context = context;
        this.msgList = msgList;
        layoutInflater = LayoutInflater.from(context);
        lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
    }

    @Override
    public void onBindViewHolder(MsgViewHolder holder, int position) {
        MsgInfo msgInfo = msgList.get(position).getMsgInfo();
        holder.msgLayout.setPadding(0, 5, 0, 5);

        holder.msg1Layout.removeAllViews();
        holder.msg2Layout.removeAllViews();

        String msg1 = msgInfo.getMsg1();
        String msg2 = msgInfo.getMsg2();

        if (TextUtils.isEmpty(msg1) && TextUtils.isEmpty(msg2)) {
            setMsg1View(TextController.getViewList(context, " ", lp), holder.msg1Layout);
        } else {
            setMsg1View(TextController.getViewList(context, msg1 == null || msg1.length() == 0 ? " " : msg1,  lp), holder.msg1Layout);
            if (!StringUtils.isEmpty(msg2)) {
                setMsg1View(TextController.getViewList(context, msg2 == null || msg2.length() == 0 ? " " : msg2, lp), holder.msg2Layout);
            }
        }
    }

    @Override
    public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MsgViewHolder(layoutInflater.inflate(R.layout.item_layout_msg_list_pete, parent, false));
    }

    public void setMsgList(List<MsgInfoWrapper> msgList) {
        this.msgList = msgList;
    }

    public static class MsgViewHolder extends RecyclerView.ViewHolder {
        LinearLayout msg1Layout;
        LinearLayout msg2Layout;
        LinearLayout msgLayout;

        public MsgViewHolder(View view) {
            super(view);
            msg1Layout = (LinearLayout) view.findViewById(R.id.ll_msg_1);
            msg2Layout = (LinearLayout) view.findViewById(R.id.ll_msg_2);
            msgLayout = (LinearLayout) view.findViewById(R.id.ll_msg);
        }
    }

    private void setMsg1View(List<TextView> viewList, LinearLayout msgLayout) {
        for (TextView textView : viewList) {
            textView.setMaxLines(1);

            msgLayout.addView(textView, lp);
            msgLayout.setGravity(Gravity.LEFT);
        }
    }
}
