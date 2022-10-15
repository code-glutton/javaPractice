package org.example.dto;

public class User {
    private static int id = 0;
    private int userid;
    private String name;
    private String pin;
    private String accountBalance;
    private String status;

    {
        userid = id;
        id++;
    }

    public int getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", name='" + name + '\'' +
                ", pin='" + pin + '\'' +
                ", accountBalance='" + accountBalance + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
