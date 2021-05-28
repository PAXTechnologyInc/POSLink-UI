/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2016 - ? Pax Corporation. All rights reserved.
 * Module Date: 2016-11-25
 * Module Author: Steven.W
 * Description:
 *
 * ============================================================================
 */
package com.paxus.view.quickclick;

/**
 * quick click protection, workaround of handling multi-touch
 *
 * @author Steven.W
 */
abstract class AQuickClickProtection extends AutoRecoveredValueSetter<Boolean> {

    AQuickClickProtection(long timeoutMs) {
        setTimeoutMs(timeoutMs);
        setValue(false);
        setRecoverTo(false);
    }

    /**
     * def 500ms
     */
    AQuickClickProtection() {
        setTimeoutMs(500);
        setValue(false);
        setRecoverTo(false);
    }

    /**
     * check if it is protecting
     *
     * @return true/false
     */
    public boolean isStarted() {
        return getValue();
    }

    /**
     * start the click protection
     */
    public void start() {
        setValue(true);
        autoRecover();
    }

    /**
     * force to stop the protection
     */
    public void stop() {
        recover();
    }
}
