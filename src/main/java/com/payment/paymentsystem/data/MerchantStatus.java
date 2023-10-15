package com.payment.paymentsystem.data;

public enum MerchantStatus {
    ACTIVE, INACTIVE;

    public static MerchantStatus fromString(String text) {
        for (MerchantStatus status : MerchantStatus.values()) {
            if (status.name().equalsIgnoreCase(text)) {
                return status;
            }
        }

        return null;
    }
}
