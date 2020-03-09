package ir.maktab.finalproject.onlinequizapplication.mapper;

import ir.maktab.finalproject.onlinequizapplication.dto.PersonSignInCompletionDTO;
import ir.maktab.finalproject.onlinequizapplication.model.Person;

import java.util.stream.Collectors;


public class PersonToPersonLoginCompletionDtoMapper {
    public static PersonSignInCompletionDTO mapper(Person person) {
        return new PersonSignInCompletionDTO(
                person.getId(),
                person.getAccount().getUsername(),
                person.getFirstName(),
                person.getLastName(),
                person.getAccount().getRoles().stream().map(RoleToRoleDtoMapper::mapper).collect(Collectors.toSet())
        );
    }
}

