package ir.maktab.finalproject.onlinequizapplication.dto;


public class AccountSearchDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String role;
    private String accountStatus;


    public AccountSearchDTO(String username, String firstName, String lastName, String address, String role, String accountStatus) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.role = role;
        this.accountStatus = accountStatus;
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

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }

    public String getAccountStatus() {
        return accountStatus;
    }
}
