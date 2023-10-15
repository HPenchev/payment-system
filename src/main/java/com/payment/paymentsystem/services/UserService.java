package com.payment.paymentsystem.services;

import com.payment.paymentsystem.data.dto.UserDTO;
import com.payment.paymentsystem.data.models.User;
import com.payment.paymentsystem.data.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

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

//    public
//    public Collection<UserDTO> importUsers(MultipartFile file)
    public Collection<UserDTO> insertUsers(Collection<UserDTO> users) {
        Collection<UserDTO> validUsers = userValidator.extractValidUsers(users);
        Collection<User> usersToInsert = new HashSet<>();

        for (UserDTO user: validUsers) {
            usersToInsert.add(userFactory.createUser(user));
        }

        userRepository.saveAll(usersToInsert);
        return validUsers;
    }
}
