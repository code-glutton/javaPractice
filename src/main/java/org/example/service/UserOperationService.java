package org.example.service;

import org.example.dto.User;

public interface UserOperationService {
    String checkAccountBalance(User user);
    String changePin(String newPin,User user);
    String blockCard(String pin, User user);
}
