package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Definitions for POSLink Manage UI
 */
public final class ManageUIConst {
    private ManageUIConst(){

    }
    /**
     * Continuous Screen<br>
     * "0" default.<br>
     * "1" indicate the terminal not go to idle screen but waiting for the next command<br>
     */
    public static class ContinuousScreen {
        public static final String DEFAULT = "0";
        public static final String DO_NOT_GO_TO_IDLE = "1";
    }

    /**
     * Line item action:<br>
     * 0: add(default value)<br>
     * 1: update<br>
     * 2: delete<br>
     */
    public static class LineItemAction {
        public static final String ADD = "0";
        public static final String UPDATE = "1";
        public static final String DELETE = "2";
    }

    /**
     * Numeric Keys:<br>
     * KEY0 KEY1 KEY2 KEY3 KEY4 KEY5 KEY6 KEY7 KEY8 KEY9<br>
     * Control Keys:<br>
     * KEYCANCEL KEYENTER KEYCLEAR<br>
     */
    public static class HardKeys {
        public static final String KEY_0 = "KEY0";
        public static final String KEY_1 = "KEY1";
        public static final String KEY_2 = "KEY2";
        public static final String KEY_3 = "KEY3";
        public static final String KEY_4 = "KEY4";
        public static final String KEY_5 = "KEY5";
        public static final String KEY_6 = "KEY6";
        public static final String KEY_7 = "KEY7";
        public static final String KEY_8 = "KEY8";
        public static final String KEY_9 = "KEY9";
        public static final String KEY_CANCEL = "KEYCANCEL";
        public static final String KEY_ENTER = "KEYENTER";
        public static final String KEY_CLEAR = "KEYCLEAR";
    }

    /**
     * Label property value:<br>
     * 0 - Unchecked (default)<br>
     * 1 - Checked<br>
     * Relevant only if the ButtonType is "CheckBox".<br>
     */
    public static class LabelProperty {
        public static final String UNCHECKED = "0";
        public static final String CHECKED = "1";
    }

    /**
     * Button Type value:<br>
     *  1 - Radio Button (default). It allows the user to choose only one of a predefined set of mutually exclusive options, and the API returns right after selection.<br>
     *  2 - CheckBox.  It allows the user to choose multiple entities of a predefined set of options, and the API is returned upon key press.<br>
     *  "CANCEL" – API return abort<br>
     *  "CLEAR" will clear the CheckBox selection<br>
     *  "ACCEPT" – API return accept<br>
     */
    public static class ButtonType {
        public static final String RADIO_BUTTON = "1";
        public static final String CHECK_BOX = "2";
    }
}
