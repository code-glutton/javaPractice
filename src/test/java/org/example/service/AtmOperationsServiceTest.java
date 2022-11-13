package org.example.service;

import org.example.dto.User;
import org.example.dto.UserStatus;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;







class AtmOperationsServiceTest {
    private User user;

    private AtmOperationsService atmOperationsService;


    private UserRepository userRepository;
    @BeforeEach
    void beforeEachMethod(){
        user = new User();
        user.setName("Osahon Odia");
        user.setAccountBalance("1000");
        user.setPin("1234");
        user.setStatus(UserStatus.ACTIVE.name());

       userRepository = new UserRepository();
        String resp = userRepository.addUser(user);

        atmOperationsService = new AtmOperationsServiceIml();
    }

    @Test
    void checkAtmPin() {
        AtmOperationsService atmOperationsService = new AtmOperationsServiceIml();

        boolean gottenValue = atmOperationsService.checkAtmPin("1234",user);
        boolean expectedValue = true;

        assertEquals(expectedValue,gottenValue);
    }

    @Test
    void doesUserExist() {
        boolean gottenValue = atmOperationsService.doesUserExist("Osahon odia");
        boolean expectedValue = true;

        assertEquals(expectedValue,gottenValue);
    }

    @Test
    void blockAccount() {
        atmOperationsService.blockAccount(user);
        boolean gottenValue = userRepository.getUser(user.getName()).getStatus().equalsIgnoreCase("blocked");
        boolean expectedValue = false;

        assertEquals(expectedValue,gottenValue);
    }
}