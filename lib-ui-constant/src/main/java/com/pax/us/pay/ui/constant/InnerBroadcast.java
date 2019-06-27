/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2018-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.

 * Module Date: 2019-3-27
 * Module Auth: Justin.Z
 * Description:

 * Revision History:
 * Date                   Author                       Action
 * 2019-3-27               Justin.Z                      Create
 * ============================================================================
 */
package com.pax.us.pay.ui.constant;


public class InnerBroadcast {

    public class ReqAction {
        /**
         * NEXT with value: request BroadPOS to go to next step with input value.
         */
        public static final String NEXT = "com.pax.us.pay.ui.NEXT";

        /**
         * ABORT: request BroadPOS to abort the transaction.
         */
        public static final String ABORT = "com.pax.us.pay.ui.ABORT";

        /**
         * PREV: request BroadPOS to go to previous step .
         */
        public static final String PREV = "com.pax.us.pay.ui.PREV";

        /**
         * SECURITY_AREA: request BroadPOS to show security widget on a specific position.
         */
        public static final String AREA = "com.pax.us.pay.ui.SECURITY_AREA";
    }

    public class RespAction {
        /**
         * ACCEPTED: BroadPOS accepted request action from your app.
         */
        public static final String ACCEPTED = "com.pax.us.pay.ui.ACCEPTED";

        /**
         * DECLINED with reason: BroadPOS declined request action with result code, result message.
         */
        public static final String DECLINED = "com.pax.us.pay.ui.DECLINED";

    }

}
