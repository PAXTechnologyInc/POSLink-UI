package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * PAN Style
 */
@Deprecated public final class PanStyles {

    /**
     * <p>@Deprecated: Instead of sending NORMAL and NEW_PAN with SecurityEntry.ACTION_INPUT_ACCOUNT,
     * SecurityEntry.ACTION_INPUT_ACCOUNT is followed by SecurityEntry.ACTION_INPUT_NEW_ACCOUNT to differentiate between the two.</p>
     */
    @Deprecated private PanStyles(){}
    /**
     * NORMAL
     * BroadPOS prompt as "Please Enter Card Number"
     */
    @Deprecated public static final String NORMAL = "NORMAL";

    /**
     * NEW
     * BroadPOS prompt as "Please Enter New Card Number"
     */
    @Deprecated public static final String NEW_PAN = "NEW";

}
