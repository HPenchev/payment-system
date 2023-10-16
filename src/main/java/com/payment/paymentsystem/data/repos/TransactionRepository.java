package com.payment.paymentsystem.data.repos;

import com.payment.paymentsystem.data.models.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TransactionRepository extends CrudRepository<Transaction, UUID> {
}
