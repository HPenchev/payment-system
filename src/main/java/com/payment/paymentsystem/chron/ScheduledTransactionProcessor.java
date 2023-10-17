package com.payment.paymentsystem.chron;

import com.payment.paymentsystem.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTransactionProcessor {

    TransactionService transactionService;

    @Autowired
    public ScheduledTransactionProcessor(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

   @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.HOURS)
    public void deleteOldTransactions() {
       transactionService.deleteOldTransactions(new Date(System.currentTimeMillis() - 3600 * 1000));
    }
}
