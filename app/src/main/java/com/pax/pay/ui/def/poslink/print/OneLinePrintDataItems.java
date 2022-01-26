/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2018-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.
 *
 * Module Date: 2019/7/30
 * Module Auth: Frank.W
 * Description:
 *
 * Revision History:
 * Date                   Author                       Action
 * 2019/7/30              Frank.W                       Create
 * ============================================================================
 */
package com.pax.pay.ui.def.poslink.print;

import java.util.ArrayList;
import java.util.List;

public class OneLinePrintDataItems {
    private List<PrintDataItem> printDataItems = new ArrayList<>();

    public OneLinePrintDataItems(List<PrintDataItem> printDataItems) {
        this.printDataItems = printDataItems;
    }

    public List<PrintDataItem> getPrintDataItems() {
        return printDataItems;
    }
}
