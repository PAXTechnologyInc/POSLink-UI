/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2018-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.

 * Module Date: 2019-3-28
 * Module Auth: Justin.Z
 * Description:

 * Revision History:
 * Date                   Author                       Action
 * 2019-3-28               Justin.Z                      Create
 * ============================================================================
 */
package com.pax.pay.ui.constant;

public interface StatusBroadcastAction {

    String TRANS_COMPLETED = "com.pax.us.pay.status.TRANS_COMPLETED";

    String TRANS_ONLINE = "com.pax.us.pay.status.TRANS_ONLINE";

    String EMV_PROCESS_STARTED = "com.pax.us.pay.status.EMV_START";

    String PIN_ENTER_CLEARED = "com,pax.us.pay.status.PIN_CLEARED";

    String PIN_ENTER_COMPLETED = "com,pax.us.pay.status.PIN_ENTER_COMPLETED";

    String PIN_ENTER_ABORTED = "com,pax.us.pay.status.PIN_ENTER_ABORTED";

    String PIN_ENTERING = "com,pax.us.pay.status.PIN_ENTERING";

    String CARD_REMOVED = "com.pax.us.pay.status.CARD_REMOVED";

    String CLSS_LIGHT_NOT_READY = "com.pax.us.pay.status.CLSS_LIGHT_NOT_READY";

    String CLSS_LIGHT_IDLE = "com.pax.us.pay.status.CLSS_LIGHT_IDLE";

    String CLSS_LIGHT_READY_FOR_TXN = "com.pax.us.pay.status.CLSS_LIGHT_READY_FOR_TXN";

    String CLSS_LIGHT_PROCESSING = "com.pax.us.pay.status.CLSS_LIGHT_PROCESSING";

    String CLSS_LIGHT_REMOVE_CARD = "com.pax.us.pay.status.CLSS_LIGHT_REMOVE_CARD";

    String CLSS_LIGHT_COMPLETED = "com.pax.us.pay.status.CLSS_LIGHT_COMPLETED";

    String CLSS_LIGHT_ERROR = "com.pax.us.pay.status.CLSS_LIGHT_ERROR";

    String WARN_REMOVE_CARD = "com.pax.us.pay.status.WARN_REMOVE_CARD";

    String BATCH_CLOSE_STARTED = "com.pax.us.pay.status.BATCH_CLOSE_STARTED";

    String BATCH_UPLOADING = "com.pax.us.pay.status.BATCH_UPLOADING";

    String BATCH_UPDATE_MSG = "com.pax.us.pay.status.UPDATE_MSG";


}
