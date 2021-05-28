/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2018-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.

 * Module Date: 2019-3-29
 * Module Auth: Justin.Z
 * Description:

 * Revision History:
 * Date                   Author                       Action
 * 2019-3-29               Justin.Z                      Create
 * ============================================================================
 */
package com.pax.pay.ui.def.eventbus;

import org.greenrobot.eventbus.EventBus;

public class EventBusUtil {

    public static void doEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    public static void unregister(Object obj) {
        if (isRegisteredToEventBus(obj)) {
            EventBus.getDefault().unregister(obj);
        }
    }

    public static boolean isRegisteredToEventBus(Object obj) {
        return EventBus.getDefault().isRegistered(obj);
    }

    public static void register(Object obj) {
        if (!isRegisteredToEventBus(obj)) {
            EventBus.getDefault().register(obj);
        }
    }
}
