package com.pax.us.pay.ui.constant.entry.enumeration;

public class FSAAmountType {
    public static final String HEALTH_CARE_AMOUNT = "healthCareAmount";
    public static final String CLINIC_AMOUNT = "clinicAmount";
    public static final String PRESCRIPTION_AMOUNT = "prescriptionAmount";
    public static final String DENTAL_AMOUNT = "dentalAmount";
    public static final String VISION_AMOUNT = "visionAmount";
    public static final String COPAY_AMOUNT = "copayAmount";
    public static final String TRANSIT_AMOUNT = "transitAmount";

    public static String[] values() {
        return new String[]{HEALTH_CARE_AMOUNT, CLINIC_AMOUNT, PRESCRIPTION_AMOUNT, DENTAL_AMOUNT, VISION_AMOUNT, COPAY_AMOUNT, TRANSIT_AMOUNT};
    }

}
