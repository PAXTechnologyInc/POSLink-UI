package com.pax.pay.ui.def.utils;

public enum HealthCareType {
    Clinical("Clinical"),
    Dental("Dental"),
    Copay("Co-Payment"),
    RX("Prescription/Rx"), //Prescription
    Vision("Vision"),
    OTC("OTC");

    private final String detail;

    HealthCareType(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
}
