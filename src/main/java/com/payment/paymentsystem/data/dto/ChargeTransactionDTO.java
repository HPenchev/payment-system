package com.payment.paymentsystem.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ChargeTransactionDTO extends TransactionDTO {
    public ChargeTransactionDTO(UUID authoriseTransactionId, String merchantEmail) {
        this.authoriseTransactionId = authoriseTransactionId;
        this.merchantEmail = merchantEmail;
    }

    private UUID id;

    @NotNull
    @NotEmpty
    private UUID authoriseTransactionId;

    @NotNull
    @NotEmpty
    private String merchantEmail;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAuthoriseTransactionId() {
        return authoriseTransactionId;
    }

    public String getMerchantEmail() {
        return merchantEmail;
    }


}
