package org.example.service;

import org.example.dto.User;

import java.util.Scanner;

public class CoreSeviceImpl implements CoreService{
    AtmOperationsService atmOperationsService = new AtmOperationsServiceIml();
    TransactionService transactionService = new TransactionServiceImpl();

    UserOperationService userOperationService = new UserOperationServiceImpl();
    @Override
    public void coreImpl(User userName) {
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Please select an operation by entering the operation number");
            System.out.println("1. Withdrawal");
            System.out.println("2. Check Account Balance");
            System.out.println("3. Transfer");
            System.out.println("4. change Pin");
            System.out.println("5. Block card");
            String operationSelect = sc.nextLine();
            switch(operationSelect){
                case "1":
                    System.out.println("Please enter the amount to withdraw");
                    String amountToWithdraw = sc.nextLine();
                    String resp = transactionService.fundWithdrawal(userName.getName(), Double.parseDouble(amountToWithdraw));
                    System.out.println(resp);
                    break;
                case "2":
                    String balanceResp = userOperationService.checkAccountBalance(userName);
                    System.out.println(balanceResp);
                    break;
                case "3":
                    System.out.println("Please enter the amount to be transferred");
                    String amountToTransfer = sc.nextLine();
                    System.out.println("Please enter the recipient name.");
                    String receiverUser = sc.nextLine();
                    String transferresp = transactionService.fundTransfer(userName.getName(), Double.parseDouble(amountToTransfer),receiverUser);
                    System.out.println(transferresp);
                    break;
                case "4":
                    System.out.println("Please enter the new pin");
                    String pin = sc.nextLine();
                    String changePin = userOperationService.changePin(pin,userName);
                    System.out.println(changePin);
                    break;
                case "5":
                    System.out.println("Please enter the new pin");
                    String blockpin = sc.nextLine();
                    String pinBlocked = userOperationService.blockCard(blockpin,userName);
                    System.out.println(pinBlocked);
                    break;
                default:
                    System.out.println("invalid input");
            }
        }catch(Exception ex){

        }
    }
}
