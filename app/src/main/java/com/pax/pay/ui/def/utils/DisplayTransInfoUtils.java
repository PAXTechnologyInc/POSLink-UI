package com.pax.pay.ui.def.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.R;
import com.paxus.view.utils.ViewUtils;

import java.util.ArrayList;
import java.util.HashMap;


public class DisplayTransInfoUtils {
    public enum ShowType {
        FEE,
        BANLANCE,
        TRANS,
        EXPAND
    }

    private final ArrayList<HashMap<String, String>> columns = new ArrayList<>();

    public DisplayTransInfoUtils(ViewGroup viewGroup, String[] key, String[] value, ShowType showType) {
        DisplayInfoContent.init_trans_map();
        switch (showType) {
//            case FEE:
//                setFeeColumn(viewGroup, key, value);
//                break;
//            case BANLANCE:
//                setBalanceColumn(viewGroup, key, value);
//                break;
            case TRANS:
                setTransColumn(viewGroup, key, value);
                break;
            case EXPAND:
                setExpandColumn(viewGroup, key, value);
                break;
        }
    }

//    void setFeeColumn(ViewGroup viewGroup, String[] key, String[] value) {
//        String leftColum = "", rightColum = "";
//        for (int i = 0; i < key.length; i++) {
//            if (!TextUtils.isEmpty(key[i])) {
//                if (DisplayInfoContent.FEE_MAP.get(key[i]) != null) {
//                    leftColum = DisplayInfoContent.FEE_MAP.get(key[i]);
//                } else
//                    leftColum = key[i];
//
//            } else {
//                leftColum = "";
//            }
//            if (!TextUtils.isEmpty(value[i]))
//                rightColum = value[i];
//            else
//                rightColum = "";
//
//            addColumns(viewGroup, leftColum, rightColum, null);
//        }
//    }
//
//    void setBalanceColumn(ViewGroup viewGroup, String[] key, String[] value) {
//        String leftColum = "", rightColum = "";
//        for (int i = 0; i < key.length; i++) {
//            if (!TextUtils.isEmpty(key[i])) {
//                if (DisplayInfoContent.BALANCE_MAP.get(key[i]) != null) {
//                    leftColum = DisplayInfoContent.BALANCE_MAP.get(key[i]);
//                } else
//                    leftColum = key[i];
//
//            } else {
//                leftColum = "";
//            }
//            if (!TextUtils.isEmpty(value[i]))
//                rightColum = value[i];
//            else
//                rightColum = "";
//
//            addColumns(viewGroup, leftColum, rightColum, null);
//        }
//    }

    void setTransColumn(ViewGroup viewGroup, String[] key, String[] value) {
        String leftColum = "", rightColum = "";
        for (int i = 0; i < key.length; i++) {
            if (!TextUtils.isEmpty(key[i])) {
                if (DisplayInfoContent.EXPAND_TRANS_MAP.containsKey(key[i])){
                    leftColum = viewGroup.getContext().getResources().getString(DisplayInfoContent.EXPAND_TRANS_MAP.get(key[i]));
                }else
                    leftColum = key[i].trim();

            } else {
                leftColum = "";
            }
            if (!TextUtils.isEmpty(value[i]))
                rightColum = value[i];
            else
                rightColum = "";

            addColumns(viewGroup, leftColum, rightColum, null);
        }
    }

    void setExpandColumn(ViewGroup viewGroup, String[] key, String[] value) {
        String leftColum = "", rightColum = "";

        if (key == null || key.length == 0 || value == null || value.length == 0)
            return;
        for (int i = 0; i < key.length; i++) {
            if (!TextUtils.isEmpty(key[i])) {
                if (DisplayInfoContent.EXPAND_TRANS_MAP.containsKey(key[i])) {
                    leftColum = viewGroup.getContext().getResources().getString(DisplayInfoContent.EXPAND_TRANS_MAP.get(key[i]));
                } else {
                    leftColum = key[i];
                }
                if (!TextUtils.isEmpty(value[i]))
                    rightColum = value[i];
                else
                    rightColum = "";
                addColumns(viewGroup, leftColum, rightColum, null);
            }
        }

//        for (Map.Entry<String, String> map : DisplayInfoContent.EXPAND_TRANS_MAP.entrySet()) {
//            for (int i = 0; i < key.length; i++) {
//                if (key[i].trim().toLowerCase().equals(map.getKey().toLowerCase())) {
//                    leftColum = map.getValue();
//                    if (!TextUtils.isEmpty(value[i]))
//                        rightColum = value[i];
//                    else
//                        rightColum = "";
//
//                    addColumns(viewGroup, leftColum, rightColum, null);
//                }
//            }
//        }
    }

    private void addColumns(ViewGroup viewGroup, String leftValue, String rightValue, String rightValueColor) {
        if (!TextUtils.isEmpty(rightValue)) {
            Context context = viewGroup.getContext();
            HashMap<String, String> column = new HashMap<>();
            column.put(context.getString(R.string.column_left), leftValue);
            column.put(context.getString(R.string.column_right), rightValue);
            if (rightValueColor == null) {
                column.put(context.getString(R.string.column_color), context.getString(R.string.color_default));
            } else {
                column.put(context.getString(R.string.column_color), rightValueColor);
            }
            columns.add(column);
        }
    }

    public void addViewsToViewGroup(ViewGroup viewGroup) {
        if (columns == null || columns.size() == 0)
            return;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 15;
        Context context = viewGroup.getContext();
        for (HashMap<String, String> value : columns) {
            String leftColum = value.get(context.getString(R.string.column_left));
            String rightColum = value.get(context.getString(R.string.column_right));
            String color = value.get(viewGroup.getResources().getString(R.string.column_color));

            View view = genSingleLineLayout(viewGroup, leftColum, rightColum, color);
            viewGroup.addView(view, params);
        }
    }

    public void addEmptyViewsToViewGroup(ViewGroup viewGroup, int iMaxHigh) {
        int defaultRows;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 15;
        Context context = viewGroup.getContext();
        if (context != null) {
            View tmpView = LayoutInflater.from(context).inflate(R.layout.layout_keyvalue, null);
            TextView valueTv = tmpView.findViewById(R.id.item_value);
            valueTv.setText("AAA");
            int textHigh = ViewUtils.sp2px(context, valueTv.getTextSize());
            defaultRows = (int) (iMaxHigh / (textHigh * 1.8));
            if (columns.size() < defaultRows) {
                for (int i = 0; i < defaultRows - columns.size(); i++) {
                    View view = genSingleLineLayout(viewGroup, "", "", context.getString(R.string.color_default));
                    viewGroup.addView(view, params);
                }
            }
        }
    }



    /**
     * 生成每一行记录
     */
    private static View genSingleLineLayout(ViewGroup viewGroup, String title, String value, String valueColor) {
        Context context = viewGroup.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.layout_keyvalue, null);

        TextView titleTv = view.findViewById(R.id.item_title);
        titleTv.setText(title);

        TextView valueTv = view.findViewById(R.id.item_value);
        valueTv.setText(value);

        if (valueColor.equals(context.getString(R.string.color_blue))) {
            valueTv.setTextColor(context.getResources().getColor(R.color.primary));
        } else if (valueColor.equals(context.getString(R.string.color_red))) {
            valueTv.setTextColor(context.getResources().getColor(R.color.accent));
        } else if (valueColor.equals(context.getString(R.string.color_default))) {
            valueTv.setTextColor(context.getResources().getColor(R.color.primary_text_light));
        }
        return view;
    }


}
