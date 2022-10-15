package org.example.service;

import org.example.dto.User;
import org.example.dto.UserStatus;
import org.example.repository.UserRepository;

import java.util.Objects;

public class AtmOperationsServiceIml implements AtmOperationsService{
    UserRepository userRepository = new UserRepository();
    @Override
    public boolean checkAtmPin(String pin,User user) {
        if(user.getPin().equals(pin)){
            return true;
        }
        return false;
    }

    @Override
    public boolean doesUserExist(String name) {
        User user = userRepository.getUser(name);
        if(!Objects.isNull(user)){
            return true;
        }
        return false;
    }

    @Override
    public void blockAccount(User user) {
        User userGotten = userRepository.getUser(user.getName());
        userGotten.setStatus(UserStatus.BLOCKED.name());
    }
}
