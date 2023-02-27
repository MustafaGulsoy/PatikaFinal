package com.mustafagulsoy.patika.services.user;

import com.mustafagulsoy.patika.entity.Role;
import com.mustafagulsoy.patika.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User deleteUser(long id);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();

    User updateUser(User user);
}
