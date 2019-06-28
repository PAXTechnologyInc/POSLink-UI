/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2017 - ? Pax Corporation. All rights reserved.
 * Module Date: 2017-3-24
 * Module Author: huangmuhua
 * Description:
 *
 * ============================================================================
 */
package com.pax.pay.poslink.ui.demo.event;

public class ClssLightEvent extends Event<String, Void> {
    public ClssLightEvent(String status) {
        super(status);
    }
}
