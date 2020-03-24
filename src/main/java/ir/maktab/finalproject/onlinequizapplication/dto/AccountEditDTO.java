package ir.maktab.finalproject.onlinequizapplication.dto;

import ir.maktab.finalproject.onlinequizapplication.enumeration.AccountStatus;


public final class AccountEditDTO {
    private Long accountID;
    private String username;
    private String firstName;
    private String lastName;
    private String role;
    private AccountStatus accountStatus;
    private String address;
    private String email;
    private String homeNumber;
    private String cellPhoneNumber;


    public AccountEditDTO(Long accountID, String username, String firstName, String lastName, String role, AccountStatus accountStatus, String address, String email, String homeNumber, String cellPhoneNumber) {
        this.accountID = accountID;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.accountStatus = accountStatus;
        this.address = address;
        this.email = email;
        this.homeNumber = homeNumber;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public Long getAccountID() {
        return accountID;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }
}
