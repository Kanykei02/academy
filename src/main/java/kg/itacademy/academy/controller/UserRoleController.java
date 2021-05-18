package kg.itacademy.academy.controller;

import kg.itacademy.academy.entity.UserRole;
import kg.itacademy.academy.model.RoleModel;
import kg.itacademy.academy.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-role")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping
    public UserRole save(@RequestBody RoleModel userRoleModel){
        return userRoleService.save(userRoleModel);
    }
}
