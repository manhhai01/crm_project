package com.cybersoft.crm.service;

import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.repository.RoleRepository;

import java.util.List;

public class RoleService {

    private RoleRepository rolesRepository = new RoleRepository();

    public List<RoleModel> getAllRoles() {
        return rolesRepository.getRoles();
    }

    public boolean deleteRoleById(int id) {
        int result = rolesRepository.deleteRoleById(id);
        return result > 0;
    }

    public boolean insertRole(String name, String description) {
        int result = rolesRepository.insertRole(name, description);
        return result > 0;
    }

    public boolean updateRole(RoleModel role) {
        int result = rolesRepository.updateRole(role);
        return result > 0;
    }
}
