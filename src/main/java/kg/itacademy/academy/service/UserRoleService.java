package kg.itacademy.academy.service;

import kg.itacademy.academy.entity.UserRole;
import kg.itacademy.academy.model.RoleModel;

public interface UserRoleService {
    UserRole save(UserRole userRole);
    UserRole save(RoleModel userRoleModel);
    UserRole findById(Long id);
}
