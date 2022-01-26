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

package com.pax.pay.ui.def.poslink.print;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is used to convert printData string to bitmap.
 */
public class PrintDataConverter {
//    public static final String SINGLE_ONLY_CONTROLLER_LIST = "SINGLE_ONLY_CONTROLLER_LIST";
//    public static final String DESCRIPTION_CONTROLLER_LIST = "DESCRIPTION_CONTROLLER_LIST";
//    public static final String CONTENT_CONTROLLER_LIST = "CONTENT_CONTROLLER_LIST";
//    public static final String BARCODE_LIST = "BARCODE_LIST";
    public static final String TEXT_SHOWING_LIST = "TEXT_SHOWING_LIST";

    /**
     * Parse the raw printData string to PrintDataItems.
     *
     * @param printData
     * @return
     */
    public static PrintDataItemContainer parse(final String printData, boolean supportLineSep) throws BarcodeParser.BarcodeFormatException {

        Map<String, List<String>> defaultList = new HashMap<String, List<String>>() {
            {
//                put(SINGLE_ONLY_CONTROLLER_LIST, PrintDataItem.SINGLE_ONLY_CONTROLLER_LIST);
//                put(DESCRIPTION_CONTROLLER_LIST, PrintDataItem.DESCRIPTION_CONTROLLER_LIST);
//                put(CONTENT_CONTROLLER_LIST, PrintDataItem.CONTENT_CONTROLLER_LIST);
//                put(BARCODE_LIST, PrintDataItem.BARCODE_LIST);
                put(PrintDataConverter.TEXT_SHOWING_LIST, PrintDataItem.TEXT_SHOWING_LIST);
            }
        };
        return parse(printData, defaultList, supportLineSep);
    }

    public static PrintDataItemContainer parse(final String printData, Map<String, List<String>> filterList, boolean supportLineSep) throws BarcodeParser.BarcodeFormatException {

        List<PrintDataItem> printDataItemList = new ArrayList<>(32);
        StringBuilder recognizer = new StringBuilder();
        StringBuilder contentBuilder = new StringBuilder();
        List<String> cmds = new ArrayList<>();
        BarcodeParser barcodeParser = new BarcodeParser();
        for (int index = 0; index < printData.length(); index++) {
            char curChar = printData.charAt(index);
            if (curChar == '\\') {
                // barcode parser should stop if it has starts
                endBarcodeParserWhenNeed(printDataItemList, contentBuilder, cmds, barcodeParser);
                // recognizer get \ but it is not a command --> it should be content
                if (recognizer.length() > 0) {
                    contentBuilder.append(recognizer.toString());
                    recognizer.delete(0, recognizer.length());
                }
                recognizer.append(curChar);
            } else if (curChar == '\n') {
                handleParseLineSep(printDataItemList, recognizer, contentBuilder, cmds, barcodeParser);
            } else if (supportLineSep && curChar == 'n' && recognizer.length() > 0
                    && recognizer.charAt(recognizer.length() - 1) == '\\') {
                recognizer.delete(recognizer.length() - 1, recognizer.length());
                handleParseLineSep(printDataItemList, recognizer, contentBuilder, cmds, barcodeParser);
            } else if (barcodeParser.isStart()) {
                if (barcodeParser.next(curChar)) {
                    contentBuilder.append(barcodeParser.end());
                    addNewPrintItemWhenHaveContent(printDataItemList, contentBuilder, cmds);
                }
                continue;
            } else {
                if (recognizer.length() > 0) {
                    // Start recognize
                    recognizer.append(curChar);
                } else {
                    contentBuilder.append(curChar);
                }
            }

            String recognizerStr = recognizer.toString();
            for (String key : filterList.keySet()) {
                if (filterList.get(key).contains(recognizerStr)) {
                    switch (key) {
//                        case SINGLE_ONLY_CONTROLLER_LIST:
//                            addNewPrintItemWhenHaveContent(printDataItemList, contentBuilder, cmds);
//                            addPrintItmWithOneCmd(printDataItemList, contentBuilder, recognizerStr);
//                            recognizer.delete(0, recognizer.length());
//                            break;
//                        case DESCRIPTION_CONTROLLER_LIST:
                        case TEXT_SHOWING_LIST:
                            addNewPrintItemWhenHaveContent(printDataItemList, contentBuilder, cmds);
                            addNewCmds(cmds, recognizerStr);
                            recognizer.delete(0, recognizer.length());
                            break;
//                        case CONTENT_CONTROLLER_LIST:
//                            addNewPrintItemWhenHaveContent(printDataItemList, contentBuilder, cmds);
//                            addNewCmds(cmds, recognizerStr);
//                            printDataItemList.add(new PrintDataItem(contentBuilder.toString(), cmds));
//                            recognizer.delete(0, recognizer.length());
//                            cmds.clear();
//                            break;
//                        case BARCODE_LIST:
//                            addNewPrintItemWhenHaveContent(printDataItemList, contentBuilder, cmds);
//                            addNewCmds(cmds, recognizerStr);
//                            recognizer.delete(0, recognizer.length());
//                            barcodeParser.start();
//                            break;
                    }
                }
            }
        }

        // Add the last item.
        if (recognizer.length() > 0) {
            contentBuilder.append(recognizer.toString());
            recognizer.delete(0, recognizer.length());
        }
        addNewPrintItemWhenHaveContent(printDataItemList, contentBuilder, cmds);


        return new PrintDataItemContainer(printDataItemList);
    }

    private static void handleParseLineSep(List<PrintDataItem> printDataItemList, StringBuilder recognizer, StringBuilder contentBuilder, List<String> cmds, BarcodeParser barcodeParser) {
        endBarcodeParserWhenNeed(printDataItemList, contentBuilder, cmds, barcodeParser);

        if (recognizer.length() > 0) {
            contentBuilder.append(recognizer.toString());
            recognizer.delete(0, recognizer.length());
        }

        // put the fetched content to a new item
        addNewPrintItemWhenHaveContent(printDataItemList, contentBuilder, cmds);
        addPrintItmWithOneCmd(printDataItemList, contentBuilder, PrintDataItem.LINE);
        cmds.clear(); //make the last cmd not affect new line text
    }

    private static void addNewCmds(List<String> cmds, String recognizerStr) {
        cmds.add(recognizerStr);
    }

    private static void addPrintItmWithOneCmd(List<PrintDataItem> printDataItemList, StringBuilder contentBuilder, String cmd) {
        printDataItemList.add(new PrintDataItem(contentBuilder.toString(), Collections.singletonList(cmd)));
    }

    private static void endBarcodeParserWhenNeed(List<PrintDataItem> printDataItemList, StringBuilder contentBuilder, List<String> cmds, BarcodeParser barcodeParser) {
        if (barcodeParser.isStart()) {
            contentBuilder.append(barcodeParser.end());
            addNewPrintItemWhenHaveContent(printDataItemList, contentBuilder, cmds);
        }
    }

    public static class BarcodeParser {

        private final StringBuilder content = new StringBuilder();
        private boolean isStart;

        public void start() {
            isStart = true;
        }

        public boolean isStart() {
            return isStart;
        }

        public boolean next(char ch) throws BarcodeFormatException {
            if (!isStart) {
                return false;
            }
            content.append(ch);
            try {
                return tryParse();
            } catch (NumberFormatException e) {
                throw new BarcodeFormatException("Barcode Format Error");
            }
        }

        private boolean tryParse() {
            try {
                String[] barcodeDefinition = content.toString().split(",");
                String dataLen = barcodeDefinition[3];
                String data = barcodeDefinition[4];
                int dataLenInt = Integer.parseInt(dataLen);

                if (dataLenInt == data.length()) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
            return false;
        }

        public String end() {
            isStart = false;
            String result = content.toString();
            content.delete(0, content.length());
            return result;
        }

        public static class BarcodeFormatException extends Exception {
            public BarcodeFormatException(String message) {
                super(message);
            }
        }
    }

    private static void addNewPrintItemWhenHaveContent(List<PrintDataItem> printDataItemList, StringBuilder contentBuilder, List<String> cmds) {
        if (contentBuilder.length() > 0) {
            contentBuilder.length();
            printDataItemList.add(new PrintDataItem(contentBuilder.toString(), cmds));
            cmds.clear();
            contentBuilder.delete(0, contentBuilder.length());
        }
    }

//    /**
//     * @param context     App context
//     * @param input       container for PrintDataItems
//     * @param bitmapWidth the width of the bitmap to print.
//     * @return The bitmap to print.
//     */
//    public static Bitmap convertPrintDataToBitmap(Context context, PrintDataItemContainer input, int bitmapWidth) throws PrintDataException {
//        //Add line separator if no line at the end.
////        List<PrintDataItem> printDataItems = input.getPrintDataItems();
////        if (!printDataItems.isEmpty() &&
////                Collections.disjoint(printDataItems.get(printDataItems.size() - 1).getCmds(), PrintDataItem.SINGLE_ONLY_CONTROLLER_LIST)) {
////            addPrintItmWithOneCmd(printDataItems, new StringBuilder(), PrintDataItem.LINE);
////        }
//
//
//
//        ScrollView scrollView = PrintItemViewConverter.convertToAView(context, input, bitmapWidth);
//        int totalHeight;
//        totalHeight = scrollView.getMeasuredHeight();
//        if (totalHeight <= 0) {
//            throw new IllegalArgumentException("Bitmap height is 0. PrintDataItemContainer should not be empty");
//        }
//        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, totalHeight, Bitmap.Config.ARGB_8888);
//        bitmap.eraseColor(Color.WHITE);
//        Canvas canvas = new Canvas(bitmap);
//        scrollView.draw(canvas);
//        return bitmap;
//    }

//    public static class PrintItemViewConverter {
//
//        private static List<PrintDataItem> filterPrintDataItems(PrintDataItemContainer input) {
//            List<PrintDataItem> printDataItems = input.getPrintDataItems();
//            List<PrintDataItem> curLineItems = new ArrayList<>(8);
//            List<OneLinePrintDataItems> oneLinePrintDataItems = new ArrayList<>();
//            for (PrintDataItem printDataItem : printDataItems) {
////                if (printDataItem.isLineSep()) {
////                    oneLinePrintDataItems.add(new OneLinePrintDataItems(curLineItems));
////                    oneLinePrintDataItems.add(new OneLinePrintDataItems(new ArrayList<PrintDataItem>(1){{add(printDataItem);}}));
////                    curLineItems = new ArrayList<>(8);
////                } else {
////                    curLineItems.add(printDataItem);
////                }
//                curLineItems.add(printDataItem);
//            }
//            if (!curLineItems.isEmpty()) {
//                oneLinePrintDataItems.add(new OneLinePrintDataItems(curLineItems));
//            }
//            List<PrintDataItem> resultItems = new ArrayList<>();
//
//            //Remove same align items.
//            for (OneLinePrintDataItems oneLinePrintDataItem : oneLinePrintDataItems) {
//                List<PrintDataItem> itemsInOneLine = oneLinePrintDataItem.getPrintDataItems();
//                Comparator<PrintDataItem> alignmentComparator = PrintDataItem.getAlignmentComparator();
//                Collections.sort(itemsInOneLine, alignmentComparator);
//                List<PrintDataItem> itemsToBeRemoved = new ArrayList<>(itemsInOneLine.size());
//                for (int i = 0; i < itemsInOneLine.size()-1; i++) {
//                    PrintDataItem curItem = itemsInOneLine.get(i);
//                    if (i+1 < itemsInOneLine.size()) {
//                        PrintDataItem nextItem = itemsInOneLine.get(i+1);
//                        if (alignmentComparator.compare(curItem, nextItem) == 0) {
//                            itemsToBeRemoved.add(curItem);
//                        }
//                    }
//                }
//                itemsInOneLine.removeAll(itemsToBeRemoved);
//                resultItems.addAll(itemsInOneLine);
//            }
//
//            return resultItems;
//        }
//
//        @NonNull
//        public static ScrollView convertToAView(Context context, PrintDataItemContainer input, int bitmapWidth) throws PrintDataException {
//            List<PrintDataItem> printDataItems = filterPrintDataItems(input);
//            LinearLayout verticalLinearLayout = new LinearLayout(context);
//            verticalLinearLayout.setOrientation(LinearLayout.VERTICAL);
//            verticalLinearLayout.setLayoutParams(new ViewGroup.LayoutParams(bitmapWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
//            LinearLayout horizontalLine = genNewHorizontalLine(context);
//
//            for (PrintDataItem printDataItem : printDataItems) {
//                PrintDataItem.ViewItem child = printDataItem.genView(context, bitmapWidth);
//                if (child.getView() != null) {
//                    if (!child.isLineSeparate()) {
//                        addViewToLine(horizontalLine, child);
//                    } else {
//                        if (horizontalLine.getChildCount() == 0) {
//                            addViewToLine(horizontalLine, child);
//                            horizontalLine = newLineForView(context, verticalLinearLayout, horizontalLine);
//                        } else {
//                            horizontalLine = newLineForView(context, verticalLinearLayout, horizontalLine);
//                            addViewToLine(horizontalLine, child);
//                            horizontalLine = newLineForView(context, verticalLinearLayout, horizontalLine);
//                        }
//                    }
//                } else if (child.isLineSeparate()) {
//                    horizontalLine = newLineForView(context, verticalLinearLayout, horizontalLine);
//                }
//            }
//            verticalLinearLayout.addView(horizontalLine);
//
//            ScrollView scrollView = new ScrollView(context);
//            scrollView.addView(verticalLinearLayout);
//            scrollView.measure(View.MeasureSpec.makeMeasureSpec(bitmapWidth, View.MeasureSpec.EXACTLY),
//                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//            scrollView.layout(0, 0, scrollView.getMeasuredWidth(), scrollView.getMeasuredHeight());
//            return scrollView;
//        }
//
//        private static void addViewToLine(LinearLayout horizontalLine, PrintDataItem.ViewItem child) {
//            View childView = child.getView();
//            horizontalLine.addView(childView);
//            childView.setTag(child);
//        }
//
//        private static LinearLayout newLineForView(Context context, LinearLayout verticalLinearLayout, LinearLayout horizontalLine) {
//            if (horizontalLine.getChildCount() == 0) {
//                //No content, means it is a line sep, it should has a min height.
//                horizontalLine.getLayoutParams().height = PrintDataItem.LINE_HEIGHT;
//            }
//            // New Line.
//            changeCenterViewProperty(horizontalLine, verticalLinearLayout.getLayoutParams().width);
//            verticalLinearLayout.addView(horizontalLine);
//            horizontalLine = genNewHorizontalLine(context);
//            return horizontalLine;
//        }
//
//        private static void changeCenterViewProperty(LinearLayout horizontalLine, int lineWidth) {
//            int childCount = horizontalLine.getChildCount();
//            List<View> children = new ArrayList<>(childCount);
//            for (int i = 0; i < childCount; i++) {
//                children.add(horizontalLine.getChildAt(i));
//            }
//
//            horizontalLine.removeAllViews();
//            Collections.sort(children, PrintDataItem.ViewItem.getComparator());
//
//            if (childCount == 2) {
//                PrintDataItem.ViewItem viewItem0 = (PrintDataItem.ViewItem) children.get(0).getTag();
//                PrintDataItem.ViewItem viewItem1 = (PrintDataItem.ViewItem) children.get(1).getTag();
//                if (viewItem0.getAlign() == viewItem1.getAlign()) {
//                    if (viewItem0.getAlign() == PrintDataItem.ViewItem.ALIGN_CENTER) {
//                        horizontalLine.setWeightSum(4);
//                        children.add(0, createPlaceHolderView(horizontalLine));
//                        children.add(createPlaceHolderView(horizontalLine));
//                    }
//                    if (viewItem0.getAlign() == PrintDataItem.ViewItem.ALIGN_LEFT) {
//                        horizontalLine.setWeightSum(3);
//                        children.add(createPlaceHolderView(horizontalLine));
//                    }
//                    if (viewItem0.getAlign() == PrintDataItem.ViewItem.ALIGN_RIGHT) {
//                        horizontalLine.setWeightSum(3);
//                        children.add(0, createPlaceHolderView(horizontalLine));
//                    }
//                } else if(viewItem0.getAlign() == PrintDataItem.ViewItem.ALIGN_LEFT
//                        && viewItem1.getAlign() == PrintDataItem.ViewItem.ALIGN_RIGHT) {
//                    if (viewItem0.getView() instanceof TextView && viewItem1.getView() instanceof TextView) {
//                        TextView leftTxtView = (TextView) viewItem0.getView();
//                        TextView rightTxtView = (TextView) viewItem1.getView();
//                        float weightSum = 2;
//                        float leftMeasureWidth = leftTxtView.getPaint().measureText(viewItem0.getContent());
//                        float leftWeight = weightSum * Math.max(Math.min((leftMeasureWidth+30)/lineWidth, 0.8f), 0.2f);
//                        ((LinearLayout.LayoutParams)leftTxtView.getLayoutParams()).weight = leftWeight;
//                        ((LinearLayout.LayoutParams)rightTxtView.getLayoutParams()).weight = weightSum - leftWeight;
//                        horizontalLine.setWeightSum(weightSum);
//                    } else {
//                        horizontalLine.setWeightSum(2);
//                    }
//                } else {
//                    horizontalLine.setWeightSum(3);
//                    children.add(createPlaceHolderViewWithAlign(horizontalLine, viewItem0, viewItem1));
//                    Collections.sort(children, PrintDataItem.ViewItem.getComparator());
//                }
//            }
//
//            for (View child : children) {
//                horizontalLine.addView(child);
//            }
//        }
//
//        @NonNull
//        private static View createPlaceHolderViewWithAlign(LinearLayout horizontalLine, PrintDataItem.ViewItem firstItem, PrintDataItem.ViewItem secondItem) {
//            View emptyView = createPlaceHolderView(horizontalLine);
//            PrintDataItem.ViewItem viewItem = new PrintDataItem.ViewItem(false, emptyView);
//            int item0Align = firstItem.getAlign();
//            int item1Align = secondItem.getAlign();
//            int targetAlign;
//            if (item1Align <= PrintDataItem.ViewItem.ALIGN_CENTER) {
//                targetAlign = PrintDataItem.ViewItem.ALIGN_RIGHT;
//            } else if (item0Align >= PrintDataItem.ViewItem.ALIGN_CENTER) {
//                targetAlign = PrintDataItem.ViewItem.ALIGN_LEFT;
//            } else {
//                targetAlign = PrintDataItem.ViewItem.ALIGN_CENTER;
//            }
//            viewItem.setAlign(targetAlign);
//            emptyView.setTag(viewItem);
//            return emptyView;
//        }
//
//        @NonNull
//        private static View createPlaceHolderView(LinearLayout horizontalLine) {
//            View emptyView = new View(horizontalLine.getContext());
//            emptyView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
//            return emptyView;
//        }
//
//        private static LinearLayout genNewHorizontalLine(Context context) {
//            LinearLayout horizontalLinearLayout = new LinearLayout(context);
//            horizontalLinearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            horizontalLinearLayout.setPadding(PrintDataItem.MARGIN, 0, PrintDataItem.MARGIN, 0);
//            return horizontalLinearLayout;
//        }
//    }

}
