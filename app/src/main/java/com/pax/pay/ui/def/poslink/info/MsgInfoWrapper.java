package com.pax.pay.ui.def.poslink.info;

import androidx.annotation.RestrictTo;

import com.fasterxml.jackson.annotation.JsonProperty;

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class MsgInfoWrapper {
    @JsonProperty(value = "index")
    private String index;
    @JsonProperty(value = "MsgInfo")
    private MsgInfo msgInfo;

    public MsgInfoWrapper() {
    }

    public MsgInfoWrapper(String index, MsgInfo msgInfo) {
        this.index = index;
        this.msgInfo = msgInfo;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public MsgInfo getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(MsgInfo msgInfo) {
        this.msgInfo = msgInfo;
    }
}
