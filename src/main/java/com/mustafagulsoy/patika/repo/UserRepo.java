package com.mustafagulsoy.patika.repo;

import com.mustafagulsoy.patika.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findUserById(Long id);
}
