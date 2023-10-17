package com.payment.paymentsystem.services;

import com.payment.paymentsystem.data.TransactionStatus;
import com.payment.paymentsystem.data.dto.UserDTO;
import com.payment.paymentsystem.data.dto.UserRole;
import com.payment.paymentsystem.data.models.AuthoriseTransaction;
import com.payment.paymentsystem.data.models.ChargeTransaction;
import com.payment.paymentsystem.data.models.Merchant;
import com.payment.paymentsystem.data.models.User;
import com.payment.paymentsystem.data.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final UserFactory userFactory;

    @Autowired
    public UserService(UserRepository userRepository, UserValidator userValidator, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.userFactory = userFactory;
    }

    public Collection<UserDTO> insertUsers(Collection<UserDTO> users) {
        Collection<UserDTO> validUsers = userValidator.extractValidUsers(users);
        Collection<User> usersToInsert = new HashSet<>();

        for (UserDTO user: validUsers) {
            usersToInsert.add(userFactory.createUser(user));
        }

        userRepository.saveAll(usersToInsert);
        return validUsers;
    }

    public Optional<UserDTO> getMerchantByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty() || !user.get().getClass().equals(Merchant.class)){
            return Optional.empty();
        }

        Merchant merchant = (Merchant)user.get();

        UserDTO result = new UserDTO(merchant.getName(),
                merchant.getEmail(),
                merchant.getDescription(),
                merchant.getStatus(),
                UserRole.MERCHANT);

        List<BigDecimal> amounts = merchant.getTransactions()
                .stream()
                .filter(t -> t.getStatus() == TransactionStatus.APPROVED)
                .map(ChargeTransaction::getAuthoriseTransaction)
                .map(AuthoriseTransaction::getAmount)
                .toList();

        BigDecimal totalAmount = BigDecimal.valueOf(0);

        for (BigDecimal amount: amounts) {
            totalAmount = totalAmount.add(amount);
        }

        result.setTotalTransactionAmount(totalAmount);

        return Optional.of(result);
    }
}
