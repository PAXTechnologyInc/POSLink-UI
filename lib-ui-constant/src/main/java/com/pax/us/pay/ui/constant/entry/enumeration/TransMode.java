package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Transaction Mode<br>
 * This is a general parameter for every Activity Action<br>
 * Default value: {@link #NORMAL}<br>
 */
public final class TransMode {
    private TransMode(){

    }

    /**
     * NORMAL
     * <p>No watermark</p>
     */
    public static final String NORMAL = "NORMAL";

    /**
     * DEMO
     * <p>BroadPOS show a watermark "FOR DEMO ONLY(Not for commercial use)"</p>
     */
    public static final String DEMO = "DEMO";

    /**
     * TEST
     * <p>BroadPOS show a watermark "FOR TEST ONLY(Not for commercial use)"</p>
     */
    public static final String TEST = "TEST";

    /**
     * TEST&amp;DEMO
     * <p>BroadPOS show a watermark "FOR TEST&amp;DEMO ONLY\n(Not for commercial use)</p>
     */
    public static final String TEST_AND_DEMO = "TEST&DEMO";

}
