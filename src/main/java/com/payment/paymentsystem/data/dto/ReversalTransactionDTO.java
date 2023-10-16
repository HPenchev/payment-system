package com.payment.paymentsystem.data.dto;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ReversalTransactionDTO extends TransactionDTO {
    @NotNull
    private UUID authoriseTransactionId;

    public UUID getAuthoriseTransactionId() {
        return authoriseTransactionId;
    }
}
