package com.payment.paymentsystem.data.dto;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class RefundTransactionDTO extends TransactionDTO {
    @NotNull
    private UUID chargeTransactionId;

    public UUID getChargeTransactionId() {
        return chargeTransactionId;
    }

    public void setChargeTransactionId(UUID chargeTransactionId) {
        this.chargeTransactionId = chargeTransactionId;
    }
}
