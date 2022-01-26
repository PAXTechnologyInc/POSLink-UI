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

package com.pax.pay.ui.def.poslink.info;

import androidx.annotation.RestrictTo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by John.W on 2018/7/4.
 * Description:
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class ItemDetail implements Serializable{

    @JsonProperty(value = "productName")
    private String productName;
    @JsonProperty(value = "plUcode")
    private String plUcode;
    @JsonProperty(value = "price")
    private double price;
    @JsonProperty(value = "unit")
    private String unit;
    @JsonProperty(value = "unitPrice")
    private double unitPrice;
    @JsonProperty(value = "tax")
    private String tax;
    @JsonProperty(value = "quantity")
    private String quantity;
    @JsonProperty(value = "productImgUri")
    private String productImgUri;
    @JsonProperty(value = "productImgDesc")
    private String productImgDesc;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPlUcode() {
        return plUcode;
    }

    public void setPlUcode(String plUcode) {
        this.plUcode = plUcode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductImgUri() {
        return productImgUri;
    }

    public void setProductImgUri(String productImgUri) {
        this.productImgUri = productImgUri;
    }

    public String getProductImgDesc() {
        return productImgDesc;
    }

    public void setProductImgDesc(String productImgDesc) {
        this.productImgDesc = productImgDesc;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemDetail{");
        sb.append("productName='").append(productName).append('\'');
        sb.append(", plUcode='").append(plUcode).append('\'');
        sb.append(", price=").append(price);
        sb.append(", unit='").append(unit).append('\'');
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", tax='").append(tax).append('\'');
        sb.append(", quantity='").append(quantity).append('\'');
        sb.append(", productImgUri='").append(productImgUri).append('\'');
        sb.append(", productImgDesc='").append(productImgDesc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
