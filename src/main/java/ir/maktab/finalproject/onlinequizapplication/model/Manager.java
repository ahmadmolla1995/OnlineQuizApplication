package ir.maktab.finalproject.onlinequizapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Manager extends Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long managerCode;


    public Manager() {}

    public Manager(Long id, String firstName, String lastName, String education, String email, String homeNumber, String cellPhoneNumber, String address, Long managerCode) {
        super(id, firstName, lastName, education, email, homeNumber, cellPhoneNumber, address);
        this.managerCode = managerCode;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(Long managerCode) {
        this.managerCode = managerCode;
    }
}

