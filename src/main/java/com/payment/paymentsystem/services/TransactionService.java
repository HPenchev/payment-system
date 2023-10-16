package com.payment.paymentsystem.services;

import com.payment.paymentsystem.data.TransactionStatus;
import com.payment.paymentsystem.data.dto.AuthoriseTransactionDTO;
import com.payment.paymentsystem.data.dto.ChargeTransactionDTO;
import com.payment.paymentsystem.data.dto.RefundTransactionDTO;
import com.payment.paymentsystem.data.dto.ReversalTransactionDTO;
import com.payment.paymentsystem.data.models.*;
import com.payment.paymentsystem.data.repos.TransactionRepository;
import com.payment.paymentsystem.data.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

import static com.payment.paymentsystem.data.MerchantStatus.INACTIVE;
import static com.payment.paymentsystem.data.TransactionStatus.*;

@Service
public class TransactionService {
    Logger logger = LoggerFactory.getLogger(UserValidator.class);

    TransactionRepository transactionRepo;
    UserRepository userRepo;

    @Autowired
    public TransactionService(TransactionRepository transactionRepo, UserRepository userRepo) {
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
    }

    public AuthoriseTransactionDTO authoriseTransaction(AuthoriseTransactionDTO transactionDTO) {
        transactionDTO.setId(transactionRepo.save(new AuthoriseTransaction(transactionDTO.getAmount(),
             transactionDTO.getCustomerEmail(),
             transactionDTO.getCustomerPhone(),
                TransactionStatus.APPROVED)).getId());

        transactionDTO.setStatus(APPROVED);
        return transactionDTO;
    }

   public ChargeTransactionDTO processPayment(ChargeTransactionDTO transactionDTO) {
       Transaction transactionFromBase =
               transactionRepo.findById(transactionDTO.getAuthoriseTransactionId()).orElse(null);
        ChargeTransaction chargeTransaction = createChargeTransaction(transactionDTO, transactionFromBase);

        if(chargeTransaction.getStatus() == APPROVED) {
            ((AuthoriseTransaction)transactionFromBase).setChargeTransaction(chargeTransaction);
        }

        ChargeTransaction newTransaction = transactionRepo.save(chargeTransaction);
        transactionRepo.save(transactionFromBase);
        transactionDTO.setStatus(newTransaction.getStatus());
        transactionDTO.setId(newTransaction.getId());

        return transactionDTO;
   }

   public RefundTransactionDTO refundPayment(RefundTransactionDTO transactionDTO) {
        Transaction transactionFromBase =
                transactionRepo.findById(transactionDTO.getChargeTransactionId()).orElse(null);
        RefundTransaction refundTransaction = createRefundTransaction(
               transactionDTO,
                transactionFromBase,
                transactionDTO.getChargeTransactionId()
               );

        if (refundTransaction.getStatus() == APPROVED) {
            transactionFromBase.setStatus(REFUNDED);
            transactionRepo.save(transactionFromBase);
        }

        transactionRepo.save(refundTransaction);
        transactionDTO.setStatus(refundTransaction.getStatus());

        return transactionDTO;
   }

   public ReversalTransactionDTO reverseTransaction(ReversalTransactionDTO transactionDTO) {
       Transaction transactionFromBase =
               transactionRepo.findById(transactionDTO.getAuthoriseTransactionId()).orElse(null);

       ReversalTransaction reversalTransaction =
               createReversalTransaction(transactionDTO,
                       transactionFromBase,
                       transactionDTO.getAuthoriseTransactionId());

       TransactionStatus reversalTransactionStatus = reversalTransaction.getStatus();

       if(reversalTransactionStatus == APPROVED) {
           transactionFromBase.setStatus(REVERSED);
       }

       transactionRepo.saveAll(Arrays.asList(reversalTransaction, transactionFromBase));

       transactionDTO.setStatus(reversalTransactionStatus);

       return transactionDTO;
   }

   private ChargeTransaction createChargeTransaction(
           ChargeTransactionDTO chargeTransaction,
           Transaction transactionFromBase) {
       String merchantEmail = chargeTransaction.getMerchantEmail();
       UUID authoriseTransactionID = chargeTransaction.getAuthoriseTransactionId();

       User user = userRepo.findByEmail(merchantEmail).orElse(null);

       Merchant merchant = null;
       AuthoriseTransaction authoriseTransaction = null;
       TransactionStatus status;

       if (user == null || !user.getClass().equals(Merchant.class) || ((Merchant) user).getStatus() == INACTIVE) {
           logger.error("Charge transaction for authorisation " + authoriseTransactionID + " and merchant " +  merchantEmail +
                   " has been stopped because no active merchant is present");
           status = ERROR;
       } else if (transactionFromBase == null
               || !transactionFromBase.getClass().equals(AuthoriseTransaction.class)
               || transactionFromBase.getStatus() != APPROVED) {
           logger.error("Charge transaction for authorisation " + authoriseTransactionID + " and merchant " +  merchantEmail +
                   " has been stopped because no approved authorisation is present");
           status = ERROR;
       } else {
           status = APPROVED;
       }

       if (user!= null && user.getClass().equals(Merchant.class)) {
           merchant = (Merchant) user;
       }

       if (transactionFromBase != null && transactionFromBase.getClass().equals(AuthoriseTransaction.class)) {
           authoriseTransaction = (AuthoriseTransaction) transactionFromBase;
       }

       return new ChargeTransaction(merchant, authoriseTransaction, status);
   }

   private RefundTransaction createRefundTransaction(
           RefundTransactionDTO refundTransaction,
           Transaction transactionFromBase,
           UUID chargeTransactionId) {

        TransactionStatus status = null;
        ChargeTransaction chargeTransaction = null;

        if(transactionFromBase == null
                || !transactionFromBase.getClass().equals(ChargeTransaction.class)
                || transactionFromBase.getStatus() != APPROVED) {
            logger.error("No charge transaction " + chargeTransactionId);
            status = ERROR;
        } else {
            status = APPROVED;
        }

       if (transactionFromBase != null && transactionFromBase.getClass().equals(ChargeTransaction.class)) {
           chargeTransaction = (ChargeTransaction) transactionFromBase;
       }

       return new RefundTransaction(status, chargeTransaction);
   }

   private ReversalTransaction createReversalTransaction(
           ReversalTransactionDTO transactionDTO,
           Transaction transactionFromBase,
           UUID authoriseTransactionId) {
       TransactionStatus status = null;
       AuthoriseTransaction authoriseTransaction = null;

       if(transactionFromBase == null
               || !transactionFromBase.getClass().equals(AuthoriseTransaction.class)
               || transactionFromBase.getStatus() != APPROVED) {
           logger.error("No valid authorise transaction " + authoriseTransactionId);
           status = ERROR;
       } else if (((AuthoriseTransaction)transactionFromBase).getChargeTransaction() != null) {
           logger.error("Authorisation already charged " + authoriseTransactionId);
            status = ERROR;
       }
       else {
           status = APPROVED;
       }

       if (transactionFromBase != null && transactionFromBase.getClass().equals(AuthoriseTransaction.class)) {
           authoriseTransaction = (AuthoriseTransaction) transactionFromBase;
       }

       return new ReversalTransaction(status, authoriseTransaction);
   }
}
