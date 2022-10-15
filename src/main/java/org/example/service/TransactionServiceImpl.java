package org.example.service;

import org.example.dto.User;
import org.example.repository.UserRepository;

import java.util.Objects;

public class TransactionServiceImpl implements TransactionService{
    UserRepository userRepository = new UserRepository();
    @Override
    public String fundWithdrawal(String user, double amountToWithdraw) {
        User userData = userRepository.getUser(user);
        double amountToDb = Double.parseDouble(userData.getAccountBalance());
        if(amountToDb < amountToWithdraw){
            return "Insufficient Funds.";
        }
        double remainder = amountToDb - amountToWithdraw;
        userData.setAccountBalance(remainder+"");
        return "Withdrawal Successful, your account balance is "+remainder;
    }

    @Override
    public String fundTransfer(String senderUser, double amountToTransfer, String receiverUser) {
        User userData = userRepository.getUser(senderUser);
        User receiverUserData = userRepository.getUser(senderUser);
        double amountToDb = Double.parseDouble(userData.getAccountBalance());
        if(amountToDb < amountToTransfer){
            return "Insufficient Funds.";
        }

        if(Objects.isNull(receiverUser)){
            return "Invalid receiver account";
        }

        double remainder = amountToDb - amountToTransfer;
        double receiverBal = amountToTransfer + Double.parseDouble(receiverUserData.getAccountBalance());
        userData.setAccountBalance(remainder+"");
        receiverUserData.setAccountBalance(receiverBal+"");
        return "Transfer Successful, your account balance is "+remainder;
    }

}
