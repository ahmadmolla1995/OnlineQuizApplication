package ir.maktab.finalproject.onlinequizapplication.dto;

import java.util.Date;


public class PersonDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String nationalCode;
    private String education;
    private String homeNumber;
    private String cellPhoneNumber;
    private String email;
    private String address;
    private Date birthDate;


    public PersonDTO(String username, String firstName, String lastName, String fatherName, String nationalCode, String education, String homeNumber, String cellPhoneNumber, String email, String address, Date birthDate) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.nationalCode = nationalCode;
        this.education = education;
        this.homeNumber = homeNumber;
        this.cellPhoneNumber = cellPhoneNumber;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
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

    public String getFatherName() {
        return fatherName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public String getEducation() {
        return education;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthDate() { return birthDate; }

    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
}

