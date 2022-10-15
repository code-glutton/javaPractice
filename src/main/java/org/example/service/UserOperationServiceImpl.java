package org.example.service;

import org.example.dto.User;
import org.example.dto.UserStatus;
import org.example.repository.UserRepository;

import java.util.Objects;

public class UserOperationServiceImpl implements UserOperationService{
    UserRepository userRepository = new UserRepository();
    @Override
    public String checkAccountBalance(User user) {
        User usergotten = userRepository.getUser(user.getName());
        if(Objects.isNull(usergotten)){
            return "No such User exists.";
        }
        return usergotten.getAccountBalance();
    }

    @Override
    public String changePin(String newPin, User user) {
        User usergotten = userRepository.getUser(user.getName());
        if(Objects.isNull(usergotten)){
            return "No such User exists.";
        }
        usergotten.setPin(newPin);
        return "pin changed";
    }

    @Override
    public String blockCard(String pin, User user) {
        User userGotten = userRepository.getUser(user.getName());
        if(!userGotten.getPin().equals(pin)){
            return "unable to block card";
        }
        userGotten.setStatus(UserStatus.BLOCKED.name());
        return "card blocked";
    }
}
