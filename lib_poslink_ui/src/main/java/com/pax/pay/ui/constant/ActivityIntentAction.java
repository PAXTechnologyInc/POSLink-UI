/*
 *
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2009-2020 PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.
 * Description: // Detail description about the function of this module,
 *             // interfaces with the other modules, and dependencies.
 * ============================================================================
 *
 */

package com.pax.pay.ui.constant;

/**
 * This actions are the actions you should register in AndroidManifest.
 */
public interface ActivityIntentAction {
    /**
     * The intent action of Input Account page
     */
    String ACTION_INPUT_ACCOUNT = "com.pax.us.pay.action.INPUTACCOUNT";
    /**
     * The intent action of Enter PIN page
     */
    String ACTION_ENTER_PIN = "com.pax.us.pay.action.ENTER_PIN";
    /**
     * The intent action of Enter Amount
     */
    String ACTION_ENTER_AMOUNT = "com.pax.us.pay.action.ENTER_AMOUNT";
    /**
     * The intent action of Enter Tip
     */
    String ACTION_ENTER_TIP = "com.pax.us.pay.action.ENTER_TIP";

    /**
     * The intent action of Select Ebt type
     */
    String ACTION_SELECT_EBT_TYPE = "com.pax.us.pay.action.SELECT_EBT_TYPE";

    /**
     * The intent action of Enter Trans.No
     */
    String ACTION_ENTER_TRANS_NO = "com.pax.us.pay.action.ENTER_TRANS_NO";

    String ACTION_DISPLAY_TRANSINFO = "com.pax.us.pay.action.DISPLAY_TRANS_INFO";

    /**
     * The intent action of Enter Exp date
     */
    String ACTION_ENTER_EXPIRE_DATE = "com.pax.us.pay.action.ENTER_EXPIRE_DATE";

    /**
     * The intent action of Enter Address
     */
    String ACTION_ENTER_ADDRESS = "com.pax.us.pay.action.ENTER_ADDRESS";

    /**
     * The intent action of Enter Zip Code
     */
    String ACTION_ENTER_ZIPCODE = "com.pax.us.pay.action.ENTER_ZIP_CODE";

    /**
     * The intent action of enter VCode/CVV
     */
    String ACTION_ENTER_VCODE = "com.pax.us.pay.action.ENTER_CVV";

    /**
     * The intent action of Select By Pass
     */
    String ACTION_SELECT_BY_PASS = "com.pax.us.pay.action.SELECT_BY_PASS";

    /**
     * The intent action of Select Card Present
     */
    String ACTION_SELECT_CARD_PRESENT = "com.pax.us.pay.action.SELECT_CARD_PRESENT";

    /**
     * The intent action of Enter AuthCode
     */
    String ACTION_ENTER_AUTH = "com.pax.us.pay.action.ENTER_AUTH_CODE";


    /**
     * The intent action of Signature
     */
    String ACTION_SIGNATURE = "com.pax.us.pay.action.SIGNATURE";


    /**
     * The intent action of Enter FSA Amount
     */
    String ACTION_ENTER_FSA_AMOUNT = "com.pax.us.pay.action.ENTER_FSA_AMOUNT";


    /**
     * The intent action of Enter voucher data
     */
    String ACTION_ENTER_VOUCHER_DATA = "com.pax.us.pay.action.ENTER_VOUCHER_DATA";

    /**
     * The intent action of Enter voucher data
     */
    String ACTION_ENTER_AVS_DATA = "com.pax.us.pay.action.ENTER_AVS_DATA";
}
