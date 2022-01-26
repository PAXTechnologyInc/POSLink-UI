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
import java.util.Arrays;
import java.util.List;

/**
 * For internal use
 */
public class PrintDataItem {

    public static final String BOLD = "\\B";
    public static final String LEFT_ALIGN = "\\L";
    public static final String RIGHT_ALIGN = "\\R";
    public static final String CENTER_ALIGN = "\\C";
    //    public static final String INVERT = "\\I";
    public static final String SMALL_FONT = "\\1";
    public static final String NORMAL_FONT = "\\2";
    public static final String BIG_FONT = "\\3";

    //    public static final String HEADER = "\\$Header";
//    public static final String TRAILER = "\\$Trailer";
//
//    public static final String DISCLAIMER = "\\$Disclaimer";
//
//    public static final String DATE = "\\$Date";
//    public static final String TIME = "\\$Time";
//    public static final String SN = "\\$SN#";
    public static final String LINE = "\n";
    public static final String LINE_SEP = "\\n";
//    public static final String BARCODE = "\\$BARD";
//    public static final String LOGO = "\\$Logo";
//    public static final String E_SIGNATURE = "\\$eSig";

//    public static final List<String> DESCRIPTION_CONTROLLER_LIST = Arrays.asList(
//            BOLD, LEFT_ALIGN, RIGHT_ALIGN, CENTER_ALIGN,
//            INVERT, SMALL_FONT, NORMAL_FONT, BIG_FONT, LOGO
//    );
//
//    public static final List<String> CONTENT_CONTROLLER_LIST = Arrays.asList(
//            DATE, TIME, SN
//    );
//
//    public static final List<String> SINGLE_ONLY_CONTROLLER_LIST = Arrays.asList(
//            HEADER, TRAILER, LINE, DISCLAIMER, E_SIGNATURE, LINE_SEP
//
//
//    public static final List<String> SINGLE_ONLY_CONTROLLER_LIST_ONLINE = Arrays.asList(
//            HEADER, TRAILER, DISCLAIMER, E_SIGNATURE
//    );
//
//    public static final List<String> BARCODE_LIST =  Arrays.asList(
//            BARCODE
//    );

    public static final List<String> TEXT_SHOWING_LIST =  Arrays.asList(
            BOLD, LEFT_ALIGN, RIGHT_ALIGN, CENTER_ALIGN, LINE, SMALL_FONT, NORMAL_FONT, BIG_FONT
    );

    public static final int FONT_SMALL_PX = 20;
    public static final int FONT_NORMAL_PX = 24;
    public static final int FONT_BIG_PX = 32;
    public static final int LINE_HEIGHT = 36;
    public static final int MARGIN = 30;

    private final List<String> cmds = new ArrayList<>(3);
    private String content = "";

    public PrintDataItem(String content, List<String> cmds) {
        this.content = content;
        if (cmds != null) {
            this.cmds.addAll(cmds);
        }
    }

//    public void addCmd(String cmd) {
//        cmds.add(cmd);
//    }

    public List<String> getCmds() {
        return cmds;
    }

//    /**
//     * @return If it is line, then return null.
//     */
//    public ViewItem genView(Context context, int bitmapWidth) throws PrintDataException {
//
//        boolean isLineSeparate = false;
//        int textSize = 0;
//        int gravity = 0;
//        int weight = 1;
//        boolean invert = false;
//        int alignment = ViewItem.ALIGN_LEFT;
//        boolean isBold = false;
//        for (String cmd : cmds) {
//            int localTextSize = getPaintTextSize(cmd);
//            if (localTextSize > 0) {
//                textSize = localTextSize;
//            }
//            if (cmd.equals(LEFT_ALIGN)) {
//                gravity = Gravity.LEFT;
//                alignment = ViewItem.ALIGN_LEFT;
//            } else if (cmd.equals(RIGHT_ALIGN)) {
//                gravity = Gravity.RIGHT;
//                alignment = ViewItem.ALIGN_RIGHT;
//            } else if (cmd.equals(CENTER_ALIGN)) {
//                gravity = Gravity.CENTER;
//                alignment = ViewItem.ALIGN_CENTER;
////            } else if (cmd.equals(INVERT)) {
////                invert = true;
////            } else if (cmd.equals(DATE)) {
////                content = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
////            } else if (cmd.equals(TIME)) {
////                content = new SimpleDateFormat("hh:mm:ss").format(new Date());
////            } else if (cmd.equals(SN)) {
////                content = SysManager.getInstance().getSN();
//            } else if (cmd.equals(LINE) || cmd.equals(LINE_SEP)) {
//                return new ViewItem(true, null);
////            } else if (cmd.equals(HEADER)) {
////                content = PETEPrintCommon.getHeader();
////                gravity = Gravity.CENTER;
////                alignment = ViewItem.ALIGN_CENTER;
////                isLineSeparate = true;
////            } else if (cmd.equals(TRAILER)) {
////                content = PETEPrintCommon.getTrailer();
////                gravity = Gravity.CENTER;
////                alignment = ViewItem.ALIGN_CENTER;
////                if (TextUtils.isEmpty(content)) {
////                    content = ".......................................................";
////                }
////                isLineSeparate = true;
////            } else if (cmd.equals(DISCLAIMER)) {
////                gravity = Gravity.CENTER;
////                alignment = ViewItem.ALIGN_CENTER;
////                textSize = FONT_SMALL_PX;
////                if (TextUtils.isEmpty(content)) {
////                    content = App.getResString(R.string.signature_info);
////                }
////                isLineSeparate = true;
////            } else if (cmd.equals(BARCODE)) {
////                PrintBarcode.BarcodeData barcodeData = PrintBarcode.parseBarcode(content, bitmapWidth);
////                ImageView imageView = createImageView(context, barcodeData.getBitmap());
////                if (barcodeData.isNeedPrintData()) {
////                    LinearLayout ly = new LinearLayout(context);
////                    ly.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
////                    ly.setOrientation(LinearLayout.VERTICAL);
////                    TextView textView = createTextView(context, FONT_SMALL_PX, Gravity.CENTER, false, barcodeData.getData(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
////                    ly.addView(imageView);
////                    ly.addView(textView);
////                    return new ViewItem(true, ly);
////                }
////                return new ViewItem(true, imageView);
////            } else if (cmd.equals(E_SIGNATURE)) {
////                File file = new File(context.getFilesDir() + File.separator + DoSignatureAction.SIGNATURE_TXT);
////                if (!file.exists()) {
////                    throw new PrintDataException(App.getResString(R.string.signature_not_found));
////                }
////                String signData = FileUtils.readFile2String(file, "utf-8");
////                Bitmap bitmap = PETEPrintCommon.generateSignatureBmp(signData);
////                return new ViewItem(true, createImageView(context, bitmap));
////            } else if (cmd.equals(LOGO)) {
////                Bitmap bitmap = PETEPrintCommon.parseLogo(context, content);
////                return new ViewItem(true, createImageView(context, bitmap));
//            } else if (cmd.equals(BOLD)) {
//                isBold = true;
//            }
//        }
//
//        TextView textView = createTextView(context, textSize, gravity, invert, content, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, weight));
//        textView.getPaint().setFakeBoldText(isBold);
//
//        ViewItem viewItem = new ViewItem(isLineSeparate, textView);
//        viewItem.setAlign(alignment);
//        viewItem.setContent(content);
//        return viewItem;
//    }

//    private static TextView createTextView(Context context, int textSize, int gravity, boolean invert, String content, LinearLayout.LayoutParams layoutParams) {
//        TextView textView = new TextView(context);
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize > 0 ? textSize : FONT_NORMAL_PX);
//        textView.setLayoutParams(layoutParams);
//        textView.setGravity(gravity);
//        textView.setTextColor(invert ? Color.WHITE : Color.BLACK);
//        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(content);
//        spannableStringBuilder.setSpan(new BackgroundColorSpan(invert ? Color.BLACK : Color.WHITE), 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textView.setText(spannableStringBuilder);
//        return textView;
//    }
//
//    private ImageView createImageView(Context context, Bitmap bitmap) {
//        ImageView imgView = new ImageView(context);
//        imgView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        imgView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//        imgView.setImageBitmap(bitmap);
//        return imgView;
//    }
//
//    private static int getPaintTextSize(String cmd) {
//        return FONT_NORMAL_PX;
//
////        if (cmd.equals(BIG_FONT)) {
////            return FONT_BIG_PX;
////        } else if (cmd.equals(NORMAL_FONT)) {
////            return FONT_NORMAL_PX;
////        } else if (cmd.equals(SMALL_FONT)) {
////            return FONT_SMALL_PX;
////        }
////        return -1;
//    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String cmd : cmds) {
            stringBuilder.append(cmd).append(" ");
        }
        stringBuilder.append(" ").append(content);
        return stringBuilder.toString();
    }

//    public static class ViewItem {
//        private boolean isLineSeparate = false;
//        private View view;
//        public static final int ALIGN_LEFT = -1;
//        public static final int ALIGN_CENTER = 0;
//        public static final int ALIGN_RIGHT = 1;
//        private int align;
//        private String content;
//
//        public ViewItem(boolean isLineSeparate, View view) {
//            this.isLineSeparate = isLineSeparate;
//            this.view = view;
//        }
//
//        @NonNull
//        public static Comparator<View> getComparator() {
//            return new Comparator<View>() {
//                @Override
//                public int compare(View l, View r) {
//                    ViewItem leftViewItem = (ViewItem) l.getTag();
//                    ViewItem rightViewItem = (ViewItem) r.getTag();
//                    return Integer.compare(leftViewItem.getAlign(), rightViewItem.getAlign());
//                }
//            };
//        }
//
//        public boolean isLineSeparate() {
//            return isLineSeparate;
//        }
//
//        public View getView() {
//            return view;
//        }
//
//
//        public int getAlign() {
//            return align;
//        }
//
//        public void setAlign(int align) {
//            this.align = align;
//        }
//
//        public String getContent() {
//            return content;
//        }
//
//        public void setContent(String content) {
//            this.content = content;
//        }
//    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public boolean isLineSep() {
//        return !Collections.disjoint(cmds, SINGLE_ONLY_CONTROLLER_LIST) || cmds.contains(LOGO) || cmds.contains(BARCODE);
//    }

//    @NonNull
//    public static Comparator<PrintDataItem> getAlignmentComparator() {
//        return new Comparator<PrintDataItem>() {
//            @Override
//            public int compare(PrintDataItem l, PrintDataItem r) {
//                List<String> leftCmds = l.getCmds();
//                List<String> rightCmds = r.getCmds();
//                Set<String> leftCenterRight = new HashSet<>(3);
//                leftCenterRight.add(LEFT_ALIGN);
//                leftCenterRight.add(CENTER_ALIGN);
//                leftCenterRight.add(RIGHT_ALIGN);
//                //leftCenterRight.add(SMALL_FONT);
//                //leftCenterRight.add(NORMAL_FONT);
//                //leftCenterRight.add(BIG_FONT);
//
//                String lastAlignCmdOfLeft = getLastAlignCmd(leftCmds, leftCenterRight);
//                String lastAlignCmdOfRight = getLastAlignCmd(rightCmds, leftCenterRight);
//                int leftAlignNum = getAlignNum(lastAlignCmdOfLeft);
//                int rightAlignNum = getAlignNum(lastAlignCmdOfRight);
//
//                return Integer.compare(leftAlignNum, rightAlignNum);
//            }
//
//            private int getAlignNum(String align) {
//                List<String> list = new ArrayList<String>() {
//                    {
//                        add(LEFT_ALIGN);
//                        add(CENTER_ALIGN);
//                        add(RIGHT_ALIGN);
//                        //add(SMALL_FONT);
//                        //add(NORMAL_FONT);
//                        //add(BIG_FONT);
//                    }
//                };
//                if (list.contains(align)) {
//                    return list.indexOf(align);
//                } else {
//                    return -1;
//                }
////                return RIGHT_ALIGN.equals(align) ? 1 :
////                        CENTER_ALIGN.equals(align) ? 0 : -1;
//            }
//
//            private String getLastAlignCmd(List<String> cmds, Set<String> leftCenterRight) {
//                for (int i = cmds.size()-1; i >= 0; i--) {
//                    String cmd = cmds.get(i);
//                    if (leftCenterRight.contains(cmd)) {
//                        return cmd;
//                    }
//                }
//                return null;
//            }
//        };
//    }
}
