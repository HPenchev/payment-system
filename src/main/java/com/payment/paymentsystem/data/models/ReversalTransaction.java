package com.payment.paymentsystem.data.models;

import com.payment.paymentsystem.data.TransactionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class ReversalTransaction extends Transaction {
    public ReversalTransaction() {
    }

    public ReversalTransaction(TransactionStatus status, AuthoriseTransaction authoriseTransaction) {
        super(status);
        this.authoriseTransaction = authoriseTransaction;
    }

    @OneToOne
    @JoinColumn(name = "authorise_transaction_id", referencedColumnName = "id")
    AuthoriseTransaction authoriseTransaction;
}
