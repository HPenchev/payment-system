package com.payment.paymentsystem.controllers;

import com.payment.paymentsystem.data.dto.AuthoriseTransactionDTO;
import com.payment.paymentsystem.data.dto.ChargeTransactionDTO;
import com.payment.paymentsystem.data.dto.RefundTransactionDTO;
import com.payment.paymentsystem.data.dto.ReversalTransactionDTO;
import com.payment.paymentsystem.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(path = "/authorise")
    public AuthoriseTransactionDTO authoriseTransaction(@RequestBody AuthoriseTransactionDTO transactionDTO) {
        return transactionService.authoriseTransaction(transactionDTO);
    }

    @PostMapping(path = "/charge")
    public ChargeTransactionDTO charge(@RequestBody ChargeTransactionDTO transactionDTO) {
        return transactionService.processPayment(transactionDTO);
    }

    @PostMapping(path = "/refund")
    public RefundTransactionDTO charge(@RequestBody RefundTransactionDTO transactionDTO) {
        return transactionService.refundPayment(transactionDTO);
    }

    @PostMapping(path = "/reverse")
    public ReversalTransactionDTO reverse(@RequestBody ReversalTransactionDTO transactionDTO) {
        return transactionService.reverseTransaction(transactionDTO);
    }
}
