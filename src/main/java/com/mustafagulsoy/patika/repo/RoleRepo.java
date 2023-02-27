package com.mustafagulsoy.patika.repo;

import com.mustafagulsoy.patika.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo  extends JpaRepository<Role,Long> {

    Role findByName(String name);
    Role findRoleById(Long id);

}
