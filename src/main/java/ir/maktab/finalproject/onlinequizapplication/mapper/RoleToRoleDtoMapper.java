package ir.maktab.finalproject.onlinequizapplication.mapper;

import ir.maktab.finalproject.onlinequizapplication.dto.RoleDTO;
import ir.maktab.finalproject.onlinequizapplication.model.Role;


public class RoleToRoleDtoMapper {
    public static RoleDTO mapper(Role role) {
        return new RoleDTO(role.getRoleType().toString());
    }
}
