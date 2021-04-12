package com.pax.us.pay.ui.component.keyboard;

public class KeyBoardStatus {
    private static boolean isKeyBoardPopuped;

    public static boolean isKeyBoardPopuped() {
        return isKeyBoardPopuped;
    }

    public static void setKeyBoardPopuped(boolean keyBoardPopuped) {
        isKeyBoardPopuped = keyBoardPopuped;
    }
}
