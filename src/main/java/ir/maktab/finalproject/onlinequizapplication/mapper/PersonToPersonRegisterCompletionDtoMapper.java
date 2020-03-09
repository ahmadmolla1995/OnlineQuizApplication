package ir.maktab.finalproject.onlinequizapplication.mapper;

import ir.maktab.finalproject.onlinequizapplication.dto.PersonRegisterCompletionDTO;
import ir.maktab.finalproject.onlinequizapplication.model.Person;


public class PersonToPersonRegisterCompletionDtoMapper {
    public static PersonRegisterCompletionDTO mapper(Person person) {
        return new PersonRegisterCompletionDTO(person.getId());
    }
}
