package org.example;

import org.example.dto.User;
import org.example.dto.UserStatus;
import org.example.repository.UserRepository;
import org.example.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        AtmOperationsService atmOperationsService = new AtmOperationsServiceIml();
        CoreService coreService = new CoreSeviceImpl();
        User user = new User();
        user.setName("Osahon Odia");
        user.setAccountBalance("1000");
        user.setPin("1234");
        user.setStatus(UserStatus.ACTIVE.name());

        User userTwo = new User();
        userTwo.setName("Emmanuel Odia");
        userTwo.setAccountBalance("500");
        userTwo.setPin("1235");
        userTwo.setStatus(UserStatus.ACTIVE.name());

        UserRepository userRepository = new UserRepository();
        String resp = userRepository.addUser(user);
        userRepository.addUser(userTwo);
        System.out.println(resp);
        System.out.println("user "+user.toString());
        userRepository.printDB();

        Scanner sc = new Scanner(System.in);
        boolean nameLoop = true;
        User gottenUser = new User();
        while(nameLoop) {
            System.out.println("Kindly enter your name");
            String name = sc.nextLine();
            gottenUser = userRepository.getUser(name);
            if (Objects.isNull(gottenUser)) {
                System.out.println("No such user exists, please try again");
            }else{
                nameLoop = false;
            }
        }
        boolean pinLoop = true;
        int counter = 0;
        while(pinLoop) {
            System.out.println("Kindly enter your pin");
            String pinEntry = sc.nextLine();
            if (atmOperationsService.checkAtmPin(pinEntry, gottenUser)) {
                pinLoop = false;
                System.out.println("correct pin");
                counter = 0;
                String cont = "yes";
                while(cont.equals("yes")) {
                    coreService.coreImpl(gottenUser);
                    System.out.println("DO YOU WANT TO PERFORM ANOTHER TRANSACTION? Y OR N");
                    String contAns = sc.nextLine();
                    if(contAns.equalsIgnoreCase("N")){
                        cont = "no";
                    }
                }
            }else{
                System.out.println("Wrong pin, please try again");
                counter++;
                if(counter == 4){
                    atmOperationsService.blockAccount(gottenUser);
                    System.out.println("account blocked, wrong pin entered too many times");
                    pinLoop = false;
                }
            }
        }
    }
}