/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2018-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.
 *
 * Module Date: 2020/1/18
 * Module Auth: Fahy.F
 * Description:
 *
 * Revision History:
 * Date                   Author                       Action
 * 2020/1/18              Fahy.F                       Create
 * ============================================================================
 */

package com.pax.us.pay.ui.constant.bean.info;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDetailWrapper {
    @JsonProperty(value = "index")
    private String index;
    @JsonProperty(value = "ItemDetail")
    private ItemDetail itemDetail;

    public ItemDetailWrapper() {
    }

    public ItemDetailWrapper(String index, ItemDetail itemDetail) {
        this.index = index;
        this.itemDetail = itemDetail;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public ItemDetail getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ItemDetail itemDetail) {
        this.itemDetail = itemDetail;
    }
}
