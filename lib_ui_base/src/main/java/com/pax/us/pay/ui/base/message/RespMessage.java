package com.pax.us.pay.ui.base.message;

public class RespMessage {
    private int resultCode;
    private String resultMessage;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMessage;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMessage = resultMsg;
    }

}
