package org.example.service;

import org.example.dto.User;
import org.example.dto.UserStatus;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    private User user;

    private UserRepository userRepository;

    private TransactionService transactionService;

    @BeforeEach
    void beforeEachMethod(){
        user = new User();
        user.setName("Osahon Odia");
        user.setAccountBalance("1000");
        user.setPin("1234");
        user.setStatus(UserStatus.ACTIVE.name());

        userRepository = new UserRepository();
        String resp = userRepository.addUser(user);

        transactionService = new TransactionServiceImpl();

    }

    @Test
    void fundWithdrawal() {
        String user = "Osahon Odia";
        double amountToWithdraw = 120;
        String result  = transactionService.fundWithdrawal(user, amountToWithdraw);
        boolean gottenResult = result.contains("Successful");
        assertTrue(gottenResult);
    }

    @Test
    void fundTransfer() {
        User userSecond = new User();
        userSecond.setName("Emmanuel Odia");
        userSecond.setAccountBalance("1000");
        userSecond.setPin("1234");
        userSecond.setStatus(UserStatus.ACTIVE.name());
        userRepository.addUser(userSecond);

        String result = transactionService.fundTransfer("Osahon odia",123,"Emmanuel Odia");
        boolean gottenResult = result.contains("Successful");
        assertTrue(gottenResult);
    }
}