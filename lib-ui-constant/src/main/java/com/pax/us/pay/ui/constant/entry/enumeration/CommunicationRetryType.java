package com.pax.us.pay.ui.constant.entry.enumeration;

public class CommunicationRetryType {
    public static final String ONLINE_RETRY = "RETRY";
    public static final String OFFLINE = "OFFLINE";

    public static String[] values() {
        return new String[]{ONLINE_RETRY, OFFLINE};
    }

}
