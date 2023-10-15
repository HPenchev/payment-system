package com.payment.paymentsystem.services;

import com.payment.paymentsystem.data.dto.UserDTO;
import com.payment.paymentsystem.data.models.Admin;
import com.payment.paymentsystem.data.models.Merchant;
import com.payment.paymentsystem.data.models.User;
import com.payment.paymentsystem.data.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Collection;

import static com.payment.paymentsystem.data.MerchantStatus.ACTIVE;
import static com.payment.paymentsystem.data.dto.UserRole.ADMIN;
import static com.payment.paymentsystem.data.dto.UserRole.MERCHANT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserValidator userValidator;
    @Mock
    private UserFactory userFactory;
    @InjectMocks
    private UserService userService;

    @Captor
    ArgumentCaptor<Collection<User>> argCaptor;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertUsers() {
        UserDTO adminDTO = new UserDTO("admin", "admin@test.ts", null, null, ADMIN);
        UserDTO merchantDTO =
                new UserDTO("merchant", "merchant@test.ts", "test description", ACTIVE, MERCHANT);

        Collection<UserDTO> users = Arrays.asList(adminDTO,merchantDTO);

        User admin = new Admin("admin", "admin@test.ts");
        User merchant = new Merchant("merchant", "merchant@test.ts", "test description", ACTIVE);

        Mockito.when(userValidator.extractValidUsers(users)).thenReturn(users);
        Mockito.when(userFactory.createUser(adminDTO)).thenReturn(admin);
        Mockito.when(userFactory.createUser(merchantDTO)).thenReturn(merchant);

        assertEquals(users, userService.insertUsers(users));

        Mockito.verify(userRepository).saveAll(argCaptor.capture());
        Collection<User> savedUsers = argCaptor.getValue();
        assert (savedUsers.contains(admin));
        assert (savedUsers.contains(merchant));
    }
}
