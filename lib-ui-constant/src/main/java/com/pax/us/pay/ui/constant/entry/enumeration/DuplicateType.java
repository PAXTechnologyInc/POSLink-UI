package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Duplicate Type
 * <p>
 *     Re-auth Option after getting DUP response<br>
 *     See {@link com.pax.us.pay.ui.constant.entry.OptionEntry#ACTION_SELECT_DUPLICATE_OVERRIDE} for details.
 * </p>
 */
public final class DuplicateType {
    private DuplicateType(){

    }
    /**
     * Override
     * @deprecated {@link DuplicateType#OVERRIDE} was used in {@link com.pax.us.pay.ui.constant.entry.OptionEntry#ACTION_SELECT_DUPLICATE_OVERRIDE}.
     * But that action is deprecated.
     */
    @Deprecated public static final String OVERRIDE = "OVERRIDE";

    /**
     * Get original response data
     * @deprecated {@link DuplicateType#GET_ORIG_RESP} was used in {@link com.pax.us.pay.ui.constant.entry.OptionEntry#ACTION_SELECT_DUPLICATE_OVERRIDE}.
     * But that action is deprecated.
     */
    @Deprecated public static final String GET_ORIG_RESP = "GET ORIGINAL RESPONSE DATA";

}
