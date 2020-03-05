package ir.maktab.finalproject.onlinequizapplication.mapper;

import ir.maktab.finalproject.onlinequizapplication.dto.PersonDTO;
import ir.maktab.finalproject.onlinequizapplication.model.Person;


public class PersonDtoToPersonMapper {
    public static Person mapper(PersonDTO personDTO, Person person) {
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setNationalCode(personDTO.getNationalCode());
        person.setBirthDate(personDTO.getBirthDate());
        person.setFatherName(personDTO.getFatherName());
        person.setEducation(personDTO.getEducation());
        person.setEmail(personDTO.getEmail());
        person.setAddress(personDTO.getAddress());
        person.setHomeNumber(personDTO.getHomeNumber());
        person.setCellPhoneNumber(personDTO.getCellPhoneNumber());

        return person;
    }
}
