package ir.maktab.finalproject.onlinequizapplication.dto;


public class PersonRegisterDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String education;
    private String homeNumber;
    private String cellPhoneNumber;
    private String email;
    private String address;
    private String roleType;


    public PersonRegisterDTO(String username, String password, String firstName, String lastName, String education, String homeNumber, String cellPhoneNumber, String email, String address, String roleType) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.education = education;
        this.homeNumber = homeNumber;
        this.cellPhoneNumber = cellPhoneNumber;
        this.email = email;
        this.address = address;
        this.roleType = roleType;
    }

    
    public String getUsername() {
        return username;
    }

    public String getPassword() { return password; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEducation() {
        return education;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getCellPhoneNumber() { return cellPhoneNumber; }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getRoleType() { return roleType; }
}

