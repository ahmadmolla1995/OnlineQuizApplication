package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.enumeration.RoleType;
import ir.maktab.finalproject.onlinequizapplication.model.Role;
import ir.maktab.finalproject.onlinequizapplication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleService {
    @Autowired
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createNewRole(RoleType roleType) {
        return roleRepository.save(new Role(null, roleType));
    }
}
