package ir.maktab.finalproject.onlinequizapplication.mapper;

import ir.maktab.finalproject.onlinequizapplication.dto.PersonRegisterDTO;
import ir.maktab.finalproject.onlinequizapplication.model.Person;


public class PersonRegisterDtoToPersonMapper {
    public static Person mapper(PersonRegisterDTO personRegisterDTO) {
        return new Person(
                null,
                personRegisterDTO.getFirstName(),
                personRegisterDTO.getLastName(),
                personRegisterDTO.getEducation(),
                personRegisterDTO.getEmail(),
                personRegisterDTO.getHomeNumber(),
                personRegisterDTO.getCellPhoneNumber(),
                personRegisterDTO.getAddress()
        );
    }
}
