package org.example.repository;

import org.example.dto.User;

import java.util.ArrayList;
import java.util.HashSet;

public class UserRepository {
    private static ArrayList<User> userDataBase = new ArrayList<>();
    private static HashSet<String> userNames = new HashSet<>();

    public String addUser(User user){
        String returnString = "";
        if(getUserNames().contains(user.getName())){
            returnString = "User already exists.";
        }else{
            getUserNames().add(user.getName());
            getUserDataBase().add(user);
            returnString = "User added successfully.";
            System.out.println(getUserDataBase());
        }
        return returnString;
    }
    public User getUser(String name){
        for (User user:getUserDataBase()) {
            if(user.getName().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }

    public void printDB(){
        System.out.println(getUserDataBase());
    }

    private ArrayList<User> getUserDataBase() {
        return userDataBase;
    }

    private HashSet<String> getUserNames() {
        return userNames;
    }
}
