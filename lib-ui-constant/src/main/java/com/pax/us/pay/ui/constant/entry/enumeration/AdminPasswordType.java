package com.pax.us.pay.ui.constant.entry.enumeration;

/**
 * Administrator Password Type
 *
 * <p>
 *  BroadPOS support Manager and Operator passwords for different types of operations.<br>
 *  <br>
 *  The Manager Password has manager level privileges and can unlock any terminal feature.<br>
 *  Includes features protected with an Operator Password – i.e. the Manager Password can be used in place of the Operator Password.<br>
 *  <br>
 *  The Operator Password has user level privileges.<br>
 *  The most used terminal operations, such as batch close and transaction review, etc. should be protected by this Operator level password<br>
 *</p>
 *
 */
public final class AdminPasswordType {
    private AdminPasswordType(){

    }
    /**
     * Operator
     */
    public static final String OPERATOR = "OPERATOR";

    /**
     * Manager
     */
    public static final String MANAGER = "MANAGER";
}
