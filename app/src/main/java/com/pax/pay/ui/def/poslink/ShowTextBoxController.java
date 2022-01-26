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
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.poslink.print.PrintDataConverter;
import com.pax.pay.ui.def.poslink.print.PrintDataItem;
import com.pax.pay.ui.def.poslink.print.PrintDataItemContainer;

import java.util.ArrayList;
import java.util.List;

public class ShowTextBoxController {
    public static int line;//记录行数

    public static void showText(Context context, LinearLayout containerLayout, String data, LinearLayout.LayoutParams lp) {
        line = 0;
        List<PrintDataItem> newLineTextList = new ArrayList<>();
        if(lp == null) {
            lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        }

        if (TextUtils.isEmpty(data))
            data = "";

        data = data.replaceAll("\r", ""); //BPOSANDJAX-264
        try {
            PrintDataItemContainer printDataItemContainer = PrintDataConverter.parse(data, true);
            List<PrintDataItem> printDataItemList = printDataItemContainer.getPrintDataItems();

            for (int i = 0; i < printDataItemList.size(); i++) {
                PrintDataItem currDataItem = printDataItemList.get(i);
                PrintDataItem preDataItem = null;
                if (i >= 1) {
                    preDataItem = printDataItemList.get(i - 1);
                }
                if (currDataItem.getCmds().contains(PrintDataItem.LINE)
                        || currDataItem.getContent().contains("\\r")
                        || currDataItem.getCmds().contains(PrintDataItem.LINE_SEP)) {
                    if (newLineTextList.size() == 0) {
                        newLineTextList.add(currDataItem);//输出空白行
                    }
                    newLineTextList = TextController.filterItems(newLineTextList, PrintDataItem.LEFT_ALIGN);
                    showTextInOnLine(context, containerLayout, newLineTextList, lp);
                    newLineTextList.clear();
                } else if (currDataItem.getCmds().contains(PrintDataItem.BOLD)
                        || currDataItem.getCmds().contains(PrintDataItem.SMALL_FONT)
                        || currDataItem.getCmds().contains(PrintDataItem.NORMAL_FONT)
                        || currDataItem.getCmds().contains(PrintDataItem.BIG_FONT)) {
                    if (preDataItem != null) {
                        if (preDataItem.getCmds().contains(PrintDataItem.LINE)
                                || preDataItem.getContent().contains("\\r")
                                || preDataItem.getCmds().contains(PrintDataItem.LINE_SEP)) {
                            newLineTextList.add(currDataItem);
                        } else {
                            newLineTextList = TextController.filterItems(newLineTextList, PrintDataItem.LEFT_ALIGN);
                            showTextInOnLine(context, containerLayout, newLineTextList, lp);
                            newLineTextList.clear();
                            newLineTextList.add(currDataItem);
                        }
                    } else {
                        newLineTextList.add(currDataItem);
                    }
                } else {
                    newLineTextList.add(currDataItem);
                }
            }
            if (newLineTextList.size() > 0) {
                newLineTextList = TextController.filterItems(newLineTextList, PrintDataItem.LEFT_ALIGN);
                showTextInOnLine(context, containerLayout, newLineTextList, lp);
                newLineTextList.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Item doesn't contains '\n'、'\\n' or '\r'
    private static void showTextInOnLine(Context context, LinearLayout containerLayout, List<PrintDataItem> printDataItemList, LinearLayout.LayoutParams lp) {
        line++;
        printDataItemList = TextController.sortList(printDataItemList);
        LinearLayout oneLineLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        oneLineLayout.setLayoutParams(params);
        int textSize = PrintDataItem.FONT_NORMAL_PX;
        for (int i = 0; i < printDataItemList.size(); i++) {
            PrintDataItem item = printDataItemList.get(i);
            if (item.getCmds().contains(PrintDataItem.BIG_FONT)) {
                textSize = PrintDataItem.FONT_BIG_PX;
                break;
            } else if (item.getCmds().contains(PrintDataItem.SMALL_FONT)) {
                textSize = PrintDataItem.FONT_SMALL_PX;
                break;
            }
        }
        for (int i = 0; i < printDataItemList.size(); i++) {
            PrintDataItem item = printDataItemList.get(i);
            TextView textView = TextController.getTextView(context, item, lp);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            oneLineLayout.addView(textView, lp);
        }
        int h = oneLineLayout.getHeight();
        containerLayout.addView(oneLineLayout);
        printDataItemList.clear();
    }

//    private static void showFirstLineTexts(Context context, LinearLayout linearLayout, List<PrintDataItem> printDataItemList, LinearLayout.LayoutParams lp) {
//        printDataItemList = TextController.sortList(printDataItemList);
//        for (int i = 0; i < printDataItemList.size(); i++) {
//            TextView textView = TextController.getTextView(context, printDataItemList.get(i), lp);
//            textView.setTextColor(Color.BLACK);
//            linearLayout.addView(textView, lp);
//        }
//    }
}
