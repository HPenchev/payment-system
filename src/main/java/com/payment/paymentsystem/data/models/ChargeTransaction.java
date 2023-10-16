package com.payment.paymentsystem.data.models;

import com.payment.paymentsystem.data.TransactionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class ChargeTransaction extends Transaction{
    public ChargeTransaction() {
        super();
    }

    public ChargeTransaction(Merchant merchant, AuthoriseTransaction authoriseTransaction, TransactionStatus status) {
        super(status);
        this.merchant = merchant;
        this.authoriseTransaction = authoriseTransaction;
    }

    @ManyToOne
    @JoinColumn(name="merchant_id")
    Merchant merchant;

    @OneToOne
    @JoinColumn(name = "authorise_transaction_id", referencedColumnName = "id")
    AuthoriseTransaction authoriseTransaction;

    public AuthoriseTransaction getAuthoriseTransaction() {
        return authoriseTransaction;
    }
}
