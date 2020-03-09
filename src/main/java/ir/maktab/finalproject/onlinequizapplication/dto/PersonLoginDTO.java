package ir.maktab.finalproject.onlinequizapplication.dto;


public class PersonLoginDTO {
    private String username;
    private String password;


    public PersonLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
