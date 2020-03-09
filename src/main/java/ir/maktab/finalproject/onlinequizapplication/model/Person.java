package ir.maktab.finalproject.onlinequizapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String firstName;
    protected String lastName;
    protected String education;
    protected String email;
    protected String homeNumber;
    protected String cellPhoneNumber;
    protected String address;

    @OneToOne(mappedBy = "person")
    protected Account account;


    public Person() {}

    public Person(Long id, String firstName, String lastName, String education, String email, String homeNumber, String cellPhoneNumber, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.education = education;
        this.email = email;
        this.homeNumber = homeNumber;
        this.cellPhoneNumber = cellPhoneNumber;
        this.address = address;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account getAccount() { return account; }

    public void setAccount(Account account) { this.account = account; }
}
