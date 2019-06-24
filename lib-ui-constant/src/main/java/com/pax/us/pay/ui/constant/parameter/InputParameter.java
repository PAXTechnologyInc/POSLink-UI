package com.pax.us.pay.ui.constant.parameter;

public final class InputParameter {
    public static final class Common {
        public static final String PARA_MESSAGE = "message";
        public static final String PARA_PACKAGE = "senderPackage";
        public static final String PARA_OPTIONS = "options";
    }

    public static final class Amount {
        public static final String PARA_CURRENCY = "currency";
        public static final String PARA_DISP_AMOUNT = "amount";
        public static final String PARA_TIP_OPTIONS = "tipOptions";
        public static final String PARA_CASHBACK_OPTIONS = "cashbackOptions";
    }

    //input account
    public static final class Account {
        public static final String PARA_ENABLE_SWIPE = "enableSwipe";
        public static final String PARA_ENABLE_INSERT = "enableInsert";
        public static final String PARA_ENABLE_TAP = "enableTap";
        public static final String PARA_ENABLE_MANUAL = "enableManualEntry";
    }

    public static final class Information {
        public static final String PARA_TITLE = "title";
    }

}
