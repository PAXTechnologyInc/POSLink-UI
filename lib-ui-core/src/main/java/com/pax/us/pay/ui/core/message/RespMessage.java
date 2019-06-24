package com.pax.us.pay.ui.core.message;

public class RespMessage {
    private String resultCode;
    private String resultMessage;

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

    @Override
    public String toString() {
        return "code:" + resultCode + " msg:" + resultMessage + "\n";
    }
}
