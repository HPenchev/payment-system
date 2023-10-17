package com.payment.paymentsystem.data.models;

import com.payment.paymentsystem.data.TransactionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class RefundTransaction extends Transaction{
    @OneToOne
    @JoinColumn(name = "change_transaction_id", referencedColumnName = "id")
    ChargeTransaction chargeTransaction;

    public RefundTransaction() {
        super();
    }

    public RefundTransaction(TransactionStatus status, ChargeTransaction chargeTransaction) {
        super(status);
        this.chargeTransaction = chargeTransaction;
    }

    public ChargeTransaction getChargeTransaction() {
        return chargeTransaction;
    }
}
