package com.pax.us.pay.ui.core;

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

    @Override
    public String toString() {
        return "code:" + resultCode + " msg:" + resultMessage + "\n";
    }
}
