package com.pax.pay.ui.def.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZ on 8/18/2020
 */
public class TextController {

    public static List<TextView> getViewList(Context context, String data, LinearLayout.LayoutParams layoutParams) {
        List<TextView> textViewList = new ArrayList<>();
        TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.ui_text_size_18));
        textView.setText(data);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewList.add(textView);

        return textViewList;
    }
}
