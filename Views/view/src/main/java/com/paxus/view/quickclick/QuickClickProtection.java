package com.paxus.view.quickclick;

/**
 * a 500ms-quick-click-protection singleton
 */
public class QuickClickProtection extends AQuickClickProtection {
    private static QuickClickProtection quickClickProtection;

    private QuickClickProtection(long timeoutMs) {
        super(timeoutMs);
    }

    public static synchronized QuickClickProtection getInstance() {
        if (quickClickProtection == null) {
            quickClickProtection = new QuickClickProtection(800);
        }

        return quickClickProtection;
    }
}
