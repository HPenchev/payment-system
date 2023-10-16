//package com.payment.paymentsystem.data.models;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//
//@Entity
//public class ChangeTransaction extends Transaction{
//    @ManyToOne
//    @JoinColumn(name="merchant_id", nullable=false)
//    Merchant merchant;
//
//    @JoinColumn(name = "authorise_transaction_id", referencedColumnName = "id")
//    AuthoriseTransaction authoriseTransaction;
//}
