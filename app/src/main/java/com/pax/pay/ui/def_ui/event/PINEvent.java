/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2018-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.

 * Module Date: 2019-4-1
 * Module Auth: Justin.Z
 * Description:

 * Revision History:
 * Date                   Author                       Action
 * 2019-4-1               Justin.Z                      Create
 * ============================================================================
 */
package com.pax.pay.ui.def_ui.event;

import com.pax.pay.ui.def_ui.eventbus.Event;


public class PINEvent extends Event {

    public PINEvent(String status, int data) {
        super(status, data);
    }
}
