package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Options when handle a partial-approved transaction
 * <p>
 * For {@link com.pax.us.pay.ui.constant.entry.ConfirmationEntry#ACTION_REVERSE_PARTIAL_APPROVAL},
 * the options are {@link PartialApprovalOption#REVERSE} and {@link PartialApprovalOption#ACCEPT}<br>
 * If select {@link PartialApprovalOption#REVERSE}, the transaction will be voided.<br>
 * If select {@link PartialApprovalOption#ACCEPT}, the transaction will be accepted with partial-approval.
 *</p>
 * <p>
 * For {@link com.pax.us.pay.ui.constant.entry.ConfirmationEntry#ACTION_SUPPLEMENT_PARTIAL_APPROVAL},
 * the options are {@link PartialApprovalOption#ACCEPT} and {@link PartialApprovalOption#DECLINE}<br>
 * If select {@link PartialApprovalOption#ACCEPT}, a split-tender transaction will be started.<br>
 * If select {@link PartialApprovalOption#DECLINE}, this transaction will be accepted with partial-approval.
 * </p>
 */
public final class PartialApprovalOption {
    private PartialApprovalOption(){

    }
    /**
     * Accept
     */
    public static final String ACCEPT = "Accept";
    /**
     * Decline
     */
    public static final String DECLINE = "Decline";
    /**
     * Reverse
     */
    public static final String REVERSE = "Reverse";
}