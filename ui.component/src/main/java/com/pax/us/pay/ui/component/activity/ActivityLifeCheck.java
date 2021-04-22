package com.pax.us.pay.ui.component.activity;

public class ActivityLifeCheck {
    private static boolean IsAppFrontGround;

    /**
     * For fixing ADJ-88
     * when using securityHover, IsAppFrontGround is always true,
     * Hence, we need another flag to identify whether the current UI is Def.
     * It is for DefUI to decide whether or not to handle status prompt
     */
    private static boolean usingDefUI = true;

    public static boolean isAppFrontGround() {
        return IsAppFrontGround;
    }

    public static void setAppFrontGround(boolean appFrontGround) {
        IsAppFrontGround = appFrontGround;
    }

    public static boolean isUsingDefUI() {
        return usingDefUI;
    }

    public static void setUsingDefUI(boolean isUsingDefUI) {
        ActivityLifeCheck.usingDefUI = isUsingDefUI;
    }
}
