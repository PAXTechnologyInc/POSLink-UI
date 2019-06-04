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
package com.pax.pay.ui.constant;

public interface ActivityBroadcastAction {

    /**
     * NEXT with value: request BroadPOS to go to next step with input value.
     */
    String BROADCAST_ACTION_NEXT = "com.pax.us.pay.ui.NEXT";

    /**
     * PREV: request BroadPOS to go to previous step .
     */
    String BROADCAST_ACTION_PREV = "com.pax.us.pay.ui.PREV";

    /**
     * ABORT: request BroadPOS to abort the transaction.
     */
    String BROADCAST_ACTION_ABORT = "com.pax.us.pay.ui.ABORT";

    /**
     * SECURITY_AREA: request BroadPOS to show security widget on a specific position.
     */
    String BROADCAST_ACTION_AREA = "com.pax.us.pay.ui.SECURITY_AREA";

    /**
     * ACCEPTED: BroadPOS accepted request action from your app.
     */
    String BROADCAST_ACTION_ACCEPTED = "com.pax.us.pay.ui.ACCEPTED";

    /**
     * DECLINED with reason: BroadPOS declined request action with result code, result message.
     */
    String BROADCAST_ACTION_DECLINED = "com.pax.us.pay.ui.DECLINED";
}
