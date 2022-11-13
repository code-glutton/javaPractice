package org.example.service;

import org.example.dto.User;
import org.example.dto.UserStatus;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserOperationServiceTest {

    private User user;

    private UserRepository userRepository;

    private UserOperationService userOperationService;

    @BeforeEach
    void beforeEachMethod(){
        user = new User();
        user.setName("Osahon Odia");
        user.setAccountBalance("1000");
        user.setPin("1234");
        user.setStatus(UserStatus.ACTIVE.name());

        userRepository = new UserRepository();
        String resp = userRepository.addUser(user);
        userOperationService = new UserOperationServiceImpl();
    }

    @Test
    void checkAccountBalance() {
        String response = userOperationService.checkAccountBalance(user);
        boolean gottenResult = response.contains("exists");
        assertFalse(gottenResult);
    }

    @Test
    void changePin() {
        String response = userOperationService.changePin("2341",user);
        boolean gottenResult = response.contains("changed");
        assertTrue(gottenResult);
    }

    @Test
    void blockCard() {
        System.out.println(user.getPin());
        String response = userOperationService.blockCard("1234",user);
        System.out.println(response);
        boolean gottenResult = response.contains("blocked");
        assertTrue(gottenResult);
    }
}