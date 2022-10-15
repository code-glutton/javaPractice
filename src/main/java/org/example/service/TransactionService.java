package org.example.service;

import org.example.dto.User;

public interface TransactionService {
    String fundWithdrawal(String user, double amountToWithdraw);
    String fundTransfer(String senderUser, double amountToTransfer, String receiverUser);

}
