package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Debit Account Type
 */
public final class AccountType {
    private AccountType(){

    }
    /**
     * Saving
     */
    public static final String SAVING = "SAVING";

    /**
     * Checking
     */
    public static final String CHECKING = "CHECKING";


    /**
     * All defined String
     * @return String[]
     */
    public static String[] values() {
        return new String[]{SAVING, CHECKING};
    }
}
