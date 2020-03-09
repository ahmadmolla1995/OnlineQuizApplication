package ir.maktab.finalproject.onlinequizapplication.dto;

import java.util.Set;


public class PersonSignInCompletionDTO {
    private Long personID;
    private String username;
    private String firstName;
    private String lastName;
    private Set<RoleDTO> roles;


    public PersonSignInCompletionDTO(Long personID, String username, String firstName, String lastName, Set<RoleDTO> roles) {
        this.personID = personID;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public Long getPersonID() {
        return personID;
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

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
