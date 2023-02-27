package com.mustafagulsoy.patika.services.user;

import com.mustafagulsoy.patika.entity.Role;
import com.mustafagulsoy.patika.entity.User;
import com.mustafagulsoy.patika.repo.RoleRepo;
import com.mustafagulsoy.patika.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.error("User found in the database : {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepo.findUserById(user.getId()) != null) {
            log.info("This username is already using.");

            return null;
        } else {
            log.info("Saving new user to the database");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            return user;
        }
    }

    @Override
    public User deleteUser(long id) {

        User user = userRepo.findUserById(id);
        if (user != null) {
            log.info("Deleting user (id : {}) from the database", id);
            userRepo.delete(user);
            return user;
        } else return null;
    }


    @Override
    public Role saveRole(Role role) {

        if(roleRepo.findRoleById(role.getId()) == null)
        {
            log.info("Saving new {} to the database", role.getName());
            return roleRepo.save(role);

        }
        log.info("Role of {} is already registered on database", role.getName());
        return null;


    }
    @Override
    public User updateUser(User user) {


        User userFromDb = userRepo.findUserById(user.getId());
        // crush the variables of the object found

        if (userFromDb != null) {
            log.info("Update user (id : {}) from the database", user.getId());
            userFromDb.setName(user.getName());
            userFromDb.setRoles(user.getRoles());
            userRepo.save(userFromDb);


            return userFromDb;
        } else return null;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

        log.info("Adding {} to {} on the database", roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {

        log.info("getting {} from the database", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {

        log.info("getting all user from the database");
        return userRepo.findAll();
    }




}
