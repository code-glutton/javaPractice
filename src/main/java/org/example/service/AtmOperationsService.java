package org.example.service;

import org.example.dto.User;

public interface AtmOperationsService {
    boolean checkAtmPin(String pin,User user);
    boolean doesUserExist(String name);
    void blockAccount(User user);
}
