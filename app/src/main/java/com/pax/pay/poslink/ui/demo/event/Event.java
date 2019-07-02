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
package com.pax.pay.poslink.ui.demo.event;

public class Event<T, O> {

    private final T status;
    private final O data;

    public Event(T status) {
        this(status, null);
    }

    public Event(T status, O data) {
        this.status = status;
        this.data = data;
    }

    public T getStatus() {
        return status;
    }


    public O getData() {
        return data;
    }

}
