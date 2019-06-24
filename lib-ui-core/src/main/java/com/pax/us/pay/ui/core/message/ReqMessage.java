package com.pax.us.pay.ui.core.message;

import java.util.List;
import java.util.Map;

class ReqMessage {
    private String message;
    private String reqPackage;
    private String reqAction;
    private String reqCategory;
    private String[] reqOption;
    private String currency;
    private long amount;
    private boolean enableEntryManual;
    private boolean enableSwipe;
    private boolean enableInsert;
    private boolean enableTap;
    private List<String> amountOption;
    private String title;
    private Map<String, String> informations;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReqPackage() {
        return reqPackage;
    }

    public void setReqPackage(String reqPackage) {
        this.reqPackage = reqPackage;
    }

    public String[] getReqOption() {
        return reqOption;
    }

    public void setReqOption(String[] reqOption) {
        this.reqOption = reqOption;
    }

    public String getReqAction() {
        return reqAction;
    }

    public void setReqAction(String reqAction) {
        this.reqAction = reqAction;
    }

    public String getReqCategory() {
        return reqCategory;
    }

    public void setReqCategory(String reqCategory) {
        this.reqCategory = reqCategory;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public boolean getEnableEntryManual() {
        return enableEntryManual;
    }

    public void setEnableEntryManual(boolean enableEntryManual) {
        this.enableEntryManual = enableEntryManual;
    }

    public boolean getEnableSwipe() {
        return enableSwipe;
    }

    public void setEnableSwipe(boolean enableSwipe) {
        this.enableSwipe = enableSwipe;
    }

    public boolean getEnableInsert() {
        return enableInsert;
    }

    public void setEnableInsert(boolean enableInsert) {
        this.enableInsert = enableInsert;
    }

    public boolean getEnableTap() {
        return enableTap;
    }

    public void setEnableTap(boolean enableTap) {
        this.enableTap = enableTap;
    }

    public void setAmountOption(List<String> amountOption) {
        this.amountOption = amountOption;
    }

    public List<String> getAmountOption() {
        return amountOption;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, String> getInformations() {
        return informations;
    }

    public void setInformations(Map<String, String> informations) {
        this.informations = informations;
    }
}
