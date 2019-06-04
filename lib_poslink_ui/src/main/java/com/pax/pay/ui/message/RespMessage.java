package com.pax.pay.ui.message;

public class RespMessage {
    private String reqAction;
    private String resultCode;
    private String resultMessage;

    public String getReqAction() {
        return reqAction;
    }

    public void setReqAction(String reqAction) {
        this.reqAction = reqAction;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMessage;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMessage = resultMsg;
    }

}
