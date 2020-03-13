package ir.maktab.finalproject.onlinequizapplication.mapper;

import ir.maktab.finalproject.onlinequizapplication.dto.PersonRegisterDTO;
import ir.maktab.finalproject.onlinequizapplication.enumeration.RoleType;
import ir.maktab.finalproject.onlinequizapplication.exception.RoleNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.model.*;


public class PersonRegisterDtoToPersonMapper {
    public static Person mapper(PersonRegisterDTO personRegisterDTO) throws RoleNotFoundException {
        RoleType roleType = RoleType.valueOf(personRegisterDTO.getRoleType());

        if (roleType.equals(RoleType.ROLE_STUDENT))
            return new Student(
                    null,
                    personRegisterDTO.getFirstName(),
                    personRegisterDTO.getLastName(),
                    personRegisterDTO.getEducation(),
                    personRegisterDTO.getEmail(),
                    personRegisterDTO.getHomeNumber(),
                    personRegisterDTO.getCellPhoneNumber(),
                    personRegisterDTO.getAddress(),
                    Long.parseLong("810193489")
            );

        else if (roleType.equals(RoleType.ROLE_MANAGER))
            return new Manager(
                    null,
                    personRegisterDTO.getFirstName(),
                    personRegisterDTO.getLastName(),
                    personRegisterDTO.getEducation(),
                    personRegisterDTO.getEmail(),
                    personRegisterDTO.getHomeNumber(),
                    personRegisterDTO.getCellPhoneNumber(),
                    personRegisterDTO.getAddress(),
                    Long.parseLong("810193489")
            );

        else if (roleType.equals(RoleType.ROLE_TEACHER))
            return new Teacher(
                    null,
                    personRegisterDTO.getFirstName(),
                    personRegisterDTO.getLastName(),
                    personRegisterDTO.getEducation(),
                    personRegisterDTO.getEmail(),
                    personRegisterDTO.getHomeNumber(),
                    personRegisterDTO.getCellPhoneNumber(),
                    personRegisterDTO.getAddress(),
                    Long.parseLong("810193489")
            );

        else if (roleType.equals(RoleType.ROLE_TEACHING_ASSISTANT))
            return new TeachingAssistant(
                    null,
                    personRegisterDTO.getFirstName(),
                    personRegisterDTO.getLastName(),
                    personRegisterDTO.getEducation(),
                    personRegisterDTO.getEmail(),
                    personRegisterDTO.getHomeNumber(),
                    personRegisterDTO.getCellPhoneNumber(),
                    personRegisterDTO.getAddress(),
                    Long.parseLong("810193489")
            );

        else
            throw new RoleNotFoundException("Role not found in database!");
    }
}
