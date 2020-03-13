package ir.maktab.finalproject.onlinequizapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class TeachingAssistant extends Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teachingAssistantCode;


    public TeachingAssistant() {
        super();
    }

    public TeachingAssistant(Long id, String firstName, String lastName, String education, String email, String homeNumber, String cellPhoneNumber, String address, Long teachingAssistantCode) {
        super(id, firstName, lastName, education, email, homeNumber, cellPhoneNumber, address);
        this.teachingAssistantCode = teachingAssistantCode;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeachingAssistantCode() {
        return teachingAssistantCode;
    }

    public void setTeachingAssistantCode(Long teachingAssistantCode) {
        this.teachingAssistantCode = teachingAssistantCode;
    }
}
