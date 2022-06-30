package com.pax.pay.ui.def.poslink;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.pax.pay.ui.def.R;
import com.pax.pay.ui.def.poslink.print.PrintDataConverter;
import com.pax.pay.ui.def.poslink.print.PrintDataItem;
import com.pax.pay.ui.def.poslink.print.PrintDataItemContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ZZ on 8/18/2020
 */
public class TextController {
//    public static List<TextView> getViewList(Context context, String data, LinearLayout.LayoutParams layoutParams) {
//        List<TextView> textViewList = new ArrayList<>();
//        TextView textView = new TextView(context);
//        textView.setLayoutParams(layoutParams);
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.ui_text_size_18));
//        textView.setText(data);
//        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//        textViewList.add(textView);
//
//        return textViewList;
//    }

//    public static List<TextView> getViewList(Context context, String data, int gravity, LinearLayout.LayoutParams layoutParams) {
//        List<TextView> textViewList = new ArrayList<>();
//        TextView textView = new TextView(context);
//        textView.setLayoutParams(layoutParams);
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.ui_text_size_18));
//        textView.setText(data);
//        textView.setGravity(gravity);
//        //textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//        textViewList.add(textView);
//
//        return textViewList;
//    }

    public static List<TextView> getViewList(Context context, String data, LinearLayout.LayoutParams layoutParams) {
        if (TextUtils.isEmpty(data))
            data = "";

        List<TextView> textViewList = new ArrayList<>();

        try {
            PrintDataItemContainer printDataItemContainer = PrintDataConverter.parse(data, false);

            List<PrintDataItem> printDataItemList = TextController.filterItems(printDataItemContainer.getPrintDataItems(), PrintDataItem.LEFT_ALIGN);

            for (int i = 0; i < printDataItemList.size(); i++) {

                //If item contain' cmds contain "\\n",filtered.
                if (!printDataItemList.get(i).getCmds().contains(PrintDataItem.LINE_SEP)) {
                    TextView textView = TextController.getTextView(context, printDataItemList.get(i), layoutParams);
                    textView.setTextColor(Color.BLACK);
                    textViewList.add(textView);
                    Typeface typeface = ResourcesCompat.getFont(context, R.font.fsxe300);
                    textView.setTypeface(typeface);
//                    textView.setEllipsize(TextUtils.TruncateAt.END);
                    textView.setSingleLine();
                }

            }

        } catch (PrintDataConverter.BarcodeParser.BarcodeFormatException e) {

            e.printStackTrace();
        }
        return textViewList;
    }


    public static TextView getTextView(Context context, PrintDataItem printDataItem, LinearLayout.LayoutParams layoutParams) {
        TextView textView = new TextView(context);
        List<String> cmds = printDataItem.getCmds();
        for (String cmd : cmds) {
            switch (cmd) {
                case PrintDataItem.LEFT_ALIGN:
                    layoutParams.gravity = Gravity.LEFT;
                    textView.setGravity(Gravity.LEFT);
                    break;
                case PrintDataItem.RIGHT_ALIGN:
                    layoutParams.gravity = Gravity.RIGHT;
                    textView.setGravity(Gravity.RIGHT);
                    break;

                case PrintDataItem.CENTER_ALIGN:
                    layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    break;

                case PrintDataItem.BOLD:
                    TextPaint textPaint = textView.getPaint();
                    textPaint.setFakeBoldText(true);
                    break;

            }
        }

        textView.setLayoutParams(layoutParams);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.ui_text_size_18));
        textView.setText(printDataItem.getContent());
        return textView;
    }



    //some item's content(not cmd) only contains "\r", "\n", filter them
    public static List<PrintDataItem> filterItems(List<PrintDataItem> printDataItems, String defaultAlign) {
        List<PrintDataItem> tempList = new ArrayList<>();

        if (printDataItems != null && printDataItems.size() > 0) {
            PrintDataItem firstItem = printDataItems.get(0);
            if (!containsAlign(firstItem)) {
                firstItem.getCmds().add(defaultAlign);
            }
            PrintDataItem leftItem = null, rightItem = null, centerItem = null, boldItem = null;
            Iterator<PrintDataItem> iterator = printDataItems.iterator();

            //Confirm left, center, right item.
            while (iterator.hasNext()) {
                PrintDataItem printDataItem = iterator.next();
                if (printDataItem.getCmds().contains(PrintDataItem.LEFT_ALIGN)) {
                    leftItem = printDataItem;
                } else if (printDataItem.getCmds().contains(PrintDataItem.RIGHT_ALIGN)) {
                    rightItem = printDataItem;
                } else if (printDataItem.getCmds().contains(PrintDataItem.CENTER_ALIGN)) {
                    centerItem = printDataItem;
                } else if (printDataItem.getCmds().contains(PrintDataItem.BOLD)) {
                    boldItem = printDataItem;
                } else {
                    tempList.add(printDataItem);
                }
            }

            if (leftItem != null) {
                tempList.add(leftItem);
            }

            if (boldItem != null) {
                tempList.add(boldItem);
            }
            if (rightItem != null) {
                tempList.add(rightItem);
            }

            if (centerItem != null) {
                tempList.add(centerItem);
            }
        }
        //After confirming left, center, right item, sorted.
        return sortList(tempList);
    }

    private static boolean containsAlign(PrintDataItem printDataItem) {
        return printDataItem.getCmds().contains(PrintDataItem.LEFT_ALIGN)
                || printDataItem.getCmds().contains(PrintDataItem.RIGHT_ALIGN)
                || printDataItem.getCmds().contains(PrintDataItem.CENTER_ALIGN);
    }

    //Sort items to make them on right position,such as: on center position
    public static List<PrintDataItem> sortList(List<PrintDataItem> list) {
        int leftCount = 0;
        int rightCount = 0;
        int centerCount = 0;

        List<PrintDataItem> tempList = new ArrayList<>();
        PrintDataItem leftItem = null, rightItem = null, centerItem = null;

        for (int i = 0; i < list.size(); i++) {
            PrintDataItem printDataItem = list.get(i);
            List<String> cmds = printDataItem.getCmds();

            //left
            if (cmds.contains(PrintDataItem.LEFT_ALIGN) && leftCount == 0) {
                leftCount++;
                leftItem = printDataItem;
            } else if (cmds.contains(PrintDataItem.RIGHT_ALIGN) && rightCount == 0) {
                rightCount++;
                rightItem = printDataItem;
            } else if (cmds.contains(PrintDataItem.CENTER_ALIGN) && centerCount == 0) {
                centerCount++;
                centerItem = printDataItem;
            } else {
                tempList.add(printDataItem);
            }
        }

        if (leftItem != null) {
            tempList.add(0, leftItem);
        }

        if (rightItem != null) {
            tempList.add(tempList.size(), rightItem);
        }

        if (centerItem != null) {

            if (tempList.size() > 2) {
                tempList.add(tempList.size() / 2 + 1, centerItem);
            } else if (tempList.size() == 2) {
                tempList.add(tempList.size() / 2, centerItem);
            } else if (tempList.size() == 1) {
                if (leftCount != 0) {
                    tempList.add(1, centerItem);
                } else if (rightCount != 0) {
                    tempList.add(0, centerItem);
                } else {
                    tempList.add(centerItem);
                }
            } else {
                tempList.add(centerItem);
            }
        }

        if (tempList.size() == 2) {
            boolean containCenter = false, addLeft = false, addRight = false, addBold = false;

            for (PrintDataItem printDataItem : tempList) {
                if (printDataItem.getCmds().contains(PrintDataItem.CENTER_ALIGN)) {
                    containCenter = true;
                } else if (printDataItem.getCmds().contains(PrintDataItem.LEFT_ALIGN)) {
                    addRight = true;
                } else if (printDataItem.getCmds().contains(PrintDataItem.RIGHT_ALIGN)) {
                    addLeft = true;
                } else if (printDataItem.getCmds().contains(PrintDataItem.BOLD)) {
                    addBold = true;
                }
            }

            if (containCenter) {
                if (addLeft) {
                    tempList.add(0, new PrintDataItem(" ", Arrays.asList(PrintDataItem.LEFT_ALIGN)));
                }

                if (addRight || addBold) {
                    tempList.add(2, new PrintDataItem(" ", Arrays.asList(PrintDataItem.RIGHT_ALIGN)));
                }

            }

        }
        return tempList;
    }

    //title is special, it is only one line
    public static List<TextView> getTitleViewList(Context context, String data, LinearLayout.LayoutParams layoutParams) {
        if (TextUtils.isEmpty(data))
            data = "";

        List<TextView> textViewList = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<String, List<String>>() {
            {
//                put(PrintDataConverter.DESCRIPTION_CONTROLLER_LIST, PrintDataItem.DESCRIPTION_CONTROLLER_LIST);
//                put(PrintDataConverter.SINGLE_ONLY_CONTROLLER_LIST, PrintDataItem.SINGLE_ONLY_CONTROLLER_LIST_ONLINE);
                put(PrintDataConverter.TEXT_SHOWING_LIST, PrintDataItem.TEXT_SHOWING_LIST);
            }
        };

        try {
            PrintDataItemContainer printDataItemContainer = PrintDataConverter.parse(data, map, false);

            List<PrintDataItem> printDataItemList = TextController.filterItems(printDataItemContainer.getPrintDataItems(), PrintDataItem.CENTER_ALIGN);
            //If just one item for title, default make it on center
            if (printDataItemList.size() == 1) {
                PrintDataItem printDataItem = printDataItemList.get(0);
                if (printDataItem.getCmds().size() == 0 || (printDataItem.getCmds().size() == 1
                        && printDataItem.getCmds().get(0).equals(PrintDataItem.BOLD))) {
                    printDataItem.getCmds().add(PrintDataItem.CENTER_ALIGN);

                }

                TextView textView = TextController.getTextView(context, printDataItem, layoutParams);
//                textView.setTextColor(Color.BLACK);
                textViewList.add(textView);
                textView.setSingleLine();
            } else {
                for (int i = 0; i < printDataItemList.size(); i++) {

                    PrintDataItem printDataItem = printDataItemList.get(i);
                    TextView textView = null;

                    //not support "\n", as a common char.
                    if (!printDataItem.getCmds().contains(PrintDataItem.LINE_SEP)) {
                        textView = TextController.getTextView(context, printDataItem, layoutParams);
                    } else if (!TextUtils.isEmpty(printDataItem.getContent())) {
                        printDataItem.setContent("\\n" + printDataItem.getContent());
                        textView = TextController.getTextView(context, printDataItem, layoutParams);
                    }

                    if (textView != null) {
//                        textView.setTextColor(Color.BLACK);
                        textView.setSingleLine();
                        textViewList.add(textView);
                    }
                }
            }

        } catch (PrintDataConverter.BarcodeParser.BarcodeFormatException e) {
            e.printStackTrace();
        }
        return textViewList;
    }


}
