package com.payment.paymentsystem.data.dto;

import com.payment.paymentsystem.data.TransactionStatus;

public abstract class TransactionDTO {
    private TransactionStatus status;

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
